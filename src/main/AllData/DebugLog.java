package weidig.adrian.java_j2ee.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Speichert alle Listener-Events im Speicher
 * Kann dann im Browser angezeigt werden
 */
public class DebugLog {

    private static final List<String> events = new ArrayList<>();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    /**
     * Log.
     *
     * @param emoji   the emoji
     * @param message the message
     */
    public static void log(String emoji, String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        String entry = String.format("[%s] %s %s", timestamp, emoji, message);
        events.add(entry);

        // Begrenze auf letzte 100 Events
        if (events.size() > 100) {
            events.remove(0);
        }
    }

    /**
     * Gets events.
     *
     * @return the events
     */
    public static List<String> getEvents() {
        return new ArrayList<>(events);
    }

    /**
     * Clear.
     */
    public static void clear() {
        events.clear();
    }
}
