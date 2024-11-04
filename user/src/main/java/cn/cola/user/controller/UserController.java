package cn.cola.user.controller;

import cn.cola.common.common.BaseResponse;
import cn.cola.common.common.ErrorCode;
import cn.cola.common.common.ResultUtils;
import cn.cola.common.constant.UserConstant;
import cn.cola.common.exception.ThrowUtils;
import cn.cola.common.utils.JwtUtils;
import cn.cola.service.user.model.dto.LoginDTO;
import cn.cola.service.user.model.dto.RegisterDTO;
import cn.cola.service.user.model.vo.UserVO;
import cn.cola.service.user.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.net.HttpCookie;

/**
 * 用户控制器
 *
 * @author ColaBlack
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 发送验证码
     */
    @GetMapping("/sendEmail")
    public BaseResponse<String> sendEmail(
            @RequestParam(value = "userAccount") String userAccount,
            @RequestParam(value = "email") String email) {
        return ResultUtils.success(userService.sendCode(userAccount, email));
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public BaseResponse<String> register(@RequestBody RegisterDTO registerDTO) {
        String userAccount = registerDTO.getUserAccount();
        String password = registerDTO.getPassword();
        String checkPassword = registerDTO.getCheckPassword();
        String email = registerDTO.getEmail();
        String code = registerDTO.getCode();
        String ret = userService.register(userAccount, password, checkPassword, email, code);
        return ResultUtils.success(ret);
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public BaseResponse<UserVO> login(@RequestBody LoginDTO loginDTO,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        String userAccount = loginDTO.getUserAccount();
        String password = loginDTO.getPassword();
        UserVO ret = userService.login(userAccount, password, request, response);
        return ResultUtils.success(ret);
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public BaseResponse<String> logout(HttpServletRequest request, HttpServletResponse response) {
        String ret = userService.logout(request, response);
        return ResultUtils.success(ret);
    }

    /**
     * 获取登录用户信息
     */
    @GetMapping("/get/UserVO")
    public BaseResponse<UserVO> getUserVO(
            @CookieValue(value = UserConstant.USER_LOGIN_STATE)
            HttpCookie cookie) {
        ThrowUtils.throwIf(
                cookie == null || userService.validLoginStatus(cookie.getValue()),
                ErrorCode.NOT_LOGIN_ERROR,
                "请先登录");
        UserVO ret = JwtUtils.verifyAndGetUserVO(cookie.getValue());
        return ResultUtils.success(ret);
    }
}