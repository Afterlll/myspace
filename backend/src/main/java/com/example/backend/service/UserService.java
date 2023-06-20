package com.example.backend.service;

import com.example.backend.bean.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 13547
* @description 针对表【user】的数据库操作Service
* @createDate 2023-05-22 22:59:54
*/
public interface UserService extends IService<User> {
    List<User> getAll();
}
