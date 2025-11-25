package weidig.adrian.java_j2ee.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import weidig.adrian.java_j2ee.util.DebugLog;

/**
 * Überwacht Änderungen an Session-Attributen
 * - Wird aufgerufen, wenn Attribute zur Session hinzugefügt werden
 * - Wird aufgerufen, wenn Attribute aus der Session entfernt werden
 * - Wird aufgerufen, wenn Attribute in der Session ersetzt werden
 */
@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        String sessionId = se.getSession().getId().substring(0, 8); // Nur erste 8 Zeichen für bessere Lesbarkeit
        DebugLog.log("📝", "Session-Attribut HINZUGEFÜGT: " + se.getName() + " = " + se.getValue() +
                " (Session: " + sessionId + "...)");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        String sessionId = se.getSession().getId().substring(0, 8);
        DebugLog.log("🗑️", "Session-Attribut ENTFERNT: " + se.getName() +
                " (Session: " + sessionId + "...)");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        String sessionId = se.getSession().getId().substring(0, 8);
        Object newValue = se.getSession().getAttribute(se.getName());
        DebugLog.log("🔄", "Session-Attribut ERSETZT: " + se.getName() +
                " (alt: " + se.getValue() + " → neu: " + newValue + ", Session: " + sessionId + "...)");
    }
}
