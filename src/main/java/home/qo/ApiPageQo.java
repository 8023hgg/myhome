package home.qo;

/**
 * Created by qijie on 2016/4/6.
 */
public class ApiPageQo extends ApiBaseQo{
    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 页大小
     */
    private Integer pageSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
