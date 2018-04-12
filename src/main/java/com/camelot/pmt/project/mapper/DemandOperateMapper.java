package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.DemandOperate;

public interface DemandOperateMapper {
    /**
     *
     * @mbggenerated 2018-04-12
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    int insert(DemandOperate record);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    int insertSelective(DemandOperate record);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    DemandOperate selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    int updateByPrimaryKeySelective(DemandOperate record);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    int updateByPrimaryKeyWithBLOBs(DemandOperate record);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    int updateByPrimaryKey(DemandOperate record);
}