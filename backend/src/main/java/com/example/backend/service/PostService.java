package com.example.backend.service;

import com.example.backend.bean.Post;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 13547
* @description 针对表【post(用户帖子)】的数据库操作Service
* @createDate 2023-05-23 21:23:02
*/
public interface PostService extends IService<Post> {
    List<Post> getAllByUserIdOrderByIdDesc(Integer userId);
    List<Post> getAllByUserId(Integer userId);
    Post getLastPost();
    List<Post> getPostsLimit10();
}
