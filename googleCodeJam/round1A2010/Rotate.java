package round1A2010;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import path.SourcePath;

/**
 * https://code.google.com/codejam/contest/544101/dashboard#s=p0
 * @author williamxia
 *
 */
public class Rotate {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		try {
			FileReader fr = new FileReader(SourcePath.getInputPath() + "/googleCodeJam/round1A2010/A-large-practice.in");
			FileWriter fw = new FileWriter(SourcePath.getOutputPath() + "/googleCodeJam/round1A2010/A-large-practice.out");
			
			BufferedReader br = new BufferedReader(fr);
			BufferedWriter bw = new BufferedWriter(fw);
			int numOfCases = Integer.valueOf(br.readLine());
			
			StringBuffer outPut = new StringBuffer();
			String[] caseKeys;
			int tableLength;
			int rowLength;
			String[][] table;
			String line;
			int emptySlotsNum;
			String result;
			
			for (int i = 0; i < numOfCases; i++){
				caseKeys = br.readLine().split(" ");
				
				tableLength = Integer.valueOf(caseKeys[0]);
				rowLength = Integer.valueOf(caseKeys[1]);
				
				table = new String[tableLength][tableLength];
				
				for (int length = 0; length < tableLength; length++){
					line = br.readLine();
					line = line.replace(".", "");
					
					//rotate
					emptySlotsNum = tableLength - line.length();
					for (int width = 0; width < tableLength; width++){
						table[width][length] = width < emptySlotsNum ? "." : line.substring(width - emptySlotsNum, width - emptySlotsNum + 1);
					}
					//rotate end
					
				}
				
				result = getRotateResult(table, rowLength);
				
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
	
	private static String getRotateResult(String[][] table, int rowLength){
		String result = "";
		String[] line;
		String point;
		
		for (int length = 0; length < table.length; length++){
			if (result.equals("Both")){
				break;
			}
			
			line = table[length];
			
			for (int width = 0; width < line.length; width++){
				if (result.contains("R") && result.contains("B")){
					result = "Both";
					break;
				}
				
				point = line[width];
				
				if (point.equals(".")){
					continue;
				}
				
				//check left
				if (width >= rowLength - 1){
					for (int i = 1; i < rowLength; i++){
						if (!table[length][width - i].equals(point)){
							break;
						}
						if (i == rowLength - 1){
							result += point;
						}
					}
				}
				
				//check right
				if (width <= table.length - rowLength){
					for (int i = 1; i < rowLength; i++){
						if (!table[length][width + i].equals(point)){
							break;
						}
						if (i == rowLength - 1){
							result += point;
						}
					}
				}
				
				//check above
				if (length >= rowLength - 1){
					for (int i = 1; i < rowLength; i++){
						if (!table[length - i][width].equals(point)){
							break;
						}
						if (i == rowLength - 1){
							result += point;
						}
					}
				}
				
				//check below
				if (length <= table.length - rowLength){
					for (int i = 1; i < rowLength; i++){
						if (!table[length + i][width].equals(point)){
							break;
						}
						if (i == rowLength - 1){
							result += point;
						}
					}
				}
				
				//check left above
				if (width >= rowLength - 1 && length >= rowLength - 1) {
					for (int i = 1; i < rowLength; i++){
						if (!table[length - i][width - i].equals(point)){
							break;
						}
						if (i == rowLength - 1){
							result += point;
						}
					}
				}
				
				//check right above
				if (width <= table.length - rowLength && length >= rowLength - 1) {
					for (int i = 1; i < rowLength; i++){
						if (!table[length - i][width + i].equals(point)){
							break;
						}
						if (i == rowLength - 1){
							result += point;
						}
					}
				}
				
				//check left below
				if (width >= rowLength - 1 && length <= table.length - rowLength) {
					for (int i = 1; i < rowLength; i++){
						if (!table[length + i][width - i].equals(point)){
							break;
						}
						if (i == rowLength - 1){
							result += point;
						}
					}
				}
				
				//check right below
				if (width <= table.length - rowLength && length <= table.length - rowLength) {
					for (int i = 1; i < rowLength; i++){
						if (!table[length + i][width + i].equals(point)){
							break;
						}
						if (i == rowLength - 1){
							result += point;
						}
					}
				}
			}
		}
		
		if (result.equals("")) {
			result = "Neither";
		}
		if (result.contains("R") && !result.contains("B")) {
			result = "Red";
		}
		if (!result.contains("R") && result.contains("B") && !result.equals("Both")) {
			result = "Blue";
		}
		return result;
	}
}
