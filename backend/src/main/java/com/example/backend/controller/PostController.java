package com.example.backend.controller;

import com.example.backend.bean.Post;
import com.example.backend.bean.User;
import com.example.backend.service.PostService;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @GetMapping("/10")
    public List<Post> getPostsLimit10() {
        List<Post> posts = postService.getPostsLimit10();
        posts.forEach(post -> {
            User user = userService.getById(post.getUserId());
            post.setUsername(user.getUsername());
            post.setPhoto(user.getPhoto());
            post.setFollowerCount(user.getFollowerCount());
        });
        return posts;
    }

    /**
     * 根据帖子信息和用户id进行帖子信息的保存
     * @param content
     * @param userId
     * @return 成功保存返回result: success 失败返回result: failed
     */
    @PostMapping
    public String savePost(
            @RequestParam("content") String content,
            @RequestParam("userId") Integer userId
    ) {
        Post post = new Post();
        post.setContent(content);
        post.setUserId(userId);
        return postService.save(post) ? "{\"result\": \"success\", \"post_id\" : \"" + postService.getLastPost().getId() + "\"}" : "{\"result\": \"failed\"}";
    }

    /**
     * 根据用户id获取该用户的所有帖子信息
     * @param userId
     * @return
     */
    @GetMapping("/")
    public List<Post> getPostByUserId(@RequestParam("userId") Integer userId) {
        return postService.getAllByUserIdOrderByIdDesc(userId);
    }

    /**
     * 根据帖子id删除一条帖子
     * @param id
     * @return 成功保存返回result: success 失败返回result: failed
     */
    @DeleteMapping
    public String dropPost(@RequestParam("post_id") Integer id) {
        return postService.removeById(id) ? "{\"result\": \"success\"}" : "{\"result\": \"failed\"}";
    }

    /**
     * 修改帖子内容
     * @param id
     * @param content
     * @return 成功保存返回result: success 失败返回result: failed
     */
    @PutMapping("/")
    public String modifyPost(
            @RequestParam("id") Integer id,
            @RequestParam("content") String content
    ) {
        Post post = new Post();
        post.setContent(content);
        post.setId(id);
        return postService.updateById(post) ? "{\"result\": \"success\"}" : "{\"result\": \"failed\"}";
    }
}
