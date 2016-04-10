package home.manager.thread;

import home.service.test.TestService;
import home.util.lock.LockUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Created by qijie on 2016/4/7.
 */
public class TestThread implements Runnable {

    private final String id;
    private final TestService testService;

    public TestThread(String id, TestService testService){
        super();
        this.testService = testService;
        this.id = id;
    }

    @Override
    public void run(){
        LockUtils lockUtils = new LockUtils();
        try {
            lockUtils.lock(id);
            System.out.println(Thread.currentThread().getName() + "线程被调用了" + ",当前时间：" + System.currentTimeMillis());
            testService.threadTest(id);
            lockUtils.unlock(id);
        }catch (Exception e){
            System.out.print(e.getMessage());
        }
    }

}
