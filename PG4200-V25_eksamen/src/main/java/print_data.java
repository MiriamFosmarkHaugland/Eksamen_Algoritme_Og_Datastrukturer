import java.util.ArrayList;

public class print_data {
    public static void main(String[] args) {
        ArrayList<City> cities = import_data.readFile();
        printArray(cities, 0);
    }

    /**
     * Funksjon for å printe ut alle eller bare de første X byene i listen
     * @param cities ArrayList med alle byer
     * @param rows Antall rader som skal printes (0 = Alle byer i ArrayList)
     */
    public static void printArray(ArrayList<City> cities, int rows){
        if(rows == 0) {
            for(City city : cities){
                System.out.println(city);
            }
        } else {
            for(int i = 0; i < rows; i++){
                System.out.println(cities.get(i));
            }
        }

    }
}
