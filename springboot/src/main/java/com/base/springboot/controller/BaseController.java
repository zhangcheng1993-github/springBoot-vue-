package com.base.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {

    @RequestMapping("/error/{code}")
    public String errorController(@PathVariable("code") String code, Model model){
        String errorStr="";
        switch (code){
            case "404":
                errorStr="找不到页面!";
                break;
            case "405":
                errorStr="405错误!";
                break;
            case "500":
                errorStr="服务器错误!";
                break;
        }
        model.addAttribute("errorStr",errorStr);
        return "errorPage";
    }
}
