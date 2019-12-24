package com.it.plus.wcw.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 万才班务经理
 * </p>
 *
 * @author zzzz
 * @since 2019-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wcw_manager")
public class Manager extends Model<Manager> {

private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 经理人id
     */
    private Integer customerId;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 姓名
     */
    private String name;

    /**
     * 防重名
     */
    private String nameAlias;

    /**
     * 状态（1待认证，2已认证）
     */
    private Integer status;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 地址
     */
    private String address;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 认证通过时间
     */
    private LocalDateTime authPassTime;

    /**
     * 性别（1.男 2.女）(弃用)
     */
    private Integer sex;

    /**
     * 是否拥有企业（1：没有2：拥有）(弃用)
     */
    private Integer hasCompany;

    /**
     * 免费试用过期时间，认证通过后会有该字段(弃用)
     */
    private LocalDateTime freeDayExpireTime;

    /**
     * 认证失败理由(弃用)
     */
    private String refuseReason;

    /**
     * 管理费状态(1.未开通，2.免费试用，3.正常，4.欠费)(弃用)
     */
    private Integer feeStatus;

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
