-- auto-generated definition
CREATE TABLE use_case_detail
(
  id             BIGINT DEFAULT '0' NOT NULL
    PRIMARY KEY,
  implement_id   BIGINT(11)         NULL
COMMENT '用例执行表ID',
procedure_name VARCHAR(255)       NULL
COMMENT '用例步骤表名称',
test_result    VARCHAR(25)        NULL
COMMENT '测试结果',
description    VARCHAR(255)       NULL
COMMENT '实际情况',
anticipate     VARCHAR(255)       NULL
COMMENT '预期',
version_id     VARCHAR(25)        NULL
COMMENT '版本号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;
