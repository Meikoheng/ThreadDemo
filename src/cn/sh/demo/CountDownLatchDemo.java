package cn.sh.demo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sh on 2017/7/3.
 * 倒计数器
 */
public class CountDownLatchDemo implements Runnable {

    private static final CountDownLatch end = new CountDownLatch(10);

    private static final CountDownLatchDemo demo = new CountDownLatchDemo();

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("check complete");
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            service.submit(demo);
        }
        end.await();
        System.out.println("Fire!");
        service.shutdown();
    }
}
