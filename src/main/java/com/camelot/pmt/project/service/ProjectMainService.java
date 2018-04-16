package com.camelot.pmt.project.service;

import java.util.List;

import com.camelot.pmt.platform.utils.DataGrid;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.platform.utils.Pager;
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
     * @param warning
     * @param projectBudget
     * @param projectOperate
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
     * @param projectMain
     * @return
     */
    ExecuteResult<String> updateByPrimaryKeySelective(ProjectMain projectMain, String createUserId, String operateDesc);

    /**
     * 删除项目
     * 
     * @param id
     * @return
     */
    ExecuteResult<String> deleteByPrimaryKey(Long id, String createUserId, String operateDesc);
}
