package home.manager;

import home.entity.Test;
import home.manager.thread.TestThread;
import home.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by qijie on 2016/4/7.
 */
@Component
public class ThreadTestManager {

    @Autowired
    private TestService testService;

    public void threadTest(){

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i =0;i<10;i++){
            executorService.execute(new TestThread("12w132",testService));
        }
    }

    public static void main(String[] argus){

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i =1;i<3;i++){
//            executorService.execute(new TestThread("1",testService));
        }
    }
}
