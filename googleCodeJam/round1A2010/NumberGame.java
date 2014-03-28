package round1A2010;

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
 * https://code.google.com/codejam/contest/544101/dashboard#s=p2
 * @author williamxia
 *
 */
public class NumberGame {
	
	static Map<Integer, Integer> failingPositionStartMap = new HashMap<Integer, Integer>();
	
	static {
		for (int i = 1; i <= 1000000; i++){
			System.out.println(i + " " + getFailingPositionStart(i));
		}
	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		try {
			FileReader fr = new FileReader(SourcePath.getInputPath() + "/googleCodeJam/round1A2010/C-large-practice.in");
			FileWriter fw = new FileWriter(SourcePath.getOutputPath() + "/googleCodeJam/round1A2010/C-large-practice.out");

			BufferedReader br = new BufferedReader(fr);
			BufferedWriter bw = new BufferedWriter(fw);
			int numOfCases = Integer.valueOf(br.readLine());
			
			StringBuffer outPut = new StringBuffer();
			
			int positionAStart;
			int positionAEnd;
			int positionBStart;
			int positionBEnd;
			
			String[] positions;
			long result;
			for (int i = 0; i < numOfCases; i++){
				positions = br.readLine().split(" ");
				
				positionAStart = Integer.valueOf(positions[0]);
				positionAEnd = Integer.valueOf(positions[1]);
				positionBStart = Integer.valueOf(positions[2]);
				positionBEnd = Integer.valueOf(positions[3]);
				
				result = getWinningPositionNum(positionAStart, positionAEnd, positionBStart, positionBEnd);
				
				System.out.println("Case #" + (i + 1) + ": " + result);
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
	
	static long getWinningPositionNum(int positionAStart, int positionAEnd, int positionBStart, int positionBEnd){
		long winningPositionNum = 0;
		int failingPositionStart;
		int failingPositionEnd;
		
		for (int i = positionAStart; i <= positionAEnd; i++){
			failingPositionStart = getFailingPositionStart(i);
			failingPositionEnd = getFailingPositionEnd(i, failingPositionStart);
			
//			for (int j = positionBStart; j <= positionBEnd; j++){
//				if (j < failingPositionStart || j > failingPositionEnd){
//					winningPositionNum ++;
//				}
//			}
			if (failingPositionStart > positionBStart){
				if (positionBEnd >= failingPositionStart){
					winningPositionNum += (failingPositionStart - positionBStart);
				} else {
					winningPositionNum += (positionBEnd - positionBStart) + 1;
				}
			} 
			
			if (failingPositionEnd < positionBEnd){
				if (positionBStart <= failingPositionEnd){
					winningPositionNum += (positionBEnd - failingPositionEnd);
				} else {
					winningPositionNum += (positionBEnd - positionBStart) + 1;
				}
			}
		}
		
		return winningPositionNum;
	}
	
	static int getFailingPositionStart(int firstParam){
		if (failingPositionStartMap.get(firstParam) != null){
			return failingPositionStartMap.get(firstParam);
		} else {
			if (firstParam == 1){
				failingPositionStartMap.put(firstParam, 1);
			} else {
				failingPositionStartMap.put(firstParam, firstParam + 1 - getFailingPositionStart(getFailingPositionStart(firstParam - 1))) ;
			}
			return failingPositionStartMap.get(firstParam);
		}
	}
	
	static int getFailingPositionEnd(int firstParam, int failingPositionStart){
		if (firstParam == 1){
			return 1;
		} else {
			return failingPositionStart + firstParam - 1;
		}
	}
}
