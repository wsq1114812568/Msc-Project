package com.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.entity.FriendshipEntity;
import com.project.mapper.FriendMapper;
import com.project.service.FriendService;
import org.springframework.stereotype.Service;

@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, FriendshipEntity> implements FriendService {


}
