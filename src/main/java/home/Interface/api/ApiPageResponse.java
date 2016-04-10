package home.Interface.api;

/**
 * Created by qijie on 2016/4/6.
 */
public class ApiPageResponse extends ApiResponse {

    /**
     * 页码
     */
    private int pageNo;

    /**
     * 分页大小
     */
    private int pageSize;

    /**
     * 总条目数
     */
    private int totalCount;

    public ApiPageResponse(){
        super();
    }

    public ApiPageResponse(int pageNo,int pageSize,int totalCount){
        super();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }
    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
