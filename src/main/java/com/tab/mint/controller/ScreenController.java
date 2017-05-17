package com.tab.mint.controller;

import com.tab.mint.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping(value = "/screen",
        method = RequestMethod.GET)
    private String screen() {
        return "This is screen";
    }

    @RequestMapping(value = "/getAllPlaybackStatus",
        method = RequestMethod.GET)
    private String getAllPlaybackStatus() {
        ScreenService screenService = new ScreenService();
        return null;
    }

}
