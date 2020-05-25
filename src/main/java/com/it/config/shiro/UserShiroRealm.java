package com.it.config.shiro;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.it.plus.sys.entity.Permission;
import com.it.plus.sys.entity.Role;
import com.it.plus.sys.entity.User;
import com.it.plus.sys.service.IPermissionService;
import com.it.plus.sys.service.IRoleService;
import com.it.plus.sys.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * @Author admin
 * @Date 2020/5/20 12:11
 */
@Slf4j
public class UserShiroRealm extends AuthorizingRealm {
    @Autowired
    private IRoleService roleService;//角色模模块
    @Autowired
    private IUserService userService;//用户模块
    @Autowired
    private IPermissionService permissionService;//权限模块
    /**
     * 用户身份识别(登录")
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken authToken = (UsernamePasswordToken) authenticationToken;
        // 获取用户输入的账号
        String userName = authToken.getUsername();
        //通过账号查找用户信息
        User user= userService.getOne(Wrappers.lambdaQuery(User.class).eq(User::getUserName,userName));
        // 将账户名,密码,盐值,getName()实例化到SimpleAuthenticationInfo中交给Shiro来管理
        return new SimpleAuthenticationInfo( user,  user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
    }

    /**
     * 访问控制。比如某个用户是否具有某个操作的使用权限
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        if (user == null) {
            log.error("授权失败，用户信息为空！！！");
            return null;
        }
        try {
            //获取用户角色集
            List<Role> roles= roleService.getUserRoles(user.getId());
            for (Role role : roles) {
                simpleAuthorizationInfo.addRole(role.getRole());
                // 根据角色查询权限
                List<Permission> permissions = permissionService.getRolePermissions(role.getId());
                for (Permission p : permissions) {
                    simpleAuthorizationInfo.addStringPermission(p.getPermission());
                }
            }
            return simpleAuthorizationInfo;
        } catch (Exception e) {
            log.error("授权失败，请检查系统内部错误!!!", e);
        }
        return simpleAuthorizationInfo;
    }
}