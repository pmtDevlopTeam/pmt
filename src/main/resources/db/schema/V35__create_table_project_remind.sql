-- auto-generated definition
CREATE TABLE project_remind
(
  id               BIGINT(11) AUTO_INCREMENT
COMMENT '主键'
PRIMARY KEY,
project_id       BIGINT(11)  NOT NULL
COMMENT '项目id',
remind_type      CHAR(2)     NOT NULL
COMMENT '提醒类型',
remind_status    CHAR(2)     NOT NULL
COMMENT '提醒状态',
remind_frequency CHAR(2)     NOT NULL
COMMENT '提醒频次',
project_role_id  VARCHAR(32) NOT NULL
COMMENT '成员在项目角色',
create_user_id   VARCHAR(32) NOT NULL
COMMENT '创建人id',
create_time      DATETIME    NOT NULL
COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
