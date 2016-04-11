package home.common.page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页
 * Created by qijie on 2016/4/11.
 */
public class Pagination extends SimplePage implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PAGE_CONDITION_CACHE = "PageCondition";
    public static final String PAGE_CURRENT_NO_CACHE = "PageCurrentNo";
    public static final String PAGE_SIZE_CACHE = "PageSize";
    public static final String PAGE_TOTAL_COUNT_CACHE = "PageTotalCount";
    public static final String PAGE_ALL_CONDITION_CACHE = "PageAllCondition";
    private String id;
    private boolean checkPassLastPageNo = true;
    private boolean selectTotalCount = true;
    private List<?> list;
    private Object condition;
    private Integer sort;

    public Pagination() {
    }

    public Pagination(int pageNo, int pageSize, int totalCount) {
        super(pageNo, pageSize, totalCount);
    }

    public Pagination(int pageNo, int pageSize, int totalCount, List<?> list) {
        super(pageNo, pageSize, totalCount);
        this.list = list;
    }

    public int getFirstResult() {
        return (this.pageNo - 1) * this.pageSize;
    }

    public List<?> getList() {
        return this.list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public Object getCondition() {
        return this.condition;
    }

    public void setCondition(Object condition) {
        this.condition = condition;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCheckPassLastPageNo() {
        return this.checkPassLastPageNo;
    }

    public void setCheckPassLastPageNo(boolean checkPassLastPageNo) {
        this.checkPassLastPageNo = checkPassLastPageNo;
    }

    public boolean isSelectTotalCount() {
        return this.selectTotalCount;
    }

    public void setSelectTotalCount(boolean selectTotalCount) {
        this.selectTotalCount = selectTotalCount;
    }
}
