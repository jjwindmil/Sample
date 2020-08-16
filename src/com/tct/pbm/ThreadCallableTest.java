package com.tct.pbm;

import java.time.LocalTime;
import java.util.concurrent.*;

public class ThreadCallableTest {
    static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            String result = "Called at " + LocalTime.now();
            Thread.sleep(3000);
            return result;
        }
    }

    public static void main(String args[]) {
        Thread thread = new Thread(
                () -> System.out.println("Runnable at " + LocalTime.now()));
        thread.start();

        MyCallable callable = new MyCallable();
        FutureTask futureTask = new FutureTask(callable);
        Thread thread1 = new Thread(futureTask);
        thread1.start();

        // 결과가 리턴되기를 기다립니다.
        try {
            System.out.println("result : " + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        MyCallable callable1 = new MyCallable();
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<String> future = executor.submit(callable1);

        // 결과가 리턴되기를 기다립니다.
        try {
            System.out.println("result : " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("1111");
    }
}
