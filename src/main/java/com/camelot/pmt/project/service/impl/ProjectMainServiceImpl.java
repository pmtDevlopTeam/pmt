package com.camelot.pmt.project.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camelot.pmt.common.GetDutys;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.project.mapper.DemandMapper;
import com.camelot.pmt.project.mapper.ProjectBudgetMapper;
import com.camelot.pmt.project.mapper.ProjectMainMapper;
import com.camelot.pmt.project.mapper.ProjectOperateMapper;
import com.camelot.pmt.project.mapper.ProjectUserMapper;
import com.camelot.pmt.project.model.ProjectBudget;
import com.camelot.pmt.project.model.ProjectMain;
import com.camelot.pmt.project.model.ProjectOperate;
import com.camelot.pmt.project.service.ProjectMainService;
import com.camelot.pmt.task.mapper.TaskMapper;
import com.camelot.pmt.testmanage.casemanage.mapper.UseCaseMapper;
import com.github.pagehelper.PageHelper;

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
    private DemandMapper demandMapper;
    @Autowired
    private ProjectUserMapper projectUserMapper;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private UseCaseMapper useCaseMapper;

    /**
     * 保存项目表以及相关表的方法
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addProject(String userId, String projectName, String projectStatus, Date startTime, Date endTime,
            String projectDesc) {
        int projectMainNum = 0;
        int projectOperateNum = 0;
        int projectBudgetNum = 0;
        try {
            // 保存projectMain
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (user != null && user.getUserId() != null) {
                ProjectMain projectMain = new ProjectMain();
                projectMain.setUserId(userId);
                projectMain.setProjectName(projectName);
                projectMain.setProjectStatus(projectStatus);
                projectMain.setStartTime(startTime);
                projectMain.setEndTime(endTime);
                projectMain.setProjectDesc(projectDesc);
                projectMain.setCreateUserId(user.getUserId());
                projectMain.setModifyUserId(user.getUserId());
                projectMain.setCreateTime(new Date());
                projectMain.setModifyTime(new Date());
                projectMain.setProjectNum("0000000001");// 编码需要确定
                projectMainNum = projectMainMapper.addProject(projectMain);
                // 保存projectOperate
                ProjectOperate projectOperate = new ProjectOperate();
                projectOperate.setCreateUserId(user.getUserId());
                projectOperate.setProjectId(projectMain.getId());
                projectOperate.setCreateTime(new Date());
                projectOperate.setOperateDesc(user.getUsername() + "     新增项目");
                projectOperateNum = projectOperateMapper.addProjectOperate(projectOperate);
                // 保存projectBudget
                ProjectBudget projectBudget = new ProjectBudget();
                projectBudget.setProjectId(projectMain.getId());
                projectBudget.setCreateUserId(user.getUserId());
                projectBudget.setModifyUserId(user.getUserId());
                projectBudget.setCreateTime(new Date());
                projectBudget.setModifyTime(new Date());
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String startTime1 = df.format(startTime);
                String endTime1 = df.format(endTime);
                projectBudget.setBudgetaryHours(GetDutys.getDutyDays(startTime1, endTime1) * 8);
                projectBudgetNum = projectBudgetMapper.addProjectBudget(projectBudget);
                if (projectMainNum > 0 && projectOperateNum > 0 && projectBudgetNum > 0) {
                    return 1;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return 0;
    }

    /**
     * 按项目状态分类查询
     */
    @Override
    public List<ProjectMain> queryByProjectStatus(String projectStatus) {
        List<ProjectMain> list = null;
        try {
            list = projectMainMapper.queryByProjectStatus(projectStatus);
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
     * 按负责人id查询
     */
    @Override
    public List<ProjectMain> queryByUserId(String userId) {
        List<ProjectMain> list = null;
        try {
            list = projectMainMapper.queryByUserId(userId);
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
     * 按创建人id查询
     */
    @Override
    public List<ProjectMain> queryByCreateUserId(String createUserId) {
        List<ProjectMain> list = null;
        try {
            list = projectMainMapper.queryByCreateUserId(createUserId);
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
     * 按修改人id查询
     */
    @Override
    public List<ProjectMain> queryByModifyUserId(String modifyUserId) {
        List<ProjectMain> list = null;
        try {
            list = projectMainMapper.queryByModifyUserId(modifyUserId);
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
     * 按照主键id进行修改
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(Long id, String userId, String projectName, String projectStatus,
            String projectDesc, Date startTime, Date endTime) {
        int projectMainNum = 0;
        int projectOperateNum = 0;
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (user != null && user.getUserId() != null) {
                projectMainNum = projectMainMapper.updateByPrimaryKeySelective(id, userId, user.getUserId(), new Date(),
                        "0000000009", projectName, projectStatus, projectDesc, startTime, endTime);
                ProjectOperate projectOperate = new ProjectOperate();
                projectOperate.setCreateTime(new Date());
                projectOperate.setProjectId(id);
                projectOperate.setCreateUserId(user.getUserId());
                projectOperate.setOperateDesc(user.getUsername() + "    更新项目");
                projectOperateNum = projectOperateMapper.addProjectOperate(projectOperate);
                if (projectMainNum > 0 && projectOperateNum > 0) {
                    return 1;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return 0;
    }

    /**
     * 删除项目 项目成员表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Long id) {
        int projectMainNum = 0;
        int projectOperateNum = 0;
        try {

            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (user != null && user.getUserId() != null) {
                ProjectMain projectMainSelect = projectMainMapper.queryByPrimaryKey(id);
                if (projectMainSelect != null && "01".equals(projectMainSelect.getProjectStatus())) {
                    projectMainNum = projectMainMapper.deleteByPrimaryKey(id);

                    ProjectOperate projectOperate = new ProjectOperate();
                    projectOperate.setCreateTime(new Date());
                    projectOperate.setProjectId(id);
                    projectOperate.setCreateUserId(user.getUserId());
                    projectOperate.setOperateDesc(user.getUsername() + "    删除项目");
                    projectOperateNum = projectOperateMapper.addProjectOperate(projectOperate);
                    if (projectMainNum > 0 && projectOperateNum > 0) {
                        return 1;
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return 0;
    }

    /**
     * 根据项目主键查询
     */
    @Override
    public ProjectMain queryByPrimaryKey(Long id) {
        ProjectMain projectMain = null;
        try {
            projectMain = projectMainMapper.queryByPrimaryKey(id);
            if (projectMain == null) {
                return null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return projectMain;
    }

    /**
     * 关闭项目时，更新相关表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByProjectById(Long id, String projectStatus, String userStatus, String demandStatus,
            String closeReason, String status, String caseStatus) {
        int projectMainNum = 0;
        int projectUserNum = 0;
        int projectOperateNum = 0;
        int demandNum = 0;
        int taskNum = 0;
        int useCaseNum = 0;
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (user != null && user.getUserId() != null) {
                // projectMain中项目id 修改人 修改时间 项目状态修改
                projectMainNum = projectMainMapper.updateById(id, projectStatus, user.getUserId(), new Date());
                // ProjectUser项目成员表成员状态修改
                projectUserNum = projectUserMapper.updateUserStatusByProjectId(id, new Date(), userStatus,
                        user.getUserId(), new Date());
                // projectOperate项目操作表存数据
                ProjectOperate projectOperate = new ProjectOperate();
                projectOperate.setCreateTime(new Date());
                projectOperate.setProjectId(id);
                projectOperate.setCreateUserId(user.getUserId());
                projectOperate.setOperateDesc(user.getUsername() + "    关闭项目时，更新相关表");
                projectOperateNum = projectOperateMapper.addProjectOperate(projectOperate);
                // demand需求表更改状态
                demandNum = demandMapper.updateByProjectId(id, demandStatus, closeReason, user.getUserId(), new Date());
                // task任务表更改状态
                taskNum = taskMapper.updateByProjectId(id, status, new Date(), user.getUserId(), new Date());
                // userCase用例状态修改
                useCaseNum = useCaseMapper.updateByProjectId(id, caseStatus, user.getUserId(), new Date());
                if (projectMainNum > 0 && projectOperateNum > 0 && projectUserNum > 0 && demandNum > 0 && taskNum > 0
                        && useCaseNum > 0) {
                    return 1;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return 0;
    }

    /**
     * 分页查询
     */
    @Override
    public List<ProjectMain> queryAllByPage(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        try {
            List<ProjectMain> list = projectMainMapper.queryAllByPage();
            if (list.size() > 0) {
                return list;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }
}
