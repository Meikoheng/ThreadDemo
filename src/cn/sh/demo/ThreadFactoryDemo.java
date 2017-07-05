package cn.sh.demo;

import java.util.concurrent.*;

/**
 * Created by sh on 2017/7/5.
 */
public class ThreadFactoryDemo {

    static final Object o = new Object();

    public static class Task implements Runnable {

        @Override
        public void run() {
            synchronized (o) {
                try {
                    Thread.sleep(3000);
                    System.out.println(System.currentTimeMillis() / 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class MyFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            System.out.println(thread.getName() + "被创建");
            return thread;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        ExecutorService service = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                new MyFactory());
        for (int i = 0; i < 5; i++) {
            service.submit(task);
        }
        Thread.sleep(4000);
    }
}
