package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.bean.Comment;
import com.example.backend.service.CommentService;
import com.example.backend.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 13547
* @description 针对表【comment(保存帖子评论的表)】的数据库操作Service实现
* @createDate 2023-05-27 17:41:12
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public List<Comment> getAllByPostId(Integer postId) {
        return commentMapper.getAllByPostId(postId);
    }

    @Override
    public int removeByPostIdAndUserIdAndCommentContent(Integer postId, Integer userId, String commentContent) {
        return commentMapper.deleteByPostIdAndUserIdAndCommentContent(postId, userId, commentContent);
    }
}




