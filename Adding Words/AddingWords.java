import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AddingWords {

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        HashMap<String, Integer> hm = new HashMap<>();
        String command;

        while ((command = br.readLine()) != null) {
            String[] cmdArray = command.split(" ");

            if (cmdArray[0].equals("def"))
                hm.put(cmdArray[1], Integer.parseInt(cmdArray[2]));

            else if (cmdArray[0].equals("clear"))
                hm.clear();

            else {
                bw.write(command.substring(5) + " ");
                int result = 0, i = 1;

                while (true) {
                    if (!hm.containsKey(cmdArray[i])) {
                        bw.write("unknown\n");
                        break;
                    }

                    if (cmdArray[i - 1].equals("calc"))
                        result += hm.get(cmdArray[i]);

                    else if (cmdArray[i - 1].equals("+"))
                        result += hm.get(cmdArray[i]);

                    else if (cmdArray[i - 1].equals("-"))
                        result -= hm.get(cmdArray[i]);

                    if (i == cmdArray.length - 2) {
                        if (hm.containsValue(result)) {
                            
                            for (Map.Entry<String, Integer> e : hm.entrySet()) {
                                if (e.getValue() == result)
                                    bw.write(e.getKey() + "\n");
                            }
                        } else
                            bw.write("unknown\n");

                        break;
                    }
                    i = i + 2;
                }
            }
        }
        br.close();
        bw.flush();
    }
}