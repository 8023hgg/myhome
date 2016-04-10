package home.util.lock;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by qijie on 2016/4/7.
 */
public class LockUtils {

    private static Set<String> typeSet = new HashSet<String>();

    /**
     * 根据type，加锁
     */
    public void lock(String type) {
        synchronized (typeSet) {
            System.out.println("开始加锁，type=" + type);
            while (!tryLock(type)) {
                try {
                    typeSet.wait();
                } catch (InterruptedException ignore) {
                }
            }
            System.out.println("结束加锁，type=" + type);
        }
    }

    /**
     * 根据type，尝试加锁 成功返回true，失败返回false
     */
    public boolean tryLock(String type) {
        synchronized (typeSet) {
            boolean result = typeSet.add(type);
            System.out.println("尝试加锁，type=" + type + "，结果=" + result);
            return result;
        }
    }

    /**
     * 根据type，解锁
     */
    public void unlock(String type) {
        synchronized (typeSet) {
            if (typeSet.contains(type)) {
                typeSet.remove(type);
                System.out.println("解锁，type=" + type);
            } else {
                System.out.println("不需要解锁,Type=" + type);
            }

            typeSet.notifyAll();
        }
    }
}
