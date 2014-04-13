package roundqualification2014;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import path.SourcePath;

/**
 * https://code.google.com/codejam/contest/2974486/dashboard
 * Problem A. Magic Trick
 * Confused? Read the quick-start guide.
Small input
6 points	
Solve A-small
You may try multiple times, with penalties for wrong submissions.
Note: To advance to the next rounds, you will need to score 25 points. Solving just this problem will not give you enough points.

Problem

Recently you went to a magic show. You were very impressed by one of the tricks, so you decided to try to figure out the secret behind it!

The magician starts by arranging 16 cards in a square grid: 4 rows of cards, with 4 cards in each row. Each card has a different number from 1 to 16 written on the side that is showing. Next, the magician asks a volunteer to choose a card, and to tell him which row that card is in.

Finally, the magician arranges the 16 cards in a square grid again, possibly in a different order. Once again, he asks the volunteer which row her card is in. With only the answers to these two questions, the magician then correctly determines which card the volunteer chose. Amazing, right?

You decide to write a program to help you understand the magician's technique. The program will be given the two arrangements of the cards, and the volunteer's answers to the two questions: the row number of the selected card in the first arrangement, and the row number of the selected card in the second arrangement. The rows are numbered 1 to 4 from top to bottom.

Your program should determine which card the volunteer chose; or if there is more than one card the volunteer might have chosen (the magician did a bad job); or if there's no card consistent with the volunteer's answers (the volunteer cheated).

Solving this problem

Usually, Google Code Jam problems have 1 Small input and 1 Large input. This problem has only 1 Small input. Once you have solved the Small input, you have finished solving this problem.

Input

The first line of the input gives the number of test cases, T. T test cases follow. Each test case starts with a line containing an integer: the answer to the first question. The next 4 lines represent the first arrangement of the cards: each contains 4 integers, separated by a single space. The next line contains the answer to the second question, and the following four lines contain the second arrangement in the same format.

Output

For each test case, output one line containing "Case #x: y", where x is the test case number (starting from 1).

If there is a single card the volunteer could have chosen, y should be the number on the card. If there are multiple cards the volunteer could have chosen, y should be "Bad magician!", without the quotes. If there are no cards consistent with the volunteer's answers, y should be "Volunteer cheated!", without the quotes. The text needs to be exactly right, so consider copying/pasting it from here.

Limits

1 ≤ T ≤ 100.
1 ≤ both answers ≤ 4.
Each number from 1 to 16 will appear exactly once in each arrangement.

Sample


Input 
 	
Output 
 
3
2
1 2 3 4
5 6 7 8
9 10 11 12
13 14 15 16
3
1 2 5 4
3 11 6 15
9 10 7 12
13 14 8 16
2
1 2 3 4
5 6 7 8
9 10 11 12
13 14 15 16
2
1 2 3 4
5 6 7 8
9 10 11 12
13 14 15 16
2
1 2 3 4
5 6 7 8
9 10 11 12
13 14 15 16
3
1 2 3 4
5 6 7 8
9 10 11 12
13 14 15 16

Case #1: 7
Case #2: Bad magician!
Case #3: Volunteer cheated!


 * @author williamxia
 *
 */
public class MagicTrick {

	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		try {
			FileReader fr = new FileReader(SourcePath.getInputPath() + "/googleCodeJam/roundqualification2014/A-small-attempt0.in");
			FileWriter fw = new FileWriter(SourcePath.getOutputPath() + "/googleCodeJam/roundqualification2014/A-small-attempt0.out");

			BufferedReader br = new BufferedReader(fr);
			BufferedWriter bw = new BufferedWriter(fw);
			int numOfCases = Integer.valueOf(br.readLine());
			
			StringBuffer outPut = new StringBuffer();
			int firstChoice;
			String[][] firstSquare;
			int secondChoice;
			String[][] secondSquare;
			String result;
			List<Integer> choiceNums;
			
			String badMagician = "Bad magician!";
			String volunteerCheated = "Volunteer cheated!";
			
			for (int i = 0; i < numOfCases; i++){
				firstChoice = Integer.valueOf(br.readLine());
				
				firstSquare = new String[4][4];
				for (int j = 0; j < 4; j++){
					firstSquare[j] = br.readLine().split(" ");
				}
				
				secondChoice = Integer.valueOf(br.readLine());
				secondSquare = new String[4][4];
				for (int k = 0; k < 4; k++){
					secondSquare[k] = br.readLine().split(" ");
				}
				
				choiceNums = getChoiceNums(firstSquare[firstChoice - 1], secondSquare[secondChoice - 1]);
				if (choiceNums.size() == 1){
					result = String.valueOf(choiceNums.get(0));
				} else if (choiceNums.size() > 1){
					result = badMagician;
				} else {
					result = volunteerCheated;
				}
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
	
	private static List<Integer> getChoiceNums(String[] firstRow, String[] secondRow){
		List<Integer> choiceNums = new ArrayList<Integer>();
		
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 4; j++){
				if (firstRow[i].equals(secondRow[j])){
					choiceNums.add(Integer.parseInt(firstRow[i]));
					break;
				}
			}
		}
		
		return choiceNums;
	}
}
