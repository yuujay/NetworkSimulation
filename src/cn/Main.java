package cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
	
	
	
	public static void main(String[] args) {
		
		int src,dest,ttl,startTime =0;
		String msg = "";
		Map<Integer, ArrayList<Integer>> neighbours = new HashMap<>(); 
			
		
			
			src = Integer.parseInt(args[1]);
			dest = Integer.parseInt(args[3]);
			ttl = Integer.parseInt(args[2]);
			startTime = Integer.parseInt(args[5]);
			
			
			System.out.println(src);
			System.out.println(dest);
//			System.out.println(ttl);
//			System.out.println(startTime);
			
			
		
		
	}
	

}
