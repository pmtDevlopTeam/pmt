package com.camelot.pmt.project.mapper;

import com.camelot.pmt.project.model.Demand;
import com.camelot.pmt.project.model.DemandWithBLOBs;

public interface DemandMapper {
    /**
     *
     * @mbggenerated 2018-04-12
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    int insert(DemandWithBLOBs record);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    int insertSelective(DemandWithBLOBs record);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    DemandWithBLOBs selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    int updateByPrimaryKeySelective(DemandWithBLOBs record);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    int updateByPrimaryKeyWithBLOBs(DemandWithBLOBs record);

    /**
     *
     * @mbggenerated 2018-04-12
     */
    int updateByPrimaryKey(Demand record);
}