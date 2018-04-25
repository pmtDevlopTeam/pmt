-- auto-generated definition
CREATE TABLE use_case_history
(
  id                 BIGINT(11) AUTO_INCREMENT
COMMENT 'ID'
PRIMARY KEY,
createTime         DATE         NULL
COMMENT '创建时间',
operation_id       VARCHAR(255) NULL
COMMENT '创建人id',
operation_function VARCHAR(255) NULL
COMMENT '操作名称',
use_case_id        BIGINT       NULL
COMMENT '用例id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;
