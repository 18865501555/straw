package cn.tedu.straw.gateway.mapper;

import cn.tedu.straw.commons.model.Permission;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class PermissionMapperTests {
    @Autowired
    PermissionMapper permissionMapper;
    @Test
    void findByUsername(){
        String username = "13800138014";
        List<Permission> permissions = permissionMapper.findByUsername(username);
        log.debug("permission list size >>> {}", permissions.size());
        for (Permission permission : permissions) {
            log.debug(">>> {}",permission);
        }
    }
}
