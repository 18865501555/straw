package cn.tedu.straw.redis.tag.controller;

import cn.tedu.straw.commons.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class TagController {
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/a")
    public R<List<Object>> a() {
        // 向Redis中存入的数据的Key，也是取出数据的Key
        String key = "tags";
        // 从Redis中读取标签列表数据
        ListOperations<String, Object> ops = redisTemplate.opsForList();
        List<Object> list = ops.range(key, 0, ops.size(key));
        // 响应结果
        R<List<Object>> r = new R<>();
        r.setState(R.State.SUCCESS);
        r.setData(list);
        return r;
    }
}
