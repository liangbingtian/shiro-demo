package com.ruyuan.realm;

import com.ruyuan.repository.bean.User;
import com.ruyuan.service.RolePermissionService;
import com.ruyuan.service.UserRoleService;
import com.ruyuan.service.UserService;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.ByteSource.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author liangbingtian
 * @date 2024/02/20 下午6:09
 */
@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        final String username = (String) principalCollection.getPrimaryPrincipal();
        final SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        final List<String> roles = userRoleService.queryRolesByUserName(username);
        info.setRoles(new HashSet<>(roles));
        Set<String> permissions = new HashSet<>();
        for (String role : info.getRoles()) {
            final List<String> permissionList = rolePermissionService.queryPermissionsByRole(role);
            if (!CollectionUtils.isNotEmpty(permissionList)) {
                permissions.addAll(permissionList);
            }
        }
        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        final String username = (String) authenticationToken.getPrincipal();
        final User user = userService.queryByUserName(username);
        if (user==null){
            throw new UnknownAccountException("user not exists");
        }
        final ByteSource bytes = Util.bytes(username);
        return new SimpleAuthenticationInfo(username, user.getPassword(), bytes, getName());
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        final HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(102);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        super.setCredentialsMatcher(hashedCredentialsMatcher);
    }
}
