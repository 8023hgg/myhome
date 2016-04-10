package home.service.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qijie on 2016/3/30.
 */
public interface BaseServiceI<T> {

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

}
