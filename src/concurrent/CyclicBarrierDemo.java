package concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
	
	
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(3 , new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Game  start!!!");
			}
		}) ; 
		new Player(cyclicBarrier , "A").start(); 
		new Player(cyclicBarrier , "B").start();  
		new Player(cyclicBarrier, "C").start();  
		
	}
	
	
}


class Player extends  Thread{
	
	private  CyclicBarrier cyclicBarrier ; 
	
	public Player(CyclicBarrier cyclicBarrier , String name){
		this.cyclicBarrier = cyclicBarrier ; 
		setName(name) ; 
	}
	
	
	public void run(){
		System.out.println(getName() + "    is wating  other  players ......");
		try {
			cyclicBarrier.await() ;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	
}

















