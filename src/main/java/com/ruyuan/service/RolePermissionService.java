package com.ruyuan.service;

import java.util.List;

/**
 * @author xx
 */
public interface RolePermissionService {

    List<String> queryPermissionsByRole(String role);

}
