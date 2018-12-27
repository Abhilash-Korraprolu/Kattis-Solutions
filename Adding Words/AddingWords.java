import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class AddingWords
{
    private static HashMap<String,Integer> hm = new HashMap<String,Integer>();

    private static String STRip;
    private static String[] input;

    ////////////////////////////////

    private static void calc()
    {
      try
      {
        //Initialising with the first number.
        int result = hm.get(input[1]);

        for(int i = 2; i < input.length; i +=2)
        {
          String op = input[i];
          {
            switch(op)
            {
              case "+":
                  // result = result + (i+1)
                  result += hm.get(input[i+1]);
                  break;

              case "-":
                  result -= hm.get(input[i+1]);
                  break;

              case "=":
                  String STRresult = null;
                  for(Map.Entry<String, Integer> entry : hm.entrySet())
                  {
                    if(entry.getValue()  == result)
                    {
                      STRresult = entry.getKey();
                      break;
                    }
                  }
                  
                  if(STRresult.equals(null))
                  {
                    throw new NullPointerException();
                  }
                  else
                  {
                    System.out.println(STRip.substring(5)+" "+STRresult);
                    break;
                  }
            }
          }
        }
      }
      catch(NullPointerException e)
      {
        System.out.println(STRip.substring(5) + " unknown");
      }
  }

  public static void main(String[] args)
  {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    try
    {
      while ((STRip = br.readLine())!=null)
      {
        if(STRip.isEmpty()) { System.exit(0); }

        input = STRip.split(" ");
        String cmd = input[0];

        switch(cmd)
        {
          case "def":
              hm.put(input[1],Integer.parseInt(input[2]));
              break;

          case "calc":
              calc();
              break;

          case "clear":
              hm.clear();
              break;
        }
      }
    }
    catch(IOException e)
    {
        e.printStackTrace();
    }
  }
}
