/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80044
 Source Host           : localhost:3306
 Source Schema         : blood_donation

 Target Server Type    : MySQL
 Target Server Version : 80044
 File Encoding         : 65001

 Date: 07/07/2026 15:39:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ai_chat_session
-- ----------------------------
DROP TABLE IF EXISTS `ai_chat_session`;
CREATE TABLE `ai_chat_session`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'AI对话会话ID',
  `user_id` bigint NOT NULL COMMENT '所属用户ID',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '新的对话' COMMENT '会话标题',
  `messages_json` json NOT NULL COMMENT '完整对话消息JSON数组',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_ai_chat_session_user_id`(`user_id`) USING BTREE,
  INDEX `idx_ai_chat_session_update_time`(`update_time`) USING BTREE,
  CONSTRAINT `ai_chat_session_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'AI对话会话表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ai_chat_session
-- ----------------------------
INSERT INTO `ai_chat_session` VALUES (2074054827557269506, 3, '你好', '[]', '2026-07-06 16:56:20', '2026-07-06 17:06:35', 1);
INSERT INTO `ai_chat_session` VALUES (2074055341221179393, 3, '我该如何预约献血活动？', '[]', '2026-07-06 16:58:22', '2026-07-06 17:06:39', 1);
INSERT INTO `ai_chat_session` VALUES (2074057206918889474, 3, '我的检验结果代表什么？', '[{\"role\": \"user\", \"content\": \"我的检验结果代表什么？\"}, {\"role\": \"assistant\", \"content\": \"根据您的系统记录，最近两次献血（2026年7月4日、5日）的**初筛结果均为合格**。初筛合格代表您在现场通过健康询问和初步血液检测，血液可以进入后续的复检流程。\\n\\n目前系统暂未显示复检结果，因此血液是否最终合格、能否入库，还需要等待复检完成。您可以根据血站通知或后续查看系统更新来确认最终状态。\\n\\n如果您对检验结果有疑问，或想了解具体项目含义，建议直接咨询血站工作人员。\"}, {\"role\": \"user\", \"content\": \"献血前需要注意什么？\"}, {\"role\": \"assistant\", \"content\": \"献血前主要注意以下几点：\\n\\n- **饮食**：献血前一餐不要空腹，建议吃清淡食物（如稀饭、馒头），避免油腻、高蛋白食物（如油条、鸡蛋、豆浆）。\\n- **作息**：保证充足睡眠，不要熬夜，献血前一晚应休息好。\\n- **身体状况**：无发热、感冒、腹泻等不适，近期未服用影响血液的药物（如阿司匹林等）。\\n- **其他**：献血前不要饮酒，多喝水；穿着宽松舒适的衣物，方便暴露手臂。\\n\\n您目前的预约记录显示**2026年7月5日**上午有一场已预约的献血活动（状态：待参加），如果您是准备参加这场活动，可以按以上建议做好准备。如果有其他具体问题，欢迎继续提问！\"}]', '2026-07-06 17:05:47', '2026-07-06 17:06:02', 0);
INSERT INTO `ai_chat_session` VALUES (2074057514076160001, 3, '我该如何预约献血活动？', '[{\"role\": \"user\", \"content\": \"我该如何预约献血活动？\"}, {\"role\": \"assistant\", \"content\": \"当然，以下是预约献血活动的具体步骤：\\n\\n1. **登录系统**：进入系统后，先登录你的账号。\\n2. **完善个人档案**：在用户中心确保个人档案信息（如姓名、身份证号、血型、联系电话等）已填写完整。\\n3. **进入预约管理**：在“预约管理”中查看当前可预约的献血活动。\\n4. **选择活动与时段**：选择你感兴趣的活动，并预约**上午**或**下午**时段。\\n5. **提交预约**：确认信息后提交，之后可在“我的预约”中查看预约状态。\\n\\n根据系统记录，你已有最近的献血记录和预约。如果遇到任何问题，可以随时告诉我。\"}]', '2026-07-06 17:07:00', '2026-07-06 17:07:06', 0);

-- ----------------------------
-- Table structure for appointment
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '预约主键ID',
  `user_id` bigint NOT NULL COMMENT '献血者用户ID',
  `activity_id` bigint NOT NULL COMMENT '关联活动ID',
  `time_slot` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '预约时段：上午/下午',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '待参加' COMMENT '状态：待参加/已取消/已完成/已失效',
  `appointment_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '预约提交时间',
  `cancel_time` datetime NULL DEFAULT NULL COMMENT '取消时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `activity_id`(`activity_id`) USING BTREE,
  CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`activity_id`) REFERENCES `blood_activity` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2073353454604017667 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '献血预约记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of appointment
-- ----------------------------
INSERT INTO `appointment` VALUES (2073353454604017666, 3, 1, '上午', '待参加', '2026-07-04 18:29:19', NULL, '2026-07-04 18:29:19', '2026-07-04 18:29:19', 0);

-- ----------------------------
-- Table structure for blood_activity
-- ----------------------------
DROP TABLE IF EXISTS `blood_activity`;
CREATE TABLE `blood_activity`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '活动主键ID',
  `activity_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动名称',
  `location` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动地点',
  `activity_date` date NOT NULL COMMENT '活动开展日期',
  `morning_quota` int NOT NULL DEFAULT 0 COMMENT '上午总名额',
  `afternoon_quota` int NOT NULL DEFAULT 0 COMMENT '下午总名额',
  `morning_remaining` int NOT NULL DEFAULT 0 COMMENT '上午剩余名额',
  `afternoon_remaining` int NOT NULL DEFAULT 0 COMMENT '下午剩余名额',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '未开始' COMMENT '状态：未开始/进行中/已结束',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2072882713903288327 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '献血招募活动表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blood_activity
-- ----------------------------
INSERT INTO `blood_activity` VALUES (1, '市中心爱心献血屋日常采血', '张店区中心血站一楼大厅', '2026-07-10', 49, 50, 48, 50, '未开始', '2026-07-04 18:04:37', '2026-07-06 20:40:45', 0);
INSERT INTO `blood_activity` VALUES (2, '1', '1', '2026-07-01', 50, 50, 50, 50, '未开始', '2026-07-04 21:47:48', '2026-07-05 11:37:36', 1);
INSERT INTO `blood_activity` VALUES (2072882713903288322, '高校校园无偿献血活动', '山东理工大学大学生艺术广场', '2026-07-05', 49, 50, 49, 50, '未开始', '2026-07-06 20:40:18', '2026-07-06 21:49:59', 1);
INSERT INTO `blood_activity` VALUES (2072882713903288323, '献血屋日常采血', '中心血站一楼大厅', '2026-07-13', 50, 50, 50, 50, '未开始', '2026-07-06 20:42:38', '2026-07-06 20:42:38', 0);
INSERT INTO `blood_activity` VALUES (2072882713903288324, '高校校园无偿献血活动', '山东理工大学大学生艺术广场', '2026-07-16', 50, 50, 50, 50, '未开始', '2026-07-06 21:50:20', '2026-07-06 21:50:20', 0);
INSERT INTO `blood_activity` VALUES (2072882713903288325, '7-7活动测试', '山东理工大学大学生艺术广场', '2026-07-05', 10, 10, 10, 10, '未开始', '2026-07-07 10:18:43', '2026-07-07 10:18:43', 0);
INSERT INTO `blood_activity` VALUES (2072882713903288326, '7-7活动测试2', '山东理工大学大学生艺术广场', '2026-07-05', 10, 10, 10, 10, '未开始', '2026-07-07 13:28:14', '2026-07-07 13:28:14', 0);

-- ----------------------------
-- Table structure for blood_collection
-- ----------------------------
DROP TABLE IF EXISTS `blood_collection`;
CREATE TABLE `blood_collection`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '采血记录ID',
  `donor_id` bigint NOT NULL COMMENT '献血者档案ID',
  `donor_id_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '献血者身份证密文',
  `donate_amount` int NOT NULL COMMENT '献血量(ml/治疗量)',
  `donate_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '献血类型：全血/成分血',
  `initial_screen_result` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '初筛结果：合格/不合格',
  `collection_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '采血时间',
  `operator_id` bigint NULL DEFAULT NULL COMMENT '操作管理员ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `donor_id`(`donor_id`) USING BTREE,
  INDEX `operator_id`(`operator_id`) USING BTREE,
  CONSTRAINT `blood_collection_ibfk_1` FOREIGN KEY (`donor_id`) REFERENCES `donor` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `blood_collection_ibfk_2` FOREIGN KEY (`operator_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2073638933392011266 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '采血记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blood_collection
-- ----------------------------
INSERT INTO `blood_collection` VALUES (2073413193044176897, 2, '370781200410283311', 200, '全血', '合格', '2026-07-04 22:26:42', 1, '2026-07-04 22:26:42', '2026-07-04 22:26:42', 0);
INSERT INTO `blood_collection` VALUES (2073617161636544513, 2, '370781200410283311', 400, '全血', '合格', '2026-07-05 11:57:12', 1, '2026-07-05 11:57:11', '2026-07-05 11:57:11', 0);
INSERT INTO `blood_collection` VALUES (2073638933392011265, 6, 'TFeQVarQH1cEvNLpFj87/9tlSqoL26kTZwOkbg4fNwg=', 200, '全血', '合格', '2026-07-05 13:23:43', 1, '2026-07-05 13:23:42', '2026-07-05 13:23:42', 0);
INSERT INTO `blood_collection` VALUES (2074098888536272898, 7, 'DW+fU9BddIcL6Bpm1fG7vNtlSqoL26kTZwOkbg4fNwg=', 200, '全血', '合格', '2026-07-06 19:51:25', 2, '2026-07-06 19:51:24', '2026-07-06 19:51:24', 0);

-- ----------------------------
-- Table structure for blood_stock
-- ----------------------------
DROP TABLE IF EXISTS `blood_stock`;
CREATE TABLE `blood_stock`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '库存明细ID',
  `collection_id` bigint NOT NULL COMMENT '关联采血记录ID',
  `blood_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '血型',
  `blood_amount` int NOT NULL COMMENT '血量ml',
  `expire_date` date NOT NULL COMMENT '血液有效期',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '正常' COMMENT '库存状态：正常/临期/已过期/已出库',
  `out_unit` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用血单位',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入库时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_collection_id`(`collection_id`) USING BTREE,
  CONSTRAINT `blood_stock_ibfk_1` FOREIGN KEY (`collection_id`) REFERENCES `blood_collection` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '血液库存明细表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blood_stock
-- ----------------------------
INSERT INTO `blood_stock` VALUES (1, 2073617161636544513, 'B型', 400, '2026-08-11', '正常', NULL, '2026-07-07 15:02:43', '2026-07-07 15:02:43', 0);
INSERT INTO `blood_stock` VALUES (2, 2073638933392011265, 'O型', 200, '2026-08-11', '正常', NULL, '2026-07-07 15:02:43', '2026-07-07 15:02:43', 0);

-- ----------------------------
-- Table structure for blood_test
-- ----------------------------
DROP TABLE IF EXISTS `blood_test`;
CREATE TABLE `blood_test`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '检验记录ID',
  `collection_id` bigint NOT NULL COMMENT '关联采血记录ID',
  `donor_id` bigint NOT NULL COMMENT '献血者档案ID',
  `recheck_result` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '复检详细结果',
  `blood_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '待检验' COMMENT '血液状态：待检验/合格/不合格/已入库',
  `unqualified_reason` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '不合格原因',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `judge_time` datetime NULL DEFAULT NULL COMMENT '判定时间',
  `operator_id` bigint NULL DEFAULT NULL COMMENT '判定管理员ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_collection_id`(`collection_id`) USING BTREE,
  INDEX `donor_id`(`donor_id`) USING BTREE,
  INDEX `operator_id`(`operator_id`) USING BTREE,
  CONSTRAINT `blood_test_ibfk_1` FOREIGN KEY (`collection_id`) REFERENCES `blood_collection` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `blood_test_ibfk_2` FOREIGN KEY (`donor_id`) REFERENCES `donor` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `blood_test_ibfk_3` FOREIGN KEY (`operator_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2073638933475897346 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '血液检验记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blood_test
-- ----------------------------
INSERT INTO `blood_test` VALUES (2073413193094508545, 2073413193044176897, 2, NULL, '待检验', NULL, NULL, NULL, NULL, '2026-07-04 22:26:42', '2026-07-04 22:26:42', 0);
INSERT INTO `blood_test` VALUES (2073617161674293249, 2073617161636544513, 2, '合格', '已入库', NULL, '', '2026-07-07 15:02:24', 2, '2026-07-05 11:57:11', '2026-07-05 11:57:11', 0);
INSERT INTO `blood_test` VALUES (2073638933475897345, 2073638933392011265, 6, '合格', '已入库', NULL, '', '2026-07-07 15:02:14', 2, '2026-07-05 13:23:42', '2026-07-05 13:23:42', 0);
INSERT INTO `blood_test` VALUES (2074098888557244417, 2074098888536272898, 7, '不合格', '不合格', '其他', '', '2026-07-06 21:01:34', 2, '2026-07-06 19:51:24', '2026-07-06 19:51:24', 0);

-- ----------------------------
-- Table structure for donor
-- ----------------------------
DROP TABLE IF EXISTS `donor`;
CREATE TABLE `donor`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '档案主键ID',
  `user_id` bigint NULL DEFAULT NULL COMMENT '关联用户ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '献血者姓名',
  `id_card` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '身份证号（加密存储）',
  `blood_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '血型：A型/B型/O型/AB型',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '联系电话',
  `medical_history` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '病史（加密）',
  `donor_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '正常' COMMENT '献血状态：正常/暂缓/永久淘汰',
  `last_donate_date` date NULL DEFAULT NULL COMMENT '上次献血日期',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `gender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `attention_flag` tinyint NOT NULL DEFAULT 0 COMMENT '是否重点关注：0-否，1-是',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `donor_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '献血者档案表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of donor
-- ----------------------------
INSERT INTO `donor` VALUES (2, 3, '李四', 'TFeQVarQH1cEvNLpFj87/zs9UMkIvVHepWrGrmovRuM=', 'B型', '13465363301', 'q9U8w8aJYr9WTAdbh0MY9w==', '正常', NULL, '2026-07-04 18:50:18', '2026-07-06 19:38:30', 0, '男', 1, '3', 0);
INSERT INTO `donor` VALUES (3, 1, '张三', 'K67cL1djGmDfCc3++I05tu6jY9XxVAq2p7XhfdrnWCQ=', 'A型', '13465363302', 'LCMNVWqc8NRlWX4Dl0fqZA==', '正常', NULL, '2026-07-04 19:03:40', '2026-07-06 19:38:34', 0, '男', 1, '山东省淄博市张店区', 0);
INSERT INTO `donor` VALUES (6, NULL, '王文武', 'TFeQVarQH1cEvNLpFj87/9tlSqoL26kTZwOkbg4fNwg=', 'O型', '13465363302', 'RCDAr6v4nY/rkpH0Yy880g==', '正常', NULL, '2026-07-05 11:54:04', '2026-07-06 19:38:40', 0, '男', 4, '4', 0);
INSERT INTO `donor` VALUES (7, NULL, '王五', 'DW+fU9BddIcL6Bpm1fG7vNtlSqoL26kTZwOkbg4fNwg=', 'O型', '15863904119', NULL, '正常', NULL, '2026-07-06 19:44:08', '2026-07-06 19:44:08', 0, '男', 21, '山东理工大学', 0);
INSERT INTO `donor` VALUES (8, NULL, '李一', 'zljUu92TE0WJMLOTibjX6+SZB7hURFW8jDsDUAnoUsg=', 'B型', '15863904119', NULL, '正常', NULL, '2026-07-06 20:02:52', '2026-07-06 20:02:52', 0, '男', 36, '山东理工大学', 0);
INSERT INTO `donor` VALUES (9, NULL, '李二', 'qajTYXK001aWpUEPOMtB7afKfTZu0chWcmwh+SqMmOM=', 'B型', '19819151223', NULL, '正常', NULL, '2026-07-06 20:11:54', '2026-07-06 20:11:54', 0, '女', 29, '淄博淄川', 0);
INSERT INTO `donor` VALUES (10, NULL, '张二', 'CSpralndYFq55Y6A7lUr2G7SzzuMJ0VKNDhtRXCV4Eo=', 'A型', '19819151223', NULL, '正常', NULL, '2026-07-06 20:13:25', '2026-07-06 20:13:25', 0, '男', 37, '山东理工大学', 0);
INSERT INTO `donor` VALUES (11, NULL, '钱六', 'Ngo6pJEPxqijfLAYjcI/r33sTe6ZW/b52Wk7rF/yn2k=', 'AB型', '19819151223', NULL, '正常', NULL, '2026-07-06 20:14:01', '2026-07-06 20:14:01', 0, '女', 28, '山东理工大学', 0);

-- ----------------------------
-- Table structure for operation_log
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `operator_id` bigint NULL DEFAULT NULL COMMENT '操作人ID',
  `operator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作人姓名',
  `operation_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作类型：新增/修改/删除/导出等',
  `operation_content` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作内容详情',
  `operation_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `ip_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'IP地址',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `operator_id`(`operator_id`) USING BTREE,
  INDEX `operation_time`(`operation_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '操作日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of operation_log
-- ----------------------------

-- ----------------------------
-- Table structure for stock_threshold
-- ----------------------------
DROP TABLE IF EXISTS `stock_threshold`;
CREATE TABLE `stock_threshold`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '阈值ID',
  `blood_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '血型',
  `threshold_value` int NOT NULL DEFAULT 5000 COMMENT '安全库存阈值（ml）',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_blood_type`(`blood_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '库存阈值配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of stock_threshold
-- ----------------------------
INSERT INTO `stock_threshold` VALUES (1, 'A型', 5000, '2026-07-03 10:36:30', NULL);
INSERT INTO `stock_threshold` VALUES (2, 'B型', 5000, '2026-07-03 10:36:30', NULL);
INSERT INTO `stock_threshold` VALUES (3, 'O型', 5000, '2026-07-03 10:36:30', NULL);
INSERT INTO `stock_threshold` VALUES (4, 'AB型', 5000, '2026-07-03 10:36:30', NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '登录用户名',
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '登录密码（BCrypt加密）',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `role` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色：ROLE_DONOR/ROLE_ADMIN/ROLE_SUPER_ADMIN',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态：0-正常 1-禁用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2073333544196882435 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户账号表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$BjIS9y7RP1vdQL/sMuWEQeYKb3tHKwMx7M.KhYVWRLsMzT4OvOija', '管理员', 'ROLE_ADMIN', 0, '2026-07-03 10:35:47', '2026-07-03 11:16:45', 0);
INSERT INTO `sys_user` VALUES (2, 'superadmin', '$2a$10$g75E5/0RPtBhRUhfl5.j3.KqPUs1ohEWHdH7UNFMgYdXjc1amI1Qy', '超级管理员', 'ROLE_SUPER_ADMIN', 0, '2026-07-03 10:35:47', '2026-07-03 11:16:56', 0);
INSERT INTO `sys_user` VALUES (3, 'donor001', '$2a$10$96VA1mfKlA9HykWBqotDwePic2zY/W07BFFfOGxj5FgYzCC06zR9C', '张三', 'ROLE_DONOR', 0, '2026-07-03 10:35:47', '2026-07-03 11:17:02', 0);
INSERT INTO `sys_user` VALUES (2073333544196882434, 'admin01', '$2a$10$w2JBk2W/tjUPz61rOiVazeK7vO9zFedooi/phwStsT.sIHNPdB5zK', 'GGBOND', 'ROLE_DONOR', 0, '2026-07-04 17:10:12', '2026-07-04 17:10:12', 0);

SET FOREIGN_KEY_CHECKS = 1;
