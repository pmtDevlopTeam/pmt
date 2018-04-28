package com.camelot.pmt.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.project.model.RemindModel;
import com.camelot.pmt.project.service.ProjectRemindService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author lixk
 * @Description: 项目提醒模块
 * @date 2018年4月17日 下午5:48:37
 */
@RestController
@Api(value = "项目管理-项目提醒模块", description = "项目管理-项目提醒模块控制器类")
@RequestMapping("/project/projectRemind")
public class ProjectRemindController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProjectRemindService projectRemindService;

    /**
     *
     * @param RemindModel
     *            remindModel
     * @return
     */
    @ApiOperation(value = "新增项目提醒", notes = "新增项目提醒")
    @PostMapping(value = "/addProjectRemind")
    public JSONObject addProjectRemind(@RequestBody RemindModel remindModel) {
        boolean flag = false;
        try {
            // 获取当前登录人
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (null == user) {
                return ApiResponse.jsonData(APIStatus.INVALIDSESSION_LOGINOUTTIME);
            }
            if ((null == remindModel) || (null == remindModel.getProjectRemind())) {
                return ApiResponse.errorPara("请求参数异常");
            }
            flag = projectRemindService.addProjectRemind(remindModel, user);
            if (flag) {
                return ApiResponse.success("添加成功");
            }
            return ApiResponse.error();
        } catch (Exception e) {
            logger.error("------新增项目提醒------" + e.getMessage());
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    /**
     * 根据项目id查询项目提醒信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "项目提醒信息查看", notes = "根据项目Id查看项目提醒信息")
    @GetMapping(value = "/queryById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "projectRoleId", value = "提醒角色id", required = true, paramType = "query", dataType = "String") })
    public JSONObject queryProjectRemindByProjectId(Long projectId, String projectRoleId) {
        try {
            if ((null == projectId) || (0 == projectId)) {
                return ApiResponse.errorPara("请求参数异常");
            }
            RemindModel remindModel = projectRemindService.queryProjectRemindByProjectId(projectId, projectRoleId);
            return ApiResponse.success(remindModel);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }
}
