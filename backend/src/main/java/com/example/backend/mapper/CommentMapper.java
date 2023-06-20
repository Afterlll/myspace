package com.example.backend.mapper;
import org.apache.ibatis.annotations.Param;

import com.example.backend.bean.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author 13547
* @description 针对表【comment(保存帖子评论的表)】的数据库操作Mapper
* @createDate 2023-05-27 17:41:12
* @Entity com.example.backend.bean.Comment
*/
@Repository
public interface CommentMapper extends BaseMapper<Comment> {
    List<Comment> getAllByPostId(@Param("postId") Integer postId);

    int deleteByPostIdAndUserIdAndCommentContent(@Param("postId") Integer postId, @Param("userId") Integer userId, @Param("commentContent") String commentContent);
}




