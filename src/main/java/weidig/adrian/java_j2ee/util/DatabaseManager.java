package weidig.adrian.java_j2ee.util;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static String DB_URL;

    static {
        try {
            // Versuche, die Datenbankdatei aus dem Klassenpfad zu laden (resources/j2ee.sqlite)
            String rawPath = DatabaseManager.class
                    .getClassLoader()
                    .getResource("j2ee.sqlite")
                    .getPath();

            // Pfad dekodieren, damit Leerzeichen usw. korrekt sind
            String decodedPath = URLDecoder.decode(rawPath, StandardCharsets.UTF_8.name());

            DB_URL = "jdbc:sqlite:" + decodedPath;
        } catch (Exception e) {
            // Fallback: relative Datei im aktuellen Arbeitsverzeichnis
            DB_URL = "jdbc:sqlite:j2ee.sqlite";
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            // Xerial SQLite JDBC-Treiber registrieren (org.sqlite.JDBC)
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite JDBC-Treiber (org.sqlite.JDBC) nicht gefunden", e);
        }

        // Verbindung zur SQLite-Datenbank herstellen
        return DriverManager.getConnection(DB_URL);
    }
}
