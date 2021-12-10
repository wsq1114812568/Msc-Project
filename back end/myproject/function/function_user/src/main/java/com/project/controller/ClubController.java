package com.project.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.common.result.Result;
import com.project.entity.AttendEntity;
import com.project.entity.ClubEntity;
import com.project.entity.Competition;
import com.project.service.ClubService;
import com.project.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/club")
@CrossOrigin
public class ClubController {

    @Autowired
    private ClubService clubService;
    @Autowired
    private CompetitionService competitionService;

    //query
    @PostMapping("findPage/{current}/{limit}")
    public Result findPage(@PathVariable long current, @PathVariable long limit){
        Page<ClubEntity> page=new Page<>(current,limit);
        Page<ClubEntity> pageClubEntity=clubService.page(page);
        return Result.ok(pageClubEntity);
    }

    //delete club
    @DeleteMapping("delete/{id}")
    public Result remove(@PathVariable Long id){
        boolean delete=clubService.removeById(id);
        if(delete){
            return Result.ok();
        }
        else{
            return Result.fail();
        }
    }

    //add new club
    @PostMapping("add")
    public Result save(@RequestBody ClubEntity clubEntity){
        boolean add=clubService.save(clubEntity);
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
        ClubEntity clubEntity=clubService.getById(id);
        return Result.ok(clubEntity);
    }

    //get club list
    @GetMapping("getList")
    public Result getClubList(){
        List<ClubEntity> list=clubService.list();
        return Result.ok(list);
    }

    //update
    @PostMapping("update")
    public Result update(@RequestBody ClubEntity clubEntity){
        boolean update=clubService.updateById(clubEntity);
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
        clubService.removeByIds(idList);
        return Result.ok();
    }

    //according to the clubid, query the list of its competition
    @GetMapping("findCompetition/{clubid}")
    public Result findCompetition(@PathVariable Long clubid){
        List<Competition> list=clubService.findCompetition(clubid);
        for(Competition c:list){
            Long competitionid=c.getCompetitionid();
            List<AttendEntity> user = competitionService.findUser(competitionid);
            Long now=new Long(((long)user.size()));
            Long lack=c.getNumber()-now;
            c.setNow(now);
            c.setLack(lack);
        }
        return Result.ok(list);
    }
    
}
