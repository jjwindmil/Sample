package sp_1day;

import java.util.concurrent.locks.ReentrantLock;

class ThreadClass extends Thread { // 'Thread' Class를 상속받는다 
    String thread_name; 
    public ThreadClass(String name) { 
        this.thread_name = name; 
    } 
    ReentrantLock lock = new ReentrantLock(); 
    public void run() { 
    	lock.lock();
    	System.out.println(thread_name); 
        for(int i=0; i<30; i++) { 
            System.out.print((i+1)+ " ");
            try { 
                sleep(10); 
            } catch (InterruptedException e) { e.printStackTrace(); }
           
        }
        lock.unlock();
    } 
} 

public class MutexThreadTest { 
    public static void main(String[] args) throws InterruptedException { 
        ThreadClass tc1 = new ThreadClass("[Thread1] ");
        ThreadClass tc2 = new ThreadClass("[Thread2] ");
        ThreadClass tc3 = new ThreadClass("[Main] ");
        tc1.start(); 
        tc2.start();
        tc3.start();
        
        tc1.join();
        tc2.join();
        tc3.join();
    } 
}