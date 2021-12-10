package com.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.entity.ResultEntity;
import com.project.mapper.ResultMapper;
import com.project.service.ResultService;
import org.springframework.stereotype.Service;

@Service
public class ResultServiceImpl extends ServiceImpl<ResultMapper,ResultEntity> implements ResultService {

}
