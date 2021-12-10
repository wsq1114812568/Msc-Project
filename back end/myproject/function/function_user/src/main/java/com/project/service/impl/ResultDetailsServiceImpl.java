package com.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.entity.ResultDetails;
import com.project.mapper.ResultDetailsMapper;
import com.project.service.ResultDetailsService;
import org.springframework.stereotype.Service;

@Service
public class ResultDetailsServiceImpl extends ServiceImpl<ResultDetailsMapper, ResultDetails> implements ResultDetailsService {
}
