package com.camelot.pmt.project.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.project.model.ProjectMain;
import com.camelot.pmt.project.service.ProjectMainService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author qiaodj
 * @date 2018年4月17日
 */
@RestController
@RequestMapping(path = "/project", produces = "application/json;charset=utf-8")
@Api(value = "项目管理接口", description = "项目管理接口")
public class ProjectMainController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectMainController.class);
    @Autowired
    private ProjectMainService projectMainService;

    /**
     * 根据具有立项权限的用户新建项目
     * 
     * @param projectMain
     * @return
     */
    @ApiOperation(notes = "新建项目", value = "根据具有立项权限的用户新建项目")
    @PostMapping("/addProject")
    public JSONObject addProject(//
            @ApiParam(value = "负责人Id", required = true) @RequestParam String userId, //
            @ApiParam(value = "项目名称", required = true) @RequestParam String projectName, //
            @ApiParam(value = "项目状态", required = true) @RequestParam String projectStatus, //
            @ApiParam(value = "开始时间", required = true) @RequestParam @DateTimeFormat(iso = ISO.DATE) Date startTime, //
            @ApiParam(value = "结束时间", required = true) @RequestParam @DateTimeFormat(iso = ISO.DATE) Date endTime, //
            @ApiParam(value = "项目描述", required = true) @RequestParam String projectDesc, //
            @ApiParam(value = "预计工时", required = true) @RequestParam Integer budgetaryHours) {

        logger.info(
                "入参封装的数据为：userId={},projectName={},projectStatus={},startTime={},endTime={},projectDesc={},budgetaryHours={}",
                userId, projectName, projectStatus, startTime, endTime, projectDesc, budgetaryHours);
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(projectName) || StringUtils.isEmpty(projectStatus)
                || startTime == null || endTime == null || StringUtils.isEmpty(projectDesc) || budgetaryHours == null) {
            return ApiResponse.errorPara("入参不能为空");
        }
        try {
            // 调用addProject方法保存数据
            int projectMainNum = projectMainService.addProject(userId, projectName, projectStatus, startTime, endTime,
                    projectDesc, budgetaryHours);
            logger.debug("调用projectMainService的addProject接口返回的条数为：", projectMainNum);
            if (projectMainNum > 0) {
                return ApiResponse.success();
            } else {
                return ApiResponse.errorPara();
            }
        } catch (Exception e) {
            // 异常
            logger.error("调用addProject接口出现异常为：", e);
            return ApiResponse.error();
        }
    }

    /**
     * 分页查询
     * 
     * @param currentPage
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "分页查询所有", notes = "分页查询所有")
    @GetMapping(value = "/queryAllByPage")
    public JSONObject queryAllByPage(//
            @ApiParam(value = "当前页数", required = true) @RequestParam Integer currentPage, //
            @ApiParam(value = "每页数量", required = true) @RequestParam Integer pageSize) {
        logger.info("入参封装的数据为：currentPage={},pageSize={}", currentPage, pageSize);
        if (currentPage == null || pageSize == null) {
            return ApiResponse.errorPara("入参不能为空");
        }
        try {
            List<ProjectMain> list = projectMainService.queryAllByPage(currentPage, pageSize);
            if (list.size() > 0) {
                return ApiResponse.success(list);
            }
            return ApiResponse.error("分页查询数据失败");
        } catch (Exception e) {
            return ApiResponse.error("分页查询数据出现异常");
        }
    }

    /**
     * 按项目状态分类查询
     * 
     * @param projectStatus
     * @return
     */
    @ApiOperation(value = "按状态分类查询", notes = "按状态分类查询")
    @GetMapping(value = "/queryByProjectStatus")
    public JSONObject queryByProjectStatus(
            @ApiParam(value = "项目状态", required = true) @RequestParam String projectStatus) {
        logger.info("入参封装的数据为：projectStatus={}", projectStatus);
        if (StringUtils.isEmpty(projectStatus)) {
            return ApiResponse.errorPara("入参不能为空");
        }
        try {
            List<ProjectMain> projectMainList = projectMainService.queryByProjectStatus(projectStatus);
            if (projectMainList.size() > 0) {
                return ApiResponse.success(projectMainList);
            }
            return ApiResponse.error("按项目状态分类查询失败");
        } catch (Exception e) {
            return ApiResponse.error("按项目状态分类查询出现异常");
        }
    }

    /**
     * 按负责人id查询
     * 
     * @param userId
     * @return
     */
    @ApiOperation(value = "按负责人id查询", notes = "按负责人id查询")
    @GetMapping(value = "/queryByUserId")
    public JSONObject queryByUserId(@ApiParam(value = "负责人Id", required = true) @RequestParam String userId) {
        logger.info("入参封装的数据为：userId={}", userId);
        if (StringUtils.isEmpty(userId)) {
            return ApiResponse.errorPara("入参不能为空");
        }
        try {
            List<ProjectMain> projectMainList = projectMainService.queryByUserId(userId);
            if (projectMainList.size() > 0) {
                return ApiResponse.success(projectMainList);
            }
            return ApiResponse.error("按负责人id查询数据失败");
        } catch (Exception e) {
            return ApiResponse.error("按负责人id查询数据出现异常");
        }
    }

    /**
     * 按创建人id查询
     * 
     * @param createUserId
     * @return
     */
    @ApiOperation(value = "按创建人id查询", notes = "按创建人id查询")
    @GetMapping(value = "/queryByCreateUserId")
    public JSONObject queryByCreateUserId(
            @ApiParam(value = "创建人id", required = true) @RequestParam String createUserId) {
        logger.info("入参封装的数据为：createUserId={}", createUserId);
        if (StringUtils.isEmpty(createUserId)) {
            return ApiResponse.errorPara("入参不能为空");
        }
        try {
            List<ProjectMain> projectMainList = projectMainService.queryByCreateUserId(createUserId);
            if (projectMainList.size() > 0) {
                return ApiResponse.success(projectMainList);
            }
            return ApiResponse.error("按创建人id查询数据失败");
        } catch (Exception e) {
            return ApiResponse.error("按创建人id查询数据出现异常");
        }
    }

    /**
     * 按修改人id查询
     * 
     * @param modifyUserId
     * @return
     */
    @ApiOperation(value = "按修改人id查询", notes = "按修改人id查询")
    @GetMapping(value = "/queryByModifyUserId")
    public JSONObject queryByModifyUserId(
            @ApiParam(value = "修改人id", required = true) @RequestParam String modifyUserId) {
        logger.info("入参封装的数据为：modifyUserId={}", modifyUserId);
        if (StringUtils.isEmpty(modifyUserId)) {
            return ApiResponse.errorPara("入参不能为空");
        }
        try {
            List<ProjectMain> projectMainList = projectMainService.queryByModifyUserId(modifyUserId);
            if (projectMainList.size() > 0) {
                return ApiResponse.success(projectMainList);
            }
            return ApiResponse.error("按修改人id查询数据失败");
        } catch (Exception e) {
            return ApiResponse.error("按修改人id查询数据出现异常");
        }
    }

    /**
     * 按主键id更新数据
     * 
     * @param id
     * @param userId
     * @param projectName
     * @param projectStatus
     * @param startTime
     * @param endTime
     * @param projectDesc
     * @return
     */
    @ApiOperation(value = "按主键id更新数据", notes = "按主键id更新数据")
    @PutMapping(value = "/updateByPrimaryKeySelective")
    public JSONObject updateByPrimaryKeySelective(@ApiParam(value = "id", required = true) @RequestParam Long id, //
            @ApiParam(value = "负责人Id", required = true) @RequestParam String userId, //
            @ApiParam(value = "项目名称", required = true) @RequestParam String projectName, //
            @ApiParam(value = "项目状态", required = true) @RequestParam String projectStatus, //
            @ApiParam(value = "开始时间", required = true) @RequestParam @DateTimeFormat(iso = ISO.DATE) Date startTime, //
            @ApiParam(value = "结束时间", required = true) @RequestParam @DateTimeFormat(iso = ISO.DATE) Date endTime, //
            @ApiParam(value = "项目描述", required = true) @RequestParam String projectDesc) {

        logger.info("入参封装的数据为：id={},userId={},projectName={},projectStatus={},startTime={},endTime={},projectDesc={}",
                id, userId, projectName, projectStatus, startTime, endTime, projectDesc);
        if (id == null || StringUtils.isEmpty(userId) || StringUtils.isEmpty(projectName)
                || StringUtils.isEmpty(projectStatus) || startTime == null || endTime == null
                || StringUtils.isEmpty(projectDesc)) {
            return ApiResponse.errorPara("入参不能为空");
        }
        try {
            int projectMainNum = projectMainService.updateByPrimaryKeySelective(id, userId, projectName, projectStatus,
                    projectDesc, startTime, endTime);
            if (projectMainNum > 0) {
                return ApiResponse.success("按主键id更新数据成功");
            }
            return ApiResponse.error("按主键id更新数据失败");
        } catch (Exception e) {
            return ApiResponse.error("按主键id更新数据出现异常");
        }
    }

    /**
     * 根据id删除项目
     * 
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id删除项目", notes = "根据id删除项目")
    @DeleteMapping(value = "/deleteByPrimaryKey")
    public JSONObject deleteByPrimaryKey(@ApiParam(value = "id", required = true) @RequestParam Long id) {

        logger.info("入参封装的数据为：id={}", id);
        if (id == null) {
            return ApiResponse.errorPara("入参不能为空");
        }
        try {
            int projectMainNum = projectMainService.deleteByPrimaryKey(id);
            if (projectMainNum > 0) {
                return ApiResponse.success("根据id删除项目数据成功");
            }
            return ApiResponse.error("根据id删除项目数据失败");
        } catch (Exception e) {
            return ApiResponse.error("根据id删除项目数据出现异常");
        }
    }

    /**
     * 按项目id查询
     * 
     * @param id
     * @return
     */
    @ApiOperation(value = "按项目id查询", notes = "按项目id查询")
    @GetMapping(value = "/queryByPrimaryKey")
    public JSONObject selectByPrimaryKey(@ApiParam(value = "项目id", required = true) @RequestParam Long id) {

        logger.info("入参封装的数据为：id={}", id);
        if (id == null) {
            return ApiResponse.errorPara("入参不能为空");
        }
        try {
            ProjectMain projectMain = projectMainService.queryByPrimaryKey(id);
            if (projectMain != null) {
                return ApiResponse.success(projectMain);
            }
            return ApiResponse.error("按项目id查询数据失败");
        } catch (Exception e) {
            return ApiResponse.error("按项目id查询数据出现异常");
        }
    }

    /**
     * 关闭项目时,更新相关数据
     * 
     * @param id
     * @param projectStatus
     * @param userStatus
     * @param demandStatus
     * @param closeReason
     * @param status
     * @param caseStatus
     * @return
     */
    @ApiOperation(value = "关闭项目时,更新相关数据", notes = "关闭项目时,更新相关数据")
    @PutMapping(value = "/updateByProjectById/close")
    public JSONObject closeProjectById(//
            @ApiParam(value = "id", required = true) @RequestParam Long id, //
            @ApiParam(value = "项目状态", required = true) @RequestParam String projectStatus, //
            @ApiParam(value = "成员在项目状态", required = true) @RequestParam String userStatus, //
            @ApiParam(value = "需求状态", required = true) @RequestParam String demandStatus, //
            @ApiParam(value = "关闭原因", required = true) @RequestParam String closeReason, //
            @ApiParam(value = "任务状态", required = true) @RequestParam String status, //
            @ApiParam(value = "用例状态", required = true) @RequestParam String caseStatus) {

        logger.info(
                "入参封装的数据为：id={},projectStatus={},userStatus={},demandStatus={},closeReason={},status={},caseStatus={}",
                id, projectStatus, userStatus, demandStatus, closeReason, status, caseStatus);
        if (id == null || StringUtils.isEmpty(projectStatus) || StringUtils.isEmpty(userStatus)
                || StringUtils.isEmpty(demandStatus) || StringUtils.isEmpty(closeReason) || StringUtils.isEmpty(status)
                || StringUtils.isEmpty(caseStatus)) {
            return ApiResponse.errorPara("入参不能为空");
        }
        try {
            int projectMainNum = projectMainService.updateByProjectById(id, projectStatus, userStatus, demandStatus,
                    closeReason, status, caseStatus);
            if (projectMainNum > 0) {
                return ApiResponse.success("关闭项目时,更新相关数据成功");
            }
            return ApiResponse.error("关闭项目时,更新相关数据失败");
        } catch (Exception e) {
            return ApiResponse.error("关闭项目时,更新相关数据出现异常");
        }
    }

    /**
     * 挂起项目 只有开始的项目才可以挂起
     * 
     * @param id
     * @param projectStatus
     * @return
     */
    @ApiOperation(value = "挂起项目 只有开始的项目才可以挂起", notes = "挂起项目 只有开始的项目才可以挂起")
    @PutMapping(value = "/updateById/suspension")
    public JSONObject updateByIdSuspension(@ApiParam(value = "id", required = true) @RequestParam Long id, //
            @ApiParam(value = "项目状态", required = true) @RequestParam String projectStatus) {

        logger.info("入参封装的数据为：id={},projectStatus={}", id, projectStatus);
        if (id == null || StringUtils.isEmpty(projectStatus)) {
            return ApiResponse.errorPara("入参不能为空");
        }
        try {
            int projectMainNum = projectMainService.updateByIdSuspension(id, projectStatus);
            if (projectMainNum > 0) {
                return ApiResponse.success("挂起项目成功");
            }
            return ApiResponse.error("挂起项目失败");
        } catch (Exception e) {
            return ApiResponse.error("挂起项目出现异常");
        }
    }

}
