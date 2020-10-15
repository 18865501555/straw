package cn.tedu.straw.redis.tag.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Long rightPushListItem(String key, Object listItem) {
        ListOperations<String, Object> ops = redisTemplate.opsForList();
        return ops.rightPush(key, listItem);
    }

    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    public Long size(String key) {
        ListOperations<String, Object> ops = redisTemplate.opsForList();
        return ops.size(key);
    }

    public List<Object> getList(String key) {
        return getList(key, 0, size(key));
    }

    public List<Object> getList(String key, long start, long end) {
        ListOperations<String, Object> ops = redisTemplate.opsForList();
        return ops.range(key, start, end);
    }
}
