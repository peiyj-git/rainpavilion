package com.rainpavilion.entity.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class AdminDTO {
    //id
    private Integer adminId;
    //电话
    private String adminPhone;
    //名字
    private String adminName;
    //密码 明文
    private String adminPassword;
    //密码 密文
    private String adminPasswordCipher;
    //盐值
    private String adminSalt;
    //创建时间
    private Date adminCreationTime;
    //创建ip地址
    private String adminCreationAddress;
    //验证码
    private String code;
    //token
    private String token;
}
