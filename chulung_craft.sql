/*
Navicat MySQL Data Transfer

Source Server         : cklinux
Source Server Version : 50173
Source Host           : 112.124.127.23:3306
Source Database       : chulung_craft

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-01-04 09:56:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `app_log`
-- ----------------------------
DROP TABLE IF EXISTS `app_log`;
CREATE TABLE `app_log` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL COMMENT '日志类型',
  `level` varchar(20) NOT NULL COMMENT '日志级别',
  `log` text NOT NULL COMMENT '日志内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=96392 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_log
-- ----------------------------

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(200) NOT NULL COMMENT '标题',
  `context` text COMMENT '内容',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `author` varchar(20) NOT NULL COMMENT '作者',
  `type_id` int(10) NOT NULL COMMENT '文章类型',
  `derivation_url` varchar(200) DEFAULT NULL COMMENT '转载原文链接',
  `version` int(10) NOT NULL DEFAULT '0' COMMENT '版本',
  `is_delete` char(1) NOT NULL DEFAULT 'N',
  `comment_count` int(10) NOT NULL DEFAULT '0' COMMENT '评论数',
  `visit_count` int(10) NOT NULL DEFAULT '0' COMMENT '查看数',
  `pic` varchar(100) DEFAULT NULL COMMENT '文章默认图片',
  `license` varchar(200) DEFAULT NULL COMMENT '版权声明',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_title` (`title`) USING BTREE,
  KEY `idx_createtime` (`create_time`) USING BTREE,
  KEY `idx_typeid` (`type_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------

-- ----------------------------
-- Table structure for `article_draft`
-- ----------------------------
DROP TABLE IF EXISTS `article_draft`;
CREATE TABLE `article_draft` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `article_id` int(10) DEFAULT NULL COMMENT '文章id',
  `title` varchar(200) NOT NULL COMMENT '标题',
  `context` text COMMENT '内容',
  `html_context` text,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `author` varchar(20) NOT NULL,
  `is_publish` char(1) NOT NULL COMMENT '是否已发布',
  `type_id` int(10) NOT NULL,
  `is_delete` char(1) NOT NULL DEFAULT 'N',
  `version` int(11) NOT NULL DEFAULT '1',
  `licence` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_title` (`title`) USING BTREE,
  KEY `idx_createtime` (`create_time`) USING BTREE,
  KEY `idx_typeid` (`type_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_draft
-- ----------------------------

-- ----------------------------
-- Table structure for `article_draft_history`
-- ----------------------------
DROP TABLE IF EXISTS `article_draft_history`;
CREATE TABLE `article_draft_history` (
  `history_id` int(10) NOT NULL AUTO_INCREMENT,
  `id` int(10) DEFAULT NULL COMMENT 'id',
  `article_id` int(10) DEFAULT NULL COMMENT '文章id',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `context` text COMMENT '内容',
  `html_context` text,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL,
  `is_publish` char(1) DEFAULT NULL COMMENT '是否已发布',
  `type_id` int(10) DEFAULT NULL,
  `is_delete` char(1) DEFAULT NULL,
  `version` int(11) DEFAULT '1',
  `licence` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`history_id`),
  KEY `idx_createtime` (`create_time`) USING BTREE,
  KEY `idx_typeid` (`type_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_draft_history
-- ----------------------------

-- ----------------------------
-- Table structure for `ciki`
-- ----------------------------
DROP TABLE IF EXISTS `ciki`;
CREATE TABLE `ciki` (
  `id` int(18) NOT NULL AUTO_INCREMENT,
  `parent_id` int(18) NOT NULL DEFAULT '0' COMMENT '父目录id',
  `title` varchar(50) NOT NULL COMMENT '目录名或文章标题',
  `en_index` varchar(50) NOT NULL COMMENT '英文索引',
  `cate_level` varchar(4) NOT NULL,
  `markdown` text,
  `html` text,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `is_delete` char(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_folder_name` (`title`,`cate_level`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ciki
-- ----------------------------
INSERT INTO `ciki` VALUES ('1', '0', 'Ciki', 'ciki', 'L1', '', null, '2016-06-27 14:36:42', '2016-06-27 14:36:54', 'N');
INSERT INTO `ciki` VALUES ('12', '1', 'database', 'database', 'L2', '', null, '2016-06-27 14:36:44', '2016-06-27 14:36:57', 'N');
INSERT INTO `ciki` VALUES ('13', '1', '编程语言', 'program_language', 'L2', '', null, '2016-06-27 14:36:47', '2016-06-27 14:37:00', 'N');
INSERT INTO `ciki` VALUES ('14', '1', '服务器', 'server', 'L2', '', null, '2016-06-27 14:36:49', '2016-06-27 14:37:04', 'N');
INSERT INTO `ciki` VALUES ('15', '1', '项目管理', 'project_manage', 'L2', '', null, '2016-06-27 14:36:52', '2016-06-27 14:37:06', 'N');

-- ----------------------------
-- Table structure for `column_type`
-- ----------------------------
DROP TABLE IF EXISTS `column_type`;
CREATE TABLE `column_type` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `cn_name` varchar(20) NOT NULL,
  `en_name` varchar(20) NOT NULL,
  `slogans` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of column_type
-- ----------------------------
INSERT INTO `column_type` VALUES ('1', '技能', 'skills', '技术改变世界，那就让世界看到你的影响力。');
INSERT INTO `column_type` VALUES ('2', '心悟', 'sensibility', '路漫漫其修远兮 吾将上下而求索。');
INSERT INTO `column_type` VALUES ('3', '转载', 'reprints', '他山之石，可以攻玉。');
INSERT INTO `column_type` VALUES ('4', '未归类', 'other', null);
INSERT INTO `column_type` VALUES ('5', '吃货', 'food', '黑暗料理十八式菜谱精要(:з」∠)');
INSERT INTO `column_type` VALUES ('6', '健身', 'exercise', '好羡慕喝凉水都会胖的人，我只是想增个肌~');
INSERT INTO `column_type` VALUES ('7', '摄影', 'photography', null);
INSERT INTO `column_type` VALUES ('8', '旅行', 'travel', '世界那么大，有空去看看！');

-- ----------------------------
-- Table structure for `comments`
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `article_id` int(10) NOT NULL COMMENT '文章id',
  `reply_id` int(10) DEFAULT NULL COMMENT '回复的comment.id',
  `comment` varchar(500) NOT NULL DEFAULT '',
  `create_time` datetime NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `is_delete` varchar(1) NOT NULL DEFAULT 'N' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_article_id` (`article_id`,`is_delete`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `config`
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `config_key` varchar(20) NOT NULL,
  `config_value` varchar(200) NOT NULL DEFAULT '',
  `config_comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`config_key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES ('ARTICLE_LICENSE', '<p>本文基于 <a href=\"https://creativecommons.org/licenses/by-sa/4.0/\">知识共享署名-相同方式共享 4.0</a> 国际许可协议发布，转载必须保留署名及链接。', null);
INSERT INTO `config` VALUES ('RESET_SEARCH_INDEX', 'false', null);

-- ----------------------------
-- Table structure for `meta_cl_blog_log`
-- ----------------------------
DROP TABLE IF EXISTS `meta_cl_blog_log`;
CREATE TABLE `meta_cl_blog_log` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `site` varchar(10) NOT NULL COMMENT '站点',
  `article_id` int(10) NOT NULL COMMENT '文章id',
  `post_id` varchar(20) NOT NULL COMMENT '对应站点文章id',
  `lastest_post_time` datetime NOT NULL COMMENT '最后推送时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_site_aid` (`site`,`article_id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meta_cl_blog_log
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nick_name` varchar(50) NOT NULL,
  `session_id` varchar(50) DEFAULT NULL COMMENT '最后一次登陆时sessionid,用于自动登陆',
  `authority` char(1) NOT NULL DEFAULT '0' COMMENT 'A:管理员 V:游客',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for `user_tracker`
-- ----------------------------
DROP TABLE IF EXISTS `user_tracker`;
CREATE TABLE `user_tracker` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `href` varchar(100) NOT NULL,
  `tuid` varchar(50) NOT NULL COMMENT '用户追踪标识',
  `referer` varchar(1000) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `params_string` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10121 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_tracker
-- ----------------------------

-- ----------------------------
-- Table structure for `visitor_info`
-- ----------------------------
DROP TABLE IF EXISTS `visitor_info`;
CREATE TABLE `visitor_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `ip` varchar(200) DEFAULT NULL,
  `user_agent` varchar(2000) DEFAULT NULL,
  `access_time` datetime DEFAULT NULL,
  `access_url` varchar(2000) DEFAULT NULL,
  `tuid` varchar(50) DEFAULT NULL COMMENT '用户追踪标识',
  `server_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=156116 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of visitor_info
-- ----------------------------
