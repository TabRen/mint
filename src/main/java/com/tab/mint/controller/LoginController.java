package com.tab.mint.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录控制器
 * <p>
 * Created by tab on 5/18/17.
 */
@Controller
public class LoginController {
    @RequestMapping(value = "/login")
    private String login() {
        return "login";
    }

}
