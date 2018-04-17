package com.camelot.pmt.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.utils.Pager;
import com.camelot.pmt.project.model.Version;
import com.camelot.pmt.project.model.VersionVo;
import com.camelot.pmt.project.service.VersionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

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
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "form", dataType = "Long"),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "versionName", value = "版本名称", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "versionType", value = "版本类型", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "remarks", value = "备注", required = true, paramType = "form", dataType = "String")
    })
    @RequestMapping(value = "/api/version/createVersion", method = RequestMethod.POST)
    public JSONObject insertVersonInfo(Long projectId,String userId,VersionVo versionVo){
        return versionService.insertVersonInfo(projectId,userId,versionVo);
    }
    /**
     * @Description: 根据id逻辑删除version信息
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/13 19:22
     */
    @ApiOperation(value = "删除版本信息", notes = "删除版本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "versionId", value = "删除版本id", required = true, paramType = "form", dataType = "Long")
    })
    @RequestMapping(value = "/api/version/deleteVersionInfo", method = RequestMethod.POST)
    public JSONObject deleteVersonById(String userId,Long versionId){
        return versionService.deleteVersionInfoById(userId,versionId);
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
            @ApiImplicitParam(name = "id", value = "版本id", required = true, paramType = "form", dataType = "Long"),
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "form", dataType = "Long"),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "versionName", value = "版本名称", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "versionType", value = "版本类型", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "remarks", value = "备注", required = true, paramType = "form", dataType = "String")
    })
    @RequestMapping(value = "/api/version/updateVersion", method = RequestMethod.POST)
    public JSONObject updateVersonInfo(Long projectId,String userId,VersionVo versionVo){
        return versionService.updateVersonInfo(projectId,userId,versionVo);
    }
    /**
      * @Description:
      * @param: 根据versionId查询version信息
      * @return:
      * @author: xueyj
      * @date: 2018/4/13 18:55
      */
    @ApiOperation(value = "编辑--查询版本信息", notes = "编辑--查询版本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "versionId", value = "版本id", required = true, paramType = "query", dataType = "Long")
    })
   @RequestMapping(value = "/api/version/findVersionInfoById", method = RequestMethod.GET)
    public JSONObject findVersionInfoById(Long versionId){
        return versionService.findVersionInfoById(versionId);
    }
    /**
     * @Description: 根据项目id查询versionList
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/13 18:39
     */
   @ApiOperation(value = "查询版本列表信息", notes = "查询版本列表信息")
   @ApiImplicitParams({
           @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query", dataType = "Long")
   })
    @RequestMapping(value = "/api/version/findVerListByProId", method = RequestMethod.GET)
    public JSONObject findVerListByProId(Long projectId){
        return versionService.findVerListByProId(projectId);
    }

    /**
      * @Description: 根据项目id，分页查询versionList
      * @param: 
      * @return: 
      * @author: xueyj
      * @date: 2018/4/17 10:43
      */
    @ApiOperation(value = "分页查询版本列表信息", notes = "分页查询版本列表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每页数量", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query", dataType = "Long")
    })
    @RequestMapping(value = "/api/version/findVerListByPageAndProId", method = RequestMethod.GET)
    public JSONObject findVerListByPageAndProId(@ApiIgnore Pager<?> page, Long projectId){
        return versionService.findVerListByPageAndProId(page.getPage(),page.getRows(),projectId);
    }
    /**
     * @Description: 根据项目id、版本类型，查询versionList
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/13 18:39
     */
    @ApiOperation(value = "查询版本列表信息", notes = "查询版本列表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "versionType", value = "版本类型", required = true, paramType = "query", dataType = "Long")
    })
    @RequestMapping(value = "/api/version/findVerListByProIdAndVerType", method = RequestMethod.GET)
    public JSONObject findVerListByProIdAndVerType(Long projectId,String versionType){
        return versionService.findVerListByProIdAndVerType(projectId,versionType);
    }

    /**
      * @Description: 根据项目id、版本类型，分页查询versionList
      * @param: 
      * @return: 
      * @author: xueyj
      * @date: 2018/4/17 10:44
      */
    @ApiOperation(value = "分页查询版本列表信息", notes = "分页查询版本列表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每页数量", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "versionType", value = "版本类型", required = true, paramType = "query", dataType = "Long")
    })
    @RequestMapping(value = "/api/version/findVerListByPageAndProIdAndVerType", method = RequestMethod.GET)
    public JSONObject findVerListByPageAndProIdAndVerType(@ApiIgnore Pager<?> page,Long projectId,String versionType){
        return versionService.findVerListByPageAndProIdAndVerType(page.getPage(),page.getRows(),projectId,versionType);
    }
}
