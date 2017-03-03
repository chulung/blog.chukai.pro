/*
Navicat MySQL Data Transfer

Source Server         : 112.124.127.23_3306
Source Server Version : 50173
Source Host           : 112.124.127.23:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-03-04 02:08:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `app_log`
-- ----------------------------
DROP TABLE IF EXISTS `app_log`;
CREATE TABLE `app_log` (
`id`  int(10) NOT NULL AUTO_INCREMENT ,
`type`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日志类型' ,
`level`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日志级别' ,
`log`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日志内容' ,
`create_time`  datetime NOT NULL COMMENT '创建时间' ,
PRIMARY KEY (`id`)
)
ENGINE=MyISAM
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=98034

;

-- ----------------------------
-- Records of app_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
`id`  int(10) NOT NULL AUTO_INCREMENT COMMENT 'id' ,
`title`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题' ,
`content`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容' ,
`create_time`  datetime NOT NULL ,
`update_time`  datetime NOT NULL ,
`author`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者' ,
`type_id`  int(10) NOT NULL COMMENT '文章类型' ,
`type_name`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '类型名' ,
`derivation_url`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '转载原文链接' ,
`version`  int(10) NOT NULL DEFAULT 0 COMMENT '版本' ,
`is_delete`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N' ,
`comment_count`  int(10) NOT NULL DEFAULT 0 COMMENT '评论数' ,
`visit_count`  int(10) NOT NULL DEFAULT 0 COMMENT '查看数' ,
`pic`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章默认图片' ,
`license`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '版权声明' ,
`summary`  varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '摘要' ,
`index_rank`  int(5) UNSIGNED NULL DEFAULT 0 ,
`tags`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签' ,
`length`  int(10) NOT NULL DEFAULT 0 COMMENT '字数' ,
PRIMARY KEY (`id`),
UNIQUE INDEX `uk_title` USING BTREE (`title`) ,
INDEX `idx_createtime` USING BTREE (`create_time`) ,
INDEX `idx_typeid` USING BTREE (`type_id`) 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=71

;

-- ----------------------------
-- Records of article
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `article_draft`
-- ----------------------------
DROP TABLE IF EXISTS `article_draft`;
CREATE TABLE `article_draft` (
`id`  int(10) NOT NULL AUTO_INCREMENT COMMENT 'id' ,
`article_id`  int(10) NULL DEFAULT NULL COMMENT '文章id' ,
`title`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题' ,
`content`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容' ,
`html_content`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`create_time`  datetime NOT NULL ,
`update_time`  datetime NOT NULL ,
`author`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`is_publish`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否已发布' ,
`type_id`  int(10) NOT NULL ,
`is_delete`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N' ,
`version`  int(11) NOT NULL DEFAULT 1 ,
`licence`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`tags`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`),
UNIQUE INDEX `uk_title` USING BTREE (`title`) ,
INDEX `idx_createtime` USING BTREE (`create_time`) ,
INDEX `idx_typeid` USING BTREE (`type_id`) 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=71

;

-- ----------------------------
-- Records of article_draft
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `article_draft_history`
-- ----------------------------
DROP TABLE IF EXISTS `article_draft_history`;
CREATE TABLE `article_draft_history` (
`history_id`  int(10) NOT NULL AUTO_INCREMENT ,
`id`  int(10) NULL DEFAULT NULL COMMENT 'id' ,
`article_id`  int(10) NULL DEFAULT NULL COMMENT '文章id' ,
`title`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题' ,
`content`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容' ,
`html_content`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`create_time`  datetime NULL DEFAULT NULL ,
`update_time`  datetime NULL DEFAULT NULL ,
`author`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`is_publish`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否已发布' ,
`type_id`  int(10) NULL DEFAULT NULL ,
`is_delete`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`version`  int(11) NULL DEFAULT 1 ,
`licence`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`tags`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`history_id`),
INDEX `idx_createtime` USING BTREE (`create_time`) ,
INDEX `idx_typeid` USING BTREE (`type_id`) 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=267

;

-- ----------------------------
-- Records of article_draft_history
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `article_tag`
-- ----------------------------
DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag` (
`id`  int(18) NOT NULL AUTO_INCREMENT ,
`tag_name`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签名' ,
`article_id`  int(18) NOT NULL COMMENT '文章id' ,
PRIMARY KEY (`id`),
INDEX `idx_article_id` USING BTREE (`article_id`) ,
INDEX `idx_tag_name` USING BTREE (`tag_name`) 
)
ENGINE=MyISAM
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=86

;

-- ----------------------------
-- Records of article_tag
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `ciki`
-- ----------------------------
DROP TABLE IF EXISTS `ciki`;
CREATE TABLE `ciki` (
`id`  int(18) NOT NULL AUTO_INCREMENT ,
`parent_id`  int(18) NOT NULL DEFAULT 0 COMMENT '父目录id' ,
`title`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '目录名或文章标题' ,
`en_index`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '英文索引' ,
`cate_level`  varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`markdown`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`html`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`create_time`  datetime NOT NULL ,
`update_time`  datetime NOT NULL ,
`is_delete`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N' ,
PRIMARY KEY (`id`),
UNIQUE INDEX `uk_folder_name` USING BTREE (`title`, `cate_level`) 
)
ENGINE=MyISAM
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=41

;

-- ----------------------------
-- Records of ciki
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `column_type`
-- ----------------------------
DROP TABLE IF EXISTS `column_type`;
CREATE TABLE `column_type` (
`id`  int(10) NOT NULL AUTO_INCREMENT ,
`cn_name`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`en_name`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`slogans`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=MyISAM
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=9

;

-- ----------------------------
-- Records of column_type
-- ----------------------------
BEGIN;
INSERT INTO `column_type` VALUES ('1', '技能', 'skills', '技术改变世界，那就让世界看到你的影响力。'), ('2', '心悟', 'sensibility', '路漫漫其修远兮 吾将上下而求索。'), ('3', '转载', 'reprints', '他山之石，可以攻玉。'), ('4', '未归类', 'other', null), ('5', '烹饪', 'cooking', '黑暗料理十八式菜谱精要(:з」∠)'), ('6', '健身', 'exercise', '好羡慕喝凉水都会胖的人，我只是想增个肌~'), ('7', '摄影', 'photography', null), ('8', '旅行', 'travel', '世界那么大，有空去看看！');
COMMIT;

-- ----------------------------
-- Table structure for `comments`
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
`id`  int(10) NOT NULL AUTO_INCREMENT ,
`article_id`  int(10) NOT NULL COMMENT '文章id' ,
`reply_id`  int(10) NULL DEFAULT NULL COMMENT '回复的comment.id' ,
`comment`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' ,
`create_time`  datetime NOT NULL ,
`user_name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`email`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`is_delete`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N' COMMENT '是否删除' ,
`website`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`),
INDEX `idx_article_id` USING BTREE (`article_id`, `is_delete`) 
)
ENGINE=MyISAM
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=31

;

-- ----------------------------
-- Records of comments
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `config`
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
`config_key`  varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`config_value`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' ,
`config_comment`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`config_key`)
)
ENGINE=MyISAM
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of config
-- ----------------------------
BEGIN;
INSERT INTO `config` VALUES ('RESET_SEARCH_INDEX', 'false', '重置索引标志');
COMMIT;

-- ----------------------------
-- Table structure for `meta_cl_blog_log`
-- ----------------------------
DROP TABLE IF EXISTS `meta_cl_blog_log`;
CREATE TABLE `meta_cl_blog_log` (
`id`  int(10) NOT NULL AUTO_INCREMENT ,
`site`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '站点' ,
`article_id`  int(10) NOT NULL COMMENT '文章id' ,
`post_id`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '对应站点文章id' ,
`lastest_post_time`  datetime NOT NULL COMMENT '最后推送时间' ,
PRIMARY KEY (`id`),
UNIQUE INDEX `uk_site_aid` USING BTREE (`site`, `article_id`) 
)
ENGINE=MyISAM
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=48

;

-- ----------------------------
-- Records of meta_cl_blog_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
`id`  int(10) NOT NULL AUTO_INCREMENT ,
`user_name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`password`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`nick_name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`session_id`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后一次登陆时sessionid,用于自动登陆' ,
`authority`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT 'A:管理员 V:游客' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=2

;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'chulung', '123456', 'chulung', 'fbe4ed4e-d084-4d45-ba3a-3c1cea744e211', 'A');
COMMIT;

-- ----------------------------
-- Table structure for `user_tracker`
-- ----------------------------
DROP TABLE IF EXISTS `user_tracker`;
CREATE TABLE `user_tracker` (
`id`  int(10) NOT NULL AUTO_INCREMENT ,
`href`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`tuid`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户追踪标识' ,
`referer`  varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`create_time`  datetime NOT NULL ,
`params_string`  varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=MyISAM
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=15021

;

-- ----------------------------
-- Records of user_tracker
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `visitor_info`
-- ----------------------------
DROP TABLE IF EXISTS `visitor_info`;
CREATE TABLE `visitor_info` (
`id`  int(10) NOT NULL AUTO_INCREMENT ,
`ip`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`user_agent`  varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`access_time`  datetime NULL DEFAULT NULL ,
`access_url`  varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`tuid`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户追踪标识' ,
`server_name`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=MyISAM
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=196455

;

-- ----------------------------
-- Records of visitor_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Auto increment value for `app_log`
-- ----------------------------
ALTER TABLE `app_log` AUTO_INCREMENT=98034;

-- ----------------------------
-- Auto increment value for `article`
-- ----------------------------
ALTER TABLE `article` AUTO_INCREMENT=71;

-- ----------------------------
-- Auto increment value for `article_draft`
-- ----------------------------
ALTER TABLE `article_draft` AUTO_INCREMENT=71;

-- ----------------------------
-- Auto increment value for `article_draft_history`
-- ----------------------------
ALTER TABLE `article_draft_history` AUTO_INCREMENT=267;

-- ----------------------------
-- Auto increment value for `article_tag`
-- ----------------------------
ALTER TABLE `article_tag` AUTO_INCREMENT=86;

-- ----------------------------
-- Auto increment value for `ciki`
-- ----------------------------
ALTER TABLE `ciki` AUTO_INCREMENT=41;

-- ----------------------------
-- Auto increment value for `column_type`
-- ----------------------------
ALTER TABLE `column_type` AUTO_INCREMENT=9;

-- ----------------------------
-- Auto increment value for `comments`
-- ----------------------------
ALTER TABLE `comments` AUTO_INCREMENT=31;

-- ----------------------------
-- Auto increment value for `meta_cl_blog_log`
-- ----------------------------
ALTER TABLE `meta_cl_blog_log` AUTO_INCREMENT=48;

-- ----------------------------
-- Auto increment value for `user`
-- ----------------------------
ALTER TABLE `user` AUTO_INCREMENT=2;


-- ----------------------------
-- Auto increment value for `user_tracker`
-- ----------------------------
ALTER TABLE `user_tracker` AUTO_INCREMENT=15021;

-- ----------------------------
-- Auto increment value for `visitor_info`
-- ----------------------------
ALTER TABLE `visitor_info` AUTO_INCREMENT=196455;
