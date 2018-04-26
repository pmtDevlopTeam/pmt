-- auto-generated definition
CREATE TABLE bug_warning
(
  id          BIGINT(11) AUTO_INCREMENT
PRIMARY KEY,
role_id     VARCHAR(32) NULL,
project_id  BIGINT(11)  NULL
COMMENT '项目id',
create_time DATE        NULL,
num         INT         NULL
COMMENT '超过天数 预警'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT 'bug预警';
