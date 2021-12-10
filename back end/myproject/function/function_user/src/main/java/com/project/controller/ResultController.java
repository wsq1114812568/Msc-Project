package com.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.common.result.Result;
import com.project.entity.ResultEntity;
import com.project.entity.UserEntity;
import com.project.service.ResultService;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/result")
@CrossOrigin
public class ResultController {
    @Autowired
    private ResultService resultService;
    @Autowired
    private UserService userService;

    @GetMapping("getResult/{competitionid}")
    public Result getResult(@PathVariable Long competitionid){
        QueryWrapper<ResultEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("competitionid",competitionid);
        ResultEntity resultEntity=resultService.getOne(wrapper);
        if(resultEntity==null){
            return Result.build(206,"The competition is not completed!");
        }
        QueryWrapper<UserEntity> wrapper1=new QueryWrapper<>();
        wrapper1.eq("id",resultEntity.getFirstid());
        String firstname=userService.getOne(wrapper1).getUsername();
        resultEntity.setFirstname(firstname);
        QueryWrapper<UserEntity> wrapper2=new QueryWrapper<>();
        wrapper2.eq("id",resultEntity.getSecondid());
        String secondname=userService.getOne(wrapper2).getUsername();
        resultEntity.setSecondname(secondname);
        QueryWrapper<UserEntity> wrapper3=new QueryWrapper<>();
        wrapper3.eq("id",resultEntity.getThirdid());
        String thirdname=userService.getOne(wrapper3).getUsername();
        resultEntity.setThirdname(thirdname);

        return Result.ok(resultEntity);
    }

    @PostMapping("addOrUpdate")
    public Result addOrUpdate(@RequestBody ResultEntity resultEntity){
        Long competitionid=resultEntity.getCompetitionid();
        QueryWrapper<ResultEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("competitionid",competitionid);
        ResultEntity result=resultService.getOne(wrapper);
        boolean add=false;
        boolean update=false;
        if(result==null){
            //add
             add=resultService.save(resultEntity);
        }
        else{
            if(resultEntity.getFirstid()!=null){
                result.setFirstid(resultEntity.getFirstid());
            }
            if(resultEntity.getSecondid()!=null){
                result.setSecondid(resultEntity.getSecondid());
            }
            if(resultEntity.getThirdid()!=null){
                result.setThirdid(resultEntity.getThirdid());
            }
            update=resultService.updateById(result);
        }
        if(add==true||update==true){
            return Result.ok();
        }
        else{
            return Result.fail();
        }

    }
}
