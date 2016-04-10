package home.service.file;

import home.entity.file.Files;
import home.exception.ShowMessageException;
import home.model.file.FileModel;
import home.service.base.BaseServiceImpl;
import home.util.UUIDGenerator;
import home.util.des.DesBUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文件服务
 * Created by qijie on 2016/4/8.
 */
@Service
@Transactional
public class FileService extends BaseServiceImpl<Files> {


    /**
     * 获取文件列表
     * @return
     */
    public List<FileModel> fileList(){

        String hql = "from Files f";
        List<Files> files = (List<Files>)this.findList(hql);
        List<FileModel> fileModels = new ArrayList<FileModel>();
        if(!CollectionUtils.isNotEmpty(files)){
           return fileModels;
        }
        for (Files file : files){
            if(doToModel(file) != null){
                fileModels.add(doToModel(file));
            }
        }
        return fileModels;
    }

    /**
     * 根据Hql 查询
     * @param hql
     * @return
     */
    public List<Files> findListByHql(String hql){

        List<Files> files = (List<Files>)this.findList(hql);
        return files;
    }

    /**
     * 保存上传文件
     * @param request
     * @param loadFile
     */
    public void saveFiles(HttpServletRequest request,MultipartFile loadFile){

        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = "/resources/file/";
        String logoRealPathDir = request.getSession().getServletContext().getRealPath(savePath);
        File file = new File(logoRealPathDir);
        //判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath+"目录不存在，需要创建");
            //创建目录
            file.mkdir();
        }

        //使用Apache文件上传组件处理文件上传步骤：
        //1、创建一个DiskFileItemFactory工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //2、创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解决上传文件名的中文乱码
        upload.setHeaderEncoding("UTF-8");
        //3、判断提交上来的数据是否是上传表单的数据
        if(!ServletFileUpload.isMultipartContent(request)){
          throw new ShowMessageException("上传数据异常");
        }

        //得到上传的文件名称，
        String filename = loadFile.getOriginalFilename();
        if(filename==null || filename.trim().equals("")){
           throw new ShowMessageException("上传文件名称不能为空");
        }

        //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如： c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
        //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
        filename = filename.substring(filename.lastIndexOf("\\")+1);
        try{
            //获取item中的上传文件的输入流
            InputStream in = loadFile.getInputStream();
            //创建一个文件输出流
            FileOutputStream out = new FileOutputStream(logoRealPathDir + "\\" + filename);
            //创建一个缓冲区
            byte buffer[] = new byte[1024];
            //判断输入流中的数据是否已经读完的标识
            int len = 0;
            //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
            while((len=in.read(buffer))>0){
                //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                out.write(buffer, 0, len);
            }
            //关闭输入流
            in.close();
            //关闭输出流
            out.close();

            //保存相应的信息到数据库
            Files newFile = new Files();
            newFile.setName(filename);
            newFile.setRealName(filename);
            newFile.setUrl(logoRealPathDir + "\\" + filename);
            newFile.setCreateDate(new Date());
            newFile.setFid(UUIDGenerator.getUUID());
            newFile.setType(1);
            save(newFile);

        }catch (Exception e){
            throw new ShowMessageException(e.getMessage());
        }
    }

    private FileModel doToModel(Files file){

        FileModel fileModel = null;
        if(file != null){
            fileModel = new FileModel();
            String fileId = DesBUtils.encrypt(file.getFid(),DesBUtils.CRYPT_KEY_DEFAULT);
            fileModel.setFid(fileId);
            fileModel.setId(file.getId());
            fileModel.setName(file.getRealName());
            fileModel.setCreateDate(file.getCreateDate());
        }
        return fileModel;
    }
}
