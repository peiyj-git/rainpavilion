package com.rainpavilion.utils.jwtUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.Map;

public class JwtUtils {

    private static final String SING = "!Q@W#E$%SFG^R";

    //生成token header.payload.sing
    public static String getToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();

        //设置令牌过期时长 (20秒)
        instance.add(Calendar.SECOND,60);

        //设置令牌过期时长 (七天)
//        instance.add(Calendar.DATE,7);

        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        //payload
        map.forEach((k,v) -> {
            builder.withClaim(k,v);
        });

        String token =
                builder.withExpiresAt(instance.getTime())
                    //sign
                    .sign(Algorithm.HMAC256(SING));
        System.out.println(token);

        return token;
    }

    //验证token合法性
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

    //获取token信息方法
//    public static DecodedJWT getTokenInfo(String token){
//        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
//        return verify;
//    }


}
