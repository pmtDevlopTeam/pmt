package com.camelot.pmt.filemanage.controller;

import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.filemanage.model.FileManageGroup;
import com.camelot.pmt.filemanage.service.FileManageGroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * 1zz
 * 
 * FileManageGroup 控制层
 *
 *
 */
@Controller

@Api(value = "产出物接口", description = "产出物接口")

@RequestMapping(value = "/file/manager/group")
public class FileManageGroupController {
    // 日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FileManageGroupService fileManageGroupService;

    @ApiOperation(value = "添加文件夹功能", notes = "添加文件夹功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "父级id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "projectId", value = "项目id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "文件夹名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "fdescribe", value = "文件夹描述", required = true, paramType = "query", dataType = "String"), })
    @RequestMapping(value = "/addFileManagerGroup", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addFileManagerGroup(HttpServletRequest request, FileManageGroup fileManageGroup) {// 添加文件夹
        Boolean b = null;
        try {
            b = fileManageGroupService.addFileManagerGroup(request, fileManageGroup);// 添加文件夹
            if (b) {
                return ApiResponse.success();
            }
            return ApiResponse.error("添加异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    @ApiOperation(value = "删除文件夹功能", notes = "删除文件夹功能")
    @ApiImplicitParams({ // 删除文件夹
            @ApiImplicitParam(name = "id", value = "文件夹id", required = true, paramType = "query", dataType = "String") })
    @RequestMapping(value = "/deleteFileGroup", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject deleteFileGroup(FileManageGroup fileManageGroup) {
        Boolean b = null;
        try {
            b = fileManageGroupService.deleteFileGroup(fileManageGroup);// 删除文件夹
            if (b) {
                return ApiResponse.success();
            }
            return ApiResponse.error("删除异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    @ApiOperation(value = "根据条件修改文件夹功能", notes = "根据条件修改文件夹功能")
    @ApiImplicitParams({ // 修改文件夹
            @ApiImplicitParam(name = "isFile", value = "是否是文件", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "createUserId", value = "创建人id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "parentId", value = "父级id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "projectId", value = "项目id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "文件夹名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "fdescribe", value = "文件夹描述", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "id", value = "文件夹id", required = true, paramType = "query", dataType = "String") })
    @RequestMapping(value = "/updateFileGroupById", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateFileGroupById(HttpServletRequest request, FileManageGroup fileManageGroup) {
        Boolean b = null;
        try {
            b = fileManageGroupService.updateFileGroupById(request, fileManageGroup);// 修改文件夹
            if (b) {
                return ApiResponse.success();
            }
            return ApiResponse.error("修改异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    @ApiOperation(value = "根据条件查询文件夹功能", notes = "根据条件查询文件夹功能")
    @ApiImplicitParams({ // 根据条件查询文件夹
            @ApiImplicitParam(name = "parentId", value = "父级id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "projectId", value = "项目id", required = false, paramType = "query", dataType = "String")

    })
    @RequestMapping(value = "/querytFileGroup", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject querytFileGroup(FileManageGroup fileManageGroup) {// 根据parentId查询根据projectId查询
        try {
            List<FileManageGroup> groupList = fileManageGroupService.querytFileGroup(fileManageGroup);// 根据条件查询文件夹
            return ApiResponse.success(groupList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    @ApiOperation(value = "根据项目id查询子节点数据", notes = "根据项目id查询子节点数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query", dataType = "String"), })
    @RequestMapping(value = "/queryTree", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject queryTree(FileManageGroup fileManageGroup) {// 根据项目id查询子节点
        try {
            List<FileManageGroup> treeList = fileManageGroupService.queryTree(fileManageGroup);// 根据项目id查询子节点
            return ApiResponse.success(treeList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }
}
