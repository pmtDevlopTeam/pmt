-- auto-generated definition
CREATE TABLE task_log
(
  id                 BIGINT(11) AUTO_INCREMENT
  COMMENT '任务日志标识号（主键）'
    PRIMARY KEY,
  task_id            BIGINT(11)   NULL
  COMMENT '任务ID',
  user_id            VARCHAR(32)  NULL
  COMMENT '操作人ID',
  operation_time     DATETIME     NULL
  COMMENT '操作时间',
  operation_button   VARCHAR(20)  NULL
  COMMENT '操作功能',
  operation_describe VARCHAR(500) NULL
  COMMENT '操作描述'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT '任务日志表';
