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
                    System.out.println("[총 스레드 개수: " + poolSize + "] 작업 스레드 이름: " + threadName);

                    int value = Integer.parseInt("숫자");
                }
            };

            // executorService.execute(runnable); //execute는 작업처리 결과 못받음
            executorService.
                    submit(runnable); // submit은 작업처리 결과를 Future타입으로 리턴

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