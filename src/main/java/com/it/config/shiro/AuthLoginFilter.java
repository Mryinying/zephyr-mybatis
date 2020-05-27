package com.it.config.shiro;

import com.alibaba.fastjson.JSONObject;
import com.it.data.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author admin
 * @Date 2020/5/26 17:00
 */
public class AuthLoginFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) {
        Subject subject = SecurityUtils.getSubject();
        // 这里配合APP需求我只需要做登录检测即可
        if (subject != null && subject.isAuthenticated()) {
            // TODO 登录检测通过，这里可以添加一些自定义操作
            return Boolean.TRUE;
        }
        // 登录检测失败返货False后会进入下面的onAccessDenied()方法
        return Boolean.FALSE;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) {
        PrintWriter out = null;
        try {
            // 这里就很简单了，向Response中写入Json响应数据，需要声明ContentType及编码格式
            servletResponse.setCharacterEncoding("UTF-8");
            servletResponse.setContentType("application/json; charset=utf-8");
            out = servletResponse.getWriter();
            out.write(JSONObject.toJSONString(Result.fail(500, "未登录或登录超时")));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return Boolean.FALSE;
    }
}
