package com.tct.pbm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadRunnable {
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		/*
		ThreadClass2 threadClass = new ThreadClass2();
		Thread thread = new Thread(threadClass);
		
		thread.start();
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        
        for (int i = 0; i < 10; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
 
                    int poolSize = threadPoolExecutor.getPoolSize();
                    String threadName = Thread.currentThread().getName();
                    System.out.println("[�� ������ ����: " + poolSize + "] �۾� ������ �̸�: " + threadName);
 
                    int value = Integer.parseInt("����");
                }
            };
 
            // executorService.execute(runnable); //execute�� �۾�ó�� ��� ������
            executorService.submit(runnable); // submit�� �۾�ó�� ����� FutureŸ������ ����
 
            Thread.sleep(10);
        }
        executorService.shutdown();

	}

}
class ThreadClass2 implements Runnable{
	
	public void run() {
		System.out.println("Running..");
	}

}