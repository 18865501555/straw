package cn.tedu.straw.api.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 当前方法的主要作用是:登陆验证授权
        auth.userDetailsService(userDetailsService);
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("{bcrypt}$2a$10$hMbxlC8y.EEYCOWPBYjSa.uYGDiC.tncZf2DP39HqVj2BquAOSmxK")
//                .authorities("admin_list", "admin_delete", "user")
//                .and()
//                .withUser("user")
//                .password("{bcrypt}$2a$10$hMbxlC8y.EEYCOWPBYjSa.uYGDiC.tncZf2DP39HqVj2BquAOSmxK")
//                .authorities("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 当前方法的主要作用是:访问控制
        http.authorizeRequests()
                /**
                 * 使用antMatchers()配置需要管理权限的URL，可以使用通配符
                 *  ? >>> 任何1个字符
                 *  * >>> 任何1层路径资源
                 *  ** >>> 任何层次的资源
                 *
                 * 例如:
                 * - 配置为/user/*
                 * - - 可以匹配/user/delete、/user/list
                 * - - 不可以匹配/user/2020/list
                 * 例如:
                 * - 配置为/user/**
                 * - - 可以匹配/user/delete、/user/list、/user/2020/list、/user/2020/08/list
                 *
                 * 紧随其后，使用hasAuthority()配置权限标识
                 */
                .antMatchers("/admin/list").hasAuthority("admin_list")
                .antMatchers("/admin/delete").hasAuthority("admin_delete")
                .antMatchers("/user/**").hasAuthority("user")
                // 对任何请求进行授权检查
                .anyRequest().authenticated();
        // 验证授权时，是使用登陆表单进行授权的
        http.formLogin();
    }
}
