package com.example.springboot.controller;

import cn.hutool.core.io.FileUtil;
import com.example.springboot.common.AuthAccess;
import com.example.springboot.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * 功能：实现文件的上传和下载
 * 作者：小华
 * 日期： 2023/12/16 16:17
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${ip:localhost}")
    String ip;

    @Value("${server.port}")
    String port;

    private static final String ROOT_PATH=System.getProperty("user.dir")+File.separator+"file";

    @AuthAccess
    //上传文件接口
    @PostMapping("/upload")//post接口
    public Result upload(MultipartFile file) throws IOException {//file存到后端
        String originalFilename = file.getOriginalFilename();//文件的原始名称
        //比如文件;abc.jpg
        String mainName = FileUtil.mainName(originalFilename);//获得abc
        String extName =FileUtil.extName("文件的后缀");//extName提供实现方法,获得文件后缀不带“.”
        //获得jpg
        if (!FileUtil.exist(ROOT_PATH)){//如果当前文件夹父级目录不存在
            FileUtil.mkdir(ROOT_PATH);//创建文件夹
        }

        //文件名重复定义文件重命名方法D:\java1\2\vue01\flie\123_abc.jpg
        if (FileUtil.exist(ROOT_PATH+File.separator+originalFilename)){//如果当上传文件已存在时，
            originalFilename=System.currentTimeMillis()+"_"+mainName+"."+extName;
            ////路径存在更改，重命名文件
        }
        File saveFile = new File(ROOT_PATH+File.separator+originalFilename);
        file.transferTo(saveFile);//存储文件到本地磁盘
        String url ="http://"+ ip + ":"+ port +"/file/download/"+originalFilename;
        //用动态获取IP的方式获取地址
        return Result.success(url);//返回文件链接，下载地址，这个下载地址后台提供
    }

    @AuthAccess
    //下载文件接口
    @GetMapping ("/download/{fileName}")//post接口
    public void download(@PathVariable String fileName, HttpServletResponse response)throws IOException {//文件流的形式
        String filePath =ROOT_PATH+File.separator+fileName;
        if (!FileUtil.exist(filePath)){//判断存不存在
                return;//不在直接返回
        }
        byte[] bytes = FileUtil.readBytes(filePath);//存在，读写文件
        //@PathVariable表示{fileName}是一个路径参数，fileName文件名称
        ServletOutputStream outputStream= response.getOutputStream();
        outputStream.write(bytes);//数组是一个字节数组，也就是文件的字节流数组
        outputStream.flush();
        outputStream.close();//不用关闭文件，不然会占用资源
    }
}
