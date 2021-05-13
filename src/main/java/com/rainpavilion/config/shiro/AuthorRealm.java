package com.rainpavilion.config.shiro;

import com.rainpavilion.entity.shiro.Admin;
import com.rainpavilion.mapper.AdminMapper;
import com.rainpavilion.service.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Set;

public class AuthorRealm extends AuthorizingRealm {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private ShiroService shiroService;

    //获取授权信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1.获取用户名  主身份
        String username = principals.getPrimaryPrincipal().toString();

        //2.根据用户名查询授权信息
        Set<String> roles = shiroService.selectRoleNameByUsername(username);
        Set<String> permissions = shiroService.selectPermissionByUsername(username);

        //3.封装info返回
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(permissions);

        return info;
    }


    //doGetAuthenticationInfo 获取认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        Admin adminDTO = new Admin();
        adminDTO.setAdminPhone(username);
        Admin admin = adminMapper.selectOne(adminDTO);
        //判断信息是否为空
        if(admin != null){

            //把user对象放在session里
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("user",admin);

            //封装info返回
            //处理盐值
            ByteSource bytes = ByteSource.Util.bytes(admin.getAdminSalt());
            /**
             * ctrl+p 看形参
             * 参数1 身份信息  账号
             * 参数2 凭证信息  密码
             * 参数3 盐值
             * 参数4 当前Realm的名字
             */
            return new SimpleAuthenticationInfo(admin.getAdminPhone(),admin.getAdminPasswordCipher(),bytes,this.getName());
        }
        return null;
    }

}



