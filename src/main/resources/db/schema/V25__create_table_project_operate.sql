-- auto-generated definition
CREATE TABLE project_operate
(
  id             INT AUTO_INCREMENT
  COMMENT 'id'
    PRIMARY KEY,
  project_id     BIGINT(11)  NOT NULL
  COMMENT '项目id',
  operate_desc   TEXT        NOT NULL
  COMMENT '操作描述',
  create_user_id VARCHAR(32) NOT NULL
  COMMENT '创建人id',
  create_time    DATETIME    NOT NULL
  COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT '项目操作记录表';
