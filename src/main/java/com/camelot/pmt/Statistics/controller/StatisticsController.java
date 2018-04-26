package com.camelot.pmt.Statistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.Statistics.model.StatisticsUserCase;
import com.camelot.pmt.Statistics.service.StatisticsService;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.filemanage.model.FileManage;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(value = "用例统计接口", description = "用例统计接口")
@RequestMapping("/statistics")
public class StatisticsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StatisticsService statisticsService;
    @ApiOperation(value = "用例覆盖率统计", notes = "用例覆盖率统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/UserCase",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject statisticsUserCase(Long projectId){
        try {
            List<StatisticsUserCase> count=statisticsService.statisticsUserCase(projectId);//用例覆盖率统计
            return ApiResponse.success(count);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }

    }
    @ApiOperation(value = "根据系统版本统计", notes = "根据系统版本统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value="/VersionUserCase",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject statisticsVersionUserCase(Long projectId){
        try {
            List<StatisticsUserCase> list= statisticsService.statisticsVersionUserCase(projectId);//根据系统版本统计
            return ApiResponse.success(list);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }
    @ApiOperation(value = "根据用例类型统计", notes = "根据用例类型统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/UserCaseByType",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject statisticsUserCaseByType(Long projectId){
        try {
            List<StatisticsUserCase> list=statisticsService.statisticsUserCaseByType(projectId);//根据用例类型统计
            return ApiResponse.success(list);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

}
