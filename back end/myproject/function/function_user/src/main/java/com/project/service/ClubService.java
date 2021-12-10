package com.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.entity.ClubEntity;
import com.project.entity.Competition;

import java.util.List;


public interface ClubService extends IService<ClubEntity> {

    List<Competition> findCompetition(Long clubid);
}
