package com.djtu.config;

import com.djtu.shiro.realms.CustomerRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //1.创建 ShiroFilter 负责拦截所有请求
        //将 name 设为与 web.xml 中 filter-name 一致, 让过滤器自动找到这个 bean
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){

        //创建过滤工厂实例, 设置默认安全管理器
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        Map<String, String> map = new HashMap<>();
        //配置系统的受限资源: 所有请求
        map.put("/test2.do", "authc"); // authc 代表资源需要认证和授权
        //配置系统的公共资源
        map.put("/test1.do", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
            //设置被拦截时跳转的地址
        shiroFilterFactoryBean.setLoginUrl("/login.do");

        return shiroFilterFactoryBean;
    }

    //2.创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    //3.创建自定义 Realm
    @Bean
    public Realm getRealm(){
        return new CustomerRealm();
    }

}
