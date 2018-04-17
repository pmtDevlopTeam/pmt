package com.camelot.pmt.platform.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.camelot.pmt.common.Pager;
import com.camelot.pmt.platform.model.DictItem;
/**
 * <p>
 *  字典项Mapper类 
 * </p>
 *
 * @author sqw
 * @since 2018-04-08
 */
@Mapper
public interface DictItemMapper {
	/**
	 * 根据一个字典项 增加一个字典项
	 * 
	 * @param DictItem dictItem
	 * @return 
	 */
	void createDictItem(DictItem dictItem);
	/**
	 * 根据字典项dictItemId 删除一个字典项
	 * 
	 * @param String dictItemId
	 * @return
	 */
	void deleteDictItemByDictItemId(String dictItemId);
	/**
	 * 根据字典项dictItemId 修改一个字典项
	 * 
	 * @param DictItem dictItem
	 * @return
	 */
	void modifyDictItemByDictItemId(DictItem dictItem);
	/**
	 * 根据字典dictId 修改字典项
	 * 
	 * @param DictItem dictItem
	 * @return
	 */
	void modifyDictItemByDictId(DictItem dictItem);
    /**
     * 根据字典项dictItemId 查询一个字典项
     * 
     * @param String dictItemId
     * @return DictItem
     */
    DictItem queryDictItemByDictItemId(String dictItemId);
    /**
     * 根据字典dictId 查询字典项 不分页
     * 
     * @param String dictId
     * @return List<DictItem>
     */
    List<DictItem> queryListDictItemByDictId(String dictId);
    /**
     * 根据字典dictId 查询字典项 分页 
     * 
     * @param String dictId,Pager page
     * @return List<DictItem>
     */
    List<DictItem> queryListDictItemByDictIdPage(@Param(value = "dictId")String dictId,@Param(value = "page")Pager page);
    /**
     * 查询全部字典项  不分页
     * 
     * @param 
     * @return List<DictItem>
     */
    List<DictItem> queryAllDictItem();
    /**
     *查询全部字典项列表  分页
     *
     *@param Pager page
     * @return List<DictItem>
     */
    List<DictItem> queryAllDictItemPage(@Param(value = "page") Pager page);
    /**
     *根据字典dictId查询字典项总数量
     *
     *@param String dictId
     *@return Long 总数量
     */
    Long countDictItemByDictId(String dictId);
    /**
     *查询字典项总数量
     *
     *@param 
     *@return Long 总数量
     */
    Long countDictItem();
    /**
     * 检查字典项编码是否存在
     * 
     * @param String dictItemCode
     * @return DictItem
     */
    DictItem checkDictItemCodeIsExist(String dictItemCode);
    /**
     * 检查字典项名称是否存在 
     * 
     * @param String dictItemName
     * @return DictItem
     */
    DictItem checkDictItemNameIsExist(String dictItemName);
    

}