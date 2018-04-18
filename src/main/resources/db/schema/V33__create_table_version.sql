-- auto-generated definition
CREATE TABLE version
(
  id             BIGINT(11) AUTO_INCREMENT
  COMMENT '版本号_id'
    PRIMARY KEY,
  project_id     BIGINT(11)         NOT NULL
  COMMENT '项目id',
  version        VARCHAR(9)         NOT NULL
  COMMENT '版本编号',
  start_time     DATETIME           NOT NULL
  COMMENT '开始时间',
  end_time       DATETIME           NOT NULL
  COMMENT '结束时间',
  version_name   VARCHAR(11)        NOT NULL
  COMMENT '版本名称',
  version_type   CHAR(6)            NOT NULL
  COMMENT '版本类型',
  create_user_id VARCHAR(32)        NOT NULL
  COMMENT '创建人id',
  create_time    DATETIME           NOT NULL
  COMMENT '创建时间',
  modify_user_id VARCHAR(32)        NOT NULL
  COMMENT '修改人id',
  modify_time    DATETIME           NOT NULL
  COMMENT '修改时间',
  remarks        TEXT               NULL
  COMMENT '备注',
  version_status INT(1) DEFAULT '0' NOT NULL
  COMMENT '版本状态（0：在使用；-1：已删除）'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT '版本表';
