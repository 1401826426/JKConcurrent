package concurrent;

import java.util.Random;
import java.util.concurrent.Exchanger;

public class ExchangerDemo {
	
	
	public static void main(String[] args) {
		Exchanger<String> ex = new Exchanger<>() ; 
		new A(ex , "ThreadA").start() ; 
		new A(ex , "ThreadB").start() ; 
		new A(ex , "ThreadC").start();
		new A(ex , "ThreadD").start(); 
	}
}


class A extends Thread{
	
	private Exchanger<String> ex ; 
	
	public A(Exchanger<String> ex , String name){
		this.ex = ex ; 
		setName(name) ; 
	}
	
	
	public void run(){
		
		String str = null ; 
		
		try{
			for(int i = 0;i < 4;i++){
				Random random = new Random() ; 
				Thread.sleep(random.nextInt(1000)) ;
				str = ex.exchange("from   " +  getName() + "   " + i) ; 
				System.out.println(getName() + "  " + i +  "   get   " +   str);
			}
		}catch(InterruptedException e){
			e.printStackTrace(); 
		}
	}
	
}




