package com.camelot.pmt.platform.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.camelot.pmt.platform.service.SchedulerTask;

@Service
public class SchedulerTaskImlp implements SchedulerTask {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 6000)
    public void reportCurrentTime() {
        System.out.println("模拟定时任务 现在时间：" + dateFormat.format(new Date()));
    }
}
