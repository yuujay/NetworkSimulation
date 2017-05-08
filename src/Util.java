import java.io.File;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Util {

	public static int getCheckSum(String msg){
		
		try {
			int checkSum = 0;
			for(int i=0;i<msg.length();i++){
				checkSum += msg.charAt(i);
			}
			return checkSum%100;
			
		} catch (Exception e) {
			// TODO: handle exception
			
			System.out.println("getChecksum " + e );
		}
		
		return -1;
		
	}
	
	public static List<String> splitStrings(String message,int length){
		List<String> strings = new ArrayList<String>();
		int index = 0;
		while (index < message.length()) {
		    strings.add(message.substring(index, Math.min(index + length,message.length())));
		    index += length;
		}
		
		return strings;
	}
	
	public static String padSpace(String message){
		StringBuilder outputMessage = new StringBuilder();
		
		int padLen = 15 - message.length();
		outputMessage.append(message);
		for(int i=0;i<padLen;i++){
			outputMessage.append(" ");
		}
		
		return outputMessage.toString();
		
	}
	
	public static void writeFile(String filename, String message) {
		try {
			RandomAccessFile file = new RandomAccessFile("src/"+filename, "rw");
			file.writeBytes(message);
			file.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("wirte file error" + e);
		}
	}
	
	public static String readStringFromFile(RandomAccessFile file,int length){
		try {
			 StringBuilder stringBuilder = new StringBuilder();
      
		        for (int i = 0 ;i<length;i++){
		            stringBuilder.append((char) file.readByte());
		        }

		        return stringBuilder.toString();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Util: readString "+e);
		}
		
		return null;
	}
	
	public static boolean compareCheckSum(String message){
		return false;
	}
	
	public static File setFilePermission(File file){
		
		try{
			
			Set<PosixFilePermission> perms = new HashSet<>();
			perms.add(PosixFilePermission.GROUP_EXECUTE);
			perms.add(PosixFilePermission.OWNER_WRITE);
			perms.add(PosixFilePermission.GROUP_READ);
			perms.add(PosixFilePermission.GROUP_WRITE);
			perms.add(PosixFilePermission.OWNER_READ);
			perms.add(PosixFilePermission.OTHERS_READ);
			perms.add(PosixFilePermission.OTHERS_WRITE);
			perms.add(PosixFilePermission.OTHERS_WRITE);
			

			Files.setPosixFilePermissions(file.toPath(), perms);
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("File create " + e);
		}
		
		return file;
	}
	
	
}
