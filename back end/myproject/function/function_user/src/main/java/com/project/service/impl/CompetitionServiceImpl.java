package com.project.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.entity.*;
import com.project.listener.CompetitionListener;
import com.project.mapper.CompetitionMapper;
import com.project.service.AttendService;
import com.project.service.CompetitionService;
import com.project.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompetitionServiceImpl extends ServiceImpl<CompetitionMapper, Competition> implements CompetitionService{

    @Autowired
    private AttendService attendService;
    @Autowired
    private UserService userService;

    //join many tables
    @Override
    public List<Competition> test() {
        List<Competition> c=baseMapper.getCompetition();
        return c;
    }

    //join many tables and page
    @Override
    public Page<CompetitionVo> getName(Page<CompetitionVo> page) {
        return page.setRecords(this.baseMapper.getName(page));
    }

    @Override
    public boolean changeStatus(Long competitionid) {
        Competition competition=baseMapper.selectById(competitionid);
        boolean changeStatus=false;
        if(competition.getStatus()==0){
            competition.setStatus(1);
            competition.setLocked(false);
            baseMapper.updateById(competition);
            changeStatus=true;
        }
        else{
            competition.setStatus(0);
            competition.setLocked(true);
            baseMapper.updateById(competition);
            changeStatus=true;
        }

        return changeStatus;
    }

    @Override
    public List<AttendEntity> findUser(Long competitionid) {
        QueryWrapper<AttendEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("competitionid",competitionid);
        List<AttendEntity> attendEntityList=attendService.list(wrapper);

        QueryWrapper<Competition> wrapper2=new QueryWrapper<>();
        wrapper2.eq("competitionid",competitionid);
        Competition competition=baseMapper.selectOne(wrapper2);
        String name=competition.getCompetitionname();

        for(AttendEntity a:attendEntityList){
            Long userid=a.getUserid();
            QueryWrapper<UserEntity> wrapper3=new QueryWrapper<>();
            wrapper3.eq("id",userid);
            UserEntity userEntity=userService.getOne(wrapper3);
            String username=userEntity.getUsername();
            a.setCompetitionName(name);
            a.setUserName(username);
        }
        return attendEntityList;
    }

    @Override
    public List<Competition> findByName(String name) {
        QueryWrapper<Competition> wrapper=new QueryWrapper<>();
        wrapper.like("competitionname",name);
        List<Competition> list=baseMapper.selectList(wrapper);
        return list;
    }

    @Override
    public void exportData(HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = "competition";
        response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");
        List<Competition> competitions = baseMapper.selectList(null);
        List<CompetitionExcel> list=new ArrayList<>();
        for(Competition c:competitions){
            CompetitionExcel competitionExcel=new CompetitionExcel();
            BeanUtils.copyProperties(c,competitionExcel);
            list.add(competitionExcel);
        }
        try {
            EasyExcel.write(response.getOutputStream(),CompetitionExcel.class).sheet("competition").doWrite(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),CompetitionExcel.class,new CompetitionListener(baseMapper)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
