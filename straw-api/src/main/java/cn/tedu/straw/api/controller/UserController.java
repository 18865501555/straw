package cn.tedu.straw.api.controller;


import cn.tedu.straw.api.dto.StudentRegisterDTO;
import cn.tedu.straw.api.ex.InsertException;
import cn.tedu.straw.api.ex.InviteCodeException;
import cn.tedu.straw.api.ex.PhoneDuplicateException;
import cn.tedu.straw.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tedu.cn
 * @since 2020-10-10
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    IUserService userService;
    // http://localhost:8080/api/v1/users/student/register?phone=13100131001&password=1234&inviteCode=JSD2004-666666
    @RequestMapping("/student/register")
    public String regStudent(StudentRegisterDTO studentRegisterDTO) {
        try {
            userService.regStudent(studentRegisterDTO);
            return "注册成功!";
        } catch (InviteCodeException e) {
            return "注册失败!邀请码错误!";
        } catch (PhoneDuplicateException e) {
            return "注册失败!手机号码已经被占用!";
        } catch (InsertException e) {
            return "注册失败!服务器忙!请稍后再次尝试!";
        } catch (Throwable e) {
            return "注册失败!出现预期以外的异常!" + e.getClass().getName() + "," + e.getMessage();
        }
    }
}
