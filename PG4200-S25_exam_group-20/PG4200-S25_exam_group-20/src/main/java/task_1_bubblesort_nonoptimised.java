import java.util.ArrayList;

public class task_1_bubblesort_nonoptimised {
    public static void main(String[] args) {

        ArrayList<City> cities = import_data.readFile();
        Timer timer = new Timer();

        timer.start();
        bubbleSort(cities);
        timer.stop();

        System.out.println("Time it took to sort the array: " + timer.durationMicros() + " µs (micro seconds).");
    }

    /**************************************************************************************
     *   SOURCE FOR BUBBLE SORT
     *   Title   : Bubble Sort Algorithm
     *   Author  : Geeks for Geeks
     *   Date    : January 21, 2025
     *   URL     : https://www.geeksforgeeks.org/bubble-sort-algorithm/
     *   Accessed: 2025‑04‑23
     *   Note    : The code has been rewritten to work with ArrayLists with City-objects
     **************************************************************************************/

    public static int[] bubbleSort(ArrayList<City> cities) {
        // Counter for analysis: iterations and number of total elements swapped
        int iteration = 0;
        int swap = 0;
        // Outer loop - Will run max n-1 times
        for (int i = 0; i < cities.size() -1 ; i++) {
            iteration++;
            // Inner loop - Compares and swaps adjacent elements if needed
            for (int j = 0; j < cities.size() - i - 1; j++) {
                // Switch the order of the two elements if the largest is on the left
                if (cities.get(j).latitude() > cities.get(j + 1).latitude()) {
                    City temp = cities.get(j);
                    cities.set(j, cities.get(j + 1));
                    cities.set(j + 1, temp);
                    swap++;
                }
            }
        }
        // Returns number of iterations and swaps that can be used for analytics and graphs
        return new int[]{iteration, swap};
    }
}