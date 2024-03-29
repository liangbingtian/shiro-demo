package com.ruyuan.service;

import java.util.List;

/**
 * @author xx
 */
public interface UserRoleService {

    List<String> queryRolesByUserName(String userName);

}
