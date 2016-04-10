package home.service.test;

import home.entity.Test;
import home.service.base.BaseServiceImpl;
import home.util.lock.LockUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by qijie on 2016/4/7.
 */
@Service("testService")
@Transactional
public class TestService extends BaseServiceImpl<Test>{

    public void threadTest(String id){
        String hql = "from Test t where t.id = 1";
        Test test = queryTestByHql(hql);
        test.setStock(test.getStock()-1);
        test.setVersion(test.getVersion()+1);
        saveOrUpdate(test);
    }
    /**
     * Hql 查询 Test
     * @param hql
     * @return
     */
    public Test queryTestByHql(String hql){

        List<Test> tests = (List<Test>)this.findList(hql);
        if(CollectionUtils.isNotEmpty(tests)){
            return tests.get(0);
        }
        return null;
    }

}
