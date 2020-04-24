// Problem: https://open.kattis.com/problems/anothercandies

// Challenge: Processing very large data

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AnotherCandies {

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Byte TC = Byte.parseByte(br.readLine());

        while (TC-- > 0) {
            br.readLine();
            long r = 0;
            short noOfChildren = Short.parseShort(br.readLine());

            for (short i = 0; i < noOfChildren; i++) {
                long noOfcandies = Long.parseLong(br.readLine());
                r += noOfcandies % noOfChildren;
            }

            System.out.println(r % noOfChildren == 0 ? "YES" : "NO");
        }
    }
}