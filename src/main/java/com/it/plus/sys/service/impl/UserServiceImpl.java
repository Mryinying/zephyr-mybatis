package com.it.plus.sys.service.impl;

import com.it.data.CacheUser;
import com.it.plus.sys.entity.User;
import com.it.plus.sys.mapper.UserMapper;
import com.it.plus.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2020-05-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public CacheUser login(String userName, String password) {

        // 获取Subject实例对象，用户实例
        Subject currentUser = SecurityUtils.getSubject();

        // 将用户名和密码封装到UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);

        // 传到 MyShiroRealm 类中的方法进行认证
        currentUser.login(token);

        // 构建缓存用户信息返回给前端
        User user = (User) currentUser.getPrincipals().getPrimaryPrincipal();
        CacheUser cacheUser = new CacheUser().setToken(currentUser.getSession().getId().toString());
        BeanUtils.copyProperties(user, cacheUser);
        return cacheUser;
    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
