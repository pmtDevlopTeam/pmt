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
import com.camelot.pmt.platform.model.DictItem;
import com.camelot.pmt.platform.service.DictItemService;
import com.camelot.pmt.util.UUIDUtil;

@Service
public class DictItemServiceImpl implements DictItemService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DictItemServiceImpl.class);

	@Autowired
	DictItemMapper dictItemMapper; 
	
	@Override
	public ExecuteResult<String> createDictItem(DictItem dictItem) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		try{
			if(dictItem == null){
				result.setSuccess(false);
				result.addErrorMessage("传入的字典项实体有误!");
				return result;
			}
			String uuid = UUIDUtil.getUUID();
			dictItem.setDictItemId(uuid);
			dictItem.setCreateUserId("1");
            long date = new Date().getTime();
            dictItem.setCreateTime(new Date(date));
            //检查字典项编码与字典项名称是否唯一
            result = checkDictItemCodeOrDictItemNameIsExist(dictItem);
            if(result.getResultMessage()==null) {
            	return result;
            }
			dictItemMapper.createDictItem(dictItem);
			result.setResult("添加字典项成功!");
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
				result.setSuccess(false);
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
	public ExecuteResult<String> modifyDictItemByDictItemId(DictItem dictItem) {
		ExecuteResult<String> result = new ExecuteResult<String>();
	       try {
				if(StringUtils.isEmpty(dictItem.getDictItemId()) ){
					result.setSuccess(false);
					result.addErrorMessage("传入的字典项实体有误!");
					return result;
				}
	            long date = new Date().getTime();
	            dictItem.setModifyTime(new Date(date));
	            dictItem.setModifyUserId("2");
	            //检查字典项编码与字典项名称是否唯一
	            result = checkDictItemCodeOrDictItemNameIsExistUpdate(dictItem);
	            if(result.getResultMessage()==null) {
	            	return result;
	            }
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
		
	}

	@Override
	public ExecuteResult<DictItem> queryDictItemByDictItemId(String dictItemId) {
		ExecuteResult<DictItem> result = new ExecuteResult<DictItem>();
		try {
			if(!StringUtils.isEmpty(dictItemId)) {
				DictItem dictItem = dictItemMapper.queryDictItemByDictItemId(dictItemId);
				if(dictItem==null) {
					return result;
				}
				result.setResult(dictItem);
				return result;
			}
			result.setSuccess(false);
			result.addErrorMessage("dictItemId为null查询失败！");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return result;

	}

	@Override
	public ExecuteResult<List<DictItem>> queryListDictItemByDictId(String dictId) {
		ExecuteResult<List<DictItem>> result = new ExecuteResult<List<DictItem>>();
		try {
			if(!StringUtils.isEmpty(dictId)) {
				List<DictItem> dictItem = dictItemMapper.queryListDictItemByDictId(dictId);
				result.setResult(dictItem);
				return result;
			}
			result.setSuccess(false);
			result.addErrorMessage("dictId为null查询失败！");
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
	public ExecuteResult<DataGrid<DictItem>> queryListDictItemByDictIdPage(String dictId, Pager page) {
		ExecuteResult<DataGrid<DictItem>> result = new ExecuteResult<DataGrid<DictItem>>();
		try{
			if(StringUtils.isEmpty(dictId) ) {
				result.setSuccess(false);
				result.addErrorMessage("传入的参数有误!");
				return result;
			}
            List<DictItem> list = dictItemMapper.queryListDictItemByDictIdPage(dictId, page);
            //如果没有查询到数据，不继续进行
            if (CollectionUtils.isEmpty(list)) {
            	DataGrid<DictItem> dg = new DataGrid<DictItem>();
            	result.setResult(dg);
                return result;
            }
            DataGrid<DictItem> dg = new DataGrid<DictItem>();
            dg.setRows(list);
            //查询总条数
            Long total = dictItemMapper.countDictItemByDictId(dictId);
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
			if(page==null) {
				result.setSuccess(false);
				result.addErrorMessage("传入的参数有误!");
				return result;
			}
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
            Long total = dictItemMapper.countDictItem();
            dg.setTotal(total);				
            result.setResult(dg);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return result;
		
	}
	
	@Override
	public ExecuteResult<String> checkDictItemCodeOrDictItemNameIsExist(DictItem dictItem) {
		ExecuteResult<String> result = new ExecuteResult<String>();
    	if (!StringUtils.isEmpty(dictItem.getDictItemCode()) && !StringUtils.isEmpty(dictItem.getDictItemName()) ){
    		//2.检查字典项是否存在
    		DictItem dictItemc = dictItemMapper.checkDictItemCodeIsExist(dictItem.getDictItemCode());
    		DictItem dictItemn = dictItemMapper.checkDictItemNameIsExist(dictItem.getDictItemName());
    		if(dictItemc == null && dictItemn == null) {
    			result.setResult("字典项编码,字典项名称不重复!");
    			result.setResultMessage("字典项编码,字典项名称不重复!");
				return result;
    		}
    		if(dictItemc != null && dictItemn == null) {
    			result.setResult("字典项编码重复!");
				return result;
    		}
    		if(dictItemc == null && dictItemn != null) {
    			result.setResult("字典项名称重复!");
				return result;
    		}
    		if(dictItemc != null && dictItemn != null) {
    			result.setResult("字典项编码重复,字典项名称重复!");
				return result;
    		}
    		
        }
		return result;
	}

	
	public ExecuteResult<String> checkDictItemCodeOrDictItemNameIsExistUpdate(DictItem dictItem) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		//1.检查字典项编码与字典项名称是否存在
		if (!StringUtils.isEmpty(dictItem.getDictItemCode()) && !StringUtils.isEmpty(dictItem.getDictItemName()) ){
    		//2.检查字典项是否存在
    		DictItem dictItemc = dictItemMapper.checkDictItemCodeIsExist(dictItem.getDictItemCode());
    		DictItem dictItemn = dictItemMapper.checkDictItemNameIsExist(dictItem.getDictItemName());
    		if(dictItemc == null && dictItemn == null) {
    			result.setResultMessage("字典项编码,字典项名称不重复!");
    			result.setResult("字典项编码,字典项名称不重复!");
				return result;
    		}
    		if(dictItemc != null && dictItemn == null) {
    			if(dictItemc.getDictItemId().equals(dictItem.getDictItemId())) {
    				result.setResultMessage("字典项编码不重复!");
    				result.setResult("字典项编码不重复!");
    				return result;
    			}
    			result.setResult("字典项编码重复!");
				return result;
    		}
    		if(dictItemc == null && dictItemn != null) {
    			if(dictItemn.getDictItemId().equals(dictItem.getDictItemId())) {
    				result.setResultMessage("字典项名称不重复!");
    				result.setResult("字典项名称不重复!");
    				return result;
    			}
    			result.setResult("字典项名称重复!");
				return result;
    		}
    		if(dictItemc != null && dictItemn != null) {
    			if(dictItem.getDictItemId().equals(dictItemc.getDictItemId())&&dictItem.getDictItemId().equals(dictItemn.getDictItemId())) {
        			result.setResultMessage("字典项编码,字典项名称不重复!");
        			result.setResult("字典项编码,字典项名称不重复!");
    				return result;
    			}
    			if(!dictItem.getDictItemId().equals(dictItemc.getDictItemId())&&dictItem.getDictItemId().equals(dictItemn.getDictItemId())) {
    				result.setResult("字典项编码重复!");
    				return result;
    			}
    			if(dictItem.getDictItemId().equals(dictItemc.getDictItemId())&&!dictItem.getDictItemId().equals(dictItemn.getDictItemId())) {
    				result.setResult("字典项名称重复!");
    				return result;
    			}
    			if(!dictItem.getDictItemId().equals(dictItemc.getDictItemId())&&!dictItem.getDictItemId().equals(dictItemn.getDictItemId())) {
    				result.setResult("字典项编码,字典项名称重复!");
    				return result;
    			}
    			
    		}
    		
        }
		return result;
	}
}
