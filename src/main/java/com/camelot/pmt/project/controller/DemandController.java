package com.camelot.pmt.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.DataGrid;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.common.Pager;
import com.camelot.pmt.project.model.Demand;
import com.camelot.pmt.project.model.DemandOperate;
import com.camelot.pmt.project.service.DemandService;

/**
 * @Author:fjy
 * @Description: 需求控制器类
 * @Date:2018/4/12_16:11
 */
@RestController
@Api(value = "项目管理-需求模块", description = "项目管理-需求模块的控制器类")
public class DemandController {

    private static final Logger logger = LoggerFactory.getLogger(DemandController.class);
    @Resource
    DemandService demandService;

    /**
     * 需求状态（未激活/已激活/已关闭/已变更）
     *
     * @param demandWithBLOBs
     * @return
     */
    @ApiOperation(value = "新增需求", notes = "新增需求")
    @PostMapping(value = "/api/demand/insertDemand")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "所属一级需求id", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "demandName", value = "需求名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "demandNum", value = "需求编号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "demandSource", value = "需求来源", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "demandLevel", value = "优先级", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sourceRemark", value = "需求来源备注", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "createUserId", value = "创建人", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "assignedTo", value = "指派给", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "reviewedWith", value = "由谁评审(人员user_id用逗号拼接)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "demandDesc", value = "需求描述", required = false, paramType = "query", dataType = "String") })
    public JSONObject insertDemand(@ApiIgnore Demand demandWithBLOBs) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            Date currentDate = new Date();
            demandWithBLOBs.setCreateTime(currentDate);
            demandWithBLOBs.setModifyUserId(demandWithBLOBs.getCreateUserId());
            demandWithBLOBs.setModifyTime(currentDate);
            // 设置新增需求状态01:未开始
            demandWithBLOBs.setDemandStatus("01");
            result = demandService.save(demandWithBLOBs);
            return ApiResponse.success(result.getResult());
        } catch (Exception e) {
            logger.error("------需求新增------" + e.getMessage());
            return ApiResponse.error();
        }
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @ApiOperation(value = "删除需求", notes = "根据id删除需求")
    @DeleteMapping(value = "/api/demand/deleteById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需求id", required = true, paramType = "query", dataType = "Long") })
    public JSONObject deleteById(Long id){
        ExecuteResult<String> result=new ExecuteResult<>();
        try{
            result=demandService.deleteById(id);
            if(result.isSuccess()){
                return ApiResponse.jsonData(APIStatus.OK_200);
            }
            return ApiResponse.jsonData(APIStatus.ERROR_503);
        }catch(Exception e){
            logger.error("------删除指定需求------"+e.getMessage());
            return ApiResponse.error();
        }
    }

    /**
     * 需求评审、变更、关闭、编辑功能 demandNeed 需求层级，传递本级需求层级前台根据父级的该字段进行传参 reviewedWith
     * 由谁评审字段用“,”拼接
     *
     * @param demand
     * @return
     */
    @ApiOperation(value = "编辑", notes = "需求编辑/评审/变更/添加备注/关闭")
    @PutMapping(value = "/api/demand/updateById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需求id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "pid", value = "所属上级需求id", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "reviewTime", value = "评审时间", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "demandNeed", value = "需求层级", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "reviewResults", value = "评审结果", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "assignedTo", value = "指派给", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "reviewedWith", value = "由谁评审", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "reviewRemark", value = "评审备注", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "reasonsejection", value = "拒绝原因", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "closeReason", value = "关闭原因", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "demandName", value = "需求名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "modifyUserId", value = "修改人id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "demandDesc", value = "需求描述", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "closeReason", value = "关闭原因", required = false, paramType = "query", dataType = "String") })
    public JSONObject updateById(Demand demand) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            if (null == demand) {
                return ApiResponse.error("");
            }
            Date currentDate = new Date();
            demand.setModifyTime(currentDate);
            result = demandService.updateByDemand(demand);
            return ApiResponse.jsonData(APIStatus.OK_200, result);
        } catch (Exception e) {
            logger.error("-------更新需求-------" + e.getMessage());
            return ApiResponse.error();
        }
    }

    /**
     * 分页查询需求列表
     *
     * @param demand
     * @param pager
     * @return
     */
    @ApiOperation(value = "查询需求列表", notes = "查询需求列表")
    @PutMapping(value = "/api/demand/queryDemandAll")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "demandNum", value = "需求编号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "demandLevel", value = "需求优先级", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "demandSource", value = "需求来源", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "projectId", value = "所属项目", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "reviewedWith", value = "由谁评审", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "显示行数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "demandName", value = "需求名称", required = false, paramType = "query", dataType = "String") })
    public JSONObject queryDemandAll(@ApiIgnore Demand demand, @ApiIgnore Pager<?> pager) {
        ExecuteResult<DataGrid<Demand>> result = new ExecuteResult<DataGrid<Demand>>();
        try {
            if (pager == null) {
                pager.setPage(1);
                pager.setRows(10);
            }
            result = demandService.findAllByPage(pager, demand);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }

    }

    /**
     * 根据id查询需求
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "需求查看", notes = "根据需求Id查看指定需求")
    @GetMapping(value = "/api/demand/findById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "所属一级需求id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "id", value = "需求id", required = true, paramType = "query", dataType = "Long") })
    public JSONObject findById(Long id) {
        ExecuteResult<Demand> result = new ExecuteResult<>();
        try {
            result = demandService.findById(id);
            return ApiResponse.success(result.getResult());
        } catch (Exception e) {
            logger.error("-------指定id查询需求-------" + e.getMessage());
            return ApiResponse.error();
        }
    }

    /**
     * 根据需求id查询需求历史记录
     *
     * @param pager
     * @param demandOperate
     * @return
     */
    @ApiOperation(value = "查询需求操作历史记录", notes = "查询需求操作历史记录")
    @GetMapping(value = "/api/demand/findWithOperate")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "显示行数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "id", value = "需求id", required = true, paramType = "query", dataType = "Long") })
    public JSONObject findWithOperate(@ApiIgnore Pager<?> pager, @ApiIgnore DemandOperate demandOperate) {
        ExecuteResult<DataGrid<DemandOperate>> result = new ExecuteResult<>();
        try {
            if (null == pager) {
                pager.setRows(10);
                pager.setPage(1);
            }
            result = demandService.findAllByPage(pager, demandOperate);
            return ApiResponse.success(result.getResult());
        } catch (Exception e) {
            logger.error("------查询需求操作历史记录------" + e.getMessage());
            return ApiResponse.error();
        }
    }

    /**
     * 根据需求id查询子父级需求
     * @param id
     * @return
     */
    @ApiOperation(value = "需求查看", notes = "根据需求id查询子父级需求")
    @GetMapping(value = "/api/demand/findChildParentById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "所属一级需求id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "id", value = "需求id", required = true, paramType = "query", dataType = "Long") })
    public JSONObject findChildParentById(Long id){
        ExecuteResult<Map<String,Object>> result=new ExecuteResult<>();
        try{
            result=demandService.findChildParentById(id);
            return ApiResponse.success(result.getResult());
        }catch(Exception e){
            logger.error("-------指定id查询子父级需求信息-------"+e.getMessage());
            return ApiResponse.error();
        }
    }
    
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

}
