package round1B2010;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import path.SourcePath;

/**
 * https://code.google.com/codejam/contest/635101/dashboard#s=p2
 * @author williamxia
 *
 */
public class YourRankisPure {
	public static void main(String[] args) {
/**		
 * {2,3,4,5,6,7}
 * {2,3,5,6,7}
 * {2,3,4,5,7}
 * {4,5,6,7}
 * {2,4,6,7}
 * {2,4,5,7}
 * {2,3,4,7}
 * {3,6,7}
 * {3,5,7}
 * {3,4,7}
 * {2,3,7}
 * {2,7}
 * {7}
*/		
		
		/**
		 * {8}
		 * {2,8}
		 * {2,3,8}
		 * {3,4,8}
		 * {3,5,8}
		 * {3,6,8}
		 * {3,7,8}
		 * {2,3,4,8}
		 * {2,4,5,8}
		 * {2,4,6,8}
		 * {2,4,7,8}
		 * {4,5,6,8}
		 * {4,5,7,8}
		 * {4,6,7,8}
		 * {2,3,4,5,8}
		 * {2,3,5,6,8}
		 * {2,3,5,7,8}
		 * {2,5,6,7,8}
		 * {2,3,4,5,6,8}
		 * {2,3,4,6,7,8}
		 * {2,3,4,5,6,7,8}
		 */
		
		int modulo = 100003;
		long start = System.currentTimeMillis();
		
		try {
			FileReader fr = new FileReader(SourcePath.getInputPath() + "/googleCodeJam/round1B2010/C-small-practice.in");
			FileWriter fw = new FileWriter(SourcePath.getOutputPath() + "/googleCodeJam/round1B2010/C-small-practice.out");
			
			BufferedReader br = new BufferedReader(fr);
			BufferedWriter bw = new BufferedWriter(fw);
			int numOfCases = Integer.valueOf(br.readLine());
			int input;
			long output = 0;
			long a;
			long b;
			long temp;
			StringBuffer outPut = new StringBuffer();
			
			for (int i = 0; i < numOfCases; i++){
				a = 1;
				b = 2;
				input = Integer.valueOf(br.readLine());
				if (input == 2){
					output = a;
				} else if (input == 3){
					output = b;
				} else {
					for (int j = 3; j < input; j++){
						output = a + b;
						temp = b;
						b = output;
						a = temp;
					}
				}
				
				outPut.append("Case #").append(i+1).append(": ").append(output % modulo).append("\n");
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
