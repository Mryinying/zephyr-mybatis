package com.it.plus.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.it.plus.sys.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2020-05-22
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("        SELECT" +
            "          r.*" +
            "        FROM" +
            "          `sys_role` AS r," +
            "          `sys_user_role` ur" +
            "        WHERE r.`id` = ur.`role_id`" +
            "          AND ur.`user_id` = #{userId}")
    List<Role> getRoles(@Param("userId") Integer userId);
}
