package com.camelot.pmt.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camelot.pmt.project.mapper.RemindContentChildMapper;
import com.camelot.pmt.project.service.RemindContentChildService;

/**
 * 
 * @author qiaodj
 * @date 2018年4月28日
 */
@Service
public class RemindContentChildServiceImpl implements RemindContentChildService {
    @Autowired
    private RemindContentChildMapper remindContentChildMapper;

    /**
     * 根据项目id、提醒内容id查询子内容信息 延时延期提醒
     */
    @Override
    public List<Object> queryByProjectIdAndByContentId(Long projectId, Long contentId) {
        List<Object> objList = new ArrayList<>();

        remindContentChildMapper.queryByProjectIdAndByContentId(projectId, contentId).stream().forEach(e -> {
            if ("02".equals(e.getCode())) {
                // 项目延期提醒
                objList.add(e.getDelayRemindDays());
            }
            if ("03".equals(e.getCode())) {
                // 项目延时提醒
                objList.add(e.getRemark());
            }
        });

        return objList;
    }

}
