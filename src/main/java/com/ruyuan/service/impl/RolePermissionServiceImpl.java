package com.ruyuan.service.impl;

import com.ruyuan.repository.bean.RolesPermissions;
import com.ruyuan.repository.bean.RolesPermissionsExample;
import com.ruyuan.repository.mapper.RolesPermissionsMapper;
import com.ruyuan.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xx
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolesPermissionsMapper mapper;

    @Override
    public List<String> queryPermissionsByRole(String role) {
        RolesPermissionsExample example = new RolesPermissionsExample();
        example.createCriteria().andRoleNameEqualTo(role);
        List<RolesPermissions> permissions = mapper.selectByExample(example);
        return permissions.stream().map(RolesPermissions::getPermission).collect(Collectors.toList());
    }
}
