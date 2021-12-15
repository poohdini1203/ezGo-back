package com.clx.ezgo.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.UUID;


public class UploadUtils {
    public static String uploadDriverImg(String id, String category, HttpServletRequest request) throws IOException {

        request.setCharacterEncoding("utf-8"); //设置编码
        String filePath = "";
        String relativePath = "/driver/" + id + "/" + category + "/";
        String realPath = request.getSession().getServletContext().getRealPath(relativePath);
        File dir = new File(realPath);
        //文件目录不存在，就创建一个
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }

        StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
        //获取formdata的值
        Iterator<String> iterator = req.getFileNames();

        while (iterator.hasNext()) {
            MultipartFile file = req.getFile(iterator.next());
            String fileName = file.getOriginalFilename();
            //真正写到磁盘上
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String kzm = fileName.substring(fileName.lastIndexOf("."));
            String filename = uuid + kzm;
            File file1 = new File(realPath + filename);
            OutputStream out = new FileOutputStream(file1);
            out.write(file.getBytes());
            out.close();
            filePath = request.getScheme() + "://" +
                    request.getServerName() + ":"
                    + request.getServerPort()
                    + relativePath + filename;

            System.out.println("本地路径:" + realPath);
        }
        return filePath;
    }
}
