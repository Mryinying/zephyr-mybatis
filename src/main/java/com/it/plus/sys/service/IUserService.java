package com.it.plus.sys.service;

import com.it.data.CacheUser;
import com.it.plus.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2020-05-22
 */
public interface IUserService extends IService<User> {

    CacheUser login(String userName, String password);

    void logout();
}
