package com.camelot.pmt.project.controller;



import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.project.model.Demand;
import com.camelot.pmt.project.model.DemandWithBLOBs;
import com.camelot.pmt.project.service.DemandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *@Author:fjy
 *@Description: 需求控制器类
 *@Date:2018/4/12_16:11
 */
@RestController
@Api(value = "项目管理-需求模块", description = "项目管理-需求模块的控制器类")
public class DemandController {

    @Resource
    DemandService demandService;

    //@RequiresPermissions
    @ApiOperation(value = "新增需求", notes = "新增需求")
    @RequestMapping(value = "demand/insertDemand", method = RequestMethod.POST)
    public JSONObject insertDemand(DemandWithBLOBs demandWithBLOBs){
        try{
            //非空判断
            if(demandWithBLOBs==null){
                return ApiResponse.errorPara();
            }
            demandService.save(demandWithBLOBs);
        }catch (Exception e){

        }

        return null;
    }





}
