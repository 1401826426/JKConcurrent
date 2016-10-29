package concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ForkJoinDemo {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ForkJoinPool  forkJoinPool = new ForkJoinPool(10) ; 
		
		Future<Long> result = forkJoinPool.submit(new NTask(1 ,99)) ;
		
		System.out.println(result.get());
		
		forkJoinPool.shutdown();  
		
	}

}



class NTask extends RecursiveTask<Long>{

	private static final long serialVersionUID = 1L;

	private int begin , end ; 
	
	private static int THRESH_HOLD = 2 ; 
	
	private static int 	MAX = 99; 
	
	
	private static Lock lock = new ReentrantLock() ; 
	
	public NTask(int begin , int end){
		this.begin = begin ; 
		this.end = end ; 
	}
	
	
	public void output(){
		lock.lock(); 
		for(int i = 1;i <= (begin-1)*3;i++)
			System.out.print(" ");
		boolean flag = false ; 
		for(int i = begin;i <= end;i++){
			if(flag)System.out.print(" ");
			System.out.printf("%02d" , i);
			flag = true ; 
		}
		for(int i = end+1;i  <= MAX ;i++)
			System.out.print("  ");
		System.out.println();
		lock.unlock(); 
	}
	
	
	@Override
	protected Long compute() {
		output() ; 
		long sum = 0 ; 
		if((end-begin) <= THRESH_HOLD){
			for(int i = begin;i<=end;i++)
				sum += i ; 
		}else{
			int mid = (begin + end) >> 1 ; 
			NTask left = new NTask(begin , mid) ; 
			left.fork() ; 
			NTask right = new NTask(mid+1 , end) ;
			right.fork() ; 
			Long lr = left.join() ; 
			Long rr = right.join() ; 
			sum += lr + rr ; 
		}
		return sum;
	}
	
}

















