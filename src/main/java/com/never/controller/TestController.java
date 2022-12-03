package com.never.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/hello")
    public String index(){
        System.out.println("hille");
        return "redirect:/uploadVideo.html";
    }
}
