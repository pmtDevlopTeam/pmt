-- auto-generated definition
CREATE TABLE project_budget
(
  id              BIGINT(11) AUTO_INCREMENT
  COMMENT '预算id'
    PRIMARY KEY,
  project_id      BIGINT(11)   NOT NULL
  COMMENT '项目id',
  actual_hours    INT          NULL
  COMMENT '实际工时',
  budgetary_hours INT          NOT NULL
  COMMENT '预计工时',
  other           VARCHAR(255) NULL
  COMMENT '其它',
  create_user_id  VARCHAR(32)  NOT NULL
  COMMENT '创建人id',
  create_time     DATETIME     NOT NULL
  COMMENT '创建时间',
  modify_user_id  VARCHAR(32)  NOT NULL
  COMMENT '修改人id',
  modify_time     DATETIME     NOT NULL
  COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT '项目预算表';
