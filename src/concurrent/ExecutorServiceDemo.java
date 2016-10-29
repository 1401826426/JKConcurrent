package concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceDemo {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService es = Executors.newFixedThreadPool(2) ; 
		
		Future<Integer> e1 = es.submit(new MC(1  , 100)) ; 
		Future<Integer> e2 = es.submit(new MC(100 , 10000)) ; 
		
		System.out.println(e1.get() + " : " + e2.get());
		
		es.shutdown();
		
		
	}
	
	
	
}


class MC implements Callable<Integer>{

	private int begin ; 
	private int end ; 
	
	public  MC(Integer begin , Integer end){
		this.begin = begin ; 
		this.end = end;  
	}
	
	
	@Override
	public Integer call() throws Exception {
		int sum = 0 ; 
		for(int i = begin;i <= end;i++){
			sum += i ; 
		}
		return sum;
	}
	
}













