package com.camelot.pmt.testmanage.casemanage.controller;

import com.camelot.pmt.platform.user.model.UserModel;
import com.camelot.pmt.testmanage.casemanage.model.UseCaseImplement;
import com.camelot.pmt.testmanage.casemanage.service.UseCaseImplementService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用例执行Controller
 *
 * @author Yurnero
 */
@RestController
@RequestMapping(value = "/test_manage/case_manage/use_case_implement")
public class UseCaseImplementController {

    @Autowired
    private UseCaseImplementService useCaseImplementService;

    @PostMapping
    public void add(HttpServletRequest request, @RequestBody UseCaseImplement useCaseImplement) {
        try {
            UserModel user = (UserModel) request.getSession().getAttribute("user");
            useCaseImplementService.add(user, useCaseImplement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping
    public List<UseCaseImplement> findByUseCaseId(Long useCaseId) {
        try {
            return useCaseImplementService.findByUseCaseId(useCaseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
