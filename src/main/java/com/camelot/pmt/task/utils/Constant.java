package com.camelot.pmt.task.utils;

/**
 * 常量类
 */
public class Constant {
    /** 超级管理员ID */
    public static final int SUPER_ADMIN = 1;

    /** 任务状态 */
    public enum TaskStatus {
        /** 待办 */
        PENDINHG("0"),
        /** 正在进行 */
        RUNING("1"),
        /** 已办 */
        ALREADY("2"),
        /** 延期 */
        OVERDUE("3"),
        /** 关闭 */
        CLOSE("4"),
        /** 提测 */
        CHECKINTEST("5");
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
        TESTTASK("2");
        private String value;

        TaskType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
