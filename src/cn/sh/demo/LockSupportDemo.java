package cn.sh.demo;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by sh on 2017/7/4.
 * 线程阻塞工具类LockSupport
 */
public class LockSupportDemo {

    public static Object u = new Object();

    public static ChangeObjectThread t1 = new ChangeObjectThread("t1");

    public static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread{

        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (u) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("in " + getName());
//                suspend();
                LockSupport.park();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
//        Thread.sleep(4000);
        t2.start();
//        t1.resume();
//        Thread.sleep(4000);
//        t2.resume();
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }
}
