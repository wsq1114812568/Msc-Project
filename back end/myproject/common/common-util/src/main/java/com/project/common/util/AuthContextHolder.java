package com.project.common.util;

import com.project.common.JwtHelper;

import javax.servlet.http.HttpServletRequest;

public class AuthContextHolder {
    public static Long getUserId(HttpServletRequest request){
        String token=request.getHeader("token");
        Long userId= JwtHelper.getUserId(token);
        return userId;
    }

    public static String getUserName(HttpServletRequest request){
        String token=request.getHeader("token");
        String userName=JwtHelper.getUserName(token);
        return userName;
    }
}
