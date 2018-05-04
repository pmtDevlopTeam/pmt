package com.camelot.pmt.platform.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.DataGrid;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.common.Pager;
import com.camelot.pmt.platform.model.Dict;
import com.camelot.pmt.platform.model.DictItem;
import com.camelot.pmt.platform.model.Menu;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.service.DictItemService;
import com.camelot.pmt.platform.service.DictService;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * 字典项Controller类
 * </p>
 *
 * @author sqw
 * @since 2018-04-08
 */
@RestController
@RequestMapping(value = "/platform/dict")
@Api(value = "基础平台-字典项管理接口", description = "基础平台-字典项管理接口")
public class DictItemController {

    // 日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DictItemService dictItemService;

    @Autowired
    DictService dictService;

    /**
     * 根据一个字典项对象 创建一个字典项
     * 
     * @param String
     *            dictIds,DictItem dictItem
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "创建字典项接口", notes = "创建单个字典项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dictItemName", value = "字典项名称", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "dictItemCode", value = "字典项编码", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "dictItemValue", value = "字典项值", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "state", value = "字典项状态", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "sortNum", value = "排序号", required = true, paramType = "form", dataType = "Integer"),
            @ApiImplicitParam(name = "dictId", value = "字典ID", required = true, paramType = "form", dataType = "String"), })
    @RequestMapping(value = "/addDictItem", method = RequestMethod.POST)
    public JSONObject addDictItem(@ApiIgnore DictItem dictItem) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        boolean flag = false;
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (StringUtils.isEmpty(user.getUserId())) {
                ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            dictItem.setCreateUserId(user.getUserId());
            dictItem.setModifyUserId(user.getUserId());
            if (StringUtils.isEmpty(dictItem.getDictId()) || StringUtils.isEmpty(dictItem.getDictItemName())
                    || StringUtils.isEmpty(dictItem.getDictItemCode())
                    || StringUtils.isEmpty(dictItem.getDictItemValue())) {
                return ApiResponse.errorPara();
            }
            // 检查字典是否存在
            Dict dict = dictService.queryDictByDictId(dictItem.getDictId());
            if (dict == null) {
                result.setResult("字典为null!");
                return ApiResponse.success(result.getResult());
            }
            // 检查字典项编码跟字典项名称是否唯一
            result = dictItemService.checkDictItemCodeOrDictItemNameIsExist(dictItem);
            // 如果字典项编码跟字典项名称唯一
            if (result.getResultMessage() != null) {
                flag = dictItemService.addDictItem(dictItem);
                if (flag) {
                    return ApiResponse.success();
                }
                return ApiResponse.error("添加异常");
            }
            return ApiResponse.success(result.getResult());
        } catch (Exception e) {
            // 异常
            return ApiResponse.error();
        }
    }

    /**
     * 根据一个字典项dictItemId 删除一个字典项
     * 
     * @param String
     *            dictItemId
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "删除字典项接口", notes = "删除单个字典项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dictItemId", value = "字典dictItemId", required = true, paramType = "query", dataType = "String") })
    @RequestMapping(value = "/deleteDictItemByDictItemId", method = RequestMethod.POST)
    public JSONObject deleteDictItemByDictItemId(@ApiIgnore DictItem dictItem) {
        boolean flag = false;
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (StringUtils.isEmpty(user.getUserId())) {
                ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            if (StringUtils.isEmpty(dictItem.getDictItemId())) {
                return ApiResponse.errorPara();
            }
            dictItem.setModifyUserId(user.getUserId());
            flag = dictItemService.deleteDictItemByDictItemId(dictItem);
            if (flag) {
                return ApiResponse.success();
            }
            return ApiResponse.error("删除异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.error();
        }
    }

    /**
     * 根据一个字典项对象 修改一个字典项
     * 
     * @param DictItem
     *            dictItem
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "修改字典项接口", notes = "修改单个字典项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dictItemId", value = "字典dictItemId", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "dictItemName", value = "字典项名称", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "dictItemCode", value = "字典项编码", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "dictItemValue", value = "字典项值", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "state", value = "字典项状态", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "sortNum", value = "排序号", required = true, paramType = "form", dataType = "Integer"), })
    @RequestMapping(value = "/updateDictItemByDictItemId", method = RequestMethod.POST)
    public JSONObject updateDictItemByDictItemId(@ApiIgnore DictItem dictItem) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        boolean flag = false;
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (StringUtils.isEmpty(user.getUserId())) {
                ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            if (StringUtils.isEmpty(dictItem.getDictItemId())) {
                return ApiResponse.errorPara();
            }
            dictItem.setModifyUserId(user.getUserId());
            // 检查字典项编码跟字典项名称是否唯一
            result = dictItemService.checkDictItemCodeOrDictItemNameIsExistUpdate(dictItem);
            // 如果字典项编码跟字典项名称唯一
            if (result.getResultMessage() != null) {
                flag = dictItemService.updateDictItemByDictItemId(dictItem);
                if (flag) {
                    return ApiResponse.success();
                }
                return ApiResponse.error("修改异常");
            }
            return ApiResponse.success(result.getResult());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.error();
        }
    }

    /**
     * 修改字典项的状态
     * 
     * @param dictItemId
     *            state
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     * 
     **/
    @ApiOperation(value = "修改字典项状态接口", notes = "修改字典项状态接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dictItemId", value = "字典项id", required = true, paramType = "form", dataType = "string"),
            @ApiImplicitParam(name = "state", value = "字典项状态  0（默认）启用 1 停用 2 锁定", required = false, paramType = "form", dataType = "string") })
    @RequestMapping(value = "/updateDictItemByDictItemIdAndState", method = RequestMethod.POST)
    public JSONObject updateDictItemByDictItemIdAndState(@ApiIgnore DictItem dictItem) {
        boolean flag = false;
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (StringUtils.isEmpty(user.getUserId())) {
                ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            if (StringUtils.isEmpty(dictItem.getDictItemId()) || StringUtils.isEmpty(dictItem.getState())) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            dictItem.setModifyUserId(user.getUserId());
            flag = dictItemService.updateDictItemByDictItemIdAndState(dictItem);
            if (flag) {
                return ApiResponse.success();
            }
            return ApiResponse.error("修改字典项状态异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.error();
        }

    }

    /**
     * 根据一个字典项dictItemId 查询一个字典项
     * 
     * @param String
     *            dictItemId
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "查询字典项接口", notes = "查询单个字典项")
    @RequestMapping(value = "/queryDictItemByDictItemId", method = RequestMethod.POST)
    public JSONObject queryDictItemByDictItemId(
            @ApiParam(value = "字典项dictItemId", required = true) @RequestParam(required = true) String dictItemId) {
        try {
            if (StringUtils.isEmpty(dictItemId)) {
                return ApiResponse.errorPara();
            }
            DictItem dictItem = dictItemService.queryDictItemByDictItemId(dictItemId);
            return ApiResponse.success(dictItem);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.error();
        }

    }

    /**
     * 根据字典dictId 查询字典项
     * 
     * @param String
     *            dictId
     * @return {"status":{"code":xxx,"message":"xxx"},"data":{xxx}]
     */
    @ApiOperation(value = "根据字典dictId查询字典项接口 ", notes = "查询字典项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dictId", value = "字典id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "currentPage", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, paramType = "query", dataType = "int"), })
    @RequestMapping(value = "/queryDictItemListByDictId", method = RequestMethod.POST)
    public JSONObject queryDictItemListByDictId(@ApiIgnore @RequestParam(required = true) String dictId,
            @ApiIgnore DictItem dictItem, @RequestParam(defaultValue = "1") Integer pageSize,
            @RequestParam(defaultValue = "10") Integer currentPage) {
        try {
            if (StringUtils.isEmpty(dictId)) {
                return ApiResponse.errorPara();
            }
            List<DictItem> list = dictItemService.queryDictItemListByDictId(dictId, pageSize, currentPage);
            PageInfo<DictItem> result = new PageInfo<DictItem>(list);
            return ApiResponse.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.error();
        }

    }

    /**
     * 查询全部字典项
     * 
     * @param
     * @return {"status": {"code":xxx,"message":"xxx"}, "data": {xxx}]
     */
    @ApiOperation(value = "查询全部字典项接口", notes = "查询全部字典项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, paramType = "query", dataType = "int"), })
    @RequestMapping(value = "/queryDictItemListAll", method = RequestMethod.POST)
    public JSONObject queryDictItemListAll(@ApiIgnore DictItem dictItem,
            @RequestParam(defaultValue = "1") Integer pageSize,
            @RequestParam(defaultValue = "10") Integer currentPage) {
        try {
            List<DictItem> list = dictItemService.queryDictItemListAll(pageSize, currentPage);
            PageInfo<DictItem> result = new PageInfo<DictItem>(list);
            return ApiResponse.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.error();
        }

    }

    /**
     * 查询全部字典项 用户
     * 
     * @param
     * @return {"status": {"code":xxx,"message":"xxx"}, "data": {xxx}]
     */
    @ApiOperation(value = "查询全部字典项接口  用户", notes = "查询全部字典项  用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, paramType = "query", dataType = "int"), })
    @RequestMapping(value = "/queryDictItemOrUserListAll", method = RequestMethod.POST)
    public JSONObject queryDictItemOrUserListAll(@ApiIgnore DictItem dictItem,
            @RequestParam(defaultValue = "1") Integer pageSize,
            @RequestParam(defaultValue = "10") Integer currentPage) {
        try {
            List<DictItem> list = dictItemService.queryDictItemOrUserListAll(pageSize, currentPage);
            PageInfo<DictItem> result = new PageInfo<DictItem>(list);
            return ApiResponse.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.error();
        }

    }

}
