package com.camelot.pmt.platform.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.APIStatus;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.common.DataGrid;
import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.common.Pager;
import com.camelot.pmt.platform.model.DictItem;
import com.camelot.pmt.platform.service.DictItemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/platform/dict")
@Api(value = "字典项管理接口", description = "字典项管理接口")
public class DictItemController {

	@Autowired
	DictItemService dictItemService;
	
	
	@ApiOperation(value="创建字典项接口", notes="创建单个字典项")
    @ApiImplicitParams({
        @ApiImplicitParam(
                name="dictItemName",value="字典项名称",required=true,paramType="form",dataType="String"),
        @ApiImplicitParam(
                name="dictItemCode",value="字典项编码",required=true,paramType="form",dataType="String"),
        @ApiImplicitParam(
                name="dictItemValue",value="字典项值",required=true,paramType="form",dataType="String"),
        @ApiImplicitParam(
                name="state",value="字典项状态",required=true,paramType="form",dataType="String"),
        @ApiImplicitParam(
                name="sortNum",value="排序号",required=true,paramType="form",dataType="Integer"),
        @ApiImplicitParam(
                name="dictIds",value="字典ID",required=true,paramType="form",dataType="String"),
    })
	@RequestMapping(value="/createDictItem", method=RequestMethod.POST)
	public JSONObject createDictItem(@ApiIgnore @RequestParam(required = true)String dictIds,@ApiIgnore DictItem dictItem) {
		ExecuteResult<DictItem> result = new ExecuteResult<DictItem>();
		try {
			//if非空
	    	if(dictItem.equals(null)){
	    		return ApiResponse.errorPara();
	    	}
	    	if (StringUtils.isEmpty(dictIds)||StringUtils.isEmpty(dictItem.getDictItemName())
	    		||StringUtils.isEmpty(dictItem.getDictItemCode())||StringUtils.isEmpty(dictItem.getDictItemValue()) )
	    	{
	    		return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
	    	//不为空调用接口查询
	    	 dictItem.setDictId(dictIds);
	    	 result = dictItemService.createDictItem(dictItem);
	    	 if(result.isSuccess()){
	    		//成功返回
	            return ApiResponse.success(result.getResultMessage());
	          }
	            return ApiResponse.error(result.getErrorMessage());
		}catch (Exception e) {
    		//异常
    		return ApiResponse.error();
		}
	}
	
	@ApiOperation(value="删除字典项接口", notes="删除单个字典项")
    @ApiImplicitParams({
        @ApiImplicitParam(
                name="dictItemId",value="字典dictItemId",required=true,paramType="query",dataType="String")
    })
	@RequestMapping(value="/deleteDictItemByDictItemId", method=RequestMethod.POST)
	public JSONObject deleteDictItemByDictItemId(@ApiIgnore String dictItemId) {
		ExecuteResult<String> result = new ExecuteResult<String>();
	   	try {
    		if(StringUtils.isEmpty(dictItemId)) {
    			return ApiResponse.jsonData(APIStatus.ERROR_400);
    		}
    		result = dictItemService.deleteDictItemByDictItemId(dictItemId);
    		if(result.isSuccess()) {
    			return ApiResponse.success(result.getResult());
    		}
    		return ApiResponse.error(result.getErrorMessage());
    	} catch (Exception e) {
    		return ApiResponse.error();
		}
	}
	

	@ApiOperation(value="修改字典项接口", notes="修改单个字典项")
    @ApiImplicitParams({
        @ApiImplicitParam(
                name="dictItemId",value="字典dictItemId",required=true,paramType="form",dataType="String"),
        @ApiImplicitParam(
                name="dictItemName",value="字典项名称",required=true,paramType="form",dataType="String"),
        @ApiImplicitParam(
                name="dictItemCode",value="字典项编码",required=true,paramType="form",dataType="String"),
        @ApiImplicitParam(
                name="dictItemValue",value="字典项值",required=true,paramType="form",dataType="String"),
        @ApiImplicitParam(
                name="state",value="字典项状态",required=true,paramType="form",dataType="String"),
        @ApiImplicitParam(
                name="sortNum",value="排序号",required=true,paramType="form",dataType="Integer"),
    })
	@RequestMapping(value="/modifyDictItemByDictItemId", method=RequestMethod.POST)
	public JSONObject modifyDictItemByDictItemId(@ApiIgnore DictItem dictItem) {
		ExecuteResult<DictItem> result = new ExecuteResult<DictItem>();
        try {

	    	if(StringUtils.isEmpty(dictItem.getDictItemId())){
		    		return ApiResponse.jsonData(APIStatus.ERROR_400);
	        }
            result = dictItemService.modifyDictItemByDictItemId(dictItem);
            if(result.isSuccess()){
                return ApiResponse.success(result.getResultMessage());
            }
            return ApiResponse.error(result.getErrorMessage());
        } catch (Exception e){
            return ApiResponse.error();
        }
	}
	
    /**
     * [通过字典项dictItemId查询单个字典项]
     * @param  dictItemId UUID
     * @return {"status": {"message": "请求处理成功.","code": 200}, "data": {DictItem}]
     */
	@ApiOperation(value="根据字典项dictItemId查询字典项接口", notes="查询单个字典项")
	@RequestMapping(value="/queryDictItemByDictItemId", method=RequestMethod.POST)
	public JSONObject queryDictItemByDictItemId(@ApiParam(value = "字典项dictItemId", required = true) @RequestParam(required = true) String dictItemId) {
		ExecuteResult<DictItem> result = new ExecuteResult<DictItem>();
		try {
            if(StringUtils.isEmpty(dictItemId)){
            	return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            result = dictItemService.queryDictItemByDictItemId(dictItemId);
            if(result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error(result.getErrorMessage());
        }catch (Exception e) {
            return ApiResponse.error();
        }
		
	}
    /**
     * [根据字典类型dictId查询字典项]
     * @param  dictId UUID
     * @return {"status": {"message": "请求处理成功.","code": 200}, "data": {List<DictItem>}]
     */
	@ApiOperation(value="根据字典类型dictId查询字典项接口 不分页", notes="查询字典项")
	@RequestMapping(value="/queryDictItemByDictId", method=RequestMethod.POST)
	public JSONObject queryDictItemByDictId(@ApiParam(value = "字典项dictId", required = true) @RequestParam(required = true) String dictId) {
		ExecuteResult<List<DictItem>> result = new ExecuteResult<List<DictItem>>();
		try {
            if(StringUtils.isEmpty(dictId)){
            	return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            result = dictItemService.queryDictItemByDictId(dictId);
            if(result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error(result.getErrorMessage());
        }catch (Exception e) {
            return ApiResponse.error();
        }
		
	}
	
	/**
     * [通过字典类型 分页查询字典项接口]
     * @param  dictId UUID page 页码,rows 每页数量
     * @return "data": {"total": 总数量,"rows":[查询的结果集],"status": {"code": 200,"message": "请求处理成功."}}
     */
    @ApiOperation(value="根据字典类型dictId分页查询字典项接口", notes="通过字典类型 分页查询字典项接口")
    @RequestMapping(value = "/queryDictItemByDictIdPage",method = RequestMethod.POST)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "int"),
    	@ApiImplicitParam(name = "rows", value = "每页数量", paramType = "query", dataType = "int")
    })
    public JSONObject queryDictItemByDictIdPage(@ApiParam(value = "字典项dictId", required = true) @RequestParam(required = true) String dictId,@ApiIgnore Pager page){
    	ExecuteResult<DataGrid<DictItem>> result = new ExecuteResult<DataGrid<DictItem>>();
    	try {
    		if(page == null) {
    			return ApiResponse.errorPara();
    		}
            if(StringUtils.isEmpty(dictId)){
            	return ApiResponse.errorPara();
            }
    		result = dictItemService.queryDictItemByDictIdPage(dictId,page);
    		if(result.isSuccess()) {
    			return ApiResponse.success(result.getResult());
    		}
    		return ApiResponse.error(result.getErrorMessage());
    	}catch (Exception e) {
    		return ApiResponse.error();
    	}
    	
    }
	
	@ApiOperation(value="查询全部字典项接口 不分页", notes="查询全部字典项")
	@RequestMapping(value="/queryAllDictItem", method=RequestMethod.POST)
	public JSONObject queryAllDictItem() {
        ExecuteResult<List<DictItem>> result = new ExecuteResult<List<DictItem>>();
        try {
            result = dictItemService.queryAllDictItem();
            if(result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error(result.getErrorMessage());
        }catch (Exception e) {
            return ApiResponse.error();
        }
        
	}
	/**
     *[分页查询全部字典项列表]
     * @param  page 页码,rows 每页数量
     * @return "data": {"total": 总数量,"rows":[查询的结果集],"status": {"code": 200,"message": "请求处理成功"}}
     */
    @ApiOperation(value="分页查询全部字典项列表", notes="分页查询全部字典项列表")
    @RequestMapping(value = "/queryAllDictItemPage",method = RequestMethod.GET)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", dataType = "int"),
    	@ApiImplicitParam(name = "rows", value = "每页数量", required = true, paramType = "query", dataType = "int")
    })
    public JSONObject queryAllDictItemPage(@ApiIgnore Pager page){
    	ExecuteResult<DataGrid<DictItem>> result = new ExecuteResult<DataGrid<DictItem>>();
    	try {
    		if(page == null) {
    			return ApiResponse.errorPara();
    		}
    		result = dictItemService.queryAllDictItemPage(page);
    		if(result.isSuccess()) {
    			return ApiResponse.success(result.getResult());
    		}
    		return ApiResponse.error(result.getErrorMessage());
    	}catch (Exception e) {
    		return ApiResponse.error();
    	}
    	
    }
    
}
