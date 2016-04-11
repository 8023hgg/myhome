package home.dao.base;

import home.common.page.Pagination;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qijie on 2016/3/30.
 */
public interface BaseDao<T>{

    public void save(T t);

    public void update(T t);

    public void saveOrUpdate(T t);

    public List<?> findList(String hql);

    public List<T> findAll();

    public void delete(T t);

    public T find(Serializable entityId);

    public T get(Serializable entityId);

    /**
     * 和数据库同步
     */
    public void flush();

    /**
     * 清除一级缓存的数据
     */
    public void clear();

    /**
     * 分页获取数据
     * @param hql
     * @param pageNo
     * @param pageSize
     * @param values
     * @return
     */
    public Pagination findPagination(String hql, int pageNo, int pageSize, Object... values);

}
