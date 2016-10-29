package concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
	
	
	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(3);
		new Racer(countDownLatch , "R1").start() ; 
		new Racer(countDownLatch , "R2").start() ; 
		new Racer(countDownLatch , "R3").start() ; 
		
		for(int i = 0 ;i < 3;i++){
			System.out.println(3-i);
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				e.printStackTrace();  
			}
			
			countDownLatch.countDown();  
			if(i == 2)
				System.out.println("start.....");
		}
	}

	
}


class Racer extends Thread{
	
	
	private CountDownLatch countDownLatch ; 
	 
	public Racer(CountDownLatch countDownLatch , String name){
		this.countDownLatch = countDownLatch ; 
		setName(name) ;
	}
	
	public void run(){
		try{
			countDownLatch.await() ; 
			for(int i = 0;i < 3;i++){
				System.out.println(getName() + "   " + i);
			}
			
		}catch(InterruptedException e){
			e.printStackTrace();  
			
		}
	}
	
}








