package com.camelot.pmt.task;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by daiyang on 2018/4/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class IntegrationTest {

//    HttpHeaders headers = new HttpHeaders();

    @Autowired
    protected TestRestTemplate restTemplate;

//    @Rule
//    // 这里注意，使用@Rule注解必须要用public
//    public OutputCapture capture = new OutputCapture();

//    public void setTokenToHeader() throws ParseException {
//        String contentType = "application/json;charset=UTF-8";
//        headers.add("Content-type", contentType);
//    }
}
