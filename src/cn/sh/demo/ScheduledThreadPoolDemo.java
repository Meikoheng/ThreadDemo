package cn.sh.demo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by sh on 2017/7/5.
 * 计划任务调度器
 */
public class ScheduledThreadPoolDemo {

    public static class Task implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                System.out.println(System.currentTimeMillis() / 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
//        service.scheduleAtFixedRate(new Task(), 0, 2, TimeUnit.SECONDS);
        service.scheduleWithFixedDelay(new Task(), 0, 2, TimeUnit.SECONDS);
    }
}
