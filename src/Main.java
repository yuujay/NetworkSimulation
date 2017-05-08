

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
	
	
	
	public static void main(String[] args) {
		
		int src = 0,dest,ttl,startTime =0;
		String msg = "";
		Map<Integer, ArrayList<Integer>> neighbours = new HashMap<>(); 
		Integer sequenceNumber = 0;	
		
		if(args.length >= 6)
		
			src = Integer.parseInt(args[0]);
			dest = Integer.parseInt(args[2]);
			ttl = Integer.parseInt(args[1]);
			msg = args[3];
			startTime = Integer.parseInt(args[4]);
			ArrayList<Integer> neighbArraylist = new ArrayList<>(); 
			for (int i= 5; i<args.length;i++){
				neighbArraylist.add(Integer.parseInt(args[i]));
			}
			neighbours.put(src, neighbArraylist);
			
			
			System.out.println(src);
			System.out.println(dest);
			System.out.println(ttl);
			System.out.println(msg);
			
			System.out.println(neighbours.get(src).toString());
			System.out.println(Util.getCheckSum(msg));
			
			
		
		
	}
	

}
