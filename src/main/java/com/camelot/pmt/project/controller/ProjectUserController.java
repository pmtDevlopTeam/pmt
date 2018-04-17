package com.camelot.pmt.project.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.camelot.pmt.common.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.project.model.ProjectUser;
import com.camelot.pmt.project.model.ProjectUserSearchVO;
import com.camelot.pmt.project.model.ProjectUserShow;
import com.camelot.pmt.project.service.ProjectUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 项目成员管理
 * 
 * @author guodx
 *
 */
@RequestMapping("/api/project/user")
@Api("项目成员管理接口")
@RestController
@EnableSwagger2
public class ProjectUserController {

    private Logger logger = LoggerFactory.getLogger(ProjectUserController.class);

    @Autowired
    private ProjectUserService projectUserService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation("项目添加成员")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "projectId", dataType = "Long", value = "项目id", required = true),
            @ApiImplicitParam(paramType = "query", name = "userId", dataType = "String", value = "成员id", required = true),
            @ApiImplicitParam(paramType = "query", name = "preJoinTime", dataType = "Date", value = "预计进项目时间", required = true),
            @ApiImplicitParam(paramType = "query", name = "preOutTime", dataType = "Date", value = "预计出项目时间", required = true),
            @ApiImplicitParam(paramType = "query", name = "userProRole", dataType = "String", value = "角色id（在项目角色）", required = true),
            @ApiImplicitParam(paramType = "query", name = "createUserId", dataType = "String", value = "创建人id", required = true),
            @ApiImplicitParam(paramType = "query", name = "preManHour", dataType = "int", value = "预计工时", required = true) })
    public JSONObject addUser(ProjectUser pu) {
        Date currentDate = new Date();
        pu.setCreateTime(currentDate);
        pu.setUserStatus(ProjectUser.STATUS_NOT_IN);
        pu.setModifyUserId(pu.getCreateUserId());
        pu.setModifyTime(currentDate);
        try {
            projectUserService.insertSelective(pu);
            return ApiResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("项目成员数据插入失败，原因 [{}]", e.getMessage());
            return ApiResponse.error("添加异常");
        }
    }

    /**
     * 确认项目成员进入项目
     * 
     * @return
     */
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    @ApiOperation("确认成员进入项目")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "modifyUserId", dataType = "String", value = "确认人id", required = true),
            @ApiImplicitParam(paramType = "query", name = "id", dataType = "Long", value = "当前一条数据的id", required = true), })
    public JSONObject confirmUserIn(String modifyUserId, Long id) {
        logger.info("confirm user in project , modifyUserId = [{}]", modifyUserId);
        ProjectUser pu = new ProjectUser();
        Date currentDate = new Date();
        pu.setModifyUserId(modifyUserId);
        pu.setModifyTime(currentDate);
        pu.setId(id);
        pu.setRealJoinTime(currentDate);
        pu.setUserStatus(ProjectUser.STATUS_IN);
        try {
            projectUserService.confirmUser(pu);
            return ApiResponse.success();
        } catch (Exception e) {
            logger.error("确认异常");
            e.printStackTrace();
            return ApiResponse.error("确认异常");
        }
    }

    /**
     * 查找项目成员，条件查询 姓名，状态，角色，项目角色
     * 
     * @param projectId
     * @return
     */
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    @ApiOperation("查找项目成员")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "projectId", dataType = "Long", value = "项目id", required = true),
            @ApiImplicitParam(paramType = "query", name = "userName", dataType = "String", value = "用户姓名", required = false),
            @ApiImplicitParam(paramType = "query", name = "userStatus", dataType = "String", value = "用户状态", required = false),
            @ApiImplicitParam(paramType = "query", name = "userProRoleIds", dataType = "String", value = "成员项目角色id", required = false),
            @ApiImplicitParam(paramType = "query", name = "roleId", dataType = "String", value = "成员角色id", required = false),
            @ApiImplicitParam(paramType = "query", name = "page", dataType = "int", value = "当前页", required = false),
            @ApiImplicitParam(paramType = "query", name = "size", dataType = "int", value = "每页条数", required = false) })
    public JSONObject findUser(ProjectUserSearchVO vo) {
        try {
            if (vo.getPage() != null && vo.getSize() != null) {
                vo.setPage((vo.getPage() - 1) * vo.getSize());
            }
            Map<String, Object> map = new HashMap<>();
            List<ProjectUserShow> list = projectUserService.searchProUserByCondition(vo);
            map.put("list", list);
            int count;
            if (list != null && list.size() > 0) {
                count = projectUserService.count(vo);
            } else {
                count = 0;
            }
            map.put("count", count);
            return ApiResponse.success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("查询异常");
        }
    }

    @RequestMapping(value = "/clean", method = { RequestMethod.POST, RequestMethod.GET })
    @ApiOperation("清除项目成员")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "projectId", dataType = "Long", value = "项目id", required = true),
            @ApiImplicitParam(paramType = "query", name = "userId", dataType = "String", value = "用户id", required = true) })
    public JSONObject cleanUser(Long projectId, String userIds) {
        String returnMessage = "请先结束人员在项目中的任务";
        if (projectId == null || userIds == null) {
            return ApiResponse.errorPara();
        }
        String[] split = userIds.split(",");
        List<String> list = Arrays.asList(split);
        try {
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();

                list.remove(next);
            }
            // 查询task任务表，根据项目id， 用户id， 状态查询
            // 查询是否有bug需要修改
            projectUserService.clearUser(projectId, "");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("修改异常");
        }
    }

}