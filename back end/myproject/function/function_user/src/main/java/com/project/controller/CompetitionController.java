package com.project.controller;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.common.result.Result;
import com.project.common.result.ResultCodeEnum;
import com.project.common.util.AuthContextHolder;
import com.project.entity.*;
import com.project.service.AttendService;
import com.project.service.ClubService;
import com.project.service.CompetitionService;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.net.www.http.HttpClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/competition")
@CrossOrigin
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private ClubService clubService;

    @Autowired
    private UserService userService;

    @Autowired
    private AttendService attendService;

    //query
    @PostMapping("findPage/{current}/{limit}")
    public Result findPage(@PathVariable long current, @PathVariable long limit){
        Page<Competition> page=new Page<>(current,limit);
        Page<Competition> pageCompetition=competitionService.page(page);
        return Result.ok(pageCompetition);
    }

    @PostMapping("findPage3/{current}/{limit}")
    public Result findList(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) CompetitionQueryVo competitionQueryVo){
        Page<Competition> page=new Page<>(current,limit);
        QueryWrapper<Competition> wrapper=new QueryWrapper<>();
        wrapper.eq("status",0);
        String competitionname=competitionQueryVo.getCompetitionname();
        String id=competitionQueryVo.getId();
        String clubid=competitionQueryVo.getClubid();
        String type=competitionQueryVo.getType();
        if(!StringUtils.isEmpty(competitionname)){
            wrapper.like("competitionname",competitionQueryVo.getCompetitionname());
        }
        if(!StringUtils.isEmpty(id)) {
            wrapper.eq("competitionid",competitionQueryVo.getId());
        }
        if(!StringUtils.isEmpty(clubid)) {
            wrapper.eq("clubid",competitionQueryVo.getClubid());
        }
        if(!StringUtils.isEmpty(type)) {
            wrapper.eq("type",competitionQueryVo.getType());
        }
        Page<Competition> pageCompetition=competitionService.page(page,wrapper);
        return Result.ok(pageCompetition);
    }

    @PostMapping("test")
    public Result test(){
        List<Competition> c=competitionService.test();
        return Result.ok(c);
    }

    //page and join many tables
    @PostMapping("findPage2/{current}/{limit}")
    public Result findPage2(@PathVariable long current, @PathVariable long limit){
        Page<CompetitionVo> page=new Page<>(current,limit);
        Page<CompetitionVo> pageCompetition=competitionService.getName(page);
        return Result.ok(pageCompetition);
    }

    //delete
    @DeleteMapping("delete/{id}")
    public Result remove(@PathVariable Long id){
        boolean delete=competitionService.removeById(id);
        if(delete){
            return Result.ok();
        }
        else{
            return Result.fail();
        }
    }

    //add
    @PostMapping("add")
    public Result save(@RequestBody Competition competition){
        boolean add=competitionService.save(competition);
        if(add){
            return Result.ok();
        }
        else{
            return Result.fail();
        }
    }

    //get by id
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id){
        Competition competition=competitionService.getById(id);
        Long clubid=competition.getClubid();
        QueryWrapper<ClubEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("clubid",clubid);
        ClubEntity clubEntity=clubService.getOne(wrapper);
        String clubname=clubEntity.getClubname();
        String location=clubEntity.getLocation();
        competition.setClubname(clubname);
        competition.setLocation(location);
        return Result.ok(competition);
    }

    //update
    @PostMapping("update")
    public Result update(@RequestBody Competition competition){
        boolean update=competitionService.updateById(competition);
        if(update){
            return Result.ok();
        }
        else{
            return Result.fail();
        }
    }

    //batch remove
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList){
        competitionService.removeByIds(idList);
        return Result.ok();
    }

    @GetMapping("changeStatus/{competitionid}")
    public Result change(@PathVariable Long competitionid){
        boolean change=competitionService.changeStatus(competitionid);
        if(change){
            return Result.ok();
        }
        else{
            return Result.fail();
        }
    }

    @GetMapping("findUser/{competitionid}")
    public Result findUser(@PathVariable Long competitionid){
        List<AttendEntity> list=competitionService.findUser(competitionid);
        return Result.ok(list);
    }

    //user system find participants by competition
    @GetMapping("findUser2/{competitionid}")
    public Result findUser2(@PathVariable Long competitionid){
        List<AttendEntity> list=competitionService.findUser(competitionid);
        List<UserEntity> ulist=new ArrayList<>();
        for(AttendEntity a:list){
            Long userid=a.getUserid();
            QueryWrapper<UserEntity> wrapper=new QueryWrapper<>();
            wrapper.eq("id",userid);
            UserEntity u=userService.getOne(wrapper);
            ulist.add(u);
        }
        return Result.ok(ulist);
    }

    @GetMapping("findByName/{competitionName}")
    public Result findByName(@PathVariable String competitionName){
        List<Competition> list=competitionService.findByName(competitionName);
        return Result.ok(list);
    }

    //use excel to export data
    @GetMapping("exportData")
    public void exportData(HttpServletResponse response){
        competitionService.exportData(response);
    }

    //use Excel to import data
    @PostMapping("importData")
    public Result importData(MultipartFile file){
        competitionService.importData(file);
        return Result.ok();
    }

    @PostMapping("attend/{competitionid}")
    public Result attend(@PathVariable Long competitionid,HttpServletRequest request){
        Long userid= AuthContextHolder.getUserId(request);
        QueryWrapper<UserEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("id",userid);
        UserEntity userEntity=userService.getOne(wrapper);

        QueryWrapper<Competition> wrapper1=new QueryWrapper<>();
        wrapper1.eq("competitionid",competitionid);
        Competition competition=competitionService.getOne(wrapper1);

        List<AttendEntity> list=competitionService.findUser(competitionid);
        int number=list.size();
        if(number==competition.getNumber()){
            return  Result.build(202,"Attendance is full");
        }
        int gender=userEntity.getGender();
        int type=competition.getType();
        if(gender==0){
            if(type!=0&&type!=2){
                return Result.build(203,"Your gender does not meet competition requirements");
            }
        }
        else{
            if(type!=1&&type!=3){
                return Result.build(203,"Your gender does not meet competition requirements");
            }
        }
        int status=competition.getStatus();
        if(status==1){
            return Result.build(204,"The competition is completed, sorry, you miss the deadline");
        }

        QueryWrapper<AttendEntity> wrapper2=new QueryWrapper<>();
        wrapper2.eq("competitionid",competitionid);
        wrapper2.eq("userid",userid);
        AttendEntity attendEntity=attendService.getOne(wrapper2);
        if(attendEntity!=null){
            return Result.build(205,"You have attended the competition, please don't attend again!");
        }
        else{
            attendEntity=new AttendEntity();
            attendEntity.setCompetitionid(competitionid);
            attendEntity.setUserid(userid);
            attendService.save(attendEntity);
            return Result.ok();
        }
    }

}
