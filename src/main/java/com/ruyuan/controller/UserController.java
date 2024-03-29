package com.ruyuan.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liangbingtian
 * @date 2024/02/20 下午6:27
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/index")
    @RequiresRoles("admin")
    public String index() {
        return "user";
    }


    @RequestMapping(value = "/del")
    @RequiresPermissions("user:del")
    public String del() {
        return "del";
    }


}
