package com.camelot.pmt.platform.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.common.DataGrid;
import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.common.Pager;
import com.camelot.pmt.platform.mapper.DictItemMapper;
import com.camelot.pmt.platform.model.DictItem;
import com.camelot.pmt.platform.service.DictItemService;

@Service
public class DictItemServiceImpl implements DictItemService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DictItemServiceImpl.class);

	@Autowired
	DictItemMapper dictItemMapper;
	
	@Override
	public ExecuteResult<DictItem> createDictItem(DictItem dictItem) {
		ExecuteResult<DictItem> result = new ExecuteResult<DictItem>();
		try{
			if(dictItem == null){
				result.addErrorMessage("传入的字典项实体有误!");
				return result;
			}
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			dictItem.setDictItemId(uuid);
			dictItem.setCreateUserId("1");
            long date = new Date().getTime();
            dictItem.setCreateTime(new Date(date));
            //检查字典项编码与字典项名称是否唯一
            result = findDictItemCodeOrDictItemName(dictItem.getDictItemCode(),dictItem.getDictItemName());
            if(result.getResultMessage()==null) {
            	return result;
            }
			dictItemMapper.createDictItem(dictItem);
			result.setResultMessage("添加字典项成功!");
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return result;

	}

	@Override
	public ExecuteResult<String> deleteDictItemByDictItemId(String dictItemId) {
		ExecuteResult<String> result = new ExecuteResult<String>();
    	try {
			if(StringUtils.isEmpty(dictItemId)){
				result.addErrorMessage("传入参数有误!");
				return result;
			}
    		dictItemMapper.deleteDictItemByDictItemId(dictItemId);
    		result.setResult("删除字典项成功！");
    	} catch (Exception e) {
    		LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return result;

	}

	@Override
	public ExecuteResult<String> deleteDictItemByDictId(String dictId) {
		ExecuteResult<String> result = new ExecuteResult<String>();
    	try {
			if(StringUtils.isEmpty(dictId)){
				result.addErrorMessage("传入参数有误!");
				return result;
			}
    		dictItemMapper.deleteDictItemByDictId(dictId);
    		result.setResult("通过字典类型id删除字典项成功！");
    	} catch (Exception e) {
    		LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return result;

	}

	@Override
	public ExecuteResult<String> modifyDictItemByDictItemId(DictItem dictItem) {
		ExecuteResult<String> result = new ExecuteResult<String>();
	       try {
				if(StringUtils.isEmpty(dictItem.getDictItemId()) ){
					result.addErrorMessage("传入的字典项实体有误!");
					return result;
				}
	            long date = new Date().getTime();
	            dictItem.setModifyTime(new Date(date));
	            dictItemMapper.modifyDictItemByDictItemId(dictItem);
	            result.setResult("修改字典项成功");
	        } catch (Exception e){
	            LOGGER.error(e.getMessage());
	            throw new RuntimeException(e);
	        }
		return result;

	}

	@Override
	public ExecuteResult<String> modifyDictItemByDictId(DictItem dictItem) {
		ExecuteResult<String> result = new ExecuteResult<String>();

	       try {
	            long date = new Date().getTime();
	            dictItem.setModifyTime(new Date(date));
	            dictItemMapper.modifyDictItemByDictId(dictItem);
	            result.setResult("通过字典类型id修改字典项成功");
	        } catch (Exception e){
	            LOGGER.error(e.getMessage());
	            throw new RuntimeException(e);
	        }
		return result;
		
//		int modifyDictItemByDictId = dictItemMapper.modifyDictItemByDictId(dictItem);
//		if(modifyDictItemByDictId == 1) {
//			return ApiResponse.success();
//		}
//		return ApiResponse.error();
	}

	@Override
	public ExecuteResult<DictItem> queryDictItemByDictItemId(String dictItemId) {
		ExecuteResult<DictItem> result = new ExecuteResult<DictItem>();
		try {
			if(!StringUtils.isEmpty(dictItemId)) {
				DictItem dictItem = dictItemMapper.queryDictItemByDictItemId(dictItemId);
				result.setResult(dictItem);
				return result;
			}
			result.addErrorMessage("查询失败！");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return result;

	}

	@Override
	public ExecuteResult<List<DictItem>> queryDictItemByDictId(String dictId) {
		ExecuteResult<List<DictItem>> result = new ExecuteResult<List<DictItem>>();
		try {
			if(!StringUtils.isEmpty(dictId)) {
				List<DictItem> dictItem = dictItemMapper.queryDictItemByDictId(dictId);
				result.setResult(dictItem);
				return result;
			}
			result.addErrorMessage("查询失败！");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return result;
		
	}

	@Override
	public ExecuteResult<List<DictItem>> queryAllDictItem() {
		ExecuteResult<List<DictItem>> result = new ExecuteResult<List<DictItem>>();
    	try {
    		List<DictItem> list = dictItemMapper.queryAllDictItem();
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
	public ExecuteResult<DataGrid<DictItem>> queryDictItemByDictIdPage(String dictId, Pager page) {
		ExecuteResult<DataGrid<DictItem>> result = new ExecuteResult<DataGrid<DictItem>>();
		try{
			if(StringUtils.isEmpty(dictId) ) {
				result.addErrorMessage("传入的参数有误！");
				return result;
			}
            List<DictItem> list = dictItemMapper.queryDictItemByDictIdPage(dictId, page);
            //如果没有查询到数据，不继续进行
            if (CollectionUtils.isEmpty(list)) {
            	DataGrid<DictItem> dg = new DataGrid<DictItem>();
            	result.setResult(dg);
                return result;
            }
            DataGrid<DictItem> dg = new DataGrid<DictItem>();
            dg.setRows(list);
            //查询总条数
//            Long total = dictItemMapper.queryCount();
            Long total = dictItemMapper.queryCountByDictId(dictId);
            dg.setTotal(total);				
            result.setResult(dg);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return result;
		
	}

	@Override
	public ExecuteResult<DataGrid<DictItem>> queryAllDictItemPage(Pager page) {
		ExecuteResult<DataGrid<DictItem>> result = new ExecuteResult<DataGrid<DictItem>>();
		try{
            List<DictItem> list = dictItemMapper.queryAllDictItemPage(page);
            //如果没有查询到数据，不继续进行
            if (CollectionUtils.isEmpty(list)) {
            	DataGrid<DictItem> dg = new DataGrid<DictItem>();
            	result.setResult(dg);
                return result;
            }            
            DataGrid<DictItem> dg = new DataGrid<DictItem>();
            dg.setRows(list);
            //查询总条数
            Long total = dictItemMapper.queryCount();
            dg.setTotal(total);				
            result.setResult(dg);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return result;
		
	}
	
	
	public ExecuteResult<DictItem> findDictItemCodeOrDictItemName(String dictItemCode,String dictItemName) {
		ExecuteResult<DictItem> result = new ExecuteResult<DictItem>();
    	if (!StringUtils.isEmpty(dictItemCode) && !StringUtils.isEmpty(dictItemName) ){
    		//2.检查字典项是否存在
    		DictItem dictItemc = dictItemMapper.findDictItemCode(dictItemCode);
    		DictItem dictItemn = dictItemMapper.findDictItemName(dictItemName);
    		if(dictItemc == null && dictItemn == null) {
    			result.setResultMessage("字典项编码,字典项名称不重复!");
				return result;
    		}
    		if(dictItemc != null && dictItemn == null) {
    			result.addErrorMessage("字典项编码重复!");
				return result;
    		}
    		if(dictItemc == null && dictItemn != null) {
    			result.addErrorMessage("字典项名称重复!");
				return result;
    		}
    		if(dictItemc != null && dictItemn != null) {
    			result.addErrorMessage("字典项编码重复,字典项名称重复!");
				return result;
    		}
    		
        }
		return result;
	}

}
