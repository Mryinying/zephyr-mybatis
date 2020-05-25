package com.it.plus.sys.service.impl;

import com.it.plus.sys.entity.Role;
import com.it.plus.sys.mapper.RoleMapper;
import com.it.plus.sys.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2020-05-22
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public List<Role> getUserRoles(Integer userId) {
        return this.baseMapper.getRoles(userId);
    }
}
