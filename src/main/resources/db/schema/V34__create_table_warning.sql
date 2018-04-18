-- auto-generated definition
CREATE TABLE warning
(
  id             BIGINT(11) AUTO_INCREMENT
  COMMENT '预警id'
    PRIMARY KEY,
  warn_type      VARCHAR(20) NULL
  COMMENT '预警类型',
  pro_id         BIGINT(11)  NULL
  COMMENT '项目id',
  warn_time      DATETIME    NULL
  COMMENT '自定义预警时间（项目和任务用到）',
  min_num        INT(8)      NULL
  COMMENT 'bug最小条数预警',
  max_num        INT(8)      NULL
  COMMENT 'bug最大条数预警',
  warn_status    CHAR(2)     NULL
  COMMENT '预警状态',
  create_user_id VARCHAR(32) NOT NULL
  COMMENT '创建人id',
  create_time    DATETIME    NOT NULL
  COMMENT '创建时间',
  modify_user_id VARCHAR(32) NOT NULL
  COMMENT '修改人id',
  modify_time    DATETIME    NOT NULL
  COMMENT '修改时间',
  task_id        BIGINT(11)  NULL
  COMMENT '任务id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT '包含：项目预警，任务预警，bug异常预警';
