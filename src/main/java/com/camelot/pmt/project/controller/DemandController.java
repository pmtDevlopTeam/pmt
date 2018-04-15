package com.camelot.pmt.project.controller;



import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.APIStatus;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.utils.DataGrid;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.platform.utils.Pager;
import com.camelot.pmt.project.model.DemandWithBLOBs;
import com.camelot.pmt.project.service.DemandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *@Author:fjy
 *@Description: 需求控制器类
 *@Date:2018/4/12_16:11
 */
@RestController
@Api(value = "项目管理-需求模块", description = "项目管理-需求模块的控制器类")
@EnableSwagger2
public class DemandController {

    private static final Logger logger=LoggerFactory.getLogger(DemandController.class);
    @Resource
    DemandService demandService;

    @ApiOperation(value = "查询需求列表", notes = "查询需求列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "demandNum", value = "需求编号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "demandLevel", value = "需求优先级", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "demandSource", value = "需求来源", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "projectId", value = "所属项目", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "reviewedWith", value = "由谁评审", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "显示行数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "demandName", value = "需求名称", required = false, paramType = "query", dataType = "String") })
    @RequestMapping(value = "demand/queryDemandAll", method = RequestMethod.POST)
    public JSONObject queryDemandAll(@ApiIgnore DemandWithBLOBs demandWithBLOBs,@ApiIgnore Pager pager){
        ExecuteResult<DataGrid<DemandWithBLOBs>> result = new ExecuteResult<DataGrid<DemandWithBLOBs>>();
        try{
            if(pager==null){
                pager.setPage(1);
                pager.setRows(10);
            }
            result = demandService.findAllByPage(pager, demandWithBLOBs);
            if(result.isSuccess()){
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponse.error();
        }

    }

    @ApiOperation(value = "新增需求", notes = "新增需求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需求id", required = false, paramType = "form", dataType = "Long"),
            @ApiImplicitParam(name = "pid", value = "所属一级需求id", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "projectId", value = "项目id", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "demandName", value = "需求名称", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "demandNum", value = "需求编号", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "demandStatus", value = "状态", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "demandSource", value = "需求来源", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "demandLevel", value = "优先级", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "sourceRemark", value = "需求来源备注", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "demandDesc", value = "需求描述", required = false, paramType = "form", dataType = "String") })
    @RequestMapping(value = "demand/insertDemand", method = RequestMethod.POST)
    public JSONObject insertDemand(DemandWithBLOBs demandWithBLOBs) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            //非空判断
            if (demandWithBLOBs == null) {
                return ApiResponse.error("");
            }
            Date currentDate = new Date();
            demandWithBLOBs.setCreateTime(currentDate);
            demandWithBLOBs.setModifyUserId(demandWithBLOBs.getCreateUserId());
            demandWithBLOBs.setModifyTime(currentDate);
            result = demandService.save(demandWithBLOBs);
            return ApiResponse.jsonData(APIStatus.OK_200, result);
        } catch (Exception e) {
            return ApiResponse.error();
        }
    }

    @ApiOperation(value = "需求查看", notes = "根据需求Id查看指定需求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "所属一级需求id", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "id", value = "需求id", required = true, paramType = "form", dataType = "Long") })
    @RequestMapping(value = "demand/findById", method = RequestMethod.GET)
    public JSONObject findById(Long id){
        ExecuteResult<DemandWithBLOBs> result=new ExecuteResult<>();
            try{
                result=demandService.findById(id);
                return ApiResponse.jsonData(APIStatus.OK_200, result);
            }catch(Exception e){
                logger.error("-------指定id查询需求-------"+e.getMessage());
                return ApiResponse.error();
            }
    }

    @ApiOperation(value = "编辑", notes = "需求编辑")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需求id", required = true, paramType = "form", dataType = "Long") })
    @RequestMapping(value = "demand/updateById", method = RequestMethod.GET)
    public JSONObject updateById(Long id){
        ExecuteResult<DemandWithBLOBs> result=new ExecuteResult<>();
        try{
            result=demandService.findById(id);
            return ApiResponse.jsonData(APIStatus.OK_200, result);
        }catch(Exception e){
            logger.error("-------指定id查询需求-------"+e.getMessage());
            return ApiResponse.error();
        }
    }

    @ApiOperation(value = "删除需求", notes = "根据id删除需求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需求id", required = true, paramType = "form", dataType = "Long") })
    @RequestMapping(value = "demand/deleteById", method = RequestMethod.GET)
    public JSONObject deleteById(Long id){
        ExecuteResult<String> result=new ExecuteResult<>();
        try{
            result=demandService.deleteById(id);
            return ApiResponse.jsonData(APIStatus.OK_200);
        }catch(Exception e){
            logger.error("------需求控制器删除指定需求------"+e.getMessage());
            return ApiResponse.error();
        }
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }



}
