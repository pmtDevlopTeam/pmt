-- auto-generated definition
CREATE TABLE platform_dict_item
(
  id              BIGINT AUTO_INCREMENT
  COMMENT '默认索引 不可作用于业务'
    PRIMARY KEY,
  dict_item_id    VARCHAR(32)                        NOT NULL
  COMMENT '字典项唯一32位UUID',
  dict_id         VARCHAR(32)                        NULL
  COMMENT '字典ID',
  dict_item_name  VARCHAR(32)                        NOT NULL
  COMMENT '字典项名称 唯一',
  dict_item_code  VARCHAR(32)                        NOT NULL
  COMMENT '字典项编码 唯一',
  dict_item_value VARCHAR(255)                       NOT NULL
  COMMENT '字典项值',
  state           VARCHAR(255) DEFAULT '0'           NOT NULL
  COMMENT '字典状态 0（默认）启用 1 停用',
  sort_num        INT DEFAULT '1000'                 NULL
  COMMENT '排序号 默认 1000',
  create_time     DATETIME DEFAULT CURRENT_TIMESTAMP NULL
  COMMENT '创建时间',
  create_user_id  VARCHAR(32)                        NULL
  COMMENT '创建人',
  modify_time     DATETIME DEFAULT CURRENT_TIMESTAMP NULL
  COMMENT '修改时间',
  modify_user_id  VARCHAR(32)                        NULL
  COMMENT '修改人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT '字典项表';
