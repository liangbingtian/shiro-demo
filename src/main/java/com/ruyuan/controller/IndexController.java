package com.ruyuan.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liangbingtian
 * @date 2024/02/20 下午5:56
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/doLogin")
    public String doLogin(String username, String password) {
        final UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            SecurityUtils.getSubject().login(token);
        }catch (Exception e) {
            e.printStackTrace();
            return "login";
        }
        return "manage";
    }

    @RequestMapping(value = "/doLogout")
    public String doLogout() {
        SecurityUtils.getSubject().logout();
        return "login";
    }

    @RequestMapping(value = "/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }
}
