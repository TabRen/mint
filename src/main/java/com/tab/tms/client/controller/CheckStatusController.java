package com.tab.tms.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务状态检查控制器
 *
 * Created by tab on 5/1/17.
 */
@RestController
public class CheckStatusController {
    @RequestMapping(value = "/checkStatus", method = RequestMethod.GET)
    private String checkStatus() {
        return "Mint is running";
    }
}
