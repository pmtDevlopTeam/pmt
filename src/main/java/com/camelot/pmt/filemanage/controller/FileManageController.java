package com.camelot.pmt.filemanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.filemanage.model.FileManage;
import com.camelot.pmt.filemanage.model.FileManageGroup;
import com.camelot.pmt.filemanage.service.FileManageService;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.caserepertory.PageBean;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
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
    @Autowired
    private FileManageService fileManageService;
    @ApiOperation(value = "添加文件功能", notes = "添加文件功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileTitle", value = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "fileAdress", value = "", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "groupId", value = "", required = false, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/addfile",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addFileManager(HttpServletRequest request, FileManage fileManage){//添加文件
        ExecuteResult<String> result = new ExecuteResult<String>();
        try{
            //判断非空
            if(fileManage==null){
                return ApiResponse.error();
            }
            // 不为空调用接口添加
            result=fileManageService.addFileManager(request,fileManage);//添加文件夹
            // 成功返回
            return ApiResponse.success(result.getResult());
        }catch (Exception e){
            // 异常
            return ApiResponse.error();
        }
       // System.out.println("1111111");/*前台接收创建人createuserid，文件夹名称name ，文件标题filetitle，文件路径fileaddress*/
       // fileManage.setCreateUserId("111");
       // fileManage.setGroupId((long) 1);
       // fileManage.setFileTitle("my name is ");//测试数据*/
       // Boolean b=fileManageService.addFileManager(request,fileManage);//添加文件
    }


    @ApiOperation(value = "删除文件功能", notes = "删除文件能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产出物id", required = true, paramType = "query", dataType = "String") })
    @RequestMapping(value = "/deletefile",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject deldeteFileById(FileManage fileManage){
        ExecuteResult<String> result = new ExecuteResult<String>();
        try{
            if(fileManage==null){
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            result=fileManageService.deleteFileById(fileManage);//删除文件
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        }catch (Exception e){
            return ApiResponse.error();
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
    @RequestMapping(value = "/updatefile",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateFileByID(HttpServletRequest request, FileManage fileManage){

        ExecuteResult<String> result = new ExecuteResult<String>();
        try{
            if(fileManage==null){
                return  ApiResponse.errorPara();
            }
            result=fileManageService.updateFileById(request,fileManage);//文件修改
            return ApiResponse.success(result.getResult());
        }catch (Exception e){
            return ApiResponse.error();
        }
    }
    @ApiOperation(value = "根据条件查询文件功能", notes = "根据条件查询文件功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产出物id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小", required = false, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/selectfile",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject selectFileByGroupID(FileManageGroup fileManageGroup){
        ExecuteResult<PageInfo> result = new ExecuteResult<PageInfo>();
        try {
            result =fileManageService.selectFileByGroupID(fileManageGroup);//查询文件详细信息
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.error();
        }

    }
    @ApiOperation(value = "查询所有文件", notes = "查询所有文件")
    @RequestMapping(value = "/selectAllFile",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject selectAllFile(){
        ExecuteResult<List<FileManage>> result = new ExecuteResult<List<FileManage>>();
        try {
            result =fileManageService.selectAllFile();//查询文件详细信息
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.error();
        }
    }


}