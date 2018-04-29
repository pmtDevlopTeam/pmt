package com.camelot.pmt.project.service;

import java.util.List;

/**
 * 
 * @author qiaodj
 * @date 2018年4月28日
 */
public interface RemindContentChildService {
    /**
     * 根据项目id、提醒内容id查询子内容信息
     * 
     * @param projectId
     * @param contentId
     * @return
     */
    List<Object> queryByProjectIdAndByContentId(Long projectId, Long contentId);
}
