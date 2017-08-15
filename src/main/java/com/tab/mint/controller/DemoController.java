package com.tab.mint.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DemoController {
  @RequestMapping(value = "/demo")
  private String demo(Model model) {
    String dateString;
    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-SS");
    dateString = dateFormat.format(date);
    model.addAttribute("name", "world");
    model.addAttribute("name2", "tab");
    model.addAttribute("currentDate", dateString);
    return "demo";
  }
}
