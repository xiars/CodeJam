package round1B2010;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import path.SourcePath;

/**
 * https://code.google.com/codejam/contest/635101/dashboard#s=p1
 * @author williamxia
 *
 */
public class PickingUpChicks {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		try {
			FileReader fr = new FileReader(SourcePath.getInputPath() + "/googleCodeJam/round1B2010/B-large-practice.in");
			FileWriter fw = new FileWriter(SourcePath.getOutputPath() + "/googleCodeJam/round1B2010/B-large-practice.out");
			
			BufferedReader br = new BufferedReader(fr);
			BufferedWriter bw = new BufferedWriter(fw);
			int numOfCases = Integer.valueOf(br.readLine());
			
			String[] caseKeys;
			int numOfChicks;
			int needNum;
			int distance;
			int leftTime;
			String[] locations;
			String[] speeds;
			int result;
			int arrivedNum;
//			List<Integer> swaps;
			int swap;
			StringBuffer outPut = new StringBuffer();
			
			for (int i = 0; i < numOfCases; i++){
				caseKeys = br.readLine().split(" ");
				numOfChicks = Integer.valueOf(caseKeys[0]);
				needNum = Integer.valueOf(caseKeys[1]);
				distance = Integer.valueOf(caseKeys[2]);
				leftTime = Integer.valueOf(caseKeys[3]);
				locations = br.readLine().split(" ");
				speeds = br.readLine().split(" ");
				arrivedNum = 0;
//				swaps = new ArrayList<Integer>();
				result = 0;
				swap = 0;
				
				for (int j = numOfChicks - 1; j >= 0; j--){
					if (arrivedNum >= needNum){
						break;
					}
					//check whether the chick can reach the parn
					if ((Integer.valueOf(speeds[j]) * leftTime) + Integer.valueOf(locations[j]) >= distance){
						arrivedNum++;
//						for (int k = j; k < numOfChicks; k++){
//							//get swap times that the chick "j" overtake chicks before it
//							if ((((distance - Integer.valueOf(locations[j])) * Integer.valueOf(speeds[k])) < ((distance - Integer.valueOf(locations[k])) * Integer.valueOf(speeds[j])))){
//								swap++;
//							}
//						}
//						swaps.add(swap);
						result += swap;
					} else {
						swap ++;
					}
				}
				
				if (arrivedNum < needNum){
					outPut.append("Case #").append(i+1).append(": ").append("IMPOSSIBLE").append("\n");
				} else {
//					Collections.sort(swaps);
//					for (int j = 0; j < needNum; j++){
//						result += swaps.get(j);
//					}
					outPut.append("Case #").append(i+1).append(": ").append(result).append("\n");
				}
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
