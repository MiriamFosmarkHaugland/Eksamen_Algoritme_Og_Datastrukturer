import java.util.ArrayList;
import java.util.Collections;

public class task_4_1_quicksort {

    public static int comparisonCount = 0;

    public static void main(String[] args) {
        ArrayList<City> cities = import_data.readFile();

        quicksort(cities, 0, cities.size() - 1);

        print_data.printArray(cities, 10);

        System.out.println(" ");
        System.out.println("Number of comparisons to sort the dataset, when pivot is the first element: " + comparisonCount);
    }

    public static int partition(ArrayList<City> array, int low, int high) {
        // Choose first element as pivot element
        double pivot = array.get(low).latitude();

        int leftIndex = low;

        for (int rightIndex = low + 1; rightIndex <= high; rightIndex++) {
            comparisonCount++;
            if (array.get(rightIndex).latitude() < pivot) {
                leftIndex++;
                Collections.swap(array, leftIndex, rightIndex);
            }
        }
        Collections.swap(array, low, leftIndex);

        return leftIndex;
    }

    public static void quicksort(ArrayList<City> array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quicksort(array, low, pivotIndex - 1);
            quicksort(array, pivotIndex + 1, high);
        }
    }
}
