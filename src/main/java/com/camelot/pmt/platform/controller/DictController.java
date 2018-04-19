package com.camelot.pmt.platform.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.Dict;
import com.camelot.pmt.platform.model.Menu;
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
@Api(value = "基础平台-字典管理接口", description = "基础平台-字典管理接口")
public class DictController {

    //日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	DictService dictService; 
	
    /**
     *  根据一个字典对象  创建一个字典
     *  
     *  @param Dict dict
     *  @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
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
	@RequestMapping(value="/addDict", method=RequestMethod.POST)
	public JSONObject addDict(@ApiIgnore Dict dict) {
		boolean flag = false;
		try {
			//if非空
	    	if(dict == null){
	    		return ApiResponse.errorPara();
	    	}
	    	if (StringUtils.isEmpty(dict.getDictCode())||StringUtils.isEmpty(dict.getDictName())||StringUtils.isEmpty(dict.getDictType())){
	    		return ApiResponse.errorPara();
            }
	    	//不为空调用接口查询
	    	flag = dictService.addDict(dict);
            if(flag){
                return ApiResponse.success();
            }
            return ApiResponse.error("添加异常");
		}catch (Exception e) {
			logger.error(e.getMessage());
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
		boolean flag = false;
	   	try {
    		if(StringUtils.isEmpty(dictId)) {
    			return ApiResponse.errorPara();
    		}
    		flag = dictService.deleteDictByDictId(dictId);
            if(flag){
                return ApiResponse.success();
            }
            return ApiResponse.error("删除异常");
    	} catch (Exception e) {
    		logger.error(e.getMessage());
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
	@RequestMapping(value="/updateDictByDictId", method=RequestMethod.POST)
	public JSONObject updateDictByDictId(@ApiIgnore Dict dict) {
		boolean flag = false;
        try {
            if(StringUtils.isEmpty(dict.getDictCode())||StringUtils.isEmpty(dict.getDictType())
            ||StringUtils.isEmpty(dict.getDictName()) || StringUtils.isEmpty(dict.getDictId()) ){
            	return ApiResponse.errorPara();
            }
            flag = dictService.updateDictByDictId(dict);
            if(flag){
                return ApiResponse.success();
            }
            return ApiResponse.error("修改异常");
        } catch (Exception e){
        	logger.error(e.getMessage());
            return ApiResponse.error();
        }
        
	}

    /**
     * 根据一个字典dictId  查询一个字典
     * 
     * @param  String dictId
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
	@ApiOperation(value="查询字典接口", notes="查询单个字典")
	@RequestMapping(value="/queryDictByDictId", method=RequestMethod.POST)
	public JSONObject queryDictByDictId(@ApiParam(value = "字典dictId", required = true) @RequestParam(required = true) String dictId) {
		try {
            if(StringUtils.isEmpty(dictId)){
            	return ApiResponse.errorPara();
            }
            	Dict dict = dictService.queryDictByDictId(dictId);
            	return ApiResponse.success(dict);
        }catch (Exception e) {
        	logger.error(e.getMessage());
            return ApiResponse.error();
        }
	}
	
    /**
     * 查询全部字典
     * 
     * @param  
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
	@ApiOperation(value="查询所有字典", notes="查询所有字典")
	@RequestMapping(value="/selectDictListAll", method=RequestMethod.GET)
	public JSONObject selectDictListAll() {
        try {
        	List<Dict> list = dictService.selectDictListAll();
            return ApiResponse.success(list);
        }catch (Exception e) {
        	logger.error(e.getMessage());
            return ApiResponse.error();
        }
        
	}
	
//    /**
//     * 检查字典编码与字典名称是否存在
//     * 
//     * @param Dict dict
//     * @return {"status":{"code":xxx,"message":"xxx"},"data":{xxx}
//     */
//  @ApiOperation(value="检查字典编码与字典名称是否存在", notes="检查字典编码与字典名称是否存在")
//  @ApiImplicitParams({
//          @ApiImplicitParam(
//                  name="dictCode",value="字典编码",required=true,paramType="form",dataType="String"),
//          @ApiImplicitParam(
//                  name="dictName",value="字典名称",required=true,paramType="form",dataType="String"),
//  })
//  @RequestMapping(value = "/checkDictCodeOrDictNameIsExist",method = RequestMethod.POST)
//  public JSONObject checkDictCodeOrDictNameIsExist(@ApiIgnore Dict dict) {
//  	ExecuteResult<String> result = new ExecuteResult<String>();
//		try {
//  		//判断非空
//	    	if(dict == null){
//	    		return ApiResponse.errorPara();
//	    	}
//	    	//不为空调用接口查询
//	    	 result = dictService.checkDictCodeOrDictNameIsExist(dict);
//	    	//成功返回
//	    	 if(result.isSuccess()) {
//	    		 return ApiResponse.success(result.getResult());
//	    	 }
//	    	return ApiResponse.error();
//  	} catch (Exception e) {
//  		//异常
//  		return ApiResponse.error();
//		}
//	}
	

	
	
}
