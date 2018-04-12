package com.camelot.pmt.platform.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.APIStatus;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.mapper.DictMapper;
import com.camelot.pmt.platform.model.Dict;
import com.camelot.pmt.platform.model.DictItem;
import com.camelot.pmt.platform.service.DictService;

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
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			dict.setDictId(uuid);
			dict.setCreateUserId("1");
            long date = new Date().getTime();
            dict.setCreateTime(new Date(date));
            //检查字典编码与字典名称是否唯一
            result = findDictCodeOrDictName(dict.getDictCode(),dict.getDictName());
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
	            result = findDictCodeOrDictNameUpdate(dict);
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

	@Override
	public ExecuteResult<Dict> findDictCode(String dictCode) {
		ExecuteResult<Dict> result = new ExecuteResult<Dict>();
    	if (!StringUtils.isEmpty(dictCode) ){
    		//1.获取用户输入的字典编码
    		String dictCodes = dictCode;
    		//2.根据字典编码去库中获取字典信息,检查字典名称是否存在
    		Dict dicts = dictMapper.findDictCode(dictCodes);
    		if(dicts == null) {
    			result.setResultMessage("该字典编码不存在！");
				return result;
    		}
    		result.setResult(dicts);
    		result.setResultMessage("该字典编码存在！");
        }
		return result;
	}

	@Override
	public ExecuteResult<Dict> findDictName(String dictName) {
		ExecuteResult<Dict> result = new ExecuteResult<Dict>();
    	if (!StringUtils.isEmpty(dictName) ){
    		//1.获取用户输入的字典编码
    		String dictNames = dictName;
    		//2.根据字典编码去库中获取字典信息,检查字典名称是否存在
    		Dict dicts = dictMapper.findDictName(dictNames);
    		if(dicts == null) {
    			result.setResultMessage("该字典名称不存在！");
				return result;
    		}
    		result.setResult(dicts);
    		result.setResultMessage("该字典名称存在！");
        }
		return result;
	}

	public ExecuteResult<Dict> findDictCodeOrDictName(String dictCode,String dictName) {
		ExecuteResult<Dict> result = new ExecuteResult<Dict>();
    	if (!StringUtils.isEmpty(dictName) && !StringUtils.isEmpty(dictCode) ){
    		//2.检查字典是否存在
    		Dict dictc = dictMapper.findDictCode(dictCode);
    		Dict dictn = dictMapper.findDictName(dictName);
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
	
	public ExecuteResult<Dict> findDictCodeOrDictNameUpdate(Dict dict) {
		ExecuteResult<Dict> result = new ExecuteResult<Dict>();
    	if (!StringUtils.isEmpty(dict.getDictCode()) && !StringUtils.isEmpty(dict.getDictName()) ){
    		//2.检查字典是否存在
    		Dict dictc = dictMapper.findDictCode(dict.getDictCode());
    		Dict dictn = dictMapper.findDictName(dict.getDictName());
    		if(dictc == null && dictn == null) {
    			System.out.println("11111");
    			result.setResultMessage("字典编码,字典名称不重复!");
				return result;
    		}
    		if(dictc != null && dictn == null) {
    			if(dictc.getDictId().equals(dict.getDictId())) {
    				System.out.println("22222");
    				result.setResultMessage("字典编码不重复!");
    				return result;
    			}
    			System.out.println("33333");
    			result.addErrorMessage("字典编码重复!");
				return result;
    		}
    		if(dictc == null && dictn != null) {
    			if(dict.getDictId().equals(dictn.getDictId())) {
    				System.out.println("44444");
    				result.setResultMessage("字典名称不重复!");
    				return result;
    			}
    			System.out.println("55555");
    			result.addErrorMessage("字典名称重复!");
				return result;
    		}
    		if(dictc != null && dictn != null) {
    			if(dict.getDictId().equals(dictc.getDictId())&&dict.getDictId().equals(dictn.getDictId())) {
    				System.out.println("66666");
        			result.setResultMessage("字典编码,字典名称不重复!");
    				return result;
    			}
    			if(!dict.getDictId().equals(dictc.getDictId())&&dict.getDictId().equals(dictn.getDictId())) {
    				System.out.println("77777");
    				result.addErrorMessage("字典编码重复!");
    				return result;
    			}
    			if(dict.getDictId().equals(dictc.getDictId())&&!dict.getDictId().equals(dictn.getDictId())) {
    				System.out.println("88888");
    				result.addErrorMessage("字典名称重复!");
    				return result;
    			}
    			if(!dict.getDictId().equals(dictc.getDictId())&&!dict.getDictId().equals(dictn.getDictId())) {
    				System.out.println("99999");
    				result.addErrorMessage("字典编码,字典名称重复!");
    				return result;
    			}
    			
    		}
    		
        }
		return result;
	}
	
}
