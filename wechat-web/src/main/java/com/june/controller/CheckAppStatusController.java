package com.june.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckAppStatusController {

    @RequestMapping("/checkappstatus.html")
    public String checkappstatus() {
        return "ok";
    }
}
