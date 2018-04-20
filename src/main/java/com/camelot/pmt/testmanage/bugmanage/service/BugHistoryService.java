package com.camelot.pmt.testmanage.bugmanage.service;

import java.util.List;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.testmanage.bugmanage.model.BugHistory;

public interface BugHistoryService {

    /**
     * 
     * <p>
     * Description:[查询bug历史]
     * </p>
     * 
     * @return List<UserModel>
     * @author [maple]
     */
    List<BugHistory> queryBugHistoryAll(Long bugId);
}
