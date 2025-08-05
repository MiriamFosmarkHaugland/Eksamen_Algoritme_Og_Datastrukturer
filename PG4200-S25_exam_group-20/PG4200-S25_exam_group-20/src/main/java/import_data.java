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

        // Create a new ArrayList to store all elements from CSV file
        ArrayList<City> worldCities = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(worldCitiesFile))) {
            String[] row;
            // Skips the header row
            reader.readNext();
            // Loop through the CSV file one row at a time until end of file
            while ((row = reader.readNext()) != null) {
                // Saves the information from the current row to a new City element
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
                // Adds the new City element to the City-list
                worldCities.add(city);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file " + worldCitiesFile);
        } catch (CsvValidationException e) {
            System.err.println("Error validating the CSV file " + worldCitiesFile);
        }
        // Return ArrayList with all cities from the CSV file
        return worldCities;
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
