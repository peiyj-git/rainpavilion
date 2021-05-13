package com.rainpavilion.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //需告知shiro过滤规则（拦截规则）
    //工厂设计模式
    //shiro会通过过滤器工厂自动创建过滤器对象(强制登录)
    //这个类只有一个用处 配置过滤规则(拦截规则)
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        /**
         * 1.配置过滤规则
         *
         * anon 匿名可访问  不用认证就可以访问
         * authc 认证可访问 必须登录之后才能访问
         *
         * 支持通配符
         *
         * 注意：不能出现 /** 的配置 有 bug
         */
        Map map = new HashMap();
        //不能一下拦截全部页面 然后放行某几个 会出bug 这样写不合适
        //anon 不需要登录
        map.put("/login.jsp","anon");
        map.put("/login/*","anon");
        map.put("/admin/*","anon");
        map.put("/log/*","anon");

        //authc 认证可访问 会被拦截
        map.put("/main.jsp", "authc");
        map.put("/guru/*", "authc");
        map.put("/menu/*", "authc");
        map.put("/jsp/*", "authc");

        factoryBean.setFilterChainDefinitionMap(map);

        //强制登录跳转页面
        factoryBean.setLoginUrl("/login.jsp");

//        需要一个安全管理器
        factoryBean.setSecurityManager(securityManager);

        return factoryBean;

    }

    /**
     * 创建安全管理器
     */
    @Bean
    public DefaultWebSecurityManager securityManager(AuthorRealm authorRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        /**
         * 需要自定义的Realm
         */
        securityManager.setRealm(authorRealm);
        return securityManager;
    }

    /**
     * 授权的Realm
     */
    @Bean
    public AuthorRealm authorRealm(HashedCredentialsMatcher hashedCredentialsMatcher){
        AuthorRealm authorRealm = new AuthorRealm();
        authorRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return authorRealm;
    }


    /**
     * 密码校验规则HashedCredentialsMatcher
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式为MD5 SHA
        credentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数 散列次数   必须和注册时候散列次数保持一致
        credentialsMatcher.setHashIterations(1024);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }


    /**
     *  开启shiro aop注解支持
     *  使用代理方式;所以需要开启代码支持;否则@RequiresRoles等注解无法生效
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}



