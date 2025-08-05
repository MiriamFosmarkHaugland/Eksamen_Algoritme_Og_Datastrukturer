import java.util.ArrayList;



public class task_1_bubblesort_optimised {
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
     **************************************************************************************/
    public static int[] bubbleSort(ArrayList<City> cities) {
        // Counter for analysis: iterations and number of total elements swapped
        int iteration = 0;
        int swap = 0;
        // Outer loop - Will run max n-1 times
        for (int i = 0; i < cities.size() - 1; i++) {
            iteration++;
            // Flag for detecting a swapping of elements
            boolean swapped = false;
            // Inner loop - Compares and swaps adjacent elements if needed
            for (int j = 0; j < cities.size() - i - 1; j++) {
                // Swap the current element if the latitude is larger than the next
                if (cities.get(j).latitude() > cities.get(j + 1).latitude()) {
                    City temp = cities.get(j);
                    cities.set(j, cities.get(j + 1));
                    cities.set(j + 1, temp);
                    swap++;
                    swapped = true;
                }
            }
            // If no elements swapped this pass the list is sorted and finishes early
            if (!swapped) break;
        }
        // Returns number of iterations and swaps that can be used for analytics and graphs
        return new int[]{iteration, swap};
    }
}