package com.camelot.pmt.project.service.impl;

import com.camelot.pmt.platform.utils.DataGrid;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.platform.utils.Pager;
import com.camelot.pmt.project.mapper.DemandMapper;
import com.camelot.pmt.project.mapper.DemandOperateMapper;
import com.camelot.pmt.project.model.Demand;
import com.camelot.pmt.project.model.DemandOperate;
import com.camelot.pmt.project.service.DemandService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class DemandServiceImpl implements DemandService {
    private static final Logger logger = LoggerFactory.getLogger(DemandServiceImpl.class);
    @Resource
    DemandMapper demandMapper;
    @Resource
    DemandOperateMapper demandOperateMapper;
    @Override
    public ExecuteResult<String> save(Demand demandWithBLOBs) {
        ExecuteResult<String> result=new ExecuteResult<>();
        try {
        if (demandWithBLOBs==null){
            result.setResultMessage("传入参数不能为空");
            return result;
        }
            demandMapper.insert(demandWithBLOBs);
            DemandOperate demandOperate=new DemandOperate();
            //TODO
            Date currentDate =new Date();
            demandOperate.setId(0l);
            demandOperate.setCreateTime(currentDate);
            demandOperate.setDemandId(demandWithBLOBs.getId());
            demandOperate.setOperateDesc("新增需求");
            demandOperateMapper.insert(demandOperate);
            result.setResult("新增需求成功");
        }catch (Exception e){
            logger.error("--------新增需求-------"+e.getMessage());
        }
        return result;
    }

    /**
     * 查询需求分页
     * @param demand
     * @return
     */
    @Override
    public ExecuteResult<DataGrid<Demand>> findAllByPage(Pager pager, Demand demand) {
        ExecuteResult<DataGrid<Demand>> result=new ExecuteResult<>();
        try{
            List<Demand> demandWithBLOBsList=demandMapper.findAllByPage(pager,demand);
            if(CollectionUtils.isEmpty(demandWithBLOBsList)){
                DataGrid<Demand> dg = new DataGrid<Demand>();
                result.setResult(dg);
                return result;
            }
            DataGrid<Demand> dg = new DataGrid<Demand>();
            dg.setRows(demandWithBLOBsList);
            //查询总条数
            Long total = demandMapper.queryCount(demand);
            dg.setTotal(total);
            result.setResult(dg);
        }catch(Exception e){
            logger.error("-------需求查询分页------"+e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
        }

    @Override
    public ExecuteResult<Demand> findById(Long id) {
        ExecuteResult<Demand> result=new ExecuteResult<>();
        Demand demandWithBLOBs = demandMapper.selectByPrimaryKey(id);
        if(null==demandWithBLOBs){
            return result;
        }
        result.setResult(demandWithBLOBs);
        return result;
    }

    @Override
    public ExecuteResult<String> deleteById(Long id) {
        ExecuteResult<String> result=new ExecuteResult<>();
        try{
            //暂留判断需求是否被引用  TODO

            //暂留操作日志
            demandMapper.deleteByPrimaryKey(id);

            DemandOperate demandOperate=new DemandOperate();
            Date currentDate =new Date();
            demandOperate.setId(0l);
            demandOperate.setCreateTime(currentDate);
            demandOperate.setCreateUserId("1");
            demandOperate.setDemandId(id);
            demandOperate.setOperateDesc("删除需求");
            demandOperateMapper.insert(demandOperate);
            result.setResult("删除需求成功");
        }catch (Exception e){
            logger.error("-------需求业务根据id删除------"+e.getMessage());
            throw new RuntimeException(e);

        }
        return result;
    }

    /**
     * 根据实体更新
     * @param demandWithBLOBs
     * @return
     */
    @Override
    public ExecuteResult<String> updateByDemand(Demand demandWithBLOBs) {
        ExecuteResult<String> result=new ExecuteResult<>();
        try{
            int updateResult=demandMapper.updateByPrimaryKeyWithBLOBs(demandWithBLOBs);

            if(updateResult>0){
                //更新成功
                result.setResult("更新需求成功");
                //暂留操作日志  TODO
                DemandOperate demandOperate=new DemandOperate();
                Date currentDate =new Date();
                demandOperate.setId(0l);
                demandOperate.setCreateTime(currentDate);
                demandOperate.setDemandId(demandWithBLOBs.getId());
                demandOperate.setOperateDesc("更新需求");
                demandOperateMapper.insert(demandOperate);
            }
            result.setResult("更新失败");
        }catch (Exception e){
            logger.error("------需求更新------"+e.getMessage());
            throw  new RuntimeException();
        }
        return result;
    }

    @Override
    public ExecuteResult<DataGrid<DemandOperate>> findAllByPage(Pager pager,DemandOperate demandOperate) {
        ExecuteResult<DataGrid<DemandOperate>> result = new ExecuteResult<>();
        try {
            List<DemandOperate> demandWithBLOBsList = demandOperateMapper.findAllByPage(pager, demandOperate);
            if (CollectionUtils.isEmpty(demandWithBLOBsList)) {
                DataGrid<DemandOperate> dg = new DataGrid<DemandOperate>();
                result.setResult(dg);
                return result;
            }
            DataGrid<DemandOperate> dg = new DataGrid<DemandOperate>();
            dg.setRows(demandWithBLOBsList);
            //查询总条数
            Long total = demandOperateMapper.queryCount(demandOperate);
            dg.setTotal(total);
            result.setResult(dg);
        } catch (Exception e) {
            logger.error("-------需求查询分页------" + e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }
}
