package com.thread.day02;

/**
 * run方法内没有sleep或wait方法时，停止线程
 */
public class RightWayStopThreadWithoutSleep implements Runnable{
    @Override
    public void run() {
           int num =0;
           while (!Thread.currentThread().isInterrupted() &&num<=Integer.MAX_VALUE/2){
               if (num %10000 ==0){
                   System.out.println(num+"是10000倍数");
               }
               num++;
           }
        System.out.println("任务运行结束了");
    }
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();
        Thread.sleep(1000);
        //终端标识
        thread.interrupt();
    }

    private static String test(){
        System.out.println();
    }
}


