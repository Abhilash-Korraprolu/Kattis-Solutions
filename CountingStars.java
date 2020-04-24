// https://open.kattis.com/problems/countingstars

import java.nio.file.*;
import java.io.*;

public class CountingStars {

	private static String STRnumOfRandC;
	private static char[][] image;
	private static int caseNum = 0;
	private static int totalRows = 0;
	private static int totalColumns = 0;
	private static int totalStars = 0;

	private static char starSymbol = '-';
	private static char anotherPartOfStar = 'p';

	private static BufferedReader br;
	private static String file = "sample.txt";

	private static void start() {
		try {
			readInputImageFile();
			while ((STRnumOfRandC = br.readLine()) != null && ++caseNum < 50) {
				String[] numOfRandC = STRnumOfRandC.trim().split(" ");
				totalRows = Integer.parseInt(numOfRandC[0]);
				totalColumns = Integer.parseInt(numOfRandC[1]);

				if (totalRows >= 1 && totalRows <= 100 && totalColumns >= 1 && totalColumns <= 100) {
					imageDataToArray();
					findingNumberOfStars();
					System.out.println("Case "+caseNum+": "+ totalStars);
				}
				else
				{
					System.exit(0);
				}
			}
		}

		catch (Exception e) {
			System.out.println("Issue: The rows and columns count don't match with the inputed image.");
			e.printStackTrace();
		}
	}

	private static void readInputImageFile() {
		try {
			br = Files.newBufferedReader(Paths.get(file));
		} catch (Exception e) {
			System.out.println("Issue: file not found");
			e.printStackTrace();
		}
	}

	private static void imageDataToArray() {
		image = new char[totalRows][];

		for (int i = 0; i < totalRows; i++) {
			try {
				image[i] = br.readLine().toCharArray();
			} catch (IOException e) {
				System.out.println("Issue:");
				e.printStackTrace();
			}
		}
	}

	private static void findingNumberOfStars() {

		totalStars=0;

		for(int r = 0; r<totalRows; r++)
		{
			for(int c = 0; c<totalColumns; c++)
			{
				if(image[r][c]==starSymbol)
				{
					totalStars++;
					image[r][c]= anotherPartOfStar;
					RIGHT_checkingForAnotherPart(r,c);
					DOWN_checkingForAnotherPart(r,c);
				}
			}
		}
	}
	//when image[r][c] is a start symbol, we keep moving to the right side to check
	//if they are adjacent star symbols. We then convert them to anotherstartpart
	//and while we do it, we also check top and bottom.
	private static void RIGHT_checkingForAnotherPart(int row, int column)
	{
		while(++column < totalColumns && image[row][column] == starSymbol)
		{
			image[row][column] =  anotherPartOfStar;
			UP_checkingForAnotherPart(row,column);
			DOWN_checkingForAnotherPart(row,column);
		}
	}

	private static void LEFT_checkingForAnotherPart(int row,int column)
	{
		while(--column >=0 && image[row][column]== starSymbol)
		{
			image[row][column]= anotherPartOfStar;
			UP_checkingForAnotherPart(row,column);
			DOWN_checkingForAnotherPart(row,column);
		}
	}

	private static void UP_checkingForAnotherPart(int row, int column)
	{
		while(--row >=0 && image[row][column]== starSymbol)
		{
			image[row][column]= anotherPartOfStar;
			LEFT_checkingForAnotherPart(row,column);
			RIGHT_checkingForAnotherPart(row,column);
		}
	}

	private static void DOWN_checkingForAnotherPart(int row, int column)
	{
		while(++row < totalRows && image[row][column]== starSymbol)
		{
			image[row][column]= anotherPartOfStar;
			LEFT_checkingForAnotherPart(row,column);
			RIGHT_checkingForAnotherPart(row,column);
		}
	}

	public static void main(String args[]) throws IOException
	{
		start();
	}
}
