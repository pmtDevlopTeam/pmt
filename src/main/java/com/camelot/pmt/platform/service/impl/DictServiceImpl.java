package com.camelot.pmt.platform.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.mapper.DictMapper;
import com.camelot.pmt.platform.model.Dict;
import com.camelot.pmt.platform.service.DictService;
import com.camelot.pmt.platform.util.UUIDUtil;

@Service
public class DictServiceImpl implements DictService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DictServiceImpl.class);

	@Autowired
	DictMapper dictMapper;

	@Override
	public ExecuteResult<Dict> createDict(Dict dict) {
		ExecuteResult<Dict> result = new ExecuteResult<Dict>();
		try{
			if(dict == null){
				result.addErrorMessage("传入的字典实体有误!");
				return result;
			}
			String uuid = UUIDUtil.getUUID();
			dict.setDictId(uuid);
			dict.setCreateUserId("1");
            long date = new Date().getTime();
            dict.setCreateTime(new Date(date));
            //检查字典编码与字典名称是否唯一
            result = checkDictCodeOrDictNameIsExist(dict);
            if(result.getResultMessage()==null) {
            	return result;
            }
			dictMapper.createDict(dict);
			result.setResultMessage("添加字典成功!");
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return result;

	}

	@Override
	public ExecuteResult<String> deleteDictByDictId(String dictId) {
		ExecuteResult<String> result = new ExecuteResult<String>();
    	try {
			if(StringUtils.isEmpty(dictId)){
				result.addErrorMessage("传入参数有误!");
				return result;
			}
			dictMapper.deleteDictByDictId(dictId);
    		dictMapper.deleteDictItemByDictId(dictId);
    		result.setResult("删除字典成功！");
    	} catch (Exception e) {
    		LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return result;

	}
		

	@Override
	public ExecuteResult<Dict> modifyDictByDictId(Dict dict) {
		ExecuteResult<Dict> result = new ExecuteResult<Dict>();
	       try {
				if(StringUtils.isEmpty(dict.getDictId()) ){
					result.addErrorMessage("传入的字典实体有误!");
					return result;
				}
	            long date = new Date().getTime();
	            dict.setModifyTime(new Date(date));
	            dict.setModifyUserId("2");
	            //检查字典编码与字典名称是否唯一
	            result = checkDictCodeOrDictNameIsExistUpdate(dict);
	            if(result.getResultMessage()==null) {
	            	return result;
	            }
	            dictMapper.modifyDictByDictId(dict);
	            result.setResultMessage("修改字典成功");
	        } catch (Exception e){
	            LOGGER.error(e.getMessage());
	            throw new RuntimeException(e);
	        }
		return result;

	}

	@Override
	public ExecuteResult<Dict> queryDictByDictId(String dictId) {
		ExecuteResult<Dict> result = new ExecuteResult<Dict>();
		try {
			if(!StringUtils.isEmpty(dictId)) {
				Dict dict = dictMapper.queryDictByDictId(dictId);
				result.setResult(dict);
				return result;
			}
			result.addErrorMessage("dictId为null查询失败！");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return result;

	}

	@Override
	public ExecuteResult<List<Dict>> queryAllDict() {
		ExecuteResult<List<Dict>> result = new ExecuteResult<List<Dict>>();
    	try {
    		List<Dict> list = dictMapper.queryAllDict();
    		if(list.size() <= 0) {
				return result;
			}
    		result.setResult(list);
    	} catch (Exception e) {
    		LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
       return result;
		
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
	public ExecuteResult<Dict> checkDictCodeOrDictNameIsExist(Dict dict) {
		ExecuteResult<Dict> result = new ExecuteResult<Dict>();
		//1.检查字典编码与字典名称是否为null
    	if (!StringUtils.isEmpty(dict.getDictCode()) && !StringUtils.isEmpty(dict.getDictName()) ){
    		//2.检查字典是否存在
    		Dict dictc = dictMapper.checkDictCodeIsExist(dict.getDictCode());
    		Dict dictn = dictMapper.checkDictNameIsExist(dict.getDictName());
    		if(dictc == null && dictn == null) {
    			result.setResultMessage("字典编码,字典名称不重复!");
				return result;
    		}
    		if(dictc != null && dictn == null) {
    			result.addErrorMessage("字典编码重复!");
				return result;
    		}
    		if(dictc == null && dictn != null) {
    			result.addErrorMessage("字典名称重复!");
				return result;
    		}
    		if(dictc != null && dictn != null) {
    			result.addErrorMessage("字典编码重复,字典名称重复!");
				return result;
    		}
    		
        }
		return result;
	}
	
	
	public ExecuteResult<Dict> checkDictCodeOrDictNameIsExistUpdate(Dict dict) {
		ExecuteResult<Dict> result = new ExecuteResult<Dict>();
		//1.检查字典编码与字典名称是否存在
		if (!StringUtils.isEmpty(dict.getDictCode()) && !StringUtils.isEmpty(dict.getDictName()) ){
    		//2.检查字典是否存在
    		Dict dictc = dictMapper.checkDictCodeIsExist(dict.getDictCode());
    		Dict dictn = dictMapper.checkDictNameIsExist(dict.getDictName());
    		if(dictc == null && dictn == null) {
    			result.setResultMessage("字典编码,字典名称不重复!");
				return result;
    		}
    		if(dictc != null && dictn == null) {
    			if(dictc.getDictId().equals(dict.getDictId())) {
    				result.setResultMessage("字典编码不重复!");
    				return result;
    			}
    			result.addErrorMessage("字典编码重复!");
				return result;
    		}
    		if(dictc == null && dictn != null) {
    			if(dict.getDictId().equals(dictn.getDictId())) {
    				result.setResultMessage("字典名称不重复!");
    				return result;
    			}
    			result.addErrorMessage("字典名称重复!");
				return result;
    		}
    		if(dictc != null && dictn != null) {
    			if(dict.getDictId().equals(dictc.getDictId())&&dict.getDictId().equals(dictn.getDictId())) {
        			result.setResultMessage("字典编码,字典名称不重复!");
    				return result;
    			}
    			if(!dict.getDictId().equals(dictc.getDictId())&&dict.getDictId().equals(dictn.getDictId())) {
    				result.addErrorMessage("字典编码重复!");
    				return result;
    			}
    			if(dict.getDictId().equals(dictc.getDictId())&&!dict.getDictId().equals(dictn.getDictId())) {
    				result.addErrorMessage("字典名称重复!");
    				return result;
    			}
    			if(!dict.getDictId().equals(dictc.getDictId())&&!dict.getDictId().equals(dictn.getDictId())) {
    				result.addErrorMessage("字典编码,字典名称重复!");
    				return result;
    			}
    			
    		}
    		
        }
		return result;
	}
	
}
