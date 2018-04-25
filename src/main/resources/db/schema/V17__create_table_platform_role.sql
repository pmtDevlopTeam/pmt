-- auto-generated definition
CREATE TABLE platform_role
(
  id             BIGINT AUTO_INCREMENT
  COMMENT '默认索引 不可作用于业务'
    PRIMARY KEY,
  role_id        VARCHAR(32)                        NOT NULL
  COMMENT '角色唯一32位UUID',
  parent_id      VARCHAR(32) DEFAULT '0'            NOT NULL
  COMMENT '上级角色ID 默认 ‘0’  0级 为顶级角色',
  role_name      VARCHAR(32)                        NOT NULL
  COMMENT '角色名称 唯一',
  state          VARCHAR(8) DEFAULT '0'             NOT NULL
  COMMENT ' 角色状态：0（默认）激活 1 禁用 2 锁定',
  create_time    DATETIME DEFAULT CURRENT_TIMESTAMP NULL
  COMMENT '创建时间',
  create_user_id VARCHAR(32)                        NULL
  COMMENT '创建人',
  modify_time    DATETIME DEFAULT CURRENT_TIMESTAMP NULL
  COMMENT '修改时间',
  modify_user_id VARCHAR(32)                        NULL
  COMMENT '修改人',
  CONSTRAINT AK_Key_role_name
  UNIQUE (role_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT '角色表';
