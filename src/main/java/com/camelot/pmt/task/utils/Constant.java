package com.camelot.pmt.task.utils;

/**
 * 常量类
 */
public class Constant {
    /** 超级管理员ID */
    public static final int SUPER_ADMIN = 1;

    /** 任务类型demo */
    public enum TaskType {
        /** 目录 */
        CATALOG(0),
        /** 菜单 */
        MENU(1),
        /** 按钮 */
        BUTTON(2);

        private int value;

        private TaskType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
