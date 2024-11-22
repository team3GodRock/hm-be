package com.example.god.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class AdminHomeController {
    @RequestMapping("/admin")
    public String home(){
        log.info("home controller");
        return "home";
    }
}
