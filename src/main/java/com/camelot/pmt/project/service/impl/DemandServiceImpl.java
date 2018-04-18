package com.camelot.pmt.project.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camelot.pmt.common.DataGrid;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.project.mapper.DemandMapper;
import com.camelot.pmt.project.mapper.DemandOperateMapper;
import com.camelot.pmt.project.model.Demand;
import com.camelot.pmt.project.model.DemandOperate;
import com.camelot.pmt.project.model.DemandVO;
import com.camelot.pmt.project.service.DemandService;

@Service
public class DemandServiceImpl implements DemandService {
    private static final Logger logger = LoggerFactory.getLogger(DemandServiceImpl.class);
    @Resource
    DemandMapper demandMapper;
    @Resource
    DemandOperateMapper demandOperateMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Demand demand, User user) {
       boolean flag=false;
        try {
            int resultCount = demandMapper.insert(demand);
            if (resultCount > 0) {
                DemandOperate demandOperate = new DemandOperate();
                demandOperate.setCreateUserId(demand.getCreateUserId());
                demandOperate.setCreateTime(demand.getCreateTime());
                demandOperate.setDemandId(demand.getId());
                demandOperate.setOperateDesc(user.getUsername() + "创建");
                //常规操作
                demandOperate.setRunType("01");
                demandOperateMapper.insert(demandOperate);
            }
            flag = true;
        } catch (Exception e) {
            throw  new RuntimeException();
        }
        return flag;
    }

    /**
     * 查询需求分页
     *
     * @param demand
     * @return
     */
    @Override
    public List<Demand> queryByPage(Demand demand,Integer pageSize,Integer currentPage) {
            PageHelper.startPage(currentPage,pageSize);
            return demandMapper.queryByPage(demand);


    }

    /**
     * 根据id查询需求(Get)
     * param  Long id
     * return DemandVO
     */

    public DemandVO queryDemandById(Long id) {
        DemandVO demandVO = new DemandVO();
        Demand demandWithBLOBs = demandMapper.selectByPrimaryKey(id);
        Map<String, Object> map = new HashMap<>();
        map.put("parantDemand", null);
        List<Demand> childDemandList = demandMapper.selectByPId(id);// 子级需求
        Demand parantDemand = demandMapper.selectByPrimaryKey(id);
        if (null != parantDemand) {
            Long pid = parantDemand.getPid();
            if ((null != pid)&&(0 != pid)) {
                // 说明不是最顶级需求，有父需求
                Demand parantDemandList = demandMapper.selectByPrimaryKey(pid);// 有待与前端沟通，是否前端传来pid？
                map.put("parantDemand", parantDemandList);
            }
        }
        map.put("childDemandList", childDemandList);
        demandVO.setDemand(demandWithBLOBs);
        demandVO.setDemandMap(map);
        return demandVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExecuteResult<String> deleteDemandById(Long id) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            // 遍历此需求下是否有引用--->查询所有需求父id为id的记录
            List<Demand> demandList = demandMapper.selectByPId(id);
            List<Long> list = new ArrayList<Long>();
            if (demandList.size() > 0) {
                for (Demand demand : demandList) {
                    List<Demand> tempList = demandMapper.selectByPId(demand.getId());
                    if (tempList.size() > 0) {
                        for (Demand demand2 : tempList) {
                            Long demandId = demand2.getId();// 获取需求id
                            // 查询此需求id下是否有未完成的引用
                            list.addAll(countQuote(demandId));
                        }
                        if (list.size() == tempList.size()) {
                            list.add(demand.getId());
                        }
                    } else {
                        // 说明没有子需求
                        list.addAll(countQuote(demand.getId()));
                    }
                }
                if (list.size() == demandList.size()) {
                    list.add(id);
                }
            } else {
                // 说明没有子需求
                list.addAll(countQuote(id));
            }
            if (list.size() <= 0) {
                // 说明需求有引用不可删除
                result.setResult("此需求有未完成的引用，不可删除！");
                result.addErrorMessage("此需求有未完成的引用，不可删除！");
                return result;
            }
            demandMapper.deleteByList(list);
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if(null!=user) {
                DemandOperate demandOperate = new DemandOperate();
                Date currentDate = new Date();
                demandOperate.setCreateTime(currentDate);
                demandOperate.setCreateUserId(user.getUserId());
                demandOperate.setDemandId(id);
                demandOperate.setOperateDesc(user.getUsername()+"删除需求");
                demandOperateMapper.insert(demandOperate);
            }
            result.setResult("删除需求成功");
        } catch (Exception e) {
            logger.error("-------需求业务根据id删除------" + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);

        }
        return result;
    }

    /**
     * 根据实体更新
     *
     * @param demand
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateByDemand(Demand demand,User user) {
       boolean flag=false;
        try {
            int updateCount = demandMapper.updateByPrimaryKeySelective(demand);
            if (updateCount > 0) {
                DemandOperate demandOperate = new DemandOperate();
                Date currentDate = new Date();
                demandOperate.setCreateTime(currentDate);
                demandOperate.setDemandId(demand.getId());
                demandOperate.setOperateDesc(user.getUsername()+"更新需求");
                demandOperate.setCreateUserId(user.getUserId());
                demandOperate.setRunType("02");
                demandOperateMapper.insert(demandOperate);
            }
           flag = true;
        } catch (Exception e) {
            logger.error("------需求更新------" + e.getMessage());
            throw new RuntimeException();
        }
        return flag;
    }

    @Override
    public List<DemandOperate> queryOperateByPage(DemandOperate demandOperate,Integer pageSize,Integer currentPage) {
        ExecuteResult<DataGrid<DemandOperate>> result = new ExecuteResult<>();
        try {
            PageHelper.startPage(currentPage,pageSize);
            List<DemandOperate> demandOperatesList = demandOperateMapper.queryOperateByPage(demandOperate);
            return demandOperatesList;
        } catch (Exception e) {
            logger.error("-------需求查询分页------" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询需求相关进行中的引用
     *@param
     *@return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    private List<Long> countQuote(Long demandId) {
        // 统计需求相关引用
        List<Long> list = new ArrayList<>();
        Long count = 0L;
        count += demandMapper.findDemandUseCase(demandId);// 用例
        count += demandMapper.fingDemandBug(demandId);// bug
        count += demandMapper.findDemandTask(demandId);// 任务
        if (count <= 0) {
            // 说明没有进行中的引用可删除需求
            list.add(demandId);
        }
        return list;
    }

    /**
     * 查询影响需求的任务信息
     *
     * @param   demandId
     * @return ExecuteResult<List<Map<String, Object>>>
     */
    @Override
    public ExecuteResult<List<Map<String, Object>>> queryDemandTaskQuoteById(Long demandId) {
        ExecuteResult<List<Map<String, Object>>> result = new ExecuteResult<>();
        if ((null == demandId) || (0 == demandId)) {
            result.addErrorMessage("传入的需求id有误");
            return result;
        }
        List<Map<String, Object>> taskList = demandMapper.queryDemandTaskQuoteById(demandId);
        result.setResult(taskList);
        return result;
    }

    /**
     * 查询影响变更需求影响的用例信息
     *
     * @param   demandId
     * @return ExecuteResult<List<Map<String, Object>>>
     */
    @Override
    public ExecuteResult<List<Map<String, Object>>> queryDemandUseCaseQuoteById(Long demandId) {
        ExecuteResult<List<Map<String, Object>>> result = new ExecuteResult<>();
        if ((null == demandId) || (0 == demandId)) {
            result.addErrorMessage("传入的需求id有误");
            return result;
        }
        List<Map<String, Object>> taskList = demandMapper.queryDemandUseCaseQuoteById(demandId);
        result.setResult(taskList);
        return result;
    }

    /**
     * 查询影响需求变更的bug信息
     *
     * @param   demandId
     * @return ExecuteResult<List<Map<String, Object>>>
     */
    @Override
    public ExecuteResult<List<Map<String, Object>>> queryDemandBugQuoteById(Long demandId) {
        ExecuteResult<List<Map<String, Object>>> result = new ExecuteResult<>();
        if ((null == demandId) || (0 == demandId)) {
            result.addErrorMessage("传入的需求id有误");
            return result;
        }
        List<Map<String, Object>> taskList = demandMapper.queryDemandBugQuoteById(demandId);
        result.setResult(taskList);
        return result;
    }

    /**
     * 需求评审
     *
     * @param demand
     * @return
     */
    @Override
    public boolean updateByReview(Demand demand, User user) {
        try {
            int resultCount = demandMapper.updateByPrimaryKeySelective(demand);
            if (resultCount > 0) {
                DemandOperate demandOperate = new DemandOperate();
                demandOperate.setCreateUserId(demand.getCreateUserId());
                demandOperate.setCreateTime(demand.getCreateTime());
                demandOperate.setDemandId(demand.getId());
                demandOperate.setOperateDesc(user.getUsername() + "评审需求");
                //评审操作
                demandOperate.setRunType("02");
                demandOperateMapper.insert(demandOperate);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}