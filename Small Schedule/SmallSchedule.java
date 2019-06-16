import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SmallSchedule{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int sizeOfBig, numberOfBigs, numberOfMachines, numberOfOneSecs, largest;
    static int[] machine;

    private static void distributingBigOnes() {

        int remainingBigs;

        if (numberOfMachines >= numberOfBigs)
            remainingBigs = numberOfBigs;
//        when num of bigs > num of machines
        else {
            int numberOfEQUALBigsPerMachine = Math.floorDiv(numberOfBigs, numberOfMachines);
            Arrays.fill(machine, numberOfEQUALBigsPerMachine * sizeOfBig);
            remainingBigs = numberOfBigs - numberOfEQUALBigsPerMachine * numberOfMachines;
        }

        for (int i = 0; i < remainingBigs; i++)
            machine[i] += sizeOfBig;

        largest = Arrays.stream(machine).max().getAsInt();
    }

    private static void distributingSmallOnes() {

        if (numberOfOneSecs == 0) {
            System.out.println(largest);
            System.exit(0);
        } else {
            for (int i = 0; i < numberOfMachines && numberOfOneSecs != 0; i++) {

                if (machine[i] < largest) {
                    int addableSecs = largest - machine[i];

//                if there is more space than we require or exactly as we require,
//                then dump it all and end the process.
                    if (addableSecs >= numberOfOneSecs) {
                        System.out.println(largest);
                        System.exit(0);
                    }
//                But if the available space is not enough
                    else {
                        machine[i] += addableSecs;
                        numberOfOneSecs -= addableSecs;
                    }
                }
            }
        }
//        If we filled the gaps with 1 secs in all machines, all machines have equal value.
//        If we still have 1secs left to distribute, we simply divide them, add the divided to largest
//        and that's the answer.
        if (numberOfOneSecs != 0) {
            int secsPerMachine = (int) Math.ceil((double) numberOfOneSecs / (double) numberOfMachines);
            System.out.println(largest + secsPerMachine);
        }
    }

    public static void main(String args[]) throws IOException {

        String[] input = br.readLine().split(" ");

        sizeOfBig = Integer.parseInt(input[0]);
        numberOfMachines = Integer.parseInt(input[1]);
        numberOfOneSecs = Integer.parseInt(input[2]);
        numberOfBigs = Integer.parseInt(input[3]);

        machine = new int[numberOfMachines];
//First distribute big ones evenly amongst available machines
        distributingBigOnes();
        distributingSmallOnes();
    }
}
