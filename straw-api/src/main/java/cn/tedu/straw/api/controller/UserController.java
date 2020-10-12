package cn.tedu.straw.api.controller;


import cn.tedu.straw.api.dto.StudentRegisterDTO;
import cn.tedu.straw.api.ex.InsertException;
import cn.tedu.straw.api.ex.InviteCodeException;
import cn.tedu.straw.api.ex.PhoneDuplicateException;
import cn.tedu.straw.api.ex.ServiceException;
import cn.tedu.straw.api.service.IUserService;
import cn.tedu.straw.commons.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tedu.cn
 * @since 2020-10-10
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    IUserService userService;

    // http://localhost:8080/api/v1/users/student/register?phone=13100131001&password=1234&inviteCode=JSD2004-666666
    @RequestMapping("/student/register")
    public R regStudent(StudentRegisterDTO studentRegisterDTO) {
        userService.regStudent(studentRegisterDTO);
        return R.ok();
    }
}
