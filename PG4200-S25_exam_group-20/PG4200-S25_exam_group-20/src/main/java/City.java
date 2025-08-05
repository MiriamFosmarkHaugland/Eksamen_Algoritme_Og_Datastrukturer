/**
 * En public record for å lagre info om hver by i WorldCities-filen.
 * public record brukes da dataen ikke skal endres på i ettertid.
 * @param name Navn på byen
 * @param asciiName Navn på byen i ASCII (A-Z)
 * @param latitude Breddegrad
 * @param longitude Lengdegrad
 * @param country Land byen ligger i
 * @param iso2 ISO2 koden (2 tegn)
 * @param iso3 ISO3 koden (3 tegn)
 * @param adminName Navnet på fylket, delstaten eller provinsen
 * @param capital beskriver byens rolle i landet eller regionen
 * @param population Befolkningstall
 * @param id Identifikator i datasettet (Primærnøkkel)
 */
public record City(String name, String asciiName, double latitude, double longitude, String country, String iso2, String iso3, String adminName, String capital, int population, long id) { }
