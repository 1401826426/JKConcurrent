package concurrent;

import java.util.ArrayList;
import java.util.List;

public class StreamDemo {
	
	public static void main(String[] args) {
		List<String> ls = new ArrayList<>() ; 
		ls.add("abc") ;ls.add("bcd") ; 
		ls.add("cde");ls.add("def") ; 
		ls.add("abc") ; 
		ls.stream().sorted().forEach(e -> System.out.println(e));
		
		System.out.println(ls.stream().distinct().count());
		
	}
	
	
	
}
