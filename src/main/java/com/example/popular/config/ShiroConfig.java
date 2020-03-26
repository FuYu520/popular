package com.example.popular.config;

import com.example.popular.shiro.relam.UserRealm;
import com.google.common.collect.Maps;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;


/**
 * shiro 配置
 *
 * @author FuYu
 * @date 2020/03/19
 */
@Configuration
public class ShiroConfig {

    // 登录地址
    @Value("${shiro.user.loginUrl}")
    private String loginUrl;

    //缓存管理器
    @Bean
    public EhCacheManager getEhCacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache/ehcache-shiro.xml");
        return ehCacheManager;
    }
    //凭证管理器

    /**
     * 自定义realm实现
     * @return
     */
    @Bean
    public UserRealm userRealm(){
        UserRealm userRealm = new UserRealm();
        userRealm.setCachingEnabled(true);
        userRealm.setAuthenticationCachingEnabled(true);
        userRealm.setAuthenticationCacheName("authenticationCache");

        userRealm.setAuthorizationCachingEnabled(true);
        userRealm.setAuthorizationCacheName("authorizationCache");
        return userRealm;
    }

    /**
     * 会话ID生成器
     * @return
     */
    @Bean
    public JavaUuidSessionIdGenerator generator(){
        return new JavaUuidSessionIdGenerator();
    }

    /**
     * 会话DAO
     * @return
     */
    @Bean
    public EnterpriseCacheSessionDAO sessionDAO(){
        EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
        enterpriseCacheSessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        enterpriseCacheSessionDAO.setSessionIdGenerator(generator());
        return enterpriseCacheSessionDAO;
    }

    /**
     * 会话验证调度器
     * @return
     */
    @Bean
    public ExecutorServiceSessionValidationScheduler sessionValidationScheduler(){
        ExecutorServiceSessionValidationScheduler sessionValidationScheduler = new ExecutorServiceSessionValidationScheduler();
        sessionValidationScheduler.setInterval(1800000L);
        //sessionValidationScheduler.setSessionManager(sessionManager());
        return sessionValidationScheduler;
    }

    /**
     * 会话管理器
     * @return
     */
    @Bean
    public DefaultWebSessionManager sessionManager(){
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setCacheManager(getEhCacheManager());
        defaultWebSessionManager.setGlobalSessionTimeout(1800000L);
        defaultWebSessionManager.isDeleteInvalidSessions();
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
        //todo 自定义任务调度
        defaultWebSessionManager.setSessionValidationScheduler(sessionValidationScheduler());
        defaultWebSessionManager.setSessionDAO(sessionDAO());
        return defaultWebSessionManager;
    }

    /**
     * 安全管理器
     * @param userRealm
     * @return
     */
    @Bean
    public SecurityManager securityManager(UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm
        securityManager.setRealm(userRealm);
        //记住我
        //securityManager.setRememberMeManager();
        //注入缓存管理器
        securityManager.setCacheManager(getEhCacheManager());
        //session管理器
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * Shiro过滤器配置
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // Shiro的核心安全接口,这个属性是必须的
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //身份认证失败，则跳转到登录页面的配置
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        //所有请求需要认证
        Map<String, String> filterChainDefinitionMap = Maps.newHashMap();
        //静态资源设置匿名访问
        filterChainDefinitionMap.put("/custom/**", "anon");
        filterChainDefinitionMap.put("images/**", "anon");
        filterChainDefinitionMap.put("/layui/**", "anon");
        filterChainDefinitionMap.put("/vendors/**", "anon");

        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/user", "user");

        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * shiro生命周期
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * aop 支持 注解需要
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
    /**
     * 注解支持
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager)
    {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
