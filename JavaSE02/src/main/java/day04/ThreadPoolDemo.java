package day04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池：
 *  1.控制线程数量（防止cpu过度切换）
 *  2.重用线程(过多的资源的消耗)
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            Runnable runn = new Runnable() {
                public void run() {
                    Thread t = Thread.currentThread();
                    System.out.println(t.getName()+"正在执行任务...");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(t.getName()+"执行任务完毕!!!");
                }
            };
            threadPool.execute(runn);
            System.out.println("将一个任务交给线程池");
        }
        //线程工作完毕后停止
        threadPool.shutdown();

        //线程立刻停止工作
//        threadPool.shutdownNow();
        System.out.println("线程池执行完毕..");

    }
}







