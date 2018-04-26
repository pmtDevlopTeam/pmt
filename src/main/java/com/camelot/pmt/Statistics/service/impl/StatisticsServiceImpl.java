package com.camelot.pmt.Statistics.service.impl;

import com.camelot.pmt.Statistics.mapper.StatisticsMapper;
import com.camelot.pmt.Statistics.model.StatisticsUserCase;
import com.camelot.pmt.Statistics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService{
    @Autowired
    private StatisticsMapper statisticsMapper;
    @Override
    public List<StatisticsUserCase> statisticsUserCase(Long projectId) {
        List<StatisticsUserCase> count=statisticsMapper.statisticsUserCase(projectId);
     return count;
    }

    @Override
    public List<StatisticsUserCase> statisticsVersionUserCase(Long projectId) {
        List<StatisticsUserCase> list= statisticsMapper.statisticsVersionUserCase(projectId);
        return list;
    }

    @Override
    public List<StatisticsUserCase> statisticsUserCaseByType(Long projectId) {
        List<StatisticsUserCase> list= statisticsMapper.statisticsUserCaseByType(projectId);
        return list;
    }
}
