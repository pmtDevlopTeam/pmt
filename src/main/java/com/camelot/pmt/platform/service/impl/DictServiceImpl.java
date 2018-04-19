package com.camelot.pmt.platform.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.mapper.DictMapper;
import com.camelot.pmt.platform.model.Dict;
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

	@Override
	public boolean addDict(Dict dict) {
		try{
			String uuid = UUIDUtil.getUUID();
			dict.setDictId(uuid);
            long date = new Date().getTime();
            dict.setCreateTime(new Date(date));
            return (dictMapper.addDict(dict)==1)?true:false;
		}catch(Exception e){
			throw new RuntimeException(e);
		}

	}

	@Override
	public boolean deleteDictByDictId(String dictId) {
		boolean flag = false;
    	try {
    		int i= dictMapper.deleteDictByDictId(dictId);
    		dictMapper.deleteDictItemByDictId(dictId);
    		if(i == 1) {
    			flag = true;
    		}
    	} catch (Exception e) {
			throw new RuntimeException(e);
		}
    	return flag;

	}
		

	@Override
	public boolean updateDictByDictId(Dict dict) {
	       try {
				if(StringUtils.isEmpty(dict.getDictId()) ){
					return false;
				}
	            long date = new Date().getTime();
	            dict.setModifyTime(new Date(date));
	            return (dictMapper.updateDictByDictId(dict)==1)?true:false;
	        } catch (Exception e){
	            throw new RuntimeException(e);
	        }
	       
	}

	@Override
	public Dict queryDictByDictId(String dictId) {
		try {
			Dict dict = dictMapper.queryDictByDictId(dictId);
			return dict;
		}catch (Exception e) {
            throw new RuntimeException(e);

		}
	}
	

	@Override
	public List<Dict> selectDictListAll() {
    		try {
    			List<Dict> list = dictMapper.selectDictListAll();
        		return list;
    		}catch (Exception e) {
                throw new RuntimeException(e);
    		}
	}

//	@Override
//	public ExecuteResult<Dict> checkDictCodeIsExist(String dictCode) {
//		ExecuteResult<Dict> result = new ExecuteResult<Dict>();
//    	if (!StringUtils.isEmpty(dictCode) ){
//    		//1.获取用户输入的字典编码
//    		String dictCodes = dictCode;
//    		//2.根据字典编码去库中获取字典信息,检查字典名称是否存在
//    		Dict dicts = dictMapper.checkDictCodeIsExist(dictCodes);
//    		if(dicts == null) {
//    			result.setResultMessage("该字典编码不存在！");
//				return result;
//    		}
//    		result.setResult(dicts);
//    		result.setResultMessage("该字典编码存在！");
//        }
//		return result;
//	}

//	@Override
//	public ExecuteResult<Dict> checkDictNameIsExist(String dictName) {
//		ExecuteResult<Dict> result = new ExecuteResult<Dict>();
//    	if (!StringUtils.isEmpty(dictName) ){
//    		//1.获取用户输入的字典编码
//    		String dictNames = dictName;
//    		//2.根据字典编码去库中获取字典信息,检查字典名称是否存在
//    		Dict dicts = dictMapper.checkDictNameIsExist(dictNames);
//    		if(dicts == null) {
//    			result.setResultMessage("该字典名称不存在！");
//				return result;
//    		}
//    		result.setResult(dicts);
//    		result.setResultMessage("该字典名称存在！");
//        }
//		return result;
//	}
	
	@Override
	public ExecuteResult<String> checkDictCodeOrDictNameIsExist(Dict dict) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		//1.检查字典编码与字典名称是否为null
    	if (!StringUtils.isEmpty(dict.getDictCode()) && !StringUtils.isEmpty(dict.getDictName()) ){
    		//2.检查字典是否存在
    		Dict dictc = dictMapper.checkDictCodeIsExist(dict.getDictCode());
    		Dict dictn = dictMapper.checkDictNameIsExist(dict.getDictName());
    		if(dictc == null && dictn == null) {
    			result.setResult("字典编码,字典名称不重复!");
    			result.setResultMessage("字典编码,字典名称不重复!");
				return result;
    		}
    		if(dictc != null && dictn == null) {
    			result.setResult("字典编码重复!");
				return result;
    		}
    		if(dictc == null && dictn != null) {
    			result.setResult("字典名称重复!");
				return result;
    		}
    		if(dictc != null && dictn != null) {
    			result.setResult("字典编码重复,字典名称重复!");
				return result;
    		}
    		
        }
		return result;
	}
	
	@Override
	public ExecuteResult<String> checkDictCodeOrDictNameIsExistUpdate(Dict dict) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		//1.检查字典编码与字典名称是否存在
		if (!StringUtils.isEmpty(dict.getDictCode()) && !StringUtils.isEmpty(dict.getDictName()) ){
    		//2.检查字典是否存在
    		Dict dictc = dictMapper.checkDictCodeIsExist(dict.getDictCode());
    		Dict dictn = dictMapper.checkDictNameIsExist(dict.getDictName());
    		if(dictc == null && dictn == null) {
    			result.setResultMessage("字典编码,字典名称不重复!");
    			result.setResult("字典编码,字典名称不重复!");
				return result;
    		}
    		if(dictc != null && dictn == null) {
    			if(dictc.getDictId().equals(dict.getDictId())) {
    				result.setResultMessage("字典编码不重复!");
    				result.setResult("字典编码不重复!");
    				return result;
    			}
    			result.setResult("字典编码重复!");
				return result;
    		}
    		if(dictc == null && dictn != null) {
    			if(dict.getDictId().equals(dictn.getDictId())) {
    				result.setResultMessage("字典名称不重复!");
    				result.setResult("字典名称不重复!");
    				return result;
    			}
    			result.setResult("字典名称重复!");
				return result;
    		}
    		if(dictc != null && dictn != null) {
    			if(dict.getDictId().equals(dictc.getDictId())&&dict.getDictId().equals(dictn.getDictId())) {
        			result.setResultMessage("字典编码,字典名称不重复!");
        			result.setResult("字典编码,字典名称不重复!");
    				return result;
    			}
    			if(!dict.getDictId().equals(dictc.getDictId())&&dict.getDictId().equals(dictn.getDictId())) {
    				result.setResult("字典编码重复!");
    				return result;
    			}
    			if(dict.getDictId().equals(dictc.getDictId())&&!dict.getDictId().equals(dictn.getDictId())) {
    				result.setResult("字典名称重复!");
    				return result;
    			}
    			if(!dict.getDictId().equals(dictc.getDictId())&&!dict.getDictId().equals(dictn.getDictId())) {
    				result.setResult("字典编码,字典名称重复!");
    				return result;
    			}
    			
    		}
    		
        }
		return result;
	}
	
}
