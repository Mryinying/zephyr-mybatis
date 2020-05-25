package com.it.plus.sys.service;

import com.it.plus.sys.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2020-05-22
 */
public interface IPermissionService extends IService<Permission> {

    List<Permission> getRolePermissions(Integer id);
}
