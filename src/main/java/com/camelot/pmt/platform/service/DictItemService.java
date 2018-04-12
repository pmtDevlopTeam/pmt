package com.camelot.pmt.platform.service;


import java.util.List;

import com.camelot.pmt.platform.common.DataGrid;
import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.common.Pager;
import com.camelot.pmt.platform.model.DictItem;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sqw
 * @since 2018-04-08
 */
public interface DictItemService {

	/**
	 * 增加一个字典项
	 * @param DictItem
	 * @return 
	 */
	ExecuteResult<DictItem> createDictItem(DictItem dictItem);
	/**
	 * 根据字典项id 删除一个字典项
	 * @param dictItemId
	 * @return
	 */
	ExecuteResult<String> deleteDictItemByDictItemId(String dictItemId);
	/**
	 * 根据字典类型id 删除字典项
	 * @param dictId
	 * @return
	 */
	ExecuteResult<String> deleteDictItemByDictId(String dictId);
	/**
	 * 根据字典项id 修改一个字典项
	 * @param DictItem
	 * @return
	 */
	ExecuteResult<DictItem> modifyDictItemByDictItemId(DictItem dictItem);
	/**
	 * 根据字典类型id 修改字典项
	 * @param DictItem
	 * @return
	 */
	ExecuteResult<String> modifyDictItemByDictId(DictItem dictItem);
    /**
     * 根据字典项id 查询一个字典项
     * @param dictItemId
     * @return
     */
	ExecuteResult<DictItem> queryDictItemByDictItemId(String dictItemId);
    /**
     * 根据字典类型id 查询字典项 不分页
     * @param dictItemId
     * @return
     */
	ExecuteResult<List<DictItem>> queryDictItemByDictId(String dictId);
	 /**
	  * 
	  *根据字典类型id 查询字典项  分页
	  * @param page dictId
	  * @return ExecuteResult<DataGrid<DictItem>>
	  */
	 ExecuteResult<DataGrid<DictItem>> queryDictItemByDictIdPage(String dictId,Pager page);
    /**
     * 查询全部字典项 不分页
     * @param dictId
     * @return
     */
	ExecuteResult<List<DictItem>> queryAllDictItem();
	 /**
	  * 
	  * 查询全部字典项 分页
	  * @param page
	  * @return ExecuteResult<DataGrid<DictItem>>
	  */
	 ExecuteResult<DataGrid<DictItem>> queryAllDictItemPage(Pager page);
	
}
