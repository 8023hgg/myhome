package home.dao.base;

import home.common.page.Pagination;
import org.hibernate.*;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

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
    public void save(T t) {
        getHibernateTemplate().save(t);
    }

    public void update(T t) {
        getHibernateTemplate().update(t);
    }

    public void saveOrUpdate(T t) {
        getHibernateTemplate().saveOrUpdate(t);
    }

    public List<?> findList(String hql) {
        List<?> list = getHibernateTemplate().find(hql);
        return list;
    }

    public List<T> findAll() {
        return null;
    }

    public void delete(T t) {
        getHibernateTemplate().delete(t);
    }

    public T find(Serializable entityId) {
       return  get(entityId);
    }

    public T get(Serializable entityId) {
        return (T) getHibernateTemplate().get(entityClazz, entityId);
    }

    public void flush() {
        getHibernateTemplate().flush();
    }

    public void clear() {
        getHibernateTemplate().clear();
    }

    /**
     * Pagination分页查询
     * @param hql
     * @param pageNo
     * @param pageSize
     * @param values
     * @return
     */
    public Pagination findPagination(String hql, int pageNo, int pageSize, Object... values) {
        Integer totalCount = queryTotalCount(hql, values);
        List<T> list = queryList(hql,pageNo,pageSize,values);
        Pagination pagination = new Pagination(pageNo, pageSize, totalCount, list);
        return pagination;
    }


    /**
     * 分页查询
     * @param queryString
     * @param pageNo
     * @param pageSize
     * @param values
     * @return
     */
    public List<T> queryList(final String queryString, final int pageNo,final int pageSize,final Object... values){
        return (List<T>)getHibernateTemplate().execute(
                new HibernateCallback() {
                    public List<T> doInHibernate(Session session)
                            throws HibernateException {
                        Query query = session.createQuery(queryString);
                        query.setFirstResult(pageNo);
                        query.setMaxResults(pageSize);
                        if(values != null) {
                            for(int i = 0; i < values.length; ++i) {
                                query.setParameter(i, values[i]);
                            }
                        }
                        return query.list();
                    }
                });

    }

    /**
     * 查询总数量
     * @param queryString
     * @param values
     * @return
     */
    public Integer queryTotalCount(final String queryString,final Object... values){
        return (Integer)getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        Assert.hasText(queryString);
                        Query queryObject = session.createQuery(queryString);
                        if(values != null) {
                            for(int i = 0; i < values.length; ++i) {
                                queryObject.setParameter(i, values[i]);
                            }
                        }
                        return queryObject.list().size();
                    }
                });
    }
}
