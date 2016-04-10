package home.controller.file;

import com.alibaba.fastjson.JSON;
import home.entity.file.Files;
import home.exception.ShowMessageException;
import home.model.file.FileModel;
import home.service.file.FileService;
import home.util.des.DesAUtils;
import home.util.des.DesBUtils;
import home.util.file.FilesUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by qijie on 2016/4/8.
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;
    /**
     * 上传文件主页
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model){

        return "file/index";
    }

    /**
     * 上传文件列表
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model){

        List<FileModel> files = fileService.fileList();
        model.addAttribute("files",files);

        return "file/list";
    }
    /**
     * 上传文件
     * @return
     */
    @RequestMapping("/load")
    public ModelAndView loadFile(HttpServletRequest request,HttpServletResponse response
                                 ,@RequestParam("file") MultipartFile fileLode){

        ModelAndView mav = new ModelAndView();

        try {
            fileService.saveFiles(request,fileLode);
        }catch (ShowMessageException e){
            mav.setView(new RedirectView("/error/error?message="+e.getMessage(),true));
        }

        mav.setView(new RedirectView("/file/list",true));
        return mav;
    }

    /**
     * @方法功能说明：删除文件
     */
    @RequestMapping(value="/del-file")
    @ResponseBody
    public String delFile(Model model,String fid,HttpServletResponse response){

        String message = "F";
        try {
            if(StringUtils.isBlank(fid)){
                return JSON.toJSONString(message);
            }

            //解锁
            String fileId = DesBUtils.decrypt(fid, DesBUtils.CRYPT_KEY_DEFAULT);
            String hql ="from Files f where f.fid='"+fileId+"'";
            List<Files> files = fileService.findListByHql(hql);
            if(!CollectionUtils.isNotEmpty(files)){
                message = "参数错误";
                return JSON.toJSONString(message);
            }
            String url = files.get(0).getUrl();
            Integer i = url.lastIndexOf(File.separator);
            url = url.substring(0, i);
//            删除数据库里的路径
            fileService.delete(files.get(0));
//            执行删除文件
            File fileDir = new File(files.get(0).getUrl());
            fileDir.delete();
//            删除文件目录
            new FilesUtils().deleteDirectory(url);
            message = "T";
        }catch (Exception e){
            System.out.print("删除文件异常:"+e.getMessage());
            message = e.getMessage();
        }
        return JSON.toJSONString(message);
    }

    /**
     *
     * @方法功能说明：文件下载
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(@RequestParam("fid") String fid)
            throws IOException {
        try {
            if(StringUtils.isBlank(fid)){
                return null;
            }

            String fileId = DesBUtils.decrypt(fid,DesBUtils.CRYPT_KEY_DEFAULT);
            String hql ="from Files f where f.fid='"+fileId+"'";
            List<Files> files = fileService.findListByHql(hql);
            if(CollectionUtils.isEmpty(files)){
                return null;
            }
            String rspName = files.get(0).getUrl();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", new String(rspName.getBytes("gb2312"),
                    "iso-8859-1"));
            File file = new File(rspName);
            byte[] bytes = FileUtils.readFileToByteArray(file);
            return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("下载文件发生异常");
        }
        return null;
    }
}
