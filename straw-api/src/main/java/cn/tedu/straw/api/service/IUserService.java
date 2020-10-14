package cn.tedu.straw.api.service;


import cn.tedu.straw.api.dto.StudentRegisterDTO;
import cn.tedu.straw.api.vo.UserLoginVO;
import cn.tedu.straw.commons.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-10-10
 */
public interface IUserService extends IService<User> {
    /**
     * 学生注册
     *
     * @param studentRegisterDTO 学生通过客户端提交的注册信息
     */
    void regStudent(StudentRegisterDTO studentRegisterDTO);

    /**
     * 获取当前尝试登陆的用户详情
     *
     * @param username 用户名
     * @return 匹配的用户详情，如果没有匹配的数据，则返回null
     */
    UserLoginVO getUserLoginDetails(String username);
}
