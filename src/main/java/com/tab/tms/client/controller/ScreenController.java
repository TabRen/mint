package com.tab.tms.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 屏幕控制器
 * <p>
 * Created by tab on 5/6/17.
 */
@RestController
public class ScreenController {
    @RequestMapping(value = "/screen", method = RequestMethod.GET)
    private String screen() {
        return "This is screen";
    }

}
