package com.it.plus.wcw.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 商户员工关系表
 * </p>
 *
 * @author zzzz
 * @since 2019-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wcw_emp_customer")
public class EmpCustomer extends Model<EmpCustomer> {

    private static final long serialVersionUID = 1L;

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
     * 客户id
     */
    private Integer customerId;

    /**
     * 结算方式
     */
    private Integer settlementType;

    /**
     * 支付方式1外包 2代发工资
     */
    private Integer payType;

    /**
     * 状态（1：在用2：停用）
     */
    private Integer status;

    /**
     * 启用时间
     */
    private LocalDateTime startTime;

    /**
     * 停用时间
     */
    private LocalDateTime stopTime;

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
