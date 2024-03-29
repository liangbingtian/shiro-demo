package com.ruyuan.service;

import com.ruyuan.repository.bean.User;

/**
 * @author xx
 */
public interface UserService {

    User queryByUserName(String userName);

}
