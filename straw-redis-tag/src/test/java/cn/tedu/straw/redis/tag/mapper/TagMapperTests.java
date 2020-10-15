package cn.tedu.straw.redis.tag.mapper;

import cn.tedu.straw.commons.vo.TagVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class TagMapperTests {
    @Autowired
    TagMapper tagMapper;

    @Test
    void findAll() {
        List<TagVO> tagVOS = tagMapper.findAll();
        log.debug("tag list size >>> {}", tagVOS.size());
        for (TagVO tagVO : tagVOS) {
            log.debug(">>> {}", tagVO);
        }
    }
}
