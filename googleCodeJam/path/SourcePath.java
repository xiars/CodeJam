package path;

public class SourcePath {
	public static String getInputPath(){
		String path = System.getProperty("user.dir") + "/input";
		return path;
	}
	
	public static String getOutputPath(){
		String path = System.getProperty("user.dir") + "/output";
		return path;
	}
}
