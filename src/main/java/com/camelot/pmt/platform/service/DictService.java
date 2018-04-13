package com.camelot.pmt.platform.service;


import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.model.Dict;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sqw
 * @since 2018-04-08
 */
public interface DictService {
	
	/**
	 * 增加一个字典
	 * @param Dict
	 * @return 
	 */
	ExecuteResult<Dict> createDict(Dict dict);
	/**
	 * 根据字典id 删除一个字典
	 * @param dictId
	 * @return
	 */
	ExecuteResult<String> deleteDictByDictId(String dictId);
	/**
	 * 根据字典id 修改一个字典
	 * @param Dict
	 * @return
	 */
	ExecuteResult<Dict> modifyDictByDictId(Dict dict);
    /**
     * 根据字典id 查询一个字典
     * @param dictId
     * @return
     */
	ExecuteResult<Dict> queryDictByDictId(String dictId);
    /**
     * 查询全部字典
     * @param dictId
     * @return
     */
	ExecuteResult<List<Dict>> queryAllDict();
    /**
     *[检查字典编码]
     * @return Dict
     * @author
     */
	ExecuteResult<Dict> findDictCode(String dictCode);
    /**
     *[检查字典名称]
     * @return Dict
     * @author
     */
	ExecuteResult<Dict> findDictName(String dictName);
	
}
