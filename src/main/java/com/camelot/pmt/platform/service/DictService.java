package com.camelot.pmt.platform.service;


import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.Dict;
import com.camelot.pmt.platform.model.Org;

import java.util.List;

/**
 * 字典服务接口类
 *
 * @author pmt
 * @since 2018-04-08
 */
public interface DictService {
	
	/**
	 * 根据一个字典 增加一个字典
	 * 
	 * @param Dict dict
	 * @return boolean
	 */
	boolean addDict(Dict dict);
	/**
	 * 根据字典dictId 删除一个字典
	 * 
	 * @param String dictId
	 * @return boolean
	 */
	boolean deleteDictByDictId(String dictId);
	/**
	 * 根据字典dictId 修改一个字典
	 * 
	 * @param Dict dict
	 * @return boolean
	 */
	boolean updateDictByDictId(Dict dict);
	/** 
	 * 修改字典的状态
	 * @param Dict dict
	 * @return JSONObject
	 * 
	 **/
	boolean updateDictByDictIdAndState(Dict dict);
	/** 
	 * 修改字典的状态和字典项状态
	 * @param Dict dict
	 * @return JSONObject
	 * 
	 **/
	boolean updateDictOrDictItemByDictIdAndState(Dict dict);
    /**
     * 根据字典dictId 查询一个字典
     * 
     * @param String dictId
     * @return Dict
     */
	Dict queryDictByDictId(String dictId);
    /**
     * 查询全部字典
     * 
     * @param 
     * @return List<Dict>
     */
	List<Dict> queryDictListAll();
//    /**
//     *检查字典编码是否存在
//     *
//     * @param String dictCode
//     * @return ExecuteResult<Dict>
//     */
//	ExecuteResult<Dict> checkDictCodeIsExist(String dictCode);
//    /**
//     *检查字典名称是否存在
//     *
//     * @param String dictName
//     * @return ExecuteResult<Dict>
//     */
//	ExecuteResult<Dict> checkDictNameIsExist(String dictName);
    /**
     * 检查字典编码与字典名称是否存在 
     *
     * @param Dict dict
     * @return boolean
     */
	ExecuteResult<String> checkDictCodeOrDictNameIsExist(Dict dict);
    /**
     * 检查字典编码与字典名称是否存在  updaete
     *
     * @param Dict dict
     * @return boolean
     */
	ExecuteResult<String> checkDictCodeOrDictNameIsExistUpdate(Dict dict);

	
}
