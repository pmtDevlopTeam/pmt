package com.camelot.pmt.project.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.camelot.pmt.common.BussinessException;
import com.camelot.pmt.common.ComEntity;
import com.camelot.pmt.common.GetJsonFormat;
import com.camelot.pmt.common.IncrementNumber;
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
     * 新增项目
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addProject(String projectName, String projectStatus, String projectDesc) {
        int projectMainNum = 0;
        int projectOperateNum = 0;
        try {
            // 保存projectMain
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (user != null && user.getUserId() != null) {
                ProjectMain projectMain = new ProjectMain();
                projectMain.setUserId(user.getUserId());
                projectMain.setProjectName(projectName);
                projectMain.setProjectStatus(projectStatus);
                projectMain.setProjectDesc(projectDesc);
                projectMain.setCreateUserId(user.getUserId());
                projectMain.setModifyUserId(user.getUserId());
                projectMain.setCreateTime(new Date());
                projectMain.setModifyTime(new Date());
                // 查询数据库最大的项目编号
                String maxProjectNum = projectMainMapper.getMaxProjectNum();
                String generProjectNum = IncrementNumber.getIncreNum(maxProjectNum);
                projectMain.setProjectNum(generProjectNum);
                projectMainNum = projectMainMapper.addProject(projectMain);
                // 保存projectOperate
                ProjectOperate projectOperate = new ProjectOperate();
                projectOperate.setCreateUserId(user.getUserId());
                projectOperate.setProjectId(projectMain.getId());
                projectOperate.setCreateTime(new Date());
                ComEntity<ProjectMain> CompareEntity = new ComEntity<>();
                List<String> compareT = CompareEntity.compareT(new ProjectMain(), projectMain, ProjectMain.class);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                projectOperate.setOperateDesc(sdf.format(new Date()) + "    新增：    "
                        + GetJsonFormat.getJsonFormat(JSON.toJSON(compareT).toString()));
                projectOperateNum = projectOperateMapper.addProjectOperate(projectOperate);
                if (projectMainNum > 0 && projectOperateNum > 0) {
                    return 1;
                }
                throw new BussinessException("新增项目失败");
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
     * 按主键id对项目进行编辑
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(Long id, String userId, String projectName, String projectStatus,
            String projectDesc, Date startTime, Date endTime, Integer budgetaryHours, String projectVisible) {
        int projectMainNum = 0;
        int projectOperateNum = 0;
        int projectBudgetNum = 0;
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (user != null && user.getUserId() != null) {
                // 更新前的数据
                ProjectMain projectMain = projectMainMapper.queryByPrimaryKey(id);
                projectMainNum = projectMainMapper.updateByPrimaryKeySelective(id, userId, user.getUserId(), new Date(),
                        projectName, projectStatus, projectDesc, startTime, endTime, projectVisible);
                // 保存项目操作表数据
                ProjectOperate projectOperate = new ProjectOperate();
                projectOperate.setCreateTime(new Date());
                projectOperate.setProjectId(id);
                projectOperate.setCreateUserId(user.getUserId());
                // 更新后的数据
                ProjectMain projectMain2 = projectMainMapper.queryByPrimaryKey(id);
                ComEntity<ProjectMain> CompareEntity = new ComEntity<>();
                List<String> compareT = CompareEntity.compareT(projectMain, projectMain2, ProjectMain.class);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                projectOperate.setOperateDesc(sdf.format(new Date()) + "    根据id进行更新：    "
                        + GetJsonFormat.getJsonFormat(JSON.toJSON(compareT).toString()));
                projectOperateNum = projectOperateMapper.addProjectOperate(projectOperate);
                // 保存projectBudget
                ProjectBudget projectBudget = new ProjectBudget();
                projectBudget.setProjectId(id);
                projectBudget.setCreateUserId(user.getUserId());
                projectBudget.setModifyUserId(user.getUserId());
                projectBudget.setCreateTime(new Date());
                projectBudget.setModifyTime(new Date());
                projectBudget.setBudgetaryHours(budgetaryHours);
                projectBudgetNum = projectBudgetMapper.addProjectBudget(projectBudget);
                if (projectMainNum > 0 && projectOperateNum > 0 && projectBudgetNum > 0) {
                    return 1;
                }
                throw new BussinessException("按主键id对项目进行编辑");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return 0;
    }

    /**
     * 删除项目 只有未开始的项目才可以删除
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

                    ComEntity<ProjectMain> CompareEntity = new ComEntity<>();
                    List<String> compareT = CompareEntity.compareT(projectMainSelect, new ProjectMain(),
                            ProjectMain.class);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    projectOperate.setOperateDesc(sdf.format(new Date()) + "    未开始的项目进行删除：    "
                            + GetJsonFormat.getJsonFormat(JSON.toJSON(compareT).toString()));
                    projectOperateNum = projectOperateMapper.addProjectOperate(projectOperate);
                    if (projectMainNum > 0 && projectOperateNum > 0) {
                        return 1;
                    }
                    throw new BussinessException("未开始的项目删除失败");
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
                // 更新前数据
                ProjectMain projectMain = projectMainMapper.queryByPrimaryKey(id);
                projectMainNum = projectMainMapper.updateById(id, projectStatus, user.getUserId(), new Date());
                // 更新后的数据
                ProjectMain projectMain2 = projectMainMapper.queryByPrimaryKey(id);
                // ProjectUser项目成员表成员状态修改
                projectUserNum = projectUserMapper.updateUserStatusByProjectId(id, new Date(), userStatus,
                        user.getUserId(), new Date());
                // projectOperate项目操作表存数据
                ProjectOperate projectOperate = new ProjectOperate();
                projectOperate.setCreateTime(new Date());
                projectOperate.setProjectId(id);
                projectOperate.setCreateUserId(user.getUserId());

                ComEntity<ProjectMain> CompareEntity = new ComEntity<>();
                List<String> compareT = CompareEntity.compareT(projectMain, projectMain2, ProjectMain.class);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                projectOperate.setOperateDesc(sdf.format(new Date()) + "    关闭项目时，更新相关表数据：    "
                        + GetJsonFormat.getJsonFormat(JSON.toJSON(compareT).toString()));
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
                throw new BussinessException("关闭项目时，更新相关表数据失败");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return 0;
    }

    /**
     * 分页查询所有项目
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

    /**
     * 挂起项目 只有开始的项目才可以挂起
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByIdSuspension(Long id, String projectStatus) {
        int projectMainNum = 0;
        int projectOperateNum = 0;
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (user != null && user.getUserId() != null) {
                // 更新前数据
                ProjectMain projectMain = projectMainMapper.queryByPrimaryKey(id);
                if (!"01".equals(projectMain.getProjectStatus())) {

                    projectMainNum = projectMainMapper.updateByIdSuspension(id, user.getUserId(), new Date(),
                            projectStatus);
                    // 更新后数据
                    ProjectMain projectMain2 = projectMainMapper.queryByPrimaryKey(id);
                    ProjectOperate projectOperate = new ProjectOperate();
                    projectOperate.setCreateTime(new Date());
                    projectOperate.setProjectId(id);
                    projectOperate.setCreateUserId(user.getUserId());

                    ComEntity<ProjectMain> CompareEntity = new ComEntity<>();
                    List<String> compareT = CompareEntity.compareT(projectMain, projectMain2, ProjectMain.class);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    projectOperate.setOperateDesc(sdf.format(new Date()) + "    挂起项目，更新项目状态：    "
                            + GetJsonFormat.getJsonFormat(JSON.toJSON(compareT).toString()));
                    projectOperateNum = projectOperateMapper.addProjectOperate(projectOperate);
                    if (projectMainNum > 0 && projectOperateNum > 0) {
                        return 1;
                    }
                    throw new BussinessException("挂起项目时，更新项目状态失败");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return 0;
    }

    /**
     * 分页查询所有项目（包括个人私有的+公开的项目）
     */
    @Override
    public List<ProjectMain> queryAllPublicAndPrivate(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        try {
            List<ProjectMain> list = projectMainMapper.queryAllPublicAndPrivate();
            if (list.size() > 0) {
                return list;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * 根据用户id,查询每个项目成员参加的项目
     */
    @Override
    public List<ProjectMain> queryByUserIdPersonal(String userId) {
        try {
            List<ProjectMain> list = projectMainMapper.queryByUserIdPersonal(userId);
            if (list.size() > 0) {
                return list;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;

    }

    /**
     * 查询所有公开项目
     */
    @Override
    public List<ProjectMain> queryAllByPublic() {
        try {
            List<ProjectMain> list = projectMainMapper.queryAllByPublic();
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
