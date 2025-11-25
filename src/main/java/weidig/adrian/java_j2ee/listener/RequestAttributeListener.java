package weidig.adrian.java_j2ee.listener;

import weidig.adrian.java_j2ee.util.DebugLog;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * Überwacht Änderungen an Request-Attributen
 * - Wird aufgerufen, wenn Attribute zum Request hinzugefügt werden
 * - Wird aufgerufen, wenn Attribute aus dem Request entfernt werden
 * - Wird aufgerufen, wenn Attribute im Request ersetzt werden
 */
@WebListener
public class RequestAttributeListener implements ServletRequestAttributeListener {

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        // Nur relevante Attribute loggen (requestStartTime wird vom RequestLifecycleListener gesetzt)
        if (!"requestStartTime".equals(srae.getName())) {
            DebugLog.log("📝", "Request-Attribut HINZUGEFÜGT: " + srae.getName() + " = " + srae.getValue());
        }
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        DebugLog.log("🗑️", "Request-Attribut ENTFERNT: " + srae.getName());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        DebugLog.log("🔄", "Request-Attribut ERSETZT: " + srae.getName());
    }
}
