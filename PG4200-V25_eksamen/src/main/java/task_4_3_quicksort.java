import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class task_4_3_quicksort {

    public static int comparisonCount = 0;

    public static void main(String[] args) {
        ArrayList<City> cities = import_data.readFile();

        quicksort(cities, 0, cities.size() - 1);

        print_data.printArray(cities, 10);

        System.out.println(" ");
        System.out.println("Number of comparisons to sort the dataset, when pivot is the last element: " + comparisonCount);
    }

    public static int partition(ArrayList<City> array, int low, int high) {
        // Choose random element as pivot element
        Random random = new Random();
        int randomIndex = random.nextInt(high - low + 1) + low;
        Collections.swap(array, randomIndex, high);

        double pivot = array.get(high).latitude();

        int leftIndex = low - 1;

        for (int rightIndex  = low; rightIndex < high; rightIndex++) {
            comparisonCount++;
            if (array.get(rightIndex).latitude() < pivot) {
                leftIndex++;
                Collections.swap(array, leftIndex, rightIndex);
            }
        }
        Collections.swap(array, leftIndex + 1, high);
        return leftIndex + 1;
    }

    public static void quicksort(ArrayList<City> array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quicksort(array, low, pivotIndex - 1);
            quicksort(array, pivotIndex + 1, high);
        }
    }
}
