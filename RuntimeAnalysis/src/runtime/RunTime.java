package runtime;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * @author Michael Gulchuk
 * @version 1.0
 */
public class RunTime {

    private static final int NANOS_IN_SECOND = 1000000000;
    private static final int ARRAY_SIZE = 10000;
    private static final int MAX_SIZE = 200000;
    // Change amount of tests here
    private static final int NUM_OF_TESTS = 20;


    /**
     * @param input an array
     * @return how many duplicates found
     */
    // algorithm is used when it gets called in main method to fix linter
    public int findDuplicatesA(int[] input){
        Arrays.sort(input);
        int count = 0;

        for(int i = 0; i < input.length; i++){
            for(int j = i + 1; j < input.length; j++){
                if(input[i] == input[j]) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    /**
     * @param input an array
     * @return how many duplicates found
     */
    // algorithm is used when it gets called in main method to fix linter
    public int findDuplicatesB(int[] input){
        LinkedList<Integer> list = new LinkedList<>();

        for (int value : input) {
            if (!list.contains(value)) {
                list.add(value);
            }
        }

        return input.length - list.size();
    }

    /**
     * @param input an array
     * @return how many duplicates found
     */
    // algorithm is used when it gets called in main method to fix linter
    public int findDuplicatesC(int[] input){
        TreeSet<Integer> list = new TreeSet<>();

        for(Integer array : input){
            list.add(array);
        }

        return input.length - list.size();
    }

    /**
     * @param input an array
     * @return how many duplicates found
     */
    // algorithm is used when it gets called in main method to fix linter
    public int findDuplicatesD(int[] input){
        HashSet<Integer> list = new HashSet<>();

        for(Integer array : input){
            list.add(array);
        }

        return input.length - list.size();
    }

    /**
     * @param input an array
     * @return how many duplicates found
     */
    // algorithm is used when it gets called in main method to fix linter
    public int findDuplicatesE(int[] input){
        int count = 0;

        for(int i = 0; i < input.length; i++){
            for(int j = i + 1; j < input.length; j++){
                if(input[i] == input[j] && i != j){
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    /**
     * @param size size of array
     * @param min minimum value to generate array
     * @param max maximum value to generate array
     * @return random array made
     */
    public static int[] randomArray(int size, int min, int max){
        Random random = new Random();
        int[] array = new int[size];

        for (int i = 0; i < array.length; i++) {
            array[i] = min + random.nextInt(max - min + 1);
        }

        return array;
    }

    /**
     * @param args args is not used
     */
    public static void main(String[] args){
        RunTime myArray = new RunTime();
        double totalSeconds = 0;

        for (int i = ARRAY_SIZE; i <= MAX_SIZE; i += ARRAY_SIZE){
            for (int j = 1; j <= NUM_OF_TESTS; j++){
                int[] array = randomArray(i, 1, i / 2);

                LocalTime before = LocalTime.now();

                // Change algorithm here to prevent overloading system
                myArray.findDuplicatesA(array);

                double nanosElapsed = Math.abs(ChronoUnit.NANOS.between(LocalTime.now(), before));
                double secondsElapsed = nanosElapsed / NANOS_IN_SECOND; //there are 1000000000 nanoseconds in a second

                // add all the seconds in looped test
                totalSeconds = totalSeconds + secondsElapsed;
           }

            // print out size and average time
            System.out.println(i + ", " + String.format("%.8f", totalSeconds/NUM_OF_TESTS));

            // reset the seconds
            totalSeconds = 0;
        }

    }
}


