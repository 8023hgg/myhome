package home.entity.file;

import home.util.des.DesAUtils;
import home.util.des.DesBUtils;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.util.Date;

/**
 * 
 * @类功能说明：文件
 *
 */
@Entity
@Table(name = "xf_file")
public class Files {

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	/**
	 * 存放路径
	 */
	private String url;
	
	/**
	 * 文件的真实名字
	 */
	private String realName;
	
	/**
	 * 文件现在的名字
	 */
	private String name;
	
	/**
	 * 文件上传日期
	 */
	private Date createDate;
	
	/**
	 * 上传文件的类型（1,2,3,4）
	 */
	private Integer type;
	
	/**
	 * 文件唯一主键
	 */
	private String fid;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}
}
