package com.wzw.work.controller;

import com.alibaba.fastjson.JSONObject;
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
        JSONObject json = new JSONObject();
        if (!file.isEmpty()) {
            String saveFileName = file.getOriginalFilename();
            File saveFile = new File("D:\\A\\upload\\" + saveFileName);
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                json.put("code","0");
                json.put("msg","上传成功");
                return json.toString();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                json.put("code","1");
                json.put("msg","上传失败");
                return json.toString();
            } catch (IOException e) {
                e.printStackTrace();
                json.put("code","1");
                json.put("msg","上传失败");
                return json.toString();
            }
        } else {
            json.put("code","1");
            json.put("msg","上传失败，因为文件为空");
            return json.toString();
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
        JSONObject json = new JSONObject();
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
                        json.put("code","0");
                        json.put("msg","所有文件上传成功");
                    } catch (Exception e) {
                        if (stream != null) {
                            stream.close();
                            stream = null;
                        }
                        json.put("code","1");
                        json.put("msg","第 " + i + " 个文件上传有错误" + e.getMessage());
                        return json.toString();
                    }
                } else {
                    json.put("code","1");
                    json.put("msg","第 " + i + " 个文件为空");
                    return json.toString();
                }
            }
            return json.toString();
        }else{
            json.put("code","1");
            json.put("msg","后台没有接收到文件");
            return json.toString();
        }

    }
}
