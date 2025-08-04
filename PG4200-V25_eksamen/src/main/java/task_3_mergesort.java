import java.util.ArrayList;

public class task_3_mergesort {

    public static int mergeCount = 0;

    public static void main(String[] args) {

        ArrayList<City> cities = import_data.readFile();

        //Collections.shuffle(cities);

        mergeSort(cities);

        System.out.println("Sorted Cities by latitude (only showing the first 10 cities): ");
        System.out.printf("%-30s %-15s%n", "City", "Latitude");
        System.out.println("----------------------------------------------------");

        int limit = Math.min(10, cities.size());

        for (int i = 0; i < limit; i++) {
            City city = cities.get(i);
            System.out.printf("%-30s %-15f%n", city.name(), city.latitude());
        }

        System.out.println(" ");
        //System.out.println("Dataset that has been shuffled");
        System.out.println("Number of merges needed to sort the dataset: " + mergeCount);
    }

    /**************************************************************************************
     *    SOURCE FOR MERGE SORT CODE
     *    Title   : Writing a Merge Sort in Pseudocode (Guide)
     *    Author  : PseudoEditor.com
     *    Date    : n.d
     *    URL     : https://pseudoeditor.com/guides/merge-sort
     *    Accessed: 2025‑03‑29
     **************************************************************************************/

    public static void mergeSort(ArrayList<City> numbers) {
        if (numbers.size() <= 1) {
            return;
        }

        int middleIndex = numbers.size() / 2;

        ArrayList<City> leftHalf = new ArrayList<>();
        ArrayList<City> rightHalf = new ArrayList<>();

        for(int i = 0; i < middleIndex; i++) {
            leftHalf.add(numbers.get(i));
        }

        for(int i = middleIndex; i < numbers.size(); i++) {
            rightHalf.add(numbers.get(i));
        }

        mergeSort(leftHalf);
        mergeSort(rightHalf);

        mergeSortedLists(numbers, leftHalf, rightHalf);
    }

    public static void mergeSortedLists(ArrayList<City> numbers, ArrayList<City> leftHalf, ArrayList<City> rightHalf) {
        mergeCount++;

        int leftIndex = 0;
        int rightIndex = 0;
        int currentIndex = 0;

        while (leftIndex < leftHalf.size() && rightIndex < rightHalf.size()) {
            if (leftHalf.get(leftIndex).latitude() < rightHalf.get(rightIndex).latitude()) {
                numbers.set(currentIndex, leftHalf.get(leftIndex));
                leftIndex++;
                currentIndex++;
            } else {
                numbers.set(currentIndex, rightHalf.get(rightIndex));
                rightIndex++;
                currentIndex++;
            }
        }

        while (leftIndex < leftHalf.size()) {
            numbers.set(currentIndex, leftHalf.get(leftIndex));
            leftIndex++;
            currentIndex++;
        }

        while (rightIndex < rightHalf.size()) {
            numbers.set(currentIndex, rightHalf.get(rightIndex));
            rightIndex++;
            currentIndex++;
        }
    }
}
