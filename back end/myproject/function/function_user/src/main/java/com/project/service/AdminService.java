package com.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.entity.AdminEntity;
import com.project.entity.Loginvo;

import java.util.Map;


public interface AdminService extends IService<AdminEntity> {

    Map<String, Object> loginByName(Loginvo loginvo);
}
