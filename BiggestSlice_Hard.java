// Problem: https://www.hackerrank.com/contests/round-1-holiday-cup/challenges/biggest-slice

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BiggestSlice_Hard{
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static int testCases, n;
	private static double r, initialAngle, minutes, seconds, angle;

	private static double[] cutAngles;
	private static ArrayList<Double> al1, al2, al3;

	private static void input() throws Exception{
		String[] ARinput = br.readLine().split(" ");

		// Input 2: radius
		r = Double.parseDouble(ARinput[0]);

		// Input 3: number of people (n) sharing.
		n = Integer.parseInt(ARinput[1]);

		// Input 4: rotation angle, minutes, seconds.
		initialAngle = Double.parseDouble(ARinput[2]);
		minutes = Double.parseDouble(ARinput[3]);
		seconds = Double.parseDouble(ARinput[4]);
	}

	private static void process() {
		angle = initialAngle + (((minutes * 60) + seconds) / 3600);

		// Bypass: If the cuts end before the pizza is within 1 rotation, then we can
		// simply bypass the 'else' part.
		if (n * angle <= 360)
		{
			double sectorArea = (((Math.PI * r * r) * angle) / 360);
			double possibleN = 360 / angle;
			System.out.format("%.6f\n", ((possibleN) > n) ? (((possibleN - n) + 1) * sectorArea) : sectorArea);
		}

		// If the pizza has to go beyond 1 rotation--which means having to cut over
		// the already cut slices, then we do the following:
		else
		{
			//As we go on cutting slices, there could be a point when we're repeating
			//the same pattern once again. It would be redundant to go all over it
			//again. So, if there is such an opportunity, we shorten the n value.
			determineN();

			cutAngles = new double[n+1];

			cutAngles[0] = 0;
			cutAngles[n] = 360;

			double tempAngle =0.0d;
			for (int i = 1; i < n; i++)
			{
				//Superimposing
				tempAngle +=  angle;
				if(tempAngle >= 360.0d)
				{
					tempAngle -=  360.0d;
				}
					cutAngles[i] = tempAngle;

			}

			sorting();
		}
	}

	private static void determineN()
	{
		double x;
		for(int i =1; i<n ; i++)
		{
			x = angle * i;

			double y = x/360.0d;
			int z = (int) y;
			if(y ==z)
			{
				//System.out.println("cut number: "+i);
				//System.out.println("max rounds: "+y);
				n = ((i/(int) y) +1) * (int) y;

				break;
			}
		}
	}

	private static void sorting()
	{
		int division = 120;
		Thread t1 = new Thread(() ->
		{
			TreeSet<Double> ts = new TreeSet<Double>();

			for(double val : cutAngles)
			{
				if(division >= val && val >= 0.0d)
				{ ts.add(val); }
			}
			al1 = new ArrayList<Double>(ts);

		});

		Thread t2 = new Thread(() ->
		{
			TreeSet<Double> ts = new TreeSet<Double>();
			for(double val : cutAngles)
			{
				if(2*division >= val && val > division)
				{ ts.add(val); }
			}
			al2 = new ArrayList<Double>(ts);

		});

		Thread t3 = new Thread(() ->
		{
			TreeSet<Double> ts = new TreeSet<Double>();
			for(double val : cutAngles)
			{
				if(360.0d >= val && val > 2*division)
				{ ts.add(val); }
			}
			al3 = new ArrayList<Double>(ts);

		});

		t1.start();
		t2.start();
		t3.start();

		//Asking the main thread to wait for threads to complete their job.
		try {
			t1.join();
			t2.join();
			t3.join();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		al1.addAll(al2);
		al1.addAll(al3);


		// The largest piece would be with the largest angle. Hence, the largest angle
		// difference is determined.
		double largestSectorAngle = 0.0d;
		for (int i = 1; i < al1.size(); i++) {
			double tempAngleDifference = al1.get(i) - al1.get(i-1);

			if (tempAngleDifference > largestSectorAngle)
				largestSectorAngle = tempAngleDifference;
		}

		// Calculate the sector area for the sector with largest angle
		// sectorArea = (((Math.PI * r * r) * largestSectorAngle) / 360);
		System.out.format("%.6f\n", (((Math.PI * r * r) * largestSectorAngle) / 360.0d));
	}

	public static void main(String args[]) throws Exception
	{
		// Input 1: Number of test cases.
		testCases = Integer.parseInt(br.readLine());

		for (int i = 1; i <= testCases; i++)
		{
			input();

			process();
		}
	}
}
