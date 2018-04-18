package com.camelot.pmt.filemanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.filemanage.model.FileManage;
import com.camelot.pmt.filemanage.model.FileManageGroup;
import com.camelot.pmt.filemanage.service.FileManageService;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.caserepertory.PageBean;
import com.camelot.pmt.platform.model.Menu;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 * FileManage 控制层

 *
 */
@Controller
@Api(value = "文件数据接口", description = "文件数据接口")
@RequestMapping(value = "/file/manager")
public class FileManageController {
    //日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FileManageService fileManageService;
    @ApiOperation(value = "添加文件功能", notes = "添加文件功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileTitle", value = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "fileAdress", value = "", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "groupId", value = "", required = false, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/addFileManager",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addFileManager(HttpServletRequest request, FileManage fileManage){//添加文件
        Boolean b=null;
        try {
            b = fileManageService.addFileManager(request,fileManage);//添加文件夹
            if(b){
                return ApiResponse.success();
            }
            return ApiResponse.error("修改异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }


    @ApiOperation(value = "删除文件功能", notes = "删除文件能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产出物id", required = true, paramType = "query", dataType = "String") })
    @RequestMapping(value = "/deldeteFileById",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject deldeteFileById(FileManage fileManage){//删除文件
        Boolean b=null;
        try {
            b = fileManageService.deleteFileById(fileManage);//删除文件
            if(b){
                return ApiResponse.success();
            }
            return ApiResponse.error("修改异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }
    @ApiOperation(value = "根据条件修改文件功能", notes = "根据条件修改文件功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产出物id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "groupId", value = "组id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "fileTitle", value = "文件标题", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "createUserId", value = "创建人", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "createTime", value = "创建时间", required = false, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/updateFileById",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateFileById(HttpServletRequest request, FileManage fileManage){//文件修改
        Boolean b=null;
        try {
            b = fileManageService.updateFileById(request,fileManage);//文件修改
            if(b){
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
            @ApiImplicitParam(name = "id", value = "产出物id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小", required = false, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/queryFileByGroupId",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject queryFileByGroupId(FileManageGroup fileManageGroup){//根据组id查询文件
        try {
            PageInfo<FileManage> fileManagePageInfo = fileManageService.queryFileByGroupId(fileManageGroup);//查询文件详细信息
            return ApiResponse.success(fileManagePageInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }
    @ApiOperation(value = "查询所有文件", notes = "查询所有文件")
    @RequestMapping(value = "/queryAllFile",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryAllFile(){
        ExecuteResult<List<FileManage>> result = new ExecuteResult<List<FileManage>>();
        try {
            result =fileManageService.queryAllFile();//查询文件详细信息
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.error();
        }
    }
}