package home.entity;

import javax.persistence.*;

/**
 * Created by qijie on 2016/4/7.
 */
@Entity
@Table(name = "xf_test")
public class Test {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 当前版本
     */
    private Integer version = 0;

    /**
     * 测试库存
     */
    private Integer stock = 1000;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
