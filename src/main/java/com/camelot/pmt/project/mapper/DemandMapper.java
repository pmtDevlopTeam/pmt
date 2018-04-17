package com.camelot.pmt.project.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.camelot.pmt.platform.utils.Pager;
import com.camelot.pmt.project.model.Demand;

public interface DemandMapper {
    /**
     *
     * @mbggenerated 2018-04-13
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insert(Demand record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int insertSelective(Demand record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    Demand selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeySelective(Demand record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKeyWithBLOBs(Demand record);

    /**
     *
     * @mbggenerated 2018-04-13
     */
    int updateByPrimaryKey(Demand record);

    /**
     * 查询需求分页
     * 
     * @param demand
     * @return
     */
    List<Demand> findAllByPage(@Param(value = "pager") Pager pager,
            @Param(value = "demand") Demand demand);

    Long queryCount( @Param(value = "demand")Demand demand);
}