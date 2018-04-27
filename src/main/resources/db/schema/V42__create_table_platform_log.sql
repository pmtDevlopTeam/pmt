-- auto-generated definition
CREATE TABLE `platform_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '默认索引 不可作用于业务',
  `log_type` varchar(32) NOT NULL COMMENT '日志类型',
  `log_operation_type` varchar(32) NOT NULL COMMENT '日志操作类型',
  `log_operation_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '日志操作时间',
  `log_operation_user_id` varchar(32) DEFAULT NULL COMMENT '日志操作人',
  `log_content` text NOT NULL COMMENT '日志详细内容',
  `log_key_word` varchar(255) NOT NULL DEFAULT '0' COMMENT '日志关键字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='操作日志表';
