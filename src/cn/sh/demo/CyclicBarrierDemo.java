package cn.sh.demo;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by sh on 2017/7/3.
 * 循环栅栏，更加高级的倒计数器
 */
public class CyclicBarrierDemo {

    public static class Solider implements Runnable {

        private String solider;

        private final CyclicBarrier cyclic;

        public Solider(String  solider, CyclicBarrier cyclic) {
           this.cyclic = cyclic;
           this.solider = solider;
        }

        @Override
        public void run() {
            try {
                //等待集合
                cyclic.await();
                //各自工作
                doWork();
                //等待任务完成
                cyclic.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        private void doWork() {
            try {
                int sleep = new Random().nextInt(10) * 1000;
                Thread.sleep(sleep);
                System.out.println(solider + "耗时：" + sleep + "s");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(solider + "：任务完成");
        }
    }

    public static class BarrierRunner implements Runnable {

        private boolean flag;

        private int number;

        public BarrierRunner(boolean flag, int number) {
            this.flag = flag;
            this.number = number;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("司令：[士兵" + number + "个，任务完成！]");
            } else {
                System.out.println("司令：[士兵" + number + "个，集合完毕！]");
                flag = true;
            }
        }
    }

    public static void main(String[] args){
        final int N = 10;
        Thread[] allSoldier = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new BarrierRunner(flag, N));
        System.out.println("集合队伍");
        for (int i = 0; i < N; i++){
            System.out.println("士兵" + i + "报道！");
            allSoldier[i] = new Thread(new Solider("士兵" + i, cyclicBarrier));
            allSoldier[i].start();
        }
    }
}
