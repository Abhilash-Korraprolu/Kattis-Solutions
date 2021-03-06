// Problem: https://open.kattis.com/problems/anagramcounting

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AnagramCounting
{
    private static String STRinput;

    private static BigInteger factorialMachine(int n)
    {
        BigInteger bigProduct = new BigInteger("1");

        for(long i =1; i <=n; i++)
        {
            bigProduct = bigProduct.multiply(BigInteger.valueOf(i));
        }

        return bigProduct;
    }

    private static BigInteger denominatorProduct()
    {
        // Put each char and its repetition values into a hashmap.
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();

        for (int i = 0; i < STRinput.length(); i++)
        {
            char c = STRinput.charAt(i);

            if (hm.containsKey(c))
            {
                hm.put(c, hm.get(c) + 1);
            }
            else
            {
                hm.put(c, 1);
            }
        }

        //Once the char repetitions are calculated, store the repetition values.
        ArrayList<Integer> repetitions = new ArrayList<Integer>(hm.values());

        BigInteger multOfReps = new BigInteger("1");

        //Find the factorial of each value and multiply it all!
        for (int i = 0; i < repetitions.size(); i++)
        {
            if (repetitions.get(i) != 1)
            {
                multOfReps = multOfReps.multiply(factorialMachine(repetitions.get(i)));
            }
        }

        return multOfReps;
    }
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);

        while((in.hasNext()))
        {
                STRinput= in.next();
                System.out.println(factorialMachine(STRinput.length()).divide(denominatorProduct()));
        }
   }
}
