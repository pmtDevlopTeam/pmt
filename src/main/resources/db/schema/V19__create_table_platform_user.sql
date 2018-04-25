-- auto-generated definition
CREATE TABLE platform_user
(
  id             BIGINT AUTO_INCREMENT
  COMMENT '默认索引 不可作用于业务'
    PRIMARY KEY,
  user_id        VARCHAR(32)                        NOT NULL
  COMMENT '用户唯一32位UUID',
  login_code     VARCHAR(32)                        NOT NULL
  COMMENT '登陆账号 唯一',
  password       VARCHAR(255)                       NOT NULL
  COMMENT '登陆密码',
  user_name      VARCHAR(255)                       NOT NULL
  COMMENT '用户姓名',
  state          VARCHAR(255) DEFAULT '0'           NOT NULL
  COMMENT ' 用户状态 : 0（默认）启用 1 停用 2 锁定',
  create_time    DATETIME DEFAULT CURRENT_TIMESTAMP NULL
  COMMENT '创建时间',
  create_user_id VARCHAR(32)                        NULL
  COMMENT '创建人',
  modify_time    DATETIME DEFAULT CURRENT_TIMESTAMP NULL
  COMMENT '修改时间',
  modify_user_id VARCHAR(32)                        NULL
  COMMENT '修改人',
  CONSTRAINT AK_Key_login_code
  UNIQUE (login_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT '用户表';
