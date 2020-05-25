/*
 Navicat Premium Data Transfer

 Source Server         : vm-master
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 192.168.85.22:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 22/05/2020 09:54:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `available` bit(1) NULL DEFAULT NULL,
  `parent_id` int(11) NULL DEFAULT NULL,
  `parent_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permission_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource_type` enum('menu','button') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `available` bit(1) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_8sggqkp1sv8guimk979mf6anf`(`role`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `expired_date` date NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` tinyint(4) NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_hl8fftx66p59oqgkkcfit3eay`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wcw_customer_info
-- ----------------------------
DROP TABLE IF EXISTS `wcw_customer_info`;
CREATE TABLE `wcw_customer_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `credit_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社会信用代码',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经理人名称',
  `business_license` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业执照',
  `short_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经理人简称',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经理人地址',
  `province` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份(字段值)',
  `city` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市(字段值)',
  `region` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区县(字段值)',
  `legal_person` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人',
  `legal_id_card` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人身份证号码',
  `id_card_positive` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证照片',
  `id_card_negative` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证反面照片',
  `auditor` int(11) NULL DEFAULT NULL COMMENT '审核人',
  `auditor_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `industry` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属行业(字段值)',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态（1：可用2：冻结）',
  `longitude` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经度',
  `latitude` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纬度',
  `audit_status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '未提交审核（0）,审核中(1),审核不通过(2),审核通过(3) 已撤销(4)',
  `p_threshold` decimal(10, 2) NULL DEFAULT 3500.00 COMMENT '本商户设置的个人起征点',
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `updated_by` int(11) NULL DEFAULT NULL COMMENT '修改人',
  `has_photo` tinyint(1) NULL DEFAULT 2 COMMENT '考勤是否需要照片（1不需要2需要）',
  `customer_type` tinyint(4) UNSIGNED NOT NULL DEFAULT 1 COMMENT '经理人类型（1.正常  2.外包商）',
  `need_manager_fee` tinyint(2) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否收取经理人管理费（1需要 2不需要）',
  `manager_fee` decimal(10, 2) UNSIGNED NULL DEFAULT 2.00 COMMENT '单条考勤收取的管理费用',
  `bill_server_rate` decimal(10, 2) UNSIGNED NULL DEFAULT 2.00 COMMENT '开票服务费',
  `empty_talent_switch` tinyint(4) UNSIGNED NOT NULL DEFAULT 2 COMMENT '特殊考勤开启状态（1.开 2.关）',
  `fee_status` tinyint(1) UNSIGNED NULL DEFAULT 3 COMMENT '管理费状态(1.未开通，2.免费试用，3.正常，4.欠费)',
  `daily_pay_type` tinyint(4) UNSIGNED NULL DEFAULT 2 COMMENT '日结人员支付方式1外包 2代发收入 3自行选择',
  `manager_limit` int(11) UNSIGNED NULL DEFAULT 30 COMMENT '经理人下状态为正常的班务经理数量上限',
  `recommend_mode` tinyint(1) NULL DEFAULT 1 COMMENT '0 不需要接收确认 1需要',
  `fail_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核未通过原因',
  `pay_fee` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '付款手续费',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx1`(`short_name`, `name`, `credit_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '万才经理人信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wcw_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `wcw_dictionary`;
CREATE TABLE `wcw_dictionary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dic_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典名称',
  `dic_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典代码',
  `dic_val` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典值',
  `level` tinyint(1) NULL DEFAULT NULL COMMENT '层级数，仅顶级才有该值',
  `need_show_category` tinyint(1) NULL DEFAULT NULL COMMENT '类目列表是否展示该类目，仅顶级类目才有该值（0.否  1.是）',
  `icon` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `pid` int(10) NULL DEFAULT 0 COMMENT '父唯一号',
  `short_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '中文简拼',
  `note` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `order_by` int(11) NULL DEFAULT 0 COMMENT '排序',
  `back_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回调url',
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) UNSIGNED NOT NULL COMMENT '创建人',
  `updated_at` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `updated_by` int(11) NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index1`(`dic_code`, `dic_val`) USING BTREE,
  INDEX `index2`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28005 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '万才字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wcw_emp_customer
-- ----------------------------
DROP TABLE IF EXISTS `wcw_emp_customer`;
CREATE TABLE `wcw_emp_customer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `talent_id` int(11) NOT NULL COMMENT '普工id',
  `customer_id` int(11) NOT NULL COMMENT '客户id',
  `settlement_type` tinyint(2) NULL DEFAULT NULL COMMENT '结算方式',
  `pay_type` tinyint(1) UNSIGNED NULL DEFAULT 2 COMMENT '支付方式1外包 2代发工资',
  `status` tinyint(2) NULL DEFAULT 1 COMMENT '状态（1：在用2：停用）',
  `start_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '启用时间',
  `stop_time` datetime(0) NULL DEFAULT NULL COMMENT '停用时间',
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `updated_by` int(11) NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx1_employee`(`talent_id`, `customer_id`, `status`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3889 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商户员工关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wcw_emp_manager
-- ----------------------------
DROP TABLE IF EXISTS `wcw_emp_manager`;
CREATE TABLE `wcw_emp_manager`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `talent_id` int(11) NULL DEFAULT NULL COMMENT '普工id',
  `manager_id` int(11) NULL DEFAULT NULL COMMENT '班务经理id',
  `quit_time` date NULL DEFAULT NULL COMMENT '离职时间',
  `binding_time` datetime(0) NULL DEFAULT NULL COMMENT '绑定时间',
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `updated_by` int(11) NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx1`(`talent_id`) USING BTREE,
  INDEX `manager_id`(`manager_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8237 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '班务经理普工人才库' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wcw_manager
-- ----------------------------
DROP TABLE IF EXISTS `wcw_manager`;
CREATE TABLE `wcw_manager`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `customer_id` int(11) NULL DEFAULT NULL COMMENT '经理人id',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `name_alias` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '防重名',
  `status` tinyint(2) UNSIGNED NULL DEFAULT 1 COMMENT '状态（1待认证，2已认证）',
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `longitude` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经度',
  `latitude` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纬度',
  `auth_pass_time` datetime(0) NULL DEFAULT NULL COMMENT '认证通过时间',
  `sex` tinyint(2) NULL DEFAULT NULL COMMENT '性别（1.男 2.女）(弃用)',
  `has_company` tinyint(2) NULL DEFAULT 2 COMMENT '是否拥有企业（1：没有2：拥有）(弃用)',
  `free_day_expire_time` datetime(0) NULL DEFAULT NULL COMMENT '免费试用过期时间，认证通过后会有该字段(弃用)',
  `refuse_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '认证失败理由(弃用)',
  `fee_status` tinyint(1) NULL DEFAULT 1 COMMENT '管理费状态(1.未开通，2.免费试用，3.正常，4.欠费)(弃用)',
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) NOT NULL COMMENT '创建人',
  `updated_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `updated_by` int(11) NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx1_manager`(`user_id`, `status`) USING BTREE,
  INDEX `mobile`(`mobile`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 452 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '万才班务经理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wcw_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `wcw_operate_log`;
CREATE TABLE `wcw_operate_log`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id ',
  `type` int(4) UNSIGNED NOT NULL COMMENT '操作目录类型 1用户管理 2系统管理',
  `content` varchar(140) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作内容',
  `be_opt_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '被操作人id ',
  `opt_id` int(11) UNSIGNED NOT NULL COMMENT '操作人id ',
  `opt_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作人id姓名 ',
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '运营后台操作记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wcw_talent
-- ----------------------------
DROP TABLE IF EXISTS `wcw_talent`;
CREATE TABLE `wcw_talent`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `id_card_positive` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证照片',
  `id_card_negative` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证反面照片',
  `id_card_hand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手持身份证',
  `real_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `real_name_alias` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名（防重名）',
  `talent_type` tinyint(2) NULL DEFAULT 1 COMMENT '员工类型（1其他2：学生3：退休人员）',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态（1：待认证2:已认证）',
  `signature_status` tinyint(1) NULL DEFAULT 2 COMMENT '是否启用默认签名（1.启用默认签名，2.不启用默认签名）',
  `signature` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '默认签名url',
  `type` tinyint(1) NULL DEFAULT 1 COMMENT '冻结状态（1：正常2冻结）',
  `is_only_child` tinyint(2) NULL DEFAULT NULL COMMENT '是否独生子女',
  `e_mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `qq` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'qq',
  `we_chat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'we_chat',
  `sex` tinyint(2) NULL DEFAULT 1 COMMENT '男(1),女(2)',
  `nation` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '汉族' COMMENT '民族',
  `birthday` date NULL DEFAULT NULL COMMENT '出生年月日期',
  `origin` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '籍贯',
  `education` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历 ',
  `marriage` tinyint(2) NULL DEFAULT 1 COMMENT '未婚(1),已婚(2),离婚(3),丧偶(4)',
  `healthy` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '健康情况，自填',
  `latitude` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纬度',
  `longitude` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经度',
  `region` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区县',
  `city` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市',
  `province` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '常住地址（家庭地址）',
  `origin_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户口所在地',
  `height` int(3) NULL DEFAULT NULL COMMENT '身高，单位cm',
  `weight` int(5) NULL DEFAULT NULL COMMENT '体重 单位:千克',
  `is_blacklist` tinyint(2) NOT NULL DEFAULT 0 COMMENT '是否黑名单 否(1),是(2)',
  `is_deleted` tinyint(2) NOT NULL DEFAULT 1 COMMENT '是否删除(1:正常,2:已删除)',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `black_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '拉黑原因',
  `best_frame` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频最佳帧截图',
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `updated_by` int(11) NULL DEFAULT NULL COMMENT '修改人',
  `is_recognised` tinyint(2) UNSIGNED NULL DEFAULT 0 COMMENT '是否进行人脸核身（0否 1是）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx2_user`(`id_card`) USING BTREE,
  INDEX `idx1_user`(`mobile`, `real_name_alias`, `id_card`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16610 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '万才普工信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wcw_user_info
-- ----------------------------
DROP TABLE IF EXISTS `wcw_user_info`;
CREATE TABLE `wcw_user_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'http://img.10000rc.com/man.jpg' COMMENT '头像url',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称（登录帐号）',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `real_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态（1：可用2：冻结）',
  `open_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信openid',
  `user_type` tinyint(1) NOT NULL COMMENT '用户类型（1.平台  2.经理人  3.班务经理app  4.经纪人app  5.普工  6.官网后台  7.客户确认人  8.淳安后台）',
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号冗余 登陆用的',
  `customer_id` int(11) NULL DEFAULT NULL COMMENT '所属客户',
  `mobile_updated_at` datetime(0) NULL DEFAULT NULL COMMENT '最新手机修改时间',
  `password_updated_at` datetime(0) NULL DEFAULT NULL COMMENT '最新密码修改时间',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最近登录时间',
  `login_ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆的IP',
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(10) NULL DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `updated_by` int(10) NULL DEFAULT NULL COMMENT '修改人',
  `push_status` tinyint(4) UNSIGNED NULL DEFAULT 1 COMMENT '是否接收推送（1.开启  2.关闭）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx1`(`user_type`, `open_id`) USING BTREE,
  INDEX `idx2`(`user_type`, `login_name`) USING BTREE,
  INDEX `idx3`(`user_type`, `id_card`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17839 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '万才用户信息' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
