package com.camelot.pmt.task.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传和下载工具类
 * 
 */
public class UploadUtils {

    /**
     * @Description 文件上传: 获得文件名称,文件保存路径,输入流,然后用流将他写入到具体的位置,即可完成文件上传
     * @param request
     * @param file
     * @return 上传文件的保存路径
     */
    public static String uploadFile(/* HttpServletRequest request, */MultipartFile file) {
        // 目标一:获得原始文件名
        String fileName = file.getOriginalFilename();
        // 新文件名 (使用UUID生成新的文件名)
        // String newFileName = UUID.randomUUID() + fileName;
        String newFileName = fileName;
        // 获得项目的路径
        // ServletContext sc = request.getSession().getServletContext();
        // 目标二: 文件保存路径
        // String path = sc.getRealPath("/file") + "\\";
        String path = Constant.localPath;
        File f = new File(path);
        if (!f.exists())
            f.mkdirs();
        if (!file.isEmpty()) {
            try {
                FileOutputStream fos = new FileOutputStream(path + newFileName);
                // 目标三: 输入流
                InputStream in = file.getInputStream();
                int b = 0;
                while ((b = in.read()) != -1) {
                    fos.write(b);
                }
                fos.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return path + newFileName;
    }

    /**
     * @Description 文件下载: 读取文件到流中,然后通过输出流输出
     * @param filePath
     *            完整的要下载的文件的路径
     * @param fileName
     *            文件的名称
     * @param response
     *            响应流
     * @return 执行结果
     * @throws UnsupportedEncodingException
     */
    public static boolean downloadFile(String filePath, String fileName, HttpServletResponse response)
            throws UnsupportedEncodingException {
        Boolean result = false;
        // 目标一:将文件从电脑中读入到流中
        File file = new File(filePath);
        if (file.exists()) {
            response.setCharacterEncoding("UTF-8");// 设置相应内容的编码格式
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String(fileName.getBytes(), "iso-8859-1"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                // 目标一:将文件从电脑中读入到流中
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    // 目标二:通过输出流输出
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                    result = true;
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    /**
     * 根据文件绝对路径，删除文件 @Title: deleteFile @Description:
     * TODO(根据文件绝对路径，删除文件) @param @param fullFilePath 设定文件 @return void 返回类型 @throws
     */
    public static void deleteFile(String fullFilePath) {
        File deleteFile = new File(fullFilePath);
        deleteFile.delete();
    }
}