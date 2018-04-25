-- auto-generated definition
CREATE TABLE platform_user_role
(
  id             BIGINT AUTO_INCREMENT
  COMMENT '默认索引 不可作用于业务'
    PRIMARY KEY,
  user_id        VARCHAR(32)                        NOT NULL
  COMMENT '用户唯一32位UUID',
  role_id        VARCHAR(32)                        NOT NULL
  COMMENT '角色唯一32位UUID',
  create_time    DATETIME DEFAULT CURRENT_TIMESTAMP NULL
  COMMENT '创建时间',
  create_user_id VARCHAR(32)                        NULL
  COMMENT '创建人',
  modify_time    DATETIME DEFAULT CURRENT_TIMESTAMP NULL
  COMMENT '修改时间',
  modify_user_id VARCHAR(32)                        NULL
  COMMENT '修改人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT '用户角色绑定表';
