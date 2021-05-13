package com.rainpavilion.config.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.rainpavilion.utils.jwtUtils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
        //获取请求头里的令牌
        String token = request.getHeader("token");
        log.info("当前token是:[{}]",token);
        Map<String,Object> map = Maps.newHashMap();
        try {
            //验证令牌
            JwtUtils.verify(token);
            return true;

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
        //将map转化为json jackson
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        map.put("state",false);
        return false;
    }

}
