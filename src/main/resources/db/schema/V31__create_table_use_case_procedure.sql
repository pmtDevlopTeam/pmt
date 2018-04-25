-- auto-generated definition
CREATE TABLE use_case_procedure
(
  id          BIGINT(11) AUTO_INCREMENT
  COMMENT '主键ID'
    PRIMARY KEY,
  step        VARCHAR(255) NULL
  COMMENT '步骤',
  expect      VARCHAR(255) NULL
  COMMENT '预期',
  num         VARCHAR(50)  NULL
  COMMENT '编号',
  use_case_id BIGINT(11)   NULL
  COMMENT '用例id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT '用例步骤表';
