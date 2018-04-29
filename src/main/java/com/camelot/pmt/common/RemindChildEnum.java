package com.camelot.pmt.common;

public enum RemindChildEnum {

    MODULE_PROGRESS(1, "模块进度", "http://localhost:8080/login?logincode=liyaohui&password=123456"),

    PROJECT_DELAY_REMINDING(2, "项目延期提醒", "http://localhost:8080/login?logincode=liyaohui&password=123456"),

    PROJECT_DELAYED_REMINDING(3, "项目延时提醒", "http://localhost:8080/login?logincode=liyaohui&password=123456"),

    MILEAGE_TASK_COMPLETION_REMINDING(4, "里程任务完成提醒", "http://localhost:8080/login?logincode=liyaohui&password=123456"),

    MILEAGE_TASK_DELAY_REMINDING(5, "里程任务延期提醒", "http://localhost:8080/login?logincode=liyaohui&password=123456"),

    MILEAGE_TASK_DELAYED_REMINDING(6, "里程任务延时提醒", "http://localhost:8080/login?logincode=liyaohui&password=123456"),

    LONG_TIME_DELAY_TASK_PROMP(7, "长时间延时任务提示", "http://localhost:8080/login?logincode=liyaohui&password=123456"),

    MODULE_DELAY_REMINDING(8, "模块延期提醒", "http://localhost:8080/login?logincode=liyaohui&password=123456"),

    MODULE_DELAYED_REMINDING(9, "模块延时提醒", "http://localhost:8080/login?logincode=liyaohui&password=123456"),

    TASK_DELAY_REMINDING(10, "任务延时提醒", "http://localhost:8080/login?logincode=liyaohui&password=123456"),

    TASK_DELAYED_REMINDING(11, "任务延期提醒", "http://localhost:8080/login?logincode=liyaohui&password=123456"),

    LONG_UNRESOLVED_BUG_REMINDER(12, "长时间未解决BUG提醒", "http://localhost:8080/login?logincode=liyaohui&password=123456"),

    PROJECT_PROGRESS(13, "项目进度", "http://localhost:8080/login?logincode=liyaohui&password=123456"),

    BUG_ON_THE_DAY(14, "当日BUG总数", "http://localhost:8080/login?logincode=liyaohui&password=123456"),

    BUG_SOLVE_DAY(15, "当日BUG解决数", "http://localhost:8080/login?logincode=liyaohui&password=123456"),

    TASK_COMPLETION_NUMBER(16, "任务完成数", "http://localhost:8080/login?logincode=liyaohui&password=123456"),;

    private Integer code;

    private String name;

    private String url;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    RemindChildEnum(Integer code, String name, String url) {
        this.code = code;
        this.name = name;
        this.url = url;

    }

}
