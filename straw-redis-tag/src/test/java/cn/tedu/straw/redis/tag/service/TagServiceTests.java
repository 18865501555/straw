package cn.tedu.straw.redis.tag.service;

import cn.tedu.straw.commons.vo.TagVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class TagServiceTests {
    @Autowired
    ITagService iTagService;

    @Test
    void getTagList() {
        List<TagVO> tagVOS = iTagService.getTagList();
        log.debug("tag list size >>> {}", tagVOS);
        for (TagVO tagVO : tagVOS) {
            log.debug(">>> {}", tagVO);
        }
    }
}
