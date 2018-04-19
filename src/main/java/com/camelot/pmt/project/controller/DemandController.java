package com.camelot.pmt.project.controller;

import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

import springfox.documentation.annotations.ApiIgnore;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.project.model.Demand;
import com.camelot.pmt.project.model.DemandOperate;
import com.camelot.pmt.project.model.DemandVO;
import com.camelot.pmt.project.service.DemandService;

/**
 * @Author:fjy
 * @Description: 需求控制器类
 * @Date:2018/4/12_16:11
 */
@RestController
@Api(value = "项目管理-需求模块", description = "项目管理-需求模块的控制器类")
@RequestMapping(value = "/platform/project")
public class DemandController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DemandService demandService;

    /**
     * 需求状态（未激活/已激活/已关闭/已变更）
     *
     * @param demand
     * @return
     */
    @ApiOperation(value = "新增需求", notes = "新增需求")
    @PostMapping(value = "/demand/addDemand")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "所属一级需求id", required = false, paramType = "query", dataType = "Long", defaultValue = "0"),
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "demandName", value = "需求名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "demandSource", value = "需求来源", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "demandLevel", value = "优先级", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sourceRemark", value = "需求来源备注", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "assignedTo", value = "指派给", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "demandNeed", value = "需求层级(默认一级)", required = true, paramType = "query", dataType = "String", defaultValue = "1"),
            @ApiImplicitParam(name = "reviewedWith", value = "由谁评审(人员user_id用逗号拼接)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "demandDesc", value = "需求描述", required = false, paramType = "query", dataType = "String") })
    public JSONObject addDemand(@ApiIgnore Demand demand) {
        boolean flag = false;
        try {
            // 获取当前登录人
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (null == user) {
                return ApiResponse.jsonData(APIStatus.INVALIDSESSION_LOGINOUTTIME);
            }
            Date currentDate = new Date();
            demand.setCreateTime(currentDate);
            demand.setCreateUserId(user.getUserId());
            demand.setModifyUserId(user.getUserId());
            demand.setModifyTime(currentDate);
            if (null == demand.getPid()) {
                demand.setPid(0l);
            }
            // 设置新增需求状态01:未开始
            demand.setDemandStatus("01");
            flag = demandService.save(demand, user);
            if (flag) {
                return ApiResponse.success("添加成功");
            }
            return ApiResponse.error();
        } catch (Exception e) {
            logger.error("------需求新增------" + e.getMessage());
            return ApiResponse.error();
        }
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除需求", notes = "根据id删除需求")
    @DeleteMapping(value = "/demand/deleteById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需求id", required = true, paramType = "query", dataType = "Long") })
    public JSONObject deleteDemandById(Long id) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            result = demandService.deleteDemandById(id);
            if (result.isSuccess()) {
                return ApiResponse.jsonData(APIStatus.OK_200);
            }
            return ApiResponse.jsonData(APIStatus.ERROR_503);
        } catch (Exception e) {
            logger.error("------删除指定需求------" + e.getMessage());
            return ApiResponse.error();
        }
    }

    /**
     * 变更、关闭、编辑功能 demandNeed 需求层级，传递本级需求层级前台根据父级的该字段进行传参 reviewedWith 由谁评审字段用“,”拼接
     *
     * @param demand
     * @return
     */
    @ApiOperation(value = "编辑", notes = "需求编辑/变更/添加备注/关闭")
    @PutMapping(value = "/demand/updateById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需求id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "pid", value = "所属上级需求id", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "demandName", value = "需求名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "demandDesc", value = "需求描述", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "demandStatus", value = "需求状态", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "closeReason", value = "关闭原因", required = false, paramType = "query", dataType = "String") })
    public JSONObject updateById(@ApiIgnore Demand demand) {
        boolean flag = false;
        try {
            // 获取当前登录人
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (null == user) {
                return ApiResponse.jsonData(APIStatus.INVALIDSESSION_LOGINOUTTIME);
            }
            Date currentDate = new Date();
            demand.setModifyTime(currentDate);
            demand.setModifyUserId(user.getUserId());
            flag = demandService.updateByDemand(demand, user);
            if (flag) {
                return ApiResponse.success();
            }
            return ApiResponse.error();
        } catch (Exception e) {
            logger.error("-------更新需求-------" + e.getMessage());
            return ApiResponse.error();
        }
    }

    /**
     * 需求评审
     */
    @ApiOperation(value = "需求评审", notes = "评审")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需求id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "reviewTime", value = "评审时间", required = true, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "reviewResults", value = "评审结果", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "reviewedWith", value = "由谁评审", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "reviewRemark", value = "评审备注", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "reasonsRejection", value = "关闭原因", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "assignedTo", value = "指派给", required = true, paramType = "query", dataType = "String") })
    @PutMapping(value = "/demand/review")
    public JSONObject updateByReview(@ApiIgnore Demand demand) {
        boolean flag = false;
        try {
            // 获取当前登录人
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (null == user) {
                return ApiResponse.jsonData(APIStatus.INVALIDSESSION_LOGINOUTTIME);
            }
            Date currentDate = new Date();
            demand.setModifyTime(currentDate);
            demand.setModifyUserId(user.getUserId());
            flag = demandService.updateByReview(demand, user);
            if (flag) {
                return ApiResponse.success();
            }
            return ApiResponse.error();
        } catch (Exception e) {
            logger.error("------需求评审-------" + e.getMessage());
            return ApiResponse.error();
        }
    }

    /**
     * 分页查询需求列表
     *
     * @param demand
     * @param
     * @return
     */
    @ApiOperation(value = "查询需求列表", notes = "查询需求列表")
    @PutMapping(value = "/demand/queryByPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "demandNum", value = "需求编号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "demandLevel", value = "需求优先级", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "demandSource", value = "需求来源", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "projectId", value = "所属项目", required = false, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "reviewedWith", value = "由谁评审", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "currentPage", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示行数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "demandName", value = "需求名称", required = false, paramType = "query", dataType = "String") })
    public JSONObject queryByPage(@ApiIgnore Demand demand, @RequestParam(defaultValue = "1") Integer pageSize,
            @RequestParam(defaultValue = "10") Integer currentPage) {
        try {
            List<Demand> list = demandService.queryByPage(demand, pageSize, currentPage);
            PageInfo<Demand> result = new PageInfo<>(list);
            return ApiResponse.success(result);
        } catch (Exception e) {
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
    @GetMapping(value = "/demand/queryById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "所属一级需求id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "id", value = "需求id", required = true, paramType = "query", dataType = "Long") })
    public JSONObject queryDemandByDemandId(Long id) {
        try {
            DemandVO demandVO = demandService.queryDemandById(id);
            return ApiResponse.success(demandVO);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /**
     * 根据需求id查询需求历史记录
     *
     * @param
     * @param demandOperate
     * @return
     */
    @ApiOperation(value = "查询需求操作历史记录", notes = "查询需求操作历史记录")
    @GetMapping(value = "/demand/findWithOperate")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示行数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "demandId", value = "需求id", required = true, paramType = "query", dataType = "Long") })
    public JSONObject findOperateByPage(@ApiIgnore DemandOperate demandOperate,
            @RequestParam(defaultValue = "1") Integer pageSize,
            @RequestParam(defaultValue = "10") Integer currentPage) {
        try {
            List<DemandOperate> list = demandService.queryOperateByPage(demandOperate, pageSize, currentPage);
            PageInfo<DemandOperate> result = new PageInfo<>(list);
            return ApiResponse.success(result);
        } catch (Exception e) {
            logger.error("------查询需求操作历史记录------" + e.getMessage());
            return ApiResponse.error();
        }
    }

    /**
     * 根据需求id查询需求变更影响的任务信息
     * 
     * @param demandId
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "根据需求id查询需求变更影响的任务", notes = "查询需求变更影响的任务信息")
    @GetMapping(value = "/queryDemandTaskQuoteById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "页码", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示行数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "demandId", value = "需求id", required = true, paramType = "query", dataType = "Long") })
    public JSONObject queryDemandTaskQuoteById(@RequestParam(value = "demandId", required = true) Long demandId,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer currentPage) {
        try {
            if ((null == demandId) || (0 == demandId)) {
                return ApiResponse.errorPara("传入的需求id有误");
            }
            PageInfo<Map<String, Object>> taskList = demandService.queryDemandTaskQuoteById(demandId, pageSize,
                    currentPage);
            return ApiResponse.success(taskList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 查询需求变更影响的任务信息
     * 
     * @param demandId
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "根据需求id查询需求变更影响的用例信息", notes = "查询需求变更影响的任务信息")
    @GetMapping(value = "/queryDemandUseCaseQuoteById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "页码", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示行数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "demandId", value = "需求id", required = true, paramType = "query", dataType = "Long") })
    public JSONObject queryDemandUseCaseQuoteById(@RequestParam(value = "demandId", required = true) Long demandId,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer currentPage) {
        try {
            PageInfo<Map<String, Object>> useCaseList = demandService.queryDemandUseCaseQuoteById(demandId, pageSize,
                    currentPage);
            return ApiResponse.success(useCaseList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 查询需求变更影响的bug信息
     * 
     * @param demandId
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "根据需求id查询需求变更影响的bug信息", notes = "查询需求变更影响的bug信息")
    @GetMapping(value = "/queryDemandBugQuoteById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "页码", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示行数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "demandId", value = "需求id", required = true, paramType = "query", dataType = "Long") })
    public JSONObject queryDemandBug(@RequestParam(value = "demandId", required = true) Long demandId,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer currentPage) {
        try {
            PageInfo<Map<String, Object>> bugList = demandService.queryDemandBugQuoteById(demandId, pageSize,
                    currentPage);
            return ApiResponse.success(bugList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

}
