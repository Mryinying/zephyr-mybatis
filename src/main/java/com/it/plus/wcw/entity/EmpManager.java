package com.it.plus.wcw.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 班务经理普工人才库
 * </p>
 *
 * @author zzzz
 * @since 2019-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wcw_emp_manager")
public class EmpManager extends Model<EmpManager> {

private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 普工id
     */
    private Integer talentId;

    /**
     * 班务经理id
     */
    private Integer managerId;

    /**
     * 离职时间
     */
    private LocalDate quitTime;

    /**
     * 绑定时间
     */
    private LocalDateTime bindingTime;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 创建人
     */
    private Integer createdBy;

    /**
     * 修改时间
     */
    private LocalDateTime updatedAt;

    /**
     * 修改人
     */
    private Integer updatedBy;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
