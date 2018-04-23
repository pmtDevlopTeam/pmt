package com.camelot.pmt.caserepertory.service.impl;

import com.camelot.pmt.caserepertory.PageBean;
import com.camelot.pmt.caserepertory.mapper.CaseRepertoryMapper;
import com.camelot.pmt.caserepertory.mapper.CaseRepertoryStepMapper;
import com.camelot.pmt.caserepertory.model.CaseRepertory;
import com.camelot.pmt.caserepertory.model.CaseRepertoryStep;
import com.camelot.pmt.caserepertory.service.CaseRepertoryService;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 用例库ServiceImpl
 *
 * @author Yurnero
 */
@Service
public class CaseRepertoryServiceImpl implements CaseRepertoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CaseRepertoryServiceImpl.class);
    @Autowired
    private CaseRepertoryMapper caseRepertoryMapper;

    @Autowired
    private CaseRepertoryStepMapper caseRepertoryStepMapper;

    @Autowired
    private ShiroUtils shiroUtils;

    @Override
    @Transactional
    public boolean addCaseRepertoryByCaseid(String ids) {
        String[] params = ids.split(",");
        int result1 = 0;
        User user = (User) shiroUtils.getSessionAttribute("user");
        for (int i = 0; i < params.length; i++) {
            String param = params[i].toString().trim();
            long param1 = Long.parseLong(param);
            CaseRepertory caseRepertory = new CaseRepertory();
            caseRepertory.setId(param1);
            caseRepertory.setCreateUserId(user.getUserId());
            caseRepertory.setCreateTime(new Date());
            caseRepertory.setModifyUserId(user.getUserId());
            caseRepertory.setModifyTime(new Date());
            String num1 = caseRepertoryMapper.querySequence("caser_num");
            caseRepertory.setNum(num1);
            // 复制数据到用例库主表并返回主键id
            result1 = caseRepertoryMapper.addCaseRepertoryByCaseid(caseRepertory);
            // 复制步骤数到用例库步骤表中并且更新父id为 result1
            long id = caseRepertory.getId();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("param1", param1);
            map.put("result1", id);
            int result2 = caseRepertoryMapper.addCaseRepertoryStepByCaseid(map);
        }
        return result1 > 0 ? true : false;
    }

    @Override
    @Transactional
    public boolean addUserCaseByCaseRepertoryid(String ids) {
        int result1 = 0;
        User user = (User) shiroUtils.getSessionAttribute("user");
        String[] params = ids.split(",");
        for (int i = 0; i < params.length; i++) {
            String param = params[i].toString().trim();
            long param1 = Long.parseLong(param);
            CaseRepertory caseRepertory = new CaseRepertory();
            caseRepertory.setId(param1);
            caseRepertory.setCreateUserId(user.getUserId());
            caseRepertory.setCreateTime(new Date());
            caseRepertory.setModifyUserId(user.getUserId());
            caseRepertory.setModifyTime(new Date());
            // 复制数据到用例主表并返回主键id
            result1 = caseRepertoryMapper.addUserCaseByCaseRepertoryid(caseRepertory);
            // 复制步骤数到用例步骤表中并且更新父id为 result1
            long id = caseRepertory.getId();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("param1", param1);
            map.put("result1", id);
            caseRepertoryMapper.addUserCaseStepByCaseRepertoryid(map);
        }
        return result1 > 0 ? true : false;
    }

    /**
     * 新增测试用例库
     * 
     * @param caseRepertory
     *            用例库
     */
    @Override
    @Transactional
    public boolean addCaseRepertory(CaseRepertory caseRepertory) {
        // 新增用例
        User user = (User) shiroUtils.getSessionAttribute("user");
        caseRepertory.setCreateUserId(user.getUserId());
        caseRepertory.setCreateTime(new Date());
        caseRepertory.setModifyUserId(user.getUserId());
        caseRepertory.setModifyTime(new Date());
        String num1 = caseRepertoryMapper.querySequence("caser_num");
        caseRepertory.setNum(num1);
        int result = caseRepertoryMapper.insertSelective(caseRepertory);
        // mybatis返回主键,并设置外键
        Long id = caseRepertory.getId();
        List<CaseRepertoryStep> list = caseRepertory.getDetail();
        // 批量新增用例步骤
        if (list.size() > 0) {
            for (CaseRepertoryStep caseRepertoryStep : list) {
                caseRepertoryStep.setUseCaseId(id);
            }
            caseRepertoryStepMapper.insertBatch(list);
        }
        return result > 0 ? true : false;

    }

    /**
     * 批量新增测试用例库
     *
     * @param list
     *            用例库集合
     */
    @Override
    @Transactional
    public boolean addBatchCaseRepertory(List<CaseRepertory> list) {
        return caseRepertoryMapper.insertBatch(list) > 0 ? true : false;
    }

    /**
     * 删除用例库
     *
     * @param ids
     *            用例库id 逗号隔开
     */
    @Override
    @Transactional
    public boolean deleteCaseRepertory(String ids) {
        Map<String, Object> param = new HashMap<>();

        String[] arr = ids.split(",");
        int[] intArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            intArr[i] = Integer.parseInt(arr[i]);
        }
        // 删除用例
        param.put("ids", intArr);
        int restul = caseRepertoryMapper.remove(param);
        // 删除用例步骤
        param.clear();
        param.put("useCaseIds", intArr);
        caseRepertoryStepMapper.remove(param);
        return restul > 0 ? true : false;
    }

    /**
     * 更新测试用例库
     *
     * @param caseRepertory
     *            用例库
     */
    @Override
    @Transactional
    public boolean updateCaseRepertory(CaseRepertory caseRepertory) {
        // 更新用例库
        User user = (User) shiroUtils.getSessionAttribute("user");
        caseRepertory.setModifyUserId(user.getUserId());
        caseRepertory.setModifyTime(new Date());
        int result = caseRepertoryMapper.updateByPrimaryKeySelective(caseRepertory);
        // 过滤出删除的步骤
        List<Long> ids = new ArrayList<>();
        Long id = caseRepertory.getId();
        List<CaseRepertoryStep> oldList = caseRepertoryStepMapper.findByUseCaseId(id);
        List<CaseRepertoryStep> list = caseRepertory.getDetail();
        for (CaseRepertoryStep caseRepertoryStep1 : oldList) {
            Long oldId = caseRepertoryStep1.getId();
            boolean flag = false;
            for (CaseRepertoryStep caseRepertoryStep2 : list) {
                if (oldId.equals(caseRepertoryStep2.getId())) {
                    flag = false;
                    break;
                } else {
                    flag = true;
                }
            }
            if (flag) {
                ids.add(oldId);
            }
        }
        // 更新用例步骤,若用例步骤ID不为null,则更新步骤,反之插入步骤
        for (CaseRepertoryStep caseRepertoryStep : list) {
            if (caseRepertoryStep.getId() != null) {
                caseRepertoryStepMapper.updateByPrimaryKeySelective(caseRepertoryStep);
            } else {
                caseRepertoryStep.setUseCaseId(id);
                caseRepertoryStepMapper.insertSelective(caseRepertoryStep);
            }
        }
        // 删除不存在的步骤
        if (ids.size() != 0) {
            Map<String, Object> param = new HashMap<>();
            param.put("ids", ids);
            caseRepertoryStepMapper.remove(param);
        }
        return result > 0 ? true : false;
    }

    /**
     * 根据ID查询用例和用例步骤
     *
     * @param id
     *            用例库ID
     * @return 返回用例库
     */
    @Override
    public CaseRepertory queryCaseRepertoryById(Long id) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        List<CaseRepertory> list = caseRepertoryMapper.find(param);
        if (list != null && list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<CaseRepertory> queryCaseRepertoryByPage(CaseRepertory caseRepertory, Integer pageSize,
            Integer currentPage) {
        PageHelper.startPage(currentPage, pageSize);
        List<CaseRepertory> list = caseRepertoryMapper.selectCondition(caseRepertory);
        return list;
    }
}
