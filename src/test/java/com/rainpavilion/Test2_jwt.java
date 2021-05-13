package com.rainpavilion;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import com.rainpavilion.entity.shiro.Admin;
import com.rainpavilion.mapper.AdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test2_jwt {
    @Autowired
    private AdminMapper adminMapper;


    //令牌的获取
    @Test
    public void contextLoads(){
        Map<String,Object> map = Maps.newHashMap();

        //令牌过期时长 (20秒)
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,60);

        String token = JWT.create()
                //header 可以不写(默认值也是这个)
                .withHeader(map)
                //payload
                .withClaim("userId", 21)
                .withClaim("username", "peiyj")

                //指定令牌的过期时间
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256("!Q@W#E$R"));
        System.out.println(token);


    }


//    //令牌的验证
//    @Test
//    public void test(){
//        //创建验证对象
//        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("!Q@W#E$R")).build();
//
//        //验证token
//        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTkzMzMyNDgsInVzZXJJZCI6MjEsInVzZXJuYW1lIjoicGVpeWoifQ.vub0_JD2e4i-w0uFZHtJBiPhy3vazVigTbG7_qrdHHg");
//        System.out.println(verify.getClaim("userId").asInt());
//        System.out.println(verify.getClaim("username").asString());
//        System.out.println("过期时间" + verify.getExpiresAt());
//
//    }




    //模拟登录
    public void login(){
        Admin adm = new Admin();
        adm.setAdminPhone("1523803");
        Admin admin = adminMapper.selectOne(adm);

        if(admin == null){
            System.out.println("账号密码错误");
        }




    }



}































