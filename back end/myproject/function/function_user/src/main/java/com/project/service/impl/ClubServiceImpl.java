package com.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.entity.ClubEntity;
import com.project.entity.Competition;
import com.project.entity.UserEntity;
import com.project.mapper.ClubMapper;
import com.project.service.ClubService;
import com.project.service.CompetitionService;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubServiceImpl extends ServiceImpl<ClubMapper, ClubEntity> implements ClubService {
    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private UserService userService;


    @Override
    public List<Competition> findCompetition(Long clubid) {
        QueryWrapper<Competition> wrapper=new QueryWrapper<>();
        wrapper.eq("clubid",clubid);
        List<Competition> competitionList=competitionService.list(wrapper);
//        for(Competition c:competitionList){
//            Long winneerid=c.getWinnerid();
//            boolean has=hasWinner(winneerid);
//            c.setHasChildren(has);
//        }
        return competitionList;
    }

    private boolean hasWinner(Long winnerid){
        QueryWrapper<UserEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("id",winnerid);
        UserEntity userEntity=userService.getOne(wrapper);
        if(userEntity==null){
            return false;
        }
        else{
            return true;
        }
    }
}
