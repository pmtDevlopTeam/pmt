-- auto-generated definition
CREATE TABLE platform_dict
(
  id             BIGINT AUTO_INCREMENT
  COMMENT '默认索引 不可作用于业务'
    PRIMARY KEY,
  dict_id        VARCHAR(32)                        NOT NULL
  COMMENT '字典唯一32位UUID',
  dict_code      VARCHAR(32)                        NULL
  COMMENT '字典编码 唯一',
  dict_type      VARCHAR(32)                        NOT NULL
  COMMENT '字典类型',
  dict_name      VARCHAR(32)                        NOT NULL
  COMMENT '字典名称 唯一',
  state          VARCHAR(255) DEFAULT '0'           NOT NULL
  COMMENT ' 字典状态 0（默认）启用 1 停用',
  create_time    DATETIME DEFAULT CURRENT_TIMESTAMP NULL
  COMMENT '创建时间',
  create_user_id VARCHAR(32)                        NULL
  COMMENT '创建人',
  modify_time    DATETIME DEFAULT CURRENT_TIMESTAMP NULL
  COMMENT '修改时间',
  modify_user_id VARCHAR(32)                        NULL
  COMMENT '修改人',
  CONSTRAINT AK_Key_dict_code
  UNIQUE (dict_code),
  CONSTRAINT AK_Key_dict_name
  UNIQUE (dict_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT '字典表';
