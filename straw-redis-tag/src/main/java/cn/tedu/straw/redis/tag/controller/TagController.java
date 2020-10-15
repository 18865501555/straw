package cn.tedu.straw.redis.tag.controller;

import cn.tedu.straw.commons.vo.R;
import cn.tedu.straw.redis.tag.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/tags")
public class TagController {
    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("")
    public R<List<Object>> getTags() {
        // 向Redis中存入的数据的Key，也是取出数据的Key
        String key = "tags";
        // 从Redis中读取标签列表数据
        List<Object> list = redisUtils.getList(key);
        // 响应结果
        return R.ok(list);
    }
}
