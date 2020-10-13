package cn.tedu.straw.api.mapper;

import cn.tedu.straw.commons.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tedu.cn
 * @since 2020-10-10
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户名查询用户详情
     * @param username  用户名
     * @return  匹配的用户详情,如果没有匹配的数据,则返回null
     */
    User findByUsername(String username);
}
