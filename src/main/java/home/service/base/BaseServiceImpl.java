package home.service.base;

import home.common.page.Pagination;
import home.dao.base.BaseDao;
import home.dao.base.BaseDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qijie on 2016/3/30.
 */
@Transactional
public class BaseServiceImpl<T> implements BaseServiceI<T>{

    @Autowired
    private BaseDao         baseDao;

    public void save(T t) {
        baseDao.save(t);
    }

    public void update(T t) {
        baseDao.update(t);
    }

    public void saveOrUpdate(T t) {
        baseDao.saveOrUpdate(t);
    }

    public List<?> findList(String hql) {
        return baseDao.findList(hql);
    }

    public List<T> findAll() {
        return baseDao.findAll();
    }

    public void delete(T t) {
        baseDao.delete(t);
    }

    public T find(Serializable entityId) {
        return (T) baseDao.find(entityId);
    }

    public T get(Serializable entityId) {
        return (T) baseDao.get(entityId);
    }

    public void flush() {
        baseDao.flush();
    }

    public void clear() {
        baseDao.clear();
    }

    public Pagination paginationList(String hql, int pageNo, int pageSize, Object... values) {

        return baseDao.findPagination(hql,pageNo,pageSize,values);
    }
}
