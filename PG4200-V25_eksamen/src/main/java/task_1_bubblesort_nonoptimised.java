import java.util.ArrayList;

public class task_1_bubblesort_nonoptimised {
    public static void main(String[] args) {

        ArrayList<City> cities = import_data.readFile();
        Timer timer = new Timer();
        Runtime runtime = Runtime.getRuntime();

        //Collections.shuffle(cities);

        timer.start();
        bubbleSort(cities);
        timer.stop();

        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory used: " + usedMemory + " Bytes");
        System.out.println("Time it took to sort the array: " + timer.durationMicros() + " µs (micro seconds).");
        printArray(cities, 10);

    }

    public static void bubbleSort(ArrayList<City> cities)
    {
        int iteration = 0;
        int swap = 0;

        for (int i = 0; i < cities.size() -1 ; i++) {
            iteration++;
            for (int j = 0; j < cities.size() - i - 1; j++) {
                if (cities.get(j).latitude() > cities.get(j + 1).latitude()) {
                    City temp = cities.get(j);
                    cities.set(j, cities.get(j + 1));
                    cities.set(j + 1, temp);
                    swap++;
                }
            }
        }
        // System.out.printf("\nIt took %d swaps and %d iterations to sort the data.\n", swap, iteration);
    }

    public static void printArray(ArrayList<City>cities, int rows)  {
        System.out.println("\nSorted cities by latitude (only showing the first 10 cities):");
        System.out.printf("%-30s %-15s%n", "City", "Latitude");
        System.out.println("---------------------------------------------------------");
        for(int i = 0; i < rows && i < cities.size(); i++){
            System.out.printf("%-30s %-15.6f%n", cities.get(i).name(), cities.get(i).latitude());
        }
    }
}