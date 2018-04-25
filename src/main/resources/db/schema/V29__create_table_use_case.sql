-- auto-generated definition
CREATE TABLE use_case
(
  id              BIGINT(11) AUTO_INCREMENT
  COMMENT '主键ID'
    PRIMARY KEY,
  project_id      BIGINT(11)              NULL
  COMMENT '相关项目ID',
  demand_id       BIGINT(11)              NULL
  COMMENT '相关需求ID',
  case_title      VARCHAR(255)            NULL
  COMMENT '用例标题(名称)',
  case_type       VARCHAR(11)             NULL
  COMMENT '用例类型(字典表ID)',
  version_id      VARCHAR(50)             NULL
  COMMENT '相关版本',
  case_level      VARCHAR(11)             NULL
  COMMENT '优先级',
  description     VARCHAR(255)            NULL
  COMMENT '用例描述',
  execute_user_id VARCHAR(50)             NULL
  COMMENT '执行人ID',
  execute_time    DATETIME                NULL
  COMMENT '最后执行时间',
  case_status     VARCHAR(50) DEFAULT '0' NULL
  COMMENT '用例状态(0:正常；1:被阻塞；2：研究中)',
  result          VARCHAR(50)             NULL
  COMMENT '结果(0:通过；1：失败)',
  create_user_id  VARCHAR(50)             NULL
  COMMENT '创建者',
  create_time     DATETIME                NULL
  COMMENT '创建时间',
  modify_user_id  VARCHAR(50)             NULL
  COMMENT '更新者',
  modify_time     DATETIME                NULL
  COMMENT '更新时间',
  del_flag        VARCHAR(50) DEFAULT '0' NULL
  COMMENT '删除标记(0:正常；1：删除)',
  apply_phase     VARCHAR(50)             NULL
  COMMENT '适用阶段',
  precondition    VARCHAR(50)             NULL
  COMMENT '前置条件'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT '用例表';
