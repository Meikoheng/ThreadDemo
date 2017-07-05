package cn.sh.demo;

import java.util.concurrent.*;

/**
 * Created by sh on 2017/7/5.
 * 拒绝策略
 */
public class RejectThreadPoolDemo {

    public static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ":Thread Id:" + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class MyRejectedHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println(r.toString() + " is discard");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        ExecutorService service = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10),
                Executors.defaultThreadFactory(),
                new MyRejectedHandler());
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            service.submit(task);
        }
    }
}
