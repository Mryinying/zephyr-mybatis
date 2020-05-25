package com.it.plus.sys.mapper;

import com.it.plus.sys.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2020-05-22
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("        SELECT" +
            "          p.*" +
            "        FROM" +
            "          `sys_permission` AS p," +
            "          `sys_role_permission` AS rp" +
            "        WHERE p.`id` = rp.`permission_id`" +
            "          AND rp.`role_id` = #{roleId}")
    List<Permission> getRolePermissions(@Param("roleId") Integer roleId);
}
