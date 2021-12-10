package com.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.common.result.Result;
import com.project.common.util.AuthContextHolder;
import com.project.entity.MessageEntity;
import com.project.entity.MessageVo;
import com.project.entity.UserQueryVo;
import com.project.service.MessageService;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/message")
@CrossOrigin
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @PostMapping("sendMessage/{friendid}")
    public Result sendMessage(@PathVariable Long friendid, HttpServletRequest request, @RequestBody(required = false)MessageVo messageVo){
        Long userid= AuthContextHolder.getUserId(request);
        MessageEntity messageEntity=new MessageEntity();
        messageEntity.setFriendid(friendid);
        messageEntity.setUserid(userid);
        messageEntity.setContent(messageVo.getContent());
        messageService.save(messageEntity);
        return Result.ok();
    }

    @GetMapping("showMessage")
    public Result showMessage(HttpServletRequest request){
        //you want to show messages you received, your userid actually is the friendid in the Message table
        Long userid= AuthContextHolder.getUserId(request);
        QueryWrapper<MessageEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("friendid",userid);
        List<MessageEntity> list=messageService.list(wrapper);
        for(MessageEntity m:list){
            Long id=m.getUserid();
            String name=userService.getById(id).getUsername();
            m.setUsername(name);
        }
        return Result.ok(list);
    }

    @DeleteMapping("deleteMessage/{id}")
    public Result deleteMessage(@PathVariable Long id){
        boolean delete=messageService.removeById(id);
        if(delete){
            return Result.ok();
        }
        else{
            return Result.fail();
        }
    }

}
