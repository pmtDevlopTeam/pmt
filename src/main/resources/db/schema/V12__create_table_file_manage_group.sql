-- auto-generated definition
CREATE TABLE file_manage_group
(
  id             BIGINT(11) AUTO_INCREMENT
  COMMENT '主键ID'
    PRIMARY KEY,
  parent_id      BIGINT(11)             NULL
  COMMENT '父级id',
  project_id     BIGINT(11)             NULL
  COMMENT '项目ID',
  name           VARCHAR(100)           NULL
  COMMENT '名称',
  fdescribe      VARCHAR(255)           NULL,
  create_user_id BIGINT(11)             NULL
  COMMENT '创建者',
  create_time    DATETIME               NULL
  COMMENT '创建时间',
  modify_user_id BIGINT(11)             NULL
  COMMENT '更新者',
  modify_time    DATETIME               NULL
  COMMENT '更新时间',
  del_flag       VARCHAR(1) DEFAULT '0' NULL
  COMMENT '删除标记',
  isfile         INT                    NULL
  COMMENT '是否文件（1是文件，0是文件夹）'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT '文档分类表';
