package com.camelot.pmt.filemanage.controller;

import com.camelot.pmt.filemanage.model.FileManage;
import com.camelot.pmt.filemanage.model.FileManageGroup;
import com.camelot.pmt.filemanage.service.FileManageService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 * FileManage 控制层
 *张战
 */
@Controller
@RequestMapping(value = "/file/manager")
public class FileManageController {
    @Autowired
    private FileManageService fileManageService;
    @RequestMapping(value = "/addfile")//添加文件
    public Boolean addFileManager(HttpServletRequest request, FileManage fileManage){
       // System.out.println("1111111");/*前台接收创建人createuserid，文件夹名称name ，文件标题filetitle，文件路径fileaddress*/
       // fileManage.setCreateUserId("111");
       // fileManage.setGroupId((long) 1);
       // fileManage.setFileTitle("my name is ");//测试数据*/
       // Boolean b=fileManageService.addFileManager(request,fileManage);//添加文件
        return null;
    }
    @RequestMapping("/deletefile")
    public Boolean deldeteFileById(FileManage fileManage){
        Boolean b=fileManageService.deleteFileById(fileManage);//删除文件
        return null;
    }
    @RequestMapping("/updatefile")
    public Boolean updateFileByID(HttpServletRequest request,FileManage fileManage){
        Boolean  b=fileManageService.updateFileById(request,fileManage);//文件修改
        return null;
    }
    @RequestMapping("/selectfile")
    @ResponseBody
    public String  selectFileByGroupID(FileManageGroup fileManageGroup){
      List<FileManage> filelist=fileManageService.selectFileByGroupID(fileManageGroup);//查询文件详细信息
        String s = filelist.toString();
        return  s;
    }


}