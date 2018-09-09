package com.wzw.work.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {


    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView("upload");
        return mav;
    }


    /**
     * @Description: 单文件上传
     * @param
     * @author Created by wuzhangwei on 2018/9/9 20:11
     */
    @RequestMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam(name = "file") MultipartFile file, HttpServletRequest request) {
        log.info("upload");
        if (!file.isEmpty()) {
            String saveFileName = file.getOriginalFilename();
            File saveFile = new File(request.getSession().getServletContext().getRealPath("/upload/") + saveFileName);
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                return "success";
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "fail";
            } catch (IOException e) {
                e.printStackTrace();
                return "fail";
            }
        } else {
            return "上传失败，因为文件为空.";
        }
    }
 

   /**
   * @Description: 多文件上传
   * @param
   * @author Created by wuzhangwei on 2018/9/9 20:10
   */
    @PostMapping("/uploadFiles")
    @ResponseBody
    public String uploadFiles(HttpServletRequest request) throws IOException {
        log.info("uploadFiles");
        File savePath = new File(request.getSession().getServletContext().getRealPath("/upload/"));
        if (!savePath.exists()) {
            savePath.mkdirs();
        }
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        if(files.size()>0) {
            for (int i = 0; i < files.size(); ++i) {
                file = files.get(i);
                if (!file.isEmpty()) {
                    try {
                        byte[] bytes = file.getBytes();
                        File saveFile = new File(savePath, file.getOriginalFilename());
                        stream = new BufferedOutputStream(new FileOutputStream(saveFile));
                        stream.write(bytes);
                        stream.close();
                    } catch (Exception e) {
                        if (stream != null) {
                            stream.close();
                            stream = null;
                        }
                        return "第 " + i + " 个文件上传有错误" + e.getMessage();
                    }
                } else {
                    return "第 " + i + " 个文件为空";
                }
            }
            return "所有文件上传成功";
        }else{
            return "后台没有接收到文件";
        }

    }
}
