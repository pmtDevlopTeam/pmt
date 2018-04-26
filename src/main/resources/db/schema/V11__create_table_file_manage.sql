-- auto-generated definition
CREATE TABLE file_manage
(
  id             BIGINT(11) AUTO_INCREMENT
  COMMENT '主键ID'
    PRIMARY KEY,
  group_id       BIGINT(11)             NULL
  COMMENT '文件id',
  file_title     VARCHAR(100)           NOT NULL
  COMMENT '文件标题',
  file_address   VARCHAR(255)           NULL
  COMMENT 'svn/git等地址',
  create_user_id BIGINT(11)             NULL
  COMMENT '创建者',
  create_time    DATETIME               NULL
  COMMENT '创建时间',
  modify_user_id BIGINT(11)             NULL
  COMMENT '更新者',
  modify_time    DATETIME               NULL
  COMMENT '更新时间',
  del_flag       VARCHAR(1) DEFAULT '0' NULL
  COMMENT '删除标记'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT '文档表';
