package cn.sh.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by sh on 2017/7/3.
 */
public class ReadWriteLockDemo {

    public static final Lock  lock = new ReentrantLock();
    public static final ReentrantReadWriteLock readWriterLock = new ReentrantReadWriteLock(true);
    public static final Lock readLock = readWriterLock.readLock();
    public static final Lock writerLock = readWriterLock.writeLock();
    private int value = 0;

    public void handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "------->Read-----" + this.value);
        } finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            this.value++;
            System.out.println(Thread.currentThread() + "----->Write----" + this.value);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args){
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();
        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handleRead(readLock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handleWrite(writerLock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 19; i<=20 ; i++) {
            new Thread(writeRunnable).start();
        }
        for (int i = 0; i <= 18; i++) {
            new Thread(readRunnable).start();
        }
    }
}
