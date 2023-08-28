/*
 Navicat Premium Data Transfer

 Source Server         : 新账务系统测试
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : 192.168.10.145:3306
 Source Schema         : base

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 28/08/2023 16:04:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cspa_fun_m
-- ----------------------------
DROP TABLE IF EXISTS `cspa_fun_m`;
CREATE TABLE `cspa_fun_m`  (
  `REFCODE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '流水号',
  `SYSTEM_TYPE` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源类型[待定]',
  `FUNID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能代号',
  `SEQNO` decimal(10, 0) NULL DEFAULT 0 COMMENT '序号',
  `LAYID` decimal(10, 0) NULL DEFAULT 0 COMMENT '当前层',
  `FUN_DESC` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '多语言代号-PCOLID',
  `FUN_PATH` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '对应页面路径',
  `PARENTID` decimal(10, 0) NULL DEFAULT 0 COMMENT '父功能代号(序号)',
  `LEVEL_CODE` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '层级代号（类似**Z**）',
  `SCPID` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '适用范围[A=平台/B=区域/C=产业/D=协会/O=OFS/W=全流程]',
  `FUN_TYPE` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源类型[0=菜单/1=按钮]',
  `BOT_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否是底层权限[0=否/1=是(default)]',
  `USE_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否在使用[0=否/1=是(default)]',
  `ICON_TYPE` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标类型[1=font-awesome/2=iconfont/3=icon-svg]',
  `ICON` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `POSITION` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源所在位置',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cspa_fun_m
-- ----------------------------
INSERT INTO `cspa_fun_m` VALUES (2, '11', 'index', 10, 1, '首页', '/home', 0, '', 'ABCDOW', '0', '', '1', NULL, 'home', 'home');
INSERT INTO `cspa_fun_m` VALUES (3, '11', 'sysmanage', 16, 1, '系统管理', '', 0, '', 'ABCDOW', '0', '', '1', NULL, 'gear', '');
INSERT INTO `cspa_fun_m` VALUES (4, '11', 'sysmanage:user:operationpersonalmanage', 1611, 3, '用户管理', '/sysmanage/operationpersonalmanage', 16, '', 'ABCDOW', '0', '', '1', NULL, '', '');
INSERT INTO `cspa_fun_m` VALUES (5, '11', 'sysmanage:user:operationpersonalmanage:new', 16101010, 4, '新增', '', 1611, '', 'ABCDOW', '1', '', '1', NULL, '', '');
INSERT INTO `cspa_fun_m` VALUES (6, '11', 'sysmanage:user:operationpersonalmanage:mod', 16101015, 4, '修改', '', 1611, '', 'ABCDOW', '1', '', '1', NULL, '', '');
INSERT INTO `cspa_fun_m` VALUES (7, '11', 'sysmanage:user:operationpersonalmanage:del', 16101020, 4, '删除', '', 1611, '', 'ABCDOW', '1', '', '1', NULL, '', '');
INSERT INTO `cspa_fun_m` VALUES (8, '11', 'sysmanage:user:operationpersonalmanage:query', 16101025, 4, '查询', '', 1611, '', 'ABCDOW', '1', '', '1', NULL, '', '');
INSERT INTO `cspa_fun_m` VALUES (9, '11', 'sysmanage:user:operationpersonalmanage:use', 16101030, 4, '启用/禁用', '', 1611, '', 'ABCDOW', '1', '', '1', NULL, '', '');
INSERT INTO `cspa_fun_m` VALUES (10, '11', 'sysmanage:role:rolemanage', 1612, 3, '角色管理', '/sysmanage/rolemanage', 16, '', 'ABCDOW', '0', '', '1', NULL, '', '');
INSERT INTO `cspa_fun_m` VALUES (11, '11', 'sysmanage:role:rolemanage:new', 16151010, 4, '新增', '', 1612, '', 'ABCDOW', '1', '', '1', NULL, '', '');
INSERT INTO `cspa_fun_m` VALUES (12, '11', 'sysmanage:role:rolemanage:mod', 16151015, 4, '修改', '', 1612, '', 'ABCDOW', '1', '', '1', NULL, '', '');
INSERT INTO `cspa_fun_m` VALUES (13, '11', 'sysmanage:role:rolemanage:del', 16151020, 4, '删除', '', 1612, '', 'ABCDOW', '1', '', '1', NULL, '', '');
INSERT INTO `cspa_fun_m` VALUES (14, '11', 'sysmanage:role:rolemanage:query', 16151025, 4, '查询', '', 1612, '', 'ABCDOW', '1', '', '1', NULL, '', '');
INSERT INTO `cspa_fun_m` VALUES (15, '11', 'sysmanage:role:rolemanage:use', 16151030, 4, '启用/禁用', '', 1612, '', 'ABCDOW', '1', '', '1', NULL, '', '');
INSERT INTO `cspa_fun_m` VALUES (16, '11', 'sysmanage:operationlog:loglist', 1613, 3, '操作日志', '/sysmanage/operationlog', 16, '', 'ABCDOW', '0', '', '1', NULL, '', '');
INSERT INTO `cspa_fun_m` VALUES (17, '11', 'sysmanage:template:templates', 1630, 2, '模板管理', '/sysmanage/templatesmanage', 16, '', 'ABCDOW', '0', '', '1', NULL, '', '');
INSERT INTO `cspa_fun_m` VALUES (18, '11', 'sysmanage:emai:emailconfigs', 1631, 2, '邮件配置', '/sysmanage/emailconfig', 16, '', 'ABCDOW', '0', '', '1', NULL, '', '');
INSERT INTO `cspa_fun_m` VALUES (19, '11', 'sysmanage:vacation:vacations', 1633, 2, '节假日设置', '/sysmanage/vacations', 16, '', 'ABCDOW', '0', '', '1', NULL, '', '');
INSERT INTO `cspa_fun_m` VALUES (20, '11', 'sysmanage:emai:emailhis', 1632, 2, '邮件历史', '/sysmanage/emailhistory', 16, '', 'ABCDOW', '0', '', '1', NULL, '', '');

-- ----------------------------
-- Table structure for email_config
-- ----------------------------
DROP TABLE IF EXISTS `email_config`;
CREATE TABLE `email_config`  (
  `REFCODE` decimal(10, 0) NOT NULL COMMENT '流水号',
  `PROREFCODE` decimal(10, 0) NULL DEFAULT NULL COMMENT '产品流水号',
  `EMAIL_TYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮件类型[01=到账通知/02=发送申购完成确认书/\r\n03=合约签署完成通知/04=发送赎回申请书/\r\n05=赎回申请已接收通知/06=配息通知/\r\n07=合约即将到期提醒/08=账户变更通知]',
  `CUST_TYPE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收件人客户',
  `CC` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '抄送[开发业务=1/渠道=2]',
  `CC_ROLE` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '抄送角色',
  `BCC` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密抄[开发业务=1/渠道=2]',
  `BCC_ROLE` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密抄角色',
  `CONTENT` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮件内容',
  `TITLE` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮件标题',
  `DATA_STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资料状态[01=保存(即先在本地保存，还未提交到审批流中去）/11=流程中/99=生效]',
  `ENTITY_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批流PK',
  `DEL_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标记[是=1/否=0(Default)]',
  `CREATE_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATE_AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者代理人',
  `CREATE_DATE` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `EDTID` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `LAST_MOD_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者',
  `AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作代理人',
  `LAST_MOD_DATE` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮件配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of email_config
-- ----------------------------

-- ----------------------------
-- Table structure for email_send_his
-- ----------------------------
DROP TABLE IF EXISTS `email_send_his`;
CREATE TABLE `email_send_his`  (
  `REFCODE` decimal(10, 0) NOT NULL COMMENT '流水号',
  `TXNO` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '批次号',
  `PROREFCODE` decimal(10, 0) NULL DEFAULT NULL COMMENT '产品流水号',
  `CUSTREFCODE` decimal(10, 0) NULL DEFAULT NULL COMMENT '客户流水号',
  `FCN_NUM` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '合约编号',
  `EMAIL_TYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮件类型[01=到账通知/02=发送申购完成确认书/\r\n03=合约签署完成通知/04=发送赎回申请书/\r\n05=赎回申请已接收通知/06=配息通知/\r\n07=合约即将到期提醒/08=账户变更通知]',
  `HOST` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮件服务器',
  `SSL_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否开启SSL[是=1/否=0(Default)]',
  `PORT` decimal(10, 0) NULL DEFAULT NULL COMMENT '端口号',
  `USERNAME` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `EMAIL_FROM` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发件人',
  `EMAIL_TO` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收件人',
  `EMAIL_CC` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '抄送',
  `EMAIL_BCC` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密抄',
  `FILE_UUID` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件',
  `GENFILE_UUID` varchar(1001) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成的附件',
  `ENCRYPTION_FILE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件发送邮件时加密码（针对PDF文件）',
  `CONTENT` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮件内容',
  `TITLE` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮件标题',
  `DELAY_TIME` datetime NULL DEFAULT NULL COMMENT '预约发送时间',
  `ACTUAL_TIME` datetime NULL DEFAULT NULL COMMENT '实际发送时间',
  `SEND_STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发送状态[1=未发送/2=发送成功/3=发送失败]',
  `DATA_STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资料状态[01=保存(即先在本地保存，还未提交到审批流中去）/11=流程中/99=生效]',
  `ENTITY_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批流PK',
  `DEL_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标记[是=1/否=0(Default)]',
  `CREATE_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATE_AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者代理人',
  `CREATE_DATE` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `EDTID` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `LAST_MOD_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者',
  `AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作代理人',
  `LAST_MOD_DATE` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮件发送历史' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of email_send_his
-- ----------------------------

-- ----------------------------
-- Table structure for fbtx_apx_m
-- ----------------------------
DROP TABLE IF EXISTS `fbtx_apx_m`;
CREATE TABLE `fbtx_apx_m`  (
  `REFCODE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '流水号',
  `RESOURCE_UUID` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源唯一编号',
  `FILE_UUID` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件上传对象惟一编号',
  `FILE_SOURCE` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件来源[1-本系统/2-文档中心]',
  `ENCRYPT_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否加密(0=否/1=是)',
  `FILE_NAME` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `FILE_TYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件类型/后缀名',
  `FILE_PATH` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `STORE_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件存储名',
  `MEMO` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '说明',
  `FILE_ID` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文档中心文件ID',
  `BIZ_KEY` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文档中心业务主键（存储路径）',
  `DATA_SOURCE` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '数据来源[1=本系统/2=其它平台]',
  `TPP_REFCODE` decimal(10, 0) NULL DEFAULT 0 COMMENT '第三方平台流水号',
  `DOWNLOADED` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否已成功下载[1=是/0=否]',
  `DOWNLOAD_COUNT` decimal(10, 0) NULL DEFAULT 0 COMMENT '文件下载次数',
  `DATA_STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资料状态[01=保存(即先在本地保存，还未提交到审批流中去）/11=流程中/99=生效]',
  `ENTITY_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批流PK',
  `DEL_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标记[是=1/否=0(Default)]',
  `CREATE_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATE_AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者代理人',
  `CREATE_DATE` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `EDTID` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `LAST_MOD_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者',
  `AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作代理人',
  `LAST_MOD_DATE` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '附件存储表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fbtx_apx_m
-- ----------------------------

-- ----------------------------
-- Table structure for fbtx_not_m
-- ----------------------------
DROP TABLE IF EXISTS `fbtx_not_m`;
CREATE TABLE `fbtx_not_m`  (
  `REFCODE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '流水号',
  `PUSH_TYPE` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息发起用户类型[1=系统/2=企业/3=用户]',
  `PUSH_ORG_REFCODE` decimal(10, 0) NULL DEFAULT 0 COMMENT '消息发起企业(机构)流水号',
  `RECEIVE_USERID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息接收用户id',
  `RECEIVE_ORG_REFCODE` decimal(10, 0) NULL DEFAULT 0 COMMENT '消息接收企业(机构)流水号',
  `NOTICE_CODE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息编号',
  `NOTICE_TITLE` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息标题',
  `NOTICE_CONTENT` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息内容',
  `NOTICE_CONTENT_HREF` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息带链接内容',
  `NOTICE_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '消息状态(0=未读/1=已读)',
  `NOTICE_TYPE` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '消息类型[1=正常/2=商承通/T=贸易通]',
  `READ_TIME` datetime NULL DEFAULT NULL COMMENT '读取时间',
  `SHOW_HOME` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否在首页显示(0=否/1=是)',
  `ORGNAME` varchar(450) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接收企业名称',
  `COMPANY_CREDIT_CODE` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接收企业统一社会信用代码',
  `BRAND_REFCODE` decimal(10, 0) NULL DEFAULT 0 COMMENT '接收企业品牌流水号',
  `TASK_TYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务类型[A=审批流待办/B=待受理报价/C=待签约项目/D=待确认应收单/E=待确认应付单/F=待签收保付单/G=待受理开单申请/H=待受理项目/I=待业务审核/J=待发起签约/K=待审批放款申请/L=待兑付保付单/M=待报价项目/N=项目审核/O=待确认签约/P=资产融资申请待受理/Q=资产融资确认/R=资产融资放款审批/S=发票补录提醒]]',
  `DATA_STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资料状态[01=保存(即先在本地保存，还未提交到审批流中去）/11=流程中/99=生效]',
  `ENTITY_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批流PK',
  `DEL_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标记[是=1/否=0(Default)]',
  `CREATE_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATE_AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者代理人',
  `CREATE_DATE` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `EDTID` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `LAST_MOD_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者',
  `AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作代理人',
  `LAST_MOD_DATE` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '平台消息提醒表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fbtx_not_m
-- ----------------------------

-- ----------------------------
-- Table structure for fbtx_ur_msg_m
-- ----------------------------
DROP TABLE IF EXISTS `fbtx_ur_msg_m`;
CREATE TABLE `fbtx_ur_msg_m`  (
  `REFCODE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '流水号',
  `TITLE` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '推送标题',
  `CONTENT` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '推送内容',
  `RECEIVE_ORG_REFCODE` decimal(10, 0) NULL DEFAULT 0 COMMENT '接收机构流水号',
  `BRAND_REFCODE` decimal(10, 0) NULL DEFAULT 0 COMMENT '品牌流水号',
  `RECEIVE_USERID` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接收用户ID',
  `DATA_STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资料状态[01=保存(即先在本地保存，还未提交到审批流中去）/11=流程中/99=生效]',
  `ENTITY_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批流PK',
  `DEL_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标记[是=1/否=0(Default)]',
  `CREATE_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATE_AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者代理人',
  `CREATE_DATE` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `EDTID` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `LAST_MOD_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者',
  `AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作代理人',
  `LAST_MOD_DATE` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '未读即时消息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fbtx_ur_msg_m
-- ----------------------------

-- ----------------------------
-- Table structure for fipa_are_m
-- ----------------------------
DROP TABLE IF EXISTS `fipa_are_m`;
CREATE TABLE `fipa_are_m`  (
  `REFCODE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '流水号',
  `AREA_LEVEL` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '地区级别[1=省/2=市/3=区]',
  `AREA_TYPE_CODE` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '区域类型代码[1=华北/2=东北/3=华东/4=华中/5=华南/6=西南/7=西北/8=台港澳]',
  `AREA_CODE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '行政区划代码',
  `PROVINCE_CODE` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '省代码',
  `AREA_DESC` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '行政区域',
  `PROVINCE_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '省名称',
  `CITY_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '市名称',
  `COUNTY_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '县名称',
  `TOWN_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '乡镇名称',
  `POST_CODE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮编',
  `DATA_STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资料状态[01=保存(即先在本地保存，还未提交到审批流中去）/11=流程中/99=生效]',
  `ENTITY_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批流PK',
  `DEL_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标记[是=1/否=0(Default)]',
  `CREATE_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATE_AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者代理人',
  `CREATE_DATE` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `EDTID` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `LAST_MOD_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者',
  `AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作代理人',
  `LAST_MOD_DATE` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '地区信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fipa_are_m
-- ----------------------------
INSERT INTO `fipa_are_m` VALUES (1, '1', '1', '110000', '11', '北京', '北京', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (2, '2', '1', '110100', '11', '北京市', '北京', '北京市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3, '3', '1', '110101', '11', '东城区', '北京', '北京市', '东城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4, '3', '1', '110102', '11', '西城区', '北京', '北京市', '西城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5, '3', '1', '110105', '11', '朝阳区', '北京', '北京市', '朝阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (6, '3', '1', '110106', '11', '丰台区', '北京', '北京市', '丰台区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (7, '3', '1', '110107', '11', '石景山区', '北京', '北京市', '石景山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (8, '3', '1', '110108', '11', '海淀区', '北京', '北京市', '海淀区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (9, '3', '1', '110109', '11', '门头沟区', '北京', '北京市', '门头沟区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (10, '3', '1', '110111', '11', '房山区', '北京', '北京市', '房山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (11, '3', '1', '110112', '11', '通州区', '北京', '北京市', '通州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (12, '3', '1', '110113', '11', '顺义区', '北京', '北京市', '顺义区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (13, '3', '1', '110114', '11', '昌平区', '北京', '北京市', '昌平区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (14, '3', '1', '110115', '11', '大兴区', '北京', '北京市', '大兴区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (15, '3', '1', '110116', '11', '怀柔区', '北京', '北京市', '怀柔区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (16, '3', '1', '110117', '11', '平谷区', '北京', '北京市', '平谷区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (17, '3', '1', '110118', '11', '密云区', '北京', '北京市', '密云区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (18, '3', '1', '110119', '11', '延庆区', '北京', '北京市', '延庆区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (19, '1', '1', '120000', '12', '天津', '天津', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (20, '2', '1', '120100', '12', '天津市', '天津', '天津市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (21, '3', '1', '120101', '12', '和平区', '天津', '天津市', '和平区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (22, '3', '1', '120102', '12', '河东区', '天津', '天津市', '河东区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (23, '3', '1', '120103', '12', '河西区', '天津', '天津市', '河西区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (24, '3', '1', '120104', '12', '南开区', '天津', '天津市', '南开区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (25, '3', '1', '120105', '12', '河北区', '天津', '天津市', '河北区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (26, '3', '1', '120106', '12', '红桥区', '天津', '天津市', '红桥区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (27, '3', '1', '120110', '12', '东丽区', '天津', '天津市', '东丽区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (28, '3', '1', '120111', '12', '西青区', '天津', '天津市', '西青区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (29, '3', '1', '120112', '12', '津南区', '天津', '天津市', '津南区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (30, '3', '1', '120113', '12', '北辰区', '天津', '天津市', '北辰区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (31, '3', '1', '120114', '12', '武清区', '天津', '天津市', '武清区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (32, '3', '1', '120115', '12', '宝坻区', '天津', '天津市', '宝坻区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (33, '3', '1', '120116', '12', '滨海新区', '天津', '天津市', '滨海新区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (34, '3', '1', '120117', '12', '宁河区', '天津', '天津市', '宁河区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (35, '3', '1', '120118', '12', '静海区', '天津', '天津市', '静海区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (36, '3', '1', '120119', '12', '蓟州区', '天津', '天津市', '蓟州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (37, '1', '1', '130000', '13', '河北省', '河北省', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (38, '2', '1', '130100', '13', '石家庄市', '河北省', '石家庄市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (39, '3', '1', '130101', '13', '市辖区', '河北省', '石家庄市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (40, '3', '1', '130102', '13', '长安区', '河北省', '石家庄市', '长安区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (41, '3', '1', '130104', '13', '桥西区', '河北省', '石家庄市', '桥西区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (42, '3', '1', '130105', '13', '新华区', '河北省', '石家庄市', '新华区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (43, '3', '1', '130107', '13', '井陉矿区', '河北省', '石家庄市', '井陉矿区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (44, '3', '1', '130108', '13', '裕华区', '河北省', '石家庄市', '裕华区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (45, '3', '1', '130109', '13', '藁城区', '河北省', '石家庄市', '藁城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (46, '3', '1', '130110', '13', '鹿泉区', '河北省', '石家庄市', '鹿泉区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (47, '3', '1', '130111', '13', '栾城区', '河北省', '石家庄市', '栾城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (48, '3', '1', '130121', '13', '井陉县', '河北省', '石家庄市', '井陉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (49, '3', '1', '130123', '13', '正定县', '河北省', '石家庄市', '正定县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (50, '3', '1', '130125', '13', '行唐县', '河北省', '石家庄市', '行唐县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (51, '3', '1', '130126', '13', '灵寿县', '河北省', '石家庄市', '灵寿县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (52, '3', '1', '130127', '13', '高邑县', '河北省', '石家庄市', '高邑县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (53, '3', '1', '130128', '13', '深泽县', '河北省', '石家庄市', '深泽县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (54, '3', '1', '130129', '13', '赞皇县', '河北省', '石家庄市', '赞皇县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (55, '3', '1', '130130', '13', '无极县', '河北省', '石家庄市', '无极县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (56, '3', '1', '130131', '13', '平山县', '河北省', '石家庄市', '平山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (57, '3', '1', '130132', '13', '元氏县', '河北省', '石家庄市', '元氏县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (58, '3', '1', '130133', '13', '赵县', '河北省', '石家庄市', '赵县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (59, '3', '1', '130181', '13', '辛集市', '河北省', '石家庄市', '辛集市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (60, '3', '1', '130183', '13', '晋州市', '河北省', '石家庄市', '晋州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (61, '3', '1', '130184', '13', '新乐市', '河北省', '石家庄市', '新乐市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (62, '2', '1', '130200', '13', '唐山市', '河北省', '唐山市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (63, '3', '1', '130201', '13', '市辖区', '河北省', '唐山市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (64, '3', '1', '130202', '13', '路南区', '河北省', '唐山市', '路南区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (65, '3', '1', '130203', '13', '路北区', '河北省', '唐山市', '路北区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (66, '3', '1', '130204', '13', '古冶区', '河北省', '唐山市', '古冶区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (67, '3', '1', '130205', '13', '开平区', '河北省', '唐山市', '开平区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (68, '3', '1', '130207', '13', '丰南区', '河北省', '唐山市', '丰南区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (69, '3', '1', '130208', '13', '丰润区', '河北省', '唐山市', '丰润区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (70, '3', '1', '130209', '13', '曹妃甸区', '河北省', '唐山市', '曹妃甸区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (71, '3', '1', '130223', '13', '滦县', '河北省', '唐山市', '滦县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (72, '3', '1', '130224', '13', '滦南县', '河北省', '唐山市', '滦南县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (73, '3', '1', '130225', '13', '乐亭县', '河北省', '唐山市', '乐亭县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (74, '3', '1', '130227', '13', '迁西县', '河北省', '唐山市', '迁西县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (75, '3', '1', '130229', '13', '玉田县', '河北省', '唐山市', '玉田县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (76, '3', '1', '130281', '13', '遵化市', '河北省', '唐山市', '遵化市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (77, '3', '1', '130283', '13', '迁安市', '河北省', '唐山市', '迁安市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (78, '2', '1', '130300', '13', '秦皇岛市', '河北省', '秦皇岛市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (79, '3', '1', '130301', '13', '市辖区', '河北省', '秦皇岛市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (80, '3', '1', '130302', '13', '海港区', '河北省', '秦皇岛市', '海港区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (81, '3', '1', '130303', '13', '山海关区', '河北省', '秦皇岛市', '山海关区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (82, '3', '1', '130304', '13', '北戴河区', '河北省', '秦皇岛市', '北戴河区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (83, '3', '1', '130306', '13', '抚宁区', '河北省', '秦皇岛市', '抚宁区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (84, '3', '1', '130321', '13', '青龙满族自治县', '河北省', '秦皇岛市', '青龙满族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (85, '3', '1', '130322', '13', '昌黎县', '河北省', '秦皇岛市', '昌黎县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (86, '3', '1', '130324', '13', '卢龙县', '河北省', '秦皇岛市', '卢龙县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (87, '2', '1', '130400', '13', '邯郸市', '河北省', '邯郸市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (88, '3', '1', '130401', '13', '市辖区', '河北省', '邯郸市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (89, '3', '1', '130402', '13', '邯山区', '河北省', '邯郸市', '邯山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (90, '3', '1', '130403', '13', '丛台区', '河北省', '邯郸市', '丛台区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (91, '3', '1', '130404', '13', '复兴区', '河北省', '邯郸市', '复兴区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (92, '3', '1', '130406', '13', '峰峰矿区', '河北省', '邯郸市', '峰峰矿区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (93, '3', '1', '130407', '13', '肥乡区', '河北省', '邯郸市', '肥乡区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (94, '3', '1', '130408', '13', '永年区', '河北省', '邯郸市', '永年区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (95, '3', '1', '130423', '13', '临漳县', '河北省', '邯郸市', '临漳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (96, '3', '1', '130424', '13', '成安县', '河北省', '邯郸市', '成安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (97, '3', '1', '130425', '13', '大名县', '河北省', '邯郸市', '大名县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (98, '3', '1', '130426', '13', '涉县', '河北省', '邯郸市', '涉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (99, '3', '1', '130427', '13', '磁县', '河北省', '邯郸市', '磁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (100, '3', '1', '130430', '13', '邱县', '河北省', '邯郸市', '邱县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (101, '3', '1', '130431', '13', '鸡泽县', '河北省', '邯郸市', '鸡泽县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (102, '3', '1', '130432', '13', '广平县', '河北省', '邯郸市', '广平县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (103, '3', '1', '130433', '13', '馆陶县', '河北省', '邯郸市', '馆陶县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (104, '3', '1', '130434', '13', '魏县', '河北省', '邯郸市', '魏县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (105, '3', '1', '130435', '13', '曲周县', '河北省', '邯郸市', '曲周县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (106, '3', '1', '130481', '13', '武安市', '河北省', '邯郸市', '武安市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (107, '2', '1', '130500', '13', '邢台市', '河北省', '邢台市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (108, '3', '1', '130501', '13', '市辖区', '河北省', '邢台市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (109, '3', '1', '130502', '13', '桥东区', '河北省', '邢台市', '桥东区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (110, '3', '1', '130503', '13', '桥西区', '河北省', '邢台市', '桥西区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (111, '3', '1', '130521', '13', '邢台县', '河北省', '邢台市', '邢台县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (112, '3', '1', '130522', '13', '临城县', '河北省', '邢台市', '临城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (113, '3', '1', '130523', '13', '内丘县', '河北省', '邢台市', '内丘县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (114, '3', '1', '130524', '13', '柏乡县', '河北省', '邢台市', '柏乡县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (115, '3', '1', '130525', '13', '隆尧县', '河北省', '邢台市', '隆尧县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (116, '3', '1', '130526', '13', '任县', '河北省', '邢台市', '任县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (117, '3', '1', '130527', '13', '南和县', '河北省', '邢台市', '南和县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (118, '3', '1', '130528', '13', '宁晋县', '河北省', '邢台市', '宁晋县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (119, '3', '1', '130529', '13', '巨鹿县', '河北省', '邢台市', '巨鹿县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (120, '3', '1', '130530', '13', '新河县', '河北省', '邢台市', '新河县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (121, '3', '1', '130531', '13', '广宗县', '河北省', '邢台市', '广宗县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (122, '3', '1', '130532', '13', '平乡县', '河北省', '邢台市', '平乡县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (123, '3', '1', '130533', '13', '威县', '河北省', '邢台市', '威县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (124, '3', '1', '130534', '13', '清河县', '河北省', '邢台市', '清河县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (125, '3', '1', '130535', '13', '临西县', '河北省', '邢台市', '临西县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (126, '3', '1', '130581', '13', '南宫市', '河北省', '邢台市', '南宫市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (127, '3', '1', '130582', '13', '沙河市', '河北省', '邢台市', '沙河市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (128, '2', '1', '130600', '13', '保定市', '河北省', '保定市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (129, '3', '1', '130601', '13', '市辖区', '河北省', '保定市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (130, '3', '1', '130602', '13', '竞秀区', '河北省', '保定市', '竞秀区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (131, '3', '1', '130606', '13', '莲池区', '河北省', '保定市', '莲池区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (132, '3', '1', '130607', '13', '满城区', '河北省', '保定市', '满城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (133, '3', '1', '130608', '13', '清苑区', '河北省', '保定市', '清苑区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (134, '3', '1', '130609', '13', '徐水区', '河北省', '保定市', '徐水区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (135, '3', '1', '130623', '13', '涞水县', '河北省', '保定市', '涞水县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (136, '3', '1', '130624', '13', '阜平县', '河北省', '保定市', '阜平县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (137, '3', '1', '130626', '13', '定兴县', '河北省', '保定市', '定兴县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (138, '3', '1', '130627', '13', '唐县', '河北省', '保定市', '唐县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (139, '3', '1', '130628', '13', '高阳县', '河北省', '保定市', '高阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (140, '3', '1', '130629', '13', '容城县', '河北省', '保定市', '容城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (141, '3', '1', '130630', '13', '涞源县', '河北省', '保定市', '涞源县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (142, '3', '1', '130631', '13', '望都县', '河北省', '保定市', '望都县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (143, '3', '1', '130632', '13', '安新县', '河北省', '保定市', '安新县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (144, '3', '1', '130633', '13', '易县', '河北省', '保定市', '易县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (145, '3', '1', '130634', '13', '曲阳县', '河北省', '保定市', '曲阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (146, '3', '1', '130635', '13', '蠡县', '河北省', '保定市', '蠡县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (147, '3', '1', '130636', '13', '顺平县', '河北省', '保定市', '顺平县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (148, '3', '1', '130637', '13', '博野县', '河北省', '保定市', '博野县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (149, '3', '1', '130638', '13', '雄县', '河北省', '保定市', '雄县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (150, '3', '1', '130681', '13', '涿州市', '河北省', '保定市', '涿州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (151, '3', '1', '130682', '13', '定州市', '河北省', '保定市', '定州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (152, '3', '1', '130683', '13', '安国市', '河北省', '保定市', '安国市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (153, '3', '1', '130684', '13', '高碑店市', '河北省', '保定市', '高碑店市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (154, '2', '1', '130700', '13', '张家口市', '河北省', '张家口市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (155, '3', '1', '130701', '13', '市辖区', '河北省', '张家口市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (156, '3', '1', '130702', '13', '桥东区', '河北省', '张家口市', '桥东区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (157, '3', '1', '130703', '13', '桥西区', '河北省', '张家口市', '桥西区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (158, '3', '1', '130705', '13', '宣化区', '河北省', '张家口市', '宣化区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (159, '3', '1', '130706', '13', '下花园区', '河北省', '张家口市', '下花园区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (160, '3', '1', '130708', '13', '万全区', '河北省', '张家口市', '万全区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (161, '3', '1', '130709', '13', '崇礼区', '河北省', '张家口市', '崇礼区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (162, '3', '1', '130722', '13', '张北县', '河北省', '张家口市', '张北县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (163, '3', '1', '130723', '13', '康保县', '河北省', '张家口市', '康保县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (164, '3', '1', '130724', '13', '沽源县', '河北省', '张家口市', '沽源县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (165, '3', '1', '130725', '13', '尚义县', '河北省', '张家口市', '尚义县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (166, '3', '1', '130726', '13', '蔚县', '河北省', '张家口市', '蔚县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (167, '3', '1', '130727', '13', '阳原县', '河北省', '张家口市', '阳原县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (168, '3', '1', '130728', '13', '怀安县', '河北省', '张家口市', '怀安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (169, '3', '1', '130730', '13', '怀来县', '河北省', '张家口市', '怀来县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (170, '3', '1', '130731', '13', '涿鹿县', '河北省', '张家口市', '涿鹿县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (171, '3', '1', '130732', '13', '赤城县', '河北省', '张家口市', '赤城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (172, '2', '1', '130800', '13', '承德市', '河北省', '承德市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (173, '3', '1', '130801', '13', '市辖区', '河北省', '承德市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (174, '3', '1', '130802', '13', '双桥区', '河北省', '承德市', '双桥区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (175, '3', '1', '130803', '13', '双滦区', '河北省', '承德市', '双滦区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (176, '3', '1', '130804', '13', '鹰手营子矿区', '河北省', '承德市', '鹰手营子矿区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (177, '3', '1', '130821', '13', '承德县', '河北省', '承德市', '承德县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (178, '3', '1', '130822', '13', '兴隆县', '河北省', '承德市', '兴隆县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (179, '3', '1', '130824', '13', '滦平县', '河北省', '承德市', '滦平县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (180, '3', '1', '130825', '13', '隆化县', '河北省', '承德市', '隆化县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (181, '3', '1', '130826', '13', '丰宁满族自治县', '河北省', '承德市', '丰宁满族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (182, '3', '1', '130827', '13', '宽城满族自治县', '河北省', '承德市', '宽城满族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (183, '3', '1', '130828', '13', '围场满族蒙古族自治县', '河北省', '承德市', '围场满族蒙古族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (184, '3', '1', '130881', '13', '平泉市', '河北省', '承德市', '平泉市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (185, '2', '1', '130900', '13', '沧州市', '河北省', '沧州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (186, '3', '1', '130901', '13', '市辖区', '河北省', '沧州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (187, '3', '1', '130902', '13', '新华区', '河北省', '沧州市', '新华区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (188, '3', '1', '130903', '13', '运河区', '河北省', '沧州市', '运河区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (189, '3', '1', '130921', '13', '沧县', '河北省', '沧州市', '沧县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (190, '3', '1', '130922', '13', '青县', '河北省', '沧州市', '青县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (191, '3', '1', '130923', '13', '东光县', '河北省', '沧州市', '东光县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (192, '3', '1', '130924', '13', '海兴县', '河北省', '沧州市', '海兴县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (193, '3', '1', '130925', '13', '盐山县', '河北省', '沧州市', '盐山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (194, '3', '1', '130926', '13', '肃宁县', '河北省', '沧州市', '肃宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (195, '3', '1', '130927', '13', '南皮县', '河北省', '沧州市', '南皮县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (196, '3', '1', '130928', '13', '吴桥县', '河北省', '沧州市', '吴桥县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (197, '3', '1', '130929', '13', '献县', '河北省', '沧州市', '献县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (198, '3', '1', '130930', '13', '孟村回族自治县', '河北省', '沧州市', '孟村回族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (199, '3', '1', '130981', '13', '泊头市', '河北省', '沧州市', '泊头市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (200, '3', '1', '130982', '13', '任丘市', '河北省', '沧州市', '任丘市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (201, '3', '1', '130983', '13', '黄骅市', '河北省', '沧州市', '黄骅市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (202, '3', '1', '130984', '13', '河间市', '河北省', '沧州市', '河间市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (203, '2', '1', '131000', '13', '廊坊市', '河北省', '廊坊市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (204, '3', '1', '131001', '13', '市辖区', '河北省', '廊坊市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (205, '3', '1', '131002', '13', '安次区', '河北省', '廊坊市', '安次区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (206, '3', '1', '131003', '13', '广阳区', '河北省', '廊坊市', '广阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (207, '3', '1', '131022', '13', '固安县', '河北省', '廊坊市', '固安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (208, '3', '1', '131023', '13', '永清县', '河北省', '廊坊市', '永清县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (209, '3', '1', '131024', '13', '香河县', '河北省', '廊坊市', '香河县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (210, '3', '1', '131025', '13', '大城县', '河北省', '廊坊市', '大城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (211, '3', '1', '131026', '13', '文安县', '河北省', '廊坊市', '文安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (212, '3', '1', '131028', '13', '大厂回族自治县', '河北省', '廊坊市', '大厂回族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (213, '3', '1', '131081', '13', '霸州市', '河北省', '廊坊市', '霸州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (214, '3', '1', '131082', '13', '三河市', '河北省', '廊坊市', '三河市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (215, '2', '1', '131100', '13', '衡水市', '河北省', '衡水市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (216, '3', '1', '131101', '13', '市辖区', '河北省', '衡水市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (217, '3', '1', '131102', '13', '桃城区', '河北省', '衡水市', '桃城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (218, '3', '1', '131103', '13', '冀州区', '河北省', '衡水市', '冀州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (219, '3', '1', '131121', '13', '枣强县', '河北省', '衡水市', '枣强县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (220, '3', '1', '131122', '13', '武邑县', '河北省', '衡水市', '武邑县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (221, '3', '1', '131123', '13', '武强县', '河北省', '衡水市', '武强县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (222, '3', '1', '131124', '13', '饶阳县', '河北省', '衡水市', '饶阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (223, '3', '1', '131125', '13', '安平县', '河北省', '衡水市', '安平县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (224, '3', '1', '131126', '13', '故城县', '河北省', '衡水市', '故城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (225, '3', '1', '131127', '13', '景县', '河北省', '衡水市', '景县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (226, '3', '1', '131128', '13', '阜城县', '河北省', '衡水市', '阜城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (227, '3', '1', '131182', '13', '深州市', '河北省', '衡水市', '深州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (228, '1', '1', '140000', '14', '山西省', '山西省', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (229, '2', '1', '140100', '14', '太原市', '山西省', '太原市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (230, '3', '1', '140101', '14', '市辖区', '山西省', '太原市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (231, '3', '1', '140105', '14', '小店区', '山西省', '太原市', '小店区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (232, '3', '1', '140106', '14', '迎泽区', '山西省', '太原市', '迎泽区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (233, '3', '1', '140107', '14', '杏花岭区', '山西省', '太原市', '杏花岭区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (234, '3', '1', '140108', '14', '尖草坪区', '山西省', '太原市', '尖草坪区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (235, '3', '1', '140109', '14', '万柏林区', '山西省', '太原市', '万柏林区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (236, '3', '1', '140110', '14', '晋源区', '山西省', '太原市', '晋源区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (237, '3', '1', '140121', '14', '清徐县', '山西省', '太原市', '清徐县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (238, '3', '1', '140122', '14', '阳曲县', '山西省', '太原市', '阳曲县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (239, '3', '1', '140123', '14', '娄烦县', '山西省', '太原市', '娄烦县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (240, '3', '1', '140181', '14', '古交市', '山西省', '太原市', '古交市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (241, '2', '1', '140200', '14', '大同市', '山西省', '大同市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (242, '3', '1', '140201', '14', '市辖区', '山西省', '大同市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (243, '3', '1', '140202', '14', '城区', '山西省', '大同市', '城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (244, '3', '1', '140203', '14', '矿区', '山西省', '大同市', '矿区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (245, '3', '1', '140211', '14', '南郊区', '山西省', '大同市', '南郊区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (246, '3', '1', '140212', '14', '新荣区', '山西省', '大同市', '新荣区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (247, '3', '1', '140221', '14', '阳高县', '山西省', '大同市', '阳高县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (248, '3', '1', '140222', '14', '天镇县', '山西省', '大同市', '天镇县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (249, '3', '1', '140223', '14', '广灵县', '山西省', '大同市', '广灵县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (250, '3', '1', '140224', '14', '灵丘县', '山西省', '大同市', '灵丘县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (251, '3', '1', '140225', '14', '浑源县', '山西省', '大同市', '浑源县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (252, '3', '1', '140226', '14', '左云县', '山西省', '大同市', '左云县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (253, '3', '1', '140227', '14', '大同县', '山西省', '大同市', '大同县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (254, '2', '1', '140300', '14', '阳泉市', '山西省', '阳泉市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (255, '3', '1', '140301', '14', '市辖区', '山西省', '阳泉市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (256, '3', '1', '140302', '14', '城区', '山西省', '阳泉市', '城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (257, '3', '1', '140303', '14', '矿区', '山西省', '阳泉市', '矿区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (258, '3', '1', '140311', '14', '郊区', '山西省', '阳泉市', '郊区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (259, '3', '1', '140321', '14', '平定县', '山西省', '阳泉市', '平定县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (260, '3', '1', '140322', '14', '盂县', '山西省', '阳泉市', '盂县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (261, '2', '1', '140400', '14', '长治市', '山西省', '长治市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (262, '3', '1', '140401', '14', '市辖区', '山西省', '长治市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (263, '3', '1', '140402', '14', '城区', '山西省', '长治市', '城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (264, '3', '1', '140411', '14', '郊区', '山西省', '长治市', '郊区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (265, '3', '1', '140421', '14', '长治县', '山西省', '长治市', '长治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (266, '3', '1', '140423', '14', '襄垣县', '山西省', '长治市', '襄垣县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (267, '3', '1', '140424', '14', '屯留县', '山西省', '长治市', '屯留县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (268, '3', '1', '140425', '14', '平顺县', '山西省', '长治市', '平顺县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (269, '3', '1', '140426', '14', '黎城县', '山西省', '长治市', '黎城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (270, '3', '1', '140427', '14', '壶关县', '山西省', '长治市', '壶关县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (271, '3', '1', '140428', '14', '长子县', '山西省', '长治市', '长子县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (272, '3', '1', '140429', '14', '武乡县', '山西省', '长治市', '武乡县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (273, '3', '1', '140430', '14', '沁县', '山西省', '长治市', '沁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (274, '3', '1', '140431', '14', '沁源县', '山西省', '长治市', '沁源县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (275, '3', '1', '140481', '14', '潞城市', '山西省', '长治市', '潞城市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (276, '2', '1', '140500', '14', '晋城市', '山西省', '晋城市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (277, '3', '1', '140501', '14', '市辖区', '山西省', '晋城市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (278, '3', '1', '140502', '14', '城区', '山西省', '晋城市', '城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (279, '3', '1', '140521', '14', '沁水县', '山西省', '晋城市', '沁水县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (280, '3', '1', '140522', '14', '阳城县', '山西省', '晋城市', '阳城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (281, '3', '1', '140524', '14', '陵川县', '山西省', '晋城市', '陵川县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (282, '3', '1', '140525', '14', '泽州县', '山西省', '晋城市', '泽州县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (283, '3', '1', '140581', '14', '高平市', '山西省', '晋城市', '高平市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (284, '2', '1', '140600', '14', '朔州市', '山西省', '朔州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (285, '3', '1', '140601', '14', '市辖区', '山西省', '朔州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (286, '3', '1', '140602', '14', '朔城区', '山西省', '朔州市', '朔城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (287, '3', '1', '140603', '14', '平鲁区', '山西省', '朔州市', '平鲁区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (288, '3', '1', '140621', '14', '山阴县', '山西省', '朔州市', '山阴县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (289, '3', '1', '140622', '14', '应县', '山西省', '朔州市', '应县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (290, '3', '1', '140623', '14', '右玉县', '山西省', '朔州市', '右玉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (291, '3', '1', '140624', '14', '怀仁县', '山西省', '朔州市', '怀仁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (292, '2', '1', '140700', '14', '晋中市', '山西省', '晋中市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (293, '3', '1', '140701', '14', '市辖区', '山西省', '晋中市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (294, '3', '1', '140702', '14', '榆次区', '山西省', '晋中市', '榆次区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (295, '3', '1', '140721', '14', '榆社县', '山西省', '晋中市', '榆社县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (296, '3', '1', '140722', '14', '左权县', '山西省', '晋中市', '左权县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (297, '3', '1', '140723', '14', '和顺县', '山西省', '晋中市', '和顺县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (298, '3', '1', '140724', '14', '昔阳县', '山西省', '晋中市', '昔阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (299, '3', '1', '140725', '14', '寿阳县', '山西省', '晋中市', '寿阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (300, '3', '1', '140726', '14', '太谷县', '山西省', '晋中市', '太谷县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (301, '3', '1', '140727', '14', '祁县', '山西省', '晋中市', '祁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (302, '3', '1', '140728', '14', '平遥县', '山西省', '晋中市', '平遥县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (303, '3', '1', '140729', '14', '灵石县', '山西省', '晋中市', '灵石县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (304, '3', '1', '140781', '14', '介休市', '山西省', '晋中市', '介休市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (305, '2', '1', '140800', '14', '运城市', '山西省', '运城市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (306, '3', '1', '140801', '14', '市辖区', '山西省', '运城市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (307, '3', '1', '140802', '14', '盐湖区', '山西省', '运城市', '盐湖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (308, '3', '1', '140821', '14', '临猗县', '山西省', '运城市', '临猗县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (309, '3', '1', '140822', '14', '万荣县', '山西省', '运城市', '万荣县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (310, '3', '1', '140823', '14', '闻喜县', '山西省', '运城市', '闻喜县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (311, '3', '1', '140824', '14', '稷山县', '山西省', '运城市', '稷山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (312, '3', '1', '140825', '14', '新绛县', '山西省', '运城市', '新绛县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (313, '3', '1', '140826', '14', '绛县', '山西省', '运城市', '绛县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (314, '3', '1', '140827', '14', '垣曲县', '山西省', '运城市', '垣曲县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (315, '3', '1', '140828', '14', '夏县', '山西省', '运城市', '夏县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (316, '3', '1', '140829', '14', '平陆县', '山西省', '运城市', '平陆县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (317, '3', '1', '140830', '14', '芮城县', '山西省', '运城市', '芮城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (318, '3', '1', '140881', '14', '永济市', '山西省', '运城市', '永济市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (319, '3', '1', '140882', '14', '河津市', '山西省', '运城市', '河津市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (320, '2', '1', '140900', '14', '忻州市', '山西省', '忻州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (321, '3', '1', '140901', '14', '市辖区', '山西省', '忻州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (322, '3', '1', '140902', '14', '忻府区', '山西省', '忻州市', '忻府区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (323, '3', '1', '140921', '14', '定襄县', '山西省', '忻州市', '定襄县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (324, '3', '1', '140922', '14', '五台县', '山西省', '忻州市', '五台县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (325, '3', '1', '140923', '14', '代县', '山西省', '忻州市', '代县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (326, '3', '1', '140924', '14', '繁峙县', '山西省', '忻州市', '繁峙县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (327, '3', '1', '140925', '14', '宁武县', '山西省', '忻州市', '宁武县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (328, '3', '1', '140926', '14', '静乐县', '山西省', '忻州市', '静乐县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (329, '3', '1', '140927', '14', '神池县', '山西省', '忻州市', '神池县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (330, '3', '1', '140928', '14', '五寨县', '山西省', '忻州市', '五寨县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (331, '3', '1', '140929', '14', '岢岚县', '山西省', '忻州市', '岢岚县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (332, '3', '1', '140930', '14', '河曲县', '山西省', '忻州市', '河曲县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (333, '3', '1', '140931', '14', '保德县', '山西省', '忻州市', '保德县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (334, '3', '1', '140932', '14', '偏关县', '山西省', '忻州市', '偏关县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (335, '3', '1', '140981', '14', '原平市', '山西省', '忻州市', '原平市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (336, '2', '1', '141000', '14', '临汾市', '山西省', '临汾市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (337, '3', '1', '141001', '14', '市辖区', '山西省', '临汾市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (338, '3', '1', '141002', '14', '尧都区', '山西省', '临汾市', '尧都区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (339, '3', '1', '141021', '14', '曲沃县', '山西省', '临汾市', '曲沃县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (340, '3', '1', '141022', '14', '翼城县', '山西省', '临汾市', '翼城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (341, '3', '1', '141023', '14', '襄汾县', '山西省', '临汾市', '襄汾县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (342, '3', '1', '141024', '14', '洪洞县', '山西省', '临汾市', '洪洞县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (343, '3', '1', '141025', '14', '古县', '山西省', '临汾市', '古县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (344, '3', '1', '141026', '14', '安泽县', '山西省', '临汾市', '安泽县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (345, '3', '1', '141027', '14', '浮山县', '山西省', '临汾市', '浮山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (346, '3', '1', '141028', '14', '吉县', '山西省', '临汾市', '吉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (347, '3', '1', '141029', '14', '乡宁县', '山西省', '临汾市', '乡宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (348, '3', '1', '141030', '14', '大宁县', '山西省', '临汾市', '大宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (349, '3', '1', '141031', '14', '隰县', '山西省', '临汾市', '隰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (350, '3', '1', '141032', '14', '永和县', '山西省', '临汾市', '永和县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (351, '3', '1', '141033', '14', '蒲县', '山西省', '临汾市', '蒲县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (352, '3', '1', '141034', '14', '汾西县', '山西省', '临汾市', '汾西县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (353, '3', '1', '141081', '14', '侯马市', '山西省', '临汾市', '侯马市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (354, '3', '1', '141082', '14', '霍州市', '山西省', '临汾市', '霍州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (355, '2', '1', '141100', '14', '吕梁市', '山西省', '吕梁市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (356, '3', '1', '141101', '14', '市辖区', '山西省', '吕梁市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (357, '3', '1', '141102', '14', '离石区', '山西省', '吕梁市', '离石区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (358, '3', '1', '141121', '14', '文水县', '山西省', '吕梁市', '文水县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (359, '3', '1', '141122', '14', '交城县', '山西省', '吕梁市', '交城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (360, '3', '1', '141123', '14', '兴县', '山西省', '吕梁市', '兴县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (361, '3', '1', '141124', '14', '临县', '山西省', '吕梁市', '临县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (362, '3', '1', '141125', '14', '柳林县', '山西省', '吕梁市', '柳林县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (363, '3', '1', '141126', '14', '石楼县', '山西省', '吕梁市', '石楼县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (364, '3', '1', '141127', '14', '岚县', '山西省', '吕梁市', '岚县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (365, '3', '1', '141128', '14', '方山县', '山西省', '吕梁市', '方山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (366, '3', '1', '141129', '14', '中阳县', '山西省', '吕梁市', '中阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (367, '3', '1', '141130', '14', '交口县', '山西省', '吕梁市', '交口县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (368, '3', '1', '141181', '14', '孝义市', '山西省', '吕梁市', '孝义市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (369, '3', '1', '141182', '14', '汾阳市', '山西省', '吕梁市', '汾阳市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (370, '1', '1', '150000', '15', '内蒙古自治区', '内蒙古自治区', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (371, '2', '1', '150100', '15', '呼和浩特市', '内蒙古自治区', '呼和浩特市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (372, '3', '1', '150101', '15', '市辖区', '内蒙古自治区', '呼和浩特市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (373, '3', '1', '150102', '15', '新城区', '内蒙古自治区', '呼和浩特市', '新城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (374, '3', '1', '150103', '15', '回民区', '内蒙古自治区', '呼和浩特市', '回民区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (375, '3', '1', '150104', '15', '玉泉区', '内蒙古自治区', '呼和浩特市', '玉泉区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (376, '3', '1', '150105', '15', '赛罕区', '内蒙古自治区', '呼和浩特市', '赛罕区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (377, '3', '1', '150121', '15', '土默特左旗', '内蒙古自治区', '呼和浩特市', '土默特左旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (378, '3', '1', '150122', '15', '托克托县', '内蒙古自治区', '呼和浩特市', '托克托县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (379, '3', '1', '150123', '15', '和林格尔县', '内蒙古自治区', '呼和浩特市', '和林格尔县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (380, '3', '1', '150124', '15', '清水河县', '内蒙古自治区', '呼和浩特市', '清水河县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (381, '3', '1', '150125', '15', '武川县', '内蒙古自治区', '呼和浩特市', '武川县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (382, '2', '1', '150200', '15', '包头市', '内蒙古自治区', '包头市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (383, '3', '1', '150201', '15', '市辖区', '内蒙古自治区', '包头市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (384, '3', '1', '150202', '15', '东河区', '内蒙古自治区', '包头市', '东河区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (385, '3', '1', '150203', '15', '昆都仑区', '内蒙古自治区', '包头市', '昆都仑区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (386, '3', '1', '150204', '15', '青山区', '内蒙古自治区', '包头市', '青山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (387, '3', '1', '150205', '15', '石拐区', '内蒙古自治区', '包头市', '石拐区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (388, '3', '1', '150206', '15', '白云鄂博矿区', '内蒙古自治区', '包头市', '白云鄂博矿区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (389, '3', '1', '150207', '15', '九原区', '内蒙古自治区', '包头市', '九原区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (390, '3', '1', '150221', '15', '土默特右旗', '内蒙古自治区', '包头市', '土默特右旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (391, '3', '1', '150222', '15', '固阳县', '内蒙古自治区', '包头市', '固阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (392, '3', '1', '150223', '15', '达尔罕茂明安联合旗', '内蒙古自治区', '包头市', '达尔罕茂明安联合旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (393, '2', '1', '150300', '15', '乌海市', '内蒙古自治区', '乌海市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (394, '3', '1', '150301', '15', '市辖区', '内蒙古自治区', '乌海市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (395, '3', '1', '150302', '15', '海勃湾区', '内蒙古自治区', '乌海市', '海勃湾区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (396, '3', '1', '150303', '15', '海南区', '内蒙古自治区', '乌海市', '海南区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (397, '3', '1', '150304', '15', '乌达区', '内蒙古自治区', '乌海市', '乌达区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (398, '2', '1', '150400', '15', '赤峰市', '内蒙古自治区', '赤峰市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (399, '3', '1', '150401', '15', '市辖区', '内蒙古自治区', '赤峰市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (400, '3', '1', '150402', '15', '红山区', '内蒙古自治区', '赤峰市', '红山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (401, '3', '1', '150403', '15', '元宝山区', '内蒙古自治区', '赤峰市', '元宝山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (402, '3', '1', '150404', '15', '松山区', '内蒙古自治区', '赤峰市', '松山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (403, '3', '1', '150421', '15', '阿鲁科尔沁旗', '内蒙古自治区', '赤峰市', '阿鲁科尔沁旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (404, '3', '1', '150422', '15', '巴林左旗', '内蒙古自治区', '赤峰市', '巴林左旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (405, '3', '1', '150423', '15', '巴林右旗', '内蒙古自治区', '赤峰市', '巴林右旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (406, '3', '1', '150424', '15', '林西县', '内蒙古自治区', '赤峰市', '林西县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (407, '3', '1', '150425', '15', '克什克腾旗', '内蒙古自治区', '赤峰市', '克什克腾旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (408, '3', '1', '150426', '15', '翁牛特旗', '内蒙古自治区', '赤峰市', '翁牛特旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (409, '3', '1', '150428', '15', '喀喇沁旗', '内蒙古自治区', '赤峰市', '喀喇沁旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (410, '3', '1', '150429', '15', '宁城县', '内蒙古自治区', '赤峰市', '宁城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (411, '3', '1', '150430', '15', '敖汉旗', '内蒙古自治区', '赤峰市', '敖汉旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (412, '2', '1', '150500', '15', '通辽市', '内蒙古自治区', '通辽市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (413, '3', '1', '150501', '15', '市辖区', '内蒙古自治区', '通辽市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (414, '3', '1', '150502', '15', '科尔沁区', '内蒙古自治区', '通辽市', '科尔沁区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (415, '3', '1', '150521', '15', '科尔沁左翼中旗', '内蒙古自治区', '通辽市', '科尔沁左翼中旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (416, '3', '1', '150522', '15', '科尔沁左翼后旗', '内蒙古自治区', '通辽市', '科尔沁左翼后旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (417, '3', '1', '150523', '15', '开鲁县', '内蒙古自治区', '通辽市', '开鲁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (418, '3', '1', '150524', '15', '库伦旗', '内蒙古自治区', '通辽市', '库伦旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (419, '3', '1', '150525', '15', '奈曼旗', '内蒙古自治区', '通辽市', '奈曼旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (420, '3', '1', '150526', '15', '扎鲁特旗', '内蒙古自治区', '通辽市', '扎鲁特旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (421, '3', '1', '150581', '15', '霍林郭勒市', '内蒙古自治区', '通辽市', '霍林郭勒市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (422, '2', '1', '150600', '15', '鄂尔多斯市', '内蒙古自治区', '鄂尔多斯市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (423, '3', '1', '150601', '15', '市辖区', '内蒙古自治区', '鄂尔多斯市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (424, '3', '1', '150602', '15', '东胜区', '内蒙古自治区', '鄂尔多斯市', '东胜区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (425, '3', '1', '150621', '15', '达拉特旗', '内蒙古自治区', '鄂尔多斯市', '达拉特旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (426, '3', '1', '150622', '15', '准格尔旗', '内蒙古自治区', '鄂尔多斯市', '准格尔旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (427, '3', '1', '150623', '15', '鄂托克前旗', '内蒙古自治区', '鄂尔多斯市', '鄂托克前旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (428, '3', '1', '150624', '15', '鄂托克旗', '内蒙古自治区', '鄂尔多斯市', '鄂托克旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (429, '3', '1', '150625', '15', '杭锦旗', '内蒙古自治区', '鄂尔多斯市', '杭锦旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (430, '3', '1', '150626', '15', '乌审旗', '内蒙古自治区', '鄂尔多斯市', '乌审旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (431, '3', '1', '150627', '15', '伊金霍洛旗', '内蒙古自治区', '鄂尔多斯市', '伊金霍洛旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (432, '2', '1', '150700', '15', '呼伦贝尔市', '内蒙古自治区', '呼伦贝尔市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (433, '3', '1', '150701', '15', '市辖区', '内蒙古自治区', '呼伦贝尔市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (434, '3', '1', '150702', '15', '海拉尔区', '内蒙古自治区', '呼伦贝尔市', '海拉尔区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (435, '3', '1', '150703', '15', '扎赉诺尔区', '内蒙古自治区', '呼伦贝尔市', '扎赉诺尔区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (436, '3', '1', '150721', '15', '阿荣旗', '内蒙古自治区', '呼伦贝尔市', '阿荣旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (437, '3', '1', '150722', '15', '莫力达瓦达斡尔族自治旗', '内蒙古自治区', '呼伦贝尔市', '莫力达瓦达斡尔族自治旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (438, '3', '1', '150723', '15', '鄂伦春自治旗', '内蒙古自治区', '呼伦贝尔市', '鄂伦春自治旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (439, '3', '1', '150724', '15', '鄂温克族自治旗', '内蒙古自治区', '呼伦贝尔市', '鄂温克族自治旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (440, '3', '1', '150725', '15', '陈巴尔虎旗', '内蒙古自治区', '呼伦贝尔市', '陈巴尔虎旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (441, '3', '1', '150726', '15', '新巴尔虎左旗', '内蒙古自治区', '呼伦贝尔市', '新巴尔虎左旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (442, '3', '1', '150727', '15', '新巴尔虎右旗', '内蒙古自治区', '呼伦贝尔市', '新巴尔虎右旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (443, '3', '1', '150781', '15', '满洲里市', '内蒙古自治区', '呼伦贝尔市', '满洲里市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (444, '3', '1', '150782', '15', '牙克石市', '内蒙古自治区', '呼伦贝尔市', '牙克石市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (445, '3', '1', '150783', '15', '扎兰屯市', '内蒙古自治区', '呼伦贝尔市', '扎兰屯市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (446, '3', '1', '150784', '15', '额尔古纳市', '内蒙古自治区', '呼伦贝尔市', '额尔古纳市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (447, '3', '1', '150785', '15', '根河市', '内蒙古自治区', '呼伦贝尔市', '根河市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (448, '2', '1', '150800', '15', '巴彦淖尔市', '内蒙古自治区', '巴彦淖尔市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (449, '3', '1', '150801', '15', '市辖区', '内蒙古自治区', '巴彦淖尔市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (450, '3', '1', '150802', '15', '临河区', '内蒙古自治区', '巴彦淖尔市', '临河区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (451, '3', '1', '150821', '15', '五原县', '内蒙古自治区', '巴彦淖尔市', '五原县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (452, '3', '1', '150822', '15', '磴口县', '内蒙古自治区', '巴彦淖尔市', '磴口县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (453, '3', '1', '150823', '15', '乌拉特前旗', '内蒙古自治区', '巴彦淖尔市', '乌拉特前旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (454, '3', '1', '150824', '15', '乌拉特中旗', '内蒙古自治区', '巴彦淖尔市', '乌拉特中旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (455, '3', '1', '150825', '15', '乌拉特后旗', '内蒙古自治区', '巴彦淖尔市', '乌拉特后旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (456, '3', '1', '150826', '15', '杭锦后旗', '内蒙古自治区', '巴彦淖尔市', '杭锦后旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (457, '2', '1', '150900', '15', '乌兰察布市', '内蒙古自治区', '乌兰察布市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (458, '3', '1', '150901', '15', '市辖区', '内蒙古自治区', '乌兰察布市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (459, '3', '1', '150902', '15', '集宁区', '内蒙古自治区', '乌兰察布市', '集宁区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (460, '3', '1', '150921', '15', '卓资县', '内蒙古自治区', '乌兰察布市', '卓资县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (461, '3', '1', '150922', '15', '化德县', '内蒙古自治区', '乌兰察布市', '化德县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (462, '3', '1', '150923', '15', '商都县', '内蒙古自治区', '乌兰察布市', '商都县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (463, '3', '1', '150924', '15', '兴和县', '内蒙古自治区', '乌兰察布市', '兴和县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (464, '3', '1', '150925', '15', '凉城县', '内蒙古自治区', '乌兰察布市', '凉城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (465, '3', '1', '150926', '15', '察哈尔右翼前旗', '内蒙古自治区', '乌兰察布市', '察哈尔右翼前旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (466, '3', '1', '150927', '15', '察哈尔右翼中旗', '内蒙古自治区', '乌兰察布市', '察哈尔右翼中旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (467, '3', '1', '150928', '15', '察哈尔右翼后旗', '内蒙古自治区', '乌兰察布市', '察哈尔右翼后旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (468, '3', '1', '150929', '15', '四子王旗', '内蒙古自治区', '乌兰察布市', '四子王旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (469, '3', '1', '150981', '15', '丰镇市', '内蒙古自治区', '乌兰察布市', '丰镇市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (470, '2', '1', '152200', '15', '兴安盟', '内蒙古自治区', '兴安盟', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (471, '3', '1', '152201', '15', '乌兰浩特市', '内蒙古自治区', '兴安盟', '乌兰浩特市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (472, '3', '1', '152202', '15', '阿尔山市', '内蒙古自治区', '兴安盟', '阿尔山市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (473, '3', '1', '152221', '15', '科尔沁右翼前旗', '内蒙古自治区', '兴安盟', '科尔沁右翼前旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (474, '3', '1', '152222', '15', '科尔沁右翼中旗', '内蒙古自治区', '兴安盟', '科尔沁右翼中旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (475, '3', '1', '152223', '15', '扎赉特旗', '内蒙古自治区', '兴安盟', '扎赉特旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (476, '3', '1', '152224', '15', '突泉县', '内蒙古自治区', '兴安盟', '突泉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (477, '2', '1', '152500', '15', '锡林郭勒盟', '内蒙古自治区', '锡林郭勒盟', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (478, '3', '1', '152501', '15', '二连浩特市', '内蒙古自治区', '锡林郭勒盟', '二连浩特市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (479, '3', '1', '152502', '15', '锡林浩特市', '内蒙古自治区', '锡林郭勒盟', '锡林浩特市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (480, '3', '1', '152522', '15', '阿巴嘎旗', '内蒙古自治区', '锡林郭勒盟', '阿巴嘎旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (481, '3', '1', '152523', '15', '苏尼特左旗', '内蒙古自治区', '锡林郭勒盟', '苏尼特左旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (482, '3', '1', '152524', '15', '苏尼特右旗', '内蒙古自治区', '锡林郭勒盟', '苏尼特右旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (483, '3', '1', '152525', '15', '东乌珠穆沁旗', '内蒙古自治区', '锡林郭勒盟', '东乌珠穆沁旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (484, '3', '1', '152526', '15', '西乌珠穆沁旗', '内蒙古自治区', '锡林郭勒盟', '西乌珠穆沁旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (485, '3', '1', '152527', '15', '太仆寺旗', '内蒙古自治区', '锡林郭勒盟', '太仆寺旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (486, '3', '1', '152528', '15', '镶黄旗', '内蒙古自治区', '锡林郭勒盟', '镶黄旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (487, '3', '1', '152529', '15', '正镶白旗', '内蒙古自治区', '锡林郭勒盟', '正镶白旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (488, '3', '1', '152530', '15', '正蓝旗', '内蒙古自治区', '锡林郭勒盟', '正蓝旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (489, '3', '1', '152531', '15', '多伦县', '内蒙古自治区', '锡林郭勒盟', '多伦县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (490, '2', '1', '152900', '15', '阿拉善盟', '内蒙古自治区', '阿拉善盟', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (491, '3', '1', '152921', '15', '阿拉善左旗', '内蒙古自治区', '阿拉善盟', '阿拉善左旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (492, '3', '1', '152922', '15', '阿拉善右旗', '内蒙古自治区', '阿拉善盟', '阿拉善右旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (493, '3', '1', '152923', '15', '额济纳旗', '内蒙古自治区', '阿拉善盟', '额济纳旗', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (494, '1', '2', '210000', '21', '辽宁省', '辽宁省', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (495, '2', '2', '210100', '21', '沈阳市', '辽宁省', '沈阳市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (496, '3', '2', '210101', '21', '市辖区', '辽宁省', '沈阳市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (497, '3', '2', '210102', '21', '和平区', '辽宁省', '沈阳市', '和平区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (498, '3', '2', '210103', '21', '沈河区', '辽宁省', '沈阳市', '沈河区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (499, '3', '2', '210104', '21', '大东区', '辽宁省', '沈阳市', '大东区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (500, '3', '2', '210105', '21', '皇姑区', '辽宁省', '沈阳市', '皇姑区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (501, '3', '2', '210106', '21', '铁西区', '辽宁省', '沈阳市', '铁西区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (502, '3', '2', '210111', '21', '苏家屯区', '辽宁省', '沈阳市', '苏家屯区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (503, '3', '2', '210112', '21', '浑南区', '辽宁省', '沈阳市', '浑南区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (504, '3', '2', '210113', '21', '沈北新区', '辽宁省', '沈阳市', '沈北新区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (505, '3', '2', '210114', '21', '于洪区', '辽宁省', '沈阳市', '于洪区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (506, '3', '2', '210122', '21', '辽中县', '辽宁省', '沈阳市', '辽中县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (507, '3', '2', '210123', '21', '康平县', '辽宁省', '沈阳市', '康平县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (508, '3', '2', '210124', '21', '法库县', '辽宁省', '沈阳市', '法库县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (509, '3', '2', '210181', '21', '新民市', '辽宁省', '沈阳市', '新民市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (510, '2', '2', '210200', '21', '大连市', '辽宁省', '大连市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (511, '3', '2', '210201', '21', '市辖区', '辽宁省', '大连市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (512, '3', '2', '210202', '21', '中山区', '辽宁省', '大连市', '中山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (513, '3', '2', '210203', '21', '西岗区', '辽宁省', '大连市', '西岗区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (514, '3', '2', '210204', '21', '沙河口区', '辽宁省', '大连市', '沙河口区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (515, '3', '2', '210211', '21', '甘井子区', '辽宁省', '大连市', '甘井子区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (516, '3', '2', '210212', '21', '旅顺口区', '辽宁省', '大连市', '旅顺口区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (517, '3', '2', '210213', '21', '金州区', '辽宁省', '大连市', '金州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (518, '3', '2', '210224', '21', '长海县', '辽宁省', '大连市', '长海县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (519, '3', '2', '210281', '21', '瓦房店市', '辽宁省', '大连市', '瓦房店市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (520, '3', '2', '210282', '21', '普兰店市', '辽宁省', '大连市', '普兰店市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (521, '3', '2', '210283', '21', '庄河市', '辽宁省', '大连市', '庄河市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (522, '2', '2', '210300', '21', '鞍山市', '辽宁省', '鞍山市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (523, '3', '2', '210301', '21', '市辖区', '辽宁省', '鞍山市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (524, '3', '2', '210302', '21', '铁东区', '辽宁省', '鞍山市', '铁东区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (525, '3', '2', '210303', '21', '铁西区', '辽宁省', '鞍山市', '铁西区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (526, '3', '2', '210304', '21', '立山区', '辽宁省', '鞍山市', '立山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (527, '3', '2', '210311', '21', '千山区', '辽宁省', '鞍山市', '千山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (528, '3', '2', '210321', '21', '台安县', '辽宁省', '鞍山市', '台安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (529, '3', '2', '210323', '21', '岫岩满族自治县', '辽宁省', '鞍山市', '岫岩满族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (530, '3', '2', '210381', '21', '海城市', '辽宁省', '鞍山市', '海城市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (531, '2', '2', '210400', '21', '抚顺市', '辽宁省', '抚顺市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (532, '3', '2', '210401', '21', '市辖区', '辽宁省', '抚顺市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (533, '3', '2', '210402', '21', '新抚区', '辽宁省', '抚顺市', '新抚区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (534, '3', '2', '210403', '21', '东洲区', '辽宁省', '抚顺市', '东洲区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (535, '3', '2', '210404', '21', '望花区', '辽宁省', '抚顺市', '望花区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (536, '3', '2', '210411', '21', '顺城区', '辽宁省', '抚顺市', '顺城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (537, '3', '2', '210421', '21', '抚顺县', '辽宁省', '抚顺市', '抚顺县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (538, '3', '2', '210422', '21', '新宾满族自治县', '辽宁省', '抚顺市', '新宾满族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (539, '3', '2', '210423', '21', '清原满族自治县', '辽宁省', '抚顺市', '清原满族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (540, '2', '2', '210500', '21', '本溪市', '辽宁省', '本溪市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (541, '3', '2', '210501', '21', '市辖区', '辽宁省', '本溪市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (542, '3', '2', '210502', '21', '平山区', '辽宁省', '本溪市', '平山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (543, '3', '2', '210503', '21', '溪湖区', '辽宁省', '本溪市', '溪湖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (544, '3', '2', '210504', '21', '明山区', '辽宁省', '本溪市', '明山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (545, '3', '2', '210505', '21', '南芬区', '辽宁省', '本溪市', '南芬区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (546, '3', '2', '210521', '21', '本溪满族自治县', '辽宁省', '本溪市', '本溪满族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (547, '3', '2', '210522', '21', '桓仁满族自治县', '辽宁省', '本溪市', '桓仁满族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (548, '2', '2', '210600', '21', '丹东市', '辽宁省', '丹东市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (549, '3', '2', '210601', '21', '市辖区', '辽宁省', '丹东市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (550, '3', '2', '210602', '21', '元宝区', '辽宁省', '丹东市', '元宝区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (551, '3', '2', '210603', '21', '振兴区', '辽宁省', '丹东市', '振兴区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (552, '3', '2', '210604', '21', '振安区', '辽宁省', '丹东市', '振安区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (553, '3', '2', '210624', '21', '宽甸满族自治县', '辽宁省', '丹东市', '宽甸满族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (554, '3', '2', '210681', '21', '东港市', '辽宁省', '丹东市', '东港市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (555, '3', '2', '210682', '21', '凤城市', '辽宁省', '丹东市', '凤城市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (556, '2', '2', '210700', '21', '锦州市', '辽宁省', '锦州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (557, '3', '2', '210701', '21', '市辖区', '辽宁省', '锦州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (558, '3', '2', '210702', '21', '古塔区', '辽宁省', '锦州市', '古塔区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (559, '3', '2', '210703', '21', '凌河区', '辽宁省', '锦州市', '凌河区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (560, '3', '2', '210711', '21', '太和区', '辽宁省', '锦州市', '太和区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (561, '3', '2', '210726', '21', '黑山县', '辽宁省', '锦州市', '黑山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (562, '3', '2', '210727', '21', '义县', '辽宁省', '锦州市', '义县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (563, '3', '2', '210781', '21', '凌海市', '辽宁省', '锦州市', '凌海市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (564, '3', '2', '210782', '21', '北镇市', '辽宁省', '锦州市', '北镇市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (565, '2', '2', '210800', '21', '营口市', '辽宁省', '营口市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (566, '3', '2', '210801', '21', '市辖区', '辽宁省', '营口市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (567, '3', '2', '210802', '21', '站前区', '辽宁省', '营口市', '站前区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (568, '3', '2', '210803', '21', '西市区', '辽宁省', '营口市', '西市区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (569, '3', '2', '210804', '21', '鲅鱼圈区', '辽宁省', '营口市', '鲅鱼圈区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (570, '3', '2', '210811', '21', '老边区', '辽宁省', '营口市', '老边区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (571, '3', '2', '210881', '21', '盖州市', '辽宁省', '营口市', '盖州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (572, '3', '2', '210882', '21', '大石桥市', '辽宁省', '营口市', '大石桥市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (573, '2', '2', '210900', '21', '阜新市', '辽宁省', '阜新市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (574, '3', '2', '210901', '21', '市辖区', '辽宁省', '阜新市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (575, '3', '2', '210902', '21', '海州区', '辽宁省', '阜新市', '海州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (576, '3', '2', '210903', '21', '新邱区', '辽宁省', '阜新市', '新邱区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (577, '3', '2', '210904', '21', '太平区', '辽宁省', '阜新市', '太平区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (578, '3', '2', '210905', '21', '清河门区', '辽宁省', '阜新市', '清河门区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (579, '3', '2', '210911', '21', '细河区', '辽宁省', '阜新市', '细河区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (580, '3', '2', '210921', '21', '阜新蒙古族自治县', '辽宁省', '阜新市', '阜新蒙古族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (581, '3', '2', '210922', '21', '彰武县', '辽宁省', '阜新市', '彰武县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (582, '2', '2', '211000', '21', '辽阳市', '辽宁省', '辽阳市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (583, '3', '2', '211001', '21', '市辖区', '辽宁省', '辽阳市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (584, '3', '2', '211002', '21', '白塔区', '辽宁省', '辽阳市', '白塔区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (585, '3', '2', '211003', '21', '文圣区', '辽宁省', '辽阳市', '文圣区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (586, '3', '2', '211004', '21', '宏伟区', '辽宁省', '辽阳市', '宏伟区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (587, '3', '2', '211005', '21', '弓长岭区', '辽宁省', '辽阳市', '弓长岭区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (588, '3', '2', '211011', '21', '太子河区', '辽宁省', '辽阳市', '太子河区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (589, '3', '2', '211021', '21', '辽阳县', '辽宁省', '辽阳市', '辽阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (590, '3', '2', '211081', '21', '灯塔市', '辽宁省', '辽阳市', '灯塔市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (591, '2', '2', '211100', '21', '盘锦市', '辽宁省', '盘锦市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (592, '3', '2', '211101', '21', '市辖区', '辽宁省', '盘锦市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (593, '3', '2', '211102', '21', '双台子区', '辽宁省', '盘锦市', '双台子区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (594, '3', '2', '211103', '21', '兴隆台区', '辽宁省', '盘锦市', '兴隆台区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (595, '3', '2', '211121', '21', '大洼县', '辽宁省', '盘锦市', '大洼县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (596, '3', '2', '211122', '21', '盘山县', '辽宁省', '盘锦市', '盘山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (597, '2', '2', '211200', '21', '铁岭市', '辽宁省', '铁岭市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (598, '3', '2', '211201', '21', '市辖区', '辽宁省', '铁岭市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (599, '3', '2', '211202', '21', '银州区', '辽宁省', '铁岭市', '银州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (600, '3', '2', '211204', '21', '清河区', '辽宁省', '铁岭市', '清河区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (601, '3', '2', '211221', '21', '铁岭县', '辽宁省', '铁岭市', '铁岭县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (602, '3', '2', '211223', '21', '西丰县', '辽宁省', '铁岭市', '西丰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (603, '3', '2', '211224', '21', '昌图县', '辽宁省', '铁岭市', '昌图县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (604, '3', '2', '211281', '21', '调兵山市', '辽宁省', '铁岭市', '调兵山市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (605, '3', '2', '211282', '21', '开原市', '辽宁省', '铁岭市', '开原市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (606, '2', '2', '211300', '21', '朝阳市', '辽宁省', '朝阳市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (607, '3', '2', '211301', '21', '市辖区', '辽宁省', '朝阳市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (608, '3', '2', '211302', '21', '双塔区', '辽宁省', '朝阳市', '双塔区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (609, '3', '2', '211303', '21', '龙城区', '辽宁省', '朝阳市', '龙城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (610, '3', '2', '211321', '21', '朝阳县', '辽宁省', '朝阳市', '朝阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (611, '3', '2', '211322', '21', '建平县', '辽宁省', '朝阳市', '建平县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (612, '3', '2', '211324', '21', '喀喇沁左翼蒙古族自治县', '辽宁省', '朝阳市', '喀喇沁左翼蒙古族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (613, '3', '2', '211381', '21', '北票市', '辽宁省', '朝阳市', '北票市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (614, '3', '2', '211382', '21', '凌源市', '辽宁省', '朝阳市', '凌源市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (615, '2', '2', '211400', '21', '葫芦岛市', '辽宁省', '葫芦岛市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (616, '3', '2', '211401', '21', '市辖区', '辽宁省', '葫芦岛市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (617, '3', '2', '211402', '21', '连山区', '辽宁省', '葫芦岛市', '连山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (618, '3', '2', '211403', '21', '龙港区', '辽宁省', '葫芦岛市', '龙港区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (619, '3', '2', '211404', '21', '南票区', '辽宁省', '葫芦岛市', '南票区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (620, '3', '2', '211421', '21', '绥中县', '辽宁省', '葫芦岛市', '绥中县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (621, '3', '2', '211422', '21', '建昌县', '辽宁省', '葫芦岛市', '建昌县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (622, '3', '2', '211481', '21', '兴城市', '辽宁省', '葫芦岛市', '兴城市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (623, '1', '2', '220000', '22', '吉林省', '吉林省', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (624, '2', '2', '220100', '22', '长春市', '吉林省', '长春市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (625, '3', '2', '220101', '22', '市辖区', '吉林省', '长春市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (626, '3', '2', '220102', '22', '南关区', '吉林省', '长春市', '南关区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (627, '3', '2', '220103', '22', '宽城区', '吉林省', '长春市', '宽城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (628, '3', '2', '220104', '22', '朝阳区', '吉林省', '长春市', '朝阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (629, '3', '2', '220105', '22', '二道区', '吉林省', '长春市', '二道区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (630, '3', '2', '220106', '22', '绿园区', '吉林省', '长春市', '绿园区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (631, '3', '2', '220112', '22', '双阳区', '吉林省', '长春市', '双阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (632, '3', '2', '220113', '22', '九台区', '吉林省', '长春市', '九台区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (633, '3', '2', '220122', '22', '农安县', '吉林省', '长春市', '农安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (634, '3', '2', '220182', '22', '榆树市', '吉林省', '长春市', '榆树市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (635, '3', '2', '220183', '22', '德惠市', '吉林省', '长春市', '德惠市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (636, '2', '2', '220200', '22', '吉林市', '吉林省', '吉林市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (637, '3', '2', '220201', '22', '市辖区', '吉林省', '吉林市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (638, '3', '2', '220202', '22', '昌邑区', '吉林省', '吉林市', '昌邑区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (639, '3', '2', '220203', '22', '龙潭区', '吉林省', '吉林市', '龙潭区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (640, '3', '2', '220204', '22', '船营区', '吉林省', '吉林市', '船营区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (641, '3', '2', '220211', '22', '丰满区', '吉林省', '吉林市', '丰满区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (642, '3', '2', '220221', '22', '永吉县', '吉林省', '吉林市', '永吉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (643, '3', '2', '220281', '22', '蛟河市', '吉林省', '吉林市', '蛟河市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (644, '3', '2', '220282', '22', '桦甸市', '吉林省', '吉林市', '桦甸市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (645, '3', '2', '220283', '22', '舒兰市', '吉林省', '吉林市', '舒兰市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (646, '3', '2', '220284', '22', '磐石市', '吉林省', '吉林市', '磐石市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (647, '2', '2', '220300', '22', '四平市', '吉林省', '四平市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (648, '3', '2', '220301', '22', '市辖区', '吉林省', '四平市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (649, '3', '2', '220302', '22', '铁西区', '吉林省', '四平市', '铁西区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (650, '3', '2', '220303', '22', '铁东区', '吉林省', '四平市', '铁东区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (651, '3', '2', '220322', '22', '梨树县', '吉林省', '四平市', '梨树县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (652, '3', '2', '220323', '22', '伊通满族自治县', '吉林省', '四平市', '伊通满族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (653, '3', '2', '220381', '22', '公主岭市', '吉林省', '四平市', '公主岭市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (654, '3', '2', '220382', '22', '双辽市', '吉林省', '四平市', '双辽市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (655, '2', '2', '220400', '22', '辽源市', '吉林省', '辽源市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (656, '3', '2', '220401', '22', '市辖区', '吉林省', '辽源市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (657, '3', '2', '220402', '22', '龙山区', '吉林省', '辽源市', '龙山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (658, '3', '2', '220403', '22', '西安区', '吉林省', '辽源市', '西安区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (659, '3', '2', '220421', '22', '东丰县', '吉林省', '辽源市', '东丰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (660, '3', '2', '220422', '22', '东辽县', '吉林省', '辽源市', '东辽县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (661, '2', '2', '220500', '22', '通化市', '吉林省', '通化市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (662, '3', '2', '220501', '22', '市辖区', '吉林省', '通化市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (663, '3', '2', '220502', '22', '东昌区', '吉林省', '通化市', '东昌区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (664, '3', '2', '220503', '22', '二道江区', '吉林省', '通化市', '二道江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (665, '3', '2', '220521', '22', '通化县', '吉林省', '通化市', '通化县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (666, '3', '2', '220523', '22', '辉南县', '吉林省', '通化市', '辉南县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (667, '3', '2', '220524', '22', '柳河县', '吉林省', '通化市', '柳河县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (668, '3', '2', '220581', '22', '梅河口市', '吉林省', '通化市', '梅河口市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (669, '3', '2', '220582', '22', '集安市', '吉林省', '通化市', '集安市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (670, '2', '2', '220600', '22', '白山市', '吉林省', '白山市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (671, '3', '2', '220601', '22', '市辖区', '吉林省', '白山市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (672, '3', '2', '220602', '22', '浑江区', '吉林省', '白山市', '浑江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (673, '3', '2', '220605', '22', '江源区', '吉林省', '白山市', '江源区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (674, '3', '2', '220621', '22', '抚松县', '吉林省', '白山市', '抚松县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (675, '3', '2', '220622', '22', '靖宇县', '吉林省', '白山市', '靖宇县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (676, '3', '2', '220623', '22', '长白朝鲜族自治县', '吉林省', '白山市', '长白朝鲜族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (677, '3', '2', '220681', '22', '临江市', '吉林省', '白山市', '临江市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (678, '2', '2', '220700', '22', '松原市', '吉林省', '松原市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (679, '3', '2', '220701', '22', '市辖区', '吉林省', '松原市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (680, '3', '2', '220702', '22', '宁江区', '吉林省', '松原市', '宁江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (681, '3', '2', '220721', '22', '前郭尔罗斯蒙古族自治县', '吉林省', '松原市', '前郭尔罗斯蒙古族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (682, '3', '2', '220722', '22', '长岭县', '吉林省', '松原市', '长岭县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (683, '3', '2', '220723', '22', '乾安县', '吉林省', '松原市', '乾安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (684, '3', '2', '220781', '22', '扶余市', '吉林省', '松原市', '扶余市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (685, '2', '2', '220800', '22', '白城市', '吉林省', '白城市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (686, '3', '2', '220801', '22', '市辖区', '吉林省', '白城市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (687, '3', '2', '220802', '22', '洮北区', '吉林省', '白城市', '洮北区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (688, '3', '2', '220821', '22', '镇赉县', '吉林省', '白城市', '镇赉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (689, '3', '2', '220822', '22', '通榆县', '吉林省', '白城市', '通榆县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (690, '3', '2', '220881', '22', '洮南市', '吉林省', '白城市', '洮南市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (691, '3', '2', '220882', '22', '大安市', '吉林省', '白城市', '大安市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (692, '2', '2', '222400', '22', '延边朝鲜族自治州', '吉林省', '延边朝鲜族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (693, '3', '2', '222401', '22', '延吉市', '吉林省', '延边朝鲜族自治州', '延吉市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (694, '3', '2', '222402', '22', '图们市', '吉林省', '延边朝鲜族自治州', '图们市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (695, '3', '2', '222403', '22', '敦化市', '吉林省', '延边朝鲜族自治州', '敦化市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (696, '3', '2', '222404', '22', '珲春市', '吉林省', '延边朝鲜族自治州', '珲春市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (697, '3', '2', '222405', '22', '龙井市', '吉林省', '延边朝鲜族自治州', '龙井市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (698, '3', '2', '222406', '22', '和龙市', '吉林省', '延边朝鲜族自治州', '和龙市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (699, '3', '2', '222424', '22', '汪清县', '吉林省', '延边朝鲜族自治州', '汪清县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (700, '3', '2', '222426', '22', '安图县', '吉林省', '延边朝鲜族自治州', '安图县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (701, '1', '2', '230000', '23', '黑龙江省', '黑龙江省', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (702, '2', '2', '230100', '23', '哈尔滨市', '黑龙江省', '哈尔滨市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (703, '3', '2', '230101', '23', '市辖区', '黑龙江省', '哈尔滨市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (704, '3', '2', '230102', '23', '道里区', '黑龙江省', '哈尔滨市', '道里区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (705, '3', '2', '230103', '23', '南岗区', '黑龙江省', '哈尔滨市', '南岗区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (706, '3', '2', '230104', '23', '道外区', '黑龙江省', '哈尔滨市', '道外区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (707, '3', '2', '230108', '23', '平房区', '黑龙江省', '哈尔滨市', '平房区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (708, '3', '2', '230109', '23', '松北区', '黑龙江省', '哈尔滨市', '松北区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (709, '3', '2', '230110', '23', '香坊区', '黑龙江省', '哈尔滨市', '香坊区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (710, '3', '2', '230111', '23', '呼兰区', '黑龙江省', '哈尔滨市', '呼兰区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (711, '3', '2', '230112', '23', '阿城区', '黑龙江省', '哈尔滨市', '阿城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (712, '3', '2', '230113', '23', '双城区', '黑龙江省', '哈尔滨市', '双城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (713, '3', '2', '230123', '23', '依兰县', '黑龙江省', '哈尔滨市', '依兰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (714, '3', '2', '230124', '23', '方正县', '黑龙江省', '哈尔滨市', '方正县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (715, '3', '2', '230125', '23', '宾县', '黑龙江省', '哈尔滨市', '宾县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (716, '3', '2', '230126', '23', '巴彦县', '黑龙江省', '哈尔滨市', '巴彦县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (717, '3', '2', '230127', '23', '木兰县', '黑龙江省', '哈尔滨市', '木兰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (718, '3', '2', '230128', '23', '通河县', '黑龙江省', '哈尔滨市', '通河县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (719, '3', '2', '230129', '23', '延寿县', '黑龙江省', '哈尔滨市', '延寿县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (720, '3', '2', '230183', '23', '尚志市', '黑龙江省', '哈尔滨市', '尚志市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (721, '3', '2', '230184', '23', '五常市', '黑龙江省', '哈尔滨市', '五常市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (722, '2', '2', '230200', '23', '齐齐哈尔市', '黑龙江省', '齐齐哈尔市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (723, '3', '2', '230201', '23', '市辖区', '黑龙江省', '齐齐哈尔市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (724, '3', '2', '230202', '23', '龙沙区', '黑龙江省', '齐齐哈尔市', '龙沙区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (725, '3', '2', '230203', '23', '建华区', '黑龙江省', '齐齐哈尔市', '建华区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (726, '3', '2', '230204', '23', '铁锋区', '黑龙江省', '齐齐哈尔市', '铁锋区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (727, '3', '2', '230205', '23', '昂昂溪区', '黑龙江省', '齐齐哈尔市', '昂昂溪区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (728, '3', '2', '230206', '23', '富拉尔基区', '黑龙江省', '齐齐哈尔市', '富拉尔基区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (729, '3', '2', '230207', '23', '碾子山区', '黑龙江省', '齐齐哈尔市', '碾子山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (730, '3', '2', '230208', '23', '梅里斯达斡尔族区', '黑龙江省', '齐齐哈尔市', '梅里斯达斡尔族区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (731, '3', '2', '230221', '23', '龙江县', '黑龙江省', '齐齐哈尔市', '龙江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (732, '3', '2', '230223', '23', '依安县', '黑龙江省', '齐齐哈尔市', '依安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (733, '3', '2', '230224', '23', '泰来县', '黑龙江省', '齐齐哈尔市', '泰来县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (734, '3', '2', '230225', '23', '甘南县', '黑龙江省', '齐齐哈尔市', '甘南县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (735, '3', '2', '230227', '23', '富裕县', '黑龙江省', '齐齐哈尔市', '富裕县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (736, '3', '2', '230229', '23', '克山县', '黑龙江省', '齐齐哈尔市', '克山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (737, '3', '2', '230230', '23', '克东县', '黑龙江省', '齐齐哈尔市', '克东县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (738, '3', '2', '230231', '23', '拜泉县', '黑龙江省', '齐齐哈尔市', '拜泉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (739, '3', '2', '230281', '23', '讷河市', '黑龙江省', '齐齐哈尔市', '讷河市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (740, '2', '2', '230300', '23', '鸡西市', '黑龙江省', '鸡西市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (741, '3', '2', '230301', '23', '市辖区', '黑龙江省', '鸡西市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (742, '3', '2', '230302', '23', '鸡冠区', '黑龙江省', '鸡西市', '鸡冠区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (743, '3', '2', '230303', '23', '恒山区', '黑龙江省', '鸡西市', '恒山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (744, '3', '2', '230304', '23', '滴道区', '黑龙江省', '鸡西市', '滴道区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (745, '3', '2', '230305', '23', '梨树区', '黑龙江省', '鸡西市', '梨树区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (746, '3', '2', '230306', '23', '城子河区', '黑龙江省', '鸡西市', '城子河区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (747, '3', '2', '230307', '23', '麻山区', '黑龙江省', '鸡西市', '麻山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (748, '3', '2', '230321', '23', '鸡东县', '黑龙江省', '鸡西市', '鸡东县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (749, '3', '2', '230381', '23', '虎林市', '黑龙江省', '鸡西市', '虎林市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (750, '3', '2', '230382', '23', '密山市', '黑龙江省', '鸡西市', '密山市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (751, '2', '2', '230400', '23', '鹤岗市', '黑龙江省', '鹤岗市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (752, '3', '2', '230401', '23', '市辖区', '黑龙江省', '鹤岗市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (753, '3', '2', '230402', '23', '向阳区', '黑龙江省', '鹤岗市', '向阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (754, '3', '2', '230403', '23', '工农区', '黑龙江省', '鹤岗市', '工农区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (755, '3', '2', '230404', '23', '南山区', '黑龙江省', '鹤岗市', '南山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (756, '3', '2', '230405', '23', '兴安区', '黑龙江省', '鹤岗市', '兴安区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (757, '3', '2', '230406', '23', '东山区', '黑龙江省', '鹤岗市', '东山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (758, '3', '2', '230407', '23', '兴山区', '黑龙江省', '鹤岗市', '兴山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (759, '3', '2', '230421', '23', '萝北县', '黑龙江省', '鹤岗市', '萝北县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (760, '3', '2', '230422', '23', '绥滨县', '黑龙江省', '鹤岗市', '绥滨县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (761, '2', '2', '230500', '23', '双鸭山市', '黑龙江省', '双鸭山市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (762, '3', '2', '230501', '23', '市辖区', '黑龙江省', '双鸭山市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (763, '3', '2', '230502', '23', '尖山区', '黑龙江省', '双鸭山市', '尖山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (764, '3', '2', '230503', '23', '岭东区', '黑龙江省', '双鸭山市', '岭东区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (765, '3', '2', '230505', '23', '四方台区', '黑龙江省', '双鸭山市', '四方台区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (766, '3', '2', '230506', '23', '宝山区', '黑龙江省', '双鸭山市', '宝山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (767, '3', '2', '230521', '23', '集贤县', '黑龙江省', '双鸭山市', '集贤县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (768, '3', '2', '230522', '23', '友谊县', '黑龙江省', '双鸭山市', '友谊县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (769, '3', '2', '230523', '23', '宝清县', '黑龙江省', '双鸭山市', '宝清县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (770, '3', '2', '230524', '23', '饶河县', '黑龙江省', '双鸭山市', '饶河县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (771, '2', '2', '230600', '23', '大庆市', '黑龙江省', '大庆市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (772, '3', '2', '230601', '23', '市辖区', '黑龙江省', '大庆市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (773, '3', '2', '230602', '23', '萨尔图区', '黑龙江省', '大庆市', '萨尔图区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (774, '3', '2', '230603', '23', '龙凤区', '黑龙江省', '大庆市', '龙凤区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (775, '3', '2', '230604', '23', '让胡路区', '黑龙江省', '大庆市', '让胡路区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (776, '3', '2', '230605', '23', '红岗区', '黑龙江省', '大庆市', '红岗区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (777, '3', '2', '230606', '23', '大同区', '黑龙江省', '大庆市', '大同区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (778, '3', '2', '230621', '23', '肇州县', '黑龙江省', '大庆市', '肇州县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (779, '3', '2', '230622', '23', '肇源县', '黑龙江省', '大庆市', '肇源县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (780, '3', '2', '230623', '23', '林甸县', '黑龙江省', '大庆市', '林甸县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (781, '3', '2', '230624', '23', '杜尔伯特蒙古族自治县', '黑龙江省', '大庆市', '杜尔伯特蒙古族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (782, '2', '2', '230700', '23', '伊春市', '黑龙江省', '伊春市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (783, '3', '2', '230701', '23', '市辖区', '黑龙江省', '伊春市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (784, '3', '2', '230702', '23', '伊春区', '黑龙江省', '伊春市', '伊春区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (785, '3', '2', '230703', '23', '南岔区', '黑龙江省', '伊春市', '南岔区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (786, '3', '2', '230704', '23', '友好区', '黑龙江省', '伊春市', '友好区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (787, '3', '2', '230705', '23', '西林区', '黑龙江省', '伊春市', '西林区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (788, '3', '2', '230706', '23', '翠峦区', '黑龙江省', '伊春市', '翠峦区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (789, '3', '2', '230707', '23', '新青区', '黑龙江省', '伊春市', '新青区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (790, '3', '2', '230708', '23', '美溪区', '黑龙江省', '伊春市', '美溪区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (791, '3', '2', '230709', '23', '金山屯区', '黑龙江省', '伊春市', '金山屯区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (792, '3', '2', '230710', '23', '五营区', '黑龙江省', '伊春市', '五营区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (793, '3', '2', '230711', '23', '乌马河区', '黑龙江省', '伊春市', '乌马河区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (794, '3', '2', '230712', '23', '汤旺河区', '黑龙江省', '伊春市', '汤旺河区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (795, '3', '2', '230713', '23', '带岭区', '黑龙江省', '伊春市', '带岭区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (796, '3', '2', '230714', '23', '乌伊岭区', '黑龙江省', '伊春市', '乌伊岭区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (797, '3', '2', '230715', '23', '红星区', '黑龙江省', '伊春市', '红星区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (798, '3', '2', '230716', '23', '上甘岭区', '黑龙江省', '伊春市', '上甘岭区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (799, '3', '2', '230722', '23', '嘉荫县', '黑龙江省', '伊春市', '嘉荫县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (800, '3', '2', '230781', '23', '铁力市', '黑龙江省', '伊春市', '铁力市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (801, '2', '2', '230800', '23', '佳木斯市', '黑龙江省', '佳木斯市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (802, '3', '2', '230801', '23', '市辖区', '黑龙江省', '佳木斯市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (803, '3', '2', '230803', '23', '向阳区', '黑龙江省', '佳木斯市', '向阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (804, '3', '2', '230804', '23', '前进区', '黑龙江省', '佳木斯市', '前进区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (805, '3', '2', '230805', '23', '东风区', '黑龙江省', '佳木斯市', '东风区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (806, '3', '2', '230811', '23', '郊区', '黑龙江省', '佳木斯市', '郊区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (807, '3', '2', '230822', '23', '桦南县', '黑龙江省', '佳木斯市', '桦南县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (808, '3', '2', '230826', '23', '桦川县', '黑龙江省', '佳木斯市', '桦川县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (809, '3', '2', '230828', '23', '汤原县', '黑龙江省', '佳木斯市', '汤原县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (810, '3', '2', '230833', '23', '抚远县', '黑龙江省', '佳木斯市', '抚远县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (811, '3', '2', '230881', '23', '同江市', '黑龙江省', '佳木斯市', '同江市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (812, '3', '2', '230882', '23', '富锦市', '黑龙江省', '佳木斯市', '富锦市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (813, '2', '2', '230900', '23', '七台河市', '黑龙江省', '七台河市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (814, '3', '2', '230901', '23', '市辖区', '黑龙江省', '七台河市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (815, '3', '2', '230902', '23', '新兴区', '黑龙江省', '七台河市', '新兴区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (816, '3', '2', '230903', '23', '桃山区', '黑龙江省', '七台河市', '桃山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (817, '3', '2', '230904', '23', '茄子河区', '黑龙江省', '七台河市', '茄子河区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (818, '3', '2', '230921', '23', '勃利县', '黑龙江省', '七台河市', '勃利县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (819, '2', '2', '231000', '23', '牡丹江市', '黑龙江省', '牡丹江市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (820, '3', '2', '231001', '23', '市辖区', '黑龙江省', '牡丹江市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (821, '3', '2', '231002', '23', '东安区', '黑龙江省', '牡丹江市', '东安区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (822, '3', '2', '231003', '23', '阳明区', '黑龙江省', '牡丹江市', '阳明区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (823, '3', '2', '231004', '23', '爱民区', '黑龙江省', '牡丹江市', '爱民区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (824, '3', '2', '231005', '23', '西安区', '黑龙江省', '牡丹江市', '西安区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (825, '3', '2', '231024', '23', '东宁县', '黑龙江省', '牡丹江市', '东宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (826, '3', '2', '231025', '23', '林口县', '黑龙江省', '牡丹江市', '林口县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (827, '3', '2', '231081', '23', '绥芬河市', '黑龙江省', '牡丹江市', '绥芬河市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (828, '3', '2', '231083', '23', '海林市', '黑龙江省', '牡丹江市', '海林市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (829, '3', '2', '231084', '23', '宁安市', '黑龙江省', '牡丹江市', '宁安市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (830, '3', '2', '231085', '23', '穆棱市', '黑龙江省', '牡丹江市', '穆棱市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (831, '2', '2', '231100', '23', '黑河市', '黑龙江省', '黑河市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (832, '3', '2', '231101', '23', '市辖区', '黑龙江省', '黑河市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (833, '3', '2', '231102', '23', '爱辉区', '黑龙江省', '黑河市', '爱辉区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (834, '3', '2', '231121', '23', '嫩江县', '黑龙江省', '黑河市', '嫩江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (835, '3', '2', '231123', '23', '逊克县', '黑龙江省', '黑河市', '逊克县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (836, '3', '2', '231124', '23', '孙吴县', '黑龙江省', '黑河市', '孙吴县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (837, '3', '2', '231181', '23', '北安市', '黑龙江省', '黑河市', '北安市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (838, '3', '2', '231182', '23', '五大连池市', '黑龙江省', '黑河市', '五大连池市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (839, '2', '2', '231200', '23', '绥化市', '黑龙江省', '绥化市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (840, '3', '2', '231201', '23', '市辖区', '黑龙江省', '绥化市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (841, '3', '2', '231202', '23', '北林区', '黑龙江省', '绥化市', '北林区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (842, '3', '2', '231221', '23', '望奎县', '黑龙江省', '绥化市', '望奎县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (843, '3', '2', '231222', '23', '兰西县', '黑龙江省', '绥化市', '兰西县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (844, '3', '2', '231223', '23', '青冈县', '黑龙江省', '绥化市', '青冈县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (845, '3', '2', '231224', '23', '庆安县', '黑龙江省', '绥化市', '庆安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (846, '3', '2', '231225', '23', '明水县', '黑龙江省', '绥化市', '明水县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (847, '3', '2', '231226', '23', '绥棱县', '黑龙江省', '绥化市', '绥棱县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (848, '3', '2', '231281', '23', '安达市', '黑龙江省', '绥化市', '安达市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (849, '3', '2', '231282', '23', '肇东市', '黑龙江省', '绥化市', '肇东市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (850, '3', '2', '231283', '23', '海伦市', '黑龙江省', '绥化市', '海伦市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (851, '2', '2', '232700', '23', '大兴安岭地区', '黑龙江省', '大兴安岭地区', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (852, '3', '2', '232721', '23', '呼玛县', '黑龙江省', '大兴安岭地区', '呼玛县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (853, '3', '2', '232722', '23', '塔河县', '黑龙江省', '大兴安岭地区', '塔河县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (854, '3', '2', '232723', '23', '漠河县', '黑龙江省', '大兴安岭地区', '漠河县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (855, '1', '3', '310000', '31', '上海', '上海', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (856, '2', '3', '310100', '31', '上海市', '上海', '上海市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (857, '3', '3', '310101', '31', '黄浦区', '上海', '上海市', '黄浦区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (858, '3', '3', '310104', '31', '徐汇区', '上海', '上海市', '徐汇区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (859, '3', '3', '310105', '31', '长宁区', '上海', '上海市', '长宁区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (860, '3', '3', '310106', '31', '静安区', '上海', '上海市', '静安区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (861, '3', '3', '310107', '31', '普陀区', '上海', '上海市', '普陀区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (862, '3', '3', '310109', '31', '虹口区', '上海', '上海市', '虹口区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (863, '3', '3', '310110', '31', '杨浦区', '上海', '上海市', '杨浦区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (864, '3', '3', '310112', '31', '闵行区', '上海', '上海市', '闵行区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (865, '3', '3', '310113', '31', '宝山区', '上海', '上海市', '宝山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (866, '3', '3', '310114', '31', '嘉定区', '上海', '上海市', '嘉定区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (867, '3', '3', '310115', '31', '浦东新区', '上海', '上海市', '浦东新区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (868, '3', '3', '310116', '31', '金山区', '上海', '上海市', '金山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (869, '3', '3', '310117', '31', '松江区', '上海', '上海市', '松江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (870, '3', '3', '310118', '31', '青浦区', '上海', '上海市', '青浦区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (871, '3', '3', '310120', '31', '奉贤区', '上海', '上海市', '奉贤区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (872, '3', '3', '310251', '31', '崇明区', '上海', '上海市', '崇明区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (873, '1', '3', '320000', '32', '江苏省', '江苏省', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (874, '2', '3', '320100', '32', '南京市', '江苏省', '南京市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (875, '3', '3', '320101', '32', '市辖区', '江苏省', '南京市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (876, '3', '3', '320102', '32', '玄武区', '江苏省', '南京市', '玄武区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (877, '3', '3', '320104', '32', '秦淮区', '江苏省', '南京市', '秦淮区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (878, '3', '3', '320105', '32', '建邺区', '江苏省', '南京市', '建邺区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (879, '3', '3', '320106', '32', '鼓楼区', '江苏省', '南京市', '鼓楼区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (880, '3', '3', '320111', '32', '浦口区', '江苏省', '南京市', '浦口区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (881, '3', '3', '320113', '32', '栖霞区', '江苏省', '南京市', '栖霞区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (882, '3', '3', '320114', '32', '雨花台区', '江苏省', '南京市', '雨花台区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (883, '3', '3', '320115', '32', '江宁区', '江苏省', '南京市', '江宁区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (884, '3', '3', '320116', '32', '六合区', '江苏省', '南京市', '六合区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (885, '3', '3', '320117', '32', '溧水区', '江苏省', '南京市', '溧水区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (886, '3', '3', '320118', '32', '高淳区', '江苏省', '南京市', '高淳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (887, '2', '3', '320200', '32', '无锡市', '江苏省', '无锡市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (888, '3', '3', '320201', '32', '市辖区', '江苏省', '无锡市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (889, '3', '3', '320202', '32', '崇安区', '江苏省', '无锡市', '崇安区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (890, '3', '3', '320203', '32', '南长区', '江苏省', '无锡市', '南长区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (891, '3', '3', '320204', '32', '北塘区', '江苏省', '无锡市', '北塘区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (892, '3', '3', '320205', '32', '锡山区', '江苏省', '无锡市', '锡山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (893, '3', '3', '320206', '32', '惠山区', '江苏省', '无锡市', '惠山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (894, '3', '3', '320211', '32', '滨湖区', '江苏省', '无锡市', '滨湖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (895, '3', '3', '320281', '32', '江阴市', '江苏省', '无锡市', '江阴市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (896, '3', '3', '320282', '32', '宜兴市', '江苏省', '无锡市', '宜兴市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (897, '2', '3', '320300', '32', '徐州市', '江苏省', '徐州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (898, '3', '3', '320301', '32', '市辖区', '江苏省', '徐州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (899, '3', '3', '320302', '32', '鼓楼区', '江苏省', '徐州市', '鼓楼区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (900, '3', '3', '320303', '32', '云龙区', '江苏省', '徐州市', '云龙区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (901, '3', '3', '320305', '32', '贾汪区', '江苏省', '徐州市', '贾汪区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (902, '3', '3', '320311', '32', '泉山区', '江苏省', '徐州市', '泉山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (903, '3', '3', '320312', '32', '铜山区', '江苏省', '徐州市', '铜山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (904, '3', '3', '320321', '32', '丰县', '江苏省', '徐州市', '丰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (905, '3', '3', '320322', '32', '沛县', '江苏省', '徐州市', '沛县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (906, '3', '3', '320324', '32', '睢宁县', '江苏省', '徐州市', '睢宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (907, '3', '3', '320381', '32', '新沂市', '江苏省', '徐州市', '新沂市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (908, '3', '3', '320382', '32', '邳州市', '江苏省', '徐州市', '邳州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (909, '2', '3', '320400', '32', '常州市', '江苏省', '常州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (910, '3', '3', '320401', '32', '市辖区', '江苏省', '常州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (911, '3', '3', '320402', '32', '天宁区', '江苏省', '常州市', '天宁区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (912, '3', '3', '320404', '32', '钟楼区', '江苏省', '常州市', '钟楼区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (913, '3', '3', '320411', '32', '新北区', '江苏省', '常州市', '新北区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (914, '3', '3', '320412', '32', '武进区', '江苏省', '常州市', '武进区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (915, '3', '3', '320413', '32', '金坛区', '江苏省', '常州市', '金坛区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (916, '3', '3', '320481', '32', '溧阳市', '江苏省', '常州市', '溧阳市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (917, '2', '3', '320500', '32', '苏州市', '江苏省', '苏州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (918, '3', '3', '320501', '32', '市辖区', '江苏省', '苏州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (919, '3', '3', '320505', '32', '虎丘区', '江苏省', '苏州市', '虎丘区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (920, '3', '3', '320506', '32', '吴中区', '江苏省', '苏州市', '吴中区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (921, '3', '3', '320507', '32', '相城区', '江苏省', '苏州市', '相城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (922, '3', '3', '320508', '32', '姑苏区', '江苏省', '苏州市', '姑苏区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (923, '3', '3', '320509', '32', '吴江区', '江苏省', '苏州市', '吴江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (924, '3', '3', '320581', '32', '常熟市', '江苏省', '苏州市', '常熟市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (925, '3', '3', '320582', '32', '张家港市', '江苏省', '苏州市', '张家港市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (926, '3', '3', '320583', '32', '昆山市', '江苏省', '苏州市', '昆山市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (927, '3', '3', '320585', '32', '太仓市', '江苏省', '苏州市', '太仓市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (928, '2', '3', '320600', '32', '南通市', '江苏省', '南通市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (929, '3', '3', '320601', '32', '市辖区', '江苏省', '南通市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (930, '3', '3', '320602', '32', '崇川区', '江苏省', '南通市', '崇川区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (931, '3', '3', '320611', '32', '港闸区', '江苏省', '南通市', '港闸区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (932, '3', '3', '320612', '32', '通州区', '江苏省', '南通市', '通州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (933, '3', '3', '320621', '32', '海安县', '江苏省', '南通市', '海安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (934, '3', '3', '320623', '32', '如东县', '江苏省', '南通市', '如东县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (935, '3', '3', '320681', '32', '启东市', '江苏省', '南通市', '启东市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (936, '3', '3', '320682', '32', '如皋市', '江苏省', '南通市', '如皋市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (937, '3', '3', '320684', '32', '海门市', '江苏省', '南通市', '海门市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (938, '2', '3', '320700', '32', '连云港市', '江苏省', '连云港市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (939, '3', '3', '320701', '32', '市辖区', '江苏省', '连云港市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (940, '3', '3', '320703', '32', '连云区', '江苏省', '连云港市', '连云区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (941, '3', '3', '320706', '32', '海州区', '江苏省', '连云港市', '海州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (942, '3', '3', '320707', '32', '赣榆区', '江苏省', '连云港市', '赣榆区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (943, '3', '3', '320722', '32', '东海县', '江苏省', '连云港市', '东海县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (944, '3', '3', '320723', '32', '灌云县', '江苏省', '连云港市', '灌云县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (945, '3', '3', '320724', '32', '灌南县', '江苏省', '连云港市', '灌南县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (946, '2', '3', '320800', '32', '淮安市', '江苏省', '淮安市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (947, '3', '3', '320801', '32', '市辖区', '江苏省', '淮安市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (948, '3', '3', '320802', '32', '清河区', '江苏省', '淮安市', '清河区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (949, '3', '3', '320803', '32', '淮安区', '江苏省', '淮安市', '淮安区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (950, '3', '3', '320804', '32', '淮阴区', '江苏省', '淮安市', '淮阴区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (951, '3', '3', '320811', '32', '清浦区', '江苏省', '淮安市', '清浦区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (952, '3', '3', '320826', '32', '涟水县', '江苏省', '淮安市', '涟水县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (953, '3', '3', '320829', '32', '洪泽县', '江苏省', '淮安市', '洪泽县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (954, '3', '3', '320830', '32', '盱眙县', '江苏省', '淮安市', '盱眙县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (955, '3', '3', '320831', '32', '金湖县', '江苏省', '淮安市', '金湖县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (956, '2', '3', '320900', '32', '盐城市', '江苏省', '盐城市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (957, '3', '3', '320901', '32', '市辖区', '江苏省', '盐城市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (958, '3', '3', '320902', '32', '亭湖区', '江苏省', '盐城市', '亭湖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (959, '3', '3', '320903', '32', '盐都区', '江苏省', '盐城市', '盐都区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (960, '3', '3', '320904', '32', '大丰区', '江苏省', '盐城市', '大丰区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (961, '3', '3', '320921', '32', '响水县', '江苏省', '盐城市', '响水县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (962, '3', '3', '320922', '32', '滨海县', '江苏省', '盐城市', '滨海县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (963, '3', '3', '320923', '32', '阜宁县', '江苏省', '盐城市', '阜宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (964, '3', '3', '320924', '32', '射阳县', '江苏省', '盐城市', '射阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (965, '3', '3', '320925', '32', '建湖县', '江苏省', '盐城市', '建湖县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (966, '3', '3', '320981', '32', '东台市', '江苏省', '盐城市', '东台市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (967, '2', '3', '321000', '32', '扬州市', '江苏省', '扬州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (968, '3', '3', '321001', '32', '市辖区', '江苏省', '扬州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (969, '3', '3', '321002', '32', '广陵区', '江苏省', '扬州市', '广陵区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (970, '3', '3', '321003', '32', '邗江区', '江苏省', '扬州市', '邗江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (971, '3', '3', '321012', '32', '江都区', '江苏省', '扬州市', '江都区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (972, '3', '3', '321023', '32', '宝应县', '江苏省', '扬州市', '宝应县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (973, '3', '3', '321081', '32', '仪征市', '江苏省', '扬州市', '仪征市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (974, '3', '3', '321084', '32', '高邮市', '江苏省', '扬州市', '高邮市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (975, '2', '3', '321100', '32', '镇江市', '江苏省', '镇江市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (976, '3', '3', '321101', '32', '市辖区', '江苏省', '镇江市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (977, '3', '3', '321102', '32', '京口区', '江苏省', '镇江市', '京口区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (978, '3', '3', '321111', '32', '润州区', '江苏省', '镇江市', '润州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (979, '3', '3', '321112', '32', '丹徒区', '江苏省', '镇江市', '丹徒区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (980, '3', '3', '321181', '32', '丹阳市', '江苏省', '镇江市', '丹阳市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (981, '3', '3', '321182', '32', '扬中市', '江苏省', '镇江市', '扬中市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (982, '3', '3', '321183', '32', '句容市', '江苏省', '镇江市', '句容市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (983, '2', '3', '321200', '32', '泰州市', '江苏省', '泰州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (984, '3', '3', '321201', '32', '市辖区', '江苏省', '泰州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (985, '3', '3', '321202', '32', '海陵区', '江苏省', '泰州市', '海陵区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (986, '3', '3', '321203', '32', '高港区', '江苏省', '泰州市', '高港区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (987, '3', '3', '321204', '32', '姜堰区', '江苏省', '泰州市', '姜堰区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (988, '3', '3', '321281', '32', '兴化市', '江苏省', '泰州市', '兴化市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (989, '3', '3', '321282', '32', '靖江市', '江苏省', '泰州市', '靖江市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (990, '3', '3', '321283', '32', '泰兴市', '江苏省', '泰州市', '泰兴市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (991, '2', '3', '321300', '32', '宿迁市', '江苏省', '宿迁市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (992, '3', '3', '321301', '32', '市辖区', '江苏省', '宿迁市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (993, '3', '3', '321302', '32', '宿城区', '江苏省', '宿迁市', '宿城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (994, '3', '3', '321311', '32', '宿豫区', '江苏省', '宿迁市', '宿豫区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (995, '3', '3', '321322', '32', '沭阳县', '江苏省', '宿迁市', '沭阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (996, '3', '3', '321323', '32', '泗阳县', '江苏省', '宿迁市', '泗阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (997, '3', '3', '321324', '32', '泗洪县', '江苏省', '宿迁市', '泗洪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (998, '1', '3', '330000', '33', '浙江省', '浙江省', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (999, '2', '3', '330100', '33', '杭州市', '浙江省', '杭州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1000, '3', '3', '330101', '33', '市辖区', '浙江省', '杭州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1001, '3', '3', '330102', '33', '上城区', '浙江省', '杭州市', '上城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1002, '3', '3', '330103', '33', '下城区', '浙江省', '杭州市', '下城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1003, '3', '3', '330104', '33', '江干区', '浙江省', '杭州市', '江干区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1004, '3', '3', '330105', '33', '拱墅区', '浙江省', '杭州市', '拱墅区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1005, '3', '3', '330106', '33', '西湖区', '浙江省', '杭州市', '西湖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1006, '3', '3', '330108', '33', '滨江区', '浙江省', '杭州市', '滨江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1007, '3', '3', '330109', '33', '萧山区', '浙江省', '杭州市', '萧山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1008, '3', '3', '330110', '33', '余杭区', '浙江省', '杭州市', '余杭区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1009, '3', '3', '330111', '33', '富阳区', '浙江省', '杭州市', '富阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1010, '3', '3', '330122', '33', '桐庐县', '浙江省', '杭州市', '桐庐县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1011, '3', '3', '330127', '33', '淳安县', '浙江省', '杭州市', '淳安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1012, '3', '3', '330182', '33', '建德市', '浙江省', '杭州市', '建德市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1013, '3', '3', '330185', '33', '临安市', '浙江省', '杭州市', '临安市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1014, '2', '3', '330200', '33', '宁波市', '浙江省', '宁波市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1015, '3', '3', '330201', '33', '市辖区', '浙江省', '宁波市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1016, '3', '3', '330203', '33', '海曙区', '浙江省', '宁波市', '海曙区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1017, '3', '3', '330204', '33', '江东区', '浙江省', '宁波市', '江东区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1018, '3', '3', '330205', '33', '江北区', '浙江省', '宁波市', '江北区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1019, '3', '3', '330206', '33', '北仑区', '浙江省', '宁波市', '北仑区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1020, '3', '3', '330211', '33', '镇海区', '浙江省', '宁波市', '镇海区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1021, '3', '3', '330212', '33', '鄞州区', '浙江省', '宁波市', '鄞州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1022, '3', '3', '330225', '33', '象山县', '浙江省', '宁波市', '象山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1023, '3', '3', '330226', '33', '宁海县', '浙江省', '宁波市', '宁海县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1024, '3', '3', '330281', '33', '余姚市', '浙江省', '宁波市', '余姚市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1025, '3', '3', '330282', '33', '慈溪市', '浙江省', '宁波市', '慈溪市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1026, '3', '3', '330283', '33', '奉化市', '浙江省', '宁波市', '奉化市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1027, '2', '3', '330300', '33', '温州市', '浙江省', '温州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1028, '3', '3', '330301', '33', '市辖区', '浙江省', '温州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1029, '3', '3', '330302', '33', '鹿城区', '浙江省', '温州市', '鹿城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1030, '3', '3', '330303', '33', '龙湾区', '浙江省', '温州市', '龙湾区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1031, '3', '3', '330304', '33', '瓯海区', '浙江省', '温州市', '瓯海区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1032, '3', '3', '330305', '33', '洞头区', '浙江省', '温州市', '洞头区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1033, '3', '3', '330324', '33', '永嘉县', '浙江省', '温州市', '永嘉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1034, '3', '3', '330326', '33', '平阳县', '浙江省', '温州市', '平阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1035, '3', '3', '330327', '33', '苍南县', '浙江省', '温州市', '苍南县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1036, '3', '3', '330328', '33', '文成县', '浙江省', '温州市', '文成县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1037, '3', '3', '330329', '33', '泰顺县', '浙江省', '温州市', '泰顺县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1038, '3', '3', '330381', '33', '瑞安市', '浙江省', '温州市', '瑞安市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1039, '3', '3', '330382', '33', '乐清市', '浙江省', '温州市', '乐清市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1040, '2', '3', '330400', '33', '嘉兴市', '浙江省', '嘉兴市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1041, '3', '3', '330401', '33', '市辖区', '浙江省', '嘉兴市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1042, '3', '3', '330402', '33', '南湖区', '浙江省', '嘉兴市', '南湖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1043, '3', '3', '330411', '33', '秀洲区', '浙江省', '嘉兴市', '秀洲区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1044, '3', '3', '330421', '33', '嘉善县', '浙江省', '嘉兴市', '嘉善县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1045, '3', '3', '330424', '33', '海盐县', '浙江省', '嘉兴市', '海盐县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1046, '3', '3', '330481', '33', '海宁市', '浙江省', '嘉兴市', '海宁市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1047, '3', '3', '330482', '33', '平湖市', '浙江省', '嘉兴市', '平湖市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1048, '3', '3', '330483', '33', '桐乡市', '浙江省', '嘉兴市', '桐乡市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1049, '2', '3', '330500', '33', '湖州市', '浙江省', '湖州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1050, '3', '3', '330501', '33', '市辖区', '浙江省', '湖州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1051, '3', '3', '330502', '33', '吴兴区', '浙江省', '湖州市', '吴兴区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1052, '3', '3', '330503', '33', '南浔区', '浙江省', '湖州市', '南浔区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1053, '3', '3', '330521', '33', '德清县', '浙江省', '湖州市', '德清县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1054, '3', '3', '330522', '33', '长兴县', '浙江省', '湖州市', '长兴县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1055, '3', '3', '330523', '33', '安吉县', '浙江省', '湖州市', '安吉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1056, '2', '3', '330600', '33', '绍兴市', '浙江省', '绍兴市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1057, '3', '3', '330601', '33', '市辖区', '浙江省', '绍兴市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1058, '3', '3', '330602', '33', '越城区', '浙江省', '绍兴市', '越城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1059, '3', '3', '330603', '33', '柯桥区', '浙江省', '绍兴市', '柯桥区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1060, '3', '3', '330604', '33', '上虞区', '浙江省', '绍兴市', '上虞区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1061, '3', '3', '330624', '33', '新昌县', '浙江省', '绍兴市', '新昌县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1062, '3', '3', '330681', '33', '诸暨市', '浙江省', '绍兴市', '诸暨市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1063, '3', '3', '330683', '33', '嵊州市', '浙江省', '绍兴市', '嵊州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1064, '2', '3', '330700', '33', '金华市', '浙江省', '金华市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1065, '3', '3', '330701', '33', '市辖区', '浙江省', '金华市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1066, '3', '3', '330702', '33', '婺城区', '浙江省', '金华市', '婺城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1067, '3', '3', '330703', '33', '金东区', '浙江省', '金华市', '金东区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1068, '3', '3', '330723', '33', '武义县', '浙江省', '金华市', '武义县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1069, '3', '3', '330726', '33', '浦江县', '浙江省', '金华市', '浦江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1070, '3', '3', '330727', '33', '磐安县', '浙江省', '金华市', '磐安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1071, '3', '3', '330781', '33', '兰溪市', '浙江省', '金华市', '兰溪市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1072, '3', '3', '330782', '33', '义乌市', '浙江省', '金华市', '义乌市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1073, '3', '3', '330783', '33', '东阳市', '浙江省', '金华市', '东阳市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1074, '3', '3', '330784', '33', '永康市', '浙江省', '金华市', '永康市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1075, '2', '3', '330800', '33', '衢州市', '浙江省', '衢州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1076, '3', '3', '330801', '33', '市辖区', '浙江省', '衢州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1077, '3', '3', '330802', '33', '柯城区', '浙江省', '衢州市', '柯城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1078, '3', '3', '330803', '33', '衢江区', '浙江省', '衢州市', '衢江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1079, '3', '3', '330822', '33', '常山县', '浙江省', '衢州市', '常山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1080, '3', '3', '330824', '33', '开化县', '浙江省', '衢州市', '开化县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1081, '3', '3', '330825', '33', '龙游县', '浙江省', '衢州市', '龙游县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1082, '3', '3', '330881', '33', '江山市', '浙江省', '衢州市', '江山市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1083, '2', '3', '330900', '33', '舟山市', '浙江省', '舟山市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1084, '3', '3', '330901', '33', '市辖区', '浙江省', '舟山市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1085, '3', '3', '330902', '33', '定海区', '浙江省', '舟山市', '定海区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1086, '3', '3', '330903', '33', '普陀区', '浙江省', '舟山市', '普陀区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1087, '3', '3', '330921', '33', '岱山县', '浙江省', '舟山市', '岱山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1088, '3', '3', '330922', '33', '嵊泗县', '浙江省', '舟山市', '嵊泗县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1089, '2', '3', '331000', '33', '台州市', '浙江省', '台州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1090, '3', '3', '331001', '33', '市辖区', '浙江省', '台州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1091, '3', '3', '331002', '33', '椒江区', '浙江省', '台州市', '椒江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1092, '3', '3', '331003', '33', '黄岩区', '浙江省', '台州市', '黄岩区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1093, '3', '3', '331004', '33', '路桥区', '浙江省', '台州市', '路桥区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1094, '3', '3', '331021', '33', '玉环县', '浙江省', '台州市', '玉环县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1095, '3', '3', '331022', '33', '三门县', '浙江省', '台州市', '三门县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1096, '3', '3', '331023', '33', '天台县', '浙江省', '台州市', '天台县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1097, '3', '3', '331024', '33', '仙居县', '浙江省', '台州市', '仙居县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1098, '3', '3', '331081', '33', '温岭市', '浙江省', '台州市', '温岭市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1099, '3', '3', '331082', '33', '临海市', '浙江省', '台州市', '临海市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1100, '2', '3', '331100', '33', '丽水市', '浙江省', '丽水市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1101, '3', '3', '331101', '33', '市辖区', '浙江省', '丽水市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1102, '3', '3', '331102', '33', '莲都区', '浙江省', '丽水市', '莲都区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1103, '3', '3', '331121', '33', '青田县', '浙江省', '丽水市', '青田县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1104, '3', '3', '331122', '33', '缙云县', '浙江省', '丽水市', '缙云县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1105, '3', '3', '331123', '33', '遂昌县', '浙江省', '丽水市', '遂昌县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1106, '3', '3', '331124', '33', '松阳县', '浙江省', '丽水市', '松阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1107, '3', '3', '331125', '33', '云和县', '浙江省', '丽水市', '云和县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1108, '3', '3', '331126', '33', '庆元县', '浙江省', '丽水市', '庆元县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1109, '3', '3', '331127', '33', '景宁畲族自治县', '浙江省', '丽水市', '景宁畲族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1110, '3', '3', '331181', '33', '龙泉市', '浙江省', '丽水市', '龙泉市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1111, '1', '3', '340000', '34', '安徽省', '安徽省', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1112, '2', '3', '340100', '34', '合肥市', '安徽省', '合肥市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1113, '3', '3', '340101', '34', '市辖区', '安徽省', '合肥市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1114, '3', '3', '340102', '34', '瑶海区', '安徽省', '合肥市', '瑶海区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1115, '3', '3', '340103', '34', '庐阳区', '安徽省', '合肥市', '庐阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1116, '3', '3', '340104', '34', '蜀山区', '安徽省', '合肥市', '蜀山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1117, '3', '3', '340111', '34', '包河区', '安徽省', '合肥市', '包河区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1118, '3', '3', '340121', '34', '长丰县', '安徽省', '合肥市', '长丰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1119, '3', '3', '340122', '34', '肥东县', '安徽省', '合肥市', '肥东县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1120, '3', '3', '340123', '34', '肥西县', '安徽省', '合肥市', '肥西县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1121, '3', '3', '340124', '34', '庐江县', '安徽省', '合肥市', '庐江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1122, '3', '3', '340181', '34', '巢湖市', '安徽省', '合肥市', '巢湖市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1123, '2', '3', '340200', '34', '芜湖市', '安徽省', '芜湖市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1124, '3', '3', '340201', '34', '市辖区', '安徽省', '芜湖市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1125, '3', '3', '340202', '34', '镜湖区', '安徽省', '芜湖市', '镜湖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1126, '3', '3', '340203', '34', '弋江区', '安徽省', '芜湖市', '弋江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1127, '3', '3', '340207', '34', '鸠江区', '安徽省', '芜湖市', '鸠江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1128, '3', '3', '340208', '34', '三山区', '安徽省', '芜湖市', '三山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1129, '3', '3', '340221', '34', '芜湖县', '安徽省', '芜湖市', '芜湖县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1130, '3', '3', '340222', '34', '繁昌县', '安徽省', '芜湖市', '繁昌县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1131, '3', '3', '340223', '34', '南陵县', '安徽省', '芜湖市', '南陵县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1132, '3', '3', '340225', '34', '无为县', '安徽省', '芜湖市', '无为县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1133, '2', '3', '340300', '34', '蚌埠市', '安徽省', '蚌埠市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1134, '3', '3', '340301', '34', '市辖区', '安徽省', '蚌埠市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1135, '3', '3', '340302', '34', '龙子湖区', '安徽省', '蚌埠市', '龙子湖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1136, '3', '3', '340303', '34', '蚌山区', '安徽省', '蚌埠市', '蚌山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1137, '3', '3', '340304', '34', '禹会区', '安徽省', '蚌埠市', '禹会区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1138, '3', '3', '340311', '34', '淮上区', '安徽省', '蚌埠市', '淮上区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1139, '3', '3', '340321', '34', '怀远县', '安徽省', '蚌埠市', '怀远县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1140, '3', '3', '340322', '34', '五河县', '安徽省', '蚌埠市', '五河县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1141, '3', '3', '340323', '34', '固镇县', '安徽省', '蚌埠市', '固镇县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1142, '2', '3', '340400', '34', '淮南市', '安徽省', '淮南市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1143, '3', '3', '340401', '34', '市辖区', '安徽省', '淮南市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1144, '3', '3', '340402', '34', '大通区', '安徽省', '淮南市', '大通区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1145, '3', '3', '340403', '34', '田家庵区', '安徽省', '淮南市', '田家庵区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1146, '3', '3', '340404', '34', '谢家集区', '安徽省', '淮南市', '谢家集区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1147, '3', '3', '340405', '34', '八公山区', '安徽省', '淮南市', '八公山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1148, '3', '3', '340406', '34', '潘集区', '安徽省', '淮南市', '潘集区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1149, '3', '3', '340421', '34', '凤台县', '安徽省', '淮南市', '凤台县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1150, '2', '3', '340500', '34', '马鞍山市', '安徽省', '马鞍山市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1151, '3', '3', '340501', '34', '市辖区', '安徽省', '马鞍山市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1152, '3', '3', '340503', '34', '花山区', '安徽省', '马鞍山市', '花山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1153, '3', '3', '340504', '34', '雨山区', '安徽省', '马鞍山市', '雨山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1154, '3', '3', '340506', '34', '博望区', '安徽省', '马鞍山市', '博望区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1155, '3', '3', '340521', '34', '当涂县', '安徽省', '马鞍山市', '当涂县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1156, '3', '3', '340522', '34', '含山县', '安徽省', '马鞍山市', '含山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1157, '3', '3', '340523', '34', '和县', '安徽省', '马鞍山市', '和县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1158, '2', '3', '340600', '34', '淮北市', '安徽省', '淮北市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1159, '3', '3', '340601', '34', '市辖区', '安徽省', '淮北市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1160, '3', '3', '340602', '34', '杜集区', '安徽省', '淮北市', '杜集区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1161, '3', '3', '340603', '34', '相山区', '安徽省', '淮北市', '相山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1162, '3', '3', '340604', '34', '烈山区', '安徽省', '淮北市', '烈山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1163, '3', '3', '340621', '34', '濉溪县', '安徽省', '淮北市', '濉溪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1164, '2', '3', '340700', '34', '铜陵市', '安徽省', '铜陵市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1165, '3', '3', '340701', '34', '市辖区', '安徽省', '铜陵市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1166, '3', '3', '340702', '34', '铜官山区', '安徽省', '铜陵市', '铜官山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1167, '3', '3', '340703', '34', '狮子山区', '安徽省', '铜陵市', '狮子山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1168, '3', '3', '340711', '34', '郊区', '安徽省', '铜陵市', '郊区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1169, '3', '3', '340721', '34', '铜陵县', '安徽省', '铜陵市', '铜陵县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1170, '2', '3', '340800', '34', '安庆市', '安徽省', '安庆市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1171, '3', '3', '340801', '34', '市辖区', '安徽省', '安庆市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1172, '3', '3', '340802', '34', '迎江区', '安徽省', '安庆市', '迎江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1173, '3', '3', '340803', '34', '大观区', '安徽省', '安庆市', '大观区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1174, '3', '3', '340811', '34', '宜秀区', '安徽省', '安庆市', '宜秀区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1175, '3', '3', '340822', '34', '怀宁县', '安徽省', '安庆市', '怀宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1176, '3', '3', '340823', '34', '枞阳县', '安徽省', '安庆市', '枞阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1177, '3', '3', '340824', '34', '潜山县', '安徽省', '安庆市', '潜山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1178, '3', '3', '340825', '34', '太湖县', '安徽省', '安庆市', '太湖县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1179, '3', '3', '340826', '34', '宿松县', '安徽省', '安庆市', '宿松县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1180, '3', '3', '340827', '34', '望江县', '安徽省', '安庆市', '望江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1181, '3', '3', '340828', '34', '岳西县', '安徽省', '安庆市', '岳西县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1182, '3', '3', '340881', '34', '桐城市', '安徽省', '安庆市', '桐城市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1183, '2', '3', '341000', '34', '黄山市', '安徽省', '黄山市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1184, '3', '3', '341001', '34', '市辖区', '安徽省', '黄山市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1185, '3', '3', '341002', '34', '屯溪区', '安徽省', '黄山市', '屯溪区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1186, '3', '3', '341003', '34', '黄山区', '安徽省', '黄山市', '黄山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1187, '3', '3', '341004', '34', '徽州区', '安徽省', '黄山市', '徽州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1188, '3', '3', '341021', '34', '歙县', '安徽省', '黄山市', '歙县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1189, '3', '3', '341022', '34', '休宁县', '安徽省', '黄山市', '休宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1190, '3', '3', '341023', '34', '黟县', '安徽省', '黄山市', '黟县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1191, '3', '3', '341024', '34', '祁门县', '安徽省', '黄山市', '祁门县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1192, '2', '3', '341100', '34', '滁州市', '安徽省', '滁州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1193, '3', '3', '341101', '34', '市辖区', '安徽省', '滁州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1194, '3', '3', '341102', '34', '琅琊区', '安徽省', '滁州市', '琅琊区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1195, '3', '3', '341103', '34', '南谯区', '安徽省', '滁州市', '南谯区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1196, '3', '3', '341122', '34', '来安县', '安徽省', '滁州市', '来安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1197, '3', '3', '341124', '34', '全椒县', '安徽省', '滁州市', '全椒县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1198, '3', '3', '341125', '34', '定远县', '安徽省', '滁州市', '定远县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1199, '3', '3', '341126', '34', '凤阳县', '安徽省', '滁州市', '凤阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1200, '3', '3', '341181', '34', '天长市', '安徽省', '滁州市', '天长市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1201, '3', '3', '341182', '34', '明光市', '安徽省', '滁州市', '明光市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1202, '2', '3', '341200', '34', '阜阳市', '安徽省', '阜阳市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1203, '3', '3', '341201', '34', '市辖区', '安徽省', '阜阳市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1204, '3', '3', '341202', '34', '颍州区', '安徽省', '阜阳市', '颍州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1205, '3', '3', '341203', '34', '颍东区', '安徽省', '阜阳市', '颍东区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1206, '3', '3', '341204', '34', '颍泉区', '安徽省', '阜阳市', '颍泉区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1207, '3', '3', '341221', '34', '临泉县', '安徽省', '阜阳市', '临泉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1208, '3', '3', '341222', '34', '太和县', '安徽省', '阜阳市', '太和县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1209, '3', '3', '341225', '34', '阜南县', '安徽省', '阜阳市', '阜南县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1210, '3', '3', '341226', '34', '颍上县', '安徽省', '阜阳市', '颍上县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1211, '3', '3', '341282', '34', '界首市', '安徽省', '阜阳市', '界首市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1212, '2', '3', '341300', '34', '宿州市', '安徽省', '宿州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1213, '3', '3', '341301', '34', '市辖区', '安徽省', '宿州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1214, '3', '3', '341302', '34', '埇桥区', '安徽省', '宿州市', '埇桥区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1215, '3', '3', '341321', '34', '砀山县', '安徽省', '宿州市', '砀山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1216, '3', '3', '341322', '34', '萧县', '安徽省', '宿州市', '萧县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1217, '3', '3', '341323', '34', '灵璧县', '安徽省', '宿州市', '灵璧县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1218, '3', '3', '341324', '34', '泗县', '安徽省', '宿州市', '泗县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1219, '2', '3', '341500', '34', '六安市', '安徽省', '六安市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1220, '3', '3', '341501', '34', '市辖区', '安徽省', '六安市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1221, '3', '3', '341502', '34', '金安区', '安徽省', '六安市', '金安区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1222, '3', '3', '341503', '34', '裕安区', '安徽省', '六安市', '裕安区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1223, '3', '3', '341521', '34', '寿县', '安徽省', '六安市', '寿县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1224, '3', '3', '341522', '34', '霍邱县', '安徽省', '六安市', '霍邱县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1225, '3', '3', '341523', '34', '舒城县', '安徽省', '六安市', '舒城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1226, '3', '3', '341524', '34', '金寨县', '安徽省', '六安市', '金寨县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1227, '3', '3', '341525', '34', '霍山县', '安徽省', '六安市', '霍山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1228, '2', '3', '341600', '34', '亳州市', '安徽省', '亳州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1229, '3', '3', '341601', '34', '市辖区', '安徽省', '亳州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1230, '3', '3', '341602', '34', '谯城区', '安徽省', '亳州市', '谯城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1231, '3', '3', '341621', '34', '涡阳县', '安徽省', '亳州市', '涡阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1232, '3', '3', '341622', '34', '蒙城县', '安徽省', '亳州市', '蒙城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1233, '3', '3', '341623', '34', '利辛县', '安徽省', '亳州市', '利辛县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1234, '2', '3', '341700', '34', '池州市', '安徽省', '池州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1235, '3', '3', '341701', '34', '市辖区', '安徽省', '池州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1236, '3', '3', '341702', '34', '贵池区', '安徽省', '池州市', '贵池区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1237, '3', '3', '341721', '34', '东至县', '安徽省', '池州市', '东至县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1238, '3', '3', '341722', '34', '石台县', '安徽省', '池州市', '石台县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1239, '3', '3', '341723', '34', '青阳县', '安徽省', '池州市', '青阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1240, '2', '3', '341800', '34', '宣城市', '安徽省', '宣城市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1241, '3', '3', '341801', '34', '市辖区', '安徽省', '宣城市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1242, '3', '3', '341802', '34', '宣州区', '安徽省', '宣城市', '宣州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1243, '3', '3', '341821', '34', '郎溪县', '安徽省', '宣城市', '郎溪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1244, '3', '3', '341822', '34', '广德县', '安徽省', '宣城市', '广德县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1245, '3', '3', '341823', '34', '泾县', '安徽省', '宣城市', '泾县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1246, '3', '3', '341824', '34', '绩溪县', '安徽省', '宣城市', '绩溪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1247, '3', '3', '341825', '34', '旌德县', '安徽省', '宣城市', '旌德县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1248, '3', '3', '341881', '34', '宁国市', '安徽省', '宣城市', '宁国市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1249, '1', '3', '350000', '35', '福建省', '福建省', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1250, '2', '3', '350100', '35', '福州市', '福建省', '福州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1251, '3', '3', '350101', '35', '市辖区', '福建省', '福州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1252, '3', '3', '350102', '35', '鼓楼区', '福建省', '福州市', '鼓楼区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1253, '3', '3', '350103', '35', '台江区', '福建省', '福州市', '台江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1254, '3', '3', '350104', '35', '仓山区', '福建省', '福州市', '仓山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1255, '3', '3', '350105', '35', '马尾区', '福建省', '福州市', '马尾区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1256, '3', '3', '350111', '35', '晋安区', '福建省', '福州市', '晋安区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1257, '3', '3', '350121', '35', '闽侯县', '福建省', '福州市', '闽侯县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1258, '3', '3', '350122', '35', '连江县', '福建省', '福州市', '连江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1259, '3', '3', '350123', '35', '罗源县', '福建省', '福州市', '罗源县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1260, '3', '3', '350124', '35', '闽清县', '福建省', '福州市', '闽清县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1261, '3', '3', '350125', '35', '永泰县', '福建省', '福州市', '永泰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1262, '3', '3', '350128', '35', '平潭县', '福建省', '福州市', '平潭县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1263, '3', '3', '350181', '35', '福清市', '福建省', '福州市', '福清市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1264, '3', '3', '350182', '35', '长乐市', '福建省', '福州市', '长乐市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1265, '2', '3', '350200', '35', '厦门市', '福建省', '厦门市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1266, '3', '3', '350201', '35', '市辖区', '福建省', '厦门市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1267, '3', '3', '350203', '35', '思明区', '福建省', '厦门市', '思明区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1268, '3', '3', '350205', '35', '海沧区', '福建省', '厦门市', '海沧区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1269, '3', '3', '350206', '35', '湖里区', '福建省', '厦门市', '湖里区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1270, '3', '3', '350211', '35', '集美区', '福建省', '厦门市', '集美区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1271, '3', '3', '350212', '35', '同安区', '福建省', '厦门市', '同安区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1272, '3', '3', '350213', '35', '翔安区', '福建省', '厦门市', '翔安区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1273, '2', '3', '350300', '35', '莆田市', '福建省', '莆田市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1274, '3', '3', '350301', '35', '市辖区', '福建省', '莆田市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1275, '3', '3', '350302', '35', '城厢区', '福建省', '莆田市', '城厢区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1276, '3', '3', '350303', '35', '涵江区', '福建省', '莆田市', '涵江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1277, '3', '3', '350304', '35', '荔城区', '福建省', '莆田市', '荔城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1278, '3', '3', '350305', '35', '秀屿区', '福建省', '莆田市', '秀屿区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1279, '3', '3', '350322', '35', '仙游县', '福建省', '莆田市', '仙游县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1280, '2', '3', '350400', '35', '三明市', '福建省', '三明市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1281, '3', '3', '350401', '35', '市辖区', '福建省', '三明市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1282, '3', '3', '350402', '35', '梅列区', '福建省', '三明市', '梅列区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1283, '3', '3', '350403', '35', '三元区', '福建省', '三明市', '三元区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1284, '3', '3', '350421', '35', '明溪县', '福建省', '三明市', '明溪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1285, '3', '3', '350423', '35', '清流县', '福建省', '三明市', '清流县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1286, '3', '3', '350424', '35', '宁化县', '福建省', '三明市', '宁化县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1287, '3', '3', '350425', '35', '大田县', '福建省', '三明市', '大田县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1288, '3', '3', '350426', '35', '尤溪县', '福建省', '三明市', '尤溪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1289, '3', '3', '350427', '35', '沙县', '福建省', '三明市', '沙县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1290, '3', '3', '350428', '35', '将乐县', '福建省', '三明市', '将乐县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1291, '3', '3', '350429', '35', '泰宁县', '福建省', '三明市', '泰宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1292, '3', '3', '350430', '35', '建宁县', '福建省', '三明市', '建宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1293, '3', '3', '350481', '35', '永安市', '福建省', '三明市', '永安市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1294, '2', '3', '350500', '35', '泉州市', '福建省', '泉州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1295, '3', '3', '350501', '35', '市辖区', '福建省', '泉州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1296, '3', '3', '350502', '35', '鲤城区', '福建省', '泉州市', '鲤城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1297, '3', '3', '350503', '35', '丰泽区', '福建省', '泉州市', '丰泽区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1298, '3', '3', '350504', '35', '洛江区', '福建省', '泉州市', '洛江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1299, '3', '3', '350505', '35', '泉港区', '福建省', '泉州市', '泉港区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1300, '3', '3', '350521', '35', '惠安县', '福建省', '泉州市', '惠安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1301, '3', '3', '350524', '35', '安溪县', '福建省', '泉州市', '安溪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1302, '3', '3', '350525', '35', '永春县', '福建省', '泉州市', '永春县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1303, '3', '3', '350526', '35', '德化县', '福建省', '泉州市', '德化县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1304, '3', '3', '350527', '35', '金门县', '福建省', '泉州市', '金门县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1305, '3', '3', '350581', '35', '石狮市', '福建省', '泉州市', '石狮市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1306, '3', '3', '350582', '35', '晋江市', '福建省', '泉州市', '晋江市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1307, '3', '3', '350583', '35', '南安市', '福建省', '泉州市', '南安市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1308, '2', '3', '350600', '35', '漳州市', '福建省', '漳州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1309, '3', '3', '350601', '35', '市辖区', '福建省', '漳州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1310, '3', '3', '350602', '35', '芗城区', '福建省', '漳州市', '芗城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1311, '3', '3', '350603', '35', '龙文区', '福建省', '漳州市', '龙文区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1312, '3', '3', '350622', '35', '云霄县', '福建省', '漳州市', '云霄县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1313, '3', '3', '350623', '35', '漳浦县', '福建省', '漳州市', '漳浦县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1314, '3', '3', '350624', '35', '诏安县', '福建省', '漳州市', '诏安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1315, '3', '3', '350625', '35', '长泰县', '福建省', '漳州市', '长泰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1316, '3', '3', '350626', '35', '东山县', '福建省', '漳州市', '东山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1317, '3', '3', '350627', '35', '南靖县', '福建省', '漳州市', '南靖县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1318, '3', '3', '350628', '35', '平和县', '福建省', '漳州市', '平和县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1319, '3', '3', '350629', '35', '华安县', '福建省', '漳州市', '华安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1320, '3', '3', '350681', '35', '龙海市', '福建省', '漳州市', '龙海市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1321, '2', '3', '350700', '35', '南平市', '福建省', '南平市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1322, '3', '3', '350701', '35', '市辖区', '福建省', '南平市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1323, '3', '3', '350702', '35', '延平区', '福建省', '南平市', '延平区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1324, '3', '3', '350703', '35', '建阳区', '福建省', '南平市', '建阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1325, '3', '3', '350721', '35', '顺昌县', '福建省', '南平市', '顺昌县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1326, '3', '3', '350722', '35', '浦城县', '福建省', '南平市', '浦城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1327, '3', '3', '350723', '35', '光泽县', '福建省', '南平市', '光泽县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1328, '3', '3', '350724', '35', '松溪县', '福建省', '南平市', '松溪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1329, '3', '3', '350725', '35', '政和县', '福建省', '南平市', '政和县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1330, '3', '3', '350781', '35', '邵武市', '福建省', '南平市', '邵武市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1331, '3', '3', '350782', '35', '武夷山市', '福建省', '南平市', '武夷山市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1332, '3', '3', '350783', '35', '建瓯市', '福建省', '南平市', '建瓯市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1333, '2', '3', '350800', '35', '龙岩市', '福建省', '龙岩市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1334, '3', '3', '350801', '35', '市辖区', '福建省', '龙岩市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1335, '3', '3', '350802', '35', '新罗区', '福建省', '龙岩市', '新罗区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1336, '3', '3', '350803', '35', '永定区', '福建省', '龙岩市', '永定区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1337, '3', '3', '350821', '35', '长汀县', '福建省', '龙岩市', '长汀县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1338, '3', '3', '350823', '35', '上杭县', '福建省', '龙岩市', '上杭县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1339, '3', '3', '350824', '35', '武平县', '福建省', '龙岩市', '武平县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1340, '3', '3', '350825', '35', '连城县', '福建省', '龙岩市', '连城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (1341, '3', '3', '350881', '35', '漳平市', '福建省', '龙岩市', '漳平市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3731, '2', '3', '350900', '35', '宁德市', '福建省', '宁德市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3732, '3', '3', '350901', '35', '市辖区', '福建省', '宁德市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3733, '3', '3', '350902', '35', '蕉城区', '福建省', '宁德市', '蕉城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3734, '3', '3', '350921', '35', '霞浦县', '福建省', '宁德市', '霞浦县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3735, '3', '3', '350922', '35', '古田县', '福建省', '宁德市', '古田县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3736, '3', '3', '350923', '35', '屏南县', '福建省', '宁德市', '屏南县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3737, '3', '3', '350924', '35', '寿宁县', '福建省', '宁德市', '寿宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3738, '3', '3', '350925', '35', '周宁县', '福建省', '宁德市', '周宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3739, '3', '3', '350926', '35', '柘荣县', '福建省', '宁德市', '柘荣县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3740, '3', '3', '350981', '35', '福安市', '福建省', '宁德市', '福安市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3741, '3', '3', '350982', '35', '福鼎市', '福建省', '宁德市', '福鼎市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3742, '1', '3', '360000', '36', '江西省', '江西省', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3743, '2', '3', '360100', '36', '南昌市', '江西省', '南昌市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3744, '3', '3', '360101', '36', '市辖区', '江西省', '南昌市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3745, '3', '3', '360102', '36', '东湖区', '江西省', '南昌市', '东湖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3746, '3', '3', '360103', '36', '西湖区', '江西省', '南昌市', '西湖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3747, '3', '3', '360104', '36', '青云谱区', '江西省', '南昌市', '青云谱区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3748, '3', '3', '360105', '36', '湾里区', '江西省', '南昌市', '湾里区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3749, '3', '3', '360111', '36', '青山湖区', '江西省', '南昌市', '青山湖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3750, '3', '3', '360112', '36', '新建区', '江西省', '南昌市', '新建区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3751, '3', '3', '360121', '36', '南昌县', '江西省', '南昌市', '南昌县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3752, '3', '3', '360123', '36', '安义县', '江西省', '南昌市', '安义县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3753, '3', '3', '360124', '36', '进贤县', '江西省', '南昌市', '进贤县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3754, '2', '3', '360200', '36', '景德镇市', '江西省', '景德镇市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3755, '3', '3', '360201', '36', '市辖区', '江西省', '景德镇市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3756, '3', '3', '360202', '36', '昌江区', '江西省', '景德镇市', '昌江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3757, '3', '3', '360203', '36', '珠山区', '江西省', '景德镇市', '珠山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3758, '3', '3', '360222', '36', '浮梁县', '江西省', '景德镇市', '浮梁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3759, '3', '3', '360281', '36', '乐平市', '江西省', '景德镇市', '乐平市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3760, '2', '3', '360300', '36', '萍乡市', '江西省', '萍乡市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3761, '3', '3', '360301', '36', '市辖区', '江西省', '萍乡市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3762, '3', '3', '360302', '36', '安源区', '江西省', '萍乡市', '安源区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3763, '3', '3', '360313', '36', '湘东区', '江西省', '萍乡市', '湘东区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3764, '3', '3', '360321', '36', '莲花县', '江西省', '萍乡市', '莲花县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3765, '3', '3', '360322', '36', '上栗县', '江西省', '萍乡市', '上栗县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3766, '3', '3', '360323', '36', '芦溪县', '江西省', '萍乡市', '芦溪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3767, '2', '3', '360400', '36', '九江市', '江西省', '九江市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3768, '3', '3', '360401', '36', '市辖区', '江西省', '九江市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3769, '3', '3', '360402', '36', '庐山区', '江西省', '九江市', '庐山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3770, '3', '3', '360403', '36', '浔阳区', '江西省', '九江市', '浔阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3771, '3', '3', '360421', '36', '九江县', '江西省', '九江市', '九江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3772, '3', '3', '360423', '36', '武宁县', '江西省', '九江市', '武宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3773, '3', '3', '360424', '36', '修水县', '江西省', '九江市', '修水县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3774, '3', '3', '360425', '36', '永修县', '江西省', '九江市', '永修县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3775, '3', '3', '360426', '36', '德安县', '江西省', '九江市', '德安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3776, '3', '3', '360427', '36', '星子县', '江西省', '九江市', '星子县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3777, '3', '3', '360428', '36', '都昌县', '江西省', '九江市', '都昌县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3778, '3', '3', '360429', '36', '湖口县', '江西省', '九江市', '湖口县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3779, '3', '3', '360430', '36', '彭泽县', '江西省', '九江市', '彭泽县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3780, '3', '3', '360481', '36', '瑞昌市', '江西省', '九江市', '瑞昌市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3781, '3', '3', '360482', '36', '共青城市', '江西省', '九江市', '共青城市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3782, '2', '3', '360500', '36', '新余市', '江西省', '新余市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3783, '3', '3', '360501', '36', '市辖区', '江西省', '新余市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3784, '3', '3', '360502', '36', '渝水区', '江西省', '新余市', '渝水区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3785, '3', '3', '360521', '36', '分宜县', '江西省', '新余市', '分宜县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3786, '2', '3', '360600', '36', '鹰潭市', '江西省', '鹰潭市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3787, '3', '3', '360601', '36', '市辖区', '江西省', '鹰潭市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3788, '3', '3', '360602', '36', '月湖区', '江西省', '鹰潭市', '月湖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3789, '3', '3', '360622', '36', '余江县', '江西省', '鹰潭市', '余江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3790, '3', '3', '360681', '36', '贵溪市', '江西省', '鹰潭市', '贵溪市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3791, '2', '3', '360700', '36', '赣州市', '江西省', '赣州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3792, '3', '3', '360701', '36', '市辖区', '江西省', '赣州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3793, '3', '3', '360702', '36', '章贡区', '江西省', '赣州市', '章贡区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3794, '3', '3', '360703', '36', '南康区', '江西省', '赣州市', '南康区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3795, '3', '3', '360721', '36', '赣县', '江西省', '赣州市', '赣县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3796, '3', '3', '360722', '36', '信丰县', '江西省', '赣州市', '信丰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3797, '3', '3', '360723', '36', '大余县', '江西省', '赣州市', '大余县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3798, '3', '3', '360724', '36', '上犹县', '江西省', '赣州市', '上犹县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3799, '3', '3', '360725', '36', '崇义县', '江西省', '赣州市', '崇义县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3800, '3', '3', '360726', '36', '安远县', '江西省', '赣州市', '安远县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3801, '3', '3', '360727', '36', '龙南县', '江西省', '赣州市', '龙南县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3802, '3', '3', '360728', '36', '定南县', '江西省', '赣州市', '定南县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3803, '3', '3', '360729', '36', '全南县', '江西省', '赣州市', '全南县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3804, '3', '3', '360730', '36', '宁都县', '江西省', '赣州市', '宁都县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3805, '3', '3', '360731', '36', '于都县', '江西省', '赣州市', '于都县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3806, '3', '3', '360732', '36', '兴国县', '江西省', '赣州市', '兴国县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3807, '3', '3', '360733', '36', '会昌县', '江西省', '赣州市', '会昌县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3808, '3', '3', '360734', '36', '寻乌县', '江西省', '赣州市', '寻乌县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3809, '3', '3', '360735', '36', '石城县', '江西省', '赣州市', '石城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3810, '3', '3', '360781', '36', '瑞金市', '江西省', '赣州市', '瑞金市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3811, '2', '3', '360800', '36', '吉安市', '江西省', '吉安市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3812, '3', '3', '360801', '36', '市辖区', '江西省', '吉安市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3813, '3', '3', '360802', '36', '吉州区', '江西省', '吉安市', '吉州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3814, '3', '3', '360803', '36', '青原区', '江西省', '吉安市', '青原区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3815, '3', '3', '360821', '36', '吉安县', '江西省', '吉安市', '吉安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3816, '3', '3', '360822', '36', '吉水县', '江西省', '吉安市', '吉水县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3817, '3', '3', '360823', '36', '峡江县', '江西省', '吉安市', '峡江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3818, '3', '3', '360824', '36', '新干县', '江西省', '吉安市', '新干县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3819, '3', '3', '360825', '36', '永丰县', '江西省', '吉安市', '永丰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3820, '3', '3', '360826', '36', '泰和县', '江西省', '吉安市', '泰和县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3821, '3', '3', '360827', '36', '遂川县', '江西省', '吉安市', '遂川县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3822, '3', '3', '360828', '36', '万安县', '江西省', '吉安市', '万安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3823, '3', '3', '360829', '36', '安福县', '江西省', '吉安市', '安福县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3824, '3', '3', '360830', '36', '永新县', '江西省', '吉安市', '永新县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3825, '3', '3', '360881', '36', '井冈山市', '江西省', '吉安市', '井冈山市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3826, '2', '3', '360900', '36', '宜春市', '江西省', '宜春市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3827, '3', '3', '360901', '36', '市辖区', '江西省', '宜春市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3828, '3', '3', '360902', '36', '袁州区', '江西省', '宜春市', '袁州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3829, '3', '3', '360921', '36', '奉新县', '江西省', '宜春市', '奉新县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3830, '3', '3', '360922', '36', '万载县', '江西省', '宜春市', '万载县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3831, '3', '3', '360923', '36', '上高县', '江西省', '宜春市', '上高县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3832, '3', '3', '360924', '36', '宜丰县', '江西省', '宜春市', '宜丰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3833, '3', '3', '360925', '36', '靖安县', '江西省', '宜春市', '靖安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3834, '3', '3', '360926', '36', '铜鼓县', '江西省', '宜春市', '铜鼓县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3835, '3', '3', '360981', '36', '丰城市', '江西省', '宜春市', '丰城市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3836, '3', '3', '360982', '36', '樟树市', '江西省', '宜春市', '樟树市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3837, '3', '3', '360983', '36', '高安市', '江西省', '宜春市', '高安市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3838, '2', '3', '361000', '36', '抚州市', '江西省', '抚州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3839, '3', '3', '361001', '36', '市辖区', '江西省', '抚州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3840, '3', '3', '361002', '36', '临川区', '江西省', '抚州市', '临川区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3841, '3', '3', '361021', '36', '南城县', '江西省', '抚州市', '南城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3842, '3', '3', '361022', '36', '黎川县', '江西省', '抚州市', '黎川县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3843, '3', '3', '361023', '36', '南丰县', '江西省', '抚州市', '南丰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3844, '3', '3', '361024', '36', '崇仁县', '江西省', '抚州市', '崇仁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3845, '3', '3', '361025', '36', '乐安县', '江西省', '抚州市', '乐安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3846, '3', '3', '361026', '36', '宜黄县', '江西省', '抚州市', '宜黄县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3847, '3', '3', '361027', '36', '金溪县', '江西省', '抚州市', '金溪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3848, '3', '3', '361028', '36', '资溪县', '江西省', '抚州市', '资溪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3849, '3', '3', '361029', '36', '东乡县', '江西省', '抚州市', '东乡县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3850, '3', '3', '361030', '36', '广昌县', '江西省', '抚州市', '广昌县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3851, '2', '3', '361100', '36', '上饶市', '江西省', '上饶市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3852, '3', '3', '361101', '36', '市辖区', '江西省', '上饶市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3853, '3', '3', '361102', '36', '信州区', '江西省', '上饶市', '信州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3854, '3', '3', '361103', '36', '广丰区', '江西省', '上饶市', '广丰区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3855, '3', '3', '361121', '36', '上饶县', '江西省', '上饶市', '上饶县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3856, '3', '3', '361123', '36', '玉山县', '江西省', '上饶市', '玉山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3857, '3', '3', '361124', '36', '铅山县', '江西省', '上饶市', '铅山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3858, '3', '3', '361125', '36', '横峰县', '江西省', '上饶市', '横峰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3859, '3', '3', '361126', '36', '弋阳县', '江西省', '上饶市', '弋阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3860, '3', '3', '361127', '36', '余干县', '江西省', '上饶市', '余干县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3861, '3', '3', '361128', '36', '鄱阳县', '江西省', '上饶市', '鄱阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3862, '3', '3', '361129', '36', '万年县', '江西省', '上饶市', '万年县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3863, '3', '3', '361130', '36', '婺源县', '江西省', '上饶市', '婺源县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3864, '3', '3', '361181', '36', '德兴市', '江西省', '上饶市', '德兴市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3865, '1', '3', '370000', '37', '山东省', '山东省', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3866, '2', '3', '370100', '37', '济南市', '山东省', '济南市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3867, '3', '3', '370101', '37', '市辖区', '山东省', '济南市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3868, '3', '3', '370102', '37', '历下区', '山东省', '济南市', '历下区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3869, '3', '3', '370103', '37', '市中区', '山东省', '济南市', '市中区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3870, '3', '3', '370104', '37', '槐荫区', '山东省', '济南市', '槐荫区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3871, '3', '3', '370105', '37', '天桥区', '山东省', '济南市', '天桥区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3872, '3', '3', '370112', '37', '历城区', '山东省', '济南市', '历城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3873, '3', '3', '370113', '37', '长清区', '山东省', '济南市', '长清区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3874, '3', '3', '370124', '37', '平阴县', '山东省', '济南市', '平阴县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3875, '3', '3', '370125', '37', '济阳县', '山东省', '济南市', '济阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3876, '3', '3', '370126', '37', '商河县', '山东省', '济南市', '商河县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3877, '3', '3', '370181', '37', '章丘市', '山东省', '济南市', '章丘市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3878, '2', '3', '370200', '37', '青岛市', '山东省', '青岛市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3879, '3', '3', '370201', '37', '市辖区', '山东省', '青岛市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3880, '3', '3', '370202', '37', '市南区', '山东省', '青岛市', '市南区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3881, '3', '3', '370203', '37', '市北区', '山东省', '青岛市', '市北区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3882, '3', '3', '370211', '37', '黄岛区', '山东省', '青岛市', '黄岛区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3883, '3', '3', '370212', '37', '崂山区', '山东省', '青岛市', '崂山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3884, '3', '3', '370213', '37', '李沧区', '山东省', '青岛市', '李沧区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3885, '3', '3', '370214', '37', '城阳区', '山东省', '青岛市', '城阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3886, '3', '3', '370281', '37', '胶州市', '山东省', '青岛市', '胶州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3887, '3', '3', '370282', '37', '即墨市', '山东省', '青岛市', '即墨市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3888, '3', '3', '370283', '37', '平度市', '山东省', '青岛市', '平度市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3889, '3', '3', '370285', '37', '莱西市', '山东省', '青岛市', '莱西市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3890, '2', '3', '370300', '37', '淄博市', '山东省', '淄博市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3891, '3', '3', '370301', '37', '市辖区', '山东省', '淄博市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3892, '3', '3', '370302', '37', '淄川区', '山东省', '淄博市', '淄川区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3893, '3', '3', '370303', '37', '张店区', '山东省', '淄博市', '张店区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3894, '3', '3', '370304', '37', '博山区', '山东省', '淄博市', '博山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3895, '3', '3', '370305', '37', '临淄区', '山东省', '淄博市', '临淄区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3896, '3', '3', '370306', '37', '周村区', '山东省', '淄博市', '周村区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3897, '3', '3', '370321', '37', '桓台县', '山东省', '淄博市', '桓台县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3898, '3', '3', '370322', '37', '高青县', '山东省', '淄博市', '高青县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3899, '3', '3', '370323', '37', '沂源县', '山东省', '淄博市', '沂源县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3900, '2', '3', '370400', '37', '枣庄市', '山东省', '枣庄市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3901, '3', '3', '370401', '37', '市辖区', '山东省', '枣庄市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3902, '3', '3', '370402', '37', '市中区', '山东省', '枣庄市', '市中区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3903, '3', '3', '370403', '37', '薛城区', '山东省', '枣庄市', '薛城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3904, '3', '3', '370404', '37', '峄城区', '山东省', '枣庄市', '峄城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3905, '3', '3', '370405', '37', '台儿庄区', '山东省', '枣庄市', '台儿庄区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3906, '3', '3', '370406', '37', '山亭区', '山东省', '枣庄市', '山亭区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3907, '3', '3', '370481', '37', '滕州市', '山东省', '枣庄市', '滕州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3908, '2', '3', '370500', '37', '东营市', '山东省', '东营市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3909, '3', '3', '370501', '37', '市辖区', '山东省', '东营市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3910, '3', '3', '370502', '37', '东营区', '山东省', '东营市', '东营区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3911, '3', '3', '370503', '37', '河口区', '山东省', '东营市', '河口区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3912, '3', '3', '370521', '37', '垦利县', '山东省', '东营市', '垦利县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3913, '3', '3', '370522', '37', '利津县', '山东省', '东营市', '利津县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3914, '3', '3', '370523', '37', '广饶县', '山东省', '东营市', '广饶县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3915, '2', '3', '370600', '37', '烟台市', '山东省', '烟台市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3916, '3', '3', '370601', '37', '市辖区', '山东省', '烟台市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3917, '3', '3', '370602', '37', '芝罘区', '山东省', '烟台市', '芝罘区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3918, '3', '3', '370611', '37', '福山区', '山东省', '烟台市', '福山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3919, '3', '3', '370612', '37', '牟平区', '山东省', '烟台市', '牟平区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3920, '3', '3', '370613', '37', '莱山区', '山东省', '烟台市', '莱山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3921, '3', '3', '370634', '37', '长岛县', '山东省', '烟台市', '长岛县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3922, '3', '3', '370681', '37', '龙口市', '山东省', '烟台市', '龙口市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3923, '3', '3', '370682', '37', '莱阳市', '山东省', '烟台市', '莱阳市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3924, '3', '3', '370683', '37', '莱州市', '山东省', '烟台市', '莱州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3925, '3', '3', '370684', '37', '蓬莱市', '山东省', '烟台市', '蓬莱市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3926, '3', '3', '370685', '37', '招远市', '山东省', '烟台市', '招远市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3927, '3', '3', '370686', '37', '栖霞市', '山东省', '烟台市', '栖霞市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3928, '3', '3', '370687', '37', '海阳市', '山东省', '烟台市', '海阳市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3929, '2', '3', '370700', '37', '潍坊市', '山东省', '潍坊市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3930, '3', '3', '370701', '37', '市辖区', '山东省', '潍坊市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3931, '3', '3', '370702', '37', '潍城区', '山东省', '潍坊市', '潍城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3932, '3', '3', '370703', '37', '寒亭区', '山东省', '潍坊市', '寒亭区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3933, '3', '3', '370704', '37', '坊子区', '山东省', '潍坊市', '坊子区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3934, '3', '3', '370705', '37', '奎文区', '山东省', '潍坊市', '奎文区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3935, '3', '3', '370724', '37', '临朐县', '山东省', '潍坊市', '临朐县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3936, '3', '3', '370725', '37', '昌乐县', '山东省', '潍坊市', '昌乐县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3937, '3', '3', '370781', '37', '青州市', '山东省', '潍坊市', '青州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3938, '3', '3', '370782', '37', '诸城市', '山东省', '潍坊市', '诸城市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3939, '3', '3', '370783', '37', '寿光市', '山东省', '潍坊市', '寿光市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3940, '3', '3', '370784', '37', '安丘市', '山东省', '潍坊市', '安丘市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3941, '3', '3', '370785', '37', '高密市', '山东省', '潍坊市', '高密市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3942, '3', '3', '370786', '37', '昌邑市', '山东省', '潍坊市', '昌邑市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3943, '2', '3', '370800', '37', '济宁市', '山东省', '济宁市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3944, '3', '3', '370801', '37', '市辖区', '山东省', '济宁市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3945, '3', '3', '370811', '37', '任城区', '山东省', '济宁市', '任城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3946, '3', '3', '370812', '37', '兖州区', '山东省', '济宁市', '兖州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3947, '3', '3', '370826', '37', '微山县', '山东省', '济宁市', '微山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3948, '3', '3', '370827', '37', '鱼台县', '山东省', '济宁市', '鱼台县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3949, '3', '3', '370828', '37', '金乡县', '山东省', '济宁市', '金乡县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3950, '3', '3', '370829', '37', '嘉祥县', '山东省', '济宁市', '嘉祥县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3951, '3', '3', '370830', '37', '汶上县', '山东省', '济宁市', '汶上县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3952, '3', '3', '370831', '37', '泗水县', '山东省', '济宁市', '泗水县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3953, '3', '3', '370832', '37', '梁山县', '山东省', '济宁市', '梁山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3954, '3', '3', '370881', '37', '曲阜市', '山东省', '济宁市', '曲阜市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3955, '3', '3', '370883', '37', '邹城市', '山东省', '济宁市', '邹城市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3956, '2', '3', '370900', '37', '泰安市', '山东省', '泰安市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3957, '3', '3', '370901', '37', '市辖区', '山东省', '泰安市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3958, '3', '3', '370902', '37', '泰山区', '山东省', '泰安市', '泰山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3959, '3', '3', '370911', '37', '岱岳区', '山东省', '泰安市', '岱岳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3960, '3', '3', '370921', '37', '宁阳县', '山东省', '泰安市', '宁阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3961, '3', '3', '370923', '37', '东平县', '山东省', '泰安市', '东平县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3962, '3', '3', '370982', '37', '新泰市', '山东省', '泰安市', '新泰市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3963, '3', '3', '370983', '37', '肥城市', '山东省', '泰安市', '肥城市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3964, '2', '3', '371000', '37', '威海市', '山东省', '威海市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3965, '3', '3', '371001', '37', '市辖区', '山东省', '威海市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3966, '3', '3', '371002', '37', '环翠区', '山东省', '威海市', '环翠区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3967, '3', '3', '371003', '37', '文登区', '山东省', '威海市', '文登区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3968, '3', '3', '371082', '37', '荣成市', '山东省', '威海市', '荣成市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3969, '3', '3', '371083', '37', '乳山市', '山东省', '威海市', '乳山市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3970, '2', '3', '371100', '37', '日照市', '山东省', '日照市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3971, '3', '3', '371101', '37', '市辖区', '山东省', '日照市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3972, '3', '3', '371102', '37', '东港区', '山东省', '日照市', '东港区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3973, '3', '3', '371103', '37', '岚山区', '山东省', '日照市', '岚山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3974, '3', '3', '371121', '37', '五莲县', '山东省', '日照市', '五莲县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3975, '3', '3', '371122', '37', '莒县', '山东省', '日照市', '莒县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3976, '2', '3', '371200', '37', '莱芜市', '山东省', '莱芜市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3977, '3', '3', '371201', '37', '市辖区', '山东省', '莱芜市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3978, '3', '3', '371202', '37', '莱城区', '山东省', '莱芜市', '莱城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3979, '3', '3', '371203', '37', '钢城区', '山东省', '莱芜市', '钢城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3980, '2', '3', '371300', '37', '临沂市', '山东省', '临沂市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3981, '3', '3', '371301', '37', '市辖区', '山东省', '临沂市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3982, '3', '3', '371302', '37', '兰山区', '山东省', '临沂市', '兰山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3983, '3', '3', '371311', '37', '罗庄区', '山东省', '临沂市', '罗庄区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3984, '3', '3', '371312', '37', '河东区', '山东省', '临沂市', '河东区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3985, '3', '3', '371321', '37', '沂南县', '山东省', '临沂市', '沂南县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3986, '3', '3', '371322', '37', '郯城县', '山东省', '临沂市', '郯城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3987, '3', '3', '371323', '37', '沂水县', '山东省', '临沂市', '沂水县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3988, '3', '3', '371324', '37', '兰陵县', '山东省', '临沂市', '兰陵县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3989, '3', '3', '371325', '37', '费县', '山东省', '临沂市', '费县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3990, '3', '3', '371326', '37', '平邑县', '山东省', '临沂市', '平邑县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3991, '3', '3', '371327', '37', '莒南县', '山东省', '临沂市', '莒南县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3992, '3', '3', '371328', '37', '蒙阴县', '山东省', '临沂市', '蒙阴县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3993, '3', '3', '371329', '37', '临沭县', '山东省', '临沂市', '临沭县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3994, '2', '3', '371400', '37', '德州市', '山东省', '德州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3995, '3', '3', '371401', '37', '市辖区', '山东省', '德州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3996, '3', '3', '371402', '37', '德城区', '山东省', '德州市', '德城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3997, '3', '3', '371403', '37', '陵城区', '山东省', '德州市', '陵城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3998, '3', '3', '371422', '37', '宁津县', '山东省', '德州市', '宁津县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (3999, '3', '3', '371423', '37', '庆云县', '山东省', '德州市', '庆云县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4000, '3', '3', '371424', '37', '临邑县', '山东省', '德州市', '临邑县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4001, '3', '3', '371425', '37', '齐河县', '山东省', '德州市', '齐河县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4002, '3', '3', '371426', '37', '平原县', '山东省', '德州市', '平原县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4003, '3', '3', '371427', '37', '夏津县', '山东省', '德州市', '夏津县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4004, '3', '3', '371428', '37', '武城县', '山东省', '德州市', '武城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4005, '3', '3', '371481', '37', '乐陵市', '山东省', '德州市', '乐陵市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4006, '3', '3', '371482', '37', '禹城市', '山东省', '德州市', '禹城市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4007, '2', '3', '371500', '37', '聊城市', '山东省', '聊城市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4008, '3', '3', '371501', '37', '市辖区', '山东省', '聊城市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4009, '3', '3', '371502', '37', '东昌府区', '山东省', '聊城市', '东昌府区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4010, '3', '3', '371521', '37', '阳谷县', '山东省', '聊城市', '阳谷县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4011, '3', '3', '371522', '37', '莘县', '山东省', '聊城市', '莘县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4012, '3', '3', '371523', '37', '茌平县', '山东省', '聊城市', '茌平县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4013, '3', '3', '371524', '37', '东阿县', '山东省', '聊城市', '东阿县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4014, '3', '3', '371525', '37', '冠县', '山东省', '聊城市', '冠县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4015, '3', '3', '371526', '37', '高唐县', '山东省', '聊城市', '高唐县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4016, '3', '3', '371581', '37', '临清市', '山东省', '聊城市', '临清市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4017, '2', '3', '371600', '37', '滨州市', '山东省', '滨州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4018, '3', '3', '371601', '37', '市辖区', '山东省', '滨州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4019, '3', '3', '371602', '37', '滨城区', '山东省', '滨州市', '滨城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4020, '3', '3', '371603', '37', '沾化区', '山东省', '滨州市', '沾化区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4021, '3', '3', '371621', '37', '惠民县', '山东省', '滨州市', '惠民县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4022, '3', '3', '371622', '37', '阳信县', '山东省', '滨州市', '阳信县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4023, '3', '3', '371623', '37', '无棣县', '山东省', '滨州市', '无棣县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4024, '3', '3', '371625', '37', '博兴县', '山东省', '滨州市', '博兴县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4025, '3', '3', '371626', '37', '邹平县', '山东省', '滨州市', '邹平县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4026, '2', '3', '371700', '37', '菏泽市', '山东省', '菏泽市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4027, '3', '3', '371701', '37', '市辖区', '山东省', '菏泽市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4028, '3', '3', '371702', '37', '牡丹区', '山东省', '菏泽市', '牡丹区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4029, '3', '3', '371721', '37', '曹县', '山东省', '菏泽市', '曹县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4030, '3', '3', '371722', '37', '单县', '山东省', '菏泽市', '单县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4031, '3', '3', '371723', '37', '成武县', '山东省', '菏泽市', '成武县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4032, '3', '3', '371724', '37', '巨野县', '山东省', '菏泽市', '巨野县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4033, '3', '3', '371725', '37', '郓城县', '山东省', '菏泽市', '郓城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4034, '3', '3', '371726', '37', '鄄城县', '山东省', '菏泽市', '鄄城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4035, '3', '3', '371727', '37', '定陶县', '山东省', '菏泽市', '定陶县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4036, '3', '3', '371728', '37', '东明县', '山东省', '菏泽市', '东明县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4037, '1', '4', '410000', '41', '河南省', '河南省', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4038, '2', '4', '410100', '41', '郑州市', '河南省', '郑州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4039, '3', '4', '410101', '41', '市辖区', '河南省', '郑州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4040, '3', '4', '410102', '41', '中原区', '河南省', '郑州市', '中原区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4041, '3', '4', '410103', '41', '二七区', '河南省', '郑州市', '二七区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4042, '3', '4', '410104', '41', '管城回族区', '河南省', '郑州市', '管城回族区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4043, '3', '4', '410105', '41', '金水区', '河南省', '郑州市', '金水区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4044, '3', '4', '410106', '41', '上街区', '河南省', '郑州市', '上街区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4045, '3', '4', '410108', '41', '惠济区', '河南省', '郑州市', '惠济区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4046, '3', '4', '410122', '41', '中牟县', '河南省', '郑州市', '中牟县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4047, '3', '4', '410181', '41', '巩义市', '河南省', '郑州市', '巩义市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4048, '3', '4', '410182', '41', '荥阳市', '河南省', '郑州市', '荥阳市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4049, '3', '4', '410183', '41', '新密市', '河南省', '郑州市', '新密市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4050, '3', '4', '410184', '41', '新郑市', '河南省', '郑州市', '新郑市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4051, '3', '4', '410185', '41', '登封市', '河南省', '郑州市', '登封市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4052, '2', '4', '410200', '41', '开封市', '河南省', '开封市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4053, '3', '4', '410201', '41', '市辖区', '河南省', '开封市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4054, '3', '4', '410202', '41', '龙亭区', '河南省', '开封市', '龙亭区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4055, '3', '4', '410203', '41', '顺河回族区', '河南省', '开封市', '顺河回族区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4056, '3', '4', '410204', '41', '鼓楼区', '河南省', '开封市', '鼓楼区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4057, '3', '4', '410205', '41', '禹王台区', '河南省', '开封市', '禹王台区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4058, '3', '4', '410211', '41', '金明区', '河南省', '开封市', '金明区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4059, '3', '4', '410212', '41', '祥符区', '河南省', '开封市', '祥符区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4060, '3', '4', '410221', '41', '杞县', '河南省', '开封市', '杞县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4061, '3', '4', '410222', '41', '通许县', '河南省', '开封市', '通许县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4062, '3', '4', '410223', '41', '尉氏县', '河南省', '开封市', '尉氏县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4063, '3', '4', '410225', '41', '兰考县', '河南省', '开封市', '兰考县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4064, '2', '4', '410300', '41', '洛阳市', '河南省', '洛阳市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4065, '3', '4', '410301', '41', '市辖区', '河南省', '洛阳市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4066, '3', '4', '410302', '41', '老城区', '河南省', '洛阳市', '老城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4067, '3', '4', '410303', '41', '西工区', '河南省', '洛阳市', '西工区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4068, '3', '4', '410304', '41', '瀍河回族区', '河南省', '洛阳市', '瀍河回族区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4069, '3', '4', '410305', '41', '涧西区', '河南省', '洛阳市', '涧西区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4070, '3', '4', '410306', '41', '吉利区', '河南省', '洛阳市', '吉利区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4071, '3', '4', '410311', '41', '洛龙区', '河南省', '洛阳市', '洛龙区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4072, '3', '4', '410322', '41', '孟津县', '河南省', '洛阳市', '孟津县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4073, '3', '4', '410323', '41', '新安县', '河南省', '洛阳市', '新安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4074, '3', '4', '410324', '41', '栾川县', '河南省', '洛阳市', '栾川县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4075, '3', '4', '410325', '41', '嵩县', '河南省', '洛阳市', '嵩县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4076, '3', '4', '410326', '41', '汝阳县', '河南省', '洛阳市', '汝阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4077, '3', '4', '410327', '41', '宜阳县', '河南省', '洛阳市', '宜阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4078, '3', '4', '410328', '41', '洛宁县', '河南省', '洛阳市', '洛宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4079, '3', '4', '410329', '41', '伊川县', '河南省', '洛阳市', '伊川县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4080, '3', '4', '410381', '41', '偃师市', '河南省', '洛阳市', '偃师市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4081, '2', '4', '410400', '41', '平顶山市', '河南省', '平顶山市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4082, '3', '4', '410401', '41', '市辖区', '河南省', '平顶山市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4083, '3', '4', '410402', '41', '新华区', '河南省', '平顶山市', '新华区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4084, '3', '4', '410403', '41', '卫东区', '河南省', '平顶山市', '卫东区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4085, '3', '4', '410404', '41', '石龙区', '河南省', '平顶山市', '石龙区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4086, '3', '4', '410411', '41', '湛河区', '河南省', '平顶山市', '湛河区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4087, '3', '4', '410421', '41', '宝丰县', '河南省', '平顶山市', '宝丰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4088, '3', '4', '410422', '41', '叶县', '河南省', '平顶山市', '叶县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4089, '3', '4', '410423', '41', '鲁山县', '河南省', '平顶山市', '鲁山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4090, '3', '4', '410425', '41', '郏县', '河南省', '平顶山市', '郏县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4091, '3', '4', '410481', '41', '舞钢市', '河南省', '平顶山市', '舞钢市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4092, '3', '4', '410482', '41', '汝州市', '河南省', '平顶山市', '汝州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4093, '2', '4', '410500', '41', '安阳市', '河南省', '安阳市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4094, '3', '4', '410501', '41', '市辖区', '河南省', '安阳市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4095, '3', '4', '410502', '41', '文峰区', '河南省', '安阳市', '文峰区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4096, '3', '4', '410503', '41', '北关区', '河南省', '安阳市', '北关区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4097, '3', '4', '410505', '41', '殷都区', '河南省', '安阳市', '殷都区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4098, '3', '4', '410506', '41', '龙安区', '河南省', '安阳市', '龙安区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4099, '3', '4', '410522', '41', '安阳县', '河南省', '安阳市', '安阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4100, '3', '4', '410523', '41', '汤阴县', '河南省', '安阳市', '汤阴县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4101, '3', '4', '410526', '41', '滑县', '河南省', '安阳市', '滑县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4102, '3', '4', '410527', '41', '内黄县', '河南省', '安阳市', '内黄县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4103, '3', '4', '410581', '41', '林州市', '河南省', '安阳市', '林州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4104, '2', '4', '410600', '41', '鹤壁市', '河南省', '鹤壁市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4105, '3', '4', '410601', '41', '市辖区', '河南省', '鹤壁市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4106, '3', '4', '410602', '41', '鹤山区', '河南省', '鹤壁市', '鹤山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4107, '3', '4', '410603', '41', '山城区', '河南省', '鹤壁市', '山城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4108, '3', '4', '410611', '41', '淇滨区', '河南省', '鹤壁市', '淇滨区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4109, '3', '4', '410621', '41', '浚县', '河南省', '鹤壁市', '浚县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4110, '3', '4', '410622', '41', '淇县', '河南省', '鹤壁市', '淇县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4111, '2', '4', '410700', '41', '新乡市', '河南省', '新乡市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4112, '3', '4', '410701', '41', '市辖区', '河南省', '新乡市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4113, '3', '4', '410702', '41', '红旗区', '河南省', '新乡市', '红旗区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4114, '3', '4', '410703', '41', '卫滨区', '河南省', '新乡市', '卫滨区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4115, '3', '4', '410704', '41', '凤泉区', '河南省', '新乡市', '凤泉区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4116, '3', '4', '410711', '41', '牧野区', '河南省', '新乡市', '牧野区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4117, '3', '4', '410721', '41', '新乡县', '河南省', '新乡市', '新乡县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4118, '3', '4', '410724', '41', '获嘉县', '河南省', '新乡市', '获嘉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4119, '3', '4', '410725', '41', '原阳县', '河南省', '新乡市', '原阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4120, '3', '4', '410726', '41', '延津县', '河南省', '新乡市', '延津县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4121, '3', '4', '410727', '41', '封丘县', '河南省', '新乡市', '封丘县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4122, '3', '4', '410728', '41', '长垣县', '河南省', '新乡市', '长垣县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4123, '3', '4', '410781', '41', '卫辉市', '河南省', '新乡市', '卫辉市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4124, '3', '4', '410782', '41', '辉县市', '河南省', '新乡市', '辉县市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4125, '2', '4', '410800', '41', '焦作市', '河南省', '焦作市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4126, '3', '4', '410801', '41', '市辖区', '河南省', '焦作市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4127, '3', '4', '410802', '41', '解放区', '河南省', '焦作市', '解放区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4128, '3', '4', '410803', '41', '中站区', '河南省', '焦作市', '中站区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4129, '3', '4', '410804', '41', '马村区', '河南省', '焦作市', '马村区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4130, '3', '4', '410811', '41', '山阳区', '河南省', '焦作市', '山阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4131, '3', '4', '410821', '41', '修武县', '河南省', '焦作市', '修武县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4132, '3', '4', '410822', '41', '博爱县', '河南省', '焦作市', '博爱县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4133, '3', '4', '410823', '41', '武陟县', '河南省', '焦作市', '武陟县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4134, '3', '4', '410825', '41', '温县', '河南省', '焦作市', '温县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4135, '3', '4', '410882', '41', '沁阳市', '河南省', '焦作市', '沁阳市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4136, '3', '4', '410883', '41', '孟州市', '河南省', '焦作市', '孟州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4137, '2', '4', '410900', '41', '濮阳市', '河南省', '濮阳市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4138, '3', '4', '410901', '41', '市辖区', '河南省', '濮阳市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4139, '3', '4', '410902', '41', '华龙区', '河南省', '濮阳市', '华龙区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4140, '3', '4', '410922', '41', '清丰县', '河南省', '濮阳市', '清丰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4141, '3', '4', '410923', '41', '南乐县', '河南省', '濮阳市', '南乐县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4142, '3', '4', '410926', '41', '范县', '河南省', '濮阳市', '范县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4143, '3', '4', '410927', '41', '台前县', '河南省', '濮阳市', '台前县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4144, '3', '4', '410928', '41', '濮阳县', '河南省', '濮阳市', '濮阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4145, '2', '4', '411000', '41', '许昌市', '河南省', '许昌市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4146, '3', '4', '411001', '41', '市辖区', '河南省', '许昌市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4147, '3', '4', '411002', '41', '魏都区', '河南省', '许昌市', '魏都区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4148, '3', '4', '411023', '41', '许昌县', '河南省', '许昌市', '许昌县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4149, '3', '4', '411024', '41', '鄢陵县', '河南省', '许昌市', '鄢陵县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4150, '3', '4', '411025', '41', '襄城县', '河南省', '许昌市', '襄城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4151, '3', '4', '411081', '41', '禹州市', '河南省', '许昌市', '禹州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4152, '3', '4', '411082', '41', '长葛市', '河南省', '许昌市', '长葛市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4153, '2', '4', '411100', '41', '漯河市', '河南省', '漯河市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4154, '3', '4', '411101', '41', '市辖区', '河南省', '漯河市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4155, '3', '4', '411102', '41', '源汇区', '河南省', '漯河市', '源汇区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4156, '3', '4', '411103', '41', '郾城区', '河南省', '漯河市', '郾城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4157, '3', '4', '411104', '41', '召陵区', '河南省', '漯河市', '召陵区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4158, '3', '4', '411121', '41', '舞阳县', '河南省', '漯河市', '舞阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4159, '3', '4', '411122', '41', '临颍县', '河南省', '漯河市', '临颍县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4160, '2', '4', '411200', '41', '三门峡市', '河南省', '三门峡市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4161, '3', '4', '411201', '41', '市辖区', '河南省', '三门峡市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4162, '3', '4', '411202', '41', '湖滨区', '河南省', '三门峡市', '湖滨区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4163, '3', '4', '411221', '41', '渑池县', '河南省', '三门峡市', '渑池县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4164, '3', '4', '411222', '41', '陕县', '河南省', '三门峡市', '陕县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4165, '3', '4', '411224', '41', '卢氏县', '河南省', '三门峡市', '卢氏县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4166, '3', '4', '411281', '41', '义马市', '河南省', '三门峡市', '义马市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4167, '3', '4', '411282', '41', '灵宝市', '河南省', '三门峡市', '灵宝市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4168, '2', '4', '411300', '41', '南阳市', '河南省', '南阳市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4169, '3', '4', '411301', '41', '市辖区', '河南省', '南阳市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4170, '3', '4', '411302', '41', '宛城区', '河南省', '南阳市', '宛城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4171, '3', '4', '411303', '41', '卧龙区', '河南省', '南阳市', '卧龙区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4172, '3', '4', '411321', '41', '南召县', '河南省', '南阳市', '南召县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4173, '3', '4', '411322', '41', '方城县', '河南省', '南阳市', '方城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4174, '3', '4', '411323', '41', '西峡县', '河南省', '南阳市', '西峡县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4175, '3', '4', '411324', '41', '镇平县', '河南省', '南阳市', '镇平县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4176, '3', '4', '411325', '41', '内乡县', '河南省', '南阳市', '内乡县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4177, '3', '4', '411326', '41', '淅川县', '河南省', '南阳市', '淅川县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4178, '3', '4', '411327', '41', '社旗县', '河南省', '南阳市', '社旗县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4179, '3', '4', '411328', '41', '唐河县', '河南省', '南阳市', '唐河县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4180, '3', '4', '411329', '41', '新野县', '河南省', '南阳市', '新野县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4181, '3', '4', '411330', '41', '桐柏县', '河南省', '南阳市', '桐柏县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4182, '3', '4', '411381', '41', '邓州市', '河南省', '南阳市', '邓州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4183, '2', '4', '411400', '41', '商丘市', '河南省', '商丘市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4184, '3', '4', '411401', '41', '市辖区', '河南省', '商丘市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4185, '3', '4', '411402', '41', '梁园区', '河南省', '商丘市', '梁园区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4186, '3', '4', '411403', '41', '睢阳区', '河南省', '商丘市', '睢阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4187, '3', '4', '411421', '41', '民权县', '河南省', '商丘市', '民权县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4188, '3', '4', '411422', '41', '睢县', '河南省', '商丘市', '睢县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4189, '3', '4', '411423', '41', '宁陵县', '河南省', '商丘市', '宁陵县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4190, '3', '4', '411424', '41', '柘城县', '河南省', '商丘市', '柘城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4191, '3', '4', '411425', '41', '虞城县', '河南省', '商丘市', '虞城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4192, '3', '4', '411426', '41', '夏邑县', '河南省', '商丘市', '夏邑县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4193, '3', '4', '411481', '41', '永城市', '河南省', '商丘市', '永城市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4194, '2', '4', '411500', '41', '信阳市', '河南省', '信阳市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4195, '3', '4', '411501', '41', '市辖区', '河南省', '信阳市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4196, '3', '4', '411502', '41', '浉河区', '河南省', '信阳市', '浉河区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4197, '3', '4', '411503', '41', '平桥区', '河南省', '信阳市', '平桥区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4198, '3', '4', '411521', '41', '罗山县', '河南省', '信阳市', '罗山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4199, '3', '4', '411522', '41', '光山县', '河南省', '信阳市', '光山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4200, '3', '4', '411523', '41', '新县', '河南省', '信阳市', '新县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4201, '3', '4', '411524', '41', '商城县', '河南省', '信阳市', '商城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4202, '3', '4', '411525', '41', '固始县', '河南省', '信阳市', '固始县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4203, '3', '4', '411526', '41', '潢川县', '河南省', '信阳市', '潢川县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4204, '3', '4', '411527', '41', '淮滨县', '河南省', '信阳市', '淮滨县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4205, '3', '4', '411528', '41', '息县', '河南省', '信阳市', '息县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4206, '2', '4', '411600', '41', '周口市', '河南省', '周口市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4207, '3', '4', '411601', '41', '市辖区', '河南省', '周口市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4208, '3', '4', '411602', '41', '川汇区', '河南省', '周口市', '川汇区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4209, '3', '4', '411621', '41', '扶沟县', '河南省', '周口市', '扶沟县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4210, '3', '4', '411622', '41', '西华县', '河南省', '周口市', '西华县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4211, '3', '4', '411623', '41', '商水县', '河南省', '周口市', '商水县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4212, '3', '4', '411624', '41', '沈丘县', '河南省', '周口市', '沈丘县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4213, '3', '4', '411625', '41', '郸城县', '河南省', '周口市', '郸城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4214, '3', '4', '411626', '41', '淮阳县', '河南省', '周口市', '淮阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4215, '3', '4', '411627', '41', '太康县', '河南省', '周口市', '太康县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4216, '3', '4', '411628', '41', '鹿邑县', '河南省', '周口市', '鹿邑县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4217, '3', '4', '411681', '41', '项城市', '河南省', '周口市', '项城市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4218, '2', '4', '411700', '41', '驻马店市', '河南省', '驻马店市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4219, '3', '4', '411701', '41', '市辖区', '河南省', '驻马店市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4220, '3', '4', '411702', '41', '驿城区', '河南省', '驻马店市', '驿城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4221, '3', '4', '411721', '41', '西平县', '河南省', '驻马店市', '西平县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4222, '3', '4', '411722', '41', '上蔡县', '河南省', '驻马店市', '上蔡县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4223, '3', '4', '411723', '41', '平舆县', '河南省', '驻马店市', '平舆县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4224, '3', '4', '411724', '41', '正阳县', '河南省', '驻马店市', '正阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4225, '3', '4', '411725', '41', '确山县', '河南省', '驻马店市', '确山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4226, '3', '4', '411726', '41', '泌阳县', '河南省', '驻马店市', '泌阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4227, '3', '4', '411727', '41', '汝南县', '河南省', '驻马店市', '汝南县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4228, '3', '4', '411728', '41', '遂平县', '河南省', '驻马店市', '遂平县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4229, '3', '4', '411729', '41', '新蔡县', '河南省', '驻马店市', '新蔡县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4230, '2', '4', '419000', '41', '省直辖县级行政区划', '河南省', '省直辖县级行政区划', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4231, '3', '4', '419001', '41', '济源市', '河南省', '省直辖县级行政区划', '济源市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4232, '1', '4', '420000', '42', '湖北省', '湖北省', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4233, '2', '4', '420100', '42', '武汉市', '湖北省', '武汉市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4234, '3', '4', '420101', '42', '市辖区', '湖北省', '武汉市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4235, '3', '4', '420102', '42', '江岸区', '湖北省', '武汉市', '江岸区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4236, '3', '4', '420103', '42', '江汉区', '湖北省', '武汉市', '江汉区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4237, '3', '4', '420104', '42', '硚口区', '湖北省', '武汉市', '硚口区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4238, '3', '4', '420105', '42', '汉阳区', '湖北省', '武汉市', '汉阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4239, '3', '4', '420106', '42', '武昌区', '湖北省', '武汉市', '武昌区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4240, '3', '4', '420107', '42', '青山区', '湖北省', '武汉市', '青山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4241, '3', '4', '420111', '42', '洪山区', '湖北省', '武汉市', '洪山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4242, '3', '4', '420112', '42', '东西湖区', '湖北省', '武汉市', '东西湖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4243, '3', '4', '420113', '42', '汉南区', '湖北省', '武汉市', '汉南区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4244, '3', '4', '420114', '42', '蔡甸区', '湖北省', '武汉市', '蔡甸区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4245, '3', '4', '420115', '42', '江夏区', '湖北省', '武汉市', '江夏区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4246, '3', '4', '420116', '42', '黄陂区', '湖北省', '武汉市', '黄陂区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4247, '3', '4', '420117', '42', '新洲区', '湖北省', '武汉市', '新洲区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4248, '2', '4', '420200', '42', '黄石市', '湖北省', '黄石市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4249, '3', '4', '420201', '42', '市辖区', '湖北省', '黄石市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4250, '3', '4', '420202', '42', '黄石港区', '湖北省', '黄石市', '黄石港区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4251, '3', '4', '420203', '42', '西塞山区', '湖北省', '黄石市', '西塞山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4252, '3', '4', '420204', '42', '下陆区', '湖北省', '黄石市', '下陆区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4253, '3', '4', '420205', '42', '铁山区', '湖北省', '黄石市', '铁山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4254, '3', '4', '420222', '42', '阳新县', '湖北省', '黄石市', '阳新县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4255, '3', '4', '420281', '42', '大冶市', '湖北省', '黄石市', '大冶市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4256, '2', '4', '420300', '42', '十堰市', '湖北省', '十堰市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4257, '3', '4', '420301', '42', '市辖区', '湖北省', '十堰市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4258, '3', '4', '420302', '42', '茅箭区', '湖北省', '十堰市', '茅箭区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4259, '3', '4', '420303', '42', '张湾区', '湖北省', '十堰市', '张湾区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4260, '3', '4', '420304', '42', '郧阳区', '湖北省', '十堰市', '郧阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4261, '3', '4', '420322', '42', '郧西县', '湖北省', '十堰市', '郧西县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4262, '3', '4', '420323', '42', '竹山县', '湖北省', '十堰市', '竹山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4263, '3', '4', '420324', '42', '竹溪县', '湖北省', '十堰市', '竹溪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4264, '3', '4', '420325', '42', '房县', '湖北省', '十堰市', '房县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4265, '3', '4', '420381', '42', '丹江口市', '湖北省', '十堰市', '丹江口市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4266, '2', '4', '420500', '42', '宜昌市', '湖北省', '宜昌市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4267, '3', '4', '420501', '42', '市辖区', '湖北省', '宜昌市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4268, '3', '4', '420502', '42', '西陵区', '湖北省', '宜昌市', '西陵区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4269, '3', '4', '420503', '42', '伍家岗区', '湖北省', '宜昌市', '伍家岗区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4270, '3', '4', '420504', '42', '点军区', '湖北省', '宜昌市', '点军区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4271, '3', '4', '420505', '42', '猇亭区', '湖北省', '宜昌市', '猇亭区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4272, '3', '4', '420506', '42', '夷陵区', '湖北省', '宜昌市', '夷陵区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4273, '3', '4', '420525', '42', '远安县', '湖北省', '宜昌市', '远安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4274, '3', '4', '420526', '42', '兴山县', '湖北省', '宜昌市', '兴山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4275, '3', '4', '420527', '42', '秭归县', '湖北省', '宜昌市', '秭归县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4276, '3', '4', '420528', '42', '长阳土家族自治县', '湖北省', '宜昌市', '长阳土家族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4277, '3', '4', '420529', '42', '五峰土家族自治县', '湖北省', '宜昌市', '五峰土家族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4278, '3', '4', '420581', '42', '宜都市', '湖北省', '宜昌市', '宜都市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4279, '3', '4', '420582', '42', '当阳市', '湖北省', '宜昌市', '当阳市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4280, '3', '4', '420583', '42', '枝江市', '湖北省', '宜昌市', '枝江市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4281, '2', '4', '420600', '42', '襄阳市', '湖北省', '襄阳市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4282, '3', '4', '420601', '42', '市辖区', '湖北省', '襄阳市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4283, '3', '4', '420602', '42', '襄城区', '湖北省', '襄阳市', '襄城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4284, '3', '4', '420606', '42', '樊城区', '湖北省', '襄阳市', '樊城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4285, '3', '4', '420607', '42', '襄州区', '湖北省', '襄阳市', '襄州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4286, '3', '4', '420624', '42', '南漳县', '湖北省', '襄阳市', '南漳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4287, '3', '4', '420625', '42', '谷城县', '湖北省', '襄阳市', '谷城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4288, '3', '4', '420626', '42', '保康县', '湖北省', '襄阳市', '保康县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4289, '3', '4', '420682', '42', '老河口市', '湖北省', '襄阳市', '老河口市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4290, '3', '4', '420683', '42', '枣阳市', '湖北省', '襄阳市', '枣阳市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4291, '3', '4', '420684', '42', '宜城市', '湖北省', '襄阳市', '宜城市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4292, '2', '4', '420700', '42', '鄂州市', '湖北省', '鄂州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4293, '3', '4', '420701', '42', '市辖区', '湖北省', '鄂州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4294, '3', '4', '420702', '42', '梁子湖区', '湖北省', '鄂州市', '梁子湖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4295, '3', '4', '420703', '42', '华容区', '湖北省', '鄂州市', '华容区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4296, '3', '4', '420704', '42', '鄂城区', '湖北省', '鄂州市', '鄂城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4297, '2', '4', '420800', '42', '荆门市', '湖北省', '荆门市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4298, '3', '4', '420801', '42', '市辖区', '湖北省', '荆门市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4299, '3', '4', '420802', '42', '东宝区', '湖北省', '荆门市', '东宝区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4300, '3', '4', '420804', '42', '掇刀区', '湖北省', '荆门市', '掇刀区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4301, '3', '4', '420821', '42', '京山县', '湖北省', '荆门市', '京山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4302, '3', '4', '420822', '42', '沙洋县', '湖北省', '荆门市', '沙洋县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4303, '3', '4', '420881', '42', '钟祥市', '湖北省', '荆门市', '钟祥市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4304, '2', '4', '420900', '42', '孝感市', '湖北省', '孝感市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4305, '3', '4', '420901', '42', '市辖区', '湖北省', '孝感市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4306, '3', '4', '420902', '42', '孝南区', '湖北省', '孝感市', '孝南区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4307, '3', '4', '420921', '42', '孝昌县', '湖北省', '孝感市', '孝昌县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4308, '3', '4', '420922', '42', '大悟县', '湖北省', '孝感市', '大悟县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4309, '3', '4', '420923', '42', '云梦县', '湖北省', '孝感市', '云梦县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4310, '3', '4', '420981', '42', '应城市', '湖北省', '孝感市', '应城市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4311, '3', '4', '420982', '42', '安陆市', '湖北省', '孝感市', '安陆市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4312, '3', '4', '420984', '42', '汉川市', '湖北省', '孝感市', '汉川市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4313, '2', '4', '421000', '42', '荆州市', '湖北省', '荆州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4314, '3', '4', '421001', '42', '市辖区', '湖北省', '荆州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4315, '3', '4', '421002', '42', '沙市区', '湖北省', '荆州市', '沙市区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4316, '3', '4', '421003', '42', '荆州区', '湖北省', '荆州市', '荆州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4317, '3', '4', '421022', '42', '公安县', '湖北省', '荆州市', '公安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4318, '3', '4', '421023', '42', '监利县', '湖北省', '荆州市', '监利县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4319, '3', '4', '421024', '42', '江陵县', '湖北省', '荆州市', '江陵县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4320, '3', '4', '421081', '42', '石首市', '湖北省', '荆州市', '石首市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4321, '3', '4', '421083', '42', '洪湖市', '湖北省', '荆州市', '洪湖市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4322, '3', '4', '421087', '42', '松滋市', '湖北省', '荆州市', '松滋市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4323, '2', '4', '421100', '42', '黄冈市', '湖北省', '黄冈市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4324, '3', '4', '421101', '42', '市辖区', '湖北省', '黄冈市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4325, '3', '4', '421102', '42', '黄州区', '湖北省', '黄冈市', '黄州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4326, '3', '4', '421121', '42', '团风县', '湖北省', '黄冈市', '团风县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4327, '3', '4', '421122', '42', '红安县', '湖北省', '黄冈市', '红安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4328, '3', '4', '421123', '42', '罗田县', '湖北省', '黄冈市', '罗田县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4329, '3', '4', '421124', '42', '英山县', '湖北省', '黄冈市', '英山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4330, '3', '4', '421125', '42', '浠水县', '湖北省', '黄冈市', '浠水县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4331, '3', '4', '421126', '42', '蕲春县', '湖北省', '黄冈市', '蕲春县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4332, '3', '4', '421127', '42', '黄梅县', '湖北省', '黄冈市', '黄梅县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4333, '3', '4', '421181', '42', '麻城市', '湖北省', '黄冈市', '麻城市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4334, '3', '4', '421182', '42', '武穴市', '湖北省', '黄冈市', '武穴市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4335, '2', '4', '421200', '42', '咸宁市', '湖北省', '咸宁市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4336, '3', '4', '421201', '42', '市辖区', '湖北省', '咸宁市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4337, '3', '4', '421202', '42', '咸安区', '湖北省', '咸宁市', '咸安区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4338, '3', '4', '421221', '42', '嘉鱼县', '湖北省', '咸宁市', '嘉鱼县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4339, '3', '4', '421222', '42', '通城县', '湖北省', '咸宁市', '通城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4340, '3', '4', '421223', '42', '崇阳县', '湖北省', '咸宁市', '崇阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4341, '3', '4', '421224', '42', '通山县', '湖北省', '咸宁市', '通山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4342, '3', '4', '421281', '42', '赤壁市', '湖北省', '咸宁市', '赤壁市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4343, '2', '4', '421300', '42', '随州市', '湖北省', '随州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4344, '3', '4', '421301', '42', '市辖区', '湖北省', '随州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4345, '3', '4', '421303', '42', '曾都区', '湖北省', '随州市', '曾都区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4346, '3', '4', '421321', '42', '随县', '湖北省', '随州市', '随县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4347, '3', '4', '421381', '42', '广水市', '湖北省', '随州市', '广水市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4348, '2', '4', '422800', '42', '恩施土家族苗族自治州', '湖北省', '恩施土家族苗族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4349, '3', '4', '422801', '42', '恩施市', '湖北省', '恩施土家族苗族自治州', '恩施市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4350, '3', '4', '422802', '42', '利川市', '湖北省', '恩施土家族苗族自治州', '利川市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4351, '3', '4', '422822', '42', '建始县', '湖北省', '恩施土家族苗族自治州', '建始县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4352, '3', '4', '422823', '42', '巴东县', '湖北省', '恩施土家族苗族自治州', '巴东县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4353, '3', '4', '422825', '42', '宣恩县', '湖北省', '恩施土家族苗族自治州', '宣恩县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4354, '3', '4', '422826', '42', '咸丰县', '湖北省', '恩施土家族苗族自治州', '咸丰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4355, '3', '4', '422827', '42', '来凤县', '湖北省', '恩施土家族苗族自治州', '来凤县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4356, '3', '4', '422828', '42', '鹤峰县', '湖北省', '恩施土家族苗族自治州', '鹤峰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4357, '2', '4', '429000', '42', '省直辖县级行政区划', '湖北省', '省直辖县级行政区划', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4358, '3', '4', '429004', '42', '仙桃市', '湖北省', '省直辖县级行政区划', '仙桃市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4359, '3', '4', '429005', '42', '潜江市', '湖北省', '省直辖县级行政区划', '潜江市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4360, '3', '4', '429006', '42', '天门市', '湖北省', '省直辖县级行政区划', '天门市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4361, '3', '4', '429021', '42', '神农架林区', '湖北省', '省直辖县级行政区划', '神农架林区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4362, '1', '4', '430000', '43', '湖南省', '湖南省', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4363, '2', '4', '430100', '43', '长沙市', '湖南省', '长沙市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4364, '3', '4', '430101', '43', '市辖区', '湖南省', '长沙市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4365, '3', '4', '430102', '43', '芙蓉区', '湖南省', '长沙市', '芙蓉区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4366, '3', '4', '430103', '43', '天心区', '湖南省', '长沙市', '天心区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4367, '3', '4', '430104', '43', '岳麓区', '湖南省', '长沙市', '岳麓区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4368, '3', '4', '430105', '43', '开福区', '湖南省', '长沙市', '开福区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4369, '3', '4', '430111', '43', '雨花区', '湖南省', '长沙市', '雨花区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4370, '3', '4', '430112', '43', '望城区', '湖南省', '长沙市', '望城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4371, '3', '4', '430121', '43', '长沙县', '湖南省', '长沙市', '长沙县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4372, '3', '4', '430124', '43', '宁乡县', '湖南省', '长沙市', '宁乡县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4373, '3', '4', '430181', '43', '浏阳市', '湖南省', '长沙市', '浏阳市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4374, '2', '4', '430200', '43', '株洲市', '湖南省', '株洲市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4375, '3', '4', '430201', '43', '市辖区', '湖南省', '株洲市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4376, '3', '4', '430202', '43', '荷塘区', '湖南省', '株洲市', '荷塘区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4377, '3', '4', '430203', '43', '芦淞区', '湖南省', '株洲市', '芦淞区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4378, '3', '4', '430204', '43', '石峰区', '湖南省', '株洲市', '石峰区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4379, '3', '4', '430211', '43', '天元区', '湖南省', '株洲市', '天元区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4380, '3', '4', '430221', '43', '株洲县', '湖南省', '株洲市', '株洲县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4381, '3', '4', '430223', '43', '攸县', '湖南省', '株洲市', '攸县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4382, '3', '4', '430224', '43', '茶陵县', '湖南省', '株洲市', '茶陵县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4383, '3', '4', '430225', '43', '炎陵县', '湖南省', '株洲市', '炎陵县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4384, '3', '4', '430281', '43', '醴陵市', '湖南省', '株洲市', '醴陵市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4385, '2', '4', '430300', '43', '湘潭市', '湖南省', '湘潭市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4386, '3', '4', '430301', '43', '市辖区', '湖南省', '湘潭市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4387, '3', '4', '430302', '43', '雨湖区', '湖南省', '湘潭市', '雨湖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4388, '3', '4', '430304', '43', '岳塘区', '湖南省', '湘潭市', '岳塘区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4389, '3', '4', '430321', '43', '湘潭县', '湖南省', '湘潭市', '湘潭县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4390, '3', '4', '430381', '43', '湘乡市', '湖南省', '湘潭市', '湘乡市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4391, '3', '4', '430382', '43', '韶山市', '湖南省', '湘潭市', '韶山市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4392, '2', '4', '430400', '43', '衡阳市', '湖南省', '衡阳市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4393, '3', '4', '430401', '43', '市辖区', '湖南省', '衡阳市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4394, '3', '4', '430405', '43', '珠晖区', '湖南省', '衡阳市', '珠晖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4395, '3', '4', '430406', '43', '雁峰区', '湖南省', '衡阳市', '雁峰区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4396, '3', '4', '430407', '43', '石鼓区', '湖南省', '衡阳市', '石鼓区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4397, '3', '4', '430408', '43', '蒸湘区', '湖南省', '衡阳市', '蒸湘区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4398, '3', '4', '430412', '43', '南岳区', '湖南省', '衡阳市', '南岳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4399, '3', '4', '430421', '43', '衡阳县', '湖南省', '衡阳市', '衡阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4400, '3', '4', '430422', '43', '衡南县', '湖南省', '衡阳市', '衡南县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4401, '3', '4', '430423', '43', '衡山县', '湖南省', '衡阳市', '衡山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4402, '3', '4', '430424', '43', '衡东县', '湖南省', '衡阳市', '衡东县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4403, '3', '4', '430426', '43', '祁东县', '湖南省', '衡阳市', '祁东县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4404, '3', '4', '430481', '43', '耒阳市', '湖南省', '衡阳市', '耒阳市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4405, '3', '4', '430482', '43', '常宁市', '湖南省', '衡阳市', '常宁市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4406, '2', '4', '430500', '43', '邵阳市', '湖南省', '邵阳市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4407, '3', '4', '430501', '43', '市辖区', '湖南省', '邵阳市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4408, '3', '4', '430502', '43', '双清区', '湖南省', '邵阳市', '双清区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4409, '3', '4', '430503', '43', '大祥区', '湖南省', '邵阳市', '大祥区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4410, '3', '4', '430511', '43', '北塔区', '湖南省', '邵阳市', '北塔区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4411, '3', '4', '430521', '43', '邵东县', '湖南省', '邵阳市', '邵东县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4412, '3', '4', '430522', '43', '新邵县', '湖南省', '邵阳市', '新邵县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4413, '3', '4', '430523', '43', '邵阳县', '湖南省', '邵阳市', '邵阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4414, '3', '4', '430524', '43', '隆回县', '湖南省', '邵阳市', '隆回县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4415, '3', '4', '430525', '43', '洞口县', '湖南省', '邵阳市', '洞口县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4416, '3', '4', '430527', '43', '绥宁县', '湖南省', '邵阳市', '绥宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4417, '3', '4', '430528', '43', '新宁县', '湖南省', '邵阳市', '新宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4418, '3', '4', '430529', '43', '城步苗族自治县', '湖南省', '邵阳市', '城步苗族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4419, '3', '4', '430581', '43', '武冈市', '湖南省', '邵阳市', '武冈市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4420, '2', '4', '430600', '43', '岳阳市', '湖南省', '岳阳市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4421, '3', '4', '430601', '43', '市辖区', '湖南省', '岳阳市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4422, '3', '4', '430602', '43', '岳阳楼区', '湖南省', '岳阳市', '岳阳楼区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4423, '3', '4', '430603', '43', '云溪区', '湖南省', '岳阳市', '云溪区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4424, '3', '4', '430611', '43', '君山区', '湖南省', '岳阳市', '君山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4425, '3', '4', '430621', '43', '岳阳县', '湖南省', '岳阳市', '岳阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4426, '3', '4', '430623', '43', '华容县', '湖南省', '岳阳市', '华容县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4427, '3', '4', '430624', '43', '湘阴县', '湖南省', '岳阳市', '湘阴县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4428, '3', '4', '430626', '43', '平江县', '湖南省', '岳阳市', '平江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4429, '3', '4', '430681', '43', '汨罗市', '湖南省', '岳阳市', '汨罗市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4430, '3', '4', '430682', '43', '临湘市', '湖南省', '岳阳市', '临湘市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4431, '2', '4', '430700', '43', '常德市', '湖南省', '常德市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4432, '3', '4', '430701', '43', '市辖区', '湖南省', '常德市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4433, '3', '4', '430702', '43', '武陵区', '湖南省', '常德市', '武陵区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4434, '3', '4', '430703', '43', '鼎城区', '湖南省', '常德市', '鼎城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4435, '3', '4', '430721', '43', '安乡县', '湖南省', '常德市', '安乡县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4436, '3', '4', '430722', '43', '汉寿县', '湖南省', '常德市', '汉寿县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4437, '3', '4', '430723', '43', '澧县', '湖南省', '常德市', '澧县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4438, '3', '4', '430724', '43', '临澧县', '湖南省', '常德市', '临澧县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4439, '3', '4', '430725', '43', '桃源县', '湖南省', '常德市', '桃源县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4440, '3', '4', '430726', '43', '石门县', '湖南省', '常德市', '石门县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4441, '3', '4', '430781', '43', '津市市', '湖南省', '常德市', '津市市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4442, '2', '4', '430800', '43', '张家界市', '湖南省', '张家界市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4443, '3', '4', '430801', '43', '市辖区', '湖南省', '张家界市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4444, '3', '4', '430802', '43', '永定区', '湖南省', '张家界市', '永定区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4445, '3', '4', '430811', '43', '武陵源区', '湖南省', '张家界市', '武陵源区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4446, '3', '4', '430821', '43', '慈利县', '湖南省', '张家界市', '慈利县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4447, '3', '4', '430822', '43', '桑植县', '湖南省', '张家界市', '桑植县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4448, '2', '4', '430900', '43', '益阳市', '湖南省', '益阳市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4449, '3', '4', '430901', '43', '市辖区', '湖南省', '益阳市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4450, '3', '4', '430902', '43', '资阳区', '湖南省', '益阳市', '资阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4451, '3', '4', '430903', '43', '赫山区', '湖南省', '益阳市', '赫山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4452, '3', '4', '430921', '43', '南县', '湖南省', '益阳市', '南县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4453, '3', '4', '430922', '43', '桃江县', '湖南省', '益阳市', '桃江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4454, '3', '4', '430923', '43', '安化县', '湖南省', '益阳市', '安化县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4455, '3', '4', '430981', '43', '沅江市', '湖南省', '益阳市', '沅江市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4456, '2', '4', '431000', '43', '郴州市', '湖南省', '郴州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4457, '3', '4', '431001', '43', '市辖区', '湖南省', '郴州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4458, '3', '4', '431002', '43', '北湖区', '湖南省', '郴州市', '北湖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4459, '3', '4', '431003', '43', '苏仙区', '湖南省', '郴州市', '苏仙区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4460, '3', '4', '431021', '43', '桂阳县', '湖南省', '郴州市', '桂阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4461, '3', '4', '431022', '43', '宜章县', '湖南省', '郴州市', '宜章县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4462, '3', '4', '431023', '43', '永兴县', '湖南省', '郴州市', '永兴县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4463, '3', '4', '431024', '43', '嘉禾县', '湖南省', '郴州市', '嘉禾县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4464, '3', '4', '431025', '43', '临武县', '湖南省', '郴州市', '临武县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4465, '3', '4', '431026', '43', '汝城县', '湖南省', '郴州市', '汝城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4466, '3', '4', '431027', '43', '桂东县', '湖南省', '郴州市', '桂东县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4467, '3', '4', '431028', '43', '安仁县', '湖南省', '郴州市', '安仁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4468, '3', '4', '431081', '43', '资兴市', '湖南省', '郴州市', '资兴市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4469, '2', '4', '431100', '43', '永州市', '湖南省', '永州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4470, '3', '4', '431101', '43', '市辖区', '湖南省', '永州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4471, '3', '4', '431102', '43', '零陵区', '湖南省', '永州市', '零陵区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4472, '3', '4', '431103', '43', '冷水滩区', '湖南省', '永州市', '冷水滩区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4473, '3', '4', '431121', '43', '祁阳县', '湖南省', '永州市', '祁阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4474, '3', '4', '431122', '43', '东安县', '湖南省', '永州市', '东安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4475, '3', '4', '431123', '43', '双牌县', '湖南省', '永州市', '双牌县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4476, '3', '4', '431124', '43', '道县', '湖南省', '永州市', '道县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4477, '3', '4', '431125', '43', '江永县', '湖南省', '永州市', '江永县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4478, '3', '4', '431126', '43', '宁远县', '湖南省', '永州市', '宁远县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4479, '3', '4', '431127', '43', '蓝山县', '湖南省', '永州市', '蓝山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4480, '3', '4', '431128', '43', '新田县', '湖南省', '永州市', '新田县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4481, '3', '4', '431129', '43', '江华瑶族自治县', '湖南省', '永州市', '江华瑶族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4482, '2', '4', '431200', '43', '怀化市', '湖南省', '怀化市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4483, '3', '4', '431201', '43', '市辖区', '湖南省', '怀化市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4484, '3', '4', '431202', '43', '鹤城区', '湖南省', '怀化市', '鹤城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4485, '3', '4', '431221', '43', '中方县', '湖南省', '怀化市', '中方县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4486, '3', '4', '431222', '43', '沅陵县', '湖南省', '怀化市', '沅陵县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4487, '3', '4', '431223', '43', '辰溪县', '湖南省', '怀化市', '辰溪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4488, '3', '4', '431224', '43', '溆浦县', '湖南省', '怀化市', '溆浦县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4489, '3', '4', '431225', '43', '会同县', '湖南省', '怀化市', '会同县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4490, '3', '4', '431226', '43', '麻阳苗族自治县', '湖南省', '怀化市', '麻阳苗族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4491, '3', '4', '431227', '43', '新晃侗族自治县', '湖南省', '怀化市', '新晃侗族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4492, '3', '4', '431228', '43', '芷江侗族自治县', '湖南省', '怀化市', '芷江侗族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4493, '3', '4', '431229', '43', '靖州苗族侗族自治县', '湖南省', '怀化市', '靖州苗族侗族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4494, '3', '4', '431230', '43', '通道侗族自治县', '湖南省', '怀化市', '通道侗族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4495, '3', '4', '431281', '43', '洪江市', '湖南省', '怀化市', '洪江市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4496, '2', '4', '431300', '43', '娄底市', '湖南省', '娄底市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4497, '3', '4', '431301', '43', '市辖区', '湖南省', '娄底市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4498, '3', '4', '431302', '43', '娄星区', '湖南省', '娄底市', '娄星区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4499, '3', '4', '431321', '43', '双峰县', '湖南省', '娄底市', '双峰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4500, '3', '4', '431322', '43', '新化县', '湖南省', '娄底市', '新化县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4501, '3', '4', '431381', '43', '冷水江市', '湖南省', '娄底市', '冷水江市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4502, '3', '4', '431382', '43', '涟源市', '湖南省', '娄底市', '涟源市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4503, '2', '4', '433100', '43', '湘西土家族苗族自治州', '湖南省', '湘西土家族苗族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4504, '3', '4', '433101', '43', '吉首市', '湖南省', '湘西土家族苗族自治州', '吉首市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4505, '3', '4', '433122', '43', '泸溪县', '湖南省', '湘西土家族苗族自治州', '泸溪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4506, '3', '4', '433123', '43', '凤凰县', '湖南省', '湘西土家族苗族自治州', '凤凰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4507, '3', '4', '433124', '43', '花垣县', '湖南省', '湘西土家族苗族自治州', '花垣县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4508, '3', '4', '433125', '43', '保靖县', '湖南省', '湘西土家族苗族自治州', '保靖县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4509, '3', '4', '433126', '43', '古丈县', '湖南省', '湘西土家族苗族自治州', '古丈县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4510, '3', '4', '433127', '43', '永顺县', '湖南省', '湘西土家族苗族自治州', '永顺县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4511, '3', '4', '433130', '43', '龙山县', '湖南省', '湘西土家族苗族自治州', '龙山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4512, '1', '5', '440000', '44', '广东省', '广东省', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4513, '2', '5', '440100', '44', '广州市', '广东省', '广州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4514, '3', '5', '440101', '44', '市辖区', '广东省', '广州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4515, '3', '5', '440103', '44', '荔湾区', '广东省', '广州市', '荔湾区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4516, '3', '5', '440104', '44', '越秀区', '广东省', '广州市', '越秀区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4517, '3', '5', '440105', '44', '海珠区', '广东省', '广州市', '海珠区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4518, '3', '5', '440106', '44', '天河区', '广东省', '广州市', '天河区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4519, '3', '5', '440111', '44', '白云区', '广东省', '广州市', '白云区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4520, '3', '5', '440112', '44', '黄埔区', '广东省', '广州市', '黄埔区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4521, '3', '5', '440113', '44', '番禺区', '广东省', '广州市', '番禺区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4522, '3', '5', '440114', '44', '花都区', '广东省', '广州市', '花都区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4523, '3', '5', '440115', '44', '南沙区', '广东省', '广州市', '南沙区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4524, '3', '5', '440117', '44', '从化区', '广东省', '广州市', '从化区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4525, '3', '5', '440118', '44', '增城区', '广东省', '广州市', '增城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4526, '2', '5', '440200', '44', '韶关市', '广东省', '韶关市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4527, '3', '5', '440201', '44', '市辖区', '广东省', '韶关市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4528, '3', '5', '440203', '44', '武江区', '广东省', '韶关市', '武江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4529, '3', '5', '440204', '44', '浈江区', '广东省', '韶关市', '浈江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4530, '3', '5', '440205', '44', '曲江区', '广东省', '韶关市', '曲江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4531, '3', '5', '440222', '44', '始兴县', '广东省', '韶关市', '始兴县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4532, '3', '5', '440224', '44', '仁化县', '广东省', '韶关市', '仁化县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4533, '3', '5', '440229', '44', '翁源县', '广东省', '韶关市', '翁源县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4534, '3', '5', '440232', '44', '乳源瑶族自治县', '广东省', '韶关市', '乳源瑶族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4535, '3', '5', '440233', '44', '新丰县', '广东省', '韶关市', '新丰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4536, '3', '5', '440281', '44', '乐昌市', '广东省', '韶关市', '乐昌市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4537, '3', '5', '440282', '44', '南雄市', '广东省', '韶关市', '南雄市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4538, '2', '5', '440300', '44', '深圳市', '广东省', '深圳市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4539, '3', '5', '440301', '44', '市辖区', '广东省', '深圳市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4540, '3', '5', '440303', '44', '罗湖区', '广东省', '深圳市', '罗湖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4541, '3', '5', '440304', '44', '福田区', '广东省', '深圳市', '福田区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4542, '3', '5', '440305', '44', '南山区', '广东省', '深圳市', '南山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4543, '3', '5', '440306', '44', '宝安区', '广东省', '深圳市', '宝安区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4544, '3', '5', '440307', '44', '龙岗区', '广东省', '深圳市', '龙岗区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4545, '3', '5', '440308', '44', '盐田区', '广东省', '深圳市', '盐田区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4546, '2', '5', '440400', '44', '珠海市', '广东省', '珠海市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4547, '3', '5', '440401', '44', '市辖区', '广东省', '珠海市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4548, '3', '5', '440402', '44', '香洲区', '广东省', '珠海市', '香洲区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4549, '3', '5', '440403', '44', '斗门区', '广东省', '珠海市', '斗门区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4550, '3', '5', '440404', '44', '金湾区', '广东省', '珠海市', '金湾区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4551, '2', '5', '440500', '44', '汕头市', '广东省', '汕头市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4552, '3', '5', '440501', '44', '市辖区', '广东省', '汕头市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4553, '3', '5', '440507', '44', '龙湖区', '广东省', '汕头市', '龙湖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4554, '3', '5', '440511', '44', '金平区', '广东省', '汕头市', '金平区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4555, '3', '5', '440512', '44', '濠江区', '广东省', '汕头市', '濠江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4556, '3', '5', '440513', '44', '潮阳区', '广东省', '汕头市', '潮阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4557, '3', '5', '440514', '44', '潮南区', '广东省', '汕头市', '潮南区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4558, '3', '5', '440515', '44', '澄海区', '广东省', '汕头市', '澄海区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4559, '3', '5', '440523', '44', '南澳县', '广东省', '汕头市', '南澳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4560, '2', '5', '440600', '44', '佛山市', '广东省', '佛山市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4561, '3', '5', '440601', '44', '市辖区', '广东省', '佛山市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4562, '3', '5', '440604', '44', '禅城区', '广东省', '佛山市', '禅城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4563, '3', '5', '440605', '44', '南海区', '广东省', '佛山市', '南海区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4564, '3', '5', '440606', '44', '顺德区', '广东省', '佛山市', '顺德区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4565, '3', '5', '440607', '44', '三水区', '广东省', '佛山市', '三水区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4566, '3', '5', '440608', '44', '高明区', '广东省', '佛山市', '高明区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4567, '2', '5', '440700', '44', '江门市', '广东省', '江门市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4568, '3', '5', '440701', '44', '市辖区', '广东省', '江门市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4569, '3', '5', '440703', '44', '蓬江区', '广东省', '江门市', '蓬江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4570, '3', '5', '440704', '44', '江海区', '广东省', '江门市', '江海区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4571, '3', '5', '440705', '44', '新会区', '广东省', '江门市', '新会区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4572, '3', '5', '440781', '44', '台山市', '广东省', '江门市', '台山市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4573, '3', '5', '440783', '44', '开平市', '广东省', '江门市', '开平市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4574, '3', '5', '440784', '44', '鹤山市', '广东省', '江门市', '鹤山市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4575, '3', '5', '440785', '44', '恩平市', '广东省', '江门市', '恩平市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4576, '2', '5', '440800', '44', '湛江市', '广东省', '湛江市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4577, '3', '5', '440801', '44', '市辖区', '广东省', '湛江市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4578, '3', '5', '440802', '44', '赤坎区', '广东省', '湛江市', '赤坎区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4579, '3', '5', '440803', '44', '霞山区', '广东省', '湛江市', '霞山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4580, '3', '5', '440804', '44', '坡头区', '广东省', '湛江市', '坡头区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4581, '3', '5', '440811', '44', '麻章区', '广东省', '湛江市', '麻章区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4582, '3', '5', '440823', '44', '遂溪县', '广东省', '湛江市', '遂溪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4583, '3', '5', '440825', '44', '徐闻县', '广东省', '湛江市', '徐闻县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4584, '3', '5', '440881', '44', '廉江市', '广东省', '湛江市', '廉江市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4585, '3', '5', '440882', '44', '雷州市', '广东省', '湛江市', '雷州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4586, '3', '5', '440883', '44', '吴川市', '广东省', '湛江市', '吴川市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4587, '2', '5', '440900', '44', '茂名市', '广东省', '茂名市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4588, '3', '5', '440901', '44', '市辖区', '广东省', '茂名市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4589, '3', '5', '440902', '44', '茂南区', '广东省', '茂名市', '茂南区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4590, '3', '5', '440904', '44', '电白区', '广东省', '茂名市', '电白区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4591, '3', '5', '440981', '44', '高州市', '广东省', '茂名市', '高州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4592, '3', '5', '440982', '44', '化州市', '广东省', '茂名市', '化州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4593, '3', '5', '440983', '44', '信宜市', '广东省', '茂名市', '信宜市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4594, '2', '5', '441200', '44', '肇庆市', '广东省', '肇庆市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4595, '3', '5', '441201', '44', '市辖区', '广东省', '肇庆市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4596, '3', '5', '441202', '44', '端州区', '广东省', '肇庆市', '端州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4597, '3', '5', '441203', '44', '鼎湖区', '广东省', '肇庆市', '鼎湖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4598, '3', '5', '441204', '44', '高要区', '广东省', '肇庆市', '高要区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4599, '3', '5', '441223', '44', '广宁县', '广东省', '肇庆市', '广宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4600, '3', '5', '441224', '44', '怀集县', '广东省', '肇庆市', '怀集县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4601, '3', '5', '441225', '44', '封开县', '广东省', '肇庆市', '封开县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4602, '3', '5', '441226', '44', '德庆县', '广东省', '肇庆市', '德庆县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4603, '3', '5', '441284', '44', '四会市', '广东省', '肇庆市', '四会市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4604, '2', '5', '441300', '44', '惠州市', '广东省', '惠州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4605, '3', '5', '441301', '44', '市辖区', '广东省', '惠州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4606, '3', '5', '441302', '44', '惠城区', '广东省', '惠州市', '惠城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4607, '3', '5', '441303', '44', '惠阳区', '广东省', '惠州市', '惠阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4608, '3', '5', '441322', '44', '博罗县', '广东省', '惠州市', '博罗县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4609, '3', '5', '441323', '44', '惠东县', '广东省', '惠州市', '惠东县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4610, '3', '5', '441324', '44', '龙门县', '广东省', '惠州市', '龙门县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4611, '2', '5', '441400', '44', '梅州市', '广东省', '梅州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4612, '3', '5', '441401', '44', '市辖区', '广东省', '梅州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4613, '3', '5', '441402', '44', '梅江区', '广东省', '梅州市', '梅江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4614, '3', '5', '441403', '44', '梅县区', '广东省', '梅州市', '梅县区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4615, '3', '5', '441422', '44', '大埔县', '广东省', '梅州市', '大埔县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4616, '3', '5', '441423', '44', '丰顺县', '广东省', '梅州市', '丰顺县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4617, '3', '5', '441424', '44', '五华县', '广东省', '梅州市', '五华县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4618, '3', '5', '441426', '44', '平远县', '广东省', '梅州市', '平远县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4619, '3', '5', '441427', '44', '蕉岭县', '广东省', '梅州市', '蕉岭县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4620, '3', '5', '441481', '44', '兴宁市', '广东省', '梅州市', '兴宁市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4621, '2', '5', '441500', '44', '汕尾市', '广东省', '汕尾市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4622, '3', '5', '441501', '44', '市辖区', '广东省', '汕尾市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4623, '3', '5', '441502', '44', '城区', '广东省', '汕尾市', '城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4624, '3', '5', '441521', '44', '海丰县', '广东省', '汕尾市', '海丰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4625, '3', '5', '441523', '44', '陆河县', '广东省', '汕尾市', '陆河县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4626, '3', '5', '441581', '44', '陆丰市', '广东省', '汕尾市', '陆丰市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4627, '2', '5', '441600', '44', '河源市', '广东省', '河源市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4628, '3', '5', '441601', '44', '市辖区', '广东省', '河源市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4629, '3', '5', '441602', '44', '源城区', '广东省', '河源市', '源城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4630, '3', '5', '441621', '44', '紫金县', '广东省', '河源市', '紫金县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4631, '3', '5', '441622', '44', '龙川县', '广东省', '河源市', '龙川县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4632, '3', '5', '441623', '44', '连平县', '广东省', '河源市', '连平县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4633, '3', '5', '441624', '44', '和平县', '广东省', '河源市', '和平县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4634, '3', '5', '441625', '44', '东源县', '广东省', '河源市', '东源县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4635, '2', '5', '441700', '44', '阳江市', '广东省', '阳江市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4636, '3', '5', '441701', '44', '市辖区', '广东省', '阳江市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4637, '3', '5', '441702', '44', '江城区', '广东省', '阳江市', '江城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4638, '3', '5', '441704', '44', '阳东区', '广东省', '阳江市', '阳东区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4639, '3', '5', '441721', '44', '阳西县', '广东省', '阳江市', '阳西县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4640, '3', '5', '441781', '44', '阳春市', '广东省', '阳江市', '阳春市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4641, '2', '5', '441800', '44', '清远市', '广东省', '清远市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4642, '3', '5', '441801', '44', '市辖区', '广东省', '清远市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4643, '3', '5', '441802', '44', '清城区', '广东省', '清远市', '清城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4644, '3', '5', '441803', '44', '清新区', '广东省', '清远市', '清新区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4645, '3', '5', '441821', '44', '佛冈县', '广东省', '清远市', '佛冈县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4646, '3', '5', '441823', '44', '阳山县', '广东省', '清远市', '阳山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4647, '3', '5', '441825', '44', '连山壮族瑶族自治县', '广东省', '清远市', '连山壮族瑶族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4648, '3', '5', '441826', '44', '连南瑶族自治县', '广东省', '清远市', '连南瑶族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4649, '3', '5', '441881', '44', '英德市', '广东省', '清远市', '英德市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4650, '3', '5', '441882', '44', '连州市', '广东省', '清远市', '连州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4651, '2', '5', '441900', '44', '东莞市', '广东省', '东莞市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4652, '3', '5', '441901', '44', '市政镇', '广东省', '东莞市', '市政镇', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4653, '2', '5', '442000', '44', '中山市', '广东省', '中山市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4654, '3', '5', '442001', '44', '市政镇', '广东省', '中山市', '市政镇', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4655, '2', '5', '445100', '44', '潮州市', '广东省', '潮州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4656, '3', '5', '445101', '44', '市辖区', '广东省', '潮州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4657, '3', '5', '445102', '44', '湘桥区', '广东省', '潮州市', '湘桥区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4658, '3', '5', '445103', '44', '潮安区', '广东省', '潮州市', '潮安区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4659, '3', '5', '445122', '44', '饶平县', '广东省', '潮州市', '饶平县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4660, '2', '5', '445200', '44', '揭阳市', '广东省', '揭阳市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4661, '3', '5', '445201', '44', '市辖区', '广东省', '揭阳市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4662, '3', '5', '445202', '44', '榕城区', '广东省', '揭阳市', '榕城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4663, '3', '5', '445203', '44', '揭东区', '广东省', '揭阳市', '揭东区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4664, '3', '5', '445222', '44', '揭西县', '广东省', '揭阳市', '揭西县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4665, '3', '5', '445224', '44', '惠来县', '广东省', '揭阳市', '惠来县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4666, '3', '5', '445281', '44', '普宁市', '广东省', '揭阳市', '普宁市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4667, '2', '5', '445300', '44', '云浮市', '广东省', '云浮市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4668, '3', '5', '445301', '44', '市辖区', '广东省', '云浮市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4669, '3', '5', '445302', '44', '云城区', '广东省', '云浮市', '云城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4670, '3', '5', '445303', '44', '云安区', '广东省', '云浮市', '云安区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4671, '3', '5', '445321', '44', '新兴县', '广东省', '云浮市', '新兴县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4672, '3', '5', '445322', '44', '郁南县', '广东省', '云浮市', '郁南县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4673, '3', '5', '445381', '44', '罗定市', '广东省', '云浮市', '罗定市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4674, '1', '5', '450000', '45', '广西壮族自治区', '广西壮族自治区', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4675, '2', '5', '450100', '45', '南宁市', '广西壮族自治区', '南宁市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4676, '3', '5', '450101', '45', '市辖区', '广西壮族自治区', '南宁市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4677, '3', '5', '450102', '45', '兴宁区', '广西壮族自治区', '南宁市', '兴宁区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4678, '3', '5', '450103', '45', '青秀区', '广西壮族自治区', '南宁市', '青秀区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4679, '3', '5', '450105', '45', '江南区', '广西壮族自治区', '南宁市', '江南区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4680, '3', '5', '450107', '45', '西乡塘区', '广西壮族自治区', '南宁市', '西乡塘区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4681, '3', '5', '450108', '45', '良庆区', '广西壮族自治区', '南宁市', '良庆区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4682, '3', '5', '450109', '45', '邕宁区', '广西壮族自治区', '南宁市', '邕宁区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4683, '3', '5', '450110', '45', '武鸣区', '广西壮族自治区', '南宁市', '武鸣区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4684, '3', '5', '450123', '45', '隆安县', '广西壮族自治区', '南宁市', '隆安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4685, '3', '5', '450124', '45', '马山县', '广西壮族自治区', '南宁市', '马山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4686, '3', '5', '450125', '45', '上林县', '广西壮族自治区', '南宁市', '上林县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4687, '3', '5', '450126', '45', '宾阳县', '广西壮族自治区', '南宁市', '宾阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4688, '3', '5', '450127', '45', '横县', '广西壮族自治区', '南宁市', '横县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4689, '2', '5', '450200', '45', '柳州市', '广西壮族自治区', '柳州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4690, '3', '5', '450201', '45', '市辖区', '广西壮族自治区', '柳州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4691, '3', '5', '450202', '45', '城中区', '广西壮族自治区', '柳州市', '城中区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4692, '3', '5', '450203', '45', '鱼峰区', '广西壮族自治区', '柳州市', '鱼峰区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4693, '3', '5', '450204', '45', '柳南区', '广西壮族自治区', '柳州市', '柳南区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4694, '3', '5', '450205', '45', '柳北区', '广西壮族自治区', '柳州市', '柳北区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4695, '3', '5', '450221', '45', '柳江县', '广西壮族自治区', '柳州市', '柳江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4696, '3', '5', '450222', '45', '柳城县', '广西壮族自治区', '柳州市', '柳城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4697, '3', '5', '450223', '45', '鹿寨县', '广西壮族自治区', '柳州市', '鹿寨县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4698, '3', '5', '450224', '45', '融安县', '广西壮族自治区', '柳州市', '融安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4699, '3', '5', '450225', '45', '融水苗族自治县', '广西壮族自治区', '柳州市', '融水苗族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4700, '3', '5', '450226', '45', '三江侗族自治县', '广西壮族自治区', '柳州市', '三江侗族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4701, '2', '5', '450300', '45', '桂林市', '广西壮族自治区', '桂林市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4702, '3', '5', '450301', '45', '市辖区', '广西壮族自治区', '桂林市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4703, '3', '5', '450302', '45', '秀峰区', '广西壮族自治区', '桂林市', '秀峰区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4704, '3', '5', '450303', '45', '叠彩区', '广西壮族自治区', '桂林市', '叠彩区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4705, '3', '5', '450304', '45', '象山区', '广西壮族自治区', '桂林市', '象山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4706, '3', '5', '450305', '45', '七星区', '广西壮族自治区', '桂林市', '七星区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4707, '3', '5', '450311', '45', '雁山区', '广西壮族自治区', '桂林市', '雁山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4708, '3', '5', '450312', '45', '临桂区', '广西壮族自治区', '桂林市', '临桂区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4709, '3', '5', '450321', '45', '阳朔县', '广西壮族自治区', '桂林市', '阳朔县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4710, '3', '5', '450323', '45', '灵川县', '广西壮族自治区', '桂林市', '灵川县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4711, '3', '5', '450324', '45', '全州县', '广西壮族自治区', '桂林市', '全州县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4712, '3', '5', '450325', '45', '兴安县', '广西壮族自治区', '桂林市', '兴安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4713, '3', '5', '450326', '45', '永福县', '广西壮族自治区', '桂林市', '永福县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4714, '3', '5', '450327', '45', '灌阳县', '广西壮族自治区', '桂林市', '灌阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4715, '3', '5', '450328', '45', '龙胜各族自治县', '广西壮族自治区', '桂林市', '龙胜各族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4716, '3', '5', '450329', '45', '资源县', '广西壮族自治区', '桂林市', '资源县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4717, '3', '5', '450330', '45', '平乐县', '广西壮族自治区', '桂林市', '平乐县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4718, '3', '5', '450331', '45', '荔浦县', '广西壮族自治区', '桂林市', '荔浦县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4719, '3', '5', '450332', '45', '恭城瑶族自治县', '广西壮族自治区', '桂林市', '恭城瑶族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4720, '2', '5', '450400', '45', '梧州市', '广西壮族自治区', '梧州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4721, '3', '5', '450401', '45', '市辖区', '广西壮族自治区', '梧州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4722, '3', '5', '450403', '45', '万秀区', '广西壮族自治区', '梧州市', '万秀区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4723, '3', '5', '450405', '45', '长洲区', '广西壮族自治区', '梧州市', '长洲区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4724, '3', '5', '450406', '45', '龙圩区', '广西壮族自治区', '梧州市', '龙圩区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4725, '3', '5', '450421', '45', '苍梧县', '广西壮族自治区', '梧州市', '苍梧县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4726, '3', '5', '450422', '45', '藤县', '广西壮族自治区', '梧州市', '藤县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4727, '3', '5', '450423', '45', '蒙山县', '广西壮族自治区', '梧州市', '蒙山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4728, '3', '5', '450481', '45', '岑溪市', '广西壮族自治区', '梧州市', '岑溪市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4729, '2', '5', '450500', '45', '北海市', '广西壮族自治区', '北海市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4730, '3', '5', '450501', '45', '市辖区', '广西壮族自治区', '北海市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4731, '3', '5', '450502', '45', '海城区', '广西壮族自治区', '北海市', '海城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4732, '3', '5', '450503', '45', '银海区', '广西壮族自治区', '北海市', '银海区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4733, '3', '5', '450512', '45', '铁山港区', '广西壮族自治区', '北海市', '铁山港区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4734, '3', '5', '450521', '45', '合浦县', '广西壮族自治区', '北海市', '合浦县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4735, '2', '5', '450600', '45', '防城港市', '广西壮族自治区', '防城港市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4736, '3', '5', '450601', '45', '市辖区', '广西壮族自治区', '防城港市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4737, '3', '5', '450602', '45', '港口区', '广西壮族自治区', '防城港市', '港口区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4738, '3', '5', '450603', '45', '防城区', '广西壮族自治区', '防城港市', '防城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4739, '3', '5', '450621', '45', '上思县', '广西壮族自治区', '防城港市', '上思县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4740, '3', '5', '450681', '45', '东兴市', '广西壮族自治区', '防城港市', '东兴市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4741, '2', '5', '450700', '45', '钦州市', '广西壮族自治区', '钦州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4742, '3', '5', '450701', '45', '市辖区', '广西壮族自治区', '钦州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4743, '3', '5', '450702', '45', '钦南区', '广西壮族自治区', '钦州市', '钦南区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4744, '3', '5', '450703', '45', '钦北区', '广西壮族自治区', '钦州市', '钦北区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4745, '3', '5', '450721', '45', '灵山县', '广西壮族自治区', '钦州市', '灵山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4746, '3', '5', '450722', '45', '浦北县', '广西壮族自治区', '钦州市', '浦北县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4747, '2', '5', '450800', '45', '贵港市', '广西壮族自治区', '贵港市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4748, '3', '5', '450801', '45', '市辖区', '广西壮族自治区', '贵港市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4749, '3', '5', '450802', '45', '港北区', '广西壮族自治区', '贵港市', '港北区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4750, '3', '5', '450803', '45', '港南区', '广西壮族自治区', '贵港市', '港南区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4751, '3', '5', '450804', '45', '覃塘区', '广西壮族自治区', '贵港市', '覃塘区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4752, '3', '5', '450821', '45', '平南县', '广西壮族自治区', '贵港市', '平南县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4753, '3', '5', '450881', '45', '桂平市', '广西壮族自治区', '贵港市', '桂平市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4754, '2', '5', '450900', '45', '玉林市', '广西壮族自治区', '玉林市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4755, '3', '5', '450901', '45', '市辖区', '广西壮族自治区', '玉林市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4756, '3', '5', '450902', '45', '玉州区', '广西壮族自治区', '玉林市', '玉州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4757, '3', '5', '450903', '45', '福绵区', '广西壮族自治区', '玉林市', '福绵区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4758, '3', '5', '450921', '45', '容县', '广西壮族自治区', '玉林市', '容县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4759, '3', '5', '450922', '45', '陆川县', '广西壮族自治区', '玉林市', '陆川县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4760, '3', '5', '450923', '45', '博白县', '广西壮族自治区', '玉林市', '博白县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4761, '3', '5', '450924', '45', '兴业县', '广西壮族自治区', '玉林市', '兴业县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4762, '3', '5', '450981', '45', '北流市', '广西壮族自治区', '玉林市', '北流市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4763, '2', '5', '451000', '45', '百色市', '广西壮族自治区', '百色市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4764, '3', '5', '451001', '45', '市辖区', '广西壮族自治区', '百色市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4765, '3', '5', '451002', '45', '右江区', '广西壮族自治区', '百色市', '右江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4766, '3', '5', '451021', '45', '田阳县', '广西壮族自治区', '百色市', '田阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4767, '3', '5', '451022', '45', '田东县', '广西壮族自治区', '百色市', '田东县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4768, '3', '5', '451023', '45', '平果县', '广西壮族自治区', '百色市', '平果县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4769, '3', '5', '451024', '45', '德保县', '广西壮族自治区', '百色市', '德保县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4770, '3', '5', '451026', '45', '那坡县', '广西壮族自治区', '百色市', '那坡县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4771, '3', '5', '451027', '45', '凌云县', '广西壮族自治区', '百色市', '凌云县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4772, '3', '5', '451028', '45', '乐业县', '广西壮族自治区', '百色市', '乐业县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4773, '3', '5', '451029', '45', '田林县', '广西壮族自治区', '百色市', '田林县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4774, '3', '5', '451030', '45', '西林县', '广西壮族自治区', '百色市', '西林县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4775, '3', '5', '451031', '45', '隆林各族自治县', '广西壮族自治区', '百色市', '隆林各族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4776, '3', '5', '451081', '45', '靖西市', '广西壮族自治区', '百色市', '靖西市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4777, '2', '5', '451100', '45', '贺州市', '广西壮族自治区', '贺州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4778, '3', '5', '451101', '45', '市辖区', '广西壮族自治区', '贺州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4779, '3', '5', '451102', '45', '八步区', '广西壮族自治区', '贺州市', '八步区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4780, '3', '5', '451121', '45', '昭平县', '广西壮族自治区', '贺州市', '昭平县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4781, '3', '5', '451122', '45', '钟山县', '广西壮族自治区', '贺州市', '钟山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4782, '3', '5', '451123', '45', '富川瑶族自治县', '广西壮族自治区', '贺州市', '富川瑶族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4783, '2', '5', '451200', '45', '河池市', '广西壮族自治区', '河池市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4784, '3', '5', '451201', '45', '市辖区', '广西壮族自治区', '河池市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4785, '3', '5', '451202', '45', '金城江区', '广西壮族自治区', '河池市', '金城江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4786, '3', '5', '451221', '45', '南丹县', '广西壮族自治区', '河池市', '南丹县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4787, '3', '5', '451222', '45', '天峨县', '广西壮族自治区', '河池市', '天峨县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4788, '3', '5', '451223', '45', '凤山县', '广西壮族自治区', '河池市', '凤山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4789, '3', '5', '451224', '45', '东兰县', '广西壮族自治区', '河池市', '东兰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4790, '3', '5', '451225', '45', '罗城仫佬族自治县', '广西壮族自治区', '河池市', '罗城仫佬族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4791, '3', '5', '451226', '45', '环江毛南族自治县', '广西壮族自治区', '河池市', '环江毛南族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4792, '3', '5', '451227', '45', '巴马瑶族自治县', '广西壮族自治区', '河池市', '巴马瑶族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4793, '3', '5', '451228', '45', '都安瑶族自治县', '广西壮族自治区', '河池市', '都安瑶族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4794, '3', '5', '451229', '45', '大化瑶族自治县', '广西壮族自治区', '河池市', '大化瑶族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4795, '3', '5', '451281', '45', '宜州市', '广西壮族自治区', '河池市', '宜州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4796, '2', '5', '451300', '45', '来宾市', '广西壮族自治区', '来宾市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4797, '3', '5', '451301', '45', '市辖区', '广西壮族自治区', '来宾市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4798, '3', '5', '451302', '45', '兴宾区', '广西壮族自治区', '来宾市', '兴宾区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4799, '3', '5', '451321', '45', '忻城县', '广西壮族自治区', '来宾市', '忻城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4800, '3', '5', '451322', '45', '象州县', '广西壮族自治区', '来宾市', '象州县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4801, '3', '5', '451323', '45', '武宣县', '广西壮族自治区', '来宾市', '武宣县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4802, '3', '5', '451324', '45', '金秀瑶族自治县', '广西壮族自治区', '来宾市', '金秀瑶族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4803, '3', '5', '451381', '45', '合山市', '广西壮族自治区', '来宾市', '合山市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4804, '2', '5', '451400', '45', '崇左市', '广西壮族自治区', '崇左市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4805, '3', '5', '451401', '45', '市辖区', '广西壮族自治区', '崇左市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4806, '3', '5', '451402', '45', '江州区', '广西壮族自治区', '崇左市', '江州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4807, '3', '5', '451421', '45', '扶绥县', '广西壮族自治区', '崇左市', '扶绥县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4808, '3', '5', '451422', '45', '宁明县', '广西壮族自治区', '崇左市', '宁明县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4809, '3', '5', '451423', '45', '龙州县', '广西壮族自治区', '崇左市', '龙州县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4810, '3', '5', '451424', '45', '大新县', '广西壮族自治区', '崇左市', '大新县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4811, '3', '5', '451425', '45', '天等县', '广西壮族自治区', '崇左市', '天等县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4812, '3', '5', '451481', '45', '凭祥市', '广西壮族自治区', '崇左市', '凭祥市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4813, '1', '5', '460000', '46', '海南省', '海南省', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4814, '2', '5', '460100', '46', '海口市', '海南省', '海口市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4815, '3', '5', '460101', '46', '市辖区', '海南省', '海口市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4816, '3', '5', '460105', '46', '秀英区', '海南省', '海口市', '秀英区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4817, '3', '5', '460106', '46', '龙华区', '海南省', '海口市', '龙华区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4818, '3', '5', '460107', '46', '琼山区', '海南省', '海口市', '琼山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4819, '3', '5', '460108', '46', '美兰区', '海南省', '海口市', '美兰区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4820, '2', '5', '460200', '46', '三亚市', '海南省', '三亚市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4821, '3', '5', '460201', '46', '市辖区', '海南省', '三亚市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4822, '3', '5', '460202', '46', '海棠区', '海南省', '三亚市', '海棠区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4823, '3', '5', '460203', '46', '吉阳区', '海南省', '三亚市', '吉阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4824, '3', '5', '460204', '46', '天涯区', '海南省', '三亚市', '天涯区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4825, '3', '5', '460205', '46', '崖州区', '海南省', '三亚市', '崖州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4826, '2', '5', '460300', '46', '三沙市', '海南省', '三沙市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4827, '3', '5', '460321', '46', '西沙群岛', '海南省', '三沙市', '西沙群岛', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4828, '3', '5', '460322', '46', '南沙群岛', '海南省', '三沙市', '南沙群岛', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4829, '3', '5', '460323', '46', '中沙群岛的岛礁及其海域', '海南省', '三沙市', '中沙群岛的岛礁及其海域', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4830, '2', '5', '469000', '46', '省直辖县级行政区划', '海南省', '省直辖县级行政区划', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4831, '3', '5', '469001', '46', '五指山市', '海南省', '省直辖县级行政区划', '五指山市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4832, '3', '5', '469002', '46', '琼海市', '海南省', '省直辖县级行政区划', '琼海市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4833, '3', '5', '469003', '46', '儋州市', '海南省', '省直辖县级行政区划', '儋州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4834, '3', '5', '469005', '46', '文昌市', '海南省', '省直辖县级行政区划', '文昌市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4835, '3', '5', '469006', '46', '万宁市', '海南省', '省直辖县级行政区划', '万宁市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4836, '3', '5', '469007', '46', '东方市', '海南省', '省直辖县级行政区划', '东方市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4837, '3', '5', '469021', '46', '定安县', '海南省', '省直辖县级行政区划', '定安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4838, '3', '5', '469022', '46', '屯昌县', '海南省', '省直辖县级行政区划', '屯昌县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4839, '3', '5', '469023', '46', '澄迈县', '海南省', '省直辖县级行政区划', '澄迈县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4840, '3', '5', '469024', '46', '临高县', '海南省', '省直辖县级行政区划', '临高县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4841, '3', '5', '469025', '46', '白沙黎族自治县', '海南省', '省直辖县级行政区划', '白沙黎族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4842, '3', '5', '469026', '46', '昌江黎族自治县', '海南省', '省直辖县级行政区划', '昌江黎族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4843, '3', '5', '469027', '46', '乐东黎族自治县', '海南省', '省直辖县级行政区划', '乐东黎族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4844, '3', '5', '469028', '46', '陵水黎族自治县', '海南省', '省直辖县级行政区划', '陵水黎族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4845, '3', '5', '469029', '46', '保亭黎族苗族自治县', '海南省', '省直辖县级行政区划', '保亭黎族苗族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4846, '3', '5', '469030', '46', '琼中黎族苗族自治县', '海南省', '省直辖县级行政区划', '琼中黎族苗族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4847, '1', '6', '500000', '50', '重庆', '重庆', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4848, '2', '6', '500100', '50', '重庆市', '重庆', '重庆市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4849, '3', '6', '500101', '50', '万州区', '重庆', '重庆市', '万州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4850, '3', '6', '500102', '50', '涪陵区', '重庆', '重庆市', '涪陵区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4851, '3', '6', '500103', '50', '渝中区', '重庆', '重庆市', '渝中区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4852, '3', '6', '500104', '50', '大渡口区', '重庆', '重庆市', '大渡口区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4853, '3', '6', '500105', '50', '江北区', '重庆', '重庆市', '江北区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4854, '3', '6', '500106', '50', '沙坪坝区', '重庆', '重庆市', '沙坪坝区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4855, '3', '6', '500107', '50', '九龙坡区', '重庆', '重庆市', '九龙坡区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4856, '3', '6', '500108', '50', '南岸区', '重庆', '重庆市', '南岸区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4857, '3', '6', '500109', '50', '北碚区', '重庆', '重庆市', '北碚区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4858, '3', '6', '500110', '50', '綦江区', '重庆', '重庆市', '綦江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4859, '3', '6', '500111', '50', '大足区', '重庆', '重庆市', '大足区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4860, '3', '6', '500112', '50', '渝北区', '重庆', '重庆市', '渝北区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4861, '3', '6', '500113', '50', '巴南区', '重庆', '重庆市', '巴南区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4862, '3', '6', '500114', '50', '黔江区', '重庆', '重庆市', '黔江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4863, '3', '6', '500115', '50', '长寿区', '重庆', '重庆市', '长寿区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4864, '3', '6', '500116', '50', '江津区', '重庆', '重庆市', '江津区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4865, '3', '6', '500117', '50', '合川区', '重庆', '重庆市', '合川区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4866, '3', '6', '500118', '50', '永川区', '重庆', '重庆市', '永川区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4867, '3', '6', '500119', '50', '南川区', '重庆', '重庆市', '南川区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4868, '3', '6', '500120', '50', '璧山区', '重庆', '重庆市', '璧山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4869, '3', '6', '500151', '50', '铜梁区', '重庆', '重庆市', '铜梁区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4870, '3', '6', '500152', '50', '潼南区', '重庆', '重庆市', '潼南区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4871, '3', '6', '500153', '50', '荣昌区', '重庆', '重庆市', '荣昌区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4872, '3', '6', '500154', '50', '开州区', '重庆', '重庆市', '开州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4873, '3', '6', '500155', '50', '梁平区', '重庆', '重庆市', '梁平区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4874, '3', '6', '500156', '50', '武隆区', '重庆', '重庆市', '武隆区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4875, '2', '6', '500200', '50', '重庆县', '重庆', '重庆县', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4876, '3', '6', '500229', '50', '城口县', '重庆', '重庆县', '城口县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4877, '3', '6', '500230', '50', '丰都县', '重庆', '重庆县', '丰都县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4878, '3', '6', '500231', '50', '垫江县', '重庆', '重庆县', '垫江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4879, '3', '6', '500233', '50', '忠县', '重庆', '重庆县', '忠县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4880, '3', '6', '500235', '50', '云阳县', '重庆', '重庆县', '云阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4881, '3', '6', '500236', '50', '奉节县', '重庆', '重庆县', '奉节县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4882, '3', '6', '500237', '50', '巫山县', '重庆', '重庆县', '巫山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4883, '3', '6', '500238', '50', '巫溪县', '重庆', '重庆县', '巫溪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4884, '3', '6', '500240', '50', '石柱土家族自治县', '重庆', '重庆县', '石柱土家族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4885, '3', '6', '500241', '50', '秀山土家族苗族自治县', '重庆', '重庆县', '秀山土家族苗族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4886, '3', '6', '500242', '50', '酉阳土家族苗族自治县', '重庆', '重庆县', '酉阳土家族苗族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4887, '3', '6', '500243', '50', '彭水苗族土家族自治县', '重庆', '重庆县', '彭水苗族土家族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4888, '1', '6', '510000', '51', '四川省', '四川省', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4889, '2', '6', '510100', '51', '成都市', '四川省', '成都市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4890, '3', '6', '510101', '51', '市辖区', '四川省', '成都市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4891, '3', '6', '510104', '51', '锦江区', '四川省', '成都市', '锦江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4892, '3', '6', '510105', '51', '青羊区', '四川省', '成都市', '青羊区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4893, '3', '6', '510106', '51', '金牛区', '四川省', '成都市', '金牛区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4894, '3', '6', '510107', '51', '武侯区', '四川省', '成都市', '武侯区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4895, '3', '6', '510108', '51', '成华区', '四川省', '成都市', '成华区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4896, '3', '6', '510112', '51', '龙泉驿区', '四川省', '成都市', '龙泉驿区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4897, '3', '6', '510113', '51', '青白江区', '四川省', '成都市', '青白江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4898, '3', '6', '510114', '51', '新都区', '四川省', '成都市', '新都区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4899, '3', '6', '510115', '51', '温江区', '四川省', '成都市', '温江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4900, '3', '6', '510121', '51', '金堂县', '四川省', '成都市', '金堂县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4901, '3', '6', '510122', '51', '双流县', '四川省', '成都市', '双流县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4902, '3', '6', '510124', '51', '郫县', '四川省', '成都市', '郫县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4903, '3', '6', '510129', '51', '大邑县', '四川省', '成都市', '大邑县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4904, '3', '6', '510131', '51', '蒲江县', '四川省', '成都市', '蒲江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4905, '3', '6', '510132', '51', '新津县', '四川省', '成都市', '新津县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4906, '3', '6', '510181', '51', '都江堰市', '四川省', '成都市', '都江堰市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4907, '3', '6', '510182', '51', '彭州市', '四川省', '成都市', '彭州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4908, '3', '6', '510183', '51', '邛崃市', '四川省', '成都市', '邛崃市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4909, '3', '6', '510184', '51', '崇州市', '四川省', '成都市', '崇州市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4910, '2', '6', '510300', '51', '自贡市', '四川省', '自贡市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4911, '3', '6', '510301', '51', '市辖区', '四川省', '自贡市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4912, '3', '6', '510302', '51', '自流井区', '四川省', '自贡市', '自流井区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4913, '3', '6', '510303', '51', '贡井区', '四川省', '自贡市', '贡井区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4914, '3', '6', '510304', '51', '大安区', '四川省', '自贡市', '大安区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4915, '3', '6', '510311', '51', '沿滩区', '四川省', '自贡市', '沿滩区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4916, '3', '6', '510321', '51', '荣县', '四川省', '自贡市', '荣县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4917, '3', '6', '510322', '51', '富顺县', '四川省', '自贡市', '富顺县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4918, '2', '6', '510400', '51', '攀枝花市', '四川省', '攀枝花市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4919, '3', '6', '510401', '51', '市辖区', '四川省', '攀枝花市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4920, '3', '6', '510402', '51', '东区', '四川省', '攀枝花市', '东区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4921, '3', '6', '510403', '51', '西区', '四川省', '攀枝花市', '西区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4922, '3', '6', '510411', '51', '仁和区', '四川省', '攀枝花市', '仁和区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4923, '3', '6', '510421', '51', '米易县', '四川省', '攀枝花市', '米易县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4924, '3', '6', '510422', '51', '盐边县', '四川省', '攀枝花市', '盐边县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4925, '2', '6', '510500', '51', '泸州市', '四川省', '泸州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4926, '3', '6', '510501', '51', '市辖区', '四川省', '泸州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4927, '3', '6', '510502', '51', '江阳区', '四川省', '泸州市', '江阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4928, '3', '6', '510503', '51', '纳溪区', '四川省', '泸州市', '纳溪区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4929, '3', '6', '510504', '51', '龙马潭区', '四川省', '泸州市', '龙马潭区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4930, '3', '6', '510521', '51', '泸县', '四川省', '泸州市', '泸县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4931, '3', '6', '510522', '51', '合江县', '四川省', '泸州市', '合江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4932, '3', '6', '510524', '51', '叙永县', '四川省', '泸州市', '叙永县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4933, '3', '6', '510525', '51', '古蔺县', '四川省', '泸州市', '古蔺县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4934, '2', '6', '510600', '51', '德阳市', '四川省', '德阳市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4935, '3', '6', '510601', '51', '市辖区', '四川省', '德阳市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4936, '3', '6', '510603', '51', '旌阳区', '四川省', '德阳市', '旌阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4937, '3', '6', '510623', '51', '中江县', '四川省', '德阳市', '中江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4938, '3', '6', '510626', '51', '罗江县', '四川省', '德阳市', '罗江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4939, '3', '6', '510681', '51', '广汉市', '四川省', '德阳市', '广汉市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4940, '3', '6', '510682', '51', '什邡市', '四川省', '德阳市', '什邡市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4941, '3', '6', '510683', '51', '绵竹市', '四川省', '德阳市', '绵竹市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4942, '2', '6', '510700', '51', '绵阳市', '四川省', '绵阳市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4943, '3', '6', '510701', '51', '市辖区', '四川省', '绵阳市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4944, '3', '6', '510703', '51', '涪城区', '四川省', '绵阳市', '涪城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4945, '3', '6', '510704', '51', '游仙区', '四川省', '绵阳市', '游仙区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4946, '3', '6', '510722', '51', '三台县', '四川省', '绵阳市', '三台县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4947, '3', '6', '510723', '51', '盐亭县', '四川省', '绵阳市', '盐亭县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4948, '3', '6', '510724', '51', '安县', '四川省', '绵阳市', '安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4949, '3', '6', '510725', '51', '梓潼县', '四川省', '绵阳市', '梓潼县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4950, '3', '6', '510726', '51', '北川羌族自治县', '四川省', '绵阳市', '北川羌族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4951, '3', '6', '510727', '51', '平武县', '四川省', '绵阳市', '平武县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4952, '3', '6', '510781', '51', '江油市', '四川省', '绵阳市', '江油市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4953, '2', '6', '510800', '51', '广元市', '四川省', '广元市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4954, '3', '6', '510801', '51', '市辖区', '四川省', '广元市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4955, '3', '6', '510802', '51', '利州区', '四川省', '广元市', '利州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4956, '3', '6', '510811', '51', '昭化区', '四川省', '广元市', '昭化区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4957, '3', '6', '510812', '51', '朝天区', '四川省', '广元市', '朝天区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4958, '3', '6', '510821', '51', '旺苍县', '四川省', '广元市', '旺苍县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4959, '3', '6', '510822', '51', '青川县', '四川省', '广元市', '青川县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4960, '3', '6', '510823', '51', '剑阁县', '四川省', '广元市', '剑阁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4961, '3', '6', '510824', '51', '苍溪县', '四川省', '广元市', '苍溪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4962, '2', '6', '510900', '51', '遂宁市', '四川省', '遂宁市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4963, '3', '6', '510901', '51', '市辖区', '四川省', '遂宁市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4964, '3', '6', '510903', '51', '船山区', '四川省', '遂宁市', '船山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4965, '3', '6', '510904', '51', '安居区', '四川省', '遂宁市', '安居区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4966, '3', '6', '510921', '51', '蓬溪县', '四川省', '遂宁市', '蓬溪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4967, '3', '6', '510922', '51', '射洪县', '四川省', '遂宁市', '射洪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4968, '3', '6', '510923', '51', '大英县', '四川省', '遂宁市', '大英县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4969, '2', '6', '511000', '51', '内江市', '四川省', '内江市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4970, '3', '6', '511001', '51', '市辖区', '四川省', '内江市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4971, '3', '6', '511002', '51', '市中区', '四川省', '内江市', '市中区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4972, '3', '6', '511011', '51', '东兴区', '四川省', '内江市', '东兴区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4973, '3', '6', '511024', '51', '威远县', '四川省', '内江市', '威远县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4974, '3', '6', '511025', '51', '资中县', '四川省', '内江市', '资中县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4975, '3', '6', '511028', '51', '隆昌县', '四川省', '内江市', '隆昌县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4976, '2', '6', '511100', '51', '乐山市', '四川省', '乐山市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4977, '3', '6', '511101', '51', '市辖区', '四川省', '乐山市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4978, '3', '6', '511102', '51', '市中区', '四川省', '乐山市', '市中区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4979, '3', '6', '511111', '51', '沙湾区', '四川省', '乐山市', '沙湾区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4980, '3', '6', '511112', '51', '五通桥区', '四川省', '乐山市', '五通桥区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4981, '3', '6', '511113', '51', '金口河区', '四川省', '乐山市', '金口河区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4982, '3', '6', '511123', '51', '犍为县', '四川省', '乐山市', '犍为县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4983, '3', '6', '511124', '51', '井研县', '四川省', '乐山市', '井研县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4984, '3', '6', '511126', '51', '夹江县', '四川省', '乐山市', '夹江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4985, '3', '6', '511129', '51', '沐川县', '四川省', '乐山市', '沐川县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4986, '3', '6', '511132', '51', '峨边彝族自治县', '四川省', '乐山市', '峨边彝族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4987, '3', '6', '511133', '51', '马边彝族自治县', '四川省', '乐山市', '马边彝族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4988, '3', '6', '511181', '51', '峨眉山市', '四川省', '乐山市', '峨眉山市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4989, '2', '6', '511300', '51', '南充市', '四川省', '南充市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4990, '3', '6', '511301', '51', '市辖区', '四川省', '南充市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4991, '3', '6', '511302', '51', '顺庆区', '四川省', '南充市', '顺庆区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4992, '3', '6', '511303', '51', '高坪区', '四川省', '南充市', '高坪区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4993, '3', '6', '511304', '51', '嘉陵区', '四川省', '南充市', '嘉陵区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4994, '3', '6', '511321', '51', '南部县', '四川省', '南充市', '南部县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4995, '3', '6', '511322', '51', '营山县', '四川省', '南充市', '营山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4996, '3', '6', '511323', '51', '蓬安县', '四川省', '南充市', '蓬安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4997, '3', '6', '511324', '51', '仪陇县', '四川省', '南充市', '仪陇县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4998, '3', '6', '511325', '51', '西充县', '四川省', '南充市', '西充县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (4999, '3', '6', '511381', '51', '阆中市', '四川省', '南充市', '阆中市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5000, '2', '6', '511400', '51', '眉山市', '四川省', '眉山市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5001, '3', '6', '511401', '51', '市辖区', '四川省', '眉山市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5002, '3', '6', '511402', '51', '东坡区', '四川省', '眉山市', '东坡区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5003, '3', '6', '511403', '51', '彭山区', '四川省', '眉山市', '彭山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5004, '3', '6', '511421', '51', '仁寿县', '四川省', '眉山市', '仁寿县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5005, '3', '6', '511423', '51', '洪雅县', '四川省', '眉山市', '洪雅县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5006, '3', '6', '511424', '51', '丹棱县', '四川省', '眉山市', '丹棱县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5007, '3', '6', '511425', '51', '青神县', '四川省', '眉山市', '青神县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5008, '2', '6', '511500', '51', '宜宾市', '四川省', '宜宾市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5009, '3', '6', '511501', '51', '市辖区', '四川省', '宜宾市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5010, '3', '6', '511502', '51', '翠屏区', '四川省', '宜宾市', '翠屏区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5011, '3', '6', '511503', '51', '南溪区', '四川省', '宜宾市', '南溪区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5012, '3', '6', '511521', '51', '宜宾县', '四川省', '宜宾市', '宜宾县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5013, '3', '6', '511523', '51', '江安县', '四川省', '宜宾市', '江安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5014, '3', '6', '511524', '51', '长宁县', '四川省', '宜宾市', '长宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5015, '3', '6', '511525', '51', '高县', '四川省', '宜宾市', '高县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5016, '3', '6', '511526', '51', '珙县', '四川省', '宜宾市', '珙县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5017, '3', '6', '511527', '51', '筠连县', '四川省', '宜宾市', '筠连县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5018, '3', '6', '511528', '51', '兴文县', '四川省', '宜宾市', '兴文县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5019, '3', '6', '511529', '51', '屏山县', '四川省', '宜宾市', '屏山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5020, '2', '6', '511600', '51', '广安市', '四川省', '广安市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5021, '3', '6', '511601', '51', '市辖区', '四川省', '广安市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5022, '3', '6', '511602', '51', '广安区', '四川省', '广安市', '广安区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5023, '3', '6', '511603', '51', '前锋区', '四川省', '广安市', '前锋区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5024, '3', '6', '511621', '51', '岳池县', '四川省', '广安市', '岳池县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5025, '3', '6', '511622', '51', '武胜县', '四川省', '广安市', '武胜县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5026, '3', '6', '511623', '51', '邻水县', '四川省', '广安市', '邻水县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5027, '3', '6', '511681', '51', '华蓥市', '四川省', '广安市', '华蓥市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5028, '2', '6', '511700', '51', '达州市', '四川省', '达州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5029, '3', '6', '511701', '51', '市辖区', '四川省', '达州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5030, '3', '6', '511702', '51', '通川区', '四川省', '达州市', '通川区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5031, '3', '6', '511703', '51', '达川区', '四川省', '达州市', '达川区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5032, '3', '6', '511722', '51', '宣汉县', '四川省', '达州市', '宣汉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5033, '3', '6', '511723', '51', '开江县', '四川省', '达州市', '开江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5034, '3', '6', '511724', '51', '大竹县', '四川省', '达州市', '大竹县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5035, '3', '6', '511725', '51', '渠县', '四川省', '达州市', '渠县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5036, '3', '6', '511781', '51', '万源市', '四川省', '达州市', '万源市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5037, '2', '6', '511800', '51', '雅安市', '四川省', '雅安市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5038, '3', '6', '511801', '51', '市辖区', '四川省', '雅安市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5039, '3', '6', '511802', '51', '雨城区', '四川省', '雅安市', '雨城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5040, '3', '6', '511803', '51', '名山区', '四川省', '雅安市', '名山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5041, '3', '6', '511822', '51', '荥经县', '四川省', '雅安市', '荥经县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5042, '3', '6', '511823', '51', '汉源县', '四川省', '雅安市', '汉源县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5043, '3', '6', '511824', '51', '石棉县', '四川省', '雅安市', '石棉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5044, '3', '6', '511825', '51', '天全县', '四川省', '雅安市', '天全县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5045, '3', '6', '511826', '51', '芦山县', '四川省', '雅安市', '芦山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5046, '3', '6', '511827', '51', '宝兴县', '四川省', '雅安市', '宝兴县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5047, '2', '6', '511900', '51', '巴中市', '四川省', '巴中市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5048, '3', '6', '511901', '51', '市辖区', '四川省', '巴中市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5049, '3', '6', '511902', '51', '巴州区', '四川省', '巴中市', '巴州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5050, '3', '6', '511903', '51', '恩阳区', '四川省', '巴中市', '恩阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5051, '3', '6', '511921', '51', '通江县', '四川省', '巴中市', '通江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5052, '3', '6', '511922', '51', '南江县', '四川省', '巴中市', '南江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5053, '3', '6', '511923', '51', '平昌县', '四川省', '巴中市', '平昌县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5054, '2', '6', '512000', '51', '资阳市', '四川省', '资阳市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5055, '3', '6', '512001', '51', '市辖区', '四川省', '资阳市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5056, '3', '6', '512002', '51', '雁江区', '四川省', '资阳市', '雁江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5057, '3', '6', '512021', '51', '安岳县', '四川省', '资阳市', '安岳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5058, '3', '6', '512022', '51', '乐至县', '四川省', '资阳市', '乐至县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5059, '3', '6', '512081', '51', '简阳市', '四川省', '资阳市', '简阳市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5060, '2', '6', '513200', '51', '阿坝藏族羌族自治州', '四川省', '阿坝藏族羌族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5061, '3', '6', '513221', '51', '汶川县', '四川省', '阿坝藏族羌族自治州', '汶川县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5062, '3', '6', '513222', '51', '理县', '四川省', '阿坝藏族羌族自治州', '理县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5063, '3', '6', '513223', '51', '茂县', '四川省', '阿坝藏族羌族自治州', '茂县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5064, '3', '6', '513224', '51', '松潘县', '四川省', '阿坝藏族羌族自治州', '松潘县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5065, '3', '6', '513225', '51', '九寨沟县', '四川省', '阿坝藏族羌族自治州', '九寨沟县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5066, '3', '6', '513226', '51', '金川县', '四川省', '阿坝藏族羌族自治州', '金川县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5067, '3', '6', '513227', '51', '小金县', '四川省', '阿坝藏族羌族自治州', '小金县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5068, '3', '6', '513228', '51', '黑水县', '四川省', '阿坝藏族羌族自治州', '黑水县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5069, '3', '6', '513229', '51', '马尔康县', '四川省', '阿坝藏族羌族自治州', '马尔康县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5070, '3', '6', '513230', '51', '壤塘县', '四川省', '阿坝藏族羌族自治州', '壤塘县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5071, '3', '6', '513231', '51', '阿坝县', '四川省', '阿坝藏族羌族自治州', '阿坝县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5072, '3', '6', '513232', '51', '若尔盖县', '四川省', '阿坝藏族羌族自治州', '若尔盖县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5073, '3', '6', '513233', '51', '红原县', '四川省', '阿坝藏族羌族自治州', '红原县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5074, '2', '6', '513300', '51', '甘孜藏族自治州', '四川省', '甘孜藏族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5075, '3', '6', '513301', '51', '康定市', '四川省', '甘孜藏族自治州', '康定市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5076, '3', '6', '513322', '51', '泸定县', '四川省', '甘孜藏族自治州', '泸定县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5077, '3', '6', '513323', '51', '丹巴县', '四川省', '甘孜藏族自治州', '丹巴县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5078, '3', '6', '513324', '51', '九龙县', '四川省', '甘孜藏族自治州', '九龙县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5079, '3', '6', '513325', '51', '雅江县', '四川省', '甘孜藏族自治州', '雅江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5080, '3', '6', '513326', '51', '道孚县', '四川省', '甘孜藏族自治州', '道孚县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5081, '3', '6', '513327', '51', '炉霍县', '四川省', '甘孜藏族自治州', '炉霍县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5082, '3', '6', '513328', '51', '甘孜县', '四川省', '甘孜藏族自治州', '甘孜县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5083, '3', '6', '513329', '51', '新龙县', '四川省', '甘孜藏族自治州', '新龙县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5084, '3', '6', '513330', '51', '德格县', '四川省', '甘孜藏族自治州', '德格县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5085, '3', '6', '513331', '51', '白玉县', '四川省', '甘孜藏族自治州', '白玉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5086, '3', '6', '513332', '51', '石渠县', '四川省', '甘孜藏族自治州', '石渠县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5087, '3', '6', '513333', '51', '色达县', '四川省', '甘孜藏族自治州', '色达县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5088, '3', '6', '513334', '51', '理塘县', '四川省', '甘孜藏族自治州', '理塘县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5089, '3', '6', '513335', '51', '巴塘县', '四川省', '甘孜藏族自治州', '巴塘县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5090, '3', '6', '513336', '51', '乡城县', '四川省', '甘孜藏族自治州', '乡城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5091, '3', '6', '513337', '51', '稻城县', '四川省', '甘孜藏族自治州', '稻城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5092, '3', '6', '513338', '51', '得荣县', '四川省', '甘孜藏族自治州', '得荣县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5093, '2', '6', '513400', '51', '凉山彝族自治州', '四川省', '凉山彝族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5094, '3', '6', '513401', '51', '西昌市', '四川省', '凉山彝族自治州', '西昌市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5095, '3', '6', '513422', '51', '木里藏族自治县', '四川省', '凉山彝族自治州', '木里藏族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5096, '3', '6', '513423', '51', '盐源县', '四川省', '凉山彝族自治州', '盐源县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5097, '3', '6', '513424', '51', '德昌县', '四川省', '凉山彝族自治州', '德昌县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5098, '3', '6', '513425', '51', '会理县', '四川省', '凉山彝族自治州', '会理县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5099, '3', '6', '513426', '51', '会东县', '四川省', '凉山彝族自治州', '会东县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5100, '3', '6', '513427', '51', '宁南县', '四川省', '凉山彝族自治州', '宁南县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5101, '3', '6', '513428', '51', '普格县', '四川省', '凉山彝族自治州', '普格县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5102, '3', '6', '513429', '51', '布拖县', '四川省', '凉山彝族自治州', '布拖县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5103, '3', '6', '513430', '51', '金阳县', '四川省', '凉山彝族自治州', '金阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5104, '3', '6', '513431', '51', '昭觉县', '四川省', '凉山彝族自治州', '昭觉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5105, '3', '6', '513432', '51', '喜德县', '四川省', '凉山彝族自治州', '喜德县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5106, '3', '6', '513433', '51', '冕宁县', '四川省', '凉山彝族自治州', '冕宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5107, '3', '6', '513434', '51', '越西县', '四川省', '凉山彝族自治州', '越西县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5108, '3', '6', '513435', '51', '甘洛县', '四川省', '凉山彝族自治州', '甘洛县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5109, '3', '6', '513436', '51', '美姑县', '四川省', '凉山彝族自治州', '美姑县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5110, '3', '6', '513437', '51', '雷波县', '四川省', '凉山彝族自治州', '雷波县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5111, '1', '6', '520000', '52', '贵州省', '贵州省', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5112, '2', '6', '520100', '52', '贵阳市', '贵州省', '贵阳市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5113, '3', '6', '520101', '52', '市辖区', '贵州省', '贵阳市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5114, '3', '6', '520102', '52', '南明区', '贵州省', '贵阳市', '南明区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5115, '3', '6', '520103', '52', '云岩区', '贵州省', '贵阳市', '云岩区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5116, '3', '6', '520111', '52', '花溪区', '贵州省', '贵阳市', '花溪区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5117, '3', '6', '520112', '52', '乌当区', '贵州省', '贵阳市', '乌当区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5118, '3', '6', '520113', '52', '白云区', '贵州省', '贵阳市', '白云区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5119, '3', '6', '520115', '52', '观山湖区', '贵州省', '贵阳市', '观山湖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5120, '3', '6', '520121', '52', '开阳县', '贵州省', '贵阳市', '开阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5121, '3', '6', '520122', '52', '息烽县', '贵州省', '贵阳市', '息烽县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5122, '3', '6', '520123', '52', '修文县', '贵州省', '贵阳市', '修文县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5123, '3', '6', '520181', '52', '清镇市', '贵州省', '贵阳市', '清镇市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5124, '3', '6', '520182', '52', '高新区', '贵州省', '贵阳市', '高新区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5125, '3', '6', '520183', '52', '贵安新区', '贵州省', '贵阳市', '贵安新区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5126, '2', '6', '520200', '52', '六盘水市', '贵州省', '六盘水市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5127, '3', '6', '520201', '52', '钟山区', '贵州省', '六盘水市', '钟山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5128, '3', '6', '520203', '52', '六枝特区', '贵州省', '六盘水市', '六枝特区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5129, '3', '6', '520221', '52', '水城县', '贵州省', '六盘水市', '水城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5130, '3', '6', '520222', '52', '盘县', '贵州省', '六盘水市', '盘县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5131, '2', '6', '520300', '52', '遵义市', '贵州省', '遵义市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5132, '3', '6', '520301', '52', '市辖区', '贵州省', '遵义市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5133, '3', '6', '520302', '52', '红花岗区', '贵州省', '遵义市', '红花岗区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5134, '3', '6', '520303', '52', '汇川区', '贵州省', '遵义市', '汇川区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5135, '3', '6', '520321', '52', '遵义县', '贵州省', '遵义市', '遵义县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5136, '3', '6', '520322', '52', '桐梓县', '贵州省', '遵义市', '桐梓县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5137, '3', '6', '520323', '52', '绥阳县', '贵州省', '遵义市', '绥阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5138, '3', '6', '520324', '52', '正安县', '贵州省', '遵义市', '正安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5139, '3', '6', '520325', '52', '道真仡佬族苗族自治县', '贵州省', '遵义市', '道真仡佬族苗族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5140, '3', '6', '520326', '52', '务川仡佬族苗族自治县', '贵州省', '遵义市', '务川仡佬族苗族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5141, '3', '6', '520327', '52', '凤冈县', '贵州省', '遵义市', '凤冈县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5142, '3', '6', '520328', '52', '湄潭县', '贵州省', '遵义市', '湄潭县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5143, '3', '6', '520329', '52', '余庆县', '贵州省', '遵义市', '余庆县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5144, '3', '6', '520330', '52', '习水县', '贵州省', '遵义市', '习水县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5145, '3', '6', '520381', '52', '赤水市', '贵州省', '遵义市', '赤水市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5146, '3', '6', '520382', '52', '仁怀市', '贵州省', '遵义市', '仁怀市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5147, '2', '6', '520400', '52', '安顺市', '贵州省', '安顺市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5148, '3', '6', '520401', '52', '市辖区', '贵州省', '安顺市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5149, '3', '6', '520402', '52', '西秀区', '贵州省', '安顺市', '西秀区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5150, '3', '6', '520403', '52', '平坝区', '贵州省', '安顺市', '平坝区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5151, '3', '6', '520422', '52', '普定县', '贵州省', '安顺市', '普定县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5152, '3', '6', '520423', '52', '镇宁布依族苗族自治县', '贵州省', '安顺市', '镇宁布依族苗族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5153, '3', '6', '520424', '52', '关岭布依族苗族自治县', '贵州省', '安顺市', '关岭布依族苗族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5154, '3', '6', '520425', '52', '紫云苗族布依族自治县', '贵州省', '安顺市', '紫云苗族布依族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5155, '2', '6', '520500', '52', '毕节市', '贵州省', '毕节市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5156, '3', '6', '520501', '52', '市辖区', '贵州省', '毕节市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5157, '3', '6', '520502', '52', '七星关区', '贵州省', '毕节市', '七星关区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5158, '3', '6', '520521', '52', '大方县', '贵州省', '毕节市', '大方县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5159, '3', '6', '520522', '52', '黔西县', '贵州省', '毕节市', '黔西县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5160, '3', '6', '520523', '52', '金沙县', '贵州省', '毕节市', '金沙县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5161, '3', '6', '520524', '52', '织金县', '贵州省', '毕节市', '织金县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5162, '3', '6', '520525', '52', '纳雍县', '贵州省', '毕节市', '纳雍县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5163, '3', '6', '520526', '52', '威宁彝族回族苗族自治县', '贵州省', '毕节市', '威宁彝族回族苗族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5164, '3', '6', '520527', '52', '赫章县', '贵州省', '毕节市', '赫章县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5165, '2', '6', '520600', '52', '铜仁市', '贵州省', '铜仁市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5166, '3', '6', '520601', '52', '市辖区', '贵州省', '铜仁市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5167, '3', '6', '520602', '52', '碧江区', '贵州省', '铜仁市', '碧江区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5168, '3', '6', '520603', '52', '万山区', '贵州省', '铜仁市', '万山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5169, '3', '6', '520621', '52', '江口县', '贵州省', '铜仁市', '江口县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5170, '3', '6', '520622', '52', '玉屏侗族自治县', '贵州省', '铜仁市', '玉屏侗族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5171, '3', '6', '520623', '52', '石阡县', '贵州省', '铜仁市', '石阡县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5172, '3', '6', '520624', '52', '思南县', '贵州省', '铜仁市', '思南县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5173, '3', '6', '520625', '52', '印江土家族苗族自治县', '贵州省', '铜仁市', '印江土家族苗族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5174, '3', '6', '520626', '52', '德江县', '贵州省', '铜仁市', '德江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5175, '3', '6', '520627', '52', '沿河土家族自治县', '贵州省', '铜仁市', '沿河土家族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5176, '3', '6', '520628', '52', '松桃苗族自治县', '贵州省', '铜仁市', '松桃苗族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5177, '2', '6', '522300', '52', '黔西南布依族苗族自治州', '贵州省', '黔西南布依族苗族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5178, '3', '6', '522301', '52', '兴义市', '贵州省', '黔西南布依族苗族自治州', '兴义市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5179, '3', '6', '522322', '52', '兴仁县', '贵州省', '黔西南布依族苗族自治州', '兴仁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5180, '3', '6', '522323', '52', '普安县', '贵州省', '黔西南布依族苗族自治州', '普安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5181, '3', '6', '522324', '52', '晴隆县', '贵州省', '黔西南布依族苗族自治州', '晴隆县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5182, '3', '6', '522325', '52', '贞丰县', '贵州省', '黔西南布依族苗族自治州', '贞丰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5183, '3', '6', '522326', '52', '望谟县', '贵州省', '黔西南布依族苗族自治州', '望谟县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5184, '3', '6', '522327', '52', '册亨县', '贵州省', '黔西南布依族苗族自治州', '册亨县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5185, '3', '6', '522328', '52', '安龙县', '贵州省', '黔西南布依族苗族自治州', '安龙县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5186, '2', '6', '522600', '52', '黔东南苗族侗族自治州', '贵州省', '黔东南苗族侗族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5187, '3', '6', '522601', '52', '凯里市', '贵州省', '黔东南苗族侗族自治州', '凯里市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5188, '3', '6', '522622', '52', '黄平县', '贵州省', '黔东南苗族侗族自治州', '黄平县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5189, '3', '6', '522623', '52', '施秉县', '贵州省', '黔东南苗族侗族自治州', '施秉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5190, '3', '6', '522624', '52', '三穗县', '贵州省', '黔东南苗族侗族自治州', '三穗县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5191, '3', '6', '522625', '52', '镇远县', '贵州省', '黔东南苗族侗族自治州', '镇远县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5192, '3', '6', '522626', '52', '岑巩县', '贵州省', '黔东南苗族侗族自治州', '岑巩县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5193, '3', '6', '522627', '52', '天柱县', '贵州省', '黔东南苗族侗族自治州', '天柱县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5194, '3', '6', '522628', '52', '锦屏县', '贵州省', '黔东南苗族侗族自治州', '锦屏县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5195, '3', '6', '522629', '52', '剑河县', '贵州省', '黔东南苗族侗族自治州', '剑河县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5196, '3', '6', '522630', '52', '台江县', '贵州省', '黔东南苗族侗族自治州', '台江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5197, '3', '6', '522631', '52', '黎平县', '贵州省', '黔东南苗族侗族自治州', '黎平县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5198, '3', '6', '522632', '52', '榕江县', '贵州省', '黔东南苗族侗族自治州', '榕江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5199, '3', '6', '522633', '52', '从江县', '贵州省', '黔东南苗族侗族自治州', '从江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5200, '3', '6', '522634', '52', '雷山县', '贵州省', '黔东南苗族侗族自治州', '雷山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5201, '3', '6', '522635', '52', '麻江县', '贵州省', '黔东南苗族侗族自治州', '麻江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5202, '3', '6', '522636', '52', '丹寨县', '贵州省', '黔东南苗族侗族自治州', '丹寨县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5203, '2', '6', '522700', '52', '黔南布依族苗族自治州', '贵州省', '黔南布依族苗族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5204, '3', '6', '522701', '52', '都匀市', '贵州省', '黔南布依族苗族自治州', '都匀市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5205, '3', '6', '522702', '52', '福泉市', '贵州省', '黔南布依族苗族自治州', '福泉市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5206, '3', '6', '522722', '52', '荔波县', '贵州省', '黔南布依族苗族自治州', '荔波县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5207, '3', '6', '522723', '52', '贵定县', '贵州省', '黔南布依族苗族自治州', '贵定县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5208, '3', '6', '522725', '52', '瓮安县', '贵州省', '黔南布依族苗族自治州', '瓮安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5209, '3', '6', '522726', '52', '独山县', '贵州省', '黔南布依族苗族自治州', '独山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5210, '3', '6', '522727', '52', '平塘县', '贵州省', '黔南布依族苗族自治州', '平塘县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5211, '3', '6', '522728', '52', '罗甸县', '贵州省', '黔南布依族苗族自治州', '罗甸县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5212, '3', '6', '522729', '52', '长顺县', '贵州省', '黔南布依族苗族自治州', '长顺县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5213, '3', '6', '522730', '52', '龙里县', '贵州省', '黔南布依族苗族自治州', '龙里县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5214, '3', '6', '522731', '52', '惠水县', '贵州省', '黔南布依族苗族自治州', '惠水县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5215, '3', '6', '522732', '52', '三都水族自治县', '贵州省', '黔南布依族苗族自治州', '三都水族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5216, '1', '6', '530000', '53', '云南省', '云南省', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5217, '2', '6', '530100', '53', '昆明市', '云南省', '昆明市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5218, '3', '6', '530101', '53', '市辖区', '云南省', '昆明市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5219, '3', '6', '530102', '53', '五华区', '云南省', '昆明市', '五华区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5220, '3', '6', '530103', '53', '盘龙区', '云南省', '昆明市', '盘龙区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5221, '3', '6', '530111', '53', '官渡区', '云南省', '昆明市', '官渡区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5222, '3', '6', '530112', '53', '西山区', '云南省', '昆明市', '西山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5223, '3', '6', '530113', '53', '东川区', '云南省', '昆明市', '东川区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5224, '3', '6', '530114', '53', '呈贡区', '云南省', '昆明市', '呈贡区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5225, '3', '6', '530122', '53', '晋宁县', '云南省', '昆明市', '晋宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5226, '3', '6', '530124', '53', '富民县', '云南省', '昆明市', '富民县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5227, '3', '6', '530125', '53', '宜良县', '云南省', '昆明市', '宜良县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5228, '3', '6', '530126', '53', '石林彝族自治县', '云南省', '昆明市', '石林彝族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5229, '3', '6', '530127', '53', '嵩明县', '云南省', '昆明市', '嵩明县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5230, '3', '6', '530128', '53', '禄劝彝族苗族自治县', '云南省', '昆明市', '禄劝彝族苗族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5231, '3', '6', '530129', '53', '寻甸回族彝族自治县', '云南省', '昆明市', '寻甸回族彝族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5232, '3', '6', '530181', '53', '安宁市', '云南省', '昆明市', '安宁市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5233, '2', '6', '530300', '53', '曲靖市', '云南省', '曲靖市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5234, '3', '6', '530301', '53', '市辖区', '云南省', '曲靖市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5235, '3', '6', '530302', '53', '麒麟区', '云南省', '曲靖市', '麒麟区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5236, '3', '6', '530321', '53', '马龙县', '云南省', '曲靖市', '马龙县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5237, '3', '6', '530322', '53', '陆良县', '云南省', '曲靖市', '陆良县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5238, '3', '6', '530323', '53', '师宗县', '云南省', '曲靖市', '师宗县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5239, '3', '6', '530324', '53', '罗平县', '云南省', '曲靖市', '罗平县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5240, '3', '6', '530325', '53', '富源县', '云南省', '曲靖市', '富源县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5241, '3', '6', '530326', '53', '会泽县', '云南省', '曲靖市', '会泽县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5242, '3', '6', '530328', '53', '沾益县', '云南省', '曲靖市', '沾益县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5243, '3', '6', '530381', '53', '宣威市', '云南省', '曲靖市', '宣威市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5244, '2', '6', '530400', '53', '玉溪市', '云南省', '玉溪市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5245, '3', '6', '530401', '53', '市辖区', '云南省', '玉溪市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5246, '3', '6', '530402', '53', '红塔区', '云南省', '玉溪市', '红塔区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5247, '3', '6', '530421', '53', '江川县', '云南省', '玉溪市', '江川县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5248, '3', '6', '530422', '53', '澄江县', '云南省', '玉溪市', '澄江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5249, '3', '6', '530423', '53', '通海县', '云南省', '玉溪市', '通海县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5250, '3', '6', '530424', '53', '华宁县', '云南省', '玉溪市', '华宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5251, '3', '6', '530425', '53', '易门县', '云南省', '玉溪市', '易门县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5252, '3', '6', '530426', '53', '峨山彝族自治县', '云南省', '玉溪市', '峨山彝族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5253, '3', '6', '530427', '53', '新平彝族傣族自治县', '云南省', '玉溪市', '新平彝族傣族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5254, '3', '6', '530428', '53', '元江哈尼族彝族傣族自治县', '云南省', '玉溪市', '元江哈尼族彝族傣族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5255, '2', '6', '530500', '53', '保山市', '云南省', '保山市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5256, '3', '6', '530501', '53', '市辖区', '云南省', '保山市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5257, '3', '6', '530502', '53', '隆阳区', '云南省', '保山市', '隆阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5258, '3', '6', '530521', '53', '施甸县', '云南省', '保山市', '施甸县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5259, '3', '6', '530523', '53', '龙陵县', '云南省', '保山市', '龙陵县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5260, '3', '6', '530524', '53', '昌宁县', '云南省', '保山市', '昌宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5261, '3', '6', '530581', '53', '腾冲市', '云南省', '保山市', '腾冲市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5262, '2', '6', '530600', '53', '昭通市', '云南省', '昭通市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5263, '3', '6', '530601', '53', '市辖区', '云南省', '昭通市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5264, '3', '6', '530602', '53', '昭阳区', '云南省', '昭通市', '昭阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5265, '3', '6', '530621', '53', '鲁甸县', '云南省', '昭通市', '鲁甸县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5266, '3', '6', '530622', '53', '巧家县', '云南省', '昭通市', '巧家县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5267, '3', '6', '530623', '53', '盐津县', '云南省', '昭通市', '盐津县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5268, '3', '6', '530624', '53', '大关县', '云南省', '昭通市', '大关县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5269, '3', '6', '530625', '53', '永善县', '云南省', '昭通市', '永善县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5270, '3', '6', '530626', '53', '绥江县', '云南省', '昭通市', '绥江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5271, '3', '6', '530627', '53', '镇雄县', '云南省', '昭通市', '镇雄县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5272, '3', '6', '530628', '53', '彝良县', '云南省', '昭通市', '彝良县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5273, '3', '6', '530629', '53', '威信县', '云南省', '昭通市', '威信县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5274, '3', '6', '530630', '53', '水富县', '云南省', '昭通市', '水富县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5275, '2', '6', '530700', '53', '丽江市', '云南省', '丽江市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5276, '3', '6', '530701', '53', '市辖区', '云南省', '丽江市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5277, '3', '6', '530702', '53', '古城区', '云南省', '丽江市', '古城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5278, '3', '6', '530721', '53', '玉龙纳西族自治县', '云南省', '丽江市', '玉龙纳西族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5279, '3', '6', '530722', '53', '永胜县', '云南省', '丽江市', '永胜县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5280, '3', '6', '530723', '53', '华坪县', '云南省', '丽江市', '华坪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5281, '3', '6', '530724', '53', '宁蒗彝族自治县', '云南省', '丽江市', '宁蒗彝族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5282, '2', '6', '530800', '53', '普洱市', '云南省', '普洱市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5283, '3', '6', '530801', '53', '市辖区', '云南省', '普洱市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5284, '3', '6', '530802', '53', '思茅区', '云南省', '普洱市', '思茅区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5285, '3', '6', '530821', '53', '宁洱哈尼族彝族自治县', '云南省', '普洱市', '宁洱哈尼族彝族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5286, '3', '6', '530822', '53', '墨江哈尼族自治县', '云南省', '普洱市', '墨江哈尼族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5287, '3', '6', '530823', '53', '景东彝族自治县', '云南省', '普洱市', '景东彝族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5288, '3', '6', '530824', '53', '景谷傣族彝族自治县', '云南省', '普洱市', '景谷傣族彝族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5289, '3', '6', '530825', '53', '镇沅彝族哈尼族拉祜族自治县', '云南省', '普洱市', '镇沅彝族哈尼族拉祜族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5290, '3', '6', '530826', '53', '江城哈尼族彝族自治县', '云南省', '普洱市', '江城哈尼族彝族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5291, '3', '6', '530827', '53', '孟连傣族拉祜族佤族自治县', '云南省', '普洱市', '孟连傣族拉祜族佤族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5292, '3', '6', '530828', '53', '澜沧拉祜族自治县', '云南省', '普洱市', '澜沧拉祜族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5293, '3', '6', '530829', '53', '西盟佤族自治县', '云南省', '普洱市', '西盟佤族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5294, '2', '6', '530900', '53', '临沧市', '云南省', '临沧市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5295, '3', '6', '530901', '53', '市辖区', '云南省', '临沧市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5296, '3', '6', '530902', '53', '临翔区', '云南省', '临沧市', '临翔区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5297, '3', '6', '530921', '53', '凤庆县', '云南省', '临沧市', '凤庆县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5298, '3', '6', '530922', '53', '云县', '云南省', '临沧市', '云县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5299, '3', '6', '530923', '53', '永德县', '云南省', '临沧市', '永德县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5300, '3', '6', '530924', '53', '镇康县', '云南省', '临沧市', '镇康县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5301, '3', '6', '530925', '53', '双江拉祜族佤族布朗族傣族自治县', '云南省', '临沧市', '双江拉祜族佤族布朗族傣族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5302, '3', '6', '530926', '53', '耿马傣族佤族自治县', '云南省', '临沧市', '耿马傣族佤族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5303, '3', '6', '530927', '53', '沧源佤族自治县', '云南省', '临沧市', '沧源佤族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5304, '2', '6', '532300', '53', '楚雄彝族自治州', '云南省', '楚雄彝族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5305, '3', '6', '532301', '53', '楚雄市', '云南省', '楚雄彝族自治州', '楚雄市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5306, '3', '6', '532322', '53', '双柏县', '云南省', '楚雄彝族自治州', '双柏县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5307, '3', '6', '532323', '53', '牟定县', '云南省', '楚雄彝族自治州', '牟定县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5308, '3', '6', '532324', '53', '南华县', '云南省', '楚雄彝族自治州', '南华县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5309, '3', '6', '532325', '53', '姚安县', '云南省', '楚雄彝族自治州', '姚安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5310, '3', '6', '532326', '53', '大姚县', '云南省', '楚雄彝族自治州', '大姚县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5311, '3', '6', '532327', '53', '永仁县', '云南省', '楚雄彝族自治州', '永仁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5312, '3', '6', '532328', '53', '元谋县', '云南省', '楚雄彝族自治州', '元谋县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5313, '3', '6', '532329', '53', '武定县', '云南省', '楚雄彝族自治州', '武定县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5314, '3', '6', '532331', '53', '禄丰县', '云南省', '楚雄彝族自治州', '禄丰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5315, '2', '6', '532500', '53', '红河哈尼族彝族自治州', '云南省', '红河哈尼族彝族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5316, '3', '6', '532501', '53', '个旧市', '云南省', '红河哈尼族彝族自治州', '个旧市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5317, '3', '6', '532502', '53', '开远市', '云南省', '红河哈尼族彝族自治州', '开远市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5318, '3', '6', '532503', '53', '蒙自市', '云南省', '红河哈尼族彝族自治州', '蒙自市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5319, '3', '6', '532504', '53', '弥勒市', '云南省', '红河哈尼族彝族自治州', '弥勒市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5320, '3', '6', '532523', '53', '屏边苗族自治县', '云南省', '红河哈尼族彝族自治州', '屏边苗族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5321, '3', '6', '532524', '53', '建水县', '云南省', '红河哈尼族彝族自治州', '建水县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5322, '3', '6', '532525', '53', '石屏县', '云南省', '红河哈尼族彝族自治州', '石屏县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5323, '3', '6', '532527', '53', '泸西县', '云南省', '红河哈尼族彝族自治州', '泸西县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5324, '3', '6', '532528', '53', '元阳县', '云南省', '红河哈尼族彝族自治州', '元阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5325, '3', '6', '532529', '53', '红河县', '云南省', '红河哈尼族彝族自治州', '红河县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5326, '3', '6', '532530', '53', '金平苗族瑶族傣族自治县', '云南省', '红河哈尼族彝族自治州', '金平苗族瑶族傣族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5327, '3', '6', '532531', '53', '绿春县', '云南省', '红河哈尼族彝族自治州', '绿春县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5328, '3', '6', '532532', '53', '河口瑶族自治县', '云南省', '红河哈尼族彝族自治州', '河口瑶族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5329, '2', '6', '532600', '53', '文山壮族苗族自治州', '云南省', '文山壮族苗族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5330, '3', '6', '532601', '53', '文山市', '云南省', '文山壮族苗族自治州', '文山市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5331, '3', '6', '532622', '53', '砚山县', '云南省', '文山壮族苗族自治州', '砚山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5332, '3', '6', '532623', '53', '西畴县', '云南省', '文山壮族苗族自治州', '西畴县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5333, '3', '6', '532624', '53', '麻栗坡县', '云南省', '文山壮族苗族自治州', '麻栗坡县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5334, '3', '6', '532625', '53', '马关县', '云南省', '文山壮族苗族自治州', '马关县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5335, '3', '6', '532626', '53', '丘北县', '云南省', '文山壮族苗族自治州', '丘北县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5336, '3', '6', '532627', '53', '广南县', '云南省', '文山壮族苗族自治州', '广南县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5337, '3', '6', '532628', '53', '富宁县', '云南省', '文山壮族苗族自治州', '富宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5338, '2', '6', '532800', '53', '西双版纳傣族自治州', '云南省', '西双版纳傣族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5339, '3', '6', '532801', '53', '景洪市', '云南省', '西双版纳傣族自治州', '景洪市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5340, '3', '6', '532822', '53', '勐海县', '云南省', '西双版纳傣族自治州', '勐海县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5341, '3', '6', '532823', '53', '勐腊县', '云南省', '西双版纳傣族自治州', '勐腊县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5342, '2', '6', '532900', '53', '大理白族自治州', '云南省', '大理白族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5343, '3', '6', '532901', '53', '大理市', '云南省', '大理白族自治州', '大理市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5344, '3', '6', '532922', '53', '漾濞彝族自治县', '云南省', '大理白族自治州', '漾濞彝族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5345, '3', '6', '532923', '53', '祥云县', '云南省', '大理白族自治州', '祥云县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5346, '3', '6', '532924', '53', '宾川县', '云南省', '大理白族自治州', '宾川县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5347, '3', '6', '532925', '53', '弥渡县', '云南省', '大理白族自治州', '弥渡县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5348, '3', '6', '532926', '53', '南涧彝族自治县', '云南省', '大理白族自治州', '南涧彝族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5349, '3', '6', '532927', '53', '巍山彝族回族自治县', '云南省', '大理白族自治州', '巍山彝族回族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5350, '3', '6', '532928', '53', '永平县', '云南省', '大理白族自治州', '永平县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5351, '3', '6', '532929', '53', '云龙县', '云南省', '大理白族自治州', '云龙县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5352, '3', '6', '532930', '53', '洱源县', '云南省', '大理白族自治州', '洱源县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5353, '3', '6', '532931', '53', '剑川县', '云南省', '大理白族自治州', '剑川县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5354, '3', '6', '532932', '53', '鹤庆县', '云南省', '大理白族自治州', '鹤庆县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5355, '2', '6', '533100', '53', '德宏傣族景颇族自治州', '云南省', '德宏傣族景颇族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5356, '3', '6', '533102', '53', '瑞丽市', '云南省', '德宏傣族景颇族自治州', '瑞丽市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5357, '3', '6', '533103', '53', '芒市', '云南省', '德宏傣族景颇族自治州', '芒市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5358, '3', '6', '533122', '53', '梁河县', '云南省', '德宏傣族景颇族自治州', '梁河县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5359, '3', '6', '533123', '53', '盈江县', '云南省', '德宏傣族景颇族自治州', '盈江县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5360, '3', '6', '533124', '53', '陇川县', '云南省', '德宏傣族景颇族自治州', '陇川县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5361, '2', '6', '533300', '53', '怒江傈僳族自治州', '云南省', '怒江傈僳族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5362, '3', '6', '533321', '53', '泸水县', '云南省', '怒江傈僳族自治州', '泸水县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5363, '3', '6', '533323', '53', '福贡县', '云南省', '怒江傈僳族自治州', '福贡县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5364, '3', '6', '533324', '53', '贡山独龙族怒族自治县', '云南省', '怒江傈僳族自治州', '贡山独龙族怒族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5365, '3', '6', '533325', '53', '兰坪白族普米族自治县', '云南省', '怒江傈僳族自治州', '兰坪白族普米族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5366, '2', '6', '533400', '53', '迪庆藏族自治州', '云南省', '迪庆藏族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5367, '3', '6', '533401', '53', '香格里拉市', '云南省', '迪庆藏族自治州', '香格里拉市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5368, '3', '6', '533422', '53', '德钦县', '云南省', '迪庆藏族自治州', '德钦县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5369, '3', '6', '533423', '53', '维西傈僳族自治县', '云南省', '迪庆藏族自治州', '维西傈僳族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5370, '1', '6', '540000', '54', '西藏自治区', '西藏自治区', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5371, '2', '6', '540100', '54', '拉萨市', '西藏自治区', '拉萨市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5372, '3', '6', '540101', '54', '市辖区', '西藏自治区', '拉萨市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5373, '3', '6', '540102', '54', '城关区', '西藏自治区', '拉萨市', '城关区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5374, '3', '6', '540121', '54', '林周县', '西藏自治区', '拉萨市', '林周县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5375, '3', '6', '540122', '54', '当雄县', '西藏自治区', '拉萨市', '当雄县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5376, '3', '6', '540123', '54', '尼木县', '西藏自治区', '拉萨市', '尼木县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5377, '3', '6', '540124', '54', '曲水县', '西藏自治区', '拉萨市', '曲水县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5378, '3', '6', '540125', '54', '堆龙德庆县', '西藏自治区', '拉萨市', '堆龙德庆县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5379, '3', '6', '540126', '54', '达孜县', '西藏自治区', '拉萨市', '达孜县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5380, '3', '6', '540127', '54', '墨竹工卡县', '西藏自治区', '拉萨市', '墨竹工卡县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5381, '2', '6', '540200', '54', '日喀则市', '西藏自治区', '日喀则市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5382, '3', '6', '540202', '54', '桑珠孜区', '西藏自治区', '日喀则市', '桑珠孜区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5383, '3', '6', '540221', '54', '南木林县', '西藏自治区', '日喀则市', '南木林县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5384, '3', '6', '540222', '54', '江孜县', '西藏自治区', '日喀则市', '江孜县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5385, '3', '6', '540223', '54', '定日县', '西藏自治区', '日喀则市', '定日县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5386, '3', '6', '540224', '54', '萨迦县', '西藏自治区', '日喀则市', '萨迦县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5387, '3', '6', '540225', '54', '拉孜县', '西藏自治区', '日喀则市', '拉孜县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5388, '3', '6', '540226', '54', '昂仁县', '西藏自治区', '日喀则市', '昂仁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5389, '3', '6', '540227', '54', '谢通门县', '西藏自治区', '日喀则市', '谢通门县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5390, '3', '6', '540228', '54', '白朗县', '西藏自治区', '日喀则市', '白朗县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5391, '3', '6', '540229', '54', '仁布县', '西藏自治区', '日喀则市', '仁布县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5392, '3', '6', '540230', '54', '康马县', '西藏自治区', '日喀则市', '康马县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5393, '3', '6', '540231', '54', '定结县', '西藏自治区', '日喀则市', '定结县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5394, '3', '6', '540232', '54', '仲巴县', '西藏自治区', '日喀则市', '仲巴县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5395, '3', '6', '540233', '54', '亚东县', '西藏自治区', '日喀则市', '亚东县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5396, '3', '6', '540234', '54', '吉隆县', '西藏自治区', '日喀则市', '吉隆县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5397, '3', '6', '540235', '54', '聂拉木县', '西藏自治区', '日喀则市', '聂拉木县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5398, '3', '6', '540236', '54', '萨嘎县', '西藏自治区', '日喀则市', '萨嘎县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5399, '3', '6', '540237', '54', '岗巴县', '西藏自治区', '日喀则市', '岗巴县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5400, '2', '6', '540300', '54', '昌都市', '西藏自治区', '昌都市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5401, '3', '6', '540302', '54', '卡若区', '西藏自治区', '昌都市', '卡若区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5402, '3', '6', '540321', '54', '江达县', '西藏自治区', '昌都市', '江达县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5403, '3', '6', '540322', '54', '贡觉县', '西藏自治区', '昌都市', '贡觉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5404, '3', '6', '540323', '54', '类乌齐县', '西藏自治区', '昌都市', '类乌齐县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5405, '3', '6', '540324', '54', '丁青县', '西藏自治区', '昌都市', '丁青县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5406, '3', '6', '540325', '54', '察雅县', '西藏自治区', '昌都市', '察雅县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5407, '3', '6', '540326', '54', '八宿县', '西藏自治区', '昌都市', '八宿县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5408, '3', '6', '540327', '54', '左贡县', '西藏自治区', '昌都市', '左贡县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5409, '3', '6', '540328', '54', '芒康县', '西藏自治区', '昌都市', '芒康县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5410, '3', '6', '540329', '54', '洛隆县', '西藏自治区', '昌都市', '洛隆县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5411, '3', '6', '540330', '54', '边坝县', '西藏自治区', '昌都市', '边坝县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5412, '2', '6', '540400', '54', '林芝市', '西藏自治区', '林芝市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5413, '3', '6', '540402', '54', '巴宜区', '西藏自治区', '林芝市', '巴宜区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5414, '3', '6', '540421', '54', '工布江达县', '西藏自治区', '林芝市', '工布江达县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5415, '3', '6', '540422', '54', '米林县', '西藏自治区', '林芝市', '米林县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5416, '3', '6', '540423', '54', '墨脱县', '西藏自治区', '林芝市', '墨脱县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5417, '3', '6', '540424', '54', '波密县', '西藏自治区', '林芝市', '波密县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5418, '3', '6', '540425', '54', '察隅县', '西藏自治区', '林芝市', '察隅县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5419, '3', '6', '540426', '54', '朗县', '西藏自治区', '林芝市', '朗县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5420, '2', '6', '542200', '54', '山南地区', '西藏自治区', '山南地区', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5421, '3', '6', '542221', '54', '乃东县', '西藏自治区', '山南地区', '乃东县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5422, '3', '6', '542222', '54', '扎囊县', '西藏自治区', '山南地区', '扎囊县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5423, '3', '6', '542223', '54', '贡嘎县', '西藏自治区', '山南地区', '贡嘎县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5424, '3', '6', '542224', '54', '桑日县', '西藏自治区', '山南地区', '桑日县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5425, '3', '6', '542225', '54', '琼结县', '西藏自治区', '山南地区', '琼结县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5426, '3', '6', '542226', '54', '曲松县', '西藏自治区', '山南地区', '曲松县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5427, '3', '6', '542227', '54', '措美县', '西藏自治区', '山南地区', '措美县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5428, '3', '6', '542228', '54', '洛扎县', '西藏自治区', '山南地区', '洛扎县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5429, '3', '6', '542229', '54', '加查县', '西藏自治区', '山南地区', '加查县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5430, '3', '6', '542231', '54', '隆子县', '西藏自治区', '山南地区', '隆子县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5431, '3', '6', '542232', '54', '错那县', '西藏自治区', '山南地区', '错那县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5432, '3', '6', '542233', '54', '浪卡子县', '西藏自治区', '山南地区', '浪卡子县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5433, '2', '6', '542400', '54', '那曲地区', '西藏自治区', '那曲地区', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5434, '3', '6', '542421', '54', '那曲县', '西藏自治区', '那曲地区', '那曲县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5435, '3', '6', '542422', '54', '嘉黎县', '西藏自治区', '那曲地区', '嘉黎县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5436, '3', '6', '542423', '54', '比如县', '西藏自治区', '那曲地区', '比如县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5437, '3', '6', '542424', '54', '聂荣县', '西藏自治区', '那曲地区', '聂荣县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5438, '3', '6', '542425', '54', '安多县', '西藏自治区', '那曲地区', '安多县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5439, '3', '6', '542426', '54', '申扎县', '西藏自治区', '那曲地区', '申扎县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5440, '3', '6', '542427', '54', '索县', '西藏自治区', '那曲地区', '索县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5441, '3', '6', '542428', '54', '班戈县', '西藏自治区', '那曲地区', '班戈县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5442, '3', '6', '542429', '54', '巴青县', '西藏自治区', '那曲地区', '巴青县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5443, '3', '6', '542430', '54', '尼玛县', '西藏自治区', '那曲地区', '尼玛县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5444, '3', '6', '542431', '54', '双湖县', '西藏自治区', '那曲地区', '双湖县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5445, '2', '6', '542500', '54', '阿里地区', '西藏自治区', '阿里地区', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5446, '3', '6', '542521', '54', '普兰县', '西藏自治区', '阿里地区', '普兰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5447, '3', '6', '542522', '54', '札达县', '西藏自治区', '阿里地区', '札达县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5448, '3', '6', '542523', '54', '噶尔县', '西藏自治区', '阿里地区', '噶尔县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5449, '3', '6', '542524', '54', '日土县', '西藏自治区', '阿里地区', '日土县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5450, '3', '6', '542525', '54', '革吉县', '西藏自治区', '阿里地区', '革吉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5451, '3', '6', '542526', '54', '改则县', '西藏自治区', '阿里地区', '改则县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5452, '3', '6', '542527', '54', '措勤县', '西藏自治区', '阿里地区', '措勤县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5453, '1', '7', '610000', '61', '陕西省', '陕西省', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5454, '2', '7', '610100', '61', '西安市', '陕西省', '西安市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5455, '3', '7', '610101', '61', '市辖区', '陕西省', '西安市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5456, '3', '7', '610102', '61', '新城区', '陕西省', '西安市', '新城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5457, '3', '7', '610103', '61', '碑林区', '陕西省', '西安市', '碑林区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5458, '3', '7', '610104', '61', '莲湖区', '陕西省', '西安市', '莲湖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5459, '3', '7', '610111', '61', '灞桥区', '陕西省', '西安市', '灞桥区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5460, '3', '7', '610112', '61', '未央区', '陕西省', '西安市', '未央区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5461, '3', '7', '610113', '61', '雁塔区', '陕西省', '西安市', '雁塔区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5462, '3', '7', '610114', '61', '阎良区', '陕西省', '西安市', '阎良区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5463, '3', '7', '610115', '61', '临潼区', '陕西省', '西安市', '临潼区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5464, '3', '7', '610116', '61', '长安区', '陕西省', '西安市', '长安区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5465, '3', '7', '610117', '61', '高陵区', '陕西省', '西安市', '高陵区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5466, '3', '7', '610122', '61', '蓝田县', '陕西省', '西安市', '蓝田县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5467, '3', '7', '610124', '61', '周至县', '陕西省', '西安市', '周至县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5468, '3', '7', '610125', '61', '户县', '陕西省', '西安市', '户县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5469, '2', '7', '610200', '61', '铜川市', '陕西省', '铜川市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5470, '3', '7', '610201', '61', '市辖区', '陕西省', '铜川市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5471, '3', '7', '610202', '61', '王益区', '陕西省', '铜川市', '王益区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5472, '3', '7', '610203', '61', '印台区', '陕西省', '铜川市', '印台区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5473, '3', '7', '610204', '61', '耀州区', '陕西省', '铜川市', '耀州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5474, '3', '7', '610222', '61', '宜君县', '陕西省', '铜川市', '宜君县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5475, '2', '7', '610300', '61', '宝鸡市', '陕西省', '宝鸡市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5476, '3', '7', '610301', '61', '市辖区', '陕西省', '宝鸡市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5477, '3', '7', '610302', '61', '渭滨区', '陕西省', '宝鸡市', '渭滨区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5478, '3', '7', '610303', '61', '金台区', '陕西省', '宝鸡市', '金台区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5479, '3', '7', '610304', '61', '陈仓区', '陕西省', '宝鸡市', '陈仓区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5480, '3', '7', '610322', '61', '凤翔县', '陕西省', '宝鸡市', '凤翔县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5481, '3', '7', '610323', '61', '岐山县', '陕西省', '宝鸡市', '岐山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5482, '3', '7', '610324', '61', '扶风县', '陕西省', '宝鸡市', '扶风县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5483, '3', '7', '610326', '61', '眉县', '陕西省', '宝鸡市', '眉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5484, '3', '7', '610327', '61', '陇县', '陕西省', '宝鸡市', '陇县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5485, '3', '7', '610328', '61', '千阳县', '陕西省', '宝鸡市', '千阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5486, '3', '7', '610329', '61', '麟游县', '陕西省', '宝鸡市', '麟游县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5487, '3', '7', '610330', '61', '凤县', '陕西省', '宝鸡市', '凤县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5488, '3', '7', '610331', '61', '太白县', '陕西省', '宝鸡市', '太白县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5489, '2', '7', '610400', '61', '咸阳市', '陕西省', '咸阳市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5490, '3', '7', '610401', '61', '市辖区', '陕西省', '咸阳市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5491, '3', '7', '610402', '61', '秦都区', '陕西省', '咸阳市', '秦都区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5492, '3', '7', '610403', '61', '杨陵区', '陕西省', '咸阳市', '杨陵区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5493, '3', '7', '610404', '61', '渭城区', '陕西省', '咸阳市', '渭城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5494, '3', '7', '610422', '61', '三原县', '陕西省', '咸阳市', '三原县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5495, '3', '7', '610423', '61', '泾阳县', '陕西省', '咸阳市', '泾阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5496, '3', '7', '610424', '61', '乾县', '陕西省', '咸阳市', '乾县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5497, '3', '7', '610425', '61', '礼泉县', '陕西省', '咸阳市', '礼泉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5498, '3', '7', '610426', '61', '永寿县', '陕西省', '咸阳市', '永寿县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5499, '3', '7', '610427', '61', '彬县', '陕西省', '咸阳市', '彬县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5500, '3', '7', '610428', '61', '长武县', '陕西省', '咸阳市', '长武县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5501, '3', '7', '610429', '61', '旬邑县', '陕西省', '咸阳市', '旬邑县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5502, '3', '7', '610430', '61', '淳化县', '陕西省', '咸阳市', '淳化县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5503, '3', '7', '610431', '61', '武功县', '陕西省', '咸阳市', '武功县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5504, '3', '7', '610481', '61', '兴平市', '陕西省', '咸阳市', '兴平市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5505, '2', '7', '610500', '61', '渭南市', '陕西省', '渭南市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5506, '3', '7', '610501', '61', '市辖区', '陕西省', '渭南市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5507, '3', '7', '610502', '61', '临渭区', '陕西省', '渭南市', '临渭区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5508, '3', '7', '610521', '61', '华县', '陕西省', '渭南市', '华县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5509, '3', '7', '610522', '61', '潼关县', '陕西省', '渭南市', '潼关县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5510, '3', '7', '610523', '61', '大荔县', '陕西省', '渭南市', '大荔县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5511, '3', '7', '610524', '61', '合阳县', '陕西省', '渭南市', '合阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5512, '3', '7', '610525', '61', '澄城县', '陕西省', '渭南市', '澄城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5513, '3', '7', '610526', '61', '蒲城县', '陕西省', '渭南市', '蒲城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5514, '3', '7', '610527', '61', '白水县', '陕西省', '渭南市', '白水县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5515, '3', '7', '610528', '61', '富平县', '陕西省', '渭南市', '富平县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5516, '3', '7', '610581', '61', '韩城市', '陕西省', '渭南市', '韩城市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5517, '3', '7', '610582', '61', '华阴市', '陕西省', '渭南市', '华阴市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5518, '2', '7', '610600', '61', '延安市', '陕西省', '延安市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5519, '3', '7', '610601', '61', '市辖区', '陕西省', '延安市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5520, '3', '7', '610602', '61', '宝塔区', '陕西省', '延安市', '宝塔区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5521, '3', '7', '610621', '61', '延长县', '陕西省', '延安市', '延长县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5522, '3', '7', '610622', '61', '延川县', '陕西省', '延安市', '延川县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5523, '3', '7', '610623', '61', '子长县', '陕西省', '延安市', '子长县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5524, '3', '7', '610624', '61', '安塞县', '陕西省', '延安市', '安塞县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5525, '3', '7', '610625', '61', '志丹县', '陕西省', '延安市', '志丹县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5526, '3', '7', '610626', '61', '吴起县', '陕西省', '延安市', '吴起县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5527, '3', '7', '610627', '61', '甘泉县', '陕西省', '延安市', '甘泉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5528, '3', '7', '610628', '61', '富县', '陕西省', '延安市', '富县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5529, '3', '7', '610629', '61', '洛川县', '陕西省', '延安市', '洛川县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5530, '3', '7', '610630', '61', '宜川县', '陕西省', '延安市', '宜川县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5531, '3', '7', '610631', '61', '黄龙县', '陕西省', '延安市', '黄龙县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5532, '3', '7', '610632', '61', '黄陵县', '陕西省', '延安市', '黄陵县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5533, '2', '7', '610700', '61', '汉中市', '陕西省', '汉中市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5534, '3', '7', '610701', '61', '市辖区', '陕西省', '汉中市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5535, '3', '7', '610702', '61', '汉台区', '陕西省', '汉中市', '汉台区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5536, '3', '7', '610721', '61', '南郑县', '陕西省', '汉中市', '南郑县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5537, '3', '7', '610722', '61', '城固县', '陕西省', '汉中市', '城固县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5538, '3', '7', '610723', '61', '洋县', '陕西省', '汉中市', '洋县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5539, '3', '7', '610724', '61', '西乡县', '陕西省', '汉中市', '西乡县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5540, '3', '7', '610725', '61', '勉县', '陕西省', '汉中市', '勉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5541, '3', '7', '610726', '61', '宁强县', '陕西省', '汉中市', '宁强县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5542, '3', '7', '610727', '61', '略阳县', '陕西省', '汉中市', '略阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5543, '3', '7', '610728', '61', '镇巴县', '陕西省', '汉中市', '镇巴县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5544, '3', '7', '610729', '61', '留坝县', '陕西省', '汉中市', '留坝县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5545, '3', '7', '610730', '61', '佛坪县', '陕西省', '汉中市', '佛坪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5546, '2', '7', '610800', '61', '榆林市', '陕西省', '榆林市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5547, '3', '7', '610801', '61', '市辖区', '陕西省', '榆林市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5548, '3', '7', '610802', '61', '榆阳区', '陕西省', '榆林市', '榆阳区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5549, '3', '7', '610821', '61', '神木县', '陕西省', '榆林市', '神木县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5550, '3', '7', '610822', '61', '府谷县', '陕西省', '榆林市', '府谷县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5551, '3', '7', '610823', '61', '横山县', '陕西省', '榆林市', '横山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5552, '3', '7', '610824', '61', '靖边县', '陕西省', '榆林市', '靖边县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5553, '3', '7', '610825', '61', '定边县', '陕西省', '榆林市', '定边县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5554, '3', '7', '610826', '61', '绥德县', '陕西省', '榆林市', '绥德县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5555, '3', '7', '610827', '61', '米脂县', '陕西省', '榆林市', '米脂县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5556, '3', '7', '610828', '61', '佳县', '陕西省', '榆林市', '佳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5557, '3', '7', '610829', '61', '吴堡县', '陕西省', '榆林市', '吴堡县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5558, '3', '7', '610830', '61', '清涧县', '陕西省', '榆林市', '清涧县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5559, '3', '7', '610831', '61', '子洲县', '陕西省', '榆林市', '子洲县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5560, '2', '7', '610900', '61', '安康市', '陕西省', '安康市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5561, '3', '7', '610901', '61', '市辖区', '陕西省', '安康市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5562, '3', '7', '610902', '61', '汉滨区', '陕西省', '安康市', '汉滨区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5563, '3', '7', '610921', '61', '汉阴县', '陕西省', '安康市', '汉阴县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5564, '3', '7', '610922', '61', '石泉县', '陕西省', '安康市', '石泉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5565, '3', '7', '610923', '61', '宁陕县', '陕西省', '安康市', '宁陕县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5566, '3', '7', '610924', '61', '紫阳县', '陕西省', '安康市', '紫阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5567, '3', '7', '610925', '61', '岚皋县', '陕西省', '安康市', '岚皋县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5568, '3', '7', '610926', '61', '平利县', '陕西省', '安康市', '平利县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5569, '3', '7', '610927', '61', '镇坪县', '陕西省', '安康市', '镇坪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5570, '3', '7', '610928', '61', '旬阳县', '陕西省', '安康市', '旬阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5571, '3', '7', '610929', '61', '白河县', '陕西省', '安康市', '白河县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5572, '2', '7', '611000', '61', '商洛市', '陕西省', '商洛市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5573, '3', '7', '611001', '61', '市辖区', '陕西省', '商洛市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5574, '3', '7', '611002', '61', '商州区', '陕西省', '商洛市', '商州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5575, '3', '7', '611021', '61', '洛南县', '陕西省', '商洛市', '洛南县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5576, '3', '7', '611022', '61', '丹凤县', '陕西省', '商洛市', '丹凤县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5577, '3', '7', '611023', '61', '商南县', '陕西省', '商洛市', '商南县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5578, '3', '7', '611024', '61', '山阳县', '陕西省', '商洛市', '山阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5579, '3', '7', '611025', '61', '镇安县', '陕西省', '商洛市', '镇安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5580, '3', '7', '611026', '61', '柞水县', '陕西省', '商洛市', '柞水县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5581, '1', '7', '620000', '62', '甘肃省', '甘肃省', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5582, '2', '7', '620100', '62', '兰州市', '甘肃省', '兰州市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5583, '3', '7', '620101', '62', '市辖区', '甘肃省', '兰州市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5584, '3', '7', '620102', '62', '城关区', '甘肃省', '兰州市', '城关区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5585, '3', '7', '620103', '62', '七里河区', '甘肃省', '兰州市', '七里河区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5586, '3', '7', '620104', '62', '西固区', '甘肃省', '兰州市', '西固区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5587, '3', '7', '620105', '62', '安宁区', '甘肃省', '兰州市', '安宁区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5588, '3', '7', '620111', '62', '红古区', '甘肃省', '兰州市', '红古区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5589, '3', '7', '620121', '62', '永登县', '甘肃省', '兰州市', '永登县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5590, '3', '7', '620122', '62', '皋兰县', '甘肃省', '兰州市', '皋兰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5591, '3', '7', '620123', '62', '榆中县', '甘肃省', '兰州市', '榆中县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5592, '2', '7', '620200', '62', '嘉峪关市', '甘肃省', '嘉峪关市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5593, '3', '7', '620201', '62', '市辖区', '甘肃省', '嘉峪关市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5594, '2', '7', '620300', '62', '金昌市', '甘肃省', '金昌市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5595, '3', '7', '620301', '62', '市辖区', '甘肃省', '金昌市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5596, '3', '7', '620302', '62', '金川区', '甘肃省', '金昌市', '金川区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5597, '3', '7', '620321', '62', '永昌县', '甘肃省', '金昌市', '永昌县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5598, '2', '7', '620400', '62', '白银市', '甘肃省', '白银市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5599, '3', '7', '620401', '62', '市辖区', '甘肃省', '白银市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5600, '3', '7', '620402', '62', '白银区', '甘肃省', '白银市', '白银区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5601, '3', '7', '620403', '62', '平川区', '甘肃省', '白银市', '平川区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5602, '3', '7', '620421', '62', '靖远县', '甘肃省', '白银市', '靖远县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5603, '3', '7', '620422', '62', '会宁县', '甘肃省', '白银市', '会宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5604, '3', '7', '620423', '62', '景泰县', '甘肃省', '白银市', '景泰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5605, '2', '7', '620500', '62', '天水市', '甘肃省', '天水市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5606, '3', '7', '620501', '62', '市辖区', '甘肃省', '天水市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5607, '3', '7', '620502', '62', '秦州区', '甘肃省', '天水市', '秦州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5608, '3', '7', '620503', '62', '麦积区', '甘肃省', '天水市', '麦积区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5609, '3', '7', '620521', '62', '清水县', '甘肃省', '天水市', '清水县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5610, '3', '7', '620522', '62', '秦安县', '甘肃省', '天水市', '秦安县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5611, '3', '7', '620523', '62', '甘谷县', '甘肃省', '天水市', '甘谷县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5612, '3', '7', '620524', '62', '武山县', '甘肃省', '天水市', '武山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5613, '3', '7', '620525', '62', '张家川回族自治县', '甘肃省', '天水市', '张家川回族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5614, '2', '7', '620600', '62', '武威市', '甘肃省', '武威市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5615, '3', '7', '620601', '62', '市辖区', '甘肃省', '武威市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5616, '3', '7', '620602', '62', '凉州区', '甘肃省', '武威市', '凉州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5617, '3', '7', '620621', '62', '民勤县', '甘肃省', '武威市', '民勤县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5618, '3', '7', '620622', '62', '古浪县', '甘肃省', '武威市', '古浪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5619, '3', '7', '620623', '62', '天祝藏族自治县', '甘肃省', '武威市', '天祝藏族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5620, '2', '7', '620700', '62', '张掖市', '甘肃省', '张掖市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5621, '3', '7', '620701', '62', '市辖区', '甘肃省', '张掖市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5622, '3', '7', '620702', '62', '甘州区', '甘肃省', '张掖市', '甘州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5623, '3', '7', '620721', '62', '肃南裕固族自治县', '甘肃省', '张掖市', '肃南裕固族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5624, '3', '7', '620722', '62', '民乐县', '甘肃省', '张掖市', '民乐县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5625, '3', '7', '620723', '62', '临泽县', '甘肃省', '张掖市', '临泽县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5626, '3', '7', '620724', '62', '高台县', '甘肃省', '张掖市', '高台县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5627, '3', '7', '620725', '62', '山丹县', '甘肃省', '张掖市', '山丹县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5628, '2', '7', '620800', '62', '平凉市', '甘肃省', '平凉市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5629, '3', '7', '620801', '62', '市辖区', '甘肃省', '平凉市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5630, '3', '7', '620802', '62', '崆峒区', '甘肃省', '平凉市', '崆峒区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5631, '3', '7', '620821', '62', '泾川县', '甘肃省', '平凉市', '泾川县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5632, '3', '7', '620822', '62', '灵台县', '甘肃省', '平凉市', '灵台县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5633, '3', '7', '620823', '62', '崇信县', '甘肃省', '平凉市', '崇信县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5634, '3', '7', '620824', '62', '华亭县', '甘肃省', '平凉市', '华亭县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5635, '3', '7', '620825', '62', '庄浪县', '甘肃省', '平凉市', '庄浪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5636, '3', '7', '620826', '62', '静宁县', '甘肃省', '平凉市', '静宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5637, '2', '7', '620900', '62', '酒泉市', '甘肃省', '酒泉市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5638, '3', '7', '620901', '62', '市辖区', '甘肃省', '酒泉市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5639, '3', '7', '620902', '62', '肃州区', '甘肃省', '酒泉市', '肃州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5640, '3', '7', '620921', '62', '金塔县', '甘肃省', '酒泉市', '金塔县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5641, '3', '7', '620922', '62', '瓜州县', '甘肃省', '酒泉市', '瓜州县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5642, '3', '7', '620923', '62', '肃北蒙古族自治县', '甘肃省', '酒泉市', '肃北蒙古族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5643, '3', '7', '620924', '62', '阿克塞哈萨克族自治县', '甘肃省', '酒泉市', '阿克塞哈萨克族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5644, '3', '7', '620981', '62', '玉门市', '甘肃省', '酒泉市', '玉门市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5645, '3', '7', '620982', '62', '敦煌市', '甘肃省', '酒泉市', '敦煌市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5646, '2', '7', '621000', '62', '庆阳市', '甘肃省', '庆阳市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5647, '3', '7', '621001', '62', '市辖区', '甘肃省', '庆阳市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5648, '3', '7', '621002', '62', '西峰区', '甘肃省', '庆阳市', '西峰区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5649, '3', '7', '621021', '62', '庆城县', '甘肃省', '庆阳市', '庆城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5650, '3', '7', '621022', '62', '环县', '甘肃省', '庆阳市', '环县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5651, '3', '7', '621023', '62', '华池县', '甘肃省', '庆阳市', '华池县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5652, '3', '7', '621024', '62', '合水县', '甘肃省', '庆阳市', '合水县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5653, '3', '7', '621025', '62', '正宁县', '甘肃省', '庆阳市', '正宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5654, '3', '7', '621026', '62', '宁县', '甘肃省', '庆阳市', '宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5655, '3', '7', '621027', '62', '镇原县', '甘肃省', '庆阳市', '镇原县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5656, '2', '7', '621100', '62', '定西市', '甘肃省', '定西市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5657, '3', '7', '621101', '62', '市辖区', '甘肃省', '定西市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5658, '3', '7', '621102', '62', '安定区', '甘肃省', '定西市', '安定区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5659, '3', '7', '621121', '62', '通渭县', '甘肃省', '定西市', '通渭县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5660, '3', '7', '621122', '62', '陇西县', '甘肃省', '定西市', '陇西县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5661, '3', '7', '621123', '62', '渭源县', '甘肃省', '定西市', '渭源县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5662, '3', '7', '621124', '62', '临洮县', '甘肃省', '定西市', '临洮县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5663, '3', '7', '621125', '62', '漳县', '甘肃省', '定西市', '漳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5664, '3', '7', '621126', '62', '岷县', '甘肃省', '定西市', '岷县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5665, '2', '7', '621200', '62', '陇南市', '甘肃省', '陇南市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5666, '3', '7', '621201', '62', '市辖区', '甘肃省', '陇南市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5667, '3', '7', '621202', '62', '武都区', '甘肃省', '陇南市', '武都区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5668, '3', '7', '621221', '62', '成县', '甘肃省', '陇南市', '成县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5669, '3', '7', '621222', '62', '文县', '甘肃省', '陇南市', '文县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5670, '3', '7', '621223', '62', '宕昌县', '甘肃省', '陇南市', '宕昌县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5671, '3', '7', '621224', '62', '康县', '甘肃省', '陇南市', '康县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5672, '3', '7', '621225', '62', '西和县', '甘肃省', '陇南市', '西和县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5673, '3', '7', '621226', '62', '礼县', '甘肃省', '陇南市', '礼县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5674, '3', '7', '621227', '62', '徽县', '甘肃省', '陇南市', '徽县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5675, '3', '7', '621228', '62', '两当县', '甘肃省', '陇南市', '两当县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5676, '2', '7', '622900', '62', '临夏回族自治州', '甘肃省', '临夏回族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5677, '3', '7', '622901', '62', '临夏市', '甘肃省', '临夏回族自治州', '临夏市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5678, '3', '7', '622921', '62', '临夏县', '甘肃省', '临夏回族自治州', '临夏县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5679, '3', '7', '622922', '62', '康乐县', '甘肃省', '临夏回族自治州', '康乐县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5680, '3', '7', '622923', '62', '永靖县', '甘肃省', '临夏回族自治州', '永靖县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5681, '3', '7', '622924', '62', '广河县', '甘肃省', '临夏回族自治州', '广河县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5682, '3', '7', '622925', '62', '和政县', '甘肃省', '临夏回族自治州', '和政县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5683, '3', '7', '622926', '62', '东乡族自治县', '甘肃省', '临夏回族自治州', '东乡族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5684, '3', '7', '622927', '62', '积石山保安族东乡族撒拉族自治县', '甘肃省', '临夏回族自治州', '积石山保安族东乡族撒拉族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5685, '2', '7', '623000', '62', '甘南藏族自治州', '甘肃省', '甘南藏族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5686, '3', '7', '623001', '62', '合作市', '甘肃省', '甘南藏族自治州', '合作市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5687, '3', '7', '623021', '62', '临潭县', '甘肃省', '甘南藏族自治州', '临潭县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5688, '3', '7', '623022', '62', '卓尼县', '甘肃省', '甘南藏族自治州', '卓尼县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5689, '3', '7', '623023', '62', '舟曲县', '甘肃省', '甘南藏族自治州', '舟曲县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5690, '3', '7', '623024', '62', '迭部县', '甘肃省', '甘南藏族自治州', '迭部县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5691, '3', '7', '623025', '62', '玛曲县', '甘肃省', '甘南藏族自治州', '玛曲县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5692, '3', '7', '623026', '62', '碌曲县', '甘肃省', '甘南藏族自治州', '碌曲县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5693, '3', '7', '623027', '62', '夏河县', '甘肃省', '甘南藏族自治州', '夏河县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5694, '1', '7', '630000', '63', '青海省', '青海省', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5695, '2', '7', '630100', '63', '西宁市', '青海省', '西宁市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5696, '3', '7', '630101', '63', '市辖区', '青海省', '西宁市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5697, '3', '7', '630102', '63', '城东区', '青海省', '西宁市', '城东区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5698, '3', '7', '630103', '63', '城中区', '青海省', '西宁市', '城中区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5699, '3', '7', '630104', '63', '城西区', '青海省', '西宁市', '城西区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5700, '3', '7', '630105', '63', '城北区', '青海省', '西宁市', '城北区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5701, '3', '7', '630121', '63', '大通回族土族自治县', '青海省', '西宁市', '大通回族土族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5702, '3', '7', '630122', '63', '湟中县', '青海省', '西宁市', '湟中县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5703, '3', '7', '630123', '63', '湟源县', '青海省', '西宁市', '湟源县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5704, '2', '7', '630200', '63', '海东市', '青海省', '海东市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5705, '3', '7', '630202', '63', '乐都区', '青海省', '海东市', '乐都区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5706, '3', '7', '630203', '63', '平安区', '青海省', '海东市', '平安区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5707, '3', '7', '630222', '63', '民和回族土族自治县', '青海省', '海东市', '民和回族土族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5708, '3', '7', '630223', '63', '互助土族自治县', '青海省', '海东市', '互助土族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5709, '3', '7', '630224', '63', '化隆回族自治县', '青海省', '海东市', '化隆回族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5710, '3', '7', '630225', '63', '循化撒拉族自治县', '青海省', '海东市', '循化撒拉族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5711, '2', '7', '632200', '63', '海北藏族自治州', '青海省', '海北藏族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5712, '3', '7', '632221', '63', '门源回族自治县', '青海省', '海北藏族自治州', '门源回族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5713, '3', '7', '632222', '63', '祁连县', '青海省', '海北藏族自治州', '祁连县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5714, '3', '7', '632223', '63', '海晏县', '青海省', '海北藏族自治州', '海晏县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5715, '3', '7', '632224', '63', '刚察县', '青海省', '海北藏族自治州', '刚察县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5716, '2', '7', '632300', '63', '黄南藏族自治州', '青海省', '黄南藏族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5717, '3', '7', '632321', '63', '同仁县', '青海省', '黄南藏族自治州', '同仁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5718, '3', '7', '632322', '63', '尖扎县', '青海省', '黄南藏族自治州', '尖扎县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5719, '3', '7', '632323', '63', '泽库县', '青海省', '黄南藏族自治州', '泽库县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5720, '3', '7', '632324', '63', '河南蒙古族自治县', '青海省', '黄南藏族自治州', '河南蒙古族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5721, '2', '7', '632500', '63', '海南藏族自治州', '青海省', '海南藏族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5722, '3', '7', '632521', '63', '共和县', '青海省', '海南藏族自治州', '共和县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5723, '3', '7', '632522', '63', '同德县', '青海省', '海南藏族自治州', '同德县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5724, '3', '7', '632523', '63', '贵德县', '青海省', '海南藏族自治州', '贵德县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5725, '3', '7', '632524', '63', '兴海县', '青海省', '海南藏族自治州', '兴海县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5726, '3', '7', '632525', '63', '贵南县', '青海省', '海南藏族自治州', '贵南县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5727, '2', '7', '632600', '63', '果洛藏族自治州', '青海省', '果洛藏族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5728, '3', '7', '632621', '63', '玛沁县', '青海省', '果洛藏族自治州', '玛沁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5729, '3', '7', '632622', '63', '班玛县', '青海省', '果洛藏族自治州', '班玛县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5730, '3', '7', '632623', '63', '甘德县', '青海省', '果洛藏族自治州', '甘德县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5731, '3', '7', '632624', '63', '达日县', '青海省', '果洛藏族自治州', '达日县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5732, '3', '7', '632625', '63', '久治县', '青海省', '果洛藏族自治州', '久治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5733, '3', '7', '632626', '63', '玛多县', '青海省', '果洛藏族自治州', '玛多县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5734, '2', '7', '632700', '63', '玉树藏族自治州', '青海省', '玉树藏族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5735, '3', '7', '632701', '63', '玉树市', '青海省', '玉树藏族自治州', '玉树市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5736, '3', '7', '632722', '63', '杂多县', '青海省', '玉树藏族自治州', '杂多县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5737, '3', '7', '632723', '63', '称多县', '青海省', '玉树藏族自治州', '称多县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5738, '3', '7', '632724', '63', '治多县', '青海省', '玉树藏族自治州', '治多县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5739, '3', '7', '632725', '63', '囊谦县', '青海省', '玉树藏族自治州', '囊谦县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5740, '3', '7', '632726', '63', '曲麻莱县', '青海省', '玉树藏族自治州', '曲麻莱县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5741, '2', '7', '632800', '63', '海西蒙古族藏族自治州', '青海省', '海西蒙古族藏族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5742, '3', '7', '632801', '63', '格尔木市', '青海省', '海西蒙古族藏族自治州', '格尔木市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5743, '3', '7', '632802', '63', '德令哈市', '青海省', '海西蒙古族藏族自治州', '德令哈市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5744, '3', '7', '632821', '63', '乌兰县', '青海省', '海西蒙古族藏族自治州', '乌兰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5745, '3', '7', '632822', '63', '都兰县', '青海省', '海西蒙古族藏族自治州', '都兰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5746, '3', '7', '632823', '63', '天峻县', '青海省', '海西蒙古族藏族自治州', '天峻县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5747, '1', '7', '640000', '64', '宁夏回族自治区', '宁夏回族自治区', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5748, '2', '7', '640100', '64', '银川市', '宁夏回族自治区', '银川市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5749, '3', '7', '640101', '64', '市辖区', '宁夏回族自治区', '银川市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5750, '3', '7', '640104', '64', '兴庆区', '宁夏回族自治区', '银川市', '兴庆区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5751, '3', '7', '640105', '64', '西夏区', '宁夏回族自治区', '银川市', '西夏区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5752, '3', '7', '640106', '64', '金凤区', '宁夏回族自治区', '银川市', '金凤区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5753, '3', '7', '640121', '64', '永宁县', '宁夏回族自治区', '银川市', '永宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5754, '3', '7', '640122', '64', '贺兰县', '宁夏回族自治区', '银川市', '贺兰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5755, '3', '7', '640181', '64', '灵武市', '宁夏回族自治区', '银川市', '灵武市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5756, '2', '7', '640200', '64', '石嘴山市', '宁夏回族自治区', '石嘴山市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5757, '3', '7', '640201', '64', '市辖区', '宁夏回族自治区', '石嘴山市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5758, '3', '7', '640202', '64', '大武口区', '宁夏回族自治区', '石嘴山市', '大武口区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5759, '3', '7', '640205', '64', '惠农区', '宁夏回族自治区', '石嘴山市', '惠农区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5760, '3', '7', '640221', '64', '平罗县', '宁夏回族自治区', '石嘴山市', '平罗县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5761, '2', '7', '640300', '64', '吴忠市', '宁夏回族自治区', '吴忠市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5762, '3', '7', '640301', '64', '市辖区', '宁夏回族自治区', '吴忠市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5763, '3', '7', '640302', '64', '利通区', '宁夏回族自治区', '吴忠市', '利通区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5764, '3', '7', '640303', '64', '红寺堡区', '宁夏回族自治区', '吴忠市', '红寺堡区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5765, '3', '7', '640323', '64', '盐池县', '宁夏回族自治区', '吴忠市', '盐池县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5766, '3', '7', '640324', '64', '同心县', '宁夏回族自治区', '吴忠市', '同心县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5767, '3', '7', '640381', '64', '青铜峡市', '宁夏回族自治区', '吴忠市', '青铜峡市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5768, '2', '7', '640400', '64', '固原市', '宁夏回族自治区', '固原市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5769, '3', '7', '640401', '64', '市辖区', '宁夏回族自治区', '固原市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5770, '3', '7', '640402', '64', '原州区', '宁夏回族自治区', '固原市', '原州区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5771, '3', '7', '640422', '64', '西吉县', '宁夏回族自治区', '固原市', '西吉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5772, '3', '7', '640423', '64', '隆德县', '宁夏回族自治区', '固原市', '隆德县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5773, '3', '7', '640424', '64', '泾源县', '宁夏回族自治区', '固原市', '泾源县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5774, '3', '7', '640425', '64', '彭阳县', '宁夏回族自治区', '固原市', '彭阳县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5775, '2', '7', '640500', '64', '中卫市', '宁夏回族自治区', '中卫市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5776, '3', '7', '640501', '64', '市辖区', '宁夏回族自治区', '中卫市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5777, '3', '7', '640502', '64', '沙坡头区', '宁夏回族自治区', '中卫市', '沙坡头区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5778, '3', '7', '640521', '64', '中宁县', '宁夏回族自治区', '中卫市', '中宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5779, '3', '7', '640522', '64', '海原县', '宁夏回族自治区', '中卫市', '海原县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5780, '1', '7', '650000', '65', '新疆维吾尔自治区', '新疆维吾尔自治区', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5781, '2', '7', '650100', '65', '乌鲁木齐市', '新疆维吾尔自治区', '乌鲁木齐市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5782, '3', '7', '650101', '65', '市辖区', '新疆维吾尔自治区', '乌鲁木齐市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5783, '3', '7', '650102', '65', '天山区', '新疆维吾尔自治区', '乌鲁木齐市', '天山区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5784, '3', '7', '650103', '65', '沙依巴克区', '新疆维吾尔自治区', '乌鲁木齐市', '沙依巴克区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5785, '3', '7', '650104', '65', '新市区', '新疆维吾尔自治区', '乌鲁木齐市', '新市区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5786, '3', '7', '650105', '65', '水磨沟区', '新疆维吾尔自治区', '乌鲁木齐市', '水磨沟区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5787, '3', '7', '650106', '65', '头屯河区', '新疆维吾尔自治区', '乌鲁木齐市', '头屯河区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5788, '3', '7', '650107', '65', '达坂城区', '新疆维吾尔自治区', '乌鲁木齐市', '达坂城区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5789, '3', '7', '650109', '65', '米东区', '新疆维吾尔自治区', '乌鲁木齐市', '米东区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5790, '3', '7', '650121', '65', '乌鲁木齐县', '新疆维吾尔自治区', '乌鲁木齐市', '乌鲁木齐县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5791, '2', '7', '650200', '65', '克拉玛依市', '新疆维吾尔自治区', '克拉玛依市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5792, '3', '7', '650201', '65', '市辖区', '新疆维吾尔自治区', '克拉玛依市', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5793, '3', '7', '650202', '65', '独山子区', '新疆维吾尔自治区', '克拉玛依市', '独山子区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5794, '3', '7', '650203', '65', '克拉玛依区', '新疆维吾尔自治区', '克拉玛依市', '克拉玛依区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5795, '3', '7', '650204', '65', '白碱滩区', '新疆维吾尔自治区', '克拉玛依市', '白碱滩区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5796, '3', '7', '650205', '65', '乌尔禾区', '新疆维吾尔自治区', '克拉玛依市', '乌尔禾区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5797, '2', '7', '650400', '65', '吐鲁番市', '新疆维吾尔自治区', '吐鲁番市', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5798, '3', '7', '650402', '65', '高昌区', '新疆维吾尔自治区', '吐鲁番市', '高昌区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5799, '3', '7', '650421', '65', '鄯善县', '新疆维吾尔自治区', '吐鲁番市', '鄯善县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5800, '3', '7', '650422', '65', '托克逊县', '新疆维吾尔自治区', '吐鲁番市', '托克逊县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5801, '2', '7', '652200', '65', '哈密地区', '新疆维吾尔自治区', '哈密地区', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5802, '3', '7', '652201', '65', '哈密市', '新疆维吾尔自治区', '哈密地区', '哈密市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5803, '3', '7', '652222', '65', '巴里坤哈萨克自治县', '新疆维吾尔自治区', '哈密地区', '巴里坤哈萨克自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5804, '3', '7', '652223', '65', '伊吾县', '新疆维吾尔自治区', '哈密地区', '伊吾县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5805, '2', '7', '652300', '65', '昌吉回族自治州', '新疆维吾尔自治区', '昌吉回族自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5806, '3', '7', '652301', '65', '昌吉市', '新疆维吾尔自治区', '昌吉回族自治州', '昌吉市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5807, '3', '7', '652302', '65', '阜康市', '新疆维吾尔自治区', '昌吉回族自治州', '阜康市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5808, '3', '7', '652323', '65', '呼图壁县', '新疆维吾尔自治区', '昌吉回族自治州', '呼图壁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5809, '3', '7', '652324', '65', '玛纳斯县', '新疆维吾尔自治区', '昌吉回族自治州', '玛纳斯县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5810, '3', '7', '652325', '65', '奇台县', '新疆维吾尔自治区', '昌吉回族自治州', '奇台县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5811, '3', '7', '652327', '65', '吉木萨尔县', '新疆维吾尔自治区', '昌吉回族自治州', '吉木萨尔县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5812, '3', '7', '652328', '65', '木垒哈萨克自治县', '新疆维吾尔自治区', '昌吉回族自治州', '木垒哈萨克自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5813, '2', '7', '652700', '65', '博尔塔拉蒙古自治州', '新疆维吾尔自治区', '博尔塔拉蒙古自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5814, '3', '7', '652701', '65', '博乐市', '新疆维吾尔自治区', '博尔塔拉蒙古自治州', '博乐市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5815, '3', '7', '652702', '65', '阿拉山口市', '新疆维吾尔自治区', '博尔塔拉蒙古自治州', '阿拉山口市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5816, '3', '7', '652722', '65', '精河县', '新疆维吾尔自治区', '博尔塔拉蒙古自治州', '精河县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5817, '3', '7', '652723', '65', '温泉县', '新疆维吾尔自治区', '博尔塔拉蒙古自治州', '温泉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5818, '2', '7', '652800', '65', '巴音郭楞蒙古自治州', '新疆维吾尔自治区', '巴音郭楞蒙古自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5819, '3', '7', '652801', '65', '库尔勒市', '新疆维吾尔自治区', '巴音郭楞蒙古自治州', '库尔勒市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5820, '3', '7', '652822', '65', '轮台县', '新疆维吾尔自治区', '巴音郭楞蒙古自治州', '轮台县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5821, '3', '7', '652823', '65', '尉犁县', '新疆维吾尔自治区', '巴音郭楞蒙古自治州', '尉犁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5822, '3', '7', '652824', '65', '若羌县', '新疆维吾尔自治区', '巴音郭楞蒙古自治州', '若羌县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5823, '3', '7', '652825', '65', '且末县', '新疆维吾尔自治区', '巴音郭楞蒙古自治州', '且末县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5824, '3', '7', '652826', '65', '焉耆回族自治县', '新疆维吾尔自治区', '巴音郭楞蒙古自治州', '焉耆回族自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5825, '3', '7', '652827', '65', '和静县', '新疆维吾尔自治区', '巴音郭楞蒙古自治州', '和静县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5826, '3', '7', '652828', '65', '和硕县', '新疆维吾尔自治区', '巴音郭楞蒙古自治州', '和硕县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5827, '3', '7', '652829', '65', '博湖县', '新疆维吾尔自治区', '巴音郭楞蒙古自治州', '博湖县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5828, '2', '7', '652900', '65', '阿克苏地区', '新疆维吾尔自治区', '阿克苏地区', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5829, '3', '7', '652901', '65', '阿克苏市', '新疆维吾尔自治区', '阿克苏地区', '阿克苏市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5830, '3', '7', '652922', '65', '温宿县', '新疆维吾尔自治区', '阿克苏地区', '温宿县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5831, '3', '7', '652923', '65', '库车县', '新疆维吾尔自治区', '阿克苏地区', '库车县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5832, '3', '7', '652924', '65', '沙雅县', '新疆维吾尔自治区', '阿克苏地区', '沙雅县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5833, '3', '7', '652925', '65', '新和县', '新疆维吾尔自治区', '阿克苏地区', '新和县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5834, '3', '7', '652926', '65', '拜城县', '新疆维吾尔自治区', '阿克苏地区', '拜城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5835, '3', '7', '652927', '65', '乌什县', '新疆维吾尔自治区', '阿克苏地区', '乌什县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5836, '3', '7', '652928', '65', '阿瓦提县', '新疆维吾尔自治区', '阿克苏地区', '阿瓦提县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5837, '3', '7', '652929', '65', '柯坪县', '新疆维吾尔自治区', '阿克苏地区', '柯坪县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5838, '2', '7', '653000', '65', '克孜勒苏柯尔克孜自治州', '新疆维吾尔自治区', '克孜勒苏柯尔克孜自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5839, '3', '7', '653001', '65', '阿图什市', '新疆维吾尔自治区', '克孜勒苏柯尔克孜自治州', '阿图什市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5840, '3', '7', '653022', '65', '阿克陶县', '新疆维吾尔自治区', '克孜勒苏柯尔克孜自治州', '阿克陶县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5841, '3', '7', '653023', '65', '阿合奇县', '新疆维吾尔自治区', '克孜勒苏柯尔克孜自治州', '阿合奇县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5842, '3', '7', '653024', '65', '乌恰县', '新疆维吾尔自治区', '克孜勒苏柯尔克孜自治州', '乌恰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5843, '2', '7', '653100', '65', '喀什地区', '新疆维吾尔自治区', '喀什地区', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5844, '3', '7', '653101', '65', '喀什市', '新疆维吾尔自治区', '喀什地区', '喀什市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5845, '3', '7', '653121', '65', '疏附县', '新疆维吾尔自治区', '喀什地区', '疏附县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5846, '3', '7', '653122', '65', '疏勒县', '新疆维吾尔自治区', '喀什地区', '疏勒县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5847, '3', '7', '653123', '65', '英吉沙县', '新疆维吾尔自治区', '喀什地区', '英吉沙县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5848, '3', '7', '653124', '65', '泽普县', '新疆维吾尔自治区', '喀什地区', '泽普县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5849, '3', '7', '653125', '65', '莎车县', '新疆维吾尔自治区', '喀什地区', '莎车县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5850, '3', '7', '653126', '65', '叶城县', '新疆维吾尔自治区', '喀什地区', '叶城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5851, '3', '7', '653127', '65', '麦盖提县', '新疆维吾尔自治区', '喀什地区', '麦盖提县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5852, '3', '7', '653128', '65', '岳普湖县', '新疆维吾尔自治区', '喀什地区', '岳普湖县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5853, '3', '7', '653129', '65', '伽师县', '新疆维吾尔自治区', '喀什地区', '伽师县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5854, '3', '7', '653130', '65', '巴楚县', '新疆维吾尔自治区', '喀什地区', '巴楚县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5855, '3', '7', '653131', '65', '塔什库尔干塔吉克自治县', '新疆维吾尔自治区', '喀什地区', '塔什库尔干塔吉克自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5856, '2', '7', '653200', '65', '和田地区', '新疆维吾尔自治区', '和田地区', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5857, '3', '7', '653201', '65', '和田市', '新疆维吾尔自治区', '和田地区', '和田市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5858, '3', '7', '653221', '65', '和田县', '新疆维吾尔自治区', '和田地区', '和田县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5859, '3', '7', '653222', '65', '墨玉县', '新疆维吾尔自治区', '和田地区', '墨玉县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5860, '3', '7', '653223', '65', '皮山县', '新疆维吾尔自治区', '和田地区', '皮山县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5861, '3', '7', '653224', '65', '洛浦县', '新疆维吾尔自治区', '和田地区', '洛浦县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5862, '3', '7', '653225', '65', '策勒县', '新疆维吾尔自治区', '和田地区', '策勒县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5863, '3', '7', '653226', '65', '于田县', '新疆维吾尔自治区', '和田地区', '于田县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5864, '3', '7', '653227', '65', '民丰县', '新疆维吾尔自治区', '和田地区', '民丰县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5865, '2', '7', '654000', '65', '伊犁哈萨克自治州', '新疆维吾尔自治区', '伊犁哈萨克自治州', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5866, '3', '7', '654002', '65', '伊宁市', '新疆维吾尔自治区', '伊犁哈萨克自治州', '伊宁市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5867, '3', '7', '654003', '65', '奎屯市', '新疆维吾尔自治区', '伊犁哈萨克自治州', '奎屯市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5868, '3', '7', '654004', '65', '霍尔果斯市', '新疆维吾尔自治区', '伊犁哈萨克自治州', '霍尔果斯市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5869, '3', '7', '654021', '65', '伊宁县', '新疆维吾尔自治区', '伊犁哈萨克自治州', '伊宁县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5870, '3', '7', '654022', '65', '察布查尔锡伯自治县', '新疆维吾尔自治区', '伊犁哈萨克自治州', '察布查尔锡伯自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5871, '3', '7', '654023', '65', '霍城县', '新疆维吾尔自治区', '伊犁哈萨克自治州', '霍城县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5872, '3', '7', '654024', '65', '巩留县', '新疆维吾尔自治区', '伊犁哈萨克自治州', '巩留县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5873, '3', '7', '654025', '65', '新源县', '新疆维吾尔自治区', '伊犁哈萨克自治州', '新源县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5874, '3', '7', '654026', '65', '昭苏县', '新疆维吾尔自治区', '伊犁哈萨克自治州', '昭苏县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5875, '3', '7', '654027', '65', '特克斯县', '新疆维吾尔自治区', '伊犁哈萨克自治州', '特克斯县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5876, '3', '7', '654028', '65', '尼勒克县', '新疆维吾尔自治区', '伊犁哈萨克自治州', '尼勒克县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5877, '2', '7', '654200', '65', '塔城地区', '新疆维吾尔自治区', '塔城地区', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5878, '3', '7', '654201', '65', '塔城市', '新疆维吾尔自治区', '塔城地区', '塔城市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5879, '3', '7', '654202', '65', '乌苏市', '新疆维吾尔自治区', '塔城地区', '乌苏市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5880, '3', '7', '654221', '65', '额敏县', '新疆维吾尔自治区', '塔城地区', '额敏县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5881, '3', '7', '654223', '65', '沙湾县', '新疆维吾尔自治区', '塔城地区', '沙湾县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5882, '3', '7', '654224', '65', '托里县', '新疆维吾尔自治区', '塔城地区', '托里县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5883, '3', '7', '654225', '65', '裕民县', '新疆维吾尔自治区', '塔城地区', '裕民县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5884, '3', '7', '654226', '65', '和布克赛尔蒙古自治县', '新疆维吾尔自治区', '塔城地区', '和布克赛尔蒙古自治县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5885, '2', '7', '654300', '65', '阿勒泰地区', '新疆维吾尔自治区', '阿勒泰地区', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5886, '3', '7', '654301', '65', '阿勒泰市', '新疆维吾尔自治区', '阿勒泰地区', '阿勒泰市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5887, '3', '7', '654321', '65', '布尔津县', '新疆维吾尔自治区', '阿勒泰地区', '布尔津县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5888, '3', '7', '654322', '65', '富蕴县', '新疆维吾尔自治区', '阿勒泰地区', '富蕴县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5889, '3', '7', '654323', '65', '福海县', '新疆维吾尔自治区', '阿勒泰地区', '福海县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5890, '3', '7', '654324', '65', '哈巴河县', '新疆维吾尔自治区', '阿勒泰地区', '哈巴河县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5891, '3', '7', '654325', '65', '青河县', '新疆维吾尔自治区', '阿勒泰地区', '青河县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5892, '3', '7', '654326', '65', '吉木乃县', '新疆维吾尔自治区', '阿勒泰地区', '吉木乃县', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5893, '2', '7', '659000', '65', '自治区直辖县级行政区划', '新疆维吾尔自治区', '自治区直辖县级行政区划', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5894, '3', '7', '659001', '65', '石河子市', '新疆维吾尔自治区', '自治区直辖县级行政区划', '石河子市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5895, '3', '7', '659002', '65', '阿拉尔市', '新疆维吾尔自治区', '自治区直辖县级行政区划', '阿拉尔市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5896, '3', '7', '659003', '65', '图木舒克市', '新疆维吾尔自治区', '自治区直辖县级行政区划', '图木舒克市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5897, '3', '7', '659004', '65', '五家渠市', '新疆维吾尔自治区', '自治区直辖县级行政区划', '五家渠市', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5898, '1', '8', '710000', '71', '台湾', '台湾', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5899, '2', '8', '710100', '71', '台湾', '台湾', '台湾', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5900, '3', '8', '710101', '71', '市辖区', '台湾', '台湾', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5901, '1', '8', '810000', '81', '香港', '香港', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5902, '2', '8', '810100', '81', '香港', '香港', '香港', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5903, '3', '8', '810101', '81', '市辖区', '香港', '香港', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5904, '1', '8', '820000', '82', '澳门', '澳门', '', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5905, '2', '8', '820100', '82', '澳门', '澳门', '澳门', '', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_are_m` VALUES (5906, '3', '8', '820101', '82', '市辖区', '澳门', '澳门', '市辖区', '', '', '99', NULL, '0', 'vteam', NULL, '2018-07-01 00:00:00', NULL, 'vteam', NULL, '2018-07-01 00:00:00');

-- ----------------------------
-- Table structure for fipa_bch_m
-- ----------------------------
DROP TABLE IF EXISTS `fipa_bch_m`;
CREATE TABLE `fipa_bch_m`  (
  `REFCODE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '流水号',
  `BANK_ID` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '银行网点代号',
  `BANK_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '银行网点名称',
  `BANK_LEVEL` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '网点级别[1=总行(行政)/2=总行(营业)/3=分(支)行]',
  `PARENT_BANK_ID` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上级行代号',
  `BELONG_BANK_ID` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属银行',
  `CNAPS_CODE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '银行大额行号',
  `BANK_ADDR` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '网点地址',
  `BANK_AREA_CODE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '网点所在地区',
  `BANK_ICON` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标名称',
  `DATA_STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资料状态[01=保存(即先在本地保存，还未提交到审批流中去）/11=流程中/99=生效]',
  `ENTITY_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批流PK',
  `DEL_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标记[是=1/否=0(Default)]',
  `CREATE_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATE_AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者代理人',
  `CREATE_DATE` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `EDTID` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `LAST_MOD_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者',
  `AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作代理人',
  `LAST_MOD_DATE` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '银行信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fipa_bch_m
-- ----------------------------

-- ----------------------------
-- Table structure for fipa_ccy_m
-- ----------------------------
DROP TABLE IF EXISTS `fipa_ccy_m`;
CREATE TABLE `fipa_ccy_m`  (
  `REFCODE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '流水号',
  `CCYID` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '币别代号',
  `CCYDESC` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '币别描述',
  `DECIMAL_POSI` decimal(10, 0) NULL DEFAULT 0 COMMENT '精确至小数位数',
  `CCY_EDIT_FMT` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编辑格式',
  `CCY_DISP_FMT` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示格式',
  `BASE_DAY` decimal(10, 0) NULL DEFAULT 0 COMMENT '计息基础日',
  `EXGRATE_01` decimal(16, 6) NULL DEFAULT 0.000000 COMMENT '币别利率换算设定1',
  `EXGRATE_02` decimal(16, 6) NULL DEFAULT 0.000000 COMMENT '币别利率换算设定2',
  `EXGRATE_03` decimal(16, 6) NULL DEFAULT 0.000000 COMMENT '币别利率换算设定3',
  `EXGRATE_04` decimal(16, 6) NULL DEFAULT 0.000000 COMMENT '币别利率换算设定4',
  `EXGRATE_05` decimal(16, 6) NULL DEFAULT 0.000000 COMMENT '币别利率换算设定5',
  `EXGRATE_06` decimal(16, 6) NULL DEFAULT 0.000000 COMMENT '币别利率换算设定6',
  `EXGRATE_07` decimal(16, 6) NULL DEFAULT 0.000000 COMMENT '币别利率换算设定7',
  `EXGRATE_08` decimal(16, 6) NULL DEFAULT 0.000000 COMMENT '币别利率换算设定8',
  `EXGRATE_09` decimal(16, 6) NULL DEFAULT 0.000000 COMMENT '币别利率换算设定9',
  `EXGRATE_10` decimal(16, 6) NULL DEFAULT 0.000000 COMMENT '币别利率换算设定10',
  `EXGRATE_11` decimal(16, 6) NULL DEFAULT 0.000000 COMMENT '币别利率换算设定11',
  `EXGRATE_12` decimal(16, 6) NULL DEFAULT 0.000000 COMMENT '币别利率换算设定12',
  `HOST_CCYID` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '当前主机币别',
  `BUSSDATE` datetime NULL DEFAULT NULL COMMENT '当前营业日',
  `BUP_PCNT` decimal(16, 6) NULL DEFAULT 0.000000 COMMENT '买入上浮百分比(0～1之间)',
  `SDW_PCNT` decimal(16, 6) NULL DEFAULT 0.000000 COMMENT '卖出下浮百分比(0～1之间)',
  `UNIT_PRICE` decimal(10, 0) NULL DEFAULT 0 COMMENT '货币单价',
  `EDI_CCYID` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'EDI币别代号',
  `DATA_STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资料状态[01=保存(即先在本地保存，还未提交到审批流中去）/11=流程中/99=生效]',
  `ENTITY_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批流PK',
  `DEL_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标记[是=1/否=0(Default)]',
  `CREATE_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATE_AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者代理人',
  `CREATE_DATE` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `EDTID` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `LAST_MOD_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者',
  `AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作代理人',
  `LAST_MOD_DATE` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '货币别信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fipa_ccy_m
-- ----------------------------

-- ----------------------------
-- Table structure for fipa_cty_m
-- ----------------------------
DROP TABLE IF EXISTS `fipa_cty_m`;
CREATE TABLE `fipa_cty_m`  (
  `REFCODE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '流水号',
  `CTYID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '国家别代号',
  `CTY_DESC` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '国家名称',
  `EDI_CTYID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'EDI国家别代号',
  `DATA_STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资料状态[01=保存(即先在本地保存，还未提交到审批流中去）/11=流程中/99=生效]',
  `ENTITY_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批流PK',
  `DEL_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标记[是=1/否=0(Default)]',
  `CREATE_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATE_AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者代理人',
  `CREATE_DATE` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `EDTID` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `LAST_MOD_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者',
  `AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作代理人',
  `LAST_MOD_DATE` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '国家别信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fipa_cty_m
-- ----------------------------

-- ----------------------------
-- Table structure for fipa_hdy_m
-- ----------------------------
DROP TABLE IF EXISTS `fipa_hdy_m`;
CREATE TABLE `fipa_hdy_m`  (
  `REFCODE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '流水号',
  `HDY_YEAR` decimal(10, 0) NULL DEFAULT 0 COMMENT '节假日日期年',
  `HDY_MONTH` decimal(10, 0) NULL DEFAULT 0 COMMENT '节假日日期月',
  `HDY_DAY` decimal(10, 0) NULL DEFAULT 0 COMMENT '节假日日期日',
  `HDY_DESC` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节假日描述',
  `DATA_STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资料状态[01=保存(即先在本地保存，还未提交到审批流中去）/11=流程中/99=生效]',
  `ENTITY_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批流PK',
  `DEL_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标记[是=1/否=0(Default)]',
  `CREATE_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATE_AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者代理人',
  `CREATE_DATE` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `EDTID` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `LAST_MOD_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者',
  `AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作代理人',
  `LAST_MOD_DATE` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '节假日信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fipa_hdy_m
-- ----------------------------
INSERT INTO `fipa_hdy_m` VALUES (2065, 2023, 1, 1, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2066, 2023, 1, 7, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2067, 2023, 1, 8, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2068, 2023, 1, 14, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2069, 2023, 1, 15, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2070, 2023, 1, 21, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2071, 2023, 1, 22, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2072, 2023, 1, 28, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2073, 2023, 1, 29, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2074, 2023, 2, 4, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2075, 2023, 2, 5, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2076, 2023, 2, 11, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2077, 2023, 2, 12, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2078, 2023, 2, 18, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2079, 2023, 2, 19, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2080, 2023, 2, 25, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2081, 2023, 2, 26, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2082, 2023, 3, 4, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2083, 2023, 3, 5, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2084, 2023, 3, 11, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2085, 2023, 3, 12, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2086, 2023, 3, 18, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2087, 2023, 3, 19, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2088, 2023, 3, 25, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2089, 2023, 3, 26, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2090, 2023, 4, 1, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2091, 2023, 4, 2, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2092, 2023, 4, 8, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2093, 2023, 4, 9, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2094, 2023, 4, 15, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2095, 2023, 4, 16, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2096, 2023, 4, 22, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2097, 2023, 4, 23, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2098, 2023, 4, 29, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2099, 2023, 4, 30, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2100, 2023, 5, 6, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2101, 2023, 5, 7, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2102, 2023, 5, 13, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2103, 2023, 5, 14, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2104, 2023, 5, 20, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2105, 2023, 5, 21, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2106, 2023, 5, 27, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2107, 2023, 5, 28, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2108, 2023, 6, 3, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2109, 2023, 6, 4, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2110, 2023, 6, 10, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2111, 2023, 6, 11, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2112, 2023, 6, 17, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2113, 2023, 6, 18, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2114, 2023, 6, 24, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2115, 2023, 6, 25, '', NULL, NULL, '1', NULL, NULL, NULL, 'MOD', 'admin', NULL, '2023-07-01 07:18:41');
INSERT INTO `fipa_hdy_m` VALUES (2116, 2023, 7, 1, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2117, 2023, 7, 2, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2118, 2023, 7, 8, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2119, 2023, 7, 9, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2120, 2023, 7, 15, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2121, 2023, 7, 16, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2122, 2023, 7, 22, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2123, 2023, 7, 23, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2124, 2023, 7, 29, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2125, 2023, 7, 30, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2126, 2023, 8, 5, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2127, 2023, 8, 6, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2128, 2023, 8, 12, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2129, 2023, 8, 13, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2130, 2023, 8, 19, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2131, 2023, 8, 20, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2132, 2023, 8, 26, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2133, 2023, 8, 27, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2134, 2023, 9, 2, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2135, 2023, 9, 3, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2136, 2023, 9, 9, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2137, 2023, 9, 10, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2138, 2023, 9, 16, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2139, 2023, 9, 17, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2140, 2023, 9, 23, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2141, 2023, 9, 24, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2142, 2023, 9, 30, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2143, 2023, 10, 1, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2144, 2023, 10, 7, '', NULL, NULL, '1', NULL, NULL, NULL, 'MOD', 'ruangaofeng', NULL, '2023-07-04 13:36:46');
INSERT INTO `fipa_hdy_m` VALUES (2145, 2023, 10, 8, '', NULL, NULL, '1', NULL, NULL, NULL, 'MOD', 'ruangaofeng', NULL, '2023-07-04 13:36:55');
INSERT INTO `fipa_hdy_m` VALUES (2146, 2023, 10, 14, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2147, 2023, 10, 15, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2148, 2023, 10, 21, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2149, 2023, 10, 22, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2150, 2023, 10, 28, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2151, 2023, 10, 29, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2152, 2023, 11, 4, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2153, 2023, 11, 5, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2154, 2023, 11, 11, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2155, 2023, 11, 12, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2156, 2023, 11, 18, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2157, 2023, 11, 19, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2158, 2023, 11, 25, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2159, 2023, 11, 26, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2160, 2023, 12, 2, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2161, 2023, 12, 3, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2162, 2023, 12, 9, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2163, 2023, 12, 10, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2164, 2023, 12, 16, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2165, 2023, 12, 17, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2166, 2023, 12, 23, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2167, 2023, 12, 24, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2168, 2023, 12, 30, '周六', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2169, 2023, 12, 31, '周日', NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fipa_hdy_m` VALUES (2276, 2023, 7, 21, '', '99', NULL, '1', 'admin', NULL, '2023-07-01 07:16:58', 'MOD', 'admin', NULL, '2023-07-01 07:17:48');
INSERT INTO `fipa_hdy_m` VALUES (2277, 2023, 6, 22, '', '99', NULL, '0', 'admin', NULL, '2023-07-01 07:18:12', 'NEW', 'admin', NULL, '2023-07-01 07:18:12');
INSERT INTO `fipa_hdy_m` VALUES (2278, 2023, 6, 23, '', '99', NULL, '0', 'admin', NULL, '2023-07-01 07:18:29', 'NEW', 'admin', NULL, '2023-07-01 07:18:29');
INSERT INTO `fipa_hdy_m` VALUES (2321, 2023, 9, 29, '中秋', '99', NULL, '0', 'ruangaofeng', NULL, '2023-07-04 13:35:09', 'NEW', 'ruangaofeng', NULL, '2023-07-04 13:35:09');
INSERT INTO `fipa_hdy_m` VALUES (2322, 2023, 10, 2, '国庆', '99', NULL, '0', 'ruangaofeng', NULL, '2023-07-04 13:35:56', 'NEW', 'ruangaofeng', NULL, '2023-07-04 13:35:56');
INSERT INTO `fipa_hdy_m` VALUES (2323, 2023, 10, 3, '国庆', '99', NULL, '0', 'ruangaofeng', NULL, '2023-07-04 13:36:08', 'NEW', 'ruangaofeng', NULL, '2023-07-04 13:36:08');
INSERT INTO `fipa_hdy_m` VALUES (2324, 2023, 10, 4, '国庆', '99', NULL, '0', 'ruangaofeng', NULL, '2023-07-04 13:36:16', 'NEW', 'ruangaofeng', NULL, '2023-07-04 13:36:16');
INSERT INTO `fipa_hdy_m` VALUES (2325, 2023, 10, 5, '国庆', '99', NULL, '0', 'ruangaofeng', NULL, '2023-07-04 13:36:22', 'NEW', 'ruangaofeng', NULL, '2023-07-04 13:36:22');
INSERT INTO `fipa_hdy_m` VALUES (2326, 2023, 10, 6, '国庆', '99', NULL, '0', 'ruangaofeng', NULL, '2023-07-04 13:36:29', 'NEW', 'ruangaofeng', NULL, '2023-07-04 13:36:29');

-- ----------------------------
-- Table structure for fipa_num_m
-- ----------------------------
DROP TABLE IF EXISTS `fipa_num_m`;
CREATE TABLE `fipa_num_m`  (
  `REFCODE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '流水号',
  `NUM_TYPE` decimal(10, 0) NULL DEFAULT 0 COMMENT '编号类型[待定]',
  `NUM_VALUE` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号对应值',
  `NUM_DATE` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '统计日期',
  `CURR_SEQNUM` decimal(10, 0) NULL DEFAULT 0 COMMENT '目前序号',
  `LAST_MOD_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者',
  `LAST_MOD_DATE` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统编号生成表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fipa_num_m
-- ----------------------------

-- ----------------------------
-- Table structure for fipa_otp_m
-- ----------------------------
DROP TABLE IF EXISTS `fipa_otp_m`;
CREATE TABLE `fipa_otp_m`  (
  `REFCODE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '流水号',
  `OTPCODE` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '投向行业编码(系统生成，对树形结构进行编码，子孙编码内记载了父辈编码信息)',
  `OTPID` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '行业类别代号(客户设定)',
  `OTPNAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '行业类别名称',
  `OTPLVL` decimal(10, 0) NOT NULL DEFAULT 1 COMMENT '行业类别级别(层级编号，1=第一层，以此类推)',
  `LOAN_EXT01` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `DATA_STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资料状态[01=保存(即先在本地保存，还未提交到审批流中去）/11=流程中/99=生效]',
  `ENTITY_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批流PK',
  `DEL_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标记[是=1/否=0(Default)]',
  `CREATE_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATE_AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者代理人',
  `CREATE_DATE` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `EDTID` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `LAST_MOD_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者',
  `AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作代理人',
  `LAST_MOD_DATE` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '行业类别表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fipa_otp_m
-- ----------------------------

-- ----------------------------
-- Table structure for fipa_sys_e
-- ----------------------------
DROP TABLE IF EXISTS `fipa_sys_e`;
CREATE TABLE `fipa_sys_e`  (
  `REFCODE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '流水号',
  `MASTER_REFCODE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '主表流水号',
  `PARAM_NAME` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数名称',
  `PARAM_VALUE` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数值',
  `PARAM_TYPE` decimal(10, 0) NULL DEFAULT NULL COMMENT '参数类型',
  `PARAM_DESC` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数描述',
  `PARAM_STATUS` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '参数状态[1=页面显示且可修改(default)/2=页面显示且不可修改/3=页面不显示仅能在数据库修改]',
  `DATA_STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资料状态[01=保存(即先在本地保存，还未提交到审批流中去）/11=流程中/99=生效]',
  `ENTITY_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批流PK',
  `DEL_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标记[是=1/否=0(Default)]',
  `CREATE_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATE_AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者代理人',
  `CREATE_DATE` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `EDTID` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `LAST_MOD_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者',
  `AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作代理人',
  `LAST_MOD_DATE` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统参数设定历程表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fipa_sys_e
-- ----------------------------

-- ----------------------------
-- Table structure for fipa_sys_m
-- ----------------------------
DROP TABLE IF EXISTS `fipa_sys_m`;
CREATE TABLE `fipa_sys_m`  (
  `REFCODE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '流水号',
  `PARAM_NAME` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数名称',
  `PARAM_VALUE` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数值',
  `PARAM_TYPE` decimal(10, 0) NULL DEFAULT NULL COMMENT '参数类型',
  `PARAM_DESC` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数描述',
  `PARAM_STATUS` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '参数状态[1=页面显示且可修改(default)/2=页面显示且不可修改/3=页面不显示仅能在数据库修改]',
  `DATA_STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资料状态[01=保存(即先在本地保存，还未提交到审批流中去）/11=流程中/99=生效]',
  `ENTITY_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批流PK',
  `DEL_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标记[是=1/否=0(Default)]',
  `CREATE_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATE_AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者代理人',
  `CREATE_DATE` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `EDTID` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `LAST_MOD_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者',
  `AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作代理人',
  `LAST_MOD_DATE` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统参数设定表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fipa_sys_m
-- ----------------------------
INSERT INTO `fipa_sys_m` VALUES (2, 'W_SYSTEM_LANGUAGE', 'zh_CN', 0, 'ÏµÍ³ÅäÖÃÄ¬ÈÏÓïÑÔ', '1', '99', NULL, '0', 'admin', NULL, '2018-07-01 00:00:00', NULL, 'admin', NULL, '2018-07-01 00:00:00');
INSERT INTO `fipa_sys_m` VALUES (3, 'REPEATED_LOGIN_FLAG', '1', 0, '是否允许用户重复登录[1:是(default),0:否]', '1', '99', NULL, '0', 'admin', NULL, '2018-07-03 00:00:00', NULL, 'admin', NULL, '2018-07-03 00:00:00');
INSERT INTO `fipa_sys_m` VALUES (4, 'SMS_SWITCH', '0', 0, '是否发送短信[1:是,0:否(default)]', '1', '99', NULL, '0', 'admin', NULL, '2018-07-02 00:00:00', NULL, 'admin', NULL, '2018-07-02 00:00:00');

-- ----------------------------
-- Table structure for fipa_tpp_m
-- ----------------------------
DROP TABLE IF EXISTS `fipa_tpp_m`;
CREATE TABLE `fipa_tpp_m`  (
  `REFCODE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '流水号',
  `PLATFORM_CODE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '平台代号',
  `PLATFORM_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '平台名称',
  `PLATFORM_SHORT_NAME` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '平台简称',
  `PLATFORM_URL` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '平台访问地址',
  `PLATFORM_INTERFACE_URL` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '平台接口地址',
  `PLATFORM_PUBLIC_KEY` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '平台对应公钥',
  `SYSTEM_PUBLIC_KEY` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '系统对应公钥',
  `SYSTEM_PRIVATE_KEY` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '系统对应私钥',
  `SYSTEM_KEY_IV` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '系统对称加密偏移量',
  `CRYPT_TYPE` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '接口加密方式[0=不加密/R=RSA非对称加密/A=ASE对称加密]',
  `INDUSTRY_TYPE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '平台所属行业',
  `DATA_STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资料状态[01=保存(即先在本地保存，还未提交到审批流中去）/11=流程中/99=生效]',
  `ENTITY_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批流PK',
  `DEL_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标记[是=1/否=0(Default)]',
  `CREATE_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATE_AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者代理人',
  `CREATE_DATE` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `EDTID` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `LAST_MOD_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者',
  `AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作代理人',
  `LAST_MOD_DATE` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '第三方平台信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fipa_tpp_m
-- ----------------------------

-- ----------------------------
-- Table structure for flog_api_m
-- ----------------------------
DROP TABLE IF EXISTS `flog_api_m`;
CREATE TABLE `flog_api_m`  (
  `REFCODE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '流水号',
  `API_ID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接口唯一标识码',
  `API_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接口名称',
  `API_TYPE` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接口类型[I=入方向/O=出方向]',
  `REQUEST_BODY` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求报文',
  `RESPONSE_BODY` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '响应报文',
  `REQUEST_TIME` datetime NULL DEFAULT NULL COMMENT '请求时间',
  `RESPONSE_TIME` datetime NULL DEFAULT NULL COMMENT '响应时间',
  `RESULT_CODE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '响应状态码',
  `CREATE_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATE_DATE` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `LAST_MOD_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者',
  `LAST_MOD_DATE` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '应用程序接口记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flog_api_m
-- ----------------------------

-- ----------------------------
-- Table structure for flog_opt_m
-- ----------------------------
DROP TABLE IF EXISTS `flog_opt_m`;
CREATE TABLE `flog_opt_m`  (
  `REFCODE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '流水号',
  `FUN_ID` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '功能代号(对应菜单树的代号) (登陆为:login,登出为:logout)',
  `FUN_DESC` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '功能描述(对应菜单树的描述)',
  `EDTID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作代号[login(登录)、logout(登出)、ENT(进入功能)、SMT(提交)、NEW、MOD、DEL/……]',
  `EDT_DESC` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作描述',
  `XML_PATH` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调用XML地址',
  `SERVICE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调用后台SERVICE名',
  `FUNCTION_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调用后台FUNCTION名',
  `PARAMETERS` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '提交后台的数据(截取前1000个字符)',
  `OPERATOR_IP` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者IP',
  `OPERATOR_ADDRESS` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者地址',
  `OPERATOR` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者',
  `OPERATOR_ORG_REFCODE` decimal(10, 0) NULL DEFAULT 0 COMMENT '操作者机构流水号',
  `OPERATE_DATE` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `USER_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者名称',
  `MOBILEPHONE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者手机号',
  `ORGNAME` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者机构名称',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flog_opt_m
-- ----------------------------

-- ----------------------------
-- Table structure for fspa_col_m
-- ----------------------------
DROP TABLE IF EXISTS `fspa_col_m`;
CREATE TABLE `fspa_col_m`  (
  `REFCODE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '流水号',
  `COL_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '多语言ID',
  `LANGUAGE_ID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '语言种类',
  `COL_DESC` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '多语言描述表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fspa_col_m
-- ----------------------------

-- ----------------------------
-- Table structure for fspa_exg_m
-- ----------------------------
DROP TABLE IF EXISTS `fspa_exg_m`;
CREATE TABLE `fspa_exg_m`  (
  `REFCODE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '流水号',
  `PARM_TYPE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数类别[参考SMME_WEB_INITIALIZE.xls说明文件]',
  `PARM_NAME` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数代号',
  `PARM_VALUE` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数值',
  `PARM_DESC` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数说明',
  `PARENT_ID` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级代号',
  `PARENT_TYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级的参数类别',
  `PARM_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '2' COMMENT '参数值标记[真实值=1/多语言代号=2]',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fspa_exg_m
-- ----------------------------

-- ----------------------------
-- Table structure for fspa_seq_m
-- ----------------------------
DROP TABLE IF EXISTS `fspa_seq_m`;
CREATE TABLE `fspa_seq_m`  (
  `NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '序列名',
  `CURRENT_VALUE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '当前值',
  PRIMARY KEY (`NAME`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据库序列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fspa_seq_m
-- ----------------------------
INSERT INTO `fspa_seq_m` VALUES ('SEQUENCE_CSPA_FUN_M', 20);
INSERT INTO `fspa_seq_m` VALUES ('SEQUENCE_FSPA_EXG_M', 1);
INSERT INTO `fspa_seq_m` VALUES ('SEQUENCE_FSPA_COL_M', 1);
INSERT INTO `fspa_seq_m` VALUES ('SEQUENCE_FSPA_SEQ_M', 1);
INSERT INTO `fspa_seq_m` VALUES ('SEQUENCE_FIPA_SYS_M', 4);
INSERT INTO `fspa_seq_m` VALUES ('SEQUENCE_FIPA_CCY_M', 1);
INSERT INTO `fspa_seq_m` VALUES ('SEQUENCE_FIPA_CTY_M', 1);
INSERT INTO `fspa_seq_m` VALUES ('SEQUENCE_FIPA_ARE_M', 1);
INSERT INTO `fspa_seq_m` VALUES ('SEQUENCE_FIPA_OTP_M', 1);
INSERT INTO `fspa_seq_m` VALUES ('SEQUENCE_FIPA_BCH_M', 1);
INSERT INTO `fspa_seq_m` VALUES ('SEQUENCE_FIPA_NUM_M', 1);
INSERT INTO `fspa_seq_m` VALUES ('SEQUENCE_FIPA_HDY_M', 1);
INSERT INTO `fspa_seq_m` VALUES ('SEQUENCE_FIPA_TPP_M', 1);
INSERT INTO `fspa_seq_m` VALUES ('SEQUENCE_FIPA_SYS_E', 1);
INSERT INTO `fspa_seq_m` VALUES ('SEQUENCE_FBTX_APX_M', 1);
INSERT INTO `fspa_seq_m` VALUES ('SEQUENCE_FBTX_NOT_M', 1);
INSERT INTO `fspa_seq_m` VALUES ('SEQUENCE_FBTX_UR_MSG_M', 1);
INSERT INTO `fspa_seq_m` VALUES ('SEQUENCE_SIPA_BUR_M', 2);
INSERT INTO `fspa_seq_m` VALUES ('SEQUENCE_SIPA_ROL_M', 2);
INSERT INTO `fspa_seq_m` VALUES ('SEQUENCE_SIPA_RTF_M', 15);
INSERT INTO `fspa_seq_m` VALUES ('SEQUENCE_SIPA_BTR_M', 2);
INSERT INTO `fspa_seq_m` VALUES ('SEQUENCE_SIPA_BTP_M', 1);
INSERT INTO `fspa_seq_m` VALUES ('SEQUENCE_FLOG_API_M', 1);
INSERT INTO `fspa_seq_m` VALUES ('SEQUENCE_FLOG_OPT_M', 1);

-- ----------------------------
-- Table structure for sipa_btp_m
-- ----------------------------
DROP TABLE IF EXISTS `sipa_btp_m`;
CREATE TABLE `sipa_btp_m`  (
  `REFCODE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '流水号',
  `USERID` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户代号',
  `PROVINCE_CODE` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省份代号',
  `EDTID` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `LAST_MOD_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者',
  `LAST_MOD_DATE` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台用户与省份关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sipa_btp_m
-- ----------------------------

-- ----------------------------
-- Table structure for sipa_btr_m
-- ----------------------------
DROP TABLE IF EXISTS `sipa_btr_m`;
CREATE TABLE `sipa_btr_m`  (
  `REFCODE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '流水号',
  `USERID` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户代号',
  `ROLEID` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色代号',
  `ORG_REFCODE` decimal(10, 0) NULL DEFAULT 0 COMMENT '所属企业流水号',
  `EDTID` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `LAST_MOD_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者',
  `LAST_MOD_DATE` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sipa_btr_m
-- ----------------------------
INSERT INTO `sipa_btr_m` VALUES (2, 'admin', 'admin', 0, 'NEW', 'vteam', '2023-08-28 15:26:06');

-- ----------------------------
-- Table structure for sipa_bur_m
-- ----------------------------
DROP TABLE IF EXISTS `sipa_bur_m`;
CREATE TABLE `sipa_bur_m`  (
  `REFCODE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '流水号',
  `SYSTEM_TYPE` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所属系统类型[1=后台/2=前台]',
  `USERID` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户代号(前台自动生成、后台登录账号)',
  `LOGIN_ID` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `USER_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `CHGPWD_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '用户登陆时是否要求修改密码[0=否/1=是(default)]',
  `VALID_DAY` decimal(10, 0) NULL DEFAULT NULL COMMENT '密码有效天数',
  `WARNING_DAY` decimal(10, 0) NULL DEFAULT NULL COMMENT '密码到期前警告天数',
  `PWD_EXPIRYDATE` datetime NULL DEFAULT NULL COMMENT '密码到期日(据ValidDay算出)',
  `CHGPWD_DATE` datetime NULL DEFAULT NULL COMMENT '上次更改密码时间',
  `RELEASE_AREA` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属地区[HK-香港，TW-台湾，SH-上海]',
  `FULL_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户姓名(无用字段)',
  `MOBILEPHONE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `EMAIL` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `TEL_AREA` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '座机号码（区号）',
  `TEL` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '座机号码（电话）',
  `EXTNO` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '座机号码（分机号）',
  `WEIXIN` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信号码',
  `WEIXIN_OPEN_ID` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信openId',
  `AREA_COUNTY` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所在地区',
  `PROVINCE_CODE` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省份代码',
  `ADDRESS` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `POSTCODE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮政编码',
  `JOBPOSITION` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人职称/职务',
  `ID_NO` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `LAST_LOGIN_ADDRESS` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上次登录地址',
  `LAST_LOGIN_DATE` datetime NULL DEFAULT NULL COMMENT '上次登录时间',
  `BRAND_REFCODE` decimal(10, 0) NULL DEFAULT 0 COMMENT '所属品牌流水号（用户类型为金融机构时为0）',
  `REGISTER_BRAND_REFCODE` decimal(10, 0) NULL DEFAULT 0 COMMENT '会员注册时品牌流水号（用于消息提醒）',
  `WEB_USER_TYPE` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '前台用户类型[1=企业/2=金融机构/3=合作机构/9=个人]',
  `MOBILE_VAILD_STATUS` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '手机验证状态[0=未验证/1=已验证]',
  `EMAIL_VAILD_STATUS` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '邮箱验证状态[0=未验证/1=已验证]',
  `REMARK` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '备注',
  `SOURCE` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '数据来源[I=正常(default,PC)/A=APP/X=小程序/W=微信/H=H5]',
  `THEME_STYLE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员主题样式(如：dynamic_deepblue)',
  `ADMIN_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否超级管理员[1=是/0=否]',
  `USE_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否启用[0=禁用/1=启用]',
  `ORGNAME` varchar(450) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `COMPANY_CREDIT_CODE` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '统一社会信用代码/注册号',
  `ACCOUNT_NO` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收款银行账号',
  `BANK_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开户银行名称',
  `CLIENT_MANAGER` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户经理',
  `CLIENT_MANAGER_PHONE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户经理手机号',
  `LEGAL_PERSON` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '法人代表',
  `LEGAL_ID_NO` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '法人身份证号码',
  `DATA_STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资料状态[01=保存(即先在本地保存，还未提交到审批流中去）/11=流程中/99=生效]',
  `ENTITY_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批流PK',
  `DEL_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标记[是=1/否=0(Default)]',
  `CREATE_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATE_AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者代理人',
  `CREATE_DATE` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `EDTID` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `LAST_MOD_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者',
  `AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作代理人',
  `LAST_MOD_DATE` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sipa_bur_m
-- ----------------------------
INSERT INTO `sipa_bur_m` VALUES (2, '1', 'admin', NULL, '管理员', '0192023a7bbd73250516f069df18b500', '0', 999, 99, NULL, NULL, NULL, NULL, '', '', '', '', '', NULL, NULL, NULL, NULL, '', NULL, '', NULL, NULL, NULL, 0, 0, NULL, '0', '0', '0', '0', NULL, '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '99', '', '0', NULL, NULL, '2023-08-28 15:26:07', 'new', 'vteam', NULL, '2023-08-28 15:26:07');

-- ----------------------------
-- Table structure for sipa_rol_m
-- ----------------------------
DROP TABLE IF EXISTS `sipa_rol_m`;
CREATE TABLE `sipa_rol_m`  (
  `REFCODE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '流水号',
  `SYSTEM_TYPE` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所属系统类型[1=后台/2=前台]',
  `ROLEID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色代号',
  `ROLE_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `ROLEDESC` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `PARENT_ROLEID` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父角色代号',
  `ORG_REFCODE` decimal(10, 0) NULL DEFAULT 0 COMMENT '所属企业流水号',
  `USE_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否启用[0=禁用/1=启用]',
  `ROLE_TYPE` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源类型[11=运营端/21=企业端/22=机构端/23=合伙人端/24=核心企业端/41=个人端]',
  `DATA_STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资料状态[01=保存(即先在本地保存，还未提交到审批流中去）/11=流程中/99=生效]',
  `ENTITY_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批流PK',
  `DEL_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标记[是=1/否=0(Default)]',
  `CREATE_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATE_AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者代理人',
  `CREATE_DATE` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `EDTID` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `LAST_MOD_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者',
  `AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作代理人',
  `LAST_MOD_DATE` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sipa_rol_m
-- ----------------------------
INSERT INTO `sipa_rol_m` VALUES (2, '1', 'admin', '管理员', '运营人员管理员角色', '', 0, '1', '11', '99', '', '0', 'admin', NULL, '2023-08-28 15:26:06', 'NEW', 'vteam', NULL, '2023-08-28 15:26:06');

-- ----------------------------
-- Table structure for sipa_rtf_m
-- ----------------------------
DROP TABLE IF EXISTS `sipa_rtf_m`;
CREATE TABLE `sipa_rtf_m`  (
  `REFCODE` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '流水号',
  `ROLEID` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色代号',
  `FUNID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '功能代号',
  `EDTID` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `LAST_MOD_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者',
  `LAST_MOD_DATE` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色与权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sipa_rtf_m
-- ----------------------------
INSERT INTO `sipa_rtf_m` VALUES (2, 'admin', 'sysmanage', 'NEW', 'nfmsuat', '2023-08-24 15:37:03');
INSERT INTO `sipa_rtf_m` VALUES (3, 'admin', 'sysmanage:operationlog:loglist', 'NEW', 'nfmsuat', '2023-08-24 15:37:02');
INSERT INTO `sipa_rtf_m` VALUES (4, 'admin', 'sysmanage:role:rolemanage', 'NEW', 'nfmsuat', '2023-08-24 15:37:02');
INSERT INTO `sipa_rtf_m` VALUES (5, 'admin', 'sysmanage:role:rolemanage:del', 'NEW', 'nfmsuat', '2023-08-24 15:37:02');
INSERT INTO `sipa_rtf_m` VALUES (6, 'admin', 'sysmanage:role:rolemanage:mod', 'NEW', 'nfmsuat', '2023-08-24 15:37:02');
INSERT INTO `sipa_rtf_m` VALUES (7, 'admin', 'sysmanage:role:rolemanage:new', 'NEW', 'nfmsuat', '2023-08-24 15:37:02');
INSERT INTO `sipa_rtf_m` VALUES (8, 'admin', 'sysmanage:role:rolemanage:query', 'NEW', 'nfmsuat', '2023-08-24 15:37:02');
INSERT INTO `sipa_rtf_m` VALUES (9, 'admin', 'sysmanage:role:rolemanage:use', 'NEW', 'nfmsuat', '2023-08-24 15:37:02');
INSERT INTO `sipa_rtf_m` VALUES (10, 'admin', 'sysmanage:user:operationpersonalmanage', 'NEW', 'nfmsuat', '2023-08-24 15:37:03');
INSERT INTO `sipa_rtf_m` VALUES (11, 'admin', 'sysmanage:user:operationpersonalmanage:del', 'NEW', 'nfmsuat', '2023-08-24 15:37:02');
INSERT INTO `sipa_rtf_m` VALUES (12, 'admin', 'sysmanage:user:operationpersonalmanage:mod', 'NEW', 'nfmsuat', '2023-08-24 15:37:02');
INSERT INTO `sipa_rtf_m` VALUES (13, 'admin', 'sysmanage:user:operationpersonalmanage:new', 'NEW', 'nfmsuat', '2023-08-24 15:37:02');
INSERT INTO `sipa_rtf_m` VALUES (14, 'admin', 'sysmanage:user:operationpersonalmanage:query', 'NEW', 'nfmsuat', '2023-08-24 15:37:02');
INSERT INTO `sipa_rtf_m` VALUES (15, 'admin', 'sysmanage:user:operationpersonalmanage:use', 'NEW', 'nfmsuat', '2023-08-24 15:37:02');

-- ----------------------------
-- Table structure for templates_m
-- ----------------------------
DROP TABLE IF EXISTS `templates_m`;
CREATE TABLE `templates_m`  (
  `REFCODE` decimal(10, 0) NOT NULL COMMENT '流水号',
  `TYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模版类型[协议=01]',
  `TYPE_SUB` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模版子类型',
  `PROREFCODE` decimal(10, 0) NULL DEFAULT NULL COMMENT '产品流水号',
  `ID` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模版编号（与templates/html下面模版文件名对应）',
  `CODE` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模版编码（与FileBKeyEnum枚举code对应）',
  `NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模版名称',
  `CONTEXT` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模版内容',
  `UUID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模版附件',
  `DATA_STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资料状态[01=保存(即先在本地保存，还未提交到审批流中去）/11=流程中/99=生效]',
  `ENTITY_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批流PK',
  `DEL_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标记[是=1/否=0(Default)]',
  `CREATE_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATE_AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者代理人',
  `CREATE_DATE` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `EDTID` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `LAST_MOD_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作者',
  `AGENT_USER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作代理人',
  `LAST_MOD_DATE` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`REFCODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '模版管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of templates_m
-- ----------------------------

-- ----------------------------
-- Function structure for NEXTVAL
-- ----------------------------
DROP FUNCTION IF EXISTS `NEXTVAL`;
delimiter ;;
CREATE FUNCTION `NEXTVAL`(SEQ_NAME VARCHAR(50))
 RETURNS decimal(10,0)
  COMMENT '获取指定序列下一取值'
BEGIN
    DECLARE CUR_VAL DECIMAL(10);
    SELECT CURRENT_VALUE + 1 INTO CUR_VAL FROM FSPA_SEQ_M WHERE NAME = SEQ_NAME;
    UPDATE FSPA_SEQ_M SET CURRENT_VALUE = CURRENT_VALUE + 1
    WHERE NAME = SEQ_NAME;
    RETURN CUR_VAL;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
