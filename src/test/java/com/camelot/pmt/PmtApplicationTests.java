package com.camelot.pmt;

import java.util.UUID;

import com.camelot.pmt.platform.model.Menu;
import com.camelot.pmt.platform.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.camelot.pmt.platform.*.mapper")
public class PmtApplicationTests {

    @Autowired
    MenuService menuService;



}
