package com.camelot.pmt.project.service.impl;

import java.util.List;

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
     * 按创建人id查询查询对应的操作表数据
     */
    @Override
    public List<ProjectOperate> queryByCreateUserId(String createUserId) {
        List<ProjectOperate> list = null;
        try {
            list = projectOperateMapper.queryByCreateUserId(createUserId);
            if (list.size() <= 0) {
                return null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return list;
    }

    /**
     * 按项目id查询对应的操作表数据
     */
    @Override
    public List<ProjectOperate> queryByProjectId(Long projectId) {
        List<ProjectOperate> list = null;
        try {
            list = projectOperateMapper.queryByProjectId(projectId);
            if (list.size() <= 0) {
                return null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return list;
    }
}
