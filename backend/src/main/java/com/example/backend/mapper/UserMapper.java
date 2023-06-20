package com.example.backend.mapper;
import java.util.List;

import com.example.backend.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 13547
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-05-22 22:59:54
* @Entity com.example.backend.bean.User
*/
@Repository
public interface UserMapper extends BaseMapper<User> {
    List<User> selectAll();
}




