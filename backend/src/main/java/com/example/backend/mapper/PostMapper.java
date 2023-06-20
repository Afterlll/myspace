package com.example.backend.mapper;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.ibatis.annotations.Param;

import com.example.backend.bean.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 13547
* @description 针对表【post(用户帖子)】的数据库操作Mapper
* @createDate 2023-05-23 21:23:02
* @Entity com.example.backend.bean.Post
*/
@Repository
public interface PostMapper extends BaseMapper<Post> {
    /**
     * 根据用户id降序排列查询所有帖子信息
     * @param userId
     * @return
     */
    List<Post> selectAllByUserIdOrderByIdDesc(@Param("userId") Integer userId);

    /**
     * 根据用户id查询所有帖子信息
     * @param userId
     * @return
     */
    List<Post> selectAllByUserId(@Param("userId") Integer userId);

    /**
     * 查询最后一条帖子信息
     * @return
     */
    Post selectLastPost();

    Long selectCount(Wrapper<Post> queryWrapper);

    List<Post> selectPostsLimit10();
}




