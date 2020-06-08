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
		List<String> testSample = Arrays.asList("��", "��", "��", "��", "��", "��", "��", "����", "1", "10", "100", "-100"); 
		
		// 4���� Thread�� ���� ThreadPool���� 
		ExecutorService threadPool = Executors.newFixedThreadPool(4);

		// Thread���� �񵿱�� ����Ǹ� �� ����� ���� Futrure ��ü 
		List<Future<String>> futures = new ArrayList<Future<String>>();

		for (final String sample : testSample) {
			
			Callable<String> callable = new Callable<String>() {
				@Override
				public String call() throws Exception {
					System.out.println("Time : "+ new Date() + " -Thread Name : " + Thread.currentThread().getName() + " - Text : " + sample);


					return "Time : "+ new Date() + " -Thread Name : " + Thread.currentThread().getName() + " - Text : " + sample;
				}
				
			};
			// ������ callable���� threadpool���� �����Ű�� ����� Future ��Ͽ� ��´�. 
			futures.add(threadPool.submit(callable));

		}
		threadPool.shutdown();
		
		List<String> result = new ArrayList<String>();
		for (Future<String> future : futures) { // future�� ��� ��� ��ü�� �޾� List�� ��´�. 
			result.add(future.get()); 
		}
	}

}
