-- auto-generated definition
CREATE TABLE remind_content
(
  id             BIGINT(11) AUTO_INCREMENT
COMMENT '主键'
PRIMARY KEY,
project_id     BIGINT(11)  NOT NULL
COMMENT '项目id',
remind_id      BIGINT(11)  NOT NULL
COMMENT '主表id',
content_code   CHAR(2)     NOT NULL
COMMENT '内容编号',
create_user_id VARCHAR(32) NOT NULL
COMMENT '创建人id',
create_time    DATETIME    NOT NULL
COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
