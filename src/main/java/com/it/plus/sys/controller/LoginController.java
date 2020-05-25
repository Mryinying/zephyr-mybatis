package com.it.plus.sys.controller;

import com.it.data.CacheUser;
import com.it.data.Result;
import com.it.plus.sys.entity.User;
import com.it.plus.sys.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description：登录Controller
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private IUserService userService;

    /**
     * description: 登录
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result login(User user) {
        log.warn("进入登录.....");

        String userName = user.getUserName();
        String password = user.getPassword();

        CacheUser loginUser = userService.login(userName, password);
        // 登录成功返回用户信息
        return Result.success("登录成功！", loginUser);
    }

    /**
     * description: 登出
     */
    @GetMapping("/logout")
    public Result logOut() {
        userService.logout();
        return Result.successMsg("登出成功！");
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * @return
     */
    @GetMapping("/un_auth")
    public Result unAuth() {
        return Result.fail(HttpStatus.UNAUTHORIZED.value(), "用户未登录！");
    }

    /**
     * 未授权，无权限，此处返回未授权状态信息由前端控制跳转页面
     * @return
     */
    @GetMapping("/unauthorized")
    public Result unauthorized() {
        return Result.fail(HttpStatus.FORBIDDEN.value(), "用户无权限！");
    }
}
