package com.camelot.pmt.platform.service;


import java.util.List;

import com.camelot.pmt.common.DataGrid;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.common.Pager;
import com.camelot.pmt.platform.model.DictItem;

/**
 * <p>
 *  字典项Service类
 * </p>
 *
 * @author sqw
 * @since 2018-04-08
 */
public interface DictItemService {

	/**
	 * 根据一个字典项  增加一个字典项 
	 * 
	 * @param DictItem dictItem
	 * @return ExecuteResult<DictItem>
	 */
	ExecuteResult<DictItem> createDictItem(DictItem dictItem);
	/**
	 * 根据字典项dictItemId 删除一个字典项
	 * 
	 * @param String dictItemId
	 * @return ExecuteResult<String>
	 */
	ExecuteResult<String> deleteDictItemByDictItemId(String dictItemId);
	/**
	 * 根据字典项dictItemId 修改一个字典项
	 * 
	 * @param DictItem dictItem
	 * @return ExecuteResult<DictItem>
	 */
	ExecuteResult<DictItem> modifyDictItemByDictItemId(DictItem dictItem);
	/**
	 * 根据字典类型dictId 修改字典项
	 * 
	 * @param DictItem dictItem
	 * @return ExecuteResult<String>
	 */
	ExecuteResult<String> modifyDictItemByDictId(DictItem dictItem);
    /**
     * 根据字典项dictItemId 查询一个字典项
     * 
     * @param String dictItemId
     * @return ExecuteResult<DictItem>
     */
	ExecuteResult<DictItem> queryDictItemByDictItemId(String dictItemId);
    /**
     * 根据字典dictId 查询字典项 不分页
     * 
     * @param String dictId
     * @return ExecuteResult<List<DictItem>>
     */
	ExecuteResult<List<DictItem>> queryListDictItemByDictId(String dictId);
	 /**
	  *根据字典dictId 查询字典项  分页
	  *
	  * @param String dictId,Pager page
	  * @return ExecuteResult<DataGrid<DictItem>>
	  */
	 ExecuteResult<DataGrid<DictItem>> queryListDictItemByDictIdPage(String dictId,Pager page);
    /**
     * 查询全部字典项 不分页
     * 
     * @param 
     * @return ExecuteResult<List<DictItem>>
     */
	ExecuteResult<List<DictItem>> queryAllDictItem();
	 /**
	  * 
	  * 查询全部字典项 分页
	  * 
	  * @param Pager page
	  * @return ExecuteResult<DataGrid<DictItem>>
	  */
	 ExecuteResult<DataGrid<DictItem>> queryAllDictItemPage(Pager page);
	 /**
	  * 检查字典项编码与字典项名称是否存在
	  * 
	  * @param DictItem dictItem
	  * @return ExecuteResult<DictItem>
	  */
	 ExecuteResult<DictItem> checkDictItemCodeOrDictItemNameIsExist(DictItem dictItem);
	
}
