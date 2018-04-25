-- auto-generated definition
CREATE TABLE bug_history
(
  id                 BIGINT(11) AUTO_INCREMENT
  COMMENT '主键'
    PRIMARY KEY,
  num                VARCHAR(50)  NOT NULL
  COMMENT '编号',
  bug_id             BIGINT(11)   NOT NULL
  COMMENT '关联bug的ID',
  operation_time     DATETIME     NULL
  COMMENT '操作时间',
  operation_id       BIGINT(11)   NULL
  COMMENT '操作人',
  operation_function VARCHAR(50)  NULL
  COMMENT '操作功能',
  operation_record   VARCHAR(255) NULL
  COMMENT '操作详情'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT 'Bug历史记录表';
