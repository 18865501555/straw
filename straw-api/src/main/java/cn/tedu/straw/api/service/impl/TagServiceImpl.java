package cn.tedu.straw.api.service.impl;


import cn.tedu.straw.api.mapper.TagMapper;
import cn.tedu.straw.api.service.ITagService;
import cn.tedu.straw.commons.model.Tag;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-10-10
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

}
