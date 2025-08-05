import java.util.ArrayList;

public class task_3_mergesort {

    public static int mergeCount = 0;

    public static void main(String[] args) {

        ArrayList<City> cities = import_data.readFile();

        //Collections.shuffle(cities);

        mergeSort(cities);

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
     *    Note    : The psuedo-code has been rewritten to work with ArrayLists with City-objects
     **************************************************************************************/

    public static void mergeSort(ArrayList<City> numbers) {
        // Stops recursion if the list is empty or just have one element
        if (numbers.size() <= 1) {
            return;
        }
        // Creates two new sub-arrays for right and left side of the middle index
        int middleIndex = numbers.size() / 2;
        ArrayList<City> leftHalf = new ArrayList<>();
        ArrayList<City> rightHalf = new ArrayList<>();
        // Add all elements between index 0 and middle index to leftHalf sub-array
        for(int i = 0; i < middleIndex; i++) {
            leftHalf.add(numbers.get(i));
        }
        // Add all elements between from middle index to the end to the right Half sub-array
        for(int i = middleIndex; i < numbers.size(); i++) {
            rightHalf.add(numbers.get(i));
        }
        // Recursively calls mergeSort() on both halves
        mergeSort(leftHalf);
        mergeSort(rightHalf);
        // Once both halves are sorted, merge the sorted sub-arrays back together
        mergeSortedLists(numbers, leftHalf, rightHalf);
    }

    public static void mergeSortedLists(ArrayList<City> numbers, ArrayList<City> leftHalf, ArrayList<City> rightHalf) {
        mergeCount++;
        int leftIndex = 0;
        int rightIndex = 0;
        int currentIndex = 0;

        // Compares elements from left and right side and adds the smallest element to main array
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
        // Adds any remaining elements from left side
        while (leftIndex < leftHalf.size()) {
            numbers.set(currentIndex, leftHalf.get(leftIndex));
            leftIndex++;
            currentIndex++;
        }
        // Adds any remaining elements from right side
        while (rightIndex < rightHalf.size()) {
            numbers.set(currentIndex, rightHalf.get(rightIndex));
            rightIndex++;
            currentIndex++;
        }
    }
}
