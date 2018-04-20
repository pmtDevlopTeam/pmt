package com.camelot.pmt.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.project.model.VersionCitingHistory;
import com.camelot.pmt.project.service.VersionCitingHistoryService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * @Package: com.camelot.pmt.project.controller
 * @ClassName: VersionCitingHistoryController
 * @Description: TODO
 * @author: xueyj
 * @date: 2018-04-20 15:54
 */
@RestController
@RequestMapping(value = "/platform/versionCitingHistory")
@Api(value = "基础平台-版本控制接口", description = "基础平台-版本控制接口")
public class VersionCitingHistoryController {
    @Resource
    VersionCitingHistoryService versionCitingHistoryService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * @Description: 添加版本关联记录接口
     * @param: projectId
     * @param: userId
     * @param: versionVo
     * @return: JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     * @author: xueyj
     * @date: 2018/4/13 18:31
     */
    @ApiOperation(value = "添加版本关联记录接口", notes = "添加版本关联记录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "versionId", value = "版本id", required = true, paramType = "form", dataType = "Long"),
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "form", dataType = "Long"),
            @ApiImplicitParam(name = "demandId", value = "需求id", required = false, paramType = "form", dataType = "Long"),
            @ApiImplicitParam(name = "taskId", value = "任务id", required = false, paramType = "form", dataType = "Long"),
            @ApiImplicitParam(name = "bugId", value = "bugid", required = false, paramType = "form", dataType = "Long"),
            @ApiImplicitParam(name = "testCaseId", value = "测试用例id", required = false, paramType = "form", dataType = "Long") })
    @RequestMapping(value = "/addVersionCitingHistory", method = RequestMethod.POST)
    public JSONObject addVersionCitingHistory(@ApiIgnore VersionCitingHistory versionCitingHistory) {
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (user != null) {
                boolean flag = versionCitingHistoryService.queryVersionCitingHistoryByParms(versionCitingHistory).size()>0;
                if (flag){
                    return ApiResponse.error("该信息已存在，无法再次添加");
                }
                flag = versionCitingHistoryService.addVersionCitingHistoryByParms(user.getUserId(),versionCitingHistory);
                if (flag) {
                    return ApiResponse.success();
                }
                return ApiResponse.error("添加异常");
            } else {
                return ApiResponse.error("用户未登录，请登录！");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /**
     * @Description: 分页查询版本关联记录接口
     * @param: versionCitingHistory
     * @param: rows
     * @return: JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     * @author: xueyj
     * @date: 2018/4/13 18:31
     */
    @ApiOperation(value = "添加版本关联记录接口", notes = "添加版本关联记录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每页数量", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "versionId", value = "版本id", required = false, paramType = "form", dataType = "Long"),
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "form", dataType = "Long"),
            @ApiImplicitParam(name = "demandId", value = "需求id", required = false, paramType = "form", dataType = "Long"),
            @ApiImplicitParam(name = "taskId", value = "任务id", required = false, paramType = "form", dataType = "Long"),
            @ApiImplicitParam(name = "bugId", value = "bugid", required = false, paramType = "form", dataType = "Long"),
            @ApiImplicitParam(name = "testCaseId", value = "测试用例id", required = false, paramType = "form", dataType = "Long") })
    @RequestMapping(value = "/queryVersionCitingHistory", method = RequestMethod.POST)
    public JSONObject queryVersionCitingHistory(int page,int rows,@ApiIgnore VersionCitingHistory versionCitingHistory) {
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (user != null) {
                PageInfo pageInfo = versionCitingHistoryService.queryVersionCitingHistoryByPageAndParms(page, rows, versionCitingHistory);
                return ApiResponse.success(pageInfo);
            } else {
                return ApiResponse.error("用户未登录，请登录！");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }
}