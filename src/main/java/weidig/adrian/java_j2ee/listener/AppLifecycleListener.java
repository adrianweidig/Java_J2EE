package weidig.adrian.java_j2ee.listener;

import weidig.adrian.java_j2ee.util.DebugLog;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Überwacht den Lifecycle der gesamten Webanwendung
 * - Wird beim Start der Anwendung aufgerufen
 * - Wird beim Stop der Anwendung aufgerufen
 */
@WebListener
public class AppLifecycleListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DebugLog.log("🚀", "═══════════════════════════════════════════════");
        DebugLog.log("🚀", "ANWENDUNG GESTARTET");
        DebugLog.log("🚀", "Context Path: " + sce.getServletContext().getContextPath());
        DebugLog.log("🚀", "═══════════════════════════════════════════════");

        // Beispiel: Initialisierungen beim Start
        sce.getServletContext().setAttribute("startTime", System.currentTimeMillis());
        sce.getServletContext().setAttribute("appVersion", "1.0-SNAPSHOT");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        long startTime = (Long) sce.getServletContext().getAttribute("startTime");
        long uptime = System.currentTimeMillis() - startTime;

        DebugLog.log("🛑", "═══════════════════════════════════════════════");
        DebugLog.log("🛑", "ANWENDUNG WIRD GESTOPPT");
        DebugLog.log("🛑", "Context Path: " + sce.getServletContext().getContextPath());
        DebugLog.log("🛑", "Uptime: " + (uptime / 1000) + " Sekunden");
        DebugLog.log("🛑", "═══════════════════════════════════════════════");

        // Beispiel: Aufräumarbeiten beim Stop
        // Datenbankverbindungen schließen, Caches leeren, etc.
    }
}
