package home.model.file;

import java.util.Date;

/**
 * Created by qijie on 2016/4/8.
 */
public class FileModel {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 删除，查询用的唯一标识
     */
    private String fid;
    /**
     * 上传文件的名称
     */
    private String name;
    /**
     * 文件保存的路径
     */
    private String path;

    /**
     * 上传日期
     */
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
