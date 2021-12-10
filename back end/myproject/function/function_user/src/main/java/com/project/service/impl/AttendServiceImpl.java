package com.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.entity.AttendEntity;
import com.project.mapper.AttendMapper;
import com.project.service.AttendService;
import org.springframework.stereotype.Service;

@Service
public class AttendServiceImpl extends ServiceImpl<AttendMapper, AttendEntity> implements AttendService {

}
