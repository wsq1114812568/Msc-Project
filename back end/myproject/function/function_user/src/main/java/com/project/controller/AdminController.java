package com.project.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.common.result.Result;
import com.project.entity.*;
import com.project.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminService adminService;
    private String name1;


    @PostMapping("login")
    public Result login(@RequestBody Loginvo loginvo){
        String adminname=loginvo.getName();
        String password=loginvo.getPassword();
        this.name1=adminname;
        Map<String,Object> info=adminService.loginByName(loginvo);
        String token=info.get("token").toString();
        return Result.ok().data2("token",token);
    }

    @GetMapping("info")
    public Result info(){
        System.out.println(name1);
        return  Result.ok().data2("roles","[admin]").data2("name",name1).data2("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    @PostMapping("findPage/{current}/{limit}")
    public Result findPageUser(@PathVariable long current, @PathVariable long limit){
        Page<AdminEntity> page=new Page<>(current,limit);
        Page<AdminEntity> pageAdminEntity=adminService.page(page);
        return Result.ok(pageAdminEntity);
    }

    //delete admin
    @DeleteMapping("delete/{id}")
    public Result removeUser(@PathVariable Long id){
        boolean delete=adminService.removeById(id);
        if(delete){
            return Result.ok();
        }
        else{
            return Result.fail();
        }
    }

    //add new admin
    @PostMapping("add")
    public Result saveUser(@RequestBody AdminEntity adminEntity){
        boolean add=adminService.save(adminEntity);
        if(add){
            return Result.ok();
        }
        else{
            return Result.fail();
        }
    }

    //get by id
    @GetMapping("get/{id}")
    public Result getUser(@PathVariable Long id){
        AdminEntity adminEntity=adminService.getById(id);
        return Result.ok(adminEntity);

    }

    //update
    @PostMapping("update")
    public Result updateUser(@RequestBody AdminEntity adminEntity){
        boolean update=adminService.updateById(adminEntity);
        if(update){
            return Result.ok();
        }
        else{
            return Result.fail();
        }
    }

    //batch remove
    @DeleteMapping("batchRemove")
    public Result batchRemoveUser(@RequestBody List<Long> idList){
        adminService.removeByIds(idList);
        return Result.ok();
    }

}
