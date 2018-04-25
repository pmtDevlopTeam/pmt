-- auto-generated definition
CREATE TABLE remind_content_child
(
  id                BIGINT(11) AUTO_INCREMENT
COMMENT '主键'
PRIMARY KEY,
project_id        BIGINT(11)  NOT NULL
COMMENT '项目id',
content_id        BIGINT(11)  NOT NULL
COMMENT '内容表id',
name              VARCHAR(20) NOT NULL
COMMENT '编码名称',
code              VARCHAR(10) NOT NULL
COMMENT '编码',
method_url        VARCHAR(50) NOT NULL
COMMENT '方法url',
create_user_id    VARCHAR(32) NOT NULL
COMMENT '创建人id',
create_time       DATETIME    NOT NULL
COMMENT '创建时间',
delay_remind_days INT         NOT NULL
COMMENT '延期提醒天数',
remark            INT         NOT NULL
COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
