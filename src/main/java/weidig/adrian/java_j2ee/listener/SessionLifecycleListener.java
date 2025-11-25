package weidig.adrian.java_j2ee.listener;

import weidig.adrian.java_j2ee.util.DebugLog;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Überwacht HTTP-Sessions
 * - Wird bei jeder neuen Session aufgerufen
 * - Wird beim Session-Timeout oder expliziten Invalidate aufgerufen
 */
@WebListener
public class SessionLifecycleListener implements HttpSessionListener {

    private static final AtomicInteger activeSessions = new AtomicInteger(0);

    /**
     * Gets active session count.
     *
     * @return the active session count
     */
    public static int getActiveSessionCount() {
        return activeSessions.get();
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        int sessionCount = activeSessions.incrementAndGet();
        String sessionId = se.getSession().getId().substring(0, 8); // Nur erste 8 Zeichen

        DebugLog.log("👤", "NEUE SESSION erstellt - Aktive: " + sessionCount + " (ID: " + sessionId + "...)");

        // Session-Timeout setzen (30 Minuten)
        se.getSession().setMaxInactiveInterval(30 * 60);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        int sessionCount = activeSessions.decrementAndGet();
        String sessionId = se.getSession().getId().substring(0, 8);

        DebugLog.log("💀", "SESSION zerstört - Verbleibend: " + sessionCount + " (ID: " + sessionId + "...)");
    }
}
