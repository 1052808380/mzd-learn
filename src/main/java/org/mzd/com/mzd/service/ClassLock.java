package org.mzd.com.mzd.service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhendong.mzd
 * @description:
 * @date: 2021/7/19 下午8:42
 */
public class ClassLock {

    private static Object lock = new Object();

    /**
     * 锁住静态变量
     * @throws InterruptedException
     */
    public void lockStaticObjectField() throws InterruptedException{
        synchronized (lock){
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10*1000);
        }
    }

    /**
     * 锁住静态方法
     * @throws InterruptedException
     */
    public  synchronized void methodLock() throws InterruptedException{
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(10*1000);
    }

    /**
     * 锁住 xxx.class
     * @throws InterruptedException
     */
    public void lockClass() throws InterruptedException{
        synchronized (ClassLock.class){
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10*1000);
        }
    }

    public static void main(String[] args){
        for (int i = 0; i < 5; i++) {
            Thread worker = new Thread(new ClassLockWorker());
            worker.setName("kite-" + i);
            worker.start();
        }
    }

    public static class ClassLockWorker implements Runnable{
        @Override
        public void run() {
            try {
                ClassLock classLock = new ClassLock();
                // 方式 1
//                classLock.lockStaticObjectField();
//                // 方式 2
                classLock.methodLock();
                // 方式 3
//                classLock.lockClass();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

