package com.never.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author molimark<br />
 * @date: 2022/11/20 0:06<br/>
 * @description: <br/>
 */
@Controller
@Api(tags = "视频上传接口")
public class VideoUploadController {
    @ApiOperation(value = "视频上传接口",notes = "upload video")
    @PostMapping("/api/uploadVideo")
    @ResponseBody
    public Map<String, String> uploadVideo(@ApiParam(name = "file",value = "MultipartFile类型文件",type = "MultipartFile",required = true)@RequestParam(name = "file") MultipartFile file)
            throws IllegalStateException {
        Map<String, String> resultMap = new HashMap<>();
        try {
            System.out.println(file);
            //获取文件后缀，因此此后端代码可接收一切文件，上传格式前端限定
            String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1)
                    .toLowerCase();
            String path = System.getProperty("user.dir") + "/src/main/resources/static/video/upload/";
            Calendar instance = Calendar.getInstance();
            String month = (instance.get(Calendar.MONTH) + 1) + "month";
            path = path + month;
            // 重构文件名称
            String pikId = UUID.randomUUID().toString().replaceAll("-", "");
            String newVidoeName = pikId + "." + fileExt;
            System.out.println("重构文件名防止上传同名文件：" + newVidoeName);
            //保存视频
            File realPath = new File(path, newVidoeName);
            if (!realPath.exists()) {
                realPath.mkdirs();
            }
            file.transferTo(realPath);
            //构造Map将视频信息返回给前端
            //视频名称重构后的名称
            resultMap.put("newVidoeName", newVidoeName);
            //正确保存视频则设置返回码为200
            resultMap.put("resCode", "200");
            //返回视频保存路径
            resultMap.put("url","/video/upload/"+month+"/"+newVidoeName);
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            //保存视频错误则设置返回码为400
            resultMap.put("resCode", "400");
            return resultMap;
        }
    }
}


