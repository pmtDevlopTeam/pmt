package com.camelot.pmt.task.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 附件上传下载工具类.
 *
 * @author zlh
 * @date 2018/4/20 14:41
 */
public final class FileUtils {

    private FileUtils() {
    }

    /**
     * 上传.
     *
     * @param file
     *            上传的文件
     * @param filePath
     *            文件上传路径
     * @author zlh
     * @date 15:13 2018/4/20
     */
    public static void uploadFile(MultipartFile file, String filePath) {
        try {
            String fileName = file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            File targetFile = new File(filePath);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(filePath + fileName);
            out.write(bytes);

            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件上传失败");
        }
    }

    public static void downloadFile(HttpServletResponse response, String filePath, String fileName) {
        File file = new File(filePath, fileName);
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
            } catch (Exception e) {
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
    }
}
