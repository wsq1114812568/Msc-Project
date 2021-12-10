package com.project.controller;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.common.result.Result;
import com.project.common.util.AuthContextHolder;
import com.project.entity.*;
import com.project.service.ClubService;
import com.project.service.CompetitionService;
import com.project.service.FriendService;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private ClubService clubService;

    //1.Conditional query with paging
    @PostMapping("findPage/{current}/{limit}")
    public Result findPageUser(@PathVariable long current, @PathVariable long limit,@RequestBody(required = false) UserQueryVo userQueryVo){
        Page<UserEntity> page=new Page<>(current,limit);
        QueryWrapper<UserEntity> wrapper=new QueryWrapper<>();
        String username=userQueryVo.getUsername();
        String id=userQueryVo.getId();
        if(!StringUtils.isEmpty(username)){
            wrapper.like("username",userQueryVo.getUsername());
        }
        if(!StringUtils.isEmpty(id)) {
            wrapper.eq("id",userQueryVo.getId());
        }
        Page<UserEntity> pageUserEntity=userService.page(page,wrapper);
        return Result.ok(pageUserEntity);
    }

    //2.delete user
    @DeleteMapping("delete/{id}")
    public Result removeUser(@PathVariable Long id){
        boolean delete=userService.removeById(id);
        if(delete){
            return Result.ok();
        }
        else{
            return Result.fail();
        }
    }

    //3.add new user
    @PostMapping("add")
    public Result saveUser(@RequestBody UserEntity userEntity){
        boolean add=userService.save(userEntity);
        if(add){
            return Result.ok();
        }
        else{
            return Result.fail();
        }
    }

    //4.get by id
    @GetMapping("get/{id}")
    public Result getUser(@PathVariable Long id){
        UserEntity userEntity=userService.getById(id);
        return Result.ok(userEntity);

    }

    //5.update
    @PostMapping("update")
    public Result updateUser(@RequestBody UserEntity userEntity){
        boolean update=userService.updateById(userEntity);
        if(update){
            return Result.ok();
        }
        else{
            return Result.fail();
        }
    }

    //6.batch remove
    @DeleteMapping("batchRemove")
    public Result batchRemoveUser(@RequestBody List<Long> idList){
        userService.removeByIds(idList);
        return Result.ok();
    }

    //7.login by phone
    @PostMapping("loginByPhone")
    public Result loginByPhone(@RequestBody Loginvo loginvo){
        Map<String,Object> info=userService.loginUser(loginvo);
        return Result.ok(info);
    }

    //8.login by password and name
    @PostMapping("loginByName")
    public Result loginByName(@RequestBody Loginvo loginvo){
        Map<String,Object> info=userService.loginByName(loginvo);
        return Result.ok(info);
    }

    //9.register
    @PostMapping("register")
    public Result register(@RequestBody Loginvo loginvo){
        Boolean register=userService.registerUser(loginvo);
        if(register==true){
            return Result.ok();
        }
        else{
            return Result.fail();
        }
    }

    //admin system find users' friends
    @GetMapping("findFriend/{userid}")
    public Result findFriend(@PathVariable Long userid){
        List<FriendshipEntity> list=userService.findFriend(userid);
        return Result.ok(list);
    }

    //user system find your friends
    @GetMapping("findFriend2")
    public Result findFriend2(HttpServletRequest request){
        Long userid=AuthContextHolder.getUserId(request);
        List<FriendshipEntity> list=userService.findFriend(userid);
        return Result.ok(list);
    }

    //user system find friends with page
    @PostMapping("findFriendPage/{current}/{limit}")
    public Result findFriendPage(HttpServletRequest request,@PathVariable long current, @PathVariable long limit){
        Long userid=AuthContextHolder.getUserId(request);
        Page<FriendshipEntity> page=new Page<>(current,limit);
        QueryWrapper<FriendshipEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("userid",userid);
        Page<FriendshipEntity> pageFriendEntity=friendService.page(page,wrapper);
        return Result.ok(pageFriendEntity);
    }

    //the adding friends requests you receive
    @GetMapping("friendRequest")
    public Result friendRequest(HttpServletRequest request){
        Long userid=AuthContextHolder.getUserId(request);
        List<FriendshipEntity> list=userService.findfriendRequest(userid);
        return Result.ok(list);
    }

    //find those users who are not your friends
    @PostMapping("findUnFriendPage/{current}/{limit}")
    public Result findUnFriendPage(HttpServletRequest request,@PathVariable long current, @PathVariable long limit,@RequestBody(required = false) UserQueryVo userQueryVo){
        Long userid=AuthContextHolder.getUserId(request);
        Page<UserEntity> page=new Page<>(current,limit);
        QueryWrapper<UserEntity> wrapper=new QueryWrapper<>();
        wrapper.ne("id",userid);
        List<FriendshipEntity> list=userService.findFriend(userid);
        for(FriendshipEntity f:list){
            Long friendid=f.getFriendid();
            wrapper.ne("id",friendid);
        }
        String username=userQueryVo.getUsername();
        String id=userQueryVo.getId();
        if(!StringUtils.isEmpty(username)){
            wrapper.like("username",userQueryVo.getUsername());
        }
        if(!StringUtils.isEmpty(id)) {
            wrapper.eq("id",userQueryVo.getId());
        }

        Page<UserEntity> pageUserEntity=userService.page(page,wrapper);
        return Result.ok(pageUserEntity);
    }

    @PostMapping("addFriend/{friendid}")
    public Result addFriend(HttpServletRequest request, @PathVariable Long friendid){
        Long userid=AuthContextHolder.getUserId(request);
        QueryWrapper<FriendshipEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("userid",userid);
        wrapper.eq("friendid",friendid);
        FriendshipEntity friendshipEntity=friendService.getOne(wrapper);
        if(friendshipEntity==null){
            boolean add=userService.addFriend(userid,friendid);
            if(add==true){
                return Result.ok();
            }
            else{
                return Result.fail();
            }
        }
        else{
            return Result.ok();
        }
    }

    @GetMapping("agree/{friendid}")
    public Result change(@PathVariable Long friendid,HttpServletRequest request){
        Long userid=AuthContextHolder.getUserId(request);
        boolean change=userService.changeFriendStatus(userid,friendid);
        if(change){
            return Result.ok();
        }
        else{
            return Result.fail();
        }
    }

    @DeleteMapping("deleteFriend/{friendid}")
    public Result deleteFriend( @PathVariable Long friendid,HttpServletRequest request){
        Long userid=AuthContextHolder.getUserId(request);
        boolean delete=userService.deleteFriend(userid,friendid);
        if(delete){
            return Result.ok();
        }
        else{
            return Result.fail();
        }
    }

    //admin system get competition by user
    @GetMapping("findCompetition/{userid}")
    public Result findCompetition(@PathVariable Long userid){
        List<AttendEntity> list=userService.findCompetition(userid);
        return Result.ok(list);
    }

    //user system get competition by user
    @GetMapping("findCompetition2")
    public Result findCompetition2(HttpServletRequest request){
        Long userid=AuthContextHolder.getUserId(request);
        List<AttendEntity> list=userService.findCompetition(userid);
        List<Competition> clist=new ArrayList<>();
        for(AttendEntity a:list){
            Long competitionid=a.getCompetitionid();
            QueryWrapper<Competition> wrapper=new QueryWrapper<>();
            wrapper.eq("competitionid",competitionid);
            Competition c=competitionService.getOne(wrapper);
            QueryWrapper<ClubEntity> wrapper1=new QueryWrapper<>();
            wrapper1.eq("clubid",c.getClubid());
            ClubEntity club=clubService.getOne(wrapper1);
            c.setClubname(club.getClubname());
            c.setLocation(club.getLocation());
            clist.add(c);
        }
        return Result.ok(clist);
    }

    @GetMapping("getUserInfo")
    public Result getUserInfo(HttpServletRequest request){
        Long userId=AuthContextHolder.getUserId(request);
        UserEntity userInfo = userService.getById(userId);
        return Result.ok(userInfo);
    }
}
