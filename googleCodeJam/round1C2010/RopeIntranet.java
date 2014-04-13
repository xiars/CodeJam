package round1C2010;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import path.SourcePath;

/**
 * https://code.google.com/codejam/contest/619102/dashboard#s=p0
 * @author williamxia
 *
 */
public class RopeIntranet {
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		try {
			FileReader fr = new FileReader(SourcePath.getInputPath() + "/googleCodeJam/round1C2010/A-small-practice.in");
			FileWriter fw = new FileWriter(SourcePath.getOutputPath() + "/googleCodeJam/round1C2010/A-small-practice.out");

			BufferedReader br = new BufferedReader(fr);
			BufferedWriter bw = new BufferedWriter(fw);
			int numOfCases = Integer.valueOf(br.readLine());
			
			StringBuffer outPut = new StringBuffer();
			int numOfWires;
			String[] wires;
			List<Integer> leftWindow;
			List<Integer> rightWindow;
			int intersectionNum;
			
			for (int i = 0; i < numOfCases; i++){
				numOfWires = Integer.valueOf(br.readLine());
				
				leftWindow = new ArrayList<Integer>(numOfWires);
				rightWindow = new ArrayList<Integer>(numOfWires);
				
				for (int j = 0; j < numOfWires; j++){
					wires = br.readLine().split(" ");
					leftWindow.add(Integer.parseInt(wires[0]));
					rightWindow.add(Integer.parseInt(wires[1]));
				}
				
				intersectionNum = getIntersectionNum(leftWindow, rightWindow);
				System.out.println("Case #" + (i + 1) + ": " + intersectionNum);
				outPut.append("Case #").append(i+1).append(": ").append(intersectionNum).append("\n");
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
	
	private static int getIntersectionNum(List<Integer> leftWindow, List<Integer> rightWindow){
		int intersectionNum = 0;
		
		Map<Integer, Integer> wireMap = new HashMap<Integer, Integer>();
		
		for (int i = 0; i < leftWindow.size(); i++){
			wireMap.put(leftWindow.get(i), rightWindow.get(i));
		}
		
		for (Integer key : wireMap.keySet()){
			for (int i = 0; i < leftWindow.size(); i++){
				if (key == leftWindow.get(i)){
					continue;
				}
				
				if (key > leftWindow.get(i) && wireMap.get(key) < rightWindow.get(i)){
					intersectionNum++;
				}
				
				if (key < leftWindow.get(i) && wireMap.get(key) > rightWindow.get(i)){
					intersectionNum++;
				}
			}
		}
		
		return intersectionNum/2;
	}
}
