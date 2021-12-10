package com.project.controller;

import com.project.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Friend")
@CrossOrigin
public class FriendController {
    @Autowired
    private FriendService friendService;
}
