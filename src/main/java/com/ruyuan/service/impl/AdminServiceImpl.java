package com.ruyuan.service.impl;

import com.ruyuan.repository.bean.Admin;
import com.ruyuan.repository.bean.AdminExample;
import com.ruyuan.repository.bean.User;
import com.ruyuan.repository.bean.UserExample;
import com.ruyuan.repository.mapper.AdminMapper;
import com.ruyuan.repository.mapper.UserMapper;
import com.ruyuan.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author xx
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin queryByAdminName(String adminName){
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andAdminNameEqualTo(adminName);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (!CollectionUtils.isEmpty(admins)) {
            return admins.get(0);
        }
        return null;
    }
}
