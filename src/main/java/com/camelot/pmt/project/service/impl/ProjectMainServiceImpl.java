package com.camelot.pmt.project.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.camelot.pmt.common.DataGrid;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.common.Pager;
import com.camelot.pmt.project.mapper.DemandMapper;
import com.camelot.pmt.project.mapper.ProjectBudgetMapper;
import com.camelot.pmt.project.mapper.ProjectMainMapper;
import com.camelot.pmt.project.mapper.ProjectOperateMapper;
import com.camelot.pmt.project.mapper.ProjectUserMapper;
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
    @Autowired
    private DemandMapper demandMapper;
    @Autowired
    private ProjectUserMapper projectUserMapper;

    /**
     * 保存立项表的方法
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExecuteResult<String> addProject(ProjectMain projectMain, ProjectOperate projectOperate,
            ProjectBudget projectBudget, Warning warning) {

        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            // 保存projectMain
            projectMain.setCreateTime(new Date());
            projectMain.setModifyTime(new Date());
            projectMainMapper.addProject(projectMain);
            // 保存projectOperate
            projectOperate.setProjectId(projectMain.getId());
            projectOperate.setCreateTime(new Date());
            projectOperateMapper.addProjectOperate(projectOperate);
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
    public ExecuteResult<DataGrid<ProjectMain>> queryAllByPage(Pager<?> page) {
        ExecuteResult<DataGrid<ProjectMain>> result = new ExecuteResult<DataGrid<ProjectMain>>();
        try {
            List<ProjectMain> list = projectMainMapper.queryAllByPage(page);
            // 查询出数据为空的话，直接返回
            if (CollectionUtils.isEmpty(list)) {
                DataGrid<ProjectMain> dg = new DataGrid<ProjectMain>();
                result.setResult(dg);
                return result;
            }
            DataGrid<ProjectMain> dg = new DataGrid<ProjectMain>();
            dg.setRows(list);
            // 查询总条数
            Long total = projectMainMapper.queryAll();
            dg.setTotal(total);
            result.setResult(dg);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 按项目状态分类查询
     */
    @Override
    public ExecuteResult<List<ProjectMain>> queryByProjectStatus(String projectStatus) {
        ExecuteResult<List<ProjectMain>> result = new ExecuteResult<List<ProjectMain>>();
        try {
            List<ProjectMain> list = projectMainMapper.queryByProjectStatus(projectStatus);
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
     */
    @Override
    public ExecuteResult<List<ProjectMain>> queryByUserId(String userId) {
        ExecuteResult<List<ProjectMain>> result = new ExecuteResult<List<ProjectMain>>();
        try {
            List<ProjectMain> list = projectMainMapper.queryByUserId(userId);
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
     */
    @Override
    public ExecuteResult<List<ProjectMain>> queryByCreateUserId(String createUserId) {
        ExecuteResult<List<ProjectMain>> result = new ExecuteResult<List<ProjectMain>>();
        try {
            List<ProjectMain> list = projectMainMapper.queryByCreateUserId(createUserId);
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
     */
    @Override
    public ExecuteResult<List<ProjectMain>> queryByModifyUserId(String modifyUserId) {
        ExecuteResult<List<ProjectMain>> result = new ExecuteResult<List<ProjectMain>>();
        try {
            List<ProjectMain> list = projectMainMapper.queryByModifyUserId(modifyUserId);
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
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExecuteResult<String> updateByPrimaryKeySelective(Long id, String userId, String modifyUserId,
            String projectNum, String projectName, String projectStatus, String projectDesc, Date startTime,
            Date endTime, String createUserId, String operateDesc) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            projectMainMapper.updateByPrimaryKeySelective(id, userId, modifyUserId, new Date(), projectNum, projectName,
                    projectStatus, projectDesc, startTime, endTime);

            ProjectOperate projectOperate = new ProjectOperate();
            projectOperate.setCreateTime(new Date());
            projectOperate.setProjectId(id);
            projectOperate.setCreateUserId(createUserId);
            projectOperate.setOperateDesc(operateDesc);

            projectOperateMapper.addProjectOperate(projectOperate);
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
            ProjectMain projectMainSelect = projectMainMapper.queryByPrimaryKey(id);
            if (projectMainSelect != null && "01".equals(projectMainSelect.getProjectStatus())) {
                projectMainMapper.deleteByPrimaryKey(id);

                ProjectOperate projectOperate = new ProjectOperate();
                projectOperate.setCreateTime(new Date());
                projectOperate.setProjectId(id);
                projectOperate.setCreateUserId(createUserId);
                projectOperate.setOperateDesc(operateDesc);

                projectOperateMapper.addProjectOperate(projectOperate);
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
    public ExecuteResult<ProjectMain> queryByPrimaryKey(Long id) {
        ExecuteResult<ProjectMain> result = new ExecuteResult<ProjectMain>();
        try {
            ProjectMain projectMain = projectMainMapper.queryByPrimaryKey(id);
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

    /**
     * 关闭时，更新相关数据
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExecuteResult<String> updateByProjectById(Long id, String createUserId, String modifyUserId,
            String projectStatus, String operateDesc, String userStatus, String demandStatus, String closeReason,
            String status, String caseStatus) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            // projectMain中项目id 修改人 修改时间 项目状态修改
            projectMainMapper.updateById(id, projectStatus, modifyUserId, new Date());
            // ProjectUser项目成员表成员状态修改
            projectUserMapper.updateUserStatusByProjectId(id, new Date(), userStatus, modifyUserId, new Date());
            // 项目操作表存数据
            ProjectOperate projectOperate = new ProjectOperate();
            projectOperate.setCreateTime(new Date());
            projectOperate.setProjectId(id);
            projectOperate.setCreateUserId(createUserId);
            projectOperate.setOperateDesc(operateDesc);
            projectOperateMapper.addProjectOperate(projectOperate);
            // demand需求表更改状态
            demandMapper.updateByProjectId(id, demandStatus, closeReason, modifyUserId, new Date());
            // task任务表更改状态

            // userCase用例状态修改

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

}
