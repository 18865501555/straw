package cn.tedu.straw.api.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PreAuthorize("hasAuthority('/admin/list')")
    @GetMapping("/admin/list")
    public String adminList(){
        return "admin list";
    }

    @PreAuthorize("hasAuthority('/admin/delete')")
    @GetMapping("/admin/delete")
    public String adminDelete(){
        return "admin delete";
    }
}
