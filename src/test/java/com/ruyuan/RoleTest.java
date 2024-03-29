package com.ruyuan;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm.SaltStyle;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.realm.text.PropertiesRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Assert;
import org.junit.Test;

/**
 * @author liangbingtian
 * @date 2024/02/20 上午11:45
 */
public class RoleTest {


    @Test
    public void permission() {
        final SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
        simpleAccountRealm.addAccount("xx", "123456", "admin");
        final DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        final UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("xx", "123456");
        final Subject subject = SecurityUtils.getSubject();
        subject.login(usernamePasswordToken);
        Assert.isTrue(subject.isAuthenticated());
        Assert.isTrue(subject.hasRole("admin"));
        subject.logout();
        Assert.isTrue(!subject.isAuthenticated());
    }

    @Test
    public void permission1() {
        final IniRealm iniRealm = new IniRealm("classpath:user.ini");
        final DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        final UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("xx", "123456");
        final Subject subject = SecurityUtils.getSubject();
        subject.login(usernamePasswordToken);
        Assert.isTrue(subject.isAuthenticated());
        Assert.isTrue(subject.hasRole("admin"));
        Assert.isTrue(subject.isPermitted("user:add"));
        Assert.isTrue(!subject.isPermitted("user:query"));
        subject.logout();
        Assert.isTrue(!subject.isAuthenticated());
    }

    @Test
    public void permission2(){
        final PropertiesRealm propertiesRealm = new PropertiesRealm();
        propertiesRealm.onInit();
        final DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(propertiesRealm);
        SecurityUtils.setSecurityManager(securityManager);
        final UsernamePasswordToken token = new UsernamePasswordToken("xx", "123456");
        final Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        Assert.isTrue(subject.isAuthenticated());
        Assert.isTrue(subject.hasRole("admin"));
        Assert.isTrue(subject.isPermitted("user:add"));
        Assert.isTrue(subject.isPermitted("user:query"));
        subject.logout();
        Assert.isTrue(!subject.isAuthenticated());
    }

    @Test
    public void permission3(){
        final DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/algorithm");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("liangbing39024&");
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        final JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setPermissionsLookupEnabled(true);
        jdbcRealm.setDataSource(druidDataSource);
        jdbcRealm.setSaltStyle(SaltStyle.EXTERNAL);

        final HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(102);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        jdbcRealm.setCredentialsMatcher(hashedCredentialsMatcher);

        final DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(jdbcRealm);
        SecurityUtils.setSecurityManager(securityManager);
        final UsernamePasswordToken token = new UsernamePasswordToken("xx", "123456");
        final Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        Assert.isTrue(subject.isAuthenticated());
        Assert.isTrue(subject.hasRole("admin"));
        Assert.isTrue(subject.isPermitted("user:add"));
        Assert.isTrue(!subject.isPermitted("user:query"));
        subject.logout();
        Assert.isTrue(!subject.isAuthenticated());
    }

}
