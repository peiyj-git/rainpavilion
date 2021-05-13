package com.rainpavilion.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import com.rainpavilion.entity.dto.AdminDTO;
import com.rainpavilion.entity.shiro.Admin;
import com.rainpavilion.entity.vo.Response;
import com.rainpavilion.entity.vo.ResponseCodeEnum;
import com.rainpavilion.mapper.AdminMapper;
import com.rainpavilion.utils.jwtUtils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping("/jwt")
@Slf4j
public class JwtLoginController {
    @Autowired
    private AdminMapper adminMapper;

    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody AdminDTO adminDTO){
        Admin admin = new Admin();
        admin.setAdminPhone(adminDTO.getAdminPhone());
        admin.setAdminPassword(adminDTO.getAdminPassword());
        log.info("用户账号:[{}]",admin.getAdminPhone());
        log.info("用户密码:[{}]",admin.getAdminPassword());

        Admin adm = adminMapper.selectOne(admin);

        Map<String,Object> map = Maps.newHashMap();

        if(adm == null){
            log.info("该用户不存在");
            map.put("400","账号或密码错误");
            return map;
        }
        log.info("用户信息:[{}]",adm);
        try{
            Map<String,String> payload = Maps.newHashMap();

            payload.put("adminId",toString(adm.getAdminId()));
            payload.put("adminName",adm.getAdminName());
            String token = JwtUtils.getToken(payload);
            map.put("state",true);
            map.put("msg","认证成功");
            map.put("token",token);
        }catch (Exception e){
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        return map;
    }

    @PostMapping("/test")
    public Map<String,Object> test(@RequestBody AdminDTO adminDTO){
        Map <String,Object> map = Maps.newHashMap();
        log.info("当前token是:[{}]",adminDTO.getToken());
        try {
            //验证令牌
            DecodedJWT verify = JwtUtils.verify(adminDTO.getToken());

            String adminId = verify.getClaim("adminId").asString();
            if(adminId != null){
                System.err.println(Integer.valueOf(adminId));
            }else {
                System.out.println("null");
            }


            System.err.println(verify.getClaim("adminName").asString());
            System.err.println("过期时间" + verify.getExpiresAt());

            map.put("state",true);
            map.put("msg","请求成功");
            return map;
            //异常细腻度处理
        }catch (SignatureVerificationException e) {
            //签名不一致异常
            map.put("mag","无效签名");
            e.printStackTrace();
        }catch (TokenExpiredException e){
            //令牌过期异常
            map.put("mag","token过期");
            e.printStackTrace();
        }catch (AlgorithmMismatchException e){
            //算法不匹配异常
            map.put("mag","算法不一致");
            e.printStackTrace();
        }catch (InvalidClaimException e){
            //失效的payload异常
            map.put("mag","失效的payload异常");
            e.printStackTrace();
        }catch (Exception e){
            //总异常
            map.put("mag","token无效");
            e.printStackTrace();
        }
        map.put("state",false);
        return map;
    }

    //把Integer转为String
    public static String toString(Object obj) {
        return (obj == null) ? null : obj.toString();
    }

}

























