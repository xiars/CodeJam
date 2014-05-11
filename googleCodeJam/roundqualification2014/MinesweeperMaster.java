package roundqualification2014;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import path.SourcePath;

/**
 * https://code.google.com/codejam/contest/2974486/dashboard#s=p2
 * Problem C. Minesweeper Master
Confused? Read the quick-start guide.
Small input
11 points	
Solve C-small
You may try multiple times, with penalties for wrong submissions.
Large input
24 points	
You must solve the small input first.
You have 8 minutes to solve 1 input file. (Judged after contest.)
Problem

Minesweeper is a computer game that became popular in the 1980s, and is still included in some versions of the Microsoft Windows operating system. This problem has a similar idea, but it does not assume you have played Minesweeper.

In this problem, you are playing a game on a grid of identical cells. The content of each cell is initially hidden. There are M mines hidden in M different cells of the grid. No other cells contain mines. You may click on any cell to reveal it. If the revealed cell contains a mine, then the game is over, and you lose. Otherwise, the revealed cell will contain a digit between 0 and 8, inclusive, which corresponds to the number of neighboring cells that contain mines. Two cells are neighbors if they share a corner or an edge. Additionally, if the revealed cell contains a 0, then all of the neighbors of the revealed cell are automatically revealed as well, recursively. When all the cells that don't contain mines have been revealed, the game ends, and you win.

For example, an initial configuration of the board may look like this ('*' denotes a mine, and 'c' is the first clicked cell):

*..*...**.
....*.....
..c..*....
........*.
..........
There are no mines adjacent to the clicked cell, so when it is revealed, it becomes a 0, and its 8 adjacent cells are revealed as well. This process continues, resulting in the following board:
*..*...**.
1112*.....
00012*....
00001111*.
00000001..
At this point, there are still un-revealed cells that do not contain mines (denoted by '.' characters), so the player has to click again in order to continue the game.
You want to win the game as quickly as possible. There is nothing quicker than winning in one click. Given the size of the board (R x C) and the number of hidden mines M, is it possible (however unlikely) to win in one click? You may choose where you click. If it is possible, then print any valid mine configuration and the coordinates of your click, following the specifications in the Output section. Otherwise, print "Impossible".

Input

The first line of the input gives the number of test cases, T. T lines follow. Each line contains three space-separated integers: R, C, and M.

Output

For each test case, output a line containing "Case #x:", where x is the test case number (starting from 1). On the following R lines, output the board configuration with C characters per line, using '.' to represent an empty cell, '*' to represent a cell that contains a mine, and 'c' to represent the clicked cell.

If there is no possible configuration, then instead of the grid, output a line with "Impossible" instead. If there are multiple possible configurations, output any one of them.

Limits

0 ≤ M < R * C.
Small dataset

1 ≤ T ≤ 230.
1 ≤ R, C ≤ 5.
Large dataset

1 ≤ T ≤ 140.
1 ≤ R, C ≤ 50.
Sample


Input 
 	
Output 
 
5
5 5 23
3 1 1
2 2 1
4 7 3
10 10 82

Case #1:
Impossible
Case #2:
c
.
*
Case #3:
Impossible
Case #4:
......*
.c....*
.......
..*....
Case #5:
**********
**********
**********
****....**
***.....**
***.c...**
***....***
**********
**********
**********

 * @author williamxia
 *
 */
public class MinesweeperMaster {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		try {
			FileReader fr = new FileReader(SourcePath.getInputPath() + "/googleCodeJam/roundqualification2014/C-large-practice.in");
			FileWriter fw = new FileWriter(SourcePath.getOutputPath() + "/googleCodeJam/roundqualification2014/C-large-practice.out");

			BufferedReader br = new BufferedReader(fr);
			BufferedWriter bw = new BufferedWriter(fw);
			int numOfCases = Integer.valueOf(br.readLine());

			StringBuffer outPut = new StringBuffer();

			String result;

			String[] caseKeys;
			int rows;
			int columns;
			int mines;

			for (int i = 0; i < numOfCases; i++){
				caseKeys = br.readLine().split(" ");
				rows = Integer.parseInt(caseKeys[0]);
				columns = Integer.parseInt(caseKeys[1]);
				mines = Integer.parseInt(caseKeys[2]);

				result = getConfiguration(rows, columns, mines);

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

	private static String getConfiguration(int rows, int columns, int mines){
		
		/*
		 * .*
		 * ****
		 * ****
		 */
		int rows1 = rows;
		int columns1 = columns;
		int mines1 = mines;
		/*
		 * .***
		 * ****
		 */
		if (mines > columns){
			mines1 = mines % columns + columns;
			rows1 = rows - (mines/columns) + 1;
		}
		
		if (mines1 > mines){
			mines1 = mines;
			rows1 = rows;
			columns1 = columns;
		}
		
		if (mines1 > rows1){
			columns1 = columns - (mines1/rows1) + 1;
			mines1 = mines1 % rows1 + rows1;
		}
		
		if (mines1 > mines){
			mines1 = mines;
			rows1 = rows;
			columns1 = columns;
		}
		
//		if (mines > columns){
//			mines1 = mines % columns;
//			columns1 = columns - (mines/columns);
//		}

		String result = "\nImpossible";
		StringBuilder configBuilder = null;
		String config;

		int cells = rows1 * columns1;
		int mapSize = (int) Math.pow(2, cells);

		for (int i = 0; i < mapSize; i++){
			if (countMines(i) != mines1){
				continue;
			}

			configBuilder = new StringBuilder(cells);
			config = Integer.toBinaryString(i);

			if (config.length() != cells){
				for (int j = 0; j < cells - config.length(); j++){
					configBuilder.append(".");
				}
			}

			configBuilder.append(config);
			config = configBuilder.toString().replace("0", ".").replace("1", "*");

			String[] configIndex = new String[1];
			configIndex[0] = config;
			if (isConfigWin(configIndex, rows1, columns1)){
				result = getWinningConfig(configIndex, rows, columns, rows1, columns1);
				break;
			}
		}

		return result;
	}

	private static int countMines(int x) {  
        int c = 0;  
        for (; x > 0; c++) {  
            x &= (x - 1); // 清除最右边的 1  
        }  
        return c;  
    }

	private static boolean isConfigWin(String[] configIndex, int rows, int columns){
		String config = configIndex[0];
		boolean isConfigWin = false;
		char[] configChar = config.toCharArray();

		String[][] configArray = new String[rows][columns];

		for (int i = 0; i < rows; i++){
			for (int j = 0; j < columns; j++){
				configArray[i][j] = String.valueOf(configChar[i * columns + j]);
			}
		}


		String[][] clickConfig;
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < columns; j++){
				if (configArray[i][j].equals("*")){
					continue;
				}

				clickConfig = reveal(configArray, rows, columns, i, j);

				if (checkConfig(clickConfig, rows, columns)){
					isConfigWin = true;
					configArray[i][j] = "c";
					config = arrayToString(configArray, rows, columns);
					configIndex[0] = config;
					return isConfigWin;
				}
			}
		}

		return isConfigWin;
	}

	private static String[][] reveal(String[][] configArray, int rows, int columns, int i, int j){
		String[][] clickConfig = new String[rows][columns];
		for (int m = 0; m < rows; m++){
			for (int n = 0; n < columns; n++){
				clickConfig[m][n] = configArray[m][n];
			}
		}

		int cellNum = getRoundMines(clickConfig, rows, columns, i, j);
		clickConfig[i][j] = String.valueOf(cellNum);
		if (cellNum == 0){
			if (i > 0 && clickConfig[i - 1][j].equals(".")){
				clickConfig = reveal(clickConfig, rows, columns, i - 1, j);
			}
			if (i > 0 && j > 0 && clickConfig[i - 1][j - 1].equals(".")){
				clickConfig = reveal(clickConfig, rows, columns, i - 1, j - 1);
			}
			if (j > 0 && clickConfig[i][j - 1].equals(".")){
				clickConfig = reveal(clickConfig, rows, columns, i, j - 1);
			}
			if (j > 0 && i < rows - 1 && clickConfig[i + 1][j - 1].equals(".")){
				clickConfig = reveal(clickConfig, rows, columns, i + 1, j - 1);
			}
			if (i < rows - 1 && clickConfig[i + 1][j].equals(".")){
				clickConfig = reveal(clickConfig, rows, columns, i + 1, j);
			}
			if (i < rows - 1 && j < columns - 1 && clickConfig[i + 1][j + 1].equals(".")){
				clickConfig = reveal(clickConfig, rows, columns, i + 1, j + 1);
			}
			if (j < columns - 1 && clickConfig[i][j + 1].equals(".")){
				clickConfig = reveal(clickConfig, rows, columns, i, j + 1);
			}
			if (j < columns - 1 && i > 0 && clickConfig[i - 1][j + 1].equals(".")){
				clickConfig = reveal(clickConfig, rows, columns, i - 1, j + 1);
			}
		}

		return clickConfig;
	}

	private static boolean checkConfig(String[][] clickConfig, int rows, int columns){
		boolean check = true;

		for (int i = 0; i < rows; i++){
			for (int j = 0; j < columns; j++){
				if (clickConfig[i][j].equals(".")){
					check = false;
					return check;
				}
			}
		}

		return check;
	}

	private static int getRoundMines(String[][] configArray, int rows, int columns, int i, int j){
		int roundMines = 0;
		if (i > 0 && configArray[i - 1][j].equals("*")){
			roundMines++;
		}
		if (i > 0 && j > 0 && configArray[i - 1][j - 1].equals("*")){
			roundMines++;
		}
		if (j > 0 && configArray[i][j - 1].equals("*")){
			roundMines++;
		}
		if (j > 0 && i < rows - 1 && configArray[i + 1][j - 1].equals("*")){
			roundMines++;
		}
		if (i < rows - 1 && configArray[i + 1][j].equals("*")){
			roundMines++;
		}
		if (i < rows - 1 && j < columns - 1 && configArray[i + 1][j + 1].equals("*")){
			roundMines++;
		}
		if (j < columns - 1 && configArray[i][j + 1].equals("*")){
			roundMines++;
		}
		if (j < columns - 1 && i > 0 && configArray[i - 1][j + 1].equals("*")){
			roundMines++;
		}
		return roundMines;
	}

	private static String getWinningConfig(String[] configIndex, int rows, int columns, int rows1, int columns1){
		StringBuilder winningBuilder = new StringBuilder();
		String config = configIndex[0];
		
		if (config.length() == rows * columns){
			for (int i = 0; i < rows; i++){
				winningBuilder.append("\n").append(config.substring(i * columns, i * columns + columns));
			}
		} else {
			for (int i = 0; i < rows1; i++){
				winningBuilder.append("\n").append(config.substring(i * columns1, i * columns1 + columns1));
				for (int j = 0; j < columns - columns1; j++){
					winningBuilder.append("*");
				}
			}
			for (int i = 0; i < rows - rows1; i++){
				winningBuilder.append("\n");
				for (int j = 0; j < columns; j++){
					winningBuilder.append("*");
				}
			}
		}


		return winningBuilder.toString();
	}

	private static String arrayToString(String[][] clickConfig, int rows, int columns){
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < columns; j++){
				result.append(clickConfig[i][j]);
			}
		}
		return result.toString();
	}
}