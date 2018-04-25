-- auto-generated definition
CREATE TABLE demand
(
  id                BIGINT(11) AUTO_INCREMENT
  COMMENT '需求id'
    PRIMARY KEY,
  pid               BIGINT(11) UNSIGNED ZEROFILL DEFAULT '00000000000' NULL
  COMMENT '所属一级需求id',
  project_id        BIGINT(11)                                         NOT NULL
  COMMENT '项目id',
  demand_name       VARCHAR(64)                                        NOT NULL
  COMMENT '需求名称',
  demand_num        VARCHAR(8)                                         NOT NULL
  COMMENT '需求编号',
  demand_status     CHAR(2) DEFAULT '01'                               NOT NULL
  COMMENT '新建默认01：待评审,02：通过,03：不通过，04：冻结',
  demand_level      CHAR(2)                                            NOT NULL
  COMMENT '优先级',
  demand_source     VARCHAR(20)                                        NOT NULL
  COMMENT '需求来源',
  source_remark     VARCHAR(20)                                        NOT NULL
  COMMENT '需求来源备注',
  demand_desc       TEXT                                               NULL
  COMMENT '需求描述',
  close_reason      TEXT                                               NULL
  COMMENT '关闭原因',
  create_user_id    VARCHAR(32)                                        NULL
  COMMENT '创建人id',
  create_time       DATETIME                                           NULL
  COMMENT '创建时间',
  modify_user_id    VARCHAR(32)                                        NULL
  COMMENT '修改人id',
  modify_time       DATETIME                                           NULL
  COMMENT '修改时间',
  review_time       DATETIME                                           NULL
  COMMENT '评审时间',
  review_results    CHAR(8)                                            NULL
  COMMENT '评审结果',
  assigned_to       VARCHAR(32)                                        NULL
  COMMENT '指派给',
  reviewed_with     VARCHAR(255)                                       NULL
  COMMENT '由谁评审',
  review_remark     TEXT                                               NULL
  COMMENT '备注',
  reasons_rejection TEXT                                               NULL
  COMMENT '拒绝原因',
  demand_need       CHAR(8) DEFAULT '1'                                NULL
  COMMENT '需求层级'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
  COMMENT '需求表';
