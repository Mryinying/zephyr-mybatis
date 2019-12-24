package com.it.plus.wcw.entity;

import java.math.BigDecimal;
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
 * 万才经理人信息表
 * </p>
 *
 * @author zzzz
 * @since 2019-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wcw_customer_info")
public class CustomerInfo extends Model<CustomerInfo> {

private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 社会信用代码
     */
    private String creditCode;

    /**
     * 经理人名称
     */
    private String name;

    /**
     * 营业执照
     */
    private String businessLicense;

    /**
     * 经理人简称
     */
    private String shortName;

    /**
     * 经理人地址
     */
    private String address;

    /**
     * 省份(字段值)
     */
    private String province;

    /**
     * 市(字段值)
     */
    private String city;

    /**
     * 区县(字段值)
     */
    private String region;

    /**
     * 法人
     */
    private String legalPerson;

    /**
     * 法人身份证号码
     */
    private String legalIdCard;

    /**
     * 审核人
     */
    private Integer auditor;

    /**
     * 审核时间
     */
    private LocalDateTime auditorTime;

    /**
     * 备注
     */
    private String note;

    /**
     * 所属行业(字段值)
     */
    private String industry;

    /**
     * 状态（1：可用2：冻结）
     */
    private Integer status;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 未提交审核（0）,审核中(1),审核不通过(2),审核通过(3) 已撤销(4)
     */
    private Integer auditStatus;

    /**
     * 本商户设置的个人起征点
     */
    private BigDecimal pThreshold;

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

    /**
     * 考勤是否需要照片（1不需要2需要）
     */
    private Integer hasPhoto;

    /**
     * 经理人类型（1.民营  2.国企  3.外资  4.其他）
     */
    private Integer customerType;

    /**
     * 是否上市（1.是  2.不是）
     */
    private Integer isListed;

    /**
     * 规模大小，选项：1.少于15人、2.15-50人、3.50-150人、4.150-500人、5.500-2000人、6.2000人以上
     */
    private Integer size;

    /**
     * 经理人简介
     */
    private String profile;

    /**
     * 经理人logo
     */
    private String logo;

    /**
     * 是否收取经理人管理费（1需要 2不需要）
     */
    private Integer needManagerFee;

    /**
     * 单条考勤收取的管理费用
     */
    private BigDecimal managerFee;

    /**
     * 开票服务费
     */
    private BigDecimal billServerRate;

    /**
     * 特殊考勤开启状态（1.开 2.关）
     */
    private Integer emptyTalentSwitch;

    /**
     * 管理费状态(1.未开通，2.免费试用，3.正常，4.欠费)
     */
    private Integer feeStatus;

    /**
     * 日结人员支付方式1外包 2代发收入 3自行选择
     */
    private Integer dailyPayType;

    /**
     * 经理人下状态为正常的班务经理数量上限
     */
    private Integer managerLimit;

    /**
     * 0 不需要接收确认 1需要
     */
    private Integer recommendMode;

    /**
     * 审核未通过原因
     */
    private String failReason;

    private String wsBankNo;

    /**
     * 付款手续费
     */
    private BigDecimal payFee;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
