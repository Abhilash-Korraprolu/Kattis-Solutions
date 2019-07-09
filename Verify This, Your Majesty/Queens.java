import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Queens {

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] coordinates = new int[n * 2];

        for (int i = 0; i < n * 2; i++) {
            String[] ip = br.readLine().split(" ");
            coordinates[i] = Integer.parseInt(ip[0]);
            coordinates[++i] = Integer.parseInt(ip[1]);

            for (int j = i - 2; j >= 0; j--) {
                int a = Math.abs(coordinates[i] - coordinates[j]);
                int b = Math.abs(coordinates[i - 1] - coordinates[--j]);

                if (a == b || a == 0 || b == 0) {
                    System.out.print("INCORRECT");
                    return;
                }
            }
        }
        
        System.out.print("CORRECT");
    }
}