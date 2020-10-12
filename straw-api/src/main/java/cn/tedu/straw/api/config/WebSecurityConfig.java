package cn.tedu.straw.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 当前类仅用于演示(案例1)，已作废
 */
@Deprecated
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 在内存中授权
        auth.inMemoryAuthentication()
                // withUser >>> 指定用户名
                .withUser("root")
                // password >>> 指定密码
                .password("{noop}1234")
                // authorities >>> 指定授权 >> 方法参数值是权限标识字符串
                .authorities("/admin/list","/admin/delete");
    }
}
