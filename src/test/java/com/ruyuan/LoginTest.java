package com.ruyuan;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.realm.text.PropertiesRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author liangbingtian
 * @date 2024/02/07 上午11:39
 */
public class LoginTest {

    @Test
    public void login(){
        final HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        SimpleAccountRealm realm = new SimpleAccountRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher);
        realm.addAccount("xx", "fc1709d0a95a6be30bc5926fdb7f22f4");
        final DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(realm);
        SecurityUtils.setSecurityManager(securityManager);
        final UsernamePasswordToken token = new UsernamePasswordToken("xx", "123456");
        final Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated());
        subject.logout();
        Assert.assertFalse(subject.isAuthenticated());
    }

    @Test
    public void login2(){
        final IniRealm iniRealm = new IniRealm("classpath:user.ini");
        iniRealm.addAccount("xx", "123456");
        final DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(iniRealm);
        SecurityUtils.setSecurityManager(securityManager);
        final UsernamePasswordToken token = new UsernamePasswordToken("xx", "123456");
        final Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated());
        subject.logout();
        Assert.assertFalse(subject.isAuthenticated());
    }

    @Test
    public void login3(){
        final PropertiesRealm propertiesRealm = new PropertiesRealm();
        propertiesRealm.onInit();
        final DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(propertiesRealm);
        SecurityUtils.setSecurityManager(securityManager);
        final UsernamePasswordToken token = new UsernamePasswordToken("xx", "123456");
        final Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated());
        subject.logout();
        Assert.assertFalse(subject.isAuthenticated());
    }

    @Test
    public void login4(){
        final DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/algorithm");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("liangbing39024&");
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        final JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(druidDataSource);
        final DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(jdbcRealm);
        SecurityUtils.setSecurityManager(securityManager);
        final UsernamePasswordToken token = new UsernamePasswordToken("xx", "123456");
        final Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated());
        subject.logout();
        Assert.assertFalse(subject.isAuthenticated());
    }

}
