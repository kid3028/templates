package com.templates.shiro;

import com.templates.entity.Permission;
import com.templates.service.PermissionService;
import com.templates.service.UserService;
import com.templates.service.impl.PermissionServiceImpl;
import com.templates.service.impl.UserSerrviceImpl;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class ShiroConfig {


    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题
     * 注意：单独一个ShiroFilterFactoryBean配置将会报错，因为在初始化ShiroFilterFactoryBean的时候需要注入SecurityManager
     * FilterChain定义说明，1、一个Url可以配置多个Filter，使用逗号分隔。2、当多个过滤器时，全部验证通过，才算通过。3、部分过滤器可以指定参数，如perms，roles
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, PermissionService permissionService){
        System.out.println("================>ShiroConfig.shiroFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //必须设置Srcuritymanager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 如果不设置默认会自动寻找web工程下根目录下的login页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功之后要跳转的页面
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        // 拦截链  必须是LinkedHashMap保证有序
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        /**
         * 配置不会拦截的链接 顺序判断   Url权限采取第一次匹配优先的方式，即从头开始使用第一个匹配的Url模式对应的拦截器链
         * 配置退出过滤器（logout），其中的具体实现退出代码shiro已经替我们实现
         * 采取从数据库动态获取权限的方式
         *
         * authc：所有的url都必须认证通过才可以访问 anon：所有的Url可以匿名访问
         */
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/loginUser", "anon");
        filterChainDefinitionMap.put("/register", "anon");
        filterChainDefinitionMap.put("/registerUser", "anon");
        filterChainDefinitionMap.put("/logout","anon");
        filterChainDefinitionMap.put("/index","authc");
        filterChainDefinitionMap.put("/code/**","anon");

        //数据动态获取权限
        List<Permission> permissions = permissionService.getAll();
        for(Permission permission : permissions) {
            filterChainDefinitionMap.put(permission.getUrl(), permission.getPerm());

        }
        filterChainDefinitionMap.put("/**", "authc");
        System.out.println(filterChainDefinitionMap);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager(myAuthRealm());
        return defaultWebSecurityManager;
    }

    @Bean
    public MyAuthRealm myAuthRealm() {
        MyAuthRealm authRealm = new MyAuthRealm();
        authRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return authRealm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(org.apache.shiro.mgt.SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    // 注意：service不可以使用@autowired注入，需要手动new，然后通过方法传参
    @Bean("permissionService")
    public PermissionService permissionService() {
        return new PermissionServiceImpl();
    }






}