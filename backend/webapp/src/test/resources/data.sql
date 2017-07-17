SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for `app_log`
-- ----------------------------
DROP TABLE IF EXISTS `app_log`;
CREATE TABLE `app_log` (
  `id`          INT(10)     NOT NULL AUTO_INCREMENT,
  `type`        VARCHAR(20) NOT NULL  COMMENT '日志类型',
  `level`       VARCHAR(20) NOT NULL  COMMENT '日志级别',
  `log`         TEXT        NOT NULL  COMMENT '日志内容',
  `create_time` DATETIME    NOT NULL  COMMENT '创建时间',
  PRIMARY KEY (`id`)
)
  ENGINE = MyISAM
  AUTO_INCREMENT = 98045
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of app_log
-- ----------------------------
-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id`             INT(10)      NOT NULL AUTO_INCREMENT  COMMENT 'id',
  `uri` varchar(100) NOT NULL DEFAULT '' COMMENT '唯一标识符，title转英文生成',
  `title`          VARCHAR(200) NOT NULL  COMMENT '标题',
  `content`        TEXT COMMENT '内容',
  `create_time`    DATETIME     NOT NULL,
  `update_time`    DATETIME     NOT NULL,
  `author`         VARCHAR(20)  NOT NULL  COMMENT '作者',
  `column_id`      INT(10)      NOT NULL  COMMENT '文章类型',
  `column_name`    VARCHAR(20)  NOT NULL DEFAULT ''  COMMENT '类型名',
  `derivation_url` VARCHAR(200)          DEFAULT NULL  COMMENT '转载原文链接',
  `version`        INT(10)      NOT NULL DEFAULT '0'  COMMENT '版本',
  `is_delete`      CHAR(1)      NOT NULL DEFAULT 'N',  `comment_count`  INT(10)      NOT NULL DEFAULT '0'
  COMMENT '评论数',  `visit_count`    INT(10)      NOT NULL DEFAULT '0'
  COMMENT '查看数',  `pic`            VARCHAR(100)          DEFAULT NULL
  COMMENT '文章默认图片',  `license`        VARCHAR(200) NOT NULL DEFAULT ''
  COMMENT '版权声明',  `summary`        VARCHAR(400) NOT NULL DEFAULT ''
  COMMENT '摘要',  `index_rank`     INT(5) UNSIGNED       DEFAULT '0',
  `tags`           VARCHAR(100)          DEFAULT NULL  COMMENT '标签',
  `length`         INT(10)      NOT NULL DEFAULT '0'  COMMENT '字数',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 71
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO article VALUES
  (1, 'uri','title', 'test content', now(), now(), 'chulung', 1, '技术', '', 1, 'N', 1, 1, '', '', 'summary', 0, NULL, 100);
-- ----------------------------
-- Table structure for `article_draft`
-- ----------------------------
DROP TABLE IF EXISTS `article_draft`;
CREATE TABLE `article_draft` (
  `id`           INT(10)      NOT NULL AUTO_INCREMENT  COMMENT 'id',
  `uri` varchar(100) NOT NULL DEFAULT '' COMMENT '唯一标识符，title转英文生成',
  `article_id`   INT(10)               DEFAULT NULL  COMMENT '文章id',
  `title`        VARCHAR(200) NOT NULL  COMMENT '标题',
  `content`      TEXT COMMENT '内容',  `html_content` TEXT,
  `create_time`  DATETIME     NOT NULL,
  `update_time`  DATETIME     NOT NULL,
  `author`       VARCHAR(20)  NOT NULL,
  `is_publish`   CHAR(1)      NOT NULL  COMMENT '是否已发布',
  `column_id`    INT(10)      NOT NULL,
  `is_delete`    CHAR(1)      NOT NULL DEFAULT 'N',
  `version`      INT(11)      NOT NULL DEFAULT '1',
  `licence`      VARCHAR(200)          DEFAULT NULL,
  `tags`         VARCHAR(100)          DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 71
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of article_draft
-- ----------------------------

-- ----------------------------
-- Table structure for `article_draft_history`
-- ----------------------------
DROP TABLE IF EXISTS `article_draft_history`;
CREATE TABLE `article_draft_history` (
  `history_id`   INT(10) NOT NULL AUTO_INCREMENT,
  `id`           INT(10)          DEFAULT NULL  COMMENT 'id',
  `uri` varchar(100) NOT NULL DEFAULT '' COMMENT '唯一标识符，title转英文生成',
  `article_id`   INT(10)          DEFAULT NULL  COMMENT '文章id',
  `title`        VARCHAR(200)     DEFAULT NULL  COMMENT '标题',
  `content`      TEXT COMMENT '内容',
  `html_content` TEXT,
  `create_time`  DATETIME         DEFAULT NULL,
  `update_time`  DATETIME         DEFAULT NULL,
  `author`       VARCHAR(20)      DEFAULT NULL,
  `is_publish`   CHAR(1)          DEFAULT NULL  COMMENT '是否已发布',
  `column_id`    INT(10)          DEFAULT NULL,
  `is_delete`    CHAR(1)          DEFAULT NULL,
  `version`      INT(11)          DEFAULT '1',
  `licence`      VARCHAR(200)     DEFAULT NULL,
  `tags`         VARCHAR(100)     DEFAULT NULL,
  PRIMARY KEY (`history_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 267
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of article_draft_history
-- ----------------------------

-- ----------------------------
-- Table structure for `article_tag`
-- ----------------------------
DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag` (
  `id`         INT(18)     NOT NULL AUTO_INCREMENT,
  `tag_name`   VARCHAR(10) NOT NULL  COMMENT '标签名',
  `article_id` INT(18)     NOT NULL  COMMENT '文章id',
  PRIMARY KEY (`id`)
)
  ENGINE = MyISAM
  AUTO_INCREMENT = 86
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of article_tag
-- ----------------------------
INSERT INTO article_tag VALUES (1, 'java', 1);
-- ----------------------------
-- Table structure for `columns`
-- ----------------------------
DROP TABLE IF EXISTS `columns`;
CREATE TABLE `columns` (
  `id`      INT(10)     NOT NULL AUTO_INCREMENT,
  `cn_name` VARCHAR(20) NOT NULL,
  `en_name` VARCHAR(20) NOT NULL,
  `slogans` VARCHAR(200)         DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = MyISAM
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of columns
-- ----------------------------
INSERT INTO `columns` VALUES ('1', '技能', 'skills', '技术改变世界，那就让世界看到你的影响力。');
INSERT INTO `columns` VALUES ('2', '心悟', 'sensibility', '路漫漫其修远兮 吾将上下而求索。');
INSERT INTO `columns` VALUES ('3', '转载', 'reprints', '他山之石，可以攻玉。');
INSERT INTO `columns` VALUES ('4', '未归类', 'other', NULL);
INSERT INTO `columns` VALUES ('5', '烹饪', 'cooking', '黑暗料理十八式菜谱精要(:з」∠)');
INSERT INTO `columns` VALUES ('6', '健身', 'exercise', '好羡慕喝凉水都会胖的人，我只是想增个肌~');
INSERT INTO `columns` VALUES ('7', '摄影', 'photography', NULL);
INSERT INTO `columns` VALUES ('8', '旅行', 'travel', '世界那么大，有空去看看！');
INSERT INTO `columns` VALUES ('9', '文档', 'doc', NULL);

-- ----------------------------
-- Table structure for `comments`
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id`          INT(10)      NOT NULL AUTO_INCREMENT,
  `article_id`  INT(10)      NOT NULL  COMMENT '文章id',
  `uri`  VARCHAR(200)      NOT NULL  COMMENT '文章uri',
  `reply_id`    INT(10)               DEFAULT NULL  COMMENT '回复的comment.id',
  `comment`     VARCHAR(500) NOT NULL DEFAULT '',
  `create_time` DATETIME     NOT NULL,
  `user_name`   VARCHAR(50)  NOT NULL,
  `email`       VARCHAR(50)           DEFAULT NULL,
  `is_delete`   VARCHAR(1)   NOT NULL DEFAULT 'N'  COMMENT '是否删除',
  `website`     VARCHAR(200)          DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = MyISAM
  AUTO_INCREMENT = 31
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO comments VALUES (1, 1, 'uri',NULL, 'comment', now(), 'test', '123@asda.com', 'N', NULL);
-- ----------------------------
-- Table structure for `config`
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `config_key`     VARCHAR(40)  NOT NULL,
  `config_value`   VARCHAR(200) NOT NULL DEFAULT '',
  `config_comment` VARCHAR(100)          DEFAULT NULL,
  PRIMARY KEY (`config_key`)
)
  ENGINE = MyISAM
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES ('RESET_SEARCH_INDEX', 'false', '重置索引标志');
INSERT INTO `config` VALUES ('RECOMMENDED_ARTICLE_IDS', '1', '推荐文章');

-- ----------------------------
-- Table structure for `meta_cl_blog_log`
-- ----------------------------
DROP TABLE IF EXISTS `meta_cl_blog_log`;
CREATE TABLE `meta_cl_blog_log` (
  `id`                INT(10)     NOT NULL AUTO_INCREMENT,
  `site`              VARCHAR(10) NOT NULL  COMMENT '站点',
  `article_id`        INT(10)     NOT NULL  COMMENT '文章id',
  `post_id`           VARCHAR(20) NOT NULL  COMMENT '对应站点文章id',
  `lastest_post_time` DATETIME    NOT NULL  COMMENT '最后推送时间',
  PRIMARY KEY (`id`)
)
  ENGINE = MyISAM
  AUTO_INCREMENT = 48
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of meta_cl_blog_log
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`         INT(10)     NOT NULL AUTO_INCREMENT,
  `user_name`  VARCHAR(50) NOT NULL,
  `password`   VARCHAR(50) NOT NULL,
  `nick_name`  VARCHAR(50) NOT NULL,
  `session_id` VARCHAR(50)          DEFAULT NULL  COMMENT '最后一次登陆时sessionid,用于自动登陆',
  `remember`  INT(1)  DEFAULT 0  ,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'test', '123456', 'test', 'fbe4ed4e-d084-4d45-ba3a-3c1cea744e211', '0');

-- ----------------------------
-- Table structure for `user_tracker`
-- ----------------------------
DROP TABLE IF EXISTS `user_tracker`;
CREATE TABLE `user_tracker` (
  `id`            INT(10)      NOT NULL AUTO_INCREMENT,
  `href`          VARCHAR(100) NOT NULL,
  `tuid`          VARCHAR(50)  NOT NULL  COMMENT '用户追踪标识',
  `referer`       VARCHAR(1000)         DEFAULT NULL,
  `create_time`   DATETIME     NOT NULL,
  `params_string` VARCHAR(1000)         DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = MyISAM
  AUTO_INCREMENT = 15025
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of user_tracker
-- ----------------------------
-- ----------------------------
-- Table structure for `visitor_info`
-- ----------------------------
DROP TABLE IF EXISTS `visitor_info`;
CREATE TABLE `visitor_info` (
  `id`          INT(10) NOT NULL AUTO_INCREMENT,
  `ip`          VARCHAR(200)     DEFAULT NULL,
  `user_agent`  VARCHAR(2000)    DEFAULT NULL,
  `access_time` DATETIME         DEFAULT NULL,
  `access_url`  VARCHAR(2000)    DEFAULT NULL,
  `tuid`        VARCHAR(50)      DEFAULT NULL  COMMENT '用户追踪标识',
  `server_name` VARCHAR(30)      DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = MyISAM
  AUTO_INCREMENT = 196484
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of visitor_info
-- ----------------------------
