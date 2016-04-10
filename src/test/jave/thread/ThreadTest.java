package thread;

import home.manager.ThreadTestManager;
import home.service.test.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by qijie on 2016/4/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ThreadTest {

    @Autowired
    private ThreadTestManager threadTestManager;

    @Autowired
    private TestService testService;

    @Test
    public void threadTest(){
        threadTestManager.threadTest();
    }

//    public void addTest(){
//
//        for(int i=0;i<10;i++){
//            testService.save(new home.entity.Test());
//        }
//    }




}
