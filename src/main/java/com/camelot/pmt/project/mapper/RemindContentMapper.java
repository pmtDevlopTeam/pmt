package com.camelot.pmt.project.mapper;

import java.util.List;

import com.camelot.pmt.project.model.RemindContent;

public interface RemindContentMapper {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int insert(RemindContent record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(RemindContent record);

    /**
     *
     * @mbggenerated
     */
    RemindContent selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RemindContent record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RemindContent record);

    int deleteByProjectId(Long projectId);

    List<RemindContent> queryByProjectId(Long projectId);

    List<RemindContent> queryByRemindId(Long remindId);

    int deleteByRemindIdList(List<RemindContent> remindContentList);
}