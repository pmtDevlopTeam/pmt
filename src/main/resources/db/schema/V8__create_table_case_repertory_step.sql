-- auto-generated definition
CREATE TABLE case_repertory_step
(
  id          BIGINT(11)  NOT NULL
  COMMENT '主键'
    PRIMARY KEY,
  step        VARCHAR(50) NULL
  COMMENT '步骤名称',
  expect      VARCHAR(50) NULL
  COMMENT '预期',
  use_case_id BIGINT(11)  NULL
  COMMENT '用例'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;
