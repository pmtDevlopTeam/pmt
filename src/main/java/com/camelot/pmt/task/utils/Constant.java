package com.camelot.pmt.task.utils;

/**
 * 常量类
 */
public class Constant {
    /** 超级管理员ID */
    public static final int SUPER_ADMIN = 1;
    /**文件上传路径,确认文件服务器具体地址后再修改*/
    public static final String localPath = "D:/file/upload" + "\\";
    /** 任务状态  */
    public enum TaskStatus {
        /**待办*/
        PENDINHG("0"),
        /**正在进行*/
        RUNING("1"),
        /**已办 */
        ALREADY("2"),
        /**延期*/
    	OVERDUE("3"),
    	/**关闭*/
    	CLOSE("4");
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
    	/**需求任务*/
    	DEMANDTASK("0"),
    	/**开发任务*/
    	DEVELOPMENTTASK("1"),
    	/**测试任务*/
    	TESTTASK("2");
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
    	/**任务类型、需求类型等等*/
    	TASK("TASK"),
    	DEMAND("DEMAND");
    	private String value;
    	AttachmentSource(String value) {
            this.value = value;
        }
		public String getValue() {
			return value;
		}
    }

}
