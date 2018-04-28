-- auto-generated definition
CREATE TABLE platform_org
(
  id             BIGINT AUTO_INCREMENT
  COMMENT '默认索引 不可作用于业务'
    PRIMARY KEY,
  org_id         VARCHAR(32)                        NOT NULL
  COMMENT '部门唯一32位UUID',
  parent_id      VARCHAR(32) DEFAULT '0'            NOT NULL
  COMMENT '上级部门ID 默认 ‘0’  0为顶级角色',
  org_code       VARCHAR(32)                        NULL
  COMMENT '部门编号(唯一)',
  org_name       VARCHAR(32)                        NOT NULL
  COMMENT '部门名称',
  org_leader   varchar(32) DEFAULT ''             
  COMMENT '部门负责人',
  state          VARCHAR(8) DEFAULT '0'             NOT NULL
  COMMENT '部门状态  0（默认）启用 1 停用 2 锁定',
  sort_num       INT DEFAULT '1000'                 NULL
  COMMENT '排序号 默认 1000',
  create_time    DATETIME DEFAULT CURRENT_TIMESTAMP NULL
  COMMENT '创建时间',
  create_user_id VARCHAR(32)                        NULL
  COMMENT '创建人',
  modify_time    DATETIME DEFAULT CURRENT_TIMESTAMP NULL
  COMMENT '修改时间',
  modify_user_id VARCHAR(32)                        NULL
  COMMENT '修改人',
  CONSTRAINT AK_Key_org_code
  UNIQUE (org_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT '部门表';
