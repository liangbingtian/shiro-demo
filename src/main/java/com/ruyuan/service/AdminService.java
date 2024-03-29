package com.ruyuan.service;

import com.ruyuan.repository.bean.Admin;
import com.ruyuan.repository.bean.User;

/**
 * @author xx
 */
public interface AdminService {

    Admin queryByAdminName(String adminName);

}
