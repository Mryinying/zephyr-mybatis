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
 * 万才普工信息
 * </p>
 *
 * @author zzzz
 * @since 2019-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wcw_talent")
public class Talent extends Model<Talent> {

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
     * 手机号码
     */
    private String mobile;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 身份证照片
     */
    private String idCardPositive;

    /**
     * 身份证反面照片
     */
    private String idCardNegative;

    /**
     * 手持身份证
     */
    private String idCardHand;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 姓名（防重名）
     */
    private String realNameAlias;

    /**
     * 员工类型（1其他2：学生3：退休人员）
     */
    private Integer talentType;

    /**
     * 状态（1：待认证2:已认证）
     */
    private Integer status;

    /**
     * 是否启用默认签名（1.启用默认签名，2.不启用默认签名）
     */
    private Integer signatureStatus;

    /**
     * 默认签名url
     */
    private String signature;

    /**
     * 冻结状态（1：正常2冻结）
     */
    private Integer type;

    /**
     * 是否独生子女
     */
    private Integer isOnlyChild;

    /**
     * 邮箱
     */
    private String eMail;

    /**
     * qq
     */
    private String qq;

    /**
     * we_chat
     */
    private String weChat;

    /**
     * 男(1),女(2)
     */
    private Integer sex;

    /**
     * 民族
     */
    private String nation;

    /**
     * 出生年月日期
     */
    private LocalDate birthday;

    /**
     * 籍贯
     */
    private String origin;

    /**
     * 学历 
     */
    private String education;

    /**
     * 未婚(1),已婚(2),离婚(3),丧偶(4)
     */
    private Integer marriage;

    /**
     * 健康情况，自填
     */
    private String healthy;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 区县
     */
    private String region;

    /**
     * 市
     */
    private String city;

    /**
     * 省份
     */
    private String province;

    /**
     * 常住地址（家庭地址）
     */
    private String address;

    /**
     * 户口所在地
     */
    private String originAddress;

    /**
     * 身高，单位cm
     */
    private Integer height;

    /**
     * 体重 单位:千克
     */
    private Integer weight;

    /**
     * 是否黑名单 否(1),是(2)
     */
    private Integer isBlacklist;

    /**
     * 是否删除(1:正常,2:已删除)
     */
    private Integer isDeleted;

    /**
     * 备注
     */
    private String remark;

    /**
     * 拉黑原因
     */
    private String blackReason;

    /**
     * 视频最佳帧截图
     */
    private String bestFrame;

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
     * 是否进行人脸核身（0否 1是）
     */
    private Integer isRecognised;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
