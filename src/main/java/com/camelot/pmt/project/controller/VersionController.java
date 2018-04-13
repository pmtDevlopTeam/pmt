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
 * @Description: TODO
 * @author: xueyj
 * @date: 2018-04-13 10:58
 */
@RestController
@Api(value = "项目管理-版本控制模块", description = "项目管理-版本控制模块的控制器类")
public class VersionController {
    @Resource
    VersionService versionService;
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
}
