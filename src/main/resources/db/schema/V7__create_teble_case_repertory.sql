-- auto-generated definition
CREATE TABLE case_repertory
(
  id           BIGINT(11)  NOT NULL
  COMMENT '主键'
    PRIMARY KEY,
  case_title   VARCHAR(50) NULL
  COMMENT '用例名称',
  case_type    VARCHAR(50) NULL
  COMMENT '用例类型',
  description  VARCHAR(50) NULL
  COMMENT '用例描述',
  apply_phase  VARCHAR(50) NULL
  COMMENT '适用阶段',
  num          VARCHAR(50) NULL
  COMMENT '用例编号',
  precondition VARCHAR(50) NULL
  COMMENT '前置条件'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;
