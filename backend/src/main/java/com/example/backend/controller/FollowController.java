package com.example.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.bean.Follow;
import com.example.backend.bean.User;
import com.example.backend.service.FollowService;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follow/")
public class FollowController {

    @Autowired
    private FollowService followService;
    @Autowired
    private UserService userService;

    /**
     * 返回关注状态
     *
     * @param userId
     * @param followId
     * @return
     */
    @GetMapping
    public String returnIsFollow(
            @RequestParam("user_id") Integer userId,
            @RequestParam("follow_id") Integer followId
    ) {
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", followId)
                .eq("follow_id", userId);
        Follow follow = followService.getOne(queryWrapper);
        return follow != null ? "{\"is_follow\" : " + follow.getIsFollow() + "}" : "{\"is_follow\" : \"0\"}";
    }

    /**
     * 更改关注状态
     * 如果未关注，则关注；如果已关注，则取消关注。
     *
     * @param userId   当前用户id
     * @param followId 要关注的id
     * @return 成功保存返回result: success 失败返回result: failed
     * 并且返回is_follow(是否关注)和count(粉丝数量)
     */
    @PostMapping
    public String modifyFollowState(
            @RequestParam("user_id") Integer userId,
            @RequestParam("follow_id") Integer followId
    ) {
        if (userId.equals(followId)) return "{\"result\" : \"failed\"}";

        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();

        // 先判断该用户是否已经关注了
        queryWrapper.eq("user_id", followId)
                .eq("follow_id", userId);
        Follow follow = followService.getOne(queryWrapper);
        queryWrapper1.eq("id", followId);
        User user = userService.getOne(queryWrapper1);
        if (follow != null) {
            if (follow.getIsFollow() == 0) {
                follow.setIsFollow(1);
                user.setFollowerCount(user.getFollowerCount() + 1);
            } else if (follow.getIsFollow() == 1) {
                follow.setIsFollow(0);
                user.setFollowerCount(user.getFollowerCount() - 1);
            }
        } else { // 关系不存在 新增对应关系
            follow = new Follow();
            follow.setUserId(followId);
            follow.setFollowId(userId);
            follow.setIsFollow(1);
            user.setFollowerCount(user.getFollowerCount() + 1);
        }

        userService.updateById(user);

        return followService.saveOrUpdate(follow) ? "{\"result\" : \"success\"}" : "{\"result\" : \"failed\"}";
    }
}
