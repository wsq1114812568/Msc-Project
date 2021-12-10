package com.project.controller;

import com.project.common.result.Result;
import com.project.common.util.RandomCodeUtil;
import com.project.entity.Code;
import com.project.mapper.CodeMapper;
import com.project.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class CodeController {
    @Autowired
    private CodeService codeService;

    //generate code to database
    @GetMapping("send/{phone}")
    public Result generateCode(@PathVariable String phone){
        //generate the code and put it into database
        String code= RandomCodeUtil.getSixBitRandom();
        Code c=new Code();
        c.setCode(code);
        c.setId("1");
        c.setPhone(phone);
        codeService.save(c);
        System.out.println(code);
        return Result.ok();
    }

}
