package com.camelot.pmt.platform.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
@Mapper
public interface DictItemMapper {
	/**
	 * 增加一个字典项
	 * @param DictItem
	 * @return 
	 */
	void createDictItem(DictItem dictItem);
	/**
	 * 根据字典项id 删除一个字典项
	 * @param dictItemId
	 * @return
	 */
	void deleteDictItemByDictItemId(String dictItemId);
	/**
	 * 根据字典类型id 删除字典项
	 * @param dictId
	 * @return
	 */
	void deleteDictItemByDictId(String dictId);
	/**
	 * 根据字典项id 修改一个字典项
	 * @param DictItem
	 * @return
	 */
	void modifyDictItemByDictItemId(DictItem dictItem);
	/**
	 * 根据字典类型id 修改字典项
	 * @param DictItem
	 * @return
	 */
	void modifyDictItemByDictId(DictItem dictItem);
    /**
     * 根据字典项id 查询一个字典项
     * @param dictItemId
     * @return
     */
    DictItem queryDictItemByDictItemId(String dictItemId);
    /**
     * 根据字典类型id 查询字典项 不分页
     * @param dictItemId
     * @return
     */
    List<DictItem> queryDictItemByDictId(String dictId);
    /**
     * 
     *根据字典类型id 查询字典项  分页
     * @return List<DictItem>
     */
    List<DictItem> queryDictItemByDictIdPage(@Param(value = "dictId")String dictId,@Param(value = "page")Pager page);
    /**
     * 查询全部字典项  不分页
     * @param dictId
     * @return
     */
    List<DictItem> queryAllDictItem();
    /**
     * 
     *查询全部字典项列表  分页
     * @return List<DictItem>
     */
    List<DictItem> queryAllDictItemPage(@Param(value = "page") Pager page);
    /**
     *[通过字典类型查询字典项总数量]</p>
     * @return Long 总数量
     */
    Long queryCountByDictId(String dictId);
    /**
     *[查询字典项总数量]</p>
     * @return Long 总数量
     */
    Long queryCount();
    /**
     *[检查字典项编码]
     * @return Dict
     * @author
     */
    DictItem findDictItemCode(String dictItemCode);
    /**
     *[检查字典项名称]
     * @return Dict
     * @author
     */
    DictItem findDictItemName(String dictItemName);
    

}