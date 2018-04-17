package com.camelot.pmt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description: 程序的总入口
 * @author LvP
 * @date 2018/3/26 16:28
 */
@MapperScan("com.camelot.pmt.**.mapper")
public class PmtApplication {
    public static void main(String[] args) {
        SpringApplication.run(PmtApplication.class, args);
    }
}
