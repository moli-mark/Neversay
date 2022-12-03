package com.never.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@Api(tags = "pdf上传接口")
public class PdfUploadController {
    @ResponseBody
    @PostMapping("/api/uploadPdf")
    public Map<String, Object> uploadPdf(@ApiParam(name = "file",value = "MultipartFile类型文件",type = "MultipartFile",required = true)@RequestParam("file") MultipartFile file, HttpServletRequest req) throws IOException {
        //首先要给文件找一个目录
        //先写返回值
        Map<String, Object> resultMap = new HashMap<>();
        try {
            //再用pdf格式开始书写,先找原始的名字
            String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1)
                    .toLowerCase();
            //判断文件类型是不是pdf
            if(!"pdf".equals(fileExt)){
                //如果不是的话，就返回类型
                resultMap.put("status","error");
                resultMap.put("msg","文件类型不对");
                return resultMap;
            }
            String path = System.getProperty("user.dir") + "/src/main/resources/static/pdf/upload/";
            Calendar instance = Calendar.getInstance();
            String month = (instance.get(Calendar.MONTH) + 1) + "month";
            path = path + month;
            // 重构文件名称
            String pikId = UUID.randomUUID().toString().replaceAll("-", "");
            String newPdfName = pikId + "." + fileExt;
            File realPath = new File(path, newPdfName);
            if (!realPath.exists()) {
                realPath.mkdirs();
            }
            file.transferTo(realPath);

            resultMap.put("newPdfName", newPdfName);
            //正确保存视频则设置返回码为200
            resultMap.put("resCode", "200");
            //返回视频保存路径
            resultMap.put("url","/pdf/upload/"+month+"/"+newPdfName);
            return resultMap;
        } catch (IOException e) {
            //返回异常
            resultMap.put("status","error");
            resultMap.put("msg",e.getMessage());
        }
        return resultMap;
    }
}