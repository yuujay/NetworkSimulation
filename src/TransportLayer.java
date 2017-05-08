import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class TransportLayer {
	
	public int source;
	public int destination;
	public String message;
	public static SortedMap<Integer, String> seqMsgMap = new TreeMap<>();
	public static Integer sequenceNumber = 0;
	
	public TransportLayer(int source, int destination, String message) {
		super();
		this.source = source;
		this.destination = destination;
		this.message = message;
	}
	
	public static String setDataFrame(int src, int dest,int seq, String message){
		StringBuilder dataFrame = new StringBuilder();
		try {
			
			dataFrame.append("D");
			dataFrame.append(src);
			dataFrame.append(dest);
			if(seq <10){
				dataFrame.append(String.format("%02d", seq));
			}
			else{
				dataFrame.append(seq);	
			}
			dataFrame.append(message);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("dataFrame " + e);
		}
		
		return dataFrame.toString();
	}

	public static String setNACKFrame(int src, int dest,int seq){
		StringBuilder nackFrame = new StringBuilder();
		try {
			
			nackFrame.append("N");
			nackFrame.append(src);
			nackFrame.append(dest);
			if(seq <10){
				nackFrame.append(String.format("%02d", seq));
			}
			else{
				nackFrame.append(seq);	
			}			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("NACK Frame " + e);
		}
		
		return nackFrame.toString();
	}
	
	
	public void send() {
		
		List<String> stringMessageFragment = Util.splitStrings(message, 5);
		for (String msg: stringMessageFragment){
			String dataFrame = setDataFrame(this.source, this.destination, sequenceNumber, msg);
			seqMsgMap.put(sequenceNumber, msg);
			sequenceNumber++;
			//call network layer method
			NetworkLayer.network_recieve_from_transport(dataFrame,this.source, destination,seqMsgMap);
//			
			
		}
		
		DataLinkLayer.datalink_receive_from_channelFile(destination, seqMsgMap);
		
		
		
		
	}
	
	

}
