import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class task_2_insertionsort {
    public static void main(String[] args) {
        ArrayList<City> cities = import_data.readFile();
        Timer timer = new Timer();

        timer.start();

        insertionSort(cities);

        if(!cities.getFirst().name().equals("Puerto Williams")){
            System.out.println("Error: Wrong output after sorting!");
            System.out.println(cities.getFirst().name());
        }

        timer.stop();

        System.out.println("\nTime it took to sort the array: " + timer.durationMicros() + " µs (micro seconds).");
    }

    /**************************************************************************************
     *    SOURCE FOR INSERTION SORT CODE
     *    Title: Data structures and algorithms in Java
     *    Author: Goodrich, M. T., Tamassia, R., & Goldwasser, M. H.
     *    Date: 2014
     *    Edition: 6th ed.
     *    Page: 111
     *    Publisher: John Wiley & Sons
     *    Note: The code has been rewritten to work with ArrayLists with City-objects
     **************************************************************************************/

    /** Insertion sort of an ArrayList of elements in ascending order based on latitude */
    public static void insertionSort(ArrayList<City> cities) {
        int i, j;

        // Starting on index 1 as index 0 is already sorted
        for (i = 1; i < cities.size(); i++) {
            // Keeps a copy of the current city element
            City current_city = cities.get(i);
            j = i;

            // Moves all elements that has a value higher than current city one index up
            while ((j > 0) && (cities.get(j-1).latitude() > current_city.latitude())) {
                cities.set(j, cities.get(j - 1));
                j--;
            }

            // Insert the current city element in the correct place in the sorted sub-list
            cities.set(j, current_city);
        }
    }

    /**************************************************************************************
     *   SOURCE FOR INSERTION SORT (binary version)
     *   Title   : Binary Insertion Sort
     *   Author  : Milos Simic
     *   Date    : March 18, 2024
     *   URL     : https://www.baeldung.com/cs/binary-insertion-sort
     *   Accessed: 2025‑04‑21
     *   Note    : The psuedo-code has been rewritten to work with ArrayLists with City-objects
     **************************************************************************************/

    public static void binaryInsertionSort(ArrayList<City> cities) {
        // Starting on index 1 as index 0 is already sorted
        for (int i = 1; i < cities.size(); i++) {
            // Creates a copy of the current city element
            City current_city = cities.get(i);
            // Create a key based on the value we are sorting that we use for the binary search
            double key = current_city.latitude();
            int low = 0;
            int high = i;

            // Execute the binary search to find the right index for inserting the current element
            while (low < high) {
                // Use an unsigned right bit shift to safely get the middle index between low and high
                // Avoids potential integer overflow from just using (low + high) / 2
                int mid = (low + high) >>> 1;
                // If current latitude is larger than key, set mid value to be new high
                if (key < cities.get(mid).latitude()) high = mid;
                // If current latitude is smaller than key, set mid + 1 value to be new low
                else low = mid + 1;
            }
            int pos = low;
            // If pos/low is equal to i, we are already in the right place, so we continue to next iteration
            if (pos == i) continue;

            // If pos is lower than i, moves all elements with a value higher than current city one index up
            for (int j = i; j > pos; j--) {
                cities.set(j, cities.get(j - 1));
            }
            // Insert the current city element in the correct place in the sorted sub-list
            cities.set(pos, current_city);
        }
    }
}
