-- auto-generated definition
CREATE TABLE remind_report
(
  id               BIGINT(11) AUTO_INCREMENT
COMMENT '主键'
PRIMARY KEY,
project_id       BIGINT(11)  NOT NULL
COMMENT '项目id',
daily_content    TEXT        NOT NULL
COMMENT '日报内容',
remind_type      CHAR(2)     NOT NULL
COMMENT '提醒类型',
remind_frequency CHAR(2)     NOT NULL
COMMENT '提醒频次',
create_user_id   VARCHAR(32) NOT NULL
COMMENT '创建人id',
create_time      DATETIME    NOT NULL
COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
COMMENT '提醒日志表';
