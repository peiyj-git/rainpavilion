
package com.rainpavilion.service.impl;

import com.rainpavilion.mapper.AdminMapper;
import com.rainpavilion.entity.shiro.Admin;
import com.rainpavilion.entity.vo.Response;
import com.rainpavilion.entity.vo.ResponseCodeEnum;
import com.rainpavilion.entity.dto.AdminDTO;
import com.rainpavilion.service.AdminService;
import com.rainpavilion.utils.IPKit;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public int addAdmin(AdminDTO adminDTO) {
        //1.生成随机的盐值
        String salt = UUID.randomUUID().toString().substring(0, 4);

        //2.加密 散列次数
        Md5Hash md5Hash = new Md5Hash(adminDTO.getAdminPassword(),salt,1024);
        String passwordCipher = md5Hash.toHex();

        //3.添加数据库
        //ip 通过请求可以获取到 Request（当前线程的Request）
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //ip工具类
        String address = IPKit.getIpAddrByRequest(request);

        Admin admin = new Admin();
        admin.setAdminPhone(adminDTO.getAdminPhone());
        admin.setAdminName(adminDTO.getAdminName());
        admin.setAdminPassword(adminDTO.getAdminPassword());
        admin.setAdminPasswordCipher(passwordCipher);
        admin.setAdminSalt(salt);
        admin.setAdminCreationTime(new Date());
        admin.setAdminCreationAddress(address);
        adminMapper.addAdmin(admin);
        return admin.getAdminId();

    }


    @Override
    public Response selectOne(AdminDTO adminDTO) {
        Admin admin = new Admin();
        admin.setAdminName(adminDTO.getAdminName());
        admin.setAdminPassword(adminDTO.getAdminPassword());
        Admin admin1 = adminMapper.selectOne(admin);
        if(admin1 == null){
            return new Response(ResponseCodeEnum.INVALID_MD5);
        }
        return Response.success(admin1);
    }




}
