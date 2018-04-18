-- auto-generated definition
CREATE TABLE task
(
  id                  BIGINT(11) AUTO_INCREMENT
  COMMENT '任务ID（主键）'
    PRIMARY KEY,
  task_num            VARCHAR(20)         NULL
  COMMENT '任务编号',
  task_name           VARCHAR(20)         NULL
  COMMENT '任务名称',
  task_parent_id      BIGINT(11)          NULL
  COMMENT '父级任务ID',
  project_id          BIGINT(11)          NULL
  COMMENT '项目ID',
  demand_id           BIGINT(11)          NULL
  COMMENT '需求ID',
  priority            CHAR(2)             NULL
  COMMENT '优先级',
  assign_user_id      VARCHAR(32)         NULL
  COMMENT '指派人ID',
  beassign_user_id    VARCHAR(32)         NULL
  COMMENT '负责人ID',
  assign_time         DATETIME            NULL
  COMMENT '任务指派时间',
  estimate_start_time DATETIME            NULL
  COMMENT '任务预计开始时间',
  estimate_end_time   DATETIME            NULL
  COMMENT '任务预计结束时间',
  actual_start_time   DATETIME            NULL
  COMMENT '任务实际开始时间',
  actual_end_time     DATETIME            NULL
  COMMENT '任务实际结束时间',
  task_type           CHAR(2)             NULL
  COMMENT '任务类型',
  task_speed          VARCHAR(20)         NULL
  COMMENT '任务进度',
  status              CHAR(2) DEFAULT '0' NULL
  COMMENT '任务状态（0 未开始 1 正在进行 2 已完成 3 延期 4 关闭）',
  node_lv             CHAR(2)             NULL
  COMMENT '节点等级（1，2，3，4）',
  task_describe       VARCHAR(255)        NULL
  COMMENT '任务描述',
  demand_change       CHAR(2)             NULL
  COMMENT '需求是否变更',
  delay_describe      VARCHAR(255)        NULL
  COMMENT '延期原因',
  estimate_hour       BIGINT(11)          NULL
  COMMENT '任务预计工时',
  infact_hour         BIGINT(11)          NULL
  COMMENT '任务实际工时',
  task_mileage        CHAR(2)             NULL
  COMMENT '任务里程',
  create_user_id      VARCHAR(32)         NULL
  COMMENT '创建人ID',
  modify_user_id      VARCHAR(32)         NULL
  COMMENT '修改人ID',
  create_time         DATETIME            NULL
  COMMENT '创建时间',
  modify_time         DATETIME            NULL
  COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT '任务';
