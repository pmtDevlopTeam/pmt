package com.camelot.pmt.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.Pager;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.project.mapper.ProjectMainMapper;
import com.camelot.pmt.project.model.ProjectMain;
import com.camelot.pmt.project.model.Version;
import com.camelot.pmt.project.model.VersionVo;
import com.camelot.pmt.project.service.VersionOperationLogService;
import com.camelot.pmt.project.service.VersionService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.camelot.pmt.project.controller
 * @ClassName: VersionController
 * @Description:
 * @author: xueyj
 * @date: 2018-04-13 10:58
 */
@RestController
@RequestMapping(value = "/platform/version")
@Api(value = "基础平台-版本控制接口", description = "基础平台-版本控制接口")
public class VersionController {
    @Resource
    VersionService versionService;
    @Autowired
    private ProjectMainMapper projectMainMapper;
    @Resource
    VersionOperationLogService versionOperationLogService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * @Description: 添加版本信息
     * @param: projectId
     * @param: userId
     * @param: versionVo
     * @return: JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     * @author: xueyj
     * @date: 2018/4/13 18:31
     */
    @ApiOperation(value = "添加版本接口", notes = "添加版本接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "form", dataType = "Long"),
            @ApiImplicitParam(name = "versionType", value = "版本类型", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "versionCode", value = "版本编号", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "versionName", value = "版本名称", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "versionRepositoryUrl", value = "版本仓库URL", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "versionRepositoryBranch", value = "版本仓库分支", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "versionRepositoryId", value = "版本仓库id", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "remarks", value = "备注", required = true, paramType = "form", dataType = "String") })
    @RequestMapping(value = "/addVersion", method = RequestMethod.POST)
    public JSONObject addVersion(Long projectId, @ApiIgnore VersionVo versionVo) {
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (user != null) {
                ProjectMain projectMain = projectMainMapper.queryByPrimaryKey(projectId);
                if (projectMain != null) {
                    boolean flag = versionService.queryVerListByProIdAndVerCode(projectId, versionVo.getVersionCode());
                    if (flag) {
                        flag = versionService.addVersion(projectId, user.getUserId(), versionVo);
                        if (flag) {
                            return ApiResponse.success();
                        }
                        return ApiResponse.error("添加异常");
                    }
                    return ApiResponse.error("该版本信息已存在，请重新添加版本信息");
                } else {
                    return ApiResponse.error("项目不存在！");
                }
            } else {
                return ApiResponse.error("用户未登录，请登录！");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
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
            @ApiImplicitParam(name = "versionId", value = "被删除版本id", required = true, paramType = "form", dataType = "Long") })
    @RequestMapping(value = "/deleteVersion", method = RequestMethod.POST)
    public JSONObject deleteVersonById(Long versionId) {
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (user != null) {
                boolean flag = versionOperationLogService.queryVersionLogByVersionId(versionId);
                if (flag) {
                    flag = versionService.updateVersionByIdAndParms(null, user.getUserId(), versionId);
                    if (flag) {
                        return ApiResponse.success();
                    }
                    return ApiResponse.error("删除异常");
                }
                return ApiResponse.error("该版本信息已被引用，无法删除！");
            } else {
                return ApiResponse.error("用户未登录，请登录！");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
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
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (user != null) {
                VersionVo versionVo = versionService.queryVersionInfoById(versionId);
                return ApiResponse.success(versionVo);
            } else {
                return ApiResponse.error("用户未登录，请登录！");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
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
            @ApiImplicitParam(name = "versionName", value = "版本名称", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "versionRepositoryUrl", value = "版本仓库URL", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "versionRepositoryBranch", value = "版本仓库分支", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "versionRepositoryId", value = "版本仓库id", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "remarks", value = "备注", required = true, paramType = "form", dataType = "String") })
    @RequestMapping(value = "/updateVersion", method = RequestMethod.POST)
    public JSONObject updateVersonInfo(@ApiIgnore Version version) {
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (user != null) {
                boolean flag = versionService.updateVersonInfo(user.getUserId(), version);
                if (flag) {
                    return ApiResponse.success();
                }
                return ApiResponse.error("更新异常");
            } else {
                return ApiResponse.error("用户未登录，请登录！");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /**
     * @Description: 根据id激活/关闭版本信息
     * @param: userId
     * @param: versionId
     * @return:
     * @author: xueyj
     * @date: 2018/4/13 19:22
     */
    @ApiOperation(value = "激活/关闭版本接口", notes = "激活/关闭版本接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "versionStatus", value = "版本状态", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "versionId", value = "版本id", required = true, paramType = "form", dataType = "Long") })
    @RequestMapping(value = "/updateVersionByIdAndParms", method = RequestMethod.POST)
    public JSONObject updateVersonByIdAndParms(String versionStatus, Long versionId) {
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (user != null) {
                boolean flag = versionService.updateVersionByIdAndParms(versionStatus, user.getUserId(), versionId);
                if (flag) {
                    return ApiResponse.success();
                }
                return ApiResponse.error("操作失败");
            } else {
                return ApiResponse.error("用户未登录，请登录！");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /**
     * @Description: 根据项目id，查询下一版本编号接口
     * @param: projectId
     * @return: JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     * @author: xueyj
     * @date: 2018/4/13 18:31
     */
    @ApiOperation(value = "根据项目id，查询下一版本编号接口", notes = "根据项目id，查询下一版本编号接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query", dataType = "String") })
    @RequestMapping(value = "/queryVersionCode", method = RequestMethod.GET)
    public JSONObject addVersion(Long projectId) {
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (user != null) {
                Map<String, String> versionMap = versionService.queryVerCodeByPorIdAndVerType(projectId);
                return ApiResponse.success(versionMap);
            } else {
                return ApiResponse.error("用户未登录，请登录！");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
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
            @ApiImplicitParam(name = "version", value = "版本编号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "versionName", value = "版本名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "versionType", value = "版本类型", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query", dataType = "Long") })
    @RequestMapping(value = "/queryVerListByProIdAndParms", method = RequestMethod.GET)
    public JSONObject queryVerListByProIdAndParms(Long projectId, @ApiIgnore VersionVo versionVo) {
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (user != null) {
                List<VersionVo> versionVoList = versionService.queryVerListByProIdAndParms(projectId, versionVo);
                return ApiResponse.success(versionVoList);
            } else {
                return ApiResponse.error("用户未登录，请登录！");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
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
            @ApiImplicitParam(name = "version", value = "版本编号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "versionName", value = "版本名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "versionType", value = "版本类型", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query", dataType = "Long") })
    @RequestMapping(value = "/queryVerListByPageAndProIdAndParm", method = RequestMethod.GET)
    public JSONObject queryVerListByPageAndProIdAndParm(int page, int rows, Long projectId,
            @ApiIgnore VersionVo versionVo) {
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (user != null) {
                PageInfo pageInfo = versionService.queryVerListByPageAndProId(page, rows, projectId, versionVo);
                return ApiResponse.success(pageInfo);
            } else {
                return ApiResponse.error("用户未登录，请登录！");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }
}