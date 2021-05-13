package com.rainpavilion.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/code")
public class ValidateCodeController extends HttpServlet {
    @RequestMapping("/validateCode.png")
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(80, 40);
        String code =captcha.getCode();
        System.out.println("----------验证码: "+code);
        HttpSession session = req.getSession();
        session.setAttribute("code", code);
        OutputStream out = resp.getOutputStream();
        captcha.write(out);
    }

}
