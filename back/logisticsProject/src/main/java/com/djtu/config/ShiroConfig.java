package com.djtu.config;

import com.djtu.shiro.filter.JwtFilter;
import com.djtu.shiro.matchers.MyHashedCredentialsMatcher;
import com.djtu.shiro.realms.CustomerRealm;
import com.djtu.shiro.session.ShiroSession;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Autowired
    private CustomerRealm realm;
    @Autowired
    private MyHashedCredentialsMatcher matcher;

    //1.创建 ShiroFilter 负责拦截所有请求
    //将 name 设为与 web.xml 中 filter-name 一致, 让过滤器自动找到这个 bean
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager, JwtFilter jwtFilter) {

        //创建过滤工厂实例, 设置默认安全管理器
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        Map<String, Filter> filterMap = new HashMap<>();
        //添加过滤器
        shiroFilterFactoryBean.setFilters(filterMap);
        filterMap.put("jwt", jwtFilter);

        Map<String, String> map = new HashMap<>();
/*
        //配置系统的受限资源
        map.put("/test2.do", "authc");
        //配置系统的公共资源
        map.put("/test1.do", "anon");
*/

        //让所有的请求进 jwt 拦截器
        map.put("/**", "jwt");

        //添加过滤map
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //设置被拦截时跳转的地址
        //shiroFilterFactoryBean.setLoginUrl("/login.do");
        return shiroFilterFactoryBean;
    }

    //2.创建安全管理器
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(SessionManager sessionManager) {
        //设置密码验证器
        //1. 设置密码验证器加密方式
        matcher.setHashAlgorithmName("md5");
        //2. 设置散列次数
        matcher.setHashIterations(1024);
        //3. 为自定义的 Realm 设置 CredentialsMatcher
        realm.setCredentialsMatcher(matcher);
        //4. 开启EH缓存
        realm.setCacheManager(new EhCacheManager());
        realm.setCachingEnabled(true);
        realm.setAuthenticationCachingEnabled(true);
        realm.setAuthenticationCacheName("authenticationCache");
        realm.setAuthorizationCachingEnabled(true);
        realm.setAuthorizationCacheName("authorizationCache");

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    //3.创建自定义 Realm
    @Bean
    public Realm getRealm() {
        return new CustomerRealm();
    }

    @Bean
    public JwtFilter getJwtFilter() {
        return new JwtFilter();
    }

    // 开启 @RequiresRoles、@RequiresPermissions 注解代理
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator app = new DefaultAdvisorAutoProxyCreator();
        app.setProxyTargetClass(true);
        return app;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    // 必须使用session管理器，才能够解决前后端分离shiro的subject未认证的问题
    @Bean
    public SessionManager sessionManager(){
        //将我们继承后重写的shiro session 注册
        ShiroSession shiroSession = new ShiroSession();
        //如果后续考虑多tomcat部署应用，可以使用shiro-redis开源插件来做session 的控制，或者nginx 的负载均衡
        shiroSession.setSessionDAO(new EnterpriseCacheSessionDAO());
        return shiroSession;
    }

}
