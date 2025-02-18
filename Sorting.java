import java.io.FileWriter;

public class Sorting {
    // static list of our gap sizes
    private static final int[] intervals = {1, 4, 13, 40, 121, 364, 1093, 3280, 9841, 29524, 88573};


    private Sorting() {
        // no instancing me
    }

    /**
     * Method to sort an SLL<Integer> using ShellSort
     * @param theInput ArrayList to be sorted
     * @param theName Name of the input file (for use in generating output.txt)
     * @return New ArrayList now sorted
     */
    public static SLL<Integer> shellSort(SLL<Integer> theInput, String theName) {
        System.out.println("Sorting " + theName + " of size " + theInput.size());
        writeRow("\n" + theName);
        writeRow("       k          pass          comp          exch");
        writeRow("---------------------------------------------------");

        // find the largest gap that will fit in our dataset
        int gap = intervals.length - 1;
        for (int i = intervals.length - 1; intervals[gap] > theInput.size(); i--) {
            gap = i;
        }
        // counter for total statistics
        int[] totals = {0, 0, 0};
        int[] statistics = {0, 0, 0};
        
        while (true) {
            if (theInput.size() < 100) System.out.println("Statistics (pass, comp, exch): " + statistics[0] + ", " + statistics[1] + ", " + statistics[2]);
            // Statistics counters (pass, comp, exch)
            statistics[0] = 0;
            statistics[1] = 0;
            statistics[2] = 0;
            
            // I'm not 100% sure this is correctly implemented ShellSort, but it does sort
            int current = 0;
            while ((current + intervals[gap]) < (theInput.size() - 1)) {
                statistics[0]++;
                statistics[1]++;

                if (theInput.get(current).compareTo((Integer) theInput.get(current + intervals[gap])) > 0) {
                    statistics[1]++;
                    statistics[2]++;
                    theInput.swap(current, current + intervals[gap]);
                }
                current++;
            }

            // Bubble sort if the gap is 1
            if (gap == 0) {
                if (theInput.size() < 100) System.out.println("Bubblin' " + theInput);
                boolean sorted = false;
                while (!sorted) {
                    statistics[0]++;
                    sorted = true;
                    for (int i = 0; i < theInput.size() - 1; i++) {
                        statistics[1]++;
                        if (theInput.get(i).compareTo((Integer) theInput.get(i + 1)) > 0) {
                            statistics[1]++;
                            statistics[2]++;
                            theInput.swap(i, i + 1); 
                            sorted = false;
                        }
                    }
                }
                
                // Writing a row to the output file, and the totals
                totals[0] += statistics[0];
                totals[1] += statistics[1];
                totals[2] += statistics[2];
                writeRow(String.format("%8s", intervals[0]) + String.format("%14s", statistics[0]) + String.format("%14s", statistics[1]) + String.format("%14s", statistics[2]));
                writeRow("---------------------------------------------------");
                writeRow(String.format("%8s", "Total:") + String.format("%14s", totals[0]) + String.format("%14s", totals[1]) + String.format("%14s", totals[2]));
                break;
            } else {
                // Writing a row to the output file and updating totals
                totals[0] += statistics[0];
                totals[1] += statistics[1];
                totals[2] += statistics[2];
                writeRow(String.format("%8s", intervals[gap]) + String.format("%14s", statistics[0]) + String.format("%14s", statistics[1]) + String.format("%14s", statistics[2]));

                gap--;
            }
        }

        return theInput;
    }

    // NOTE: Replaced by the SLL.swap() method, used because of the private Node class.
    // private static SLL<Integer> swap(SLL<Integer> theList, int indexA, int indexB) {
    //     return theList;
    // }

    /**
     * This method writes a line to the output file, each call on a new line
     * @param theText The string to be written
     */
    // Note: if you are running this program again, if you do not delete output.txt or it's contents
    // the new data will be written to the same file without overwriting, duplicating the information.
    private static void writeRow(String theText) {
        try {
            FileWriter writer = new FileWriter("output.txt", true);
            writer.append(theText + "\n");
            writer.close();
        } catch (Exception e) {
            System.out.println("shits fucked");
            e.printStackTrace();
        }
    }
}