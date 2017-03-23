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
) ENGINE=MyISAM AUTO_INCREMENT=98045 DEFAULT CHARSET=utf8;

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
  `content` text COMMENT '内容',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `author` varchar(20) NOT NULL COMMENT '作者',
  `column_id` int(10) NOT NULL COMMENT '文章类型',
  `column_name` varchar(20) NOT NULL DEFAULT '' COMMENT '类型名',
  `derivation_url` varchar(200) DEFAULT NULL COMMENT '转载原文链接',
  `version` int(10) NOT NULL DEFAULT '0' COMMENT '版本',
  `is_delete` char(1) NOT NULL DEFAULT 'N',
  `comment_count` int(10) NOT NULL DEFAULT '0' COMMENT '评论数',
  `visit_count` int(10) NOT NULL DEFAULT '0' COMMENT '查看数',
  `pic` varchar(100) DEFAULT NULL COMMENT '文章默认图片',
  `license` varchar(200) NOT NULL DEFAULT '' COMMENT '版权声明',
  `summary` varchar(400) NOT NULL DEFAULT '' COMMENT '摘要',
  `index_rank` int(5) unsigned DEFAULT '0',
  `tags` varchar(100) DEFAULT NULL COMMENT '标签',
  `length` int(10) NOT NULL DEFAULT '0' COMMENT '字数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO article VALUES(1,'title','test content',now(),now(),'chulung',1,'技术','',1,'N',1,1,'','','summary',0,null,100);
-- ----------------------------
-- Table structure for `article_draft`
-- ----------------------------
DROP TABLE IF EXISTS `article_draft`;
CREATE TABLE `article_draft` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `article_id` int(10) DEFAULT NULL COMMENT '文章id',
  `title` varchar(200) NOT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `html_content` text,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `author` varchar(20) NOT NULL,
  `is_publish` char(1) NOT NULL COMMENT '是否已发布',
  `column_id` int(10) NOT NULL,
  `is_delete` char(1) NOT NULL DEFAULT 'N',
  `version` int(11) NOT NULL DEFAULT '1',
  `licence` varchar(200) DEFAULT NULL,
  `tags` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

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
  `content` text COMMENT '内容',
  `html_content` text,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL,
  `is_publish` char(1) DEFAULT NULL COMMENT '是否已发布',
  `column_id` int(10) DEFAULT NULL,
  `is_delete` char(1) DEFAULT NULL,
  `version` int(11) DEFAULT '1',
  `licence` varchar(200) DEFAULT NULL,
  `tags` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`history_id`)
) ENGINE=InnoDB AUTO_INCREMENT=267 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_draft_history
-- ----------------------------

-- ----------------------------
-- Table structure for `article_tag`
-- ----------------------------
DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag` (
  `id` int(18) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(10) NOT NULL COMMENT '标签名',
  `article_id` int(18) NOT NULL COMMENT '文章id',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=86 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_tag
-- ----------------------------
INSERT INTO article_tag VALUES(1,'java',1);
-- ----------------------------
-- Table structure for `columns`
-- ----------------------------
DROP TABLE IF EXISTS `columns`;
CREATE TABLE `columns` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `cn_name` varchar(20) NOT NULL,
  `en_name` varchar(20) NOT NULL,
  `slogans` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of columns
-- ----------------------------
INSERT INTO `columns` VALUES ('1', '技能', 'skills', '技术改变世界，那就让世界看到你的影响力。');
INSERT INTO `columns` VALUES ('2', '心悟', 'sensibility', '路漫漫其修远兮 吾将上下而求索。');
INSERT INTO `columns` VALUES ('3', '转载', 'reprints', '他山之石，可以攻玉。');
INSERT INTO `columns` VALUES ('4', '未归类', 'other', null);
INSERT INTO `columns` VALUES ('5', '烹饪', 'cooking', '黑暗料理十八式菜谱精要(:з」∠)');
INSERT INTO `columns` VALUES ('6', '健身', 'exercise', '好羡慕喝凉水都会胖的人，我只是想增个肌~');
INSERT INTO `columns` VALUES ('7', '摄影', 'photography', null);
INSERT INTO `columns` VALUES ('8', '旅行', 'travel', '世界那么大，有空去看看！');
INSERT INTO `columns` VALUES ('9', '文档', 'doc', null);

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
  `website` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comments
-- ----------------------------

-- ----------------------------
-- Table structure for `config`
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `config_key` varchar(40) NOT NULL,
  `config_value` varchar(200) NOT NULL DEFAULT '',
  `config_comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`config_key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES ('ARTICLE_LICENSE', '<p>本文基于 <a href=\"https://creativecommons.org/licenses/by-nc-nd/4.0/deed.zh\">知识共享-署名-非商业性使用-禁止演绎 4.0 国际</a>许可协议发布，转载必须保留署名及链接。', '默认版权声明');
INSERT INTO `config` VALUES ('RESET_SEARCH_INDEX', 'false', '重置索引标志');
INSERT INTO `config` VALUES ('RECOMMENDED_ARTICLE_IDS', '1', '推荐文章');

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
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

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
INSERT INTO `user` VALUES ('1', 'test', '123456', 'test', 'fbe4ed4e-d084-4d45-ba3a-3c1cea744e211', 'A');

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
) ENGINE=MyISAM AUTO_INCREMENT=15025 DEFAULT CHARSET=utf8;

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
) ENGINE=MyISAM AUTO_INCREMENT=196484 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of visitor_info
-- ----------------------------
