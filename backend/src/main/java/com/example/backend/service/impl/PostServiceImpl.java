package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.bean.Post;
import com.example.backend.service.PostService;
import com.example.backend.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 13547
* @description 针对表【post(用户帖子)】的数据库操作Service实现
* @createDate 2023-05-23 21:23:02
*/
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService{

    @Autowired
    private PostMapper postMapper;

    @Override
    public List<Post> getAllByUserIdOrderByIdDesc(Integer userId) {
        return postMapper.selectAllByUserIdOrderByIdDesc(userId);
    }

    @Override
    public List<Post> getAllByUserId(Integer userId) {
        return postMapper.selectAllByUserId(userId);
    }

    @Override
    public Post getLastPost() {
        return postMapper.selectLastPost();
    }

    @Override
    public List<Post> getPostsLimit10() {
        return postMapper.selectPostsLimit10();
    }
}




