package com.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.common.result.Result;
import com.project.common.util.AuthContextHolder;
import com.project.entity.*;
import com.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/resultDetails")
@CrossOrigin
public class ResultDetailsController {
    @Autowired
    private ResultDetailsService resultDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private ResultService resultService;
    @Autowired
    private AttendService attendService;

    @GetMapping("getResultDetails/{competitionid}")
    public Result getResultDetails(@PathVariable Long competitionid){
        QueryWrapper<ResultDetails> wrapper=new QueryWrapper<>();
        wrapper.eq("competitionid",competitionid);
        List<ResultDetails> list=resultDetailsService.list(wrapper);
        for(ResultDetails resultDetails:list){
            QueryWrapper<UserEntity> wrapper1=new QueryWrapper<>();
            wrapper1.eq("id",resultDetails.getUser1id());
            String user1name=userService.getOne(wrapper1).getUsername();
            resultDetails.setUser1name(user1name);

            QueryWrapper<UserEntity> wrapper2=new QueryWrapper<>();
            wrapper2.eq("id",resultDetails.getUser2id());
            String user2name=userService.getOne(wrapper2).getUsername();
            resultDetails.setUser2name(user2name);
        }
        return Result.ok(list);
    }

    //user system get result details by userid
    @GetMapping("getlist/{competitionid}")
    public Result getlist(HttpServletRequest request,@PathVariable Long competitionid){
        Long userid= AuthContextHolder.getUserId(request);
        String username=AuthContextHolder.getUserName(request);
        QueryWrapper<ResultDetails> wrapper1=new QueryWrapper<>();
        wrapper1.eq("competitionid",competitionid);
        wrapper1.eq("user1id",userid);
        List<ResultDetails> list1=resultDetailsService.list(wrapper1);
        QueryWrapper<ResultDetails> wrapper2=new QueryWrapper<>();
        wrapper2.eq("user2id",userid);
        List<ResultDetails> list2=resultDetailsService.list(wrapper2);
        List<ResultDetails> list3=new ArrayList<>();
        for(ResultDetails r:list1){
            QueryWrapper<UserEntity> wrapper=new QueryWrapper<>();
            wrapper.eq("id",r.getUser2id());
            UserEntity userEntity=userService.getOne(wrapper);
            QueryWrapper<Competition> wrapper3=new QueryWrapper<>();
            wrapper3.eq("competitionid",r.getCompetitionid());
            r.setCompetitionname(competitionService.getOne(wrapper3).getCompetitionname());
            r.setUser2name(userEntity.getUsername());
            r.setUser1name(username);
            list3.add(r);
        }
        for(ResultDetails r:list2){
            QueryWrapper<UserEntity> wrapper=new QueryWrapper<>();
            wrapper.eq("id",r.getUser1id());
            UserEntity userEntity=userService.getOne(wrapper);
            QueryWrapper<Competition> wrapper3=new QueryWrapper<>();
            wrapper3.eq("competitionid",r.getCompetitionid());
            r.setCompetitionname(competitionService.getOne(wrapper3).getCompetitionname());
            r.setUser1name(userEntity.getUsername());
            r.setUser2name(username);
            list3.add(r);
        }
        return Result.ok(list3);

    }

    //get final result of each competition that user attended by userid
    @GetMapping("finalResult")
    public Result finalResult(HttpServletRequest request){
        Long userid=AuthContextHolder.getUserId(request);
        String username=AuthContextHolder.getUserName(request);
        QueryWrapper<AttendEntity> wrapper2=new QueryWrapper<>();
        wrapper2.eq("userid",userid);
        List<AttendEntity> attendEntityList=attendService.list(wrapper2);
        List<ResultEntity> list=new ArrayList<>();
        for(AttendEntity a:attendEntityList){
            QueryWrapper<ResultEntity> wrapper=new QueryWrapper<>();
            wrapper.eq("competitionid",a.getCompetitionid());
            ResultEntity r=resultService.getOne(wrapper);
            if(r!=null){
                list.add(r);
            }
            else{
                ResultEntity r2=new ResultEntity();
                r2.setCompetitionid(a.getCompetitionid());
                resultService.save(r2);
                list.add(r2);
            }

        }
        List<ResultVo> list2=new ArrayList<>();
        for(ResultEntity r:list){
            ResultVo resultVo=new ResultVo();
            resultVo.setUsername(username);
            QueryWrapper<Competition> wrapper=new QueryWrapper<>();
            wrapper.eq("competitionid",r.getCompetitionid());
            resultVo.setCompetitionid(r.getCompetitionid());
            resultVo.setCompetitionname(competitionService.getOne(wrapper).getCompetitionname());
            if(r.getFirstid()==userid){
                resultVo.setResult("First Place");
            }
            else if(r.getSecondid()==userid){
                resultVo.setResult("Second Place");
            }
            else if(r.getThirdid()==userid){
                resultVo.setResult("Third Place");
            }
            else{
                resultVo.setResult("Lose");
            }
            list2.add(resultVo);
        }
        return Result.ok(list2);
    }

    @PostMapping("add")
    public Result addOrUpdate(@RequestBody ResultDetails resultDetails) {
        Long competitionid=resultDetails.getCompetitionid();
        Long user1id=resultDetails.getUser1id();
        Long user2id=resultDetails.getUser2id();
        QueryWrapper<ResultDetails> wrapper=new QueryWrapper<>();
        wrapper.eq("competitionid",competitionid);
        wrapper.eq("user1id",user1id);
        wrapper.eq("user2id",user2id);
        ResultDetails r=resultDetailsService.getOne(wrapper);
        boolean add=false;
        boolean update=false;
        if(r==null){
            QueryWrapper<ResultDetails> wrapper1=new QueryWrapper<>();
            wrapper1.eq("competitionid",competitionid);
            wrapper1.eq("user1id",user2id);
            wrapper1.eq("user2id",user1id);
            ResultDetails r1=resultDetailsService.getOne(wrapper1);
            if(r1==null){
                add=resultDetailsService.save(resultDetails);
            }
            else{
                r1.setScore1(resultDetails.getScore2());
                r1.setScore2(resultDetails.getScore1());
                update=resultDetailsService.updateById(r1);
            }
        }
        else{
            r.setUser1id(resultDetails.getUser1id());
            r.setUser2id(resultDetails.getUser2id());
            r.setScore1(resultDetails.getScore1());
            r.setScore2(resultDetails.getScore2());
            update=resultDetailsService.updateById(r);
        }
        if(add==true||update==true){
            return Result.ok();
        }
        else{
            return Result.fail();
        }
    }

}
