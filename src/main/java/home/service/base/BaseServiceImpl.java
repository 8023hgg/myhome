package home.service.base;

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

    @Override
    public void save(T t) {
        baseDao.save(t);
    }

    @Override
    public void update(T t) {
        baseDao.update(t);
    }

    @Override
    public void saveOrUpdate(T t) {
        baseDao.saveOrUpdate(t);
    }

    @Override
    public List<?> findList(String hql) {
        return baseDao.findList(hql);
    }

    @Override
    public List<T> findAll() {
        return baseDao.findAll();
    }

    @Override
    public void delete(T t) {
        baseDao.delete(t);
    }

    @Override
    public T find(Serializable entityId) {
        return (T) baseDao.find(entityId);
    }

    @Override
    public T get(Serializable entityId) {
        return (T) baseDao.get(entityId);
    }

    @Override
    public void flush() {
        baseDao.flush();
    }

    @Override
    public void clear() {
        baseDao.clear();
    }
}
