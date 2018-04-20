package com.camelot.pmt.filemanage.controller;

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
import com.camelot.pmt.filemanage.model.FileManage;
import com.camelot.pmt.filemanage.model.FileManageGroup;
import com.camelot.pmt.filemanage.service.FileManageService;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *
 * FileManage 控制层
 *
 * 
 */
@Controller
@Api(value = "文件数据接口", description = "文件数据接口")
@RequestMapping(value = "/file/manager")
public class FileManageController {
    // 日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FileManageService fileManageService;

    @ApiOperation(value = "添加文件功能", notes = "添加文件功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileTitle", value = "文件标题", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "fileAddress", value = "文件地址", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "parentId", value = "文件夹父级id", required = false, paramType = "query", dataType = "String") })
    @RequestMapping(value = "/addFileManager", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addFileManager(HttpServletRequest request, FileManage fileManage, Long parentId) {// 添加文件
        Boolean b = null;
        try {
            b = fileManageService.addFileManager(request, fileManage, parentId);// 添加文件夹
            if (b) {
                return ApiResponse.success();
            }
            return ApiResponse.error("添加异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    @ApiOperation(value = "删除文件功能", notes = "删除文件能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产出物id", required = true, paramType = "query", dataType = "String") })
    @RequestMapping(value = "/deldeteFileById", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject deldeteFileById(FileManage fileManage) {// 删除文件
        Boolean b = null;
        try {
            b = fileManageService.deleteFileById(fileManage);// 删除文件
            if (b) {
                return ApiResponse.success();
            }
            return ApiResponse.error("删除异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    @ApiOperation(value = "根据条件修改文件功能", notes = "根据条件修改文件功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产出物id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "groupId", value = "组id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "fileTitle", value = "文件标题", required = false, paramType = "query", dataType = "String") })
    @RequestMapping(value = "/updateFileById", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateFileById(HttpServletRequest request, FileManage fileManage) {// 文件修改
        Boolean b = null;
        try {
            b = fileManageService.updateFileById(request, fileManage);// 文件修改
            if (b) {
                return ApiResponse.success();
            }
            return ApiResponse.error("修改异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    @ApiOperation(value = "根据条件查询文件功能", notes = "根据条件查询文件功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "组id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小", required = true, paramType = "query", dataType = "String") })
    @RequestMapping(value = "/queryFileByGroupId", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject queryFileByGroupId(FileManageGroup fileManageGroup) {// 根据组id查询文件
        try {
            PageInfo<FileManage> fileManagePageInfo = fileManageService.queryFileByGroupId(fileManageGroup);// 查询文件详细信息
            return ApiResponse.success(fileManagePageInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

}