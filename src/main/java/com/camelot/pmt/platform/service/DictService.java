package com.camelot.pmt.platform.service;


import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.Dict;

import java.util.List;

/**
 * <p>
 *  字典Service类
 * </p>
 *
 * @author sqw
 * @since 2018-04-08
 */
public interface DictService {
	
	/**
	 * 根据一个字典 增加一个字典
	 * 
	 * @param Dict dict
	 * @return ExecuteResult<Dict>
	 */
	ExecuteResult<Dict> createDict(Dict dict);
	/**
	 * 根据字典dictId 删除一个字典
	 * 
	 * @param String dictId
	 * @return ExecuteResult<String>
	 */
	ExecuteResult<String> deleteDictByDictId(String dictId);
	/**
	 * 根据字典dictId 修改一个字典
	 * 
	 * @param Dict dict
	 * @return ExecuteResult<Dict>
	 */
	ExecuteResult<Dict> modifyDictByDictId(Dict dict);
    /**
     * 根据字典dictId 查询一个字典
     * 
     * @param String dictId
     * @return ExecuteResult<Dict>
     */
	ExecuteResult<Dict> queryDictByDictId(String dictId);
    /**
     * 查询全部字典
     * 
     * @param 
     * @return ExecuteResult<List<Dict>>
     */
	ExecuteResult<List<Dict>> queryAllDict();
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
     * @return ExecuteResult<Dict>
     */
	ExecuteResult<Dict> checkDictCodeOrDictNameIsExist(Dict dict);

	
}
