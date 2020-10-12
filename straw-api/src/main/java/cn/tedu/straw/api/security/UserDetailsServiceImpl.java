package cn.tedu.straw.api.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 假设仅root是正确的用户名
        if ("root".equals(username)) {
            //用户名是root，则返回该用户的信息，后续Spring Security将根据返回的信息完成登陆验证及授权
            UserDetails userDetails = User.builder()
                    .username("root")
                    .password("{bcrypt}$2a$10$hMbxlC8y.EEYCOWPBYjSa.uYGDiC.tncZf2DP39HqVj2BquAOSmxK")
                    .authorities("user")
                    .build();
            return userDetails;
        }
        // 用户名不是root，则返回null，表示"无此用户"
        return null;
    }
}
