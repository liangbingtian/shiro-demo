package com.ruyuan.service.impl;

import com.ruyuan.repository.bean.UserRoles;
import com.ruyuan.repository.bean.UserRolesExample;
import com.ruyuan.repository.mapper.UserRolesMapper;
import com.ruyuan.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xx
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRolesMapper mapper;

    @Override
    public List<String> queryRolesByUserName(String userName) {
        UserRolesExample example = new UserRolesExample();
        example.createCriteria().andUsernameEqualTo(userName);
        List<UserRoles> userRoles = mapper.selectByExample(example);
        return userRoles.stream().map(UserRoles::getRoleName).collect(Collectors.toList());
    }
}
