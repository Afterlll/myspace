package com.example.backend.service;

import com.example.backend.bean.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 13547
* @description 针对表【comment(保存帖子评论的表)】的数据库操作Service
* @createDate 2023-05-27 17:41:12
*/
public interface CommentService extends IService<Comment> {
    List<Comment> getAllByPostId(@Param("postId") Integer postId);
    int removeByPostIdAndUserIdAndCommentContent(@Param("postId") Integer postId, @Param("userId") Integer userId, @Param("commentContent") String commentContent);

}
