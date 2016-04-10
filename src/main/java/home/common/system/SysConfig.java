package home.common.system;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by qijie on 2016/4/5.
 */
public class SysConfig {
    public static final Short ENABLE_FALSE = Short.valueOf((short)0);
    public static final Short ENABLE_TRUE = Short.valueOf((short)1);
    @Id
    @GeneratedValue(
            generator = "generator"
    )
    @GenericGenerator(
            name = "generator",
            strategy = "assigned"
    )
    @Column(
            name = "ID",
            unique = true
    )
    private String id;
    @Column(
            name = "CREATE_DATE"
    )
    private Date createDate;
    @Column(
            name = "LAST_MODIFIED_DATE"
    )
    private Date lastModifiedDate;
    private String confName;
    private String confValue;
    private String remark;
    private Short enable;

    public SysConfig() {
    }

    public String getConfName() {
        return this.confName;
    }

    public void setConfName(String confName) {
        this.confName = confName;
    }

    public String getConfValue() {
        return this.confValue;
    }

    public void setConfValue(String confValue) {
        this.confValue = confValue;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Short getEnable() {
        return this.enable;
    }

    public void setEnable(Short enable) {
        this.enable = enable;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public boolean enable() {
        return ENABLE_TRUE.equals(this.enable)?true:(ENABLE_FALSE.equals(this.enable)?false:false);
    }
}
