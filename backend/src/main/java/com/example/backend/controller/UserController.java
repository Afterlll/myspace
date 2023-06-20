package com.example.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.bean.Follow;
import com.example.backend.bean.User;
import com.example.backend.service.FollowService;
import com.example.backend.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 头像的网络映射url
     */
    @Value("${netPhotoURLHead}")
    private String netPhotoURLHead;

    @Autowired
    private UserService userService;
    @Autowired
    private FollowService followService;
    /**
     * 根据用户id获取用户信息
     * @param id
     * @return
     */
    @GetMapping("/")
    public User getUserById(@Param("id") Integer id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return userService.getOne(queryWrapper);
    }

    /**
     * 修改用户信息
     * 用来修改密码
     * @param username
     * @param password
     * @return
     */
    @PutMapping("/")
    public User modifyUser(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "birth", required = false) String birth,
            @RequestParam(value = "old_password", required = false) String oldPassword,
            @RequestParam(value = "old_username", required = false) String oldUsername,
            @RequestPart(value = "photo", required = false) MultipartFile photo
    ) {
        // 生日不为空就是修改密码
        if (birth != null) {
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("username", username);

            User user = userService.getOne(wrapper);

            if (user == null) {
                user = new User();
                user.setErrorMessage("用户名不存在");
                return user;
            }

            wrapper.eq("username", username)
                    .eq("birth", birth);
            user = userService.getOne(wrapper);

            if (user == null) {
                user = new User();
                user.setErrorMessage("生日错误，请重新输入你的密保");
                return user;
            }

            user.setPassword(password);
            userService.updateById(user);

            return user;
        }

        // 将图片保存在本地，该字段的值为在数据库中的本地地址
        // 文件命名规则：UUID
        if (photo != null) {
            if(!photo.isEmpty()) {
                String s = UUID.randomUUID().toString();
                // 动态拼接本地服务器地址
                String localFileName = "D:\\dev\\tomcat_11.0.0\\apache-tomcat-11.0.0-M4\\apache-tomcat-11.0.0-M4\\webapps\\myspace\\photo\\" + s;
                // 动态拼接网络地址
                String netFileName = "http://192.168.174.1:9999/myspace/photo/" + s;
                // 数据库存储的是本地服务器资源URL
                User user = new User();
                user.setId(id);
                user.setPhoto(netFileName);
                try {
                    // 图片资源存储在本地服务器上
                    photo.transferTo(new File(localFileName));
                    userService.updateById(user);
                    return user;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        // 判断用户名是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (oldPassword != null && !username.equals(oldUsername)) {
            queryWrapper.eq("username", oldUsername);
        } else {
            queryWrapper.eq("username", username);
        }
        User user = userService.getOne(queryWrapper);

        if (user == null) {
            user = new User();
            user.setErrorMessage("用户名不存在");
            return user;
        }

        // 根据用户传递过来的旧密码查询，判断是否跟数据库中的一致
        if (oldPassword != null) {
            if (!username.equals(oldUsername)) {
                queryWrapper.eq("username", oldUsername)
                        .eq("password", oldPassword);
            } else {
                queryWrapper.eq("username", username)
                        .eq("password", oldPassword);
            }

            user = userService.getOne(queryWrapper);

            if (user == null) {
                user = new User();
                user.setErrorMessage("您输入的旧密码错误");
                return user;
            }

            if (oldPassword.equals(password)) {
                user = new User();
                user.setErrorMessage("你要更改的密码与原来的密码一致");
                return user;
            }
        }

        user.setUsername(username);
        user.setPassword(password);
        userService.updateById(user);

        return user;
    }
    /**
     * 获取用户列表
     * 拉取的用户是用户的关注列表
     * @return
     */
    @GetMapping("/userList")
    public List<User> getUserList(@RequestParam("id") Integer id) {
        List<Follow> follows = followService.getByFollowIdAndIsFollow(id, 1);
        List<User> users = new ArrayList<>();
        follows.forEach(follow -> {
            // 根据userId查询出user信息
            users.add(userService.getById(follow.getUserId()));
        });
        return users;
    }

    /**
     * 用户登录
     * 判断用户是否存在
     * @param username
     * @param password
     * @return 用户信息
     */
    @PostMapping("/login")
    public User login(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        // 判断用户名是否存在
        queryWrapper.eq("username", username);
        User user = userService.getOne(queryWrapper);

        // 用户名不存在
        if (user == null) {
            user = new User();
            user.setErrorMessage("用户名不存在");
            return user;
        }

        // 用户名存在
        queryWrapper.eq("username", username)
                .eq("password", password);
        user = userService.getOne(queryWrapper);

        // 密码错误
        if (user == null) {
            user = new User();
            user.setErrorMessage("密码错误，请重新输入");
            return user;
        }

        return user;
    }

    /**
     * 注册用户 也就是保存用户信息
     * @param username
     * @param password
     * @param photo
     * @return 用户名
     */
    @PostMapping("/")
    public User registerUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("birth") String birth,
            @RequestPart(value = "photo", required = false) MultipartFile photo
            ) {

        // 注册前要判断用户名是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userService.getOne(queryWrapper);
        if (user != null) {
            user = new User();
            user.setErrorMessage("您要注册的用户名已存在");
            return user;
        }

        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setBirth(birth);
        // 将图片保存在本地，该字段的值为在数据库中的本地地址
        // 文件命名规则：UUID
        if (photo != null) {
            if(!photo.isEmpty()) {
                String s = UUID.randomUUID().toString();
                String localFileName = "D:\\dev\\tomcat_11.0.0\\apache-tomcat-11.0.0-M4\\apache-tomcat-11.0.0-M4\\webapps\\myspace\\photo\\" + s;
                String netFileName = "http://192.168.174.1:9999/myspace/photo/" + s;
                // 数据库存储的是本地服务器资源URL
                user.setPhoto(netFileName);
                try {
                    // 图片资源存储在本地服务器上
                    photo.transferTo(new File(localFileName));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            // 使用默认的头像
            user.setPhoto("http://192.168.174.1:9999/myspace/photo/v2-6afa72220d29f045c15217aa6b275808_720w.png");
        }
        // 保存用户信息
        userService.save(user);
        return user;
    }
}