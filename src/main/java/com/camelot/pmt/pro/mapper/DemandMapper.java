package com.camelot.pmt.pro.mapper;

import com.camelot.pmt.pro.model.Demand;
import com.camelot.pmt.pro.model.DemandWithBLOBs;

public interface DemandMapper {
    int deleteByPrimaryKey(Integer reqId);

    int insert(DemandWithBLOBs record);

    int insertSelective(DemandWithBLOBs record);

    DemandWithBLOBs selectByPrimaryKey(Integer reqId);

    int updateByPrimaryKeySelective(DemandWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(DemandWithBLOBs record);

    int updateByPrimaryKey(Demand record);
}