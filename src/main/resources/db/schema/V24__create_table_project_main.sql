-- auto-generated definition
CREATE TABLE project_main
(
  id             BIGINT(11) AUTO_INCREMENT
  COMMENT '主键'
    PRIMARY KEY,
  user_id        VARCHAR(32)          NOT NULL
  COMMENT '负责人id',
  project_num    CHAR(20)             NOT NULL
  COMMENT '编号',
  project_name   VARCHAR(64)          NOT NULL
  COMMENT '名称',
  project_desc   TEXT                 NOT NULL
  COMMENT '描述',
  project_status CHAR(2) DEFAULT '01' NOT NULL
  COMMENT '状态 01未开始 02进行中 03完成 04延期 05挂起 06关闭',
  start_time     DATETIME             NOT NULL
  COMMENT '起始时间（是立项时预计）',
  end_time       DATETIME             NOT NULL
  COMMENT '结束时间（是立项时预计）',
  create_user_id VARCHAR(32)          NOT NULL
  COMMENT '创建人id',
  create_time    DATETIME             NOT NULL
  COMMENT '创建时间',
  modify_user_id VARCHAR(32)          NOT NULL
  COMMENT '修改人id',
  modify_time    DATETIME             NOT NULL
  COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT '项目表';
