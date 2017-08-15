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
  // 登录页面
  @RequestMapping(value = "/login")
  private String login() {
    return "login";
  }

  // 登入后跳转
  @RequestMapping(value = "/")
  private String loginPass() {
    return "screen";
  }

}
