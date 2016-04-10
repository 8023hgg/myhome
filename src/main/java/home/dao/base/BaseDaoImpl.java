package home.dao.base;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by qijie on 2016/3/30.
 */
@Repository("baseDao")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    protected Class<T> entityClazz;

    @Resource
    protected SessionFactory sessionFactory;

    @PostConstruct
    public void setSF() {
        super.setSessionFactory(sessionFactory);
    }

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        try {
            entityClazz = (Class<T>) ((ParameterizedType) getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[0];
        } catch (Exception ex) {
        }
    }
    @Override
    public void save(T t) {
        getHibernateTemplate().save(t);
    }

    @Override
    public void update(T t) {
        getHibernateTemplate().update(t);
    }

    @Override
    public void saveOrUpdate(T t) {
        getHibernateTemplate().saveOrUpdate(t);
    }

    @Override
    public List<?> findList(String hql) {
        List<?> list = getHibernateTemplate().find(hql);
        return list;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public void delete(T t) {
        getHibernateTemplate().delete(t);
    }

    @Override
    public T find(Serializable entityId) {
       return  get(entityId);
    }

    @Override
    public T get(Serializable entityId) {
        return (T) getHibernateTemplate().get(entityClazz, entityId);
    }

    @Override
    public void flush() {
        getHibernateTemplate().flush();
    }

    @Override
    public void clear() {
        getHibernateTemplate().clear();
    }
}
