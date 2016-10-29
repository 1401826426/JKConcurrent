package concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class LockDemo {
	
	public static void main(String[] args) {
		new NT().start();  
		new NT().start();  
		new NT().start();  
	}
	
	
}

class Data{
	static int i  =0 ; 
//	static Lock lock = new ReentrantLock() ; 
	static AtomicInteger atomic = new AtomicInteger(0) ; 
	static void operate(){
		System.out.println(atomic.incrementAndGet());
//		lock.lock();
//		i++ ; 
//		System.out.println(i);
//		lock.unlock();
	}
}


class NT extends Thread{
	
	public void run(){
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Data.operate(); 
		}
	}
	
	
}






















