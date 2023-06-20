package com.example.backend.service;

import com.example.backend.bean.Follow;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 13547
* @description 针对表【follow(关注关系表)】的数据库操作Service
* @createDate 2023-05-24 14:39:55
*/
public interface FollowService extends IService<Follow> {
    List<Follow> getAllByFollowId(Integer followId);
    List<Follow> getByFollowIdAndIsFollow(@Param("followId") Integer followId, @Param("isFollow") Integer isFollow);
}
