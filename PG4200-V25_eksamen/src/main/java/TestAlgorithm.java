import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;

public class TestAlgorithm {
    public static void main(String[] args) {
        ArrayList<City> dataset = import_data.readFile();
        String algorithmName = "Insertion Sort";

        // Runs varm-up of the JVM for JIT optimization
        runJVMWarmUp(dataset, 10, false, algorithmName, task_2_insertionsort::insertionSort);

        // runJVMWarmUpQuickSort(dataset, 100, false, task_4_1_quicksort::quicksort);

        // Runs the algorithm with and without shuffelling of the data set
        testAlgorithm(dataset, false, 100, algorithmName, task_2_insertionsort::insertionSort);
        testAlgorithm(dataset, true, 100, algorithmName, task_2_insertionsort::insertionSort);

        // testQuickSort(dataset, false, 100, task_4_1_quicksort::quicksort);
        // testQuickSort(dataset, true, 100, task_4_1_quicksort::quicksort);


    }

    /**
     * Test av algoritme som tar inn kun 1 paramter som er et ArrayList<City>.
     * @param dataset Data som skal sorteres - ArrayList<City>
     * @param shuffle Bool om datasettet skal mikses før hver test
     * @param repetitions Antall ganger testen skal kjøres
     * @param name Navn på algoritmen som testes
     * @param algorithm Algoritmen som skal testes, gitt som lambda
     */
    private static void testAlgorithm(ArrayList<City> dataset, boolean shuffle,
                                      int repetitions, String name, Consumer<ArrayList<City>> algorithm) {
        System.out.printf("\n*** Starting test of %s ***\n", name);
        Timer timer = new Timer();
        long totalTime = 0;

        for(int i = 0; i < repetitions; i++){
            ArrayList<City> copiedData = new ArrayList<>(dataset);
            if(shuffle){
                Collections.shuffle(copiedData);
            }
            timer.start();

            algorithm.accept(copiedData);

            timer.stop();
            totalTime += timer.durationMicros();
        }
        if(shuffle){
            System.out.printf("Total runtime for %d tests of %s when data is shuffled: %.2f seconds (%d µs).\n", repetitions, name, totalTime / 1_000_000.0, totalTime);
            System.out.printf("Average runtime for %d tests when data is shuffled: %d µs (%.2f ms / %.4f s)\n", repetitions, (totalTime / repetitions), ((totalTime / 1000.0) / repetitions), ((totalTime / 1_000_000.0) / repetitions));
        } else {
            System.out.printf("Total runtime for %d tests of %s when data is not shuffled: %.2f seconds (%d µs).\n", repetitions, name, totalTime / 1_000_000.0, totalTime);
            System.out.printf("Average runtime for %d tests when data is not shuffled: %d µs (%.2f ms / %.4f s)\n", repetitions, (totalTime / repetitions), ((totalTime / 1000.0) / repetitions), ((totalTime / 1_000_000.0) / repetitions));
        }
    }

    /**
     * Oppvarming av JVM for å skape hot spots for JIT-kompilering (Just-in-time-compilation)
     * @param dataset Data som skal sorteres - ArrayList<City>
     * @param warmUpLength Antall runder oppvarmingen skal kjøres
     * @param print Skal det printes ut når hver runde starter?
     * @param name Navn på algoritmen som testes
     * @param algorithm Algoritmen som skal testes, gitt som lambda
     */
    public static void runJVMWarmUp(ArrayList<City> dataset, int warmUpLength, boolean print,
                                    String name, Consumer<ArrayList<City>> algorithm){
        System.out.printf("*** Starting warm-up of JVM with %s ***\n", name);

        for (int i = 0; i < warmUpLength; i++){
            if(i == (double) (warmUpLength / 2)) System.out.println("*** Warm-up half way, be patient... ***");
            if(print) System.out.printf("Warm-up lap %d starting\n", i);

            ArrayList<City> copiedData = new ArrayList<>(dataset);

            algorithm.accept(copiedData);

            if(!copiedData.getFirst().name().equals("Puerto Williams")){
                System.out.printf("Error: Wrong output after warm-up lap %d!\n", i);
            }
        }
        System.out.printf("*** Finished warm-up of JVM with %s ***\n", name);
    }

    /**
     * Interface for å kunne ta imot tre parameter til en Quick Sort-metode vi henter inn
     * @param <T> Datasettet
     * @param <U> Low (sett til 0)
     * @param <V> High (sett til størrelsen av datasettet - 1)
     */
    public interface QuickSortMethod<T, U, V> {
        void accept(T t, U low, V high);
    }

    /**
     * Oppvarmingsfunksjon av JVM for Quick sort
     * @param dataset Data som skal sorteres - ArrayList<City>
     * @param warmUpLength Antall runder oppvarmingen skal kjøres
     * @param print Skal det printes ut når hver runde starter?
     * @param algorithm Algoritmen som skal testes, gitt som lambda med 3 parameter
     */
    public static void runJVMWarmUpQuickSort(ArrayList<City> dataset, int warmUpLength, boolean print,
                                             QuickSortMethod<ArrayList<City>, Integer, Integer> algorithm){
        System.out.println("*** Starting warm-up of JVM with Quick Sort ***");

        for (int i = 0; i < warmUpLength; i++){
            if(i == (double) (warmUpLength / 2)) System.out.println("*** Warm-up half way, be patient... ***");
            if(print) System.out.printf("Warm-up lap %d starting\n", i);

            ArrayList<City> copiedData = new ArrayList<>(dataset);

            algorithm.accept(copiedData, 0, copiedData.size() - 1);

            if(!copiedData.getFirst().name().equals("Puerto Williams")){
                System.out.printf("Error: Wrong output after warm-up lap %d!\n", i);
            }
        }

        System.out.println("*** Finished warm-up of JVM with Quick Sort");
    }

    /**
     * Test av Quick Sort algoritme som tar inn 3 paramter (ArrayList<City>, int, int)
     * @param dataset Data som skal sorteres - ArrayList<City>
     * @param shuffle Bool om datasettet skal mikses før hver test
     * @param repetitions Antall ganger testen skal kjøres
     * @param algorithm Algoritmen som skal testes, gitt som lambda med 3 parameter
     */
    private static void testQuickSort(ArrayList<City> dataset, boolean shuffle,
                                      int repetitions, QuickSortMethod<ArrayList<City>, Integer, Integer> algorithm) {
        System.out.println("\n*** Starting test of Quick Sort ***");
        Timer timer = new Timer();
        long totalTime = 0;

        for(int i = 0; i < repetitions; i++){
            timer.start();
            ArrayList<City> copiedData = new ArrayList<>(dataset);
            if(shuffle){
                Collections.shuffle(copiedData);
            }
            algorithm.accept(copiedData, 0, copiedData.size() - 1);
            timer.stop();
            totalTime += timer.durationMicros();
        }
        if(shuffle){
            System.out.printf("Total runtime for %d tests of Quick Sort when data is shuffled: %.2f seconds (%d µs).\n", repetitions, totalTime / 1_000_000.0, totalTime);
            System.out.printf("Average runtime for %d tests when data is shuffled: %d µs (%.2f ms / %.4f s)\n", repetitions, (totalTime / repetitions), ((totalTime / 1000.0) / repetitions), ((totalTime / 1_000_000.0) / repetitions));
        } else {
            System.out.printf("Total runtime for %d tests of Quick Sort when data is not shuffled: %.2f seconds (%d µs).\n", repetitions, totalTime / 1_000_000.0, totalTime);
            System.out.printf("Average runtime for %d tests when data is not shuffled: %d µs (%.2f ms / %.4f s)\n", repetitions, (totalTime / repetitions), ((totalTime / 1000.0) / repetitions), ((totalTime / 1_000_000.0) / repetitions));
        }
    }
}
