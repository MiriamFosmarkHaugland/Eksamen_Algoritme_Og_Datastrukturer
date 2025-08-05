/**
 * Klasse for funksjoner som brukes til å regne ut tiden det tar å utføre algoritmer. Bruker System.nanoTime().
 */
public class Timer {
    private long startTime;
    private long endTime;

    /**
     * Starter tidtakingen.
     */
    public void start() {
        startTime = System.nanoTime();
    }

    /**
     * Avslutter tidtakingen.
     */
    public void stop() {
        endTime = System.nanoTime();
    }

    /**
     * Returnerer tiden brukt i nanosekunder (ns).
     * Raskeste/minste tallet for små kodeblokker.
     * Du må kjøre start() og stop() først!
     * @return
     */
    public long durationNanos(){
        return (endTime - startTime);
    }

    /**
     * Returnerer tiden brukt i mikrosekunder (µs).
     * 1/1000 av nanosekunder om du ikke ønsker så stort tall.
     * Du må kjøre start() og stop() først!
     * @return
     */
    public long durationMicros() {
        return (endTime - startTime) / 1000;
    }

    /**
     * Returnerer tiden brukt i millisekunder (ms).
     * 1/1000_000 av nanosekunder.
     * Du må kjøre start() og stop() først!
     * @return
     */
    public int duratinoMilli(){
        return (int) ((endTime - startTime) / 1_000_000);
    }

}