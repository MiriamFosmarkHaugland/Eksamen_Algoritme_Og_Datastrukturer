import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class import_data {
    public static void main(String[] args) {
        readFile();
    }

    public static ArrayList<City> readFile() {
        // Path to CSV file
        String worldCitiesFile = "src/main/resources/worldcities.csv";

        // List to store all the latitude data
        ArrayList<City> worldCitiesLatitudeList = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(worldCitiesFile))) {
            String[] row;

            // Hopper over header-raden
            reader.readNext();

            // Kjører gjennom en rad og lagrer den til et City-objekt
            while ((row = reader.readNext()) != null) {
                City city = new City(
                        row[0],
                        row[1],
                        Double.parseDouble(row[2]),
                        Double.parseDouble(row[3]),
                        row[4],
                        row[5],
                        row[6],
                        row[7],
                        row[8],
                        parseIntOrZero(row[9]),
                        Long.parseLong(row[10])
                );

                // Lagrer byen i listen
                worldCitiesLatitudeList.add(city);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file " + worldCitiesFile);
        } catch (CsvValidationException e) {
            System.err.println("Error validating the CSV file " + worldCitiesFile);
        }

        // Return the list of all city latitudes
        return worldCitiesLatitudeList;
    }

    /**
     * Flere rader har ingen data i "Population" (kolonne 10), så vi returerer 0 for å unngå feil
     * @param data String som sendes over for å sjekke
     * @return
     */
    private static int parseIntOrZero(String data) {
        if (data == null || data.isBlank()) {
            return 0;
        }
        return Integer.parseInt(data);
    }
}
