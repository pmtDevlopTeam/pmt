package com.camelot.pmt.project.controller;

import javax.annotation.Resource;

import com.camelot.pmt.common.Pager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.project.model.VersionVo;
import com.camelot.pmt.project.service.VersionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Package: com.camelot.pmt.project.controller
 * @ClassName: VersionController
 * @Description:
 * @author: xueyj
 * @date: 2018-04-13 10:58
 */
@Slf4j
@RestController
@RequestMapping(value = "/platform/version")
@Api(value = "基础平台-版本控制接口", description = "基础平台-版本控制接口")
public class VersionController {
    @Resource
    VersionService versionService;

    /**
     * @Description: 添加版本信息
     * @param: projectId
     * @param: userId
     * @param: versionVo
     * @return: JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     * @author: xueyj
     * @date: 2018/4/13 18:31
     */
    @ApiOperation(value = "添加版本接口", notes = "添加单个版本")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "form", dataType = "Long"),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "versionName", value = "版本名称", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "versionType", value = "版本类型", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "remarks", value = "备注", required = true, paramType = "form", dataType = "String") })
    @RequestMapping(value = "/addVersion", method = RequestMethod.POST)
    public JSONObject addVersion(Long projectId, String userId, @ApiIgnore VersionVo versionVo) {
        return versionService.addVersion(projectId, userId, versionVo);
    }

    /**
     * @Description: 根据id逻辑删除version信息
     * @param: userId
     * @param: versionId
     * @return:
     * @author: xueyj
     * @date: 2018/4/13 19:22
     */
    @ApiOperation(value = "删除版本接口", notes = "删除版本接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "versionId", value = "删除版本id", required = true, paramType = "form", dataType = "Long") })
    @RequestMapping(value = "/deleteVersion", method = RequestMethod.POST)
    public JSONObject deleteVersonById(String userId, Long versionId) {
        return versionService.deleteVersionById(userId, versionId);
    }

    /**
     * @Description:
     * @param: 根据versionId查询version信息
     * @return:
     * @author: xueyj
     * @date: 2018/4/13 18:55
     */
    @ApiOperation(value = "根据版本id查询版本接口", notes = "根据版本id查询版本接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "versionId", value = "版本id", required = true, paramType = "query", dataType = "Long") })
    @RequestMapping(value = "/queryVersionInfoById", method = RequestMethod.GET)
    public JSONObject queryVersionInfoById(Long versionId) {
        return versionService.queryVersionInfoById(versionId);
    }

    /**
     * @Description: 根据id更新version
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/13 18:58
     */
    @ApiOperation(value = "根据版本id修改版本信息接口", notes = "根据版本id修改版本信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "版本id", required = true, paramType = "form", dataType = "Long"),
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "form", dataType = "Long"),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "versionName", value = "版本名称", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "versionType", value = "版本类型", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "remarks", value = "备注", required = true, paramType = "form", dataType = "String") })
    @RequestMapping(value = "/updateVersion", method = RequestMethod.POST)
    public JSONObject updateVersonInfo(Long projectId, String userId, @ApiIgnore VersionVo versionVo) {
        return versionService.updateVersonInfo(projectId, userId, versionVo);
    }

    /**
     * @Description: 根据项目id查询versionList
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/13 18:39
     */
    @ApiOperation(value = "根据项目id查询版本列表接口", notes = "根据项目id查询版本列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query", dataType = "Long") })
    @RequestMapping(value = "/queryVerListByProId", method = RequestMethod.GET)
    public JSONObject queryVerListByProId(Long projectId) {
        return versionService.queryVerListByProId(projectId);
    }

    /**
     * @Description: 根据项目id，分页查询versionList
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/17 10:43
     */
    @ApiOperation(value = "根据项目id，分页查询版本列表接口", notes = "根据项目id，分页查询版本列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每页数量", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query", dataType = "Long") })
    @RequestMapping(value = "/queryVerListByPageAndProId", method = RequestMethod.GET)
    public JSONObject queryVerListByPageAndProId(@ApiIgnore Pager<?> page, Long projectId) {
        return versionService.queryVerListByPageAndProId(page.getPage(), page.getRows(), projectId);
    }

    /**
     * @Description: 根据项目id、版本类型，查询versionList
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/13 18:39
     */
    @ApiOperation(value = "根据项目id，版本类型查询版本列表接口", notes = "根据项目id，版本类型查询版本列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "versionType", value = "版本类型", required = true, paramType = "query", dataType = "Long") })
    @RequestMapping(value = "/queryVerListByProIdAndVerType", method = RequestMethod.GET)
    public JSONObject queryVerListByProIdAndVerType(Long projectId, String versionType) {
        return versionService.queryVerListByProIdAndVerType(projectId, versionType);
    }

    /**
     * @Description: 根据项目id、版本类型，分页查询versionList
     * @param:
     * @return:
     * @author: xueyj
     * @date: 2018/4/17 10:44
     */
    @ApiOperation(value = "根据项目id，版本类型，分页查询版本列表接口", notes = "根据项目id，版本类型，分页查询版本列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每页数量", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "versionType", value = "版本类型", required = true, paramType = "query", dataType = "Long") })
    @RequestMapping(value = "/queryVerListByPageAndProIdAndVerType", method = RequestMethod.GET)
    public JSONObject queryVerListByPageAndProIdAndVerType(@ApiIgnore Pager<?> page, Long projectId,
            String versionType) {
        return versionService.queryVerListByPageAndProIdAndVerType(page.getPage(), page.getRows(), projectId,
                versionType);
    }
}
