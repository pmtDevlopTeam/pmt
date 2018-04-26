package com.camelot.pmt.Statistics.service;

import com.camelot.pmt.Statistics.model.StatisticsUserCase;
import com.camelot.pmt.filemanage.model.FileManageGroup;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import java.util.List;
public interface StatisticsService {

    List<StatisticsUserCase> statisticsUserCase(Long projectId);

    List<StatisticsUserCase> statisticsVersionUserCase(Long projectId);

    List<StatisticsUserCase> statisticsUserCaseByType(Long projectId);
}