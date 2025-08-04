import java.util.ArrayList;
import java.util.Random;

public class task_2_insertionsort {
    public static void main(String[] args) {

        ArrayList<City> cities = import_data.readFile();
        Timer timer = new Timer();

        System.out.println("Size of Cities: " + cities.size() + ".\n");

        timer.start();

        insertionSort(cities);

        timer.stop();

        System.out.println("\nTime it took to sort the array: " + timer.durationMicros() + " µs (micro seconds).");
    }

    /**************************************************************************************
     *    SOURCE FOR INSERTIONSORT CODE
     *    Title: Data structures and algorithms in Java
     *    Author: Goodrich, M. T., Tamassia, R., & Goldwasser, M. H.
     *    Date: 2014
     *    Edition: 6th ed.
     *    Page: 111
     *    Publisher: John Wiley & Sons
     **************************************************************************************/

    /** Insertion-sort of an array of characters into nondecreasing order */
    public static void insertionSort(ArrayList<City> cities) {
        int i, j;

        for (i = 1; i < cities.size(); i++) {
            City current_city = cities.get(i);
            j = i;

            while ((j > 0) && (cities.get(j-1).latitude() > current_city.latitude())) {
                cities.set(j, cities.get(j - 1));
                j--;
                }
            cities.set(j, current_city);
        }
    }

    /**
     * Returnerer et array på NumbersMax med heltall mellom 0 og 10x parameter NumbersMax
     * @param NumbersMax
     * @return Array: int[]
     */
    public static int[] largeArray(int NumbersMax){
        int[] largeArray = new int[NumbersMax];
        Random random = new Random();

        for(int i = 0; i < NumbersMax; i++){
            largeArray[i] = random.nextInt(NumbersMax * 10); // gives a number between 0 and (n x 10)
        }

        return largeArray;
    }

}
