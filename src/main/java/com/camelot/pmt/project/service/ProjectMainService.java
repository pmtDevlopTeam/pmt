package com.camelot.pmt.project.service;

import java.util.Date;
import java.util.List;

import com.camelot.pmt.common.DataGrid;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.common.Pager;
import com.camelot.pmt.project.model.ProjectBudget;
import com.camelot.pmt.project.model.ProjectMain;
import com.camelot.pmt.project.model.ProjectOperate;
import com.camelot.pmt.project.model.Warning;

/**
 * 
 * @author qiaodj
 * @date 2018年4月14日
 */
public interface ProjectMainService {
    /**
     * 根据项目主键查询
     * 
     * @param id
     * @return
     */
    ExecuteResult<ProjectMain> selectByPrimaryKey(Long id);

    /**
     * 保存立项时相关联表数据
     * 
     * @param projectMain
     * @param projectOperate
     * @param projectBudget
     * @param warning
     * @return
     */
    ExecuteResult<String> save(ProjectMain projectMain, ProjectOperate projectOperate, ProjectBudget projectBudget,
            Warning warning);

    /**
     * 分页查询
     * 
     * @param page
     * @return
     */
    ExecuteResult<DataGrid<ProjectMain>> findAllByPage(Pager<?> page);

    /**
     * 按状态分类查询
     * 
     * @param projectStatus
     * @return
     */
    ExecuteResult<List<ProjectMain>> findByProjectStatus(String projectStatus);

    /**
     * 按负责人id查询
     * 
     * @param userId
     * @return
     */
    ExecuteResult<List<ProjectMain>> findByUserId(String userId);

    /**
     * 按创建人id查询
     * 
     * @param createUserId
     * @return
     */
    ExecuteResult<List<ProjectMain>> findByCreateUserId(String createUserId);

    /**
     * 按修改人id查询
     * 
     * @param modifyUserId
     * @return
     */
    ExecuteResult<List<ProjectMain>> findByModifyUserId(String modifyUserId);

    /**
     * 按主键id进行更新
     * 
     * @param id
     * @param userId
     * @param modifyUserId
     * @param modifyTime
     * @param projectNum
     * @param projectName
     * @param projectStatus
     * @param projectDesc
     * @param startTime
     * @param endTime
     * @param createUserId
     * @param operateDesc
     * @return
     */
    ExecuteResult<String> updateByPrimaryKeySelective(Long id, String userId, String modifyUserId, String projectNum,
            String projectName, String projectStatus, String projectDesc, Date startTime, Date endTime,
            String createUserId, String operateDesc);

    /**
     * 根据id删除项目
     * 
     * @param id
     * @param createUserId
     * @param operateDesc
     * @return
     */
    ExecuteResult<String> deleteByPrimaryKey(Long id, String createUserId, String operateDesc);

    /**
     * 关闭时，更新相关数据
     * 
     * @param id
     * @param createUserId
     * @param modifyUserId
     * @param projectStatus
     * @param operateDesc
     * @param userStatus
     * @param demandStatus
     * @param closeReason
     * @param status
     * @param caseStatus
     * @return
     */
    ExecuteResult<String> closeProjectById(Long id, String createUserId, String modifyUserId, String projectStatus,
            String operateDesc, String userStatus, String demandStatus, String closeReason, String status,
            String caseStatus);

}
