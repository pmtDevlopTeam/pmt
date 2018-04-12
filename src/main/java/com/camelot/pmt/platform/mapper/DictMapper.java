package com.camelot.pmt.platform.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.camelot.pmt.platform.model.Dict;
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
public interface DictMapper {
	/**
	 * 增加一个字典
	 * @param Dict
	 * @return 
	 */
	void createDict(Dict dict);
	/**
	 * 根据字典id 删除一个字典
	 * @param dictId
	 * @return
	 */
	void deleteDictByDictId(String dictId);
    /**
	 * 根据字典类型id 删除字典项
	 * @param dictId
	 * @return
	 */
	void deleteDictItemByDictId(String dictId);
	/**
	 * 根据字典id 修改一个字典
	 * @param Dict
	 * @return
	 */
	void modifyDictByDictId(Dict dict);
    /**
     * 根据字典id 查询一个字典
     * @param dictId
     * @return
     */
    Dict queryDictByDictId(String dictId);
    /**
     * 根据字典类型id 查询字典项
     * @param dictItemId
     * @return
     */
    List<DictItem> queryDictItemByDictId(String dictId);
    /**
     * 查询全部字典
     * @param dictId
     * @return
     */
    List<Dict> queryAllDict();
    /**
     *[检查字典编码]
     * @return Dict
     * @author
     */
    Dict findDictCode(String dictCode);
    /**
     *[检查字典名称]
     * @return Dict
     * @author
     */
    Dict findDictName(String dictName);

}