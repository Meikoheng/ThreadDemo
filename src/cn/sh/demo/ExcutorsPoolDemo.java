package cn.sh.demo;

import java.util.concurrent.*;

/**
 * Created by sh on 2017/7/6.
 * 线程池扩展
 */
public class ExcutorsPoolDemo {

    public static class Task implements Runnable {

        private String name;

        public Task(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("正在执行:Thread ID:" + Thread.currentThread().getId()
                + ",Task Name=" + name);
        }
    }

    static class MyThreadPoolExecutor extends ThreadPoolExecutor {

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            System.out.println("准备执行:" + ((Task) r).getName());
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            System.out.println("执行完成:" + ((Task) r).getName());
        }

        @Override
        protected void terminated() {
            System.out.println("线程池退出！");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = new MyThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        for (int i = 0; i < 5; i++) {
            Task task = new Task("TASK-" + i);
            service.execute(task);
            Thread.sleep(100);
        }
        service.shutdown();
    }
}
