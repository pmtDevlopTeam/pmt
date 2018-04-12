package com.camelot.pmt.filemanage.controller;

import com.camelot.pmt.filemanage.model.FileManageGroup;
import com.camelot.pmt.filemanage.service.FileManageGroupService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 * FileManageGroup 控制层
 *
 * 张战
 */
@Controller
@RequestMapping(value = "/file/manager/group")
public class FileManageGroupController {
    @Autowired
    private FileManageGroupService fileManageGroupService;
    @RequestMapping("/addfilegroup")
    public Boolean  addFileManagerGroup(HttpServletRequest request, FileManageGroup fileManageGroup)
    {
        fileManageGroup.setName("zz");
        Boolean b=fileManageGroupService.addFileManagerGroup(request,fileManageGroup);//添加文件夹
        return null;
    }
    @RequestMapping("/deletefilegroup")
    public Boolean deleteFileGroup(FileManageGroup fileManageGroup){
        Boolean b=fileManageGroupService.deleteFileGroup(fileManageGroup);//删除文件夹
        return null;
    }
    @RequestMapping("/updatefilegroup")
    public Boolean updateFileGroupById(FileManageGroup fileManageGroup){
      Boolean b= fileManageGroupService.updateFileGroupById(fileManageGroup);//修改文件夹
     return null;
    }
   /* @RequestMapping("/selectFilegroupByProjectID")
    @ResponseBody
    public String  selectFileGroupByProjectID(FileManageGroup fileManageGroup){
      List<FileManageGroup> filemanagegroup=fileManageGroupService.selectFileGroup(fileManageGroup);
        String s = filemanagegroup.toString();
        return s;
    }*/
   @RequestMapping("/selectFileGroup")
    @ResponseBody
    public String selectFileGroupByParentID(FileManageGroup fileManageGroup){
      List<FileManageGroup> filemanagegroup=fileManageGroupService.selectFileGroup(fileManageGroup);
        String s = filemanagegroup.toString();
        return s;

    }

}