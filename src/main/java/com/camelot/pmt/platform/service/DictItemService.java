package com.camelot.pmt.platform.service;

import java.util.List;

import com.camelot.pmt.common.DataGrid;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.common.Pager;
import com.camelot.pmt.platform.model.Dict;
import com.camelot.pmt.platform.model.DictItem;

/**
 * 字典项服务接口类
 *
 * @author pmt
 * @since 2018-04-08
 */
public interface DictItemService {

    /**
     * 根据一个字典项 增加一个字典项
     * 
     * @param DictItem
     *            dictItem
     * @return boolean
     */
    boolean addDictItem(DictItem dictItem);

    /**
     * 根据字典项dictItemId 删除一个字典项
     * 
     * @param String
     *            dictItemId
     * @return boolean
     */
    boolean deleteDictItemByDictItemId(String dictItemId);

    /**
     * 根据字典项dictItemId 修改一个字典项
     * 
     * @param DictItem
     *            dictItem
     * @return boolean
     */
    boolean updateDictItemByDictItemId(DictItem dictItem);

    /**
     * 根据字典项dictItemId state 修改一个字典项的状态
     * 
     * @param DictItem
     *            dictItem
     * @return JSONObject
     * 
     **/
    boolean updateDictItemByDictItemIdAndState(DictItem dictItem);

    /**
     * 根据字典项dictItemId 查询一个字典项
     * 
     * @param String
     *            dictItemId
     * @return DictItem
     */
    DictItem queryDictItemByDictItemId(String dictItemId);

    /**
     * 根据字典dictId 查询字典项
     * 
     * @param String
     *            dictId
     * @return List<DictItem>
     */
    List<DictItem> queryDictItemListByDictId(String dictId, Integer pageSize, Integer currentPage);

    /**
     * 查询全部字典项
     * 
     * @param
     * @return List<DictItem>
     */
    List<DictItem> queryDictItemListAll(Integer pageSize, Integer currentPage);

    /**
     * 查询全部字典项 用户
     * 
     * @param
     * @return List<DictItem>
     */
    List<DictItem> queryDictItemOrUserListAll(Integer pageSize, Integer currentPage);

    // /**
    // *根据字典dictId 查询字典项 分页
    // *
    // * @param String dictId,Pager page
    // * @return ExecuteResult<DataGrid<DictItem>>
    // */
    // ExecuteResult<DataGrid<DictItem>> queryListDictItemByDictIdPage(String
    // dictId, Pager page);
    // /**
    // *
    // * 查询全部字典项 分页
    // *
    // * @param Pager page
    // * @return ExecuteResult<DataGrid<DictItem>>
    // */
    // ExecuteResult<DataGrid<DictItem>> queryAllDictItemPage(Pager page);
    /**
     * 检查字典项编码与字典项名称是否存在
     * 
     * @param DictItem
     *            dictItem
     * @return ExecuteResult<DictItem>
     */
    ExecuteResult<String> checkDictItemCodeOrDictItemNameIsExist(DictItem dictItem);

    /**
     * 检查字典项编码与字典项名称是否存在 update
     * 
     * @param DictItem
     *            dictItem
     * @return ExecuteResult<DictItem>
     */
    ExecuteResult<String> checkDictItemCodeOrDictItemNameIsExistUpdate(DictItem dictItem);

}
