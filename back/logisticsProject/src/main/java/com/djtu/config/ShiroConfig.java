package com.djtu.config;

import com.djtu.shiro.filter.JwtFilter;
import com.djtu.shiro.matchers.MyHashedCredentialsMatcher;
import com.djtu.shiro.realms.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //1.创建 ShiroFilter 负责拦截所有请求
        //将 name 设为与 web.xml 中 filter-name 一致, 让过滤器自动找到这个 bean
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager, JwtFilter jwtFilter){

        //创建过滤工厂实例, 设置默认安全管理器
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", jwtFilter);

        Map<String, String> map = new HashMap<>();
        /*
        //配置系统的受限资源
        map.put("/test2.do", "authc");
        //配置系统的公共资源
        map.put("/test1.do", "anon");
         */

        //让所有的请求进 jwt 拦截器
        map.put("/", "jwt");

        //添加过滤器
        shiroFilterFactoryBean.setFilters(filterMap);
        //添加过滤map
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
            //设置被拦截时跳转的地址
        shiroFilterFactoryBean.setLoginUrl("/login.do");

        return shiroFilterFactoryBean;
    }

    //2.创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm){
        //向下转型 realm 这样才可以设置密码验证器
        CustomerRealm customerRealm = (CustomerRealm) realm;
        //设置密码验证器
        //1. 创建 Shiro 提供的 CredentialsMatcher 密码验证器
        MyHashedCredentialsMatcher matcher = new MyHashedCredentialsMatcher();
        //2. 设置其加密方式
        matcher.setHashAlgorithmName("md5");
        //3. 设置散列次数
        matcher.setHashIterations(1024);
        //4. 为自定义的 Realm 设置 CredentialsMatcher
        customerRealm.setCredentialsMatcher(matcher);

        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(customerRealm);
        return defaultWebSecurityManager;
    }

    //3.创建自定义 Realm
    @Bean
    public Realm getRealm(){
        return new CustomerRealm();
    }

    @Bean
    public JwtFilter getJwtFilter(){
        return new JwtFilter();
    }

}
