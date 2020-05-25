package com.it.plus.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.it.plus.sys.entity.Permission;
import com.it.plus.sys.mapper.PermissionMapper;
import com.it.plus.sys.service.IPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2020-05-22
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public List<Permission> getRolePermissions(Integer roleId) {

        return this.baseMapper.getRolePermissions(roleId);
    }
}
