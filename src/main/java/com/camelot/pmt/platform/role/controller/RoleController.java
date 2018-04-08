package com.camelot.pmt.platform.role.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.APIStatus;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.common.PlatformR;
import com.camelot.pmt.platform.utils.ExecuteResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/platform/role")
public class RoleController {


    @GetMapping(value = "/queryRoleArray")
    public JSONObject queryRoleArray(){
        ExecuteResult result = new ExecuteResult();


        return ApiResponse.jsonData(APIStatus.OK_200,result.getResult());
    }
}
