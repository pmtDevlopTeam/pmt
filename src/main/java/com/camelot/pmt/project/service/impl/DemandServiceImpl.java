package com.camelot.pmt.project.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.common.IncrementNumber;
import com.camelot.pmt.common.compareBeanAttr;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.project.mapper.DemandMapper;
import com.camelot.pmt.project.mapper.DemandOperateMapper;
import com.camelot.pmt.project.model.Demand;
import com.camelot.pmt.project.model.DemandOperate;
import com.camelot.pmt.project.model.DemandVO;
import com.camelot.pmt.project.service.DemandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class DemandServiceImpl implements DemandService {
    private static final Logger logger = LoggerFactory.getLogger(DemandServiceImpl.class);
    @Resource
    DemandMapper demandMapper;
    @Resource
    DemandOperateMapper demandOperateMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addDemand(Demand demand, User user) {
        boolean flag = false;
        try {
            String demandNum = queryDemandNum(demand);
            demand.setDemandNum(demandNum);
            int resultCount = demandMapper.insert(demand);
            if (resultCount > 0) {
                DemandOperate demandOperate = new DemandOperate();
                demandOperate.setCreateUserId(demand.getCreateUserId());
                demandOperate.setCreateTime(demand.getCreateTime());
                demandOperate.setDemandId(demand.getId());
                demandOperate.setOperateDesc(user.getUsername() + "创建");
                // 常规操作
                demandOperate.setRunType("01");
                demandOperateMapper.insert(demandOperate);
            }
            flag = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
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
    public List<Demand> queryByPage(Demand demand, Integer pageSize, Integer currentPage) {
        PageHelper.startPage(currentPage, pageSize);
        return demandMapper.queryByPage(demand);

    }

    /**
     * 根据id查询需求(Get) param Long id return DemandVO
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
            if ((null != pid) && (0 != pid)) {
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
            if (null != user) {
                DemandOperate demandOperate = new DemandOperate();
                Date currentDate = new Date();
                demandOperate.setCreateTime(currentDate);
                demandOperate.setCreateUserId(user.getUserId());
                demandOperate.setDemandId(id);
                demandOperate.setOperateDesc(user.getUsername() + "删除需求");
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
    public boolean updateByDemand(Demand demand, User user) {
        boolean flag = false;
        try {
            Demand oldDemand = demandMapper.selectByPrimaryKey(demand.getId());
            Object obj = compareBeanAttr.compareBeanAttr(Demand.class, demand, oldDemand, new String[] { "id" });
            String operateDesc = (String) obj;

            int updateCount = demandMapper.updateByPrimaryKeySelective(demand);
            if (updateCount > 0) {
                DemandOperate demandOperate = new DemandOperate();
                Date currentDate = new Date();
                demandOperate.setCreateTime(currentDate);
                demandOperate.setDemandId(demand.getId());
                demandOperate.setOperateDesc(user.getUsername() + ":" + operateDesc);
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
    public List<DemandOperate> queryOperateByPage(DemandOperate demandOperate, Integer pageSize, Integer currentPage) {
        try {
            PageHelper.startPage(currentPage, pageSize);
            List<DemandOperate> demandOperatesList = demandOperateMapper.queryOperateByPage(demandOperate);
            return demandOperatesList;
        } catch (Exception e) {
            logger.error("-------需求查询分页------" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询需求相关进行中的引用
     * 
     * @param
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
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
     * @param demandId
     * @return ExecuteResult<List<Map<String, Object>>>
     */
    @Override
    public PageInfo<Map<String, Object>> queryDemandTaskQuoteById(Long demandId, Integer pageSize,
            Integer currentPage) {
        PageHelper.startPage(currentPage, pageSize);
        List<Map<String, Object>> taskList = demandMapper.queryDemandTaskQuoteById(demandId);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(taskList);
        return pageInfo;
    }

    /**
     * 查询影响变更需求影响的用例信息
     *
     * @param demandId
     * @return ExecuteResult<List<Map<String, Object>>>
     */
    @Override
    public PageInfo<Map<String, Object>> queryDemandUseCaseQuoteById(Long demandId, Integer pageSize,
            Integer currentPage) {
        PageHelper.startPage(currentPage, pageSize);
        List<Map<String, Object>> userCaseList = demandMapper.queryDemandUseCaseQuoteById(demandId);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(userCaseList);
        return pageInfo;
    }

    /**
     * 查询影响需求变更的bug信息
     *
     * @param demandId
     * @return ExecuteResult<List<Map<String, Object>>>
     */
    @Override
    public PageInfo<Map<String, Object>> queryDemandBugQuoteById(Long demandId, Integer pageSize, Integer currentPage) {
        PageHelper.startPage(currentPage, pageSize);
        List<Map<String, Object>> bugList = demandMapper.queryDemandBugQuoteById(demandId);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(bugList);
        return pageInfo;
    }

    /**
     * 需求评审
     *
     * @param demand
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateByReview(Demand demand, User user) {
        boolean flag = false;
        try {
            int resultCount = demandMapper.updateByPrimaryKeySelective(demand);
            if (resultCount > 0) {
                DemandOperate demandOperate = new DemandOperate();
                demandOperate.setCreateUserId(user.getUserId());
                demandOperate.setCreateTime(demand.getModifyTime());
                demandOperate.setDemandId(demand.getId());
                demandOperate.setOperateDesc(user.getUsername() + "评审需求");
                // 评审操作
                demandOperate.setRunType("02");
                demandOperateMapper.insert(demandOperate);
            }
            flag = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

    /**
     * 新增级联需求
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addDemandList(DemandVO demandVO, User user) {
        boolean flag = false;
        Demand demand = demandVO.getDemand();
        Date currentDate = new Date();
        demand.setCreateTime(currentDate);
        demand.setCreateUserId(user.getUserId());
        demand.setModifyUserId(user.getUserId());
        demand.setModifyTime(currentDate);
        try {
            String demandNum = queryDemandNum(demand);
            demand.setDemandNum(demandNum.toString());
            int resultCount = demandMapper.insert(demand);
            if (resultCount > 0) {
                Long pid = demand.getId();// 返回的主键id
                List<Demand> demandList = demandVO.getDemandList();
                if ((null != demandList) && (demandList.size() > 0)) {
                    // 2级需求列表
                    String snum = "00";
                    for (Demand demand2 : demandList) {
                        if (null != demand2) {
                            snum = IncrementNumber.getIncreNum(snum);
                            demand2.setDemandNum(demandNum + "." + snum);
                            demand2.setPid(pid);
                            demand2.setCreateTime(currentDate);
                            demand2.setCreateUserId(user.getUserId());
                            demand2.setModifyUserId(user.getUserId());
                            demand2.setModifyTime(currentDate);
                        }

                    }
                    resultCount = demandMapper.insertDemandList(demandList);
                }
                DemandOperate demandOperate = new DemandOperate();
                demandOperate.setCreateUserId(demand.getCreateUserId());
                demandOperate.setCreateTime(demand.getCreateTime());
                demandOperate.setDemandId(demand.getId());
                demandOperate.setOperateDesc(user.getUsername() + "创建");
                // 常规操作
                demandOperate.setRunType("01");
                demandOperateMapper.insert(demandOperate);
            }
            flag = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

    /**
     * 获取需求编号
     * 
     * @param Demand
     *            demand
     * @return demandNum
     */
    private String queryDemandNum(Demand demand) {
        String demandNum = "01";
        // 查询是否已有同级别需求
        if ("1".equals(demand.getDemandNeed())) {
            demandNum = demandMapper.queryMaxDemandNumByDemand(demand);
            if ((null != demandNum) && (!"".equals(demandNum))) {
                demandNum = IncrementNumber.getIncreNum(demandNum);
            } else {
                demandNum = "01";
            }
        }
        String num = demandMapper.queryMaxDemandNumByDemand(demand);
        String parantNum = demandMapper.queryParantDemandById(demand.getPid());
        if (null != num) {
            demandNum = IncrementNumber.getIncreNum(num.substring(num.lastIndexOf(".") + 1));// 最后一位
        }
        if (null != parantNum) {
            return (parantNum + "." + demandNum);
        } else {
            return (demandNum);
        }
    }

    /**
     * 添加需求并返回主键id
     * 
     * @param Demand
     *            demand User user
     * @return demandId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long addDemandAndReturnId(Demand demand, User user) {
        Long demandId = 0L;
        try {
            String demandNum = queryDemandNum(demand);
            demand.setDemandNum(demandNum);
            int resultCount = demandMapper.insert(demand);
            demandId = demand.getId();
            if (resultCount > 0) {
                DemandOperate demandOperate = new DemandOperate();
                demandOperate.setCreateUserId(demand.getCreateUserId());
                demandOperate.setCreateTime(demand.getCreateTime());
                demandOperate.setDemandId(demand.getId());
                demandOperate.setOperateDesc(user.getUsername() + "创建");
                // 常规操作
                demandOperate.setRunType("01");
                demandOperateMapper.insert(demandOperate);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return demandId;
    }
}