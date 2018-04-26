-- auto-generated definition
CREATE TABLE attachment
(
id BIGINT (11) NOT NULL COMMENT '主键ID',
--  id BIGINT(11)  NOT NULL
--  COMMENT '主键id' PRIMARY KEY,
attachment_url VARCHAR(50)NULL COMMENT '附件url',
attachment_tile VARCHAR(50)NULL COMMENT '附件名称',
attachment_source VARCHAR(50)NULL COMMENT '附件来源',
source_id BIGINT(11)NULL COMMENT '来源ID',
create_user_id BIGINT(11)NULL,
create_time DATETIME NULL,
modify_user_id BIGINT(11)NULL,
modify_time DATETIME NULL, PRIMARY KEY(id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '附件表';
