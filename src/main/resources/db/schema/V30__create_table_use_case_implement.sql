-- auto-generated definition
CREATE TABLE use_case_implement
(
  id               BIGINT(11) AUTO_INCREMENT
  COMMENT '主键ID'
    PRIMARY KEY,
  use_case_id      BIGINT(11)  NULL
  COMMENT '用例ID',
  implement_result VARCHAR(25) NULL
  COMMENT '结果',
  execute_time     DATETIME    NULL
  COMMENT '执行时间',
  execute_user_id  VARCHAR(50) NULL
  COMMENT '执行ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT '用例执行表';
