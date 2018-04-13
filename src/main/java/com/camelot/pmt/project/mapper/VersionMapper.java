package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.Version;

public interface VersionMapper {
    /**
     *
     * @mbggenerated 2018-04-13
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insert(Version record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insertSelective(Version record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    Version selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeySelective(Version record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKey(Version record);
}