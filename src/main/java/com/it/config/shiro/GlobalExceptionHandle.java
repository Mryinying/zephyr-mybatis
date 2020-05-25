package com.it.config.shiro;

import com.it.data.Constants;
import com.it.data.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(ShiroException.class)
    public Result doHandleShiroException(ShiroException e) {
        //这里从登录方法抓取到异常之后会统一跳转到登录页面
        if (e instanceof UnknownAccountException) {
            log.error("该账号不存在",e);
            //该账号不存在
            return Result.fail(Constants.Global.FAILURE,"该账号不存在");
        }
        if (e instanceof LockedAccountException) {
            log.error("该账户已被锁定",e);
            //该账户已被锁定,请联系管理员
            return Result.fail(Constants.Global.FAILURE,"该账户已被锁定,请联系管理员");
        }
        if (e instanceof IncorrectCredentialsException) {
            log.error("密码错误",e);
            //密码错误
            return Result.fail(Constants.Global.FAILURE,"密码错误");
        }
        if (e instanceof AuthorizationException) {
            log.error("没有相应权限",e);
            //没有相应权限，请联系管理员
            String message = e.getMessage();
            String msg=message.substring(message.indexOf("[")+1,message.indexOf("]"));
            //判断是角色错误还是权限错误
            if (message.contains("role")) {
                return Result.fail(Constants.Global.FAILURE,"对不起，您没有" + msg + "角色");
            } else if (message.contains("permission")) {
                return Result.fail(Constants.Global.FAILURE,"对不起，您没有" + msg + "权限");
            } else {
                return Result.fail(Constants.Global.FAILURE,"没有相应权限，请联系管理员");
            }
        } else {
            log.error("登录失败",e);
            //登录失败
            return Result.fail(Constants.Global.FAILURE,"登录失败");
        }
    }


}
