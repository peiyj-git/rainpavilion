package com.rainpavilion;

import com.rainpavilion.entity.dto.AdminDTO;
import com.rainpavilion.entity.shiro.Admin;
import com.rainpavilion.entity.vo.Response;
import com.rainpavilion.service.AdminService;
import com.rainpavilion.service.MenuService;
import com.rainpavilion.service.ShiroService;
import com.rainpavilion.service.impl.ShiroServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test1_shiroTest {
    @Autowired
    private ShiroService shiroService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private MenuService menuService;




    private String username = "zhangsan";
    private String password = "123456";


    //测试 -> 从配置文件读取用户信息,进行判断登录
    @Test
    public void test1(){
//        1 读取配置文件数据给Subject
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
//        SecurityManager 安全管理器
        SecurityManager securityManager = factory.getInstance();
//        给工具类设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);
//        2 获取Subject主题 (Subject是通过工具类get出来的 不是new出来的 保证了单例)
        Subject subject = SecurityUtils.getSubject();

//        3 将用户输入的信息 用户名密码 封装到Subject
//        调用Subject的Login方法
//        token 令牌: 封装身份信息
//        行业统一: token就是封装身份信息
//        将用户输入的账号密码 封装到token中
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

//        没有异常就是登录成功
//        有任何异常都表示 账号或密码不对
//        不能把详细信息暴露给客户,不安全
        try{
            subject.login(token);
            System.err.println("认证成功");
        }catch (Exception e){
            System.err.println("账号或密码不正确");
        }


    }


    //测试sql语句是否正常
    @Test
    public void test2(){
        //注册
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setAdminName("测试2");
        adminDTO.setAdminPhone("1234567");
        adminDTO.setAdminPassword("123456");
//        int i = adminService.addAdmin(adminDTO);
//        System.out.println(i);


        Set<String> strings =
                shiroService.selectPermissionByUsername("15238032");
        strings.forEach(System.out::println);

        Set<String> strings1 =
                shiroService.selectPermissionByUsername("15238032");
        strings1.forEach(System.err::println);

    }


    //权限sql测试
    @Test
    public void test3(){
        Set<String> strings = shiroService.selectPermissionByUsername("1523803");
        System.out.println(strings);

    }



}
