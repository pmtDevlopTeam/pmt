package com.camelot.pmt.platform.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.common.Modular;
import com.camelot.pmt.platform.log.LogAspect;
import com.camelot.pmt.platform.mapper.DictItemMapper;
import com.camelot.pmt.platform.mapper.DictMapper;
import com.camelot.pmt.platform.model.Dict;
import com.camelot.pmt.platform.model.DictItem;
import com.camelot.pmt.platform.model.Org;
import com.camelot.pmt.platform.service.DictService;
import com.camelot.pmt.util.UUIDUtil;

/**
 * 字典服务接口类
 *
 * @author pmt
 * @since 2018-04-08
 */
@Service
public class DictServiceImpl implements DictService {

    @Autowired
    DictMapper dictMapper;

    @Autowired
    DictItemMapper dictItemMapper;
    
    @Autowired
    private LogAspect logAspect;

    @Override
    public boolean addDict(Dict dict) {
        String uuid = UUIDUtil.getUUID();
        dict.setDictId(uuid);
        long date = new Date().getTime();
        dict.setCreateTime(new Date(date));
        int num = dictMapper.addDict(dict);
        if(num == 1) {
        	// 添加日志
        	logAspect.insertAddLog(dict, Modular.DICT, dict.getCreateUserId());
        	return true;
        }else {
        	return false;
        }
    }

    @Override
    public boolean deleteDictByDictId(Dict dict) {
    	Dict dictObj = dictMapper.queryDictByDictId(dict.getDictId());
        int num = dictMapper.deleteDictByDictId(dict.getDictId());
        dictMapper.deleteDictItemByDictId(dict.getDictId());
        if (num == 1) {
        	// 添加日志
        	logAspect.insertDeleteLog(Modular.DICT, dict.getModifyUserId(), dictObj.getDictName());
            return true;
        }
        return false;
    }

    @Override
    public boolean updateDictByDictId(Dict dict) {

        long date = new Date().getTime();
        dict.setModifyTime(new Date(date));
        Dict dictBefore = dictMapper.queryDictByDictId(dict.getDictId());
        int num = dictMapper.updateDictByDictId(dict);
        if(num == 1) {
        	Dict dictAfter = dictMapper.queryDictByDictId(dict.getDictId());
        	// 添加日志
        	logAspect.insertUpdateLog(dictAfter, dictBefore, Modular.DICT, dict.getModifyUserId());
        	return true;
        }else {
        	return false;
        }
        
    }

    @Override
    public boolean updateDictByDictIdAndState(Dict dict) {
        long date = new Date().getTime();
        dict.setModifyTime(new Date(date));
        Dict dictBefore = dictMapper.queryDictByDictId(dict.getDictId());
        int num = dictMapper.updateDictByDictIdAndState(dict);
        if(num == 1) {
        	Dict dictAfter = dictMapper.queryDictByDictId(dict.getDictId());
        	// 添加日志
        	logAspect.insertUpdateLog(dictAfter, dictBefore, Modular.DICT, dict.getModifyUserId());
        	return true;
        }else {
        	return false;
        }
        
    }

    @Override
    public boolean updateDictOrDictItemByDictIdAndState(Dict dict) {
    	long date = new Date().getTime();
        dict.setModifyTime(new Date(date));
        Dict dictBefore = dictMapper.queryDictByDictId(dict.getDictId());
//        // 查询字典项List
//        List<DictItem> dictItemList = dictItemMapper.queryDictItemListByDictId(dict.getDictId());
//        for (DictItem dictItem : dictItemList) {
//            dictItem.setState(dict.getState());
//            dictItem.setModifyUserId(dict.getModifyUserId());
//            dictItem.setModifyTime(new Date(date));
//            dictItemMapper.updateDictItemByDictItemIdAndState(dictItem);
//        }
        int num = dictMapper.updateDictByDictIdAndState(dict);
        if(num == 1) {
        	Dict dictAfter = dictMapper.queryDictByDictId(dict.getDictId());
        	// 添加日志
        	logAspect.insertUpdateLog(dictAfter, dictBefore, Modular.DICT, dict.getModifyUserId());
        	updateDictItemState(dict);
        	return true;
        }else {
        	return false;
        }
        
    }
    
    private boolean updateDictItemState(Dict dict) {
    	long date = new Date().getTime();
    	// 查询字典项List
    	List<DictItem> dictItemList = dictItemMapper.queryDictItemListByDictId(dict.getDictId());
		if (CollectionUtils.isEmpty(dictItemList)) {
			return true;
		}else {
	    	for (DictItem dictItem : dictItemList) {
	    		DictItem dictItemBefore = dictItemMapper.queryDictItemByDictItemId(dictItem.getDictItemId());
	            dictItem.setState(dict.getState());
	            dictItem.setModifyUserId(dict.getModifyUserId());
	            dictItem.setModifyTime(new Date(date));
	            int num = dictItemMapper.updateDictItemByDictItemIdAndState(dictItem);
	            if(num == 1) {
		    		DictItem dictItemAfter = dictItemMapper.queryDictItemByDictItemId(dictItem.getDictItemId());
	            	// 添加日志
	            	logAspect.insertUpdateLog(dictItemAfter, dictItemBefore, Modular.DICTITEM, dict.getModifyUserId());
	            }
	        }
	    	return true;
		}
		
    }
    
    @Override
    public Dict queryDictByDictId(String dictId) {
        Dict dict = dictMapper.queryDictByDictId(dictId);
        return dict;
    }

    @Override
    public List<Dict> queryDictListAll() {
        List<Dict> list = dictMapper.queryDictListAll();
        return list;
    }

    // @Override
    // public ExecuteResult<Dict> checkDictCodeIsExist(String dictCode) {
    // ExecuteResult<Dict> result = new ExecuteResult<Dict>();
    // if (!StringUtils.isEmpty(dictCode) ){
    // //1.获取用户输入的字典编码
    // String dictCodes = dictCode;
    // //2.根据字典编码去库中获取字典信息,检查字典名称是否存在
    // Dict dicts = dictMapper.checkDictCodeIsExist(dictCodes);
    // if(dicts == null) {
    // result.setResultMessage("该字典编码不存在！");
    // return result;
    // }
    // result.setResult(dicts);
    // result.setResultMessage("该字典编码存在！");
    // }
    // return result;
    // }

    // @Override
    // public ExecuteResult<Dict> checkDictNameIsExist(String dictName) {
    // ExecuteResult<Dict> result = new ExecuteResult<Dict>();
    // if (!StringUtils.isEmpty(dictName) ){
    // //1.获取用户输入的字典编码
    // String dictNames = dictName;
    // //2.根据字典编码去库中获取字典信息,检查字典名称是否存在
    // Dict dicts = dictMapper.checkDictNameIsExist(dictNames);
    // if(dicts == null) {
    // result.setResultMessage("该字典名称不存在！");
    // return result;
    // }
    // result.setResult(dicts);
    // result.setResultMessage("该字典名称存在！");
    // }
    // return result;
    // }

    @Override
    public ExecuteResult<String> checkDictCodeOrDictNameIsExist(Dict dict) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        // 1.检查字典编码与字典名称是否为null
        if (!StringUtils.isEmpty(dict.getDictCode()) && !StringUtils.isEmpty(dict.getDictName())) {
            // 2.检查字典是否存在
            Dict dictc = dictMapper.checkDictCodeIsExist(dict.getDictCode());
            Dict dictn = dictMapper.checkDictNameIsExist(dict.getDictName());
            if (dictc == null && dictn == null) {
                result.setResult("字典编码,字典名称不重复!");
                result.setResultMessage("字典编码,字典名称不重复!");
                return result;
            }
            if (dictc != null && dictn == null) {
                result.setResult("字典编码重复!");
                return result;
            }
            if (dictc == null && dictn != null) {
                result.setResult("字典名称重复!");
                return result;
            }
            if (dictc != null && dictn != null) {
                result.setResult("字典编码重复,字典名称重复!");
                return result;
            }

        }
        return result;
    }

    @Override
    public ExecuteResult<String> checkDictCodeOrDictNameIsExistUpdate(Dict dict) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        // 1.检查字典编码与字典名称是否存在
        if (!StringUtils.isEmpty(dict.getDictCode()) && !StringUtils.isEmpty(dict.getDictName())) {
            // 2.检查字典是否存在
            Dict dictc = dictMapper.checkDictCodeIsExist(dict.getDictCode());
            Dict dictn = dictMapper.checkDictNameIsExist(dict.getDictName());
            if (dictc == null && dictn == null) {
                result.setResultMessage("字典编码,字典名称不重复!");
                result.setResult("字典编码,字典名称不重复!");
                return result;
            }
            if (dictc != null && dictn == null) {
                if (dictc.getDictId().equals(dict.getDictId())) {
                    result.setResultMessage("字典编码不重复!");
                    result.setResult("字典编码不重复!");
                    return result;
                }
                result.setResult("字典编码重复!");
                return result;
            }
            if (dictc == null && dictn != null) {
                if (dict.getDictId().equals(dictn.getDictId())) {
                    result.setResultMessage("字典名称不重复!");
                    result.setResult("字典名称不重复!");
                    return result;
                }
                result.setResult("字典名称重复!");
                return result;
            }
            if (dictc != null && dictn != null) {
                if (dict.getDictId().equals(dictc.getDictId()) && dict.getDictId().equals(dictn.getDictId())) {
                    result.setResultMessage("字典编码,字典名称不重复!");
                    result.setResult("字典编码,字典名称不重复!");
                    return result;
                }
                if (!dict.getDictId().equals(dictc.getDictId()) && dict.getDictId().equals(dictn.getDictId())) {
                    result.setResult("字典编码重复!");
                    return result;
                }
                if (dict.getDictId().equals(dictc.getDictId()) && !dict.getDictId().equals(dictn.getDictId())) {
                    result.setResult("字典名称重复!");
                    return result;
                }
                if (!dict.getDictId().equals(dictc.getDictId()) && !dict.getDictId().equals(dictn.getDictId())) {
                    result.setResult("字典编码,字典名称重复!");
                    return result;
                }

            }

        }
        return result;
    }

}
