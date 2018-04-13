package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.DemandOperate;

public interface DemandOperateMapper {
    /**
     *
     * @mbggenerated 2018-04-13
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insert(DemandOperate record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insertSelective(DemandOperate record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    DemandOperate selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeySelective(DemandOperate record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeyWithBLOBs(DemandOperate record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKey(DemandOperate record);
}