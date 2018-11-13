import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Tries
{
    private class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfTheWord;
        public TrieNode() {
            children = new HashMap<>();
            endOfTheWord = false;
        }
    }

    private final TrieNode root;
    public Tries() {
        root = new TrieNode();
    }

    public void insert(String word) {

        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null)
            {
                node = new TrieNode();
                current.children.put(ch, node);
            }
            //if we retrieve a chained node, or if we dont (in which case we create a fresh one), either case, we are setting current to this new one--an increment like.
            current = node;
        }
        //mark current node's 'endOfTheWord' as true
        current.endOfTheWord = true;
    }

    public boolean search(String word) {
        //returns true => is prefix of another number. returns false => not a prefix of another number.
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++)
        {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            //if node does not exist for given char then return false
            if (node == null) {
                return false;
            }

            if(i == word.length() -1 )
            {
                TrieNode last = node;
                if(last.children.isEmpty()) return false;
            }

            current = node;

        }
        //returns true if current's endOfTheWord is true, else returns false.

        return true;
    }


    public static void main(String args[]) throws NumberFormatException, IOException
    {
        int testCases_total;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //Input 1: taking in number of test cases
        testCases_total = Integer.parseInt(br.readLine());

        for(int b = 0; b < testCases_total; b++)
        {
            Tries t = new Tries();

            //Input 2: number of phnos in each test case
            int phnosPerTC = Integer.parseInt(br.readLine());

            //Input 3: Creating array to store all the strings. And also inserting them.
            String[] phno = new String[phnosPerTC];

            for(int s = 0; s < phnosPerTC; s++)
            {
                phno[s] = br.readLine();
                t.insert(phno[s]);
            }

            //Checking the suffixes
            boolean isConsistent = true;
            for(int s = 0; s < phnosPerTC; s++)
            {
                if(t.search(phno[s]))
                {
                    isConsistent = false;
                    break;
                }
                else {}

            }

            System.out.println((isConsistent) ? "YES" : "NO");
        }
    }
}
