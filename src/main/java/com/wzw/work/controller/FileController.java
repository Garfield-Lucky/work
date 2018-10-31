package com.wzw.work.controller;

import com.alibaba.fastjson.JSONObject;
import com.wzw.work.entity.GeneralUploadFile;
import com.wzw.work.entity.User;
import com.wzw.work.service.GeneralUploadFileService;
import com.wzw.work.service.KityMindService;
import com.wzw.work.util.DeleteFileUtil;
import com.wzw.work.util.json.JosnToStrUtil;
import com.wzw.work.util.page.PageQueryResult;
import com.wzw.work.util.page.PageSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController extends BaseController {


    //文件上传路径
    private static final String WEB_UPLOAD_PATH="../upload/";

    @Autowired
    GeneralUploadFileService generalUploadFileService;

    /**
     * @Description: 文件上传主页
     *
     * @param
     * @author Created by wuzhangwei on 18-10-31 上午9:29
     */
    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView("uploadPage");
        return mav;
    }


    /**
     * @Description: layui插件上传
     *
     * @param
     * @author Created by wuzhangwei on 18-10-31 上午9:29
     */

    @RequestMapping("/layuiupload")
    public ModelAndView layuiupload(){
        ModelAndView mav = new ModelAndView("layuiupload");
        return mav;
    }


    /**
     * @Description: plupload插件上传
     *
     * @param
     * @author Created by wuzhangwei on 18-10-31 上午9:29
     */

    @RequestMapping("/plupload")
    public ModelAndView plupload(){
        ModelAndView mav = new ModelAndView("plupload");
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
            GeneralUploadFile entity = new GeneralUploadFile();
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String name = UUID.randomUUID().toString().replace("-","");
            String saveFileName = name + suffixName;//文件重命名
            String file_path = WEB_UPLOAD_PATH + saveFileName;//文件存储路径
            File saveFile = new File(file_path);
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
                //入库
                entity.setFileName(fileName);
                entity.setFileNewName(saveFileName);
                entity.setFileSuffix(suffixName);
                entity.setFilePath(file_path);
                User user = (User)request.getSession().getAttribute("sessionUser");
                entity.setCreateUserName(user.getUserName());
                generalUploadFileService.save(entity);

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
            }catch (Exception e) {
                e.printStackTrace();
                json.put("code","1");
                json.put("msg","入库失败");
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
        File savePath = new File(WEB_UPLOAD_PATH);
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
                        GeneralUploadFile entity = new GeneralUploadFile();
                        byte[] bytes = file.getBytes();
                        String fileName = file.getOriginalFilename();
                        String suffixName = fileName.substring(fileName.lastIndexOf("."));
                        String name = UUID.randomUUID().toString().replace("-","");
                        String saveFileName = name + suffixName;//文件重命名
                        String file_path = savePath +"/"+ saveFileName;//文件存储路径
                        File saveFile = new File(file_path);
                        stream = new BufferedOutputStream(new FileOutputStream(saveFile));
                        stream.write(bytes);
                        stream.close();
                        //入库
                        entity.setFileName(fileName);
                        entity.setFileNewName(saveFileName);
                        entity.setFileSuffix(suffixName);
                        entity.setFilePath(file_path);
                        User user = (User)request.getSession().getAttribute("sessionUser");
                        entity.setCreateUserName(user.getUserName());
                        generalUploadFileService.save(entity);
                        json.put("code","0");
                        json.put("msg","所有文件上传成功");
                    } catch (Exception e) {
                        if (stream != null) {
                            stream.close();
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

    /**
     * @Description: 查看上传文件列表
     *
     * @param
     * @author Created by wuzhangwei on 18-10-31 下午1:35
     */

    @RequestMapping(value="/findFileList")
    @ResponseBody
    public String findUserList(int page,int limit,String fileName,String createUserName) {
        log.info("*********findUserList page "+page+" pageSize "+limit+"**********************");

        PageSettings pageSetting = PageSettings.of(page, limit, sortname, sortorder);

        PageQueryResult<GeneralUploadFile> pageResult = new PageQueryResult<GeneralUploadFile>(pageSetting);
        Map map =new HashMap();
        map.put("page",page-1);
        map.put("pageSize",limit);
        map.put("fileName",fileName);
        map.put("createUserName",createUserName);

        List<GeneralUploadFile> listFile = generalUploadFileService.list(map);
        // 查询条件
        pageResult.setTotalCount(listFile.size());
        pageResult.setResult(listFile);
        return JosnToStrUtil.ObjToJsonStr_LayUi(pageResult);
    }

    /**
     * @Description: 删除文件
     *
     * @param
     * @author Created by wuzhangwei on 18-10-31 下午1:42
     */
    @RequestMapping(value="/delete")
    @ResponseBody
    public String deleteFile(@RequestParam(required = true)Integer id) throws Exception {

        JSONObject json = new JSONObject();
        try {
            GeneralUploadFile entity = generalUploadFileService.findById(id);
            String filePath = entity.getFilePath();
            generalUploadFileService.delete(id);// 删除
            DeleteFileUtil.deleteDataAndFile(filePath);
            json.put("code",0);
            json.put("msg","删除文件成功");
            return json.toJSONString();
        } catch (Exception e) {
            json.put("code",1);
            json.put("msg","删除文件失败");
            return json.toJSONString();
        }
    }

}
