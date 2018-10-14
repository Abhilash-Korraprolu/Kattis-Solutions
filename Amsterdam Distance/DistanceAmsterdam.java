import java.util.Scanner;

public class DistanceAmsterdam {

	private static double segmentsTotal;
	private static double halfRingsTotal;
	private static double radius;

	private static double A_x;
	private static double A_y;
	private static double B_x;
	private static double B_y;

	// because the relative error of at most 10−6
	private static double angleBetweenSegments;
	private static double segmentBitLength;

	private static void takingInput() {
		Scanner scan = new Scanner(System.in);

		try {
			segmentsTotal = scan.nextDouble();
			halfRingsTotal = scan.nextDouble();
			radius = scan.nextDouble();

			A_x = scan.nextDouble();
			A_y = scan.nextDouble();
			B_x = scan.nextDouble();
			B_y = scan.nextDouble();
		} catch (Exception e) {
			System.err.println("Error in input.");
			e.printStackTrace();
		}
		scan.close();

		rangeCheck();
	}

	private static void rangeCheck()
	{
		if(segmentsTotal < 1 || segmentsTotal > 100 ||
		   halfRingsTotal < 1 || halfRingsTotal > 100 ||
		   radius < 1 || radius > 1000 ||
		   A_x < 0 || A_x > segmentsTotal || A_y < 0 || A_y > halfRingsTotal ||
		   B_x < 0 || B_x > segmentsTotal || B_y < 0 || B_y > halfRingsTotal)
		{
			System.err.println("Input out value(s) of range ");
			System.exit(-1);
		}
		else {}

		String STRradius = Double.toString(radius);
		if(STRradius.indexOf('.') != -1)
		{
			/* shortened:
			 * String sub = STRradius.substring(STRradius.indexOf('.') +1);
			 * char[] checkPrecision = sub.toCharArray();
			 * if(checkPrecision.length > 15) */

			if(STRradius.substring(STRradius.indexOf('.') +1).toCharArray().length > 15)
			{
				System.err.println("The radius of the city, must be entered with at most 15 digits after the decimal point.");
				System.exit(-1);
			}
		}
		else {}
	}

	private static double arcLength(double Y_coordinate) {
		// The Y coordinate represents the ring number from the center. If we multiply
		// that with segmentBitLength, we get the distance to the Y coordinate from the
		// center.
		//shortened: double r = (Y_coordinate * segmentBitLength);
		return (2 * Math.PI * (Y_coordinate * segmentBitLength) * (angleBetweenSegments / 360));
	}

	public static void main(String args[]) {
		takingInput();

		angleBetweenSegments = 180 / segmentsTotal;
		segmentBitLength = radius / halfRingsTotal;

		/* NOTE:
		 1. The x coordinate indicates segment number and the y coordinate indicates ring number.
		 2. |x2-x1| determines the 'up-down'/horizontal'ish distance from the center.
		 |y2-y1| determine the arc/vertical'ish distance from left to right.

		 The distance walked 'up-down' + arc distance (if the final destination is on an
		 outer ring, then we first travel the arc number A_y (since, its smaller) and then travel
		 up-down. However, if the final destination is on an inner point, then we first travel
		 the up-down distance and to go towards the center, and then travel the smaller arc to the
		 destination (B_Y). So, min(A_Y,B_Y) will help make this choice.
		*/
		double segmentandArcDist = (Math.abs(A_y - B_y) * segmentBitLength)	+ (Math.abs(A_x - B_x) * arcLength(Math.min(A_y, B_y)));
		/*
		 What if we ignored traveling the arc and just traveled 'up-down' from your initial
		 corner to the center, and then from the center, straight to the destination? Is that shorter?

		 So, A_y would here also represent (initial position ring number - center i.e, ring 0) &
		 B_y: (final position ring number - center i.e, ring 0)
		 multiply this total segment distance to travel with segmentBitLength.
		 */
		double onlySegmentDist = (A_y + B_y) * segmentBitLength;

		System.out.println(Math.min(segmentandArcDist, onlySegmentDist));

	}

}
