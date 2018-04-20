package com.camelot.pmt.task.service.impl;

import com.camelot.pmt.task.mapper.TaskFileMapper;
import com.camelot.pmt.task.model.TaskFile;
import com.camelot.pmt.task.service.TaskFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 附件相关接口
 *
 * @author zlh
 * @date 2018/4/17 10:24
 */
@Service
@Transactional
public class TaskFileServiceImpl implements TaskFileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskFileServiceImpl.class);

    @Autowired
    private TaskFileMapper taskFileMapper;

    /**
     * 插入需求类型任务的附件元信息
     *
     * @param taskFile 参数
     * @return boolean
     * @author zlh
     * @date 10:21 2018/4/17
     */
    @Override
    public boolean insert(TaskFile taskFile) {
        try {
            // check参数
            if (taskFile == null) {
                throw new RuntimeException("参数错误");
            }
            int insertResult = taskFileMapper.insert(taskFile);
            return insertResult == 1 ? true : false;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据附件来源和来源id查询附件元信息
     *
     * @param taskFile 查询需要的参数
     * @return TaskFile
     * @author zlh
     * @date 17:03 2018/4/17
     */
    @Override
    public TaskFile queryByTaskFile(TaskFile taskFile) {
        try {
            // check参数
            if (taskFile == null) {
                throw new RuntimeException("参数错误");
            }
            return taskFileMapper.queryByTaskFile(taskFile);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 附件下载
     *
     * @param taskFile 参数
     * @return TaskFile
     * @author zlh
     * @date 17:03 2018/4/17
     */
    @Override
    public boolean download(TaskFile taskFile, HttpServletResponse response) {
        String fileName = taskFile.getAttachmentTile();
        String path = taskFile.getAttachmentUrl();
        if (StringUtils.isEmpty(fileName) && StringUtils.isEmpty(path)) {
            throw new RuntimeException("参数错误");
        }
        File file = new File(path, fileName);
        if (file.exists()) {
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                return true;
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                throw new RuntimeException("下载失败");
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        throw new RuntimeException("文件不存在");
    }
}
