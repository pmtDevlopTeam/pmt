-- auto-generated definition
CREATE TABLE platform_menu
(
  id              BIGINT AUTO_INCREMENT
  COMMENT '默认索引 不可作用于业务'
    PRIMARY KEY,
  menu_id         VARCHAR(32)                        NOT NULL
  COMMENT '菜单唯一32位UUID',
  parent_id       VARCHAR(32) DEFAULT '0'            NOT NULL
  COMMENT '上级菜单ID 默认 ‘0’  0级为顶级角色',
  menu_name       VARCHAR(255)                       NOT NULL
  COMMENT '菜单名称',
  menu_type       VARCHAR(255)                       NOT NULL
  COMMENT '菜单类型 1 目录 2 菜单 3 按钮',
  menu_url        VARCHAR(255)                       NULL
  COMMENT '菜单请求地址 /xxx/xxx',
  menu_permission VARCHAR(255)                       NULL
  COMMENT '菜单授权 模块:功能:方法  platform:menu:createMenu',
  menu_icon       VARCHAR(255)                       NULL
  COMMENT '菜单图标',
  state           VARCHAR(255) DEFAULT '0'           NOT NULL
  COMMENT '菜单状态  0（默认）启用 1 停用 2 锁定',
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
  COMMENT '权限菜单表';
