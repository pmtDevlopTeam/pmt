package com.camelot.pmt.project.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.camelot.pmt.platform.utils.DataGrid;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.platform.utils.Pager;
import com.camelot.pmt.project.mapper.ProjectBudgetMapper;
import com.camelot.pmt.project.mapper.ProjectMainMapper;
import com.camelot.pmt.project.mapper.ProjectOperateMapper;
import com.camelot.pmt.project.mapper.WarningMapper;
import com.camelot.pmt.project.model.ProjectBudget;
import com.camelot.pmt.project.model.ProjectMain;
import com.camelot.pmt.project.model.ProjectOperate;
import com.camelot.pmt.project.model.Warning;
import com.camelot.pmt.project.service.ProjectMainService;

/**
 * 
 * @author qiaodj
 * @date 2018年4月14日
 */
@Service
public class ProjectMainServiceImpl implements ProjectMainService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectMainServiceImpl.class);
    @Autowired
    private ProjectMainMapper projectMainMapper;
    @Autowired
    private ProjectBudgetMapper projectBudgetMapper;
    @Autowired
    private ProjectOperateMapper projectOperateMapper;
    @Autowired
    private WarningMapper warningMapper;

    /**
     * 保存立项表的方法
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExecuteResult<String> save(ProjectMain projectMain, ProjectOperate projectOperate,
            ProjectBudget projectBudget, Warning warning) {

        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            // 保存projectMain
            projectMain.setCreateTime(new Date());
            projectMain.setModifyTime(new Date());
            projectMainMapper.insert(projectMain);
            // 保存projectOperate
            projectOperate.setProjectId(projectMain.getId());
            projectOperate.setCreateTime(new Date());
            projectOperateMapper.insert(projectOperate);
            // 保存projectBudget
            projectBudget.setProjectId(projectMain.getId());
            projectBudget.setCreateTime(new Date());
            projectBudget.setModifyTime(new Date());
            projectBudgetMapper.insert(projectBudget);
            // 保存warning
            warning.setProId(projectMain.getId());
            warning.setCreateTime(new Date());
            warning.setModifyTime(new Date());
            warningMapper.insert(warning);
            result.setResult("添加数据成功!");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 分页查询
     */
    @Override
    public ExecuteResult<DataGrid<ProjectMain>> findAllByPage(Pager<?> page) {
        ExecuteResult<DataGrid<ProjectMain>> result = new ExecuteResult<DataGrid<ProjectMain>>();
        try {
            List<ProjectMain> list = projectMainMapper.findAllByPage(page);
            // 查询出数据为空的话，直接返回
            if (CollectionUtils.isEmpty(list)) {
                DataGrid<ProjectMain> dg = new DataGrid<ProjectMain>();
                result.setResult(dg);
                return result;
            }
            DataGrid<ProjectMain> dg = new DataGrid<ProjectMain>();
            dg.setRows(list);
            // 查询总条数
            Long total = projectMainMapper.findAll();
            dg.setTotal(total);
            result.setResult(dg);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 按状态分类查询
     * 
     * @param projectStatus
     * @return
     */
    @Override
    public ExecuteResult<List<ProjectMain>> findByProjectStatus(String projectStatus) {
        ExecuteResult<List<ProjectMain>> result = new ExecuteResult<List<ProjectMain>>();
        try {
            List<ProjectMain> list = projectMainMapper.findByProjectStatus(projectStatus);
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
     * 按负责人id查询
     * 
     * @param userId
     * @return
     */
    @Override
    public ExecuteResult<List<ProjectMain>> findByUserId(String userId) {
        ExecuteResult<List<ProjectMain>> result = new ExecuteResult<List<ProjectMain>>();
        try {
            List<ProjectMain> list = projectMainMapper.findByUserId(userId);
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
     * 按创建人id查询
     * 
     * @param createUserId
     * @return
     */
    @Override
    public ExecuteResult<List<ProjectMain>> findByCreateUserId(String createUserId) {
        ExecuteResult<List<ProjectMain>> result = new ExecuteResult<List<ProjectMain>>();
        try {
            List<ProjectMain> list = projectMainMapper.findByCreateUserId(createUserId);
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
     * 按修改人id查询
     * 
     * @param createUserId
     * @return
     */
    @Override
    public ExecuteResult<List<ProjectMain>> findByModifyUserId(String modifyUserId) {
        ExecuteResult<List<ProjectMain>> result = new ExecuteResult<List<ProjectMain>>();
        try {
            List<ProjectMain> list = projectMainMapper.findByModifyUserId(modifyUserId);
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
     * 按照主键id进行修改
     * 
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExecuteResult<String> updateByPrimaryKeySelective(ProjectMain projectMain, String createUserId,
            String operateDesc) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            projectMainMapper.updateByPrimaryKeySelective(projectMain);

            ProjectOperate projectOperate = new ProjectOperate();
            projectOperate.setCreateTime(new Date());
            projectOperate.setProjectId(projectMain.getId());
            projectOperate.setCreateUserId(createUserId);
            projectOperate.setOperateDesc(operateDesc);

            projectOperateMapper.insert(projectOperate);
            result.setResult("更新数据成功!");

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 删除项目 项目成员表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExecuteResult<String> deleteByPrimaryKey(Long id, String createUserId, String operateDesc) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            ProjectMain projectMainSelect = projectMainMapper.selectByPrimaryKey(id);
            if (projectMainSelect != null && "01".equals(projectMainSelect.getProjectStatus())) {
                projectMainMapper.deleteByPrimaryKey(id);

                ProjectOperate projectOperate = new ProjectOperate();
                projectOperate.setCreateTime(new Date());
                projectOperate.setProjectId(id);
                projectOperate.setCreateUserId(createUserId);
                projectOperate.setOperateDesc(operateDesc);

                projectOperateMapper.insert(projectOperate);
                result.setResult("删除数据成功！");
            } else {
                result.setResult("项目进行中，不允许删除！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 根据项目主键查询
     */
    @Override
    public ExecuteResult<ProjectMain> selectByPrimaryKey(Long id) {
        ExecuteResult<ProjectMain> result = new ExecuteResult<ProjectMain>();
        try {
            ProjectMain projectMain = projectMainMapper.selectByPrimaryKey(id);
            if (projectMain == null) {
                return result;
            }
            result.setResult(projectMain);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }
}
