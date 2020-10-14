package cn.tedu.straw.api.mapper;

import cn.tedu.straw.commons.model.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserMapperTests {
    @Autowired
    UserMapper userMapper;

    @Test
    void insert() {
        User user = new User()
                .setUsername("gogo")
                .setPassword("1234");
        int rows = userMapper.insert(user);
        log.debug("rows >>> {}", rows);
    }

    @Test
    void deleteById() {
        int rows = userMapper.deleteById(23);
        log.debug("rows >>> {}", rows);
    }

    @Test
    void updateById() {
        User user = new User()
                .setId(24)
                .setPassword("6666");
        int rows = userMapper.updateById(user);
        log.debug("rows >>> {}", rows);
    }

    @Test
    void selectById() {
        int id = 5;
        User user = userMapper.selectById(id);
        log.debug("user >>> {}", user);
    }

    @Test
    void selectByUsername() {
        // 根据用户名查询数据，指定用户名的值
        String username = "root";
        // 创建查询时的条件对象，相当配置SQL语句中的WHERE子句
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // eq方法用于表示username=?，表示"等于"的判断条件
        queryWrapper.eq("username", username);
        // 执行查询
        User user = userMapper.selectOne(queryWrapper);
        // 测试输出
        log.debug("query result >>> {}", user);
    }
}
