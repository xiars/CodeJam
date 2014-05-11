package round1C2014;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import path.SourcePath;

/**
 * https://code.google.com/codejam/contest/544101/dashboard#s=p1
 * @author williamxia
 *
 */
public class PartElf {
	static List<Long> pow2List = new ArrayList<Long>();
	static long max = (long) Math.pow(2, 40);
	static BigInteger b = new BigInteger(String.valueOf(max));
	
	static {
		for (int i = 0; i <= 40; i++){
			pow2List.add(i, (long) Math.pow(2, i));
		}
	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		try {
			FileReader fr = new FileReader(SourcePath.getInputPath() + "/googleCodeJam/round1C2014/A-small-attempt0.in");
			FileWriter fw = new FileWriter(SourcePath.getOutputPath() + "/googleCodeJam/round1C2014/A-small-attempt0.out");

			BufferedReader br = new BufferedReader(fr);
			BufferedWriter bw = new BufferedWriter(fw);
			
			int numOfCases = Integer.valueOf(br.readLine());
			String[] caseKeys;
			
			StringBuffer outPut = new StringBuffer();
			long element;
			long denominator;
			String result;
			
			for (int i = 0; i < numOfCases; i++){
				caseKeys = br.readLine().split("/");
				
				element = Long.valueOf(caseKeys[0]);
				denominator = Long.valueOf(caseKeys[1]);
				
				result = getMinNumber(element, denominator);
				
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
	
	private static String getMinNumber(long element, long denominator){
		
		BigInteger a = new BigInteger(String.valueOf(element));
		
		BigInteger c = new BigInteger(String.valueOf(denominator));
		BigInteger result = a.multiply(b).mod(c);
		if (result.intValue() != 0){
			return "impossible";
		} else {
			long elfRate = a.multiply(b).divide(c).longValue();
			
			for (int i = 0; i <= 40; i++){
				if (pow2List.get(i) == elfRate){
					return String.valueOf(40 - i);
				}
				
				if (pow2List.get(i) > elfRate && pow2List.get(i - 1) < elfRate){
					return String.valueOf(40 - i + 1);
				}
			}
			
			return "??";
		}
	}
	
	
	@Test
	public void test(){
		System.out.println(getMinNumber(72, 96));
	}
}
