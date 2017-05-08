

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
	
	
	
	public static void main(String[] args) throws InterruptedException {
		
		int src = 0,dest,ttl,startTime =0;
		String msg = "";
		Map<Integer, ArrayList<Integer>> neighbours = new HashMap<>(); 
		Integer sequenceNumber = 0;	
		
		if(args.length >= 6)
			
			
			
			src = Integer.parseInt(args[0]);
			dest = Integer.parseInt(args[2]);
			ttl = Integer.parseInt(args[1]);
		
			startTime = Integer.parseInt(args[4]);
			msg = args[3];
			System.out.println(msg);
			ArrayList<Integer> neighbArraylist = new ArrayList<>(); 
			for (int i= 5; i<args.length;i++){
				neighbArraylist.add(Integer.parseInt(args[i]));
			}
			neighbours.put(src, neighbArraylist);
//			TimeUnit.SECONDS.sleep(startTime);
			TransportLayer transObj = new TransportLayer(src,dest,msg);
			
			transObj.send();
			
			
			
			
			
			
			
			
			
		
		
	}
	

	

}
