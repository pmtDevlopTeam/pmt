package com.camelot.pmt.Statistics.mapper;

import com.camelot.pmt.Statistics.model.StatisticsUserCase;

import java.util.List;

public interface StatisticsMapper {
    List<StatisticsUserCase> statisticsUserCase(Long projectId);

    List<StatisticsUserCase> statisticsVersionUserCase(Long projectId);

    List<StatisticsUserCase> statisticsUserCaseByType(Long projectId);
}
