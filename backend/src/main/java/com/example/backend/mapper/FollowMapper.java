package com.example.backend.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.example.backend.bean.Follow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 13547
* @description 针对表【follow(关注关系表)】的数据库操作Mapper
* @createDate 2023-05-24 14:39:55
* @Entity com.example.backend.bean.Follow
*/
@Repository
public interface FollowMapper extends BaseMapper<Follow> {
    List<Follow> getAllByFollowId(@Param("followId") Integer followId);

    List<Follow> selectAllByFollowIdAndIsFollow(@Param("followId") Integer followId, @Param("isFollow") Integer isFollow);
}




