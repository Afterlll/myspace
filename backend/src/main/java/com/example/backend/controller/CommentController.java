package com.example.backend.controller;

import com.example.backend.bean.Comment;
import com.example.backend.bean.Result;
import com.example.backend.bean.User;
import com.example.backend.service.CommentService;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/comment/")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    /**
     * 保存评论信息
     * @param postId
     * @param userId
     * @param commentContent
     * @return
     */
    @PostMapping
    public Result saveComment(
            @RequestParam("post_id") Integer postId,
            @RequestParam("user_id") Integer userId,
            @RequestParam("comment_content") String commentContent
    ) {
        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setUserId(userId);
        comment.setCommentContent(commentContent);
        Result result = new Result();
        if (commentService.save(comment)) {
            result.setResult("success");
        } else {
            result.setResult("failed");
        }
        return result;
    }

    @DeleteMapping
    public Result deleteCommentById(
            @RequestParam(value = "comment_id", required = false) Integer commentId,
            @RequestParam(value = "post_id",required = false) Integer postId,
            @RequestParam(value = "user_id",required = false) Integer userId,
            @RequestParam(value = "comment_content",required = false) String commentContent
    ) {
        System.out.println(commentId);
        System.out.println(postId);
        Result result = new Result();
        if (commentService.removeById(commentId)) {
            result.setResult("success");
        } else {
            // 根据id删除失败之后，尝试使用 postId + userId + commentContent 删除评论数据
            if (commentService.removeByPostIdAndUserIdAndCommentContent(postId, userId, commentContent) > 0) {
                result.setResult("success");
            } else {
                result.setResult("failed");
            }
        }
        return result;
    }

    @GetMapping
    List<Comment> getCommentsByPostId(@RequestParam("post_id") Integer postId) {
        List<Comment> comments = commentService.getAllByPostId(postId);
        comments.forEach(comment -> {
            User user = userService.getById(comment.getUserId());
            comment.setUsername(user.getUsername());
            comment.setPhoto(user.getPhoto());
        });
        Collections.reverse(comments);
        return comments;
    }
}
