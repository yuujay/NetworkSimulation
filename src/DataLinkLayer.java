import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectOutputStream.PutField;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class DataLinkLayer {
	public static HashMap<Integer,SortedMap<Integer, String> > allSourceContents = new HashMap<>();
	public static void datalink_receive_from_network(String message, int source, int next_hop){
		
		
		try {
			//set Output Frame
			String channelFileName = "from" + source + "to" + next_hop + ".txt";
//			Path currentRelativePath = Paths.get("src");
			Path currentRelativePath = Paths.get(channelFileName);
			
			String path = currentRelativePath.toAbsolutePath().toString() ;
			
			File fileHandle = new File(path);
						
//			fileHandle = Util.setFilePermission(channelFileName);
			//check file exist and insert file
			
			RandomAccessFile file = new RandomAccessFile(path, "rw");
			
			file.seek(file.length());
			file.writeBytes(setOutputFrame(message));
			file.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	public static void datalink_receive_from_channelFile(int destination,SortedMap<Integer,String> seqMsgMap){
		
		try {
			Path currentRelativePath = Paths.get("");
			String path = currentRelativePath.toAbsolutePath().toString();
//			System.out.println(path);
			File dir = new File(path);
			
			
			File[] allFiles = dir.listFiles(new FilenameFilter() {
			    public boolean accept(File dir, String name) {
			    	return name.startsWith("from") && name.endsWith(".txt");
			    }
			});
			
			for (File file:allFiles){
				file = Util.setFilePermission(file.getAbsolutePath());
				int seekIndex =0;
				StringBuilder recievedDataMsg;
				
				SortedMap<Integer, String> data = new TreeMap<>();
				RandomAccessFile channelFile = new RandomAccessFile(file, "rw");
				
				while(channelFile.length() > channelFile.getFilePointer()){
					String dum = Util.readStringFromFile(channelFile,2);
					
					 
					String message = Util.readStringFromFile(channelFile, 15);
					String checkSum = Util.readStringFromFile(channelFile, 2);
					Integer src = (int) message.charAt(1) - 48;
					Integer dest = (int) message.charAt(2) - 48;
					Integer sequenceNumber =Integer.parseInt(message.substring(3, 5));
					
					if(message.charAt(0) == 'D' && Util.getCheckSum(message) == Integer.parseInt(checkSum)){											
						
						if(dest == destination){							
							data.put(sequenceNumber, seqMsgMap.get(sequenceNumber));							
							allSourceContents.put(src, data);														
						}					
					}
					else if(message.charAt(0) == 'D' && Util.getCheckSum(message) != Integer.parseInt(checkSum)) {
						String nackFrame = TransportLayer.setNACKFrame(dest, src, sequenceNumber);
						datalink_receive_from_network(nackFrame, src, dest);
					}
					else if (message.charAt(0) == 'N') {
					//resend pack with the sequence number	
						String dupDataFrame = TransportLayer.setDataFrame(src, dest, sequenceNumber, seqMsgMap.get(sequenceNumber));
						datalink_receive_from_network(dupDataFrame,dest, src);
						
					}
				}

				recievedDataMsg = new StringBuilder();
				for(Integer sourceID:allSourceContents.keySet()){
					
					recievedDataMsg.setLength(0);
					SortedMap<Integer, String> resultSeqMap = allSourceContents.get(sourceID);
					
					for(String resultStr: resultSeqMap.values()){
						recievedDataMsg.append(resultStr);
					}
					
					
					String newFilePath = "node"+destination+"receivedFile.txt";
					String newMsg = "From "+sourceID+" received : " + recievedDataMsg.toString();
					String path1 = currentRelativePath.toAbsolutePath() + "/" + newFilePath;
					RandomAccessFile newFile = new RandomAccessFile(path1, "rw");
					newFile.writeBytes(newMsg);
					newFile.close();
//					Util.writeFile("node"+destination+"receivedFile.txt", );
				}
//				
//				
				channelFile.close();
				
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("chanbel file "+e);
		}
	
		
		//
		
	}
	
	public static String setOutputFrame(String message){
		String paddedMsg = Util.padSpace(message);
		int checksum = Util.getCheckSum(paddedMsg);
		StringBuilder outputFrame = new StringBuilder();
		outputFrame.append("XX");
		outputFrame.append(paddedMsg);
		if(checksum <10){
			outputFrame.append(String.format("%02d",checksum));	
		}
		else{
			outputFrame.append(checksum);
		}
		
		return outputFrame.toString();
				
	}
	
	

}
