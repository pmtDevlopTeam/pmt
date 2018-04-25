package com.camelot.pmt.task.utils;

/**
 * 常量类
 */
public class Constant {
    /** 超级管理员ID */
    public static final int SUPER_ADMIN = 1;
    /** 文件上传路径,确认文件服务器具体地址后再修改 */
    public static final String localPath = "D:/file/upload" + "\\";

    /** 任务状态 */
    public enum TaskStatus {
        /** 待办 */
        PENDINHG("0"),
        /** 正在进行 */
        RUNING("1"),
        /** 已办 */
        ALREADY("2"),
        /** 关闭 */
        CLOSE("3");
        private String value;

        TaskStatus(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /** 任务类型 */
    public enum TaskType {
        /** 需求任务 */
        DEMANDTASK("0"),
        /** 开发任务 */
        DEVELOPMENTTASK("1"),
        /** 测试任务 */
        TESTTASK("2"),
        /** 设计任务 */
        DESIGN("3");
        private String value;

        TaskType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /** 文件数据源类型 */
    public enum AttachmentSource {
        /** 任务类型、需求类型等等 */
        TASK("TASK"), DEMAND("DEMAND");
        private String value;

        AttachmentSource(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /** 任务日志按钮类型 */
    public enum TaskLogOperationButton {
        /** 新建任务 */
        CREATETASK("新建"),
        /** 评估任务 */
        EVALUATION("评估"),
        /** 开始任务 */
        UPDATETASK("修改"),
        /** 修改任务 */
        DELETETASK("删除"),
        /** 开始任务 */
        STARTTASK("开始"),
        /** 完成任务 */
        COMPLETETASK("完成"),
        /** 关闭任务 */
        CLOSETASK("关闭"),
        /** 提测任务 */
        MEASUREMENT("提测"),
        /** 重做任务 */
        REDOTASK("重做");
        private String value;

        TaskLogOperationButton(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
