package com.camelot.pmt.project.mapper;

import java.util.List;

import com.camelot.pmt.project.model.ProjectOperate;

public interface ProjectOperateMapper {
    /**
     * 根据项目id进行查询
     * 
     * @param createUserId
     * @return
     */
    List<ProjectOperate> findByProjectId(Long projectId);

    /**
     * 根据创建人id进行查询
     * 
     * @param createUserId
     * @return
     */
    List<ProjectOperate> findByCreateUserId(String createUserId);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insert(ProjectOperate record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insertSelective(ProjectOperate record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    ProjectOperate selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeySelective(ProjectOperate record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeyWithBLOBs(ProjectOperate record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKey(ProjectOperate record);
}