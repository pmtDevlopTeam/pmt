package com.camelot.pmt.pro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camelot.api.model.Response;
import com.camelot.pmt.pro.mapper.ProjectMapper;
import com.camelot.pmt.pro.model.Project;
import com.camelot.pmt.pro.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public Response getProjectInfo(Integer proId) {
        Response response = new Response();
        Project project = projectMapper.getProjectInfo(proId);

        if (project != null) {
            response.setMessage("查询数据成功！！");
            response.setTraceId("200");
        } else {
            response.setMessage("查询数据失败！！");
            response.setTraceId("500");
        }

        return response;
    }

}
