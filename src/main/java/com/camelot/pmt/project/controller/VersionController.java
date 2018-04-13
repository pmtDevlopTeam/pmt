package com.camelot.pmt.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.project.model.Version;
import com.camelot.pmt.project.service.VersionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Package: com.camelot.pmt.project.controller
 * @ClassName: VersionController
 * @Description:
 * @author: xueyj
 * @date: 2018-04-13 10:58
 */
@RestController
@Api(value = "项目管理-版本控制模块", description = "项目管理-版本控制模块的控制器类")
public class VersionController {
    @Resource
    VersionService versionService;

    /**
      * @Description: 添加版本信息
      * @param:
      * @return:
      * @author: xueyj
      * @date: 2018/4/13 18:31
      */
    @ApiOperation(value = "添加版本", notes = "添加版本")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "versionName", value = "版本名称", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "versionType", value = "版本类型", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "remarks", value = "备注", required = true, paramType = "form", dataType = "String")
    })
    @RequestMapping(value = "version/addVersion", method = RequestMethod.POST)
    public JSONObject insertVersonInfo(Version version){
        Long projectId = null;
        String userId ="";
        return versionService.insertVersonInfo(projectId,userId,version);
    }

    /**
      * @Description: 根据项目id查询versionList
      * @param:
      * @return:
      * @author: xueyj
      * @date: 2018/4/13 18:39
      */
    @ApiOperation(value = "查询版本列表信息", notes = "查询版本列表信息")
    @RequestMapping(value = "version/queryVersionList", method = RequestMethod.GET)
    public JSONObject queryVersonListByProId(){
        Long projectId = null;
        return versionService.getVersionListInfo(projectId);
    }
    /**
      * @Description:
      * @param: 根据versionId查询version信息
      * @return:
      * @author: xueyj
      * @date: 2018/4/13 18:55
      */
    @ApiOperation(value = "查询版本信息", notes = "查询版本信息")
    @RequestMapping(value = "version/queryVersionList", method = RequestMethod.POST)
    public JSONObject queryVersonById(Version version){
        return versionService.getVersionInfoById(version.getId());
    }
    /**
      * @Description: 根据id更新version
      * @param:
      * @return:
      * @author: xueyj
      * @date: 2018/4/13 18:58
      */
    @ApiOperation(value = "修改版本信息", notes = "修改版本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "versionName", value = "版本名称", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "versionType", value = "版本类型", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "remarks", value = "备注", required = true, paramType = "form", dataType = "String")
    })
    @RequestMapping(value = "version/updateVersion", method = RequestMethod.POST)
    public JSONObject updateVersonInfo(String userId,Long versionId){
        return versionService.updateVersonInfo(userId,versionId);
    }
    /**
      * @Description: 根据id逻辑删除version信息
      * @param: 
      * @return: 
      * @author: xueyj
      * @date: 2018/4/13 19:22
      */
    @ApiOperation(value = "删除版本信息", notes = "删除版本信息")
    @RequestMapping(value = "version/deleteVersionInfo", method = RequestMethod.POST)
    public JSONObject deleteVersonById(String userId,Version version){
        return versionService.deleteVersionInfoById(userId,version.getId());
    }
}
