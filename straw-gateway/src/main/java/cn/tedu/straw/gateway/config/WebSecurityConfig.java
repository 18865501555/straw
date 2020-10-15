package cn.tedu.straw.gateway.config;

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
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 登陆页面的URL
        String loginPageUrl = "/login.html";
        // 处理登陆的URL、也就是提交用户名、密码到哪个位置
        // 默认情况下Spring Security的/login路径
        // - 当请求方式是GET时，表示访问登陆页面
        // - 当请求方式是POST时，是处理登陆请求
        String loginProcessingUrl = "/login";
        // 登陆失败后重定向到的URL
        String loginFailureUrl = "/login.html?error";
        // 登陆成功后重定向到的URL
        String loginSuccessUrl = "/index.html";
        // 退出登陆的URL
        String logoutUrl = "/logout";
        // 退出登陆成功后重定向到的URL
        String logoutSuccessUrl = "/login.html?logout";


        // -------------------------------------------------
        // 对所有请求进行授权
        http.authorizeRequests().anyRequest().permitAll();
        // 关闭跨域攻击
        http.csrf().disable();
        // 使用表单验证登陆
        http.formLogin()
                .loginPage(loginPageUrl)
                .loginProcessingUrl(loginProcessingUrl)
                .failureUrl(loginFailureUrl)
                .defaultSuccessUrl(loginSuccessUrl);
        // 配置退出登陆
        http.logout()
                .logoutUrl(logoutUrl)
                .logoutSuccessUrl(logoutSuccessUrl);
    }
}
