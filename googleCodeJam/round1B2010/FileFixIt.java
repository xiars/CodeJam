package round1B2010;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import path.SourcePath;

/**
 * https://code.google.com/codejam/contest/635101/dashboard#s=p0
 * @author williamxia
 *
 */
public class FileFixIt {
	private final static String pathStart = "/";
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		try {
			FileReader fr = new FileReader(SourcePath.getInputPath() + "/googleCodeJam/round1B2010/A-large-practice.in");
			FileWriter fw = new FileWriter(SourcePath.getOutputPath() + "/googleCodeJam/round1B2010/A-large-practice.out");
			
			BufferedReader br = new BufferedReader(fr);
			BufferedWriter bw = new BufferedWriter(fw);
			int numOfCases = Integer.valueOf(br.readLine());
			
			StringBuffer outPut = new StringBuffer();
			int n;
			int m;
			Map<String, String> pathMap;
			String path;
			String[] caseLines;
			String[] unitPath;
			StringBuffer existPaths;
			int result;
			
			for (int i = 0; i < numOfCases; i++){
				caseLines = br.readLine().split(" ");
				n = Integer.valueOf(caseLines[0]);
				m = Integer.valueOf(caseLines[1]);
				pathMap = new HashMap<String, String>();
				result = 0;
				
				for (int j = 0; j < n + m; j++){
					path = br.readLine();
					unitPath = path.split(pathStart);
					existPaths = new StringBuffer();
					
					if (j < n){
						for (int k = 1; k < unitPath.length; k++){
							existPaths.append(pathStart).append(unitPath[k]);
							pathMap.put(existPaths.toString(), "1");
						}
					} else {
						for (int k = 1; k < unitPath.length; k++){
							existPaths.append(pathStart).append(unitPath[k]);
							
							if (pathMap.get(existPaths.toString()) == null) {
								result++;
								pathMap.put(existPaths.toString(), "1");
							}
						}
					}
				}
				
				outPut.append("Case #").append(i+1).append(": ").append(result).append("\n");
			}
			
			br.close();
			fr.close();
			bw.write(outPut.toString());
			bw.flush();
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
		
	}
}
