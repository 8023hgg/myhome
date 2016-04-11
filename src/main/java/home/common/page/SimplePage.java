package home.common.page;

/**
 * Created by qijie on 2016/4/11.
 */
public class SimplePage {
    private static final long serialVersionUID = 1L;
    public static final int DEF_COUNT = 20;
    protected int totalCount = 0;
    protected int pageSize = 20;
    protected int pageNo = 1;
    protected int startIndex = 0;

    public static int checkPageNo(Integer pageNo) {
        return pageNo != null && pageNo.intValue() >= 1?pageNo.intValue():1;
    }

    public SimplePage() {
    }

    public SimplePage(int pageNo, int pageSize, int totalCount) {
        this.setTotalCount(totalCount);
        this.setPageSize(Integer.valueOf(pageSize));
        this.setPageNo(Integer.valueOf(pageNo));
        this.adjustPageNo();
    }

    public void adjustPageNo() {
        if(this.pageNo != 1) {
            int totalPage = this.getTotalPage();
            if(this.pageNo > totalPage) {
                this.pageNo = totalPage;
            }

            this.startIndex = this.pageSize * (this.pageNo - 1);
        }
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public int getTotalPage() {
        int totalPage = this.totalCount / this.pageSize;
        if(totalPage == 0 || this.totalCount % this.pageSize != 0) {
            ++totalPage;
        }

        return totalPage;
    }

    public boolean isFirstPage() {
        return this.pageNo <= 1;
    }

    public boolean isLastPage() {
        return this.pageNo >= this.getTotalPage();
    }

    public int getNextPage() {
        return this.isLastPage()?this.pageNo:this.pageNo + 1;
    }

    public int getPretPage() {
        return this.isFirstPage()?this.pageNo:this.pageNo - 1;
    }

    public void setTotalCount(int totalCount) {
        if(totalCount < 0) {
            this.totalCount = 0;
        } else {
            this.totalCount = totalCount;
        }

    }

    public void setPageSize(Integer pageSize) {
        if(pageSize != null && pageSize.intValue() >= 1) {
            this.pageSize = pageSize.intValue();
        } else {
            this.pageSize = 20;
        }

        this.startIndex = this.pageSize * (this.pageNo - 1);
    }

    public void setPageNo(Integer pageNo) {
        if(pageNo != null && pageNo.intValue() >= 1) {
            this.pageNo = pageNo.intValue();
        } else {
            this.pageNo = 1;
        }

        this.startIndex = this.pageSize * (this.pageNo - 1);
    }
}
