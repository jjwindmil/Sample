package sp_1day;



public class ThreadTest {
	public static class Run1 implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=0; i<10; i++) {
				System.out.println("[Thread1] " + i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	public static class Run2 implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=0; i<10; i++) {
				System.out.println("[Thread2] " + i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		Runnable r1 = new Run1();
		Runnable r2 = new Run2();
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0; i<10; i++) {
					System.out.println("[Main] " + i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		});

		
		*/
		Runnable r1 = new Run1();
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r1);
		Thread t3 = new Thread(r1);
		t1.start();
		t2.start();
		t3.start();
	}

}
