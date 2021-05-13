package com.rainpavilion.controller;

import com.rainpavilion.entity.dto.AdminDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/adminLogin")
    public Map<String,Object> selectUser(String adminPhone, String adminPassword, String code ,HttpSession session){
        String codecode = (String)session.getAttribute("code");
        Map<String,Object>map=new HashMap<>();
        if(codecode.equals(code)){
            //1.封装token
            UsernamePasswordToken token = new UsernamePasswordToken(adminPhone,adminPassword);
            System.out.println("==============="+token);
            //2.获取主体
            Subject subject = SecurityUtils.getSubject();
            //3.认证
            try {
                subject.login(token);
                map.put("flag",1);
            } catch (AuthenticationException e) {
                map.put("flag",2);
            }
        }else {
            map.put("flag",3);
        }
        return map;
    }

}
