// Problem: https://open.kattis.com/problems/averagespeed

import java.io.*;

public class AverageSpeed {

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        double speed = 0.0d, dist = 0.0d, secs1 = 0.0d;
        String s;

        while ((s = br.readLine()) != null) {
            String[] ip = s.split("[:\\s]");
            int h = Integer.parseInt(ip[0]);
            int m = Integer.parseInt(ip[1]);
            double secs2 = Double.parseDouble(ip[2]);

            secs2 += (h * 60 * 60) + (m * 60);
            dist += speed * ((secs2 - secs1) / 60.0d / 60.0d);
            secs1 = secs2;

            if (ip.length == 4)
                speed = Double.parseDouble(ip[3]);
            else
                bw.write(String.format(s + " %.2f km\n", dist));
        }

        br.close();
        bw.flush();
    }
}