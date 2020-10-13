package cn.tedu.straw.api.service.impl;


import cn.tedu.straw.api.dto.StudentRegisterDTO;
import cn.tedu.straw.api.ex.ClassDisabledException;
import cn.tedu.straw.api.ex.InsertException;
import cn.tedu.straw.api.ex.InviteCodeException;
import cn.tedu.straw.api.ex.PhoneDuplicateException;
import cn.tedu.straw.api.mapper.ClassInfoMapper;
import cn.tedu.straw.api.mapper.UserMapper;
import cn.tedu.straw.api.mapper.UserRoleMapper;
import cn.tedu.straw.api.service.IUserService;
import cn.tedu.straw.api.util.PasswordUtils;
import cn.tedu.straw.commons.model.ClassInfo;
import cn.tedu.straw.commons.model.Role;
import cn.tedu.straw.commons.model.User;
import cn.tedu.straw.commons.model.UserRole;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-10-10
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ClassInfoMapper classInfoMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public void regStudent(StudentRegisterDTO studentRegisterDTO) {
        // ---------------------------------------------------
        // 业务层主要负责业务流程和业务逻辑，以保证数据的安全性和完整性
        // ---------------------------------------------------

        // 基于参数inviteCode调用classInfoMapper的selectOne()方法，查询班级信息
        log.debug("准备注册:参数StudentRegisterDTO >>> {}", studentRegisterDTO);
        QueryWrapper<ClassInfo> classInfoQueryWrapper = new QueryWrapper<>();
        classInfoQueryWrapper.eq("invite_code", studentRegisterDTO.getInviteCode());
        ClassInfo classInfo = classInfoMapper.selectOne(classInfoQueryWrapper);
        log.debug("根据邀请码查询班级信息 >>> {}", classInfo);
        // 判断查询结果是否为null
        if (classInfo == null) {
            // 是：邀请码不存在，不允许注册
            // -- 抛出“邀请码错误(InviteCodeException)”的异常
            throw new InviteCodeException("注册失败!邀请码错误!");
        }
        // 判断班级状态enabled是否为0(禁用)
        if (classInfo.getEnabled() == 0) {
            throw new ClassDisabledException("注册失败!尝试注册的班级已经被禁用!请联系管理员获取有效的邀请码!");
        }
        // 基于参数phone调用持久层的selectOne()方法，根据手机号码查询用户信息，得到User类型的对象result
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone", studentRegisterDTO.getPhone());
        User result = userMapper.selectOne(userQueryWrapper);
        log.debug("根据手机号码查询用户 >>> {}", result);
        // 判断查询结果result是否不为null
        if (result != null) {
            // 是：手机号码已经被注册，则不允许插入数据
            // -- 抛出“手机号码已经被占用(PhoneDuplicateException)”的异常
            throw new PhoneDuplicateException("注册失败!手机号码已经被占用!");
        }
        // 手机号码未被注册，准备插入数据

        String rawPassword = studentRegisterDTO.getPassword();
        String encodePassword = PasswordUtils.encode(rawPassword);

        // 创建User对象，名为user
        User user = new User()
                // 补全user对象中的属性值：username > 参数studentRegisterDTO.getPhone()
                .setUsername(studentRegisterDTO.getPhone())
                // 补全user对象中的属性值：nickname > 参数studentRegisterDTO.getNickname()
                .setNickname(studentRegisterDTO.getNickname())
                // 补全user对象中的属性值：password > 参数，需要加密
                .setPassword(encodePassword)
                // 补全user对象中的属性值：gender > 参数studentRegisterDTO.getGender()
                .setGender(studentRegisterDTO.getGender())
                // 补全user对象中的属性值：dayOfBirth > 参数studentRegisterDTO.getDayOfBirth()
                .setDayOfBirth(studentRegisterDTO.getDayOfBirth())
                // 补全user对象中的属性值：phone > 参数studentRegisterDTO.getPhone()
                .setPhone(studentRegisterDTO.getPhone())
                // 补全user对象中的属性值：classId > 此前查询得到的班级信息中包含classId
                .setClassId(classInfo.getId())
                // 补全user对象中的属性值：createdTime > 当前时间：LocalDateTime.now()
                .setCreatedTime(LocalDateTime.now())
                // 补全user对象中的属性值：enabled > 1
                .setEnabled(1)
                // 补全user对象中的属性值：locked > 0
                .setLocked(0)
                // 补全user对象中的属性值：type > 0
                .setType(0)
                // 补全user对象中的属性值：selfIntroduction > 参数studentRegisterDTO.getSelfIntroduction()
                .setSelfIntroduction(studentRegisterDTO.getSelfIntroduction());
        log.debug("准备插入用户数据  >>> {}", user);
        // 调用持久层的insert()方法，向用户表中插入用户提交的注册数据，获取返回值
        int rows = userMapper.insert(user);
        log.debug("插入用户数据操作结果 >>> {}", rows);
        // 判断返回值是否不为1
        if (rows != 1) {
            // 是：抛出“插入用户数据失败(InsertException)”的异常
            throw new InsertException("注册失败!服务器忙，请稍后再次尝试!");
        }

        // 向User_role表中插入数据，以分配用户角色
        UserRole userRole = new UserRole()
                .setUserId(user.getId())
                .setRoleId(Role.STUDENT);
        rows = userRoleMapper.insert(userRole);
        if (rows != 1) {
            throw new InsertException("注册失败!服务器忙，请稍后再次尝试!");
        }
    }
}
