package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.Project;

public interface ProjectMapper {
    int deleteByPrimaryKey(Integer proId);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Integer proId);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKeyWithBLOBs(Project record);

    int updateByPrimaryKey(Project record);

}