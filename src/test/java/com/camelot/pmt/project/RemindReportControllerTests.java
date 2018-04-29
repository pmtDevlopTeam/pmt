package com.camelot.pmt.project;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.camelot.pmt.PmtApplication;
import com.camelot.pmt.platform.login.Login;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PmtApplication.class)
public class RemindReportControllerTests {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired(required = false)
    private SessionsSecurityManager securityManager;

    @Before
    public void setup() throws Exception {
        SecurityUtils.setSecurityManager(securityManager);
        new Login().login("aaa", "aaa", null);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testAddRemindReport() throws Exception {

        String contentAsString = mockMvc
                .perform(post("/remind/addRemindReport").param("projectId", "1").param("projectRoleId", "r1")
                        .param("remindStatus", "01"))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        assertEquals("{\"data\":\"新增数据成功\",\"status\":{\"code\":200,\"message\":\"请求处理成功.\"}}", contentAsString);

    }
}
