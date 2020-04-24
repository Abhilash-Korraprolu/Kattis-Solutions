//Problem: https://open.kattis.com/problems/amsterdamdistance

import java.util.Scanner;

public class DistanceAmsterdam {

	public static void main(String args[]){

		double noOfSegments_Xs, noOfArcs_Ys, ax, ay, bx, by, radius;

		Scanner scan = new Scanner(System.in);
		noOfSegments_Xs = scan.nextDouble();
		noOfArcs_Ys = scan.nextDouble();
		radius = scan.nextDouble();
		ax = scan.nextDouble();
		ay = scan.nextDouble();
		bx = scan.nextDouble();
		by = scan.nextDouble();
		scan.close();

		double PI = 3.141592653589;
		double segmentBitLength = radius / noOfArcs_Ys;
		double angle = 180.0 / noOfSegments_Xs;
		double arcRadius = (ay < by ? ay : by) * segmentBitLength;
		double arcBitLength = (angle / 360.0) * 2.0 * PI * arcRadius;

		double d1 = (Math.abs(ax - bx) * arcBitLength) + (Math.abs(ay - by) * segmentBitLength);
		double d2 = (ay + by) * segmentBitLength;
		System.out.println(Math.min(d1, d2));
	}
}