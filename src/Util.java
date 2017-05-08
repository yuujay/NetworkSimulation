
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
	
	public static String dataFrame(int src, int dest,int seq, String message){
		StringBuilder dataFrame = new StringBuilder();
		try {
			
			dataFrame.append("XXD");
			dataFrame.append(src);
			dataFrame.append(dest);
			dataFrame.append(seq)
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("dataFrame " + e);
		}
		
		return dataFrame.toString();
	}
}
