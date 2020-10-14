package cn.tedu.straw.api.security;

import cn.tedu.straw.api.service.IUserService;
import cn.tedu.straw.api.vo.UserLoginVO;
import cn.tedu.straw.commons.security.LoginUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserService iUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 调用业务层获取用户名匹配的用户信息
        UserLoginVO userLoginVO = iUserService.getUserLoginDetails(username);
        // 从查询得到的结果中取出用户的权限信息，并封装为以下构造方法所需要的参数类型
        String[] authorityArray = new String[userLoginVO.getPermissions().size()];
        for (int i = 0; i < authorityArray.length; i++) {
            authorityArray[i] = userLoginVO.getPermissions().get(i).getName();
        }
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(authorityArray);
        // 将获取到的用户信息封装为UserDetails的类型，也就是LoginUserInfo类型的对象
        LoginUserInfo loginUserInfo = new LoginUserInfo(
                userLoginVO.getUsername(),
                userLoginVO.getPassword(),
                userLoginVO.getEnabled() == 1,
                true,
                true,
                userLoginVO.getLocked() == 0,
                authorities
        );
        loginUserInfo.setId(userLoginVO.getId())
                .setNickname(userLoginVO.getNickname())
                .setPhone(userLoginVO.getPhone())
                .setType(userLoginVO.getType());
        // 返回
        return loginUserInfo;
    }

}
