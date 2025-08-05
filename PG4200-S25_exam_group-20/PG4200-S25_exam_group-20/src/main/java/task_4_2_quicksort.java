import java.util.ArrayList;
import java.util.Collections;

public class task_4_2_quicksort {

    public static int comparisonCount = 0;

    public static void main(String[] args) {
        ArrayList<City> cities = import_data.readFile();

        quicksort(cities, 0, cities.size() - 1);

        System.out.println("Number of comparisons to sort the dataset, when pivot is the last element: " + comparisonCount);
    }

    /**************************************************************************************
     *    SOURCE FOR QUICKSORT CODE
     *    Title: Swift Data Structure and Algorithms
     *    Author: Azar, E & Alebicto, M. E
     *    Date: 2016
     *    Edition: 1st ed.
     *    Page: 120
     *    Publisher: Packt Publishing
     *    Note: The code has been rewritten to work with ArrayLists with City-objects
     **************************************************************************************/

    /* Quick Sort with pivot being the last element */
    public static void quicksort(ArrayList<City> array, int low, int high) {
        // Stops recursion if the list is empty or just have one element
        if (low < high) {
            // Partition the array around the pivot, returning index of the pivot element
            int pivotIndex = partition(array, low, high);
            // The method calls itself recursivly twice for the right and left side of the pivot index
            quicksort(array, low, pivotIndex - 1);
            quicksort(array, pivotIndex + 1, high);
        }
    }

    public static int partition(ArrayList<City> array, int low, int high) {
        // Chooses the last element in current segment as the pivot element
        double pivot = array.get(high).latitude();
        // leftIndex marks the boundary of elements smaller than the pivot
        int leftIndex = low - 1;
        // Iterates through the subarray to find elements smaller than the pivot
        for (int rightIndex = low; rightIndex < high; rightIndex++) {
            comparisonCount++;
            // If element is smaller than the pivot, move it to the left section
            if (array.get(rightIndex).latitude() < pivot) {
                leftIndex++;
                Collections.swap(array, leftIndex, rightIndex);
            }
        }
        // Place the pivot element in its correctly sorted position
        Collections.swap(array, leftIndex + 1, high);
        // Return the index of the pivot where it currently is placed
        return leftIndex + 1;
    }
}
