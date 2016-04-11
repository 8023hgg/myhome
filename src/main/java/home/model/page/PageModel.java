package home.model.page;

/**
 * Created by qijie on 2016/4/11.
 */
public class PageModel {
    /**
     * 第几页
     */
    private Integer pageNo;
    //每页大小
    private Integer pageSize;
    //总数量
    private Integer totalSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer getPageSize) {
        this.pageSize = getPageSize;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }
}
