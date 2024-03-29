package com.ruyuan.config;

import com.ruyuan.realm.MyRealm;
import java.util.Properties;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * @author liangbingtian
 * @date 2024/02/20 下午6:20
 */
@Configuration
public class MyShiroConfig {

    @Bean
    public SessionsSecurityManager securityManager(@Autowired MyRealm myRealm) {
        final DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm);
        return defaultWebSecurityManager;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        final DefaultShiroFilterChainDefinition defaultShiroFilterChainDefinition = new DefaultShiroFilterChainDefinition();
        defaultShiroFilterChainDefinition.addPathDefinition("/manage/**", "authc");
        return defaultShiroFilterChainDefinition;
    }

    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        final SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        final Properties properties = new Properties();
        properties.setProperty("org.apache.shiro.authz.AuthorizationException", "/unauthorized");
        resolver.setExceptionMappings(properties);
        return resolver;
    }
}
