package com.camelot.pmt.platform.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.Dict;
import com.camelot.pmt.platform.service.DictService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 *  字典Controller类
 * </p>
 *
 * @author sqw
 * @since 2018-04-08
 */
@RestController
@RequestMapping(value = "/platform/dict")
@Api(value = "字典管理接口", description = "字典管理接口")
public class DictController {

	
	@Autowired
	DictService dictService; 
	
    /**
     *  根据一个字典对象  创建一个字典
     *  
     *  @param Dict dict
     *  @return JSONObject  {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
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
	
    /**
     *  根据一个字典dictId  删除一个字典
     *  
     *  @param String dictId
     *  @return JSONObject  {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
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
    			return ApiResponse.errorPara();
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

    /**
     *  根据一个字典对象  修改一个字典
     *  
     *  @param Dict dict
     *  @return JSONObject  {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
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
            	return ApiResponse.errorPara();
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
     * 根据一个字典dictId  查询一个字典
     * 
     * @param  String dictId
     * @return {"status": {"code":xxx,"message":"xxx"}, "data": {xxx}]
     */
	@ApiOperation(value="根据字典dictId查询单个字典", notes="查询单个字典")
	@RequestMapping(value="/queryDictByDictId", method=RequestMethod.POST)
	public JSONObject queryDictByDictId(@ApiParam(value = "字典dictId", required = true) @RequestParam(required = true) String dictId) {
		ExecuteResult<Dict> result = new ExecuteResult<Dict>();
		try {
            if(StringUtils.isEmpty(dictId)){
            	return ApiResponse.errorPara();
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
     * 查询全部字典
     * 
     * @param  
     * @return {"status": {"code":xxx,"message":"xxx"}, "data": {xxx}]
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
	
    /**
     * 检查字典编码与字典名称是否存在
     * 
     * @param Dict dict
     * @return {"status":{"code":xxx,"message":"xxx"},"data":{xxx}
     */
  @ApiOperation(value="检查字典编码与字典名称是否存在", notes="检查字典编码与字典名称是否存在")
  @ApiImplicitParams({
          @ApiImplicitParam(
                  name="dictCode",value="字典编码",required=true,paramType="form",dataType="String"),
          @ApiImplicitParam(
                  name="dictName",value="字典名称",required=true,paramType="form",dataType="String"),
  })
  @RequestMapping(value = "/checkDictCodeOrDictNameIsExist",method = RequestMethod.POST)
  public JSONObject checkDictCodeOrDictNameIsExist(@ApiIgnore Dict dict) {
  	ExecuteResult<Dict> result = new ExecuteResult<Dict>();
		try {
  		//判断非空
	    	if(dict == null){
	    		return ApiResponse.errorPara();
	    	}
	    	//不为空调用接口查询
	    	 result = dictService.checkDictCodeOrDictNameIsExist(dict);
	    	 if(result.isSuccess()) {
	    		 return ApiResponse.success(result.getResultMessage());
	    	 }
	    	//成功返回
	    	return ApiResponse.error(result.getErrorMessage());
  	} catch (Exception e) {
  		//异常
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
