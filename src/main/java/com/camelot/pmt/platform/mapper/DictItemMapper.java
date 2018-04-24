package com.camelot.pmt.platform.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.camelot.pmt.common.Pager;
import com.camelot.pmt.platform.model.Dict;
import com.camelot.pmt.platform.model.DictItem;

/**
 * 字典项管理Mapper数据层接口
 *
 * @author pmt
 * @since 2018-04-08
 */
@Mapper
public interface DictItemMapper {
	
	/**
	 * 根据一个字典项 增加一个字典项
	 * 
	 * @param DictItem dictItem
	 * @return int	1：成功；非1：失败
	 */
	int addDictItem(DictItem dictItem);
	/**
	 * 根据字典项dictItemId 删除一个字典项
	 * 
	 * @param String dictItemId
	 * @return int	1：成功；非1：失败
	 */
	int deleteDictItemByDictItemId(String dictItemId);
	/**
	 * 根据字典项dictItemId 修改一个字典项
	 * 
	 * @param DictItem dictItem
	 * @return int	1：成功；非1：失败
	 */
	int updateDictItemByDictItemId(DictItem dictItem);
	/**
	 *  根据字典dictItemId state 修改一个字典状态
	 * @param DictItem dictItem
	 * @return int	1：成功；非1：失败
	 * 
	 **/
	int updateDictItemByDictItemIdAndState(DictItem dictItem);
    /**
     * 根据字典项dictItemId 查询一个字典项
     * 
     * @param String dictItemId
     * @return DictItem
     */
    DictItem queryDictItemByDictItemId(String dictItemId);
    /**
     * 根据字典dictId 查询字典项 
     * 
     * @param String dictId
     * @return List<DictItem>
     */
    List<DictItem> queryDictItemListByDictId(String dictId);
    /**
     * 查询全部字典项  
     * 
     * @param 
     * @return List<DictItem>
     */
    List<DictItem> queryDictItemListAll();
    /**
     * 查询全部字典项  用户
     * 
     * @param 
     * @return List<DictItem>
     */
    List<DictItem> queryDictItemOrUserListAll();
    /**
     * 根据字典dictId 查询字典项 分页 
     * 
     * @param String dictId,Pager page
     * @return List<DictItem>
     */
    List<DictItem> queryListDictItemByDictIdPage(@Param(value = "dictId") String dictId, @Param(value = "page") Pager page);
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