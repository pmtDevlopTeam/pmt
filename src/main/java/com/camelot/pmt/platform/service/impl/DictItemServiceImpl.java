package com.camelot.pmt.platform.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.camelot.pmt.common.DataGrid;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.common.Pager;
import com.camelot.pmt.platform.mapper.DictItemMapper;
import com.camelot.pmt.platform.model.Dict;
import com.camelot.pmt.platform.model.DictItem;
import com.camelot.pmt.platform.model.Menu;
import com.camelot.pmt.platform.service.DictItemService;
import com.camelot.pmt.util.UUIDUtil;
import com.github.pagehelper.PageHelper;

/**
 * 字典项服务接口类
 *
 * @author pmt
 * @since 2018-04-08
 */
@Service
public class DictItemServiceImpl implements DictItemService {

    @Autowired
    DictItemMapper dictItemMapper;

    @Override
    public boolean addDictItem(DictItem dictItem) {
        String uuid = UUIDUtil.getUUID();
        dictItem.setDictItemId(uuid);
        long date = new Date().getTime();
        dictItem.setCreateTime(new Date(date));
        return (dictItemMapper.addDictItem(dictItem) == 1) ? true : false;
    }

    @Override
    public boolean deleteDictItemByDictItemId(String dictItemId) {
        boolean flag = false;
        int i = dictItemMapper.deleteDictItemByDictItemId(dictItemId);
        if (i == 1) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean updateDictItemByDictItemId(DictItem dictItem) {
        if (StringUtils.isEmpty(dictItem.getDictItemId())) {
            return false;
        }
        long date = new Date().getTime();
        dictItem.setModifyTime(new Date(date));
        return (dictItemMapper.updateDictItemByDictItemId(dictItem) == 1) ? true : false;
    }

    @Override
    public boolean updateDictItemByDictItemIdAndState(DictItem dictItem) {
        if (StringUtils.isEmpty(dictItem.getDictItemId())) {
            return false;
        }
        long date = new Date().getTime();
        dictItem.setModifyTime(new Date(date));
        return (dictItemMapper.updateDictItemByDictItemIdAndState(dictItem) == 1) ? true : false;
    }

    @Override
    public DictItem queryDictItemByDictItemId(String dictItemId) {
        DictItem dictItem = dictItemMapper.queryDictItemByDictItemId(dictItemId);
        return dictItem;
    }

    @Override
    public List<DictItem> queryDictItemListByDictId(String dictId, Integer pageSize, Integer currentPage) {
        PageHelper.startPage(currentPage, pageSize);
        List<DictItem> list = dictItemMapper.queryDictItemListByDictId(dictId);
        return list;
    }

    @Override
    public List<DictItem> queryDictItemListAll(Integer pageSize, Integer currentPage) {
        PageHelper.startPage(currentPage, pageSize);
        List<DictItem> list = dictItemMapper.queryDictItemListAll();
        return list;
    }

    @Override
    public List<DictItem> queryDictItemOrUserListAll(Integer pageSize, Integer currentPage) {
        PageHelper.startPage(currentPage, pageSize);
        List<DictItem> list = dictItemMapper.queryDictItemOrUserListAll();
        return list;
    }

    // @Override
    // public ExecuteResult<DataGrid<DictItem>> queryListDictItemByDictIdPage(String
    // dictId, Pager page) {
    // ExecuteResult<DataGrid<DictItem>> result = new
    // ExecuteResult<DataGrid<DictItem>>();
    // try{
    // if(StringUtils.isEmpty(dictId) ) {
    // result.setSuccess(false);
    // result.addErrorMessage("传入的参数有误!");
    // return result;
    // }
    // List<DictItem> list = dictItemMapper.queryListDictItemByDictIdPage(dictId,
    // page);
    // //如果没有查询到数据，不继续进行
    // if (CollectionUtils.isEmpty(list)) {
    // DataGrid<DictItem> dg = new DataGrid<DictItem>();
    // result.setResult(dg);
    // return result;
    // }
    // DataGrid<DictItem> dg = new DataGrid<DictItem>();
    // dg.setRows(list);
    // //查询总条数
    // Long total = dictItemMapper.countDictItemByDictId(dictId);
    // dg.setTotal(total);
    // result.setResult(dg);
    // }catch(Exception e){
    // throw new RuntimeException(e);
    // }
    // return result;
    //
    // }
    //
    // @Override
    // public ExecuteResult<DataGrid<DictItem>> queryAllDictItemPage(Pager page) {
    // ExecuteResult<DataGrid<DictItem>> result = new
    // ExecuteResult<DataGrid<DictItem>>();
    // try{
    // if(page==null) {
    // result.setSuccess(false);
    // result.addErrorMessage("传入的参数有误!");
    // return result;
    // }
    // List<DictItem> list = dictItemMapper.queryAllDictItemPage(page);
    // //如果没有查询到数据，不继续进行
    // if (CollectionUtils.isEmpty(list)) {
    // DataGrid<DictItem> dg = new DataGrid<DictItem>();
    // result.setResult(dg);
    // return result;
    // }
    // DataGrid<DictItem> dg = new DataGrid<DictItem>();
    // dg.setRows(list);
    // //查询总条数
    // Long total = dictItemMapper.countDictItem();
    // dg.setTotal(total);
    // result.setResult(dg);
    // }catch(Exception e){
    // throw new RuntimeException(e);
    // }
    // return result;
    //
    // }

    @Override
    public ExecuteResult<String> checkDictItemCodeOrDictItemNameIsExist(DictItem dictItem) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        if (!StringUtils.isEmpty(dictItem.getDictItemCode()) && !StringUtils.isEmpty(dictItem.getDictItemName())) {
            // 2.检查字典项是否存在
            DictItem dictItemc = dictItemMapper.checkDictItemCodeIsExist(dictItem.getDictItemCode());
            DictItem dictItemn = dictItemMapper.checkDictItemNameIsExist(dictItem.getDictItemName());
            if (dictItemc == null && dictItemn == null) {
                result.setResult("字典项编码,字典项名称不重复!");
                result.setResultMessage("字典项编码,字典项名称不重复!");
                return result;
            }
            if (dictItemc != null && dictItemn == null) {
                result.setResult("字典项编码重复!");
                return result;
            }
            if (dictItemc == null && dictItemn != null) {
                result.setResult("字典项名称重复!");
                return result;
            }
            if (dictItemc != null && dictItemn != null) {
                result.setResult("字典项编码重复,字典项名称重复!");
                return result;
            }

        }
        return result;
    }

    public ExecuteResult<String> checkDictItemCodeOrDictItemNameIsExistUpdate(DictItem dictItem) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        // 1.检查字典项编码与字典项名称是否存在
        if (!StringUtils.isEmpty(dictItem.getDictItemCode()) && !StringUtils.isEmpty(dictItem.getDictItemName())) {
            // 2.检查字典项是否存在
            DictItem dictItemc = dictItemMapper.checkDictItemCodeIsExist(dictItem.getDictItemCode());
            DictItem dictItemn = dictItemMapper.checkDictItemNameIsExist(dictItem.getDictItemName());
            if (dictItemc == null && dictItemn == null) {
                result.setResultMessage("字典项编码,字典项名称不重复!");
                result.setResult("字典项编码,字典项名称不重复!");
                return result;
            }
            if (dictItemc != null && dictItemn == null) {
                if (dictItemc.getDictItemId().equals(dictItem.getDictItemId())) {
                    result.setResultMessage("字典项编码不重复!");
                    result.setResult("字典项编码不重复!");
                    return result;
                }
                result.setResult("字典项编码重复!");
                return result;
            }
            if (dictItemc == null && dictItemn != null) {
                if (dictItemn.getDictItemId().equals(dictItem.getDictItemId())) {
                    result.setResultMessage("字典项名称不重复!");
                    result.setResult("字典项名称不重复!");
                    return result;
                }
                result.setResult("字典项名称重复!");
                return result;
            }
            if (dictItemc != null && dictItemn != null) {
                if (dictItem.getDictItemId().equals(dictItemc.getDictItemId())
                        && dictItem.getDictItemId().equals(dictItemn.getDictItemId())) {
                    result.setResultMessage("字典项编码,字典项名称不重复!");
                    result.setResult("字典项编码,字典项名称不重复!");
                    return result;
                }
                if (!dictItem.getDictItemId().equals(dictItemc.getDictItemId())
                        && dictItem.getDictItemId().equals(dictItemn.getDictItemId())) {
                    result.setResult("字典项编码重复!");
                    return result;
                }
                if (dictItem.getDictItemId().equals(dictItemc.getDictItemId())
                        && !dictItem.getDictItemId().equals(dictItemn.getDictItemId())) {
                    result.setResult("字典项名称重复!");
                    return result;
                }
                if (!dictItem.getDictItemId().equals(dictItemc.getDictItemId())
                        && !dictItem.getDictItemId().equals(dictItemn.getDictItemId())) {
                    result.setResult("字典项编码,字典项名称重复!");
                    return result;
                }

            }

        }
        return result;
    }

}
