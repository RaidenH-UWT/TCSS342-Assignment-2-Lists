import java.io.FileReader;

public class Tester {
    // ArrayList object to store our data
    private final SLL<Integer> DATA;

    // ArrayList object to store our sorted data
    private final SLL<Integer> SORTEDDATA;

    // Object to store our filename
    private final String FILENAME;

    // Sorting duration
    private final float DURATION;

    /**
     * Constructor for the Tester class. 
     * 
     * @param theFileName Name of the input file to be parsed
     */
    public Tester(String theFileName) {
        super();

        FILENAME = theFileName;

        DATA = readValues();

        long startTime = System.currentTimeMillis();
        SORTEDDATA = resultShellSort();
        long endTime = System.currentTimeMillis();

        DURATION =  (float) (endTime - startTime) / 1000;
    }

    /**
     * This method reads Integer values from the FILENAME file
     * @return SLL list of all numbers in the file
     */
    private SLL<Integer> readValues() {
        SLL<Integer> readData = new SLL<>();
        try {
            FileReader fileReader = new FileReader(FILENAME);
            // Value to add to the list
            int num = 0;
            // Looping over the file
            while (true) {
                // grab the next character
                int i = fileReader.read();
                if (i == 32) {
                    // If we hit a space, add the value to the list
                    readData.append(num);
                    // Reset the value
                    num = 0;
                } else if (i > -1 && i < 38) {
                    // pass if we hit a any control characters
                } else if (i - 48 > -1) {
                    // If we have an actual value, concatenate it with our current value
                    num = Integer.parseInt(Integer.toString(num) + Character.toString(i));
                } else {
                    // If i < 0 we've reached end of file, add the last value and break

                    // Adds the remaining value if there isn't another space
                    if (num > 0) {
                        readData.append(num);
                    }
                    break;
                }
            }

            fileReader.close();
        } catch (Exception e) {
            System.out.println("File not found " + e);
        }
        return readData;
    }

    /**
     * Displays information about this Tester object in the console
     */
    public void displayResult() {
        // Console output
        System.out.println("\n" + FILENAME);
        System.out.println("Unsorted: \n" + getData().toString());
        System.out.println("\nSorted: \n" + SORTEDDATA.toString());
        System.out.println("\nDuration: " + DURATION + " seconds");
    }

    /**
     * Sorts the ArrayList of this object using the Sorting.shellSort() method
     * @return Sorted ArrayList
     */
    private SLL<Integer> resultShellSort() {
        return Sorting.shellSort(getData(), FILENAME);
    }

    /**
     * Getter method for the unsorted data
     * @return This Tester objects unsorted data
     */
    private SLL<Integer> getData() {
        return DATA;
    }

    /**
     * Main method for testing and generating output
     * @param args Unused
     */
    public static void main(String[] args) {
        // All our input files
        Tester inputOrdered100 = new Tester("./input/inorder100.txt");
        Tester inputOrdered1000 = new Tester("./input/inorder1000.txt");
        Tester inputOrdered10000 = new Tester("./input/inorder10000.txt");
        Tester inputRandom10 = new Tester("./input/random10.txt");
        Tester inputRandom100 = new Tester("./input/random100.txt");
        Tester inputRandom1000 = new Tester("./input/random1000.txt");
        Tester inputRandom10000 = new Tester("./input/random10000.txt");
        Tester inputReverse100 = new Tester("./input/reverse100.txt");
        Tester inputReverse1000 = new Tester("./input/reverse1000.txt");
        Tester inputReverse10000 = new Tester("./input/reverse10000.txt");

        // Console output for all the files
        inputOrdered100.displayResult();
        inputOrdered1000.displayResult();
        inputOrdered10000.displayResult();
        inputRandom10.displayResult();
        inputRandom100.displayResult();
        inputRandom1000.displayResult();
        inputRandom10000.displayResult();
        inputReverse100.displayResult();
        inputReverse1000.displayResult();
        inputReverse10000.displayResult();
    }
}