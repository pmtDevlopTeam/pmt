package com.camelot.pmt.filemanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.filemanage.model.FileManageGroup;
import com.camelot.pmt.filemanage.service.FileManageGroupService;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;
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

 *1zz

 * FileManageGroup 控制层
 *
 *
 */
@Controller



@Api(value = "产出物接口", description = "产出物接口")

@RequestMapping(value = "/file/manager/group")
public class FileManageGroupController {
    @Autowired
    private FileManageGroupService fileManageGroupService;
    @ApiOperation(value = "添加文件夹功能", notes = "添加文件夹功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "parentId", value = "父级id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "projectId", value = "项目id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "文件夹名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "fdescribe", value = "文件夹描述", required = true, paramType = "query", dataType = "String"),
    })
    @RequestMapping(value="/addfilegroup",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addFileManagerGroup(HttpServletRequest request, FileManageGroup fileManageGroup)
    {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try{
            //判断非空
            if(fileManageGroup==null){
                return ApiResponse.error();
            }
            // 不为空调用接口添加
            result=fileManageGroupService.addFileManagerGroup(request,fileManageGroup);//添加文件夹
            // 成功返回
            return ApiResponse.success(result.getResult());
        }catch (Exception e){
            // 异常
            return ApiResponse.error();
        }

    }
    @ApiOperation(value = "删除文件夹功能", notes = "删除文件夹功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文件夹id", required = true, paramType = "query", dataType = "String") })
    @RequestMapping(value="/deletefilegroup",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject deleteFileGroup(FileManageGroup fileManageGroup){
        ExecuteResult<String> result = new ExecuteResult<String>();
        try{
            if(fileManageGroup==null){
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            result=fileManageGroupService.deleteFileGroup(fileManageGroup);//删除文件夹
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        }catch (Exception e){
            return ApiResponse.error();
        }
    }
    @ApiOperation(value = "根据条件修改文件夹功能", notes = "根据条件修改文件夹功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isFile", value = "是否是文件", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "createUserId", value = "创建人id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "parentId", value = "父级id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "projectId", value = "项目id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "文件夹名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "fdescribe", value = "文件夹描述", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "id", value = "文件夹id", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value="/updatefilegroup",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateFileGroupById(HttpServletRequest request,FileManageGroup fileManageGroup){
        ExecuteResult<String> result = new ExecuteResult<String>();
        try{
            if(fileManageGroup==null){
                return  ApiResponse.errorPara();
            }
            result=fileManageGroupService.updateFileGroupById(request,fileManageGroup);//修改文件夹
            return ApiResponse.success(result.getResult());
        }catch (Exception e){
            return ApiResponse.error();
        }
    }

    @ApiOperation(value = "根据条件查询文件夹功能", notes = "根据条件查询文件夹功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "父级id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "projectId", value = "项目id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "id", value = "文件夹id", required = false, paramType = "query", dataType = "String")
    })
   @RequestMapping(value="/selectFileGroup",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject selectFileGroupByParentID(FileManageGroup fileManageGroup){//根据parentId查询根据projectId查询
        ExecuteResult<List<FileManageGroup>> result = new ExecuteResult<List<FileManageGroup>>();
        try {
            result = fileManageGroupService.selectFileGroup(fileManageGroup);//根据条件查询文件夹
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.error();
        }
    }
    /*@RequestMapping("/batechDeleteFileGroupByIds")
public JSONObject batechDeleteFileGroupByIds(List<Long> ids){
        ExecuteResult<String> result = new ExecuteResult<String>();
        try{
            FileManageGroup group = new FileManageGroup();
            for (Long id:
                    ids) {
                group.setId(id);
                result=   fileManageGroupService.deleteFileGroup(group);
            }
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        }catch (Exception e){
            return ApiResponse.error();
        }





    }*/
   /* @ApiOperation(value = "根据条件查询文件夹功能", notes = "根据条件查询文件夹功能")
    @RequestMapping(value = "/selectTree",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject selectTree(FileManageGroup fileManageGroup){
      List <FileManageGroup>  treeList=fileManageGroupService.selectTree(fileManageGroup);
        return null;
    }*/

            }

