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
import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.model.Dict;
import com.camelot.pmt.platform.service.DictService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/platform/dict")
@Api(value = "字典管理接口", description = "字典管理接口")
public class DictController {

	
	@Autowired
	DictService dictService;
	
	
	@ApiOperation(value="创建字典接口", notes="创建单个字典")
    @ApiImplicitParams({
        @ApiImplicitParam(
                name="dictCode",value="字典编码",required=true,paramType="form",dataType="String"),
        @ApiImplicitParam(
                name="dictType",value="字典类型",required=true,paramType="form",dataType="String"),
        @ApiImplicitParam(
                name="dictName",value="字典名称",required=true,paramType="form",dataType="String"),
        @ApiImplicitParam(
                name="state",value="字典状态",required=true,paramType="form",dataType="String"),
    })
	@RequestMapping(value="/createDict", method=RequestMethod.POST)
	public JSONObject createDict(@ApiIgnore Dict dict) {
		ExecuteResult<Dict> result = new ExecuteResult<Dict>();
		try {
			//if非空
	    	if(dict == null){
	    		return ApiResponse.errorPara();
	    	}
	    	if (StringUtils.isEmpty(dict.getDictCode())||StringUtils.isEmpty(dict.getDictName())||StringUtils.isEmpty(dict.getDictType())){
	    		return ApiResponse.errorPara();
            }
	    	//不为空调用接口查询
	    	 result = dictService.createDict(dict);
	    	 if(result.isSuccess()){
	            return ApiResponse.success(result.getResultMessage());
	          }
	            return ApiResponse.error(result.getErrorMessage());
		}catch (Exception e) {
    		//异常
    		return ApiResponse.error();
		}
	}
	
	@ApiOperation(value="删除字典接口", notes="删除单个字典")
    @ApiImplicitParams({
        @ApiImplicitParam(
                name="dictId",value="字典dictId",required=true,paramType="query",dataType="String")
    })
	@RequestMapping(value="/deleteDictByDictId", method=RequestMethod.POST)
	public JSONObject deleteDictByDictId(@ApiIgnore String dictId) {
    	ExecuteResult<String> result = new ExecuteResult<String>();
	   	try {
    		if(StringUtils.isEmpty(dictId)) {
    			return ApiResponse.jsonData(APIStatus.ERROR_400);
    		}
    		result = dictService.deleteDictByDictId(dictId);
    		if(result.isSuccess()) {
    			return ApiResponse.success(result.getResult());
    		}
    		return ApiResponse.error(result.getErrorMessage());
    	} catch (Exception e) {
    		return ApiResponse.error();
		}
	   	
	}

	@ApiOperation(value="修改字典接口", notes="修改单个字典")
    @ApiImplicitParams({
    	@ApiImplicitParam(
                name="dictId",value="字典dictId",required=true,paramType="form",dataType="String"),
    	@ApiImplicitParam(
                name="dictCode",value="字典编码",required=true,paramType="form",dataType="String"),
        @ApiImplicitParam(
                name="dictType",value="字典类型",required=true,paramType="form",dataType="String"),
        @ApiImplicitParam(
                name="dictName",value="字典名称",required=true,paramType="form",dataType="String"),
        @ApiImplicitParam(
                name="state",value="字典状态",required=true,paramType="form",dataType="String"),
    })
	@RequestMapping(value="/modifyDictByDictId", method=RequestMethod.POST)
	public JSONObject modifyDictByDictId(@ApiIgnore Dict dict) {
		ExecuteResult<Dict> result = new ExecuteResult<Dict>();
        try {
            if(StringUtils.isEmpty(dict.getDictCode())||StringUtils.isEmpty(dict.getDictType())
            ||StringUtils.isEmpty(dict.getDictName()) || StringUtils.isEmpty(dict.getDictId()) ){
            	return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            result = dictService.modifyDictByDictId(dict);
            if(result.isSuccess()){
                return ApiResponse.success(result.getResultMessage());
            }
            return ApiResponse.error(result.getErrorMessage());
        } catch (Exception e){
            return ApiResponse.error();
        }
        
	}
	
    /**
     * [查询单个字典]
     * @param  dictId UUID
     * @return {"status": {"message": "请求处理成功.","code": 200}, "data": {Dict}]
     */
	@ApiOperation(value="根据字典dictId查询单个字典", notes="查询单个字典")
	@RequestMapping(value="/queryDictByDictId", method=RequestMethod.POST)
	public JSONObject queryDictByDictId(@ApiParam(value = "字典dictId", required = true) @RequestParam(required = true) String dictId) {
		ExecuteResult<Dict> result = new ExecuteResult<Dict>();
		try {
            if(StringUtils.isEmpty(dictId)){
            	return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            result = dictService.queryDictByDictId(dictId);
            if(result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error(result.getErrorMessage());
        }catch (Exception e) {
            return ApiResponse.error();
        }
		
	}
	
    /**
     * [查询所有字典]
     * @return {"status": {"message": "请求处理成功.","code": 200}, "data": {Dict列表}]
     */
	@ApiOperation(value="查询所有字典", notes="查询所有字典")
	@RequestMapping(value="/queryAllDict", method=RequestMethod.GET)
	public JSONObject queryAllDict() {
        ExecuteResult<List<Dict>> result = new ExecuteResult<List<Dict>>();
        try {
            result = dictService.queryAllDict();
            if(result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        }catch (Exception e) {
            return ApiResponse.error();
        }
        
	}
	
//    @ApiOperation(value="检查字典编码是否唯一", notes="检查字典编码是否唯一")
//    @ApiImplicitParams({
//            @ApiImplicitParam(
//                    name="dictCode",value="字典编码",required=true,paramType="form",dataType="String"),
//    })
//    @RequestMapping(value = "/checkDictCode",method = RequestMethod.POST)
//    public JSONObject checkDictCode(@ApiIgnore String dictCode) {
//    	ExecuteResult<Dict> result = new ExecuteResult<Dict>();
//		try {
//    		//判断非空
//	    	if(dictCode == null){
//	    		return ApiResponse.errorPara();
//	    	}
//	    	//不为空调用接口查询
//	    	 result = dictService.findDictCode(dictCode);
//	    	 if(result.getResult() == null) {
//	    		 return ApiResponse.success(result.getResultMessage());
//	    	 }
//	    	//成功返回
////	    	return ApiResponse.success(result.getResult());
//	    	return ApiResponse.success(result.getResultMessage());
//    	} catch (Exception e) {
//    		//异常
//    		return ApiResponse.error();
//		}
//	}
//    
//    @ApiOperation(value="检查字典名称是否唯一", notes="检查字典名称是否唯一")
//    @ApiImplicitParams({
//            @ApiImplicitParam(
//                    name="dictName",value="字典名称",required=true,paramType="form",dataType="String"),
//    })
//    @RequestMapping(value = "/checkDictName",method = RequestMethod.POST)
//    public JSONObject checkDictName(@ApiIgnore String dictName) {
//    	ExecuteResult<Dict> result = new ExecuteResult<Dict>();
//		try {
//    		//判断非空
//	    	if(dictName == null){
//	    		return ApiResponse.errorPara();
//	    	}
//	    	//不为空调用接口查询
//	    	 result = dictService.findDictName(dictName);
//	    	 if(result.getResult() == null) {
//	    		 return ApiResponse.success(result.getResultMessage());
//	    	 }
//	    	//成功返回
////	    	return ApiResponse.success(result.getResult());
//	    	return ApiResponse.success(result.getResultMessage());
//    	} catch (Exception e) {
//    		//异常
//    		return ApiResponse.error();
//		}
//	}
	
	
}
