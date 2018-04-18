-- auto-generated definition
CREATE TABLE demand_operate
(
  id             BIGINT(11) AUTO_INCREMENT
  COMMENT '主键'
    PRIMARY KEY,
  demand_id      BIGINT(11)  NOT NULL
  COMMENT '需求id',
  operate_desc   TEXT        NOT NULL
  COMMENT '操作描述',
  create_user_id VARCHAR(32) NOT NULL
  COMMENT '创建人id',
  create_time    DATETIME    NOT NULL
  COMMENT '创建时间',
  run_type       CHAR(8)     NULL
  COMMENT '操作类型(01:评审操作记录）'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT '任务操作记录表';
