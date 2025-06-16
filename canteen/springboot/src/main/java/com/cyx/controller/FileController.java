package com.cyx.controller;

import cn.hutool.core.io.FileUtil;
import com.cyx.common.Result;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @Title: FileController
 * @Author 曦
 * @Date 2025/6/4 20:27
 * @description:
 */
@RestController
@RequestMapping("/files")
public class FileController {
    //本地磁盘文件的存储路径 D:\JavaWebProject\canteen\files\
    private static final String filePath = System.getProperty("user.dir") + "/files/";
    //上传文件
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        if(!FileUtil.exist(filePath)){
            FileUtil.mkdir(filePath);
        }
        //文件的原始名称
        String originalFilename = file.getOriginalFilename();
        // 构造本地文件的完整路径
        String realFilePath = filePath + originalFilename;
        //处理同名文件会发生覆盖的问题
        if(FileUtil.exist(realFilePath)){//同名文件已存在 123.jpg=>123_1234123333.jpg 时间戳
            originalFilename = FileUtil.mainName(originalFilename) + "_"
                    + System.currentTimeMillis() + FileUtil.extName(originalFilename);
            realFilePath = filePath + originalFilename;
        }
        // 创建本地文件对象
        File localFile = new File(realFilePath);
        // 将接收到的文件写入到指定路径下（本地保存）
        file.transferTo(localFile);
        String url = "http://localhost:9090/files/download/" + originalFilename;
        return Result.success(url);
    }

    //下载文件
    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        // 构造本地文件的完整路径
        String realFilePath = filePath + fileName;
        byte[] bytes = FileUtil.readBytes(realFilePath);
        ServletOutputStream os = response.getOutputStream();
        os.write(bytes);
        os.flush();
        os.close();
    }

}
