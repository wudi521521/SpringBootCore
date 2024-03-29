package com.thread.day02;

import com.thread.day01.Thread02;

public class RightWayStopThreadWithoutSleepWithInterrupt {

    public static void main(String[] args) throws InterruptedException {
      Runnable runnable=  ()->{
            int num =0;
            while(num<=300 && !Thread.currentThread().isInterrupted()){
                if (num %100==0){
                    System.out.println(num+"是100的倍数");
                }
                num++;
            }
          try {
              Thread.sleep(1000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      };
      Thread thread = new Thread(runnable);
      thread.start();
      Thread.sleep(1000);
      thread.interrupt();
    }
}
