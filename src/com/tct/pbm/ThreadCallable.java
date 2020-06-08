package com.tct.pbm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadCallable {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		List<String> testSample = Arrays.asList("가", "나", "다", "라", "마", "바", "사", "후후", "1", "10", "100", "-100"); 
		
		// 4개의 Thread를 가진 ThreadPool생성 
		ExecutorService threadPool = Executors.newFixedThreadPool(4);

		// Thread들이 비동기로 수행되면 그 결과를 담을 Futrure 객체 
		List<Future<String>> futures = new ArrayList<Future<String>>();

		for (final String sample : testSample) {
			
			Callable<String> callable = new Callable<String>() {
				@Override
				public String call() throws Exception {
					System.out.println("Time : "+ new Date() + " -Thread Name : " + Thread.currentThread().getName() + " - Text : " + sample);


					return "Time : "+ new Date() + " -Thread Name : " + Thread.currentThread().getName() + " - Text : " + sample;
				}
				
			};
			// 생성된 callable들을 threadpool에서 수행시키고 결과는 Future 목록에 담는다. 
			futures.add(threadPool.submit(callable));

		}
		threadPool.shutdown();
		
		List<String> result = new ArrayList<String>();
		for (Future<String> future : futures) { // future에 담긴 결과 객체를 받아 List에 담는다. 
			result.add(future.get()); 
		}
	}

}
