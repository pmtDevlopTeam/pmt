-- auto-generated definition
CREATE TABLE bug_manage
(
  `id`             BIGINT(11) AUTO_INCREMENT
  COMMENT '主键ID'
    PRIMARY KEY,
  bug_no           VARCHAR(255)     NULL
  COMMENT 'bug编号',
  project_id       BIGINT(11)       NULL
  COMMENT '项目ID',
  demand_id        BIGINT(11)       NULL
  COMMENT '相关需求ID',
  task1_id         BIGINT(11)       NULL
  COMMENT '所属一级任务模块',
  task_id          BIGINT(11)       NULL
  COMMENT '相关任务ID',
  designated_id    VARCHAR(255)     NULL
  COMMENT '指派人(负责人)',
  version_id       VARCHAR(255)     NULL
  COMMENT '影响版本ID',
  bug_title        VARCHAR(255)     NULL
  COMMENT '标题',
  bug_type         VARCHAR(255)     NULL
  COMMENT 'bug类型',
  case_terminal    VARCHAR(25)      NULL
  COMMENT '测试终端',
  case_environment VARCHAR(25)      NULL
  COMMENT '测试环境',
  bug_level        VARCHAR(25)      NULL
  COMMENT 'bug级别（优先级）',
  bug_status       VARCHAR(25)      NULL
  COMMENT '状态（0未确认 1已确认 2已解决 3已关闭  4已撤销）',
  create_user_id   VARCHAR(255)     NULL
  COMMENT '创建者',
  create_time      DATETIME         NULL
  COMMENT '创建时间',
  end_time         DATETIME         NULL
  COMMENT '截止日期',
  modify_user_id   VARCHAR(255)     NULL
  COMMENT '更新者',
  modify_time      DATETIME         NULL
  COMMENT '更新时间',
  del_flag         CHAR DEFAULT '0' NULL
  COMMENT '删除标记',
  steps_reproduce  VARCHAR(255)     NULL
  COMMENT '重现步骤',
  solve_time       DATETIME         NULL
  COMMENT '解决时间',
  solve_program    INT              NULL
  COMMENT '解决方案',
  solve_id         VARCHAR(255)     NULL
  COMMENT '解决人',
  close_time       DATETIME         NULL
  COMMENT '关闭时间',
  close_id         VARCHAR(255)     NULL
  COMMENT '关闭人',
  serious_degree   VARCHAR(25)      NULL
  COMMENT '严重程度',
  close_reason     VARCHAR(255)     NULL
  COMMENT '关闭原因',
  case_id          BIGINT(11)       NULL
  COMMENT '用例ID',
  bug_describe     VARCHAR(255)     NULL
  COMMENT '备注',
  close_stauts     VARCHAR(11)      NULL
  COMMENT '关闭状态（正常，不正常）'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT 'bug管理';
