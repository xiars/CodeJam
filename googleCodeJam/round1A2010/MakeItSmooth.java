package round1A2010;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import path.SourcePath;

/**
 * https://code.google.com/codejam/contest/544101/dashboard#s=p1
 * @author williamxia
 *
 */
public class MakeItSmooth {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		try {
			FileReader fr = new FileReader(SourcePath.getInputPath() + "/googleCodeJam/round1A2010/B-small-practice.in");
			FileWriter fw = new FileWriter(SourcePath.getOutputPath() + "/googleCodeJam/round1A2010/B-small-practice.out");

			BufferedReader br = new BufferedReader(fr);
			BufferedWriter bw = new BufferedWriter(fw);
			int numOfCases = Integer.valueOf(br.readLine());
			
			StringBuffer outPut = new StringBuffer();
			String[] caseKeys;
			int deleteCost;
			int insertCost;
			int mostDistance;
			int arrayLenth;
			String[] pixelArray;
			
			for (int i = 0; i < numOfCases; i++){
				caseKeys = br.readLine().split(" ");
				
				deleteCost = Integer.valueOf(caseKeys[0]);
				insertCost = Integer.valueOf(caseKeys[1]);
				mostDistance = Integer.valueOf(caseKeys[2]);
				arrayLenth = Integer.valueOf(caseKeys[3]);
				
				pixelArray = br.readLine().split(" ");
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
