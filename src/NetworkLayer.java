import java.util.SortedMap;
import java.util.TreeMap;

public class NetworkLayer {
	
	
	public static void network_recieve_from_transport(String message,int source,int destination,SortedMap<Integer,String> seqMsgMap){
		DataLinkLayer.datalink_receive_from_network(message,source,destination);
		
		
	}
	
	
	
	

}
