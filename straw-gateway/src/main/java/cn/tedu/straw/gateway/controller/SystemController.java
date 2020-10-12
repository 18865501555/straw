package cn.tedu.straw.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemController {

    @GetMapping("/index.html")
    public String index() {
        /**
         * 在整合了Thymeleaf的SpringBoot项目中
         * 当转发到视图时，默认情况下
         * 前缀是 >>> /templates/
         * 后缀是 >>> .html
         * 当返回index时，会根据"前缀+返回值+后嘴"组合出 >>> /templates/index.html
         */
        return "index";
    }

    @GetMapping("/register.html")
    public String register() {
        return "register";
    }
}
