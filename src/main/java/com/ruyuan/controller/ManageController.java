package com.ruyuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liangbingtian
 * @date 2024/02/20 下午6:27
 */
@Controller
@RequestMapping("/manage")
public class ManageController {

    @RequestMapping(value = "/index")
    public String index() {
        return "manage";
    }


}
