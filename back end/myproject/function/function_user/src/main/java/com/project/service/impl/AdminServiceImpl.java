package com.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.common.JwtHelper;
import com.project.common.exception.MyProjectException;
import com.project.common.result.ResultCodeEnum;
import com.project.entity.AdminEntity;
import com.project.entity.Loginvo;
import com.project.entity.UserEntity;
import com.project.mapper.AdminMapper;
import com.project.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminEntity> implements AdminService {


    @Override
    public Map<String, Object> loginByName(Loginvo loginvo) {
        String adminname=loginvo.getName();
        String password=loginvo.getPassword();
        QueryWrapper<AdminEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("adminname",adminname);
        AdminEntity adminEntity=baseMapper.selectOne(wrapper);
        if(adminEntity==null){
            throw new MyProjectException(ResultCodeEnum.FAIL);
        }
        if(!adminEntity.getPassword().equals(password)){
            throw new MyProjectException(ResultCodeEnum.FAIL);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("adminname",adminname);
        String token= JwtHelper.createToken(adminEntity.getId(),adminEntity.getAdminname());
        map.put("token",token);
        return map;
    }
}
