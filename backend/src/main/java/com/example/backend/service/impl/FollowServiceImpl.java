package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.bean.Follow;
import com.example.backend.service.FollowService;
import com.example.backend.mapper.FollowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 13547
* @description 针对表【follow(关注关系表)】的数据库操作Service实现
* @createDate 2023-05-24 14:39:55
*/
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow>
    implements FollowService{

    @Autowired
    private FollowMapper followMapper;

    @Override
    public List<Follow> getAllByFollowId(Integer followId) {
        return followMapper.getAllByFollowId(followId);
    }

    @Override
    public List<Follow> getByFollowIdAndIsFollow(Integer followId, Integer isFollow) {
        return followMapper.selectAllByFollowIdAndIsFollow(followId, isFollow);
    }
}




