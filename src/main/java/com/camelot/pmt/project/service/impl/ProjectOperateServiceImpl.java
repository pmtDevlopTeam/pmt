package com.camelot.pmt.project.service.impl;

import java.util.List;

import com.camelot.pmt.common.ExecuteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camelot.pmt.project.mapper.ProjectOperateMapper;
import com.camelot.pmt.project.model.ProjectOperate;
import com.camelot.pmt.project.service.ProjectOperateService;

/**
 * 
 * @author qiaodj
 * @date 2018年4月16日
 */
@Service
public class ProjectOperateServiceImpl implements ProjectOperateService {
    private static final Logger logger = LoggerFactory.getLogger(ProjectMainServiceImpl.class);
    @Autowired
    private ProjectOperateMapper projectOperateMapper;

    /**
     * 按创建人id查询
     * 
     * @param createUserId
     * @return
     */
    @Override
    public ExecuteResult<List<ProjectOperate>> findByCreateUserId(String createUserId) {

        ExecuteResult<List<ProjectOperate>> result = new ExecuteResult<>();
        try {
            List<ProjectOperate> list = projectOperateMapper.findByCreateUserId(createUserId);
            if (list.size() <= 0) {
                return result;
            }
            result.setResult(list);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 按项目id查询
     * 
     * @param createUserId
     * @return
     */
    @Override
    public ExecuteResult<List<ProjectOperate>> findByProjectId(Long projectId) {
        ExecuteResult<List<ProjectOperate>> result = new ExecuteResult<>();
        try {
            List<ProjectOperate> list = projectOperateMapper.findByProjectId(projectId);
            if (list.size() <= 0) {
                return result;
            }
            result.setResult(list);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }
}
