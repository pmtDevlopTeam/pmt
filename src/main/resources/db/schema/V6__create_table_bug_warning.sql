-- auto-generated definition
CREATE TABLE bug_warning
(
  id             BIGINT(11) AUTO_INCREMENT COMMENT '主键ID'
    PRIMARY KEY,
  project_id     BIGINT(11)         NULL
  COMMENT '项目ID',
  bug_min_num    INT(5)             NULL
  COMMENT 'bug最小值',
  bug_max_num    INT(5)             NULL
  COMMENT 'bug最大值',
  status         INT(1)             NULL
  COMMENT '是否启用(0未启用;1:已启用)',
  create_user_id VARCHAR(255)       NULL
  COMMENT '创建者',
  create_time    DATETIME           NULL
  COMMENT '创建时间',
  modify_user_id VARCHAR(255)       NULL
  COMMENT '更新者',
  modify_time    DATETIME           NULL
  COMMENT '更新时间',
  del_flag       INT(1) DEFAULT '0' NULL
  COMMENT '删除标记(0:正常；1：删除)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT 'bug预警';
