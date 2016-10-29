package concurrent;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
	public static void main(String[] args) {
		Semaphore semphore = new Semaphore(2) ; 
		Person p1 = new Person(semphore , "A") ; 
		p1.start() ; 
		Person p2 = new Person(semphore , "B") ; 
		p2.start() ; 
		Person p3 = new Person(semphore , "C") ; 
		p3.start() ; 
	}
	
}


class Person extends Thread{
	
	
	private Semaphore semaphore ; 
	
	public Person(Semaphore semaphore , String name){
		setName(name) ; 
		this.semaphore = semaphore ; 
	}
	
	public void run(){
		System.out.println(getName() + "   is wating.......");
		try{
			semaphore.acquire(); 
			System.out.println(getName() + " is   serving.....");
			Thread.sleep(1000);
			
		}catch(InterruptedException e){
			e.printStackTrace(); 
		}
		System.out.println(getName() + "  is  done");
		semaphore.release();
	}
	
	
}


















