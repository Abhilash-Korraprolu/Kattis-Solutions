import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Rig {
    public static void main(String args[]) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int noOfItems, X;
        int[] itemPrices;

        // Input: Line 1
        String[] ip1 = br.readLine().split(" ");
        noOfItems = Integer.parseInt(ip1[0]);
        X = Integer.parseInt(ip1[1]);
        // Input: Line 2
        String[] ip2 = br.readLine().split(" ");
        itemPrices = new int[noOfItems];

        for (int i = 0; i < noOfItems; i++)
            itemPrices[i] = Integer.parseInt(ip2[i]);

        Arrays.sort(itemPrices);
        for (int i = 1; i < noOfItems; i++) {
            if (itemPrices[i - 1] + itemPrices[i] > X) {
                System.out.println(i);
                System.exit(0);
            }
        }

        System.out.println(noOfItems);
    }
}