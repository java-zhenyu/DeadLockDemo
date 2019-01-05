package com.atguigu.Interview.study.thread;

import java.util.concurrent.TimeUnit;


/**
 * @auther zzyy
 * @create 2018-12-12 17:36
 */
public class DeadLockDemo extends Thread
{
    private String lockA;
    private String lockB;

    public DeadLockDemo(String lockA, String lockB)
    {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    public void run()
    {
        synchronized (lockA)
        {
            System.out.println(Thread.currentThread().getName() + " 尝试获得: " + lockA);
            try
            {
                TimeUnit.SECONDS.sleep(2);
                synchronized (lockB)
                {
                    System.out.println(Thread.currentThread().getName() + " 尝试获得: " + lockB);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        String lockA = "lockA";
        String lockB = "lockB";
        DeadLockDemo t1 = new DeadLockDemo(lockA, lockB);
        DeadLockDemo t2 = new DeadLockDemo(lockB, lockA);
        t1.setName("Thread01");
        t2.setName("Thread02");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}