-- auto-generated definition
CREATE TABLE platform_user_info
(
  id             BIGINT AUTO_INCREMENT
  COMMENT '默认索引 不可作用于业务'
    PRIMARY KEY,
  user_id        VARCHAR(32)                        NULL
  COMMENT '用户唯一32位UUID',
  user_job_num   VARCHAR(32)                        NULL
  COMMENT '用户工号 唯一',
  user_phone     VARCHAR(11)                        NULL
  COMMENT '用户手机号',
  user_mail      VARCHAR(32)                        NULL
  COMMENT '用户邮箱',
  create_time    DATETIME DEFAULT CURRENT_TIMESTAMP NULL
  COMMENT '创建时间',
  create_user_id VARCHAR(32)                        NULL
  COMMENT '创建人',
  modify_time    DATETIME DEFAULT CURRENT_TIMESTAMP NULL
  COMMENT '修改时间',
  modify_user_id VARCHAR(32)                        NULL
  COMMENT '修改人',
  CONSTRAINT AK_Key_user_job_num
  UNIQUE (user_job_num)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT '用户信息表';
