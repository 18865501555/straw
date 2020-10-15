package cn.tedu.straw.redis.tag.schedule;

import cn.tedu.straw.commons.vo.TagVO;
import cn.tedu.straw.redis.tag.service.ITagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class TagSchedule {
    @Autowired
    private ITagService iTagService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 只要添加了@Scheduled注解，就是计划任务方法
    // 在@Scheduled注解中的fixedRate表示执行频率，以毫秒为单位
    // 关于计划任务的方法
    // - 应该使用public权限
    // - 应该使用void表示返回值类型
    // - 方法名称可以自定义
    // - 参数列表应该为空
    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void loadTags() {
        // 向Redis中存入的数据的Key
        String key = "tags";
        // 清除Redis服务器中名为tags的数据,避免反复向List类型的数据中添加重复的数据
        redisTemplate.delete(key);
        // 从数据库中查询Tag列表
        List<TagVO> tagVOS = iTagService.getTagList();
        // 获取操作Redis中List类型数据的操作器
        ListOperations<String, Object> ops = redisTemplate.opsForList();
        // 遍历查询到的Tag列表
        for (TagVO tagVO : tagVOS) {
            // 将标签数据保存到Redis中,一次只能放1个数据
            ops.rightPush(key, tagVO);
        }
    }
}
