package com.camelot.pmt.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camelot.pmt.project.mapper.ProjectMapper;
import com.camelot.pmt.project.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

}
