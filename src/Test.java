import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		System.out.println(String.format("%02d", 1));
		System.getProperty("user.dir");
		File path = new File("src");
		Path currentRelativePath = Paths.get("src");
		path.getPath();
		System.out.println(currentRelativePath.toAbsolutePath());
		
		
	}

}
