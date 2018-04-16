package com.camelot.pmt.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.camelot.pmt.platform.utils.DataGrid;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.platform.utils.Pager;
import com.camelot.pmt.project.mapper.DemandMapper;
import com.camelot.pmt.project.model.DemandWithBLOBs;
import com.camelot.pmt.project.service.DemandService;

@Service
public class DemandServiceImpl implements DemandService {
    private static final Logger logger = LoggerFactory.getLogger(DemandServiceImpl.class);
    @Resource
    DemandMapper demandMapper;

    @Override
    public ExecuteResult<String> save(DemandWithBLOBs demandWithBLOBs) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            if (demandWithBLOBs == null) {
                result.setResultMessage("传入参数不能为空");
                return result;
            }
            demandMapper.insert(demandWithBLOBs);
            result.setResult("新增需求成功");
        } catch (Exception e) {
            logger.error("--------新增需求-------" + e.getMessage());

        }
        return result;
    }

    /**
     * 查询需求分页
     * 
     * @param demandWithBLOBs
     * @return
     */
    @Override
    public ExecuteResult<DataGrid<DemandWithBLOBs>> findAllByPage(Pager pager, DemandWithBLOBs demandWithBLOBs) {
        ExecuteResult<DataGrid<DemandWithBLOBs>> result = new ExecuteResult<>();
        try {
            List<DemandWithBLOBs> demandWithBLOBsList = demandMapper.findAllByPage(pager, demandWithBLOBs);
            if (CollectionUtils.isEmpty(demandWithBLOBsList)) {
                DataGrid<DemandWithBLOBs> dg = new DataGrid<DemandWithBLOBs>();
                result.setResult(dg);
                return result;
            }
            DataGrid<DemandWithBLOBs> dg = new DataGrid<DemandWithBLOBs>();
            dg.setRows(demandWithBLOBsList);
            // 查询总条数
            Long total = demandMapper.queryCount(demandWithBLOBs);
            dg.setTotal(total);
            result.setResult(dg);
        } catch (Exception e) {
            logger.error("-------需求查询分页------" + e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }
}
