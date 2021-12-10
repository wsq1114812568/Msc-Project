package com.project.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.common.JwtHelper;
import com.project.common.exception.MyProjectException;
import com.project.common.result.ResultCodeEnum;
import com.project.entity.*;
import com.project.mapper.UserMapper;
import com.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    @Autowired
    private CodeService codeService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private AttendService attendService;

    @Autowired
    private CompetitionService competitionService;

    //login by phone and verification code
    @Override
    public Map<String, Object> loginUser(Loginvo loginvo) {
        //get phone number and verification code from front end
        String phone=loginvo.getPhone();
        String code=loginvo.getCode();
        //judge whether they are null
        if(StringUtils.isEmpty(phone)||StringUtils.isEmpty(code)){
            throw new MyProjectException(ResultCodeEnum.FAIL);
        }
        //judge whether it is the first time to login
        QueryWrapper<UserEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("phone",phone);
        UserEntity userEntity=baseMapper.selectOne(wrapper);
        //if is, register this phone
        if(userEntity==null){
            //add information into database
            userEntity=new UserEntity();
            userEntity.setUsername("");
            userEntity.setPhone(phone);
            userEntity.setPassword("");
            baseMapper.insert(userEntity);
        }
        //if not, login,judge the code whether right
        QueryWrapper<Code> wrapper1=new QueryWrapper<>();
        wrapper.eq("phone",phone);
        Code codeInDatabase=codeService.getOne(wrapper1);
        //if not right, login fail
        if(!codeInDatabase.getCode().equals(code)){
            throw new MyProjectException(ResultCodeEnum.FAIL);
        }
        //if right, login successfully, return token and name
        Map<String,Object> map=new HashMap<>();
        String name=userEntity.getUsername();
        if(StringUtils.isEmpty(name)){
            name=userEntity.getPhone();
        }
        map.put("name",name);
        //gnerate a token
        String token=JwtHelper.createToken(userEntity.getId(),userEntity.getUsername());
        map.put("token",token);
        return map;
    }

    @Override
    public Boolean registerUser(Loginvo loginvo) {
        String name=loginvo.getName();
        String phone=loginvo.getPhone();
        String password=loginvo.getPassword();
        QueryWrapper<UserEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("phone",phone);
        UserEntity userEntity=baseMapper.selectOne(wrapper);
        Boolean re=false;
        if(userEntity==null){
            userEntity=new UserEntity();
            userEntity.setUsername(name);
            userEntity.setPhone(phone);
            userEntity.setPassword(password);
            baseMapper.insert(userEntity);
            re=true;
        }
        else{
            userEntity.setUsername(name);
            userEntity.setPassword(password);
            baseMapper.updateById(userEntity);
            re=true;
        }
        return re;
    }

    @Override
    public Map<String, Object> loginByName(Loginvo loginvo) {
        String name=loginvo.getName();
        String password=loginvo.getPassword();
        QueryWrapper<UserEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("username",name);
        UserEntity userEntity=baseMapper.selectOne(wrapper);
        if(userEntity==null){
            throw new MyProjectException(ResultCodeEnum.FAIL);
        }
        if(!userEntity.getPassword().equals(password)){
            throw new MyProjectException(ResultCodeEnum.FAIL);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("name",name);
        String token=JwtHelper.createToken(userEntity.getId(),userEntity.getUsername());
        map.put("token",token);
        return map;
    }

    @Override
    public List<FriendshipEntity> findFriend(Long userid) {
        QueryWrapper<FriendshipEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("userid",userid);
        wrapper.eq("status",1);
        List<FriendshipEntity> friendshipEntityList=friendService.list(wrapper);
        for(FriendshipEntity f:friendshipEntityList ){
            Long friendid=f.getFriendid();
            QueryWrapper<UserEntity> wrapper2=new QueryWrapper<>();
            wrapper2.eq("id",friendid);
            UserEntity userEntity=baseMapper.selectOne(wrapper2);
            String name=userEntity.getUsername();
            String phone=userEntity.getPhone();
            f.setFriendname(name);
            f.setFriendphone(phone);
        }
        return friendshipEntityList;
    }

    @Override
    public List<AttendEntity> findCompetition(Long userid) {
        QueryWrapper<AttendEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("userid",userid);
        List<AttendEntity> attendEntityList=attendService.list(wrapper);

        QueryWrapper<UserEntity> wrapper2=new QueryWrapper<>();
        wrapper2.eq("id",userid);
        UserEntity userEntity=baseMapper.selectOne(wrapper2);
        String username=userEntity.getUsername();

        for(AttendEntity a:attendEntityList){
            Long competitionid=a.getCompetitionid();
            QueryWrapper<Competition> wrapper3=new QueryWrapper<>();
            wrapper3.eq("competitionid",competitionid);
            Competition competition=competitionService.getOne(wrapper3);
            String name=competition.getCompetitionname();
            a.setCompetitionName(name);
            a.setUserName(username);
        }
        return attendEntityList;
    }

    @Override
    public boolean addFriend(Long userid, Long friendid) {
        boolean add=false;
        FriendshipEntity friendshipEntity=new FriendshipEntity();
        friendshipEntity.setUserid(userid);
        friendshipEntity.setFriendid(friendid);
        friendService.save(friendshipEntity);
        return add=true;
    }

    @Override
    public boolean deleteFriend(Long userid, Long friendid) {
        boolean delete=false;
        QueryWrapper<FriendshipEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("userid",userid);
        wrapper.eq("friendid",friendid);
        FriendshipEntity friendshipEntity=friendService.getOne(wrapper);
        if(friendshipEntity!=null){
            friendService.removeById(friendshipEntity.getId());
            delete=true;
        }
       return delete;
    }

    @Override
    public boolean changeFriendStatus(Long userid, Long friendid) {
        QueryWrapper<FriendshipEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("userid",userid);
        wrapper.eq("friendid",friendid);
        FriendshipEntity friendshipEntity=friendService.getOne(wrapper);

        friendshipEntity.setStatus(1);
//        System.out.println(friendshipEntity);
        friendService.updateById(friendshipEntity);

        Long newUserid=friendid;
        Long newFriendid=userid;
        QueryWrapper<FriendshipEntity> wrapper2=new QueryWrapper<>();
        wrapper2.eq("userid",newUserid);
        wrapper2.eq("friendid",newFriendid);
        FriendshipEntity friendshipEntity2=friendService.getOne(wrapper2);
        if(friendshipEntity2==null){
            friendshipEntity2=new FriendshipEntity();
            friendshipEntity2.setFriendid(newFriendid);
            friendshipEntity2.setUserid(newUserid);
            friendshipEntity2.setStatus(1);
            System.out.println(friendshipEntity2);
            friendService.save(friendshipEntity2);
        }
        else{
            friendshipEntity2.setStatus(1);
            friendService.updateById(friendshipEntity2);
        }
        return true;
    }

    @Override
    public List<FriendshipEntity> findfriendRequest(Long userid) {
        QueryWrapper<FriendshipEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("userid",userid);
        wrapper.eq("status",0);
        List<FriendshipEntity> friendshipEntityList=friendService.list(wrapper);
        for(FriendshipEntity f:friendshipEntityList ){
            Long friendid=f.getFriendid();
            QueryWrapper<UserEntity> wrapper2=new QueryWrapper<>();
            wrapper2.eq("id",friendid);
            UserEntity userEntity=baseMapper.selectOne(wrapper2);
            String name=userEntity.getUsername();
            String phone=userEntity.getPhone();
            f.setFriendname(name);
            f.setFriendphone(phone);
        }
        return friendshipEntityList;
    }
}
