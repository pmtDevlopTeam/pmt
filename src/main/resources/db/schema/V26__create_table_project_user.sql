-- auto-generated definition
CREATE TABLE project_user
(
  id             BIGINT(11) AUTO_INCREMENT
  COMMENT 'id'
    PRIMARY KEY,
  project_id     BIGINT(11)  NOT NULL
  COMMENT '项目id',
  user_id        VARCHAR(32) NOT NULL
  COMMENT '用户id',
  pre_join_time  DATETIME    NOT NULL
  COMMENT '预计进项目日期',
  real_join_time DATETIME    NULL
  COMMENT '实际进项目日期',
  pre_out_time   DATETIME    NOT NULL
  COMMENT '预计出项目日期',
  real_out_time  DATETIME    NULL
  COMMENT '实际出项目日期',
  pre_man_hour   INT         NOT NULL
  COMMENT '预计工时',
  user_status    CHAR(2)     NOT NULL
  COMMENT '成员在项目状态  01进入，02未进入，03已出 04 暂离',
  user_pro_role  VARCHAR(32) NOT NULL
  COMMENT '成员在项目角色id',
  create_user_id VARCHAR(32) NOT NULL
  COMMENT '创建人id',
  create_time    DATETIME    NOT NULL
  COMMENT '创建时间',
  modify_user_id VARCHAR(32) NOT NULL
  COMMENT '修改人id',
  modify_time    DATETIME    NOT NULL
  COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT '项目成员表';
