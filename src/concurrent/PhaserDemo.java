package concurrent;

import java.util.concurrent.Phaser;

public class PhaserDemo {
	
	public static void main(String[] args) {
		
		Phaser phaser = new Phaser(1) ; 
		System.out.println("starting........");
		
		
		new Worker(phaser , "服务员").start();  
		new Worker(phaser , "厨师").start();  
		new Worker(phaser , "上菜员").start();  
		
		
		for(int i = 1;i <= 3;i++){
			phaser.arriveAndAwaitAdvance() ; 
			System.out.println("Order " + i + "  finished!!");
		}
		phaser.arriveAndDeregister() ; 
		System.out.println("All done!!!");
		
	}
	
	
}


class Worker extends Thread{
	
	private Phaser phaser  ; 
	
	public Worker(Phaser phaser , String name){
		setName(name) ; 
		this.phaser = phaser ; 
		this.phaser.register() ; 
	}
	
	public void run(){
		
		for(int i = 1;i <= 3;i++){
			System.out.println("current order : " + i + ":"  + getName());
			if(i == 3){
				this.phaser.arriveAndDeregister() ; 
			}else{
				this.phaser.arriveAndAwaitAdvance() ; 
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	
}







































