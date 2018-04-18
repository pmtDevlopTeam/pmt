package com.camelot.pmt.platform.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.camelot.pmt.platform.model.Dict;
import com.camelot.pmt.platform.model.DictItem;

/**
 * <p>
 * 字典Mapper类
 * </p>
 *
 * @author sqw
 * @since 2018-04-08
 */
@Mapper
public interface DictMapper {

    /**
     * 根据一个字典 增加一个字典
     * 
     * @param Dict
     *            dict
     * @return
     */
    void createDict(Dict dict);

    /**
     * 根据字典dictId 删除一个字典
     * 
     * @param String
     *            dictId
     * @return
     */
    void deleteDictByDictId(String dictId);

    /**
     * 根据字典dictId 删除字典项
     * 
     * @param dictId
     * @return
     */
    void deleteDictItemByDictId(String dictId);

    /**
     * 根据字典dictId 修改一个字典
     * 
     * @param Dict
     *            dict
     * @return
     */
    void modifyDictByDictId(Dict dict);

    /**
     * 根据字典dictId 查询一个字典
     * 
     * @param String
     *            dictId
     * @return Dict
     */
    Dict queryDictByDictId(String dictId);

    /**
     * 查询全部字典
     * 
     * @param
     * @return List<Dict>
     */
    List<Dict> queryAllDict();

    /**
     * 查询字典总数量
     *
     * @return Long
     */
    Long countDict();

    /**
     * 检查字典编码是否存在
     * 
     * @param String
     *            dictCode
     * @return Dict
     */
    Dict checkDictCodeIsExist(String dictCode);

    /**
     * 检查字典名称是否存在
     * 
     * @param String
     *            dictName
     * @return Dict
     */
    Dict checkDictNameIsExist(String dictName);

}