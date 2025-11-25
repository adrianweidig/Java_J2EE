package weidig.adrian.java_j2ee.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;
import weidig.adrian.java_j2ee.util.DebugLog;

/**
 * Überwacht Änderungen an Application-Scope-Attributen
 * - Wird aufgerufen, wenn Attribute hinzugefügt werden
 * - Wird aufgerufen, wenn Attribute entfernt werden
 * - Wird aufgerufen, wenn Attribute ersetzt werden
 */
@WebListener
public class AppAttributeListener implements ServletContextAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        DebugLog.log("📝", "Context-Attribut HINZUGEFÜGT: " + scae.getName() + " = " + scae.getValue());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {
        DebugLog.log("🗑️", "Context-Attribut ENTFERNT: " + scae.getName());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {
        Object newValue = scae.getServletContext().getAttribute(scae.getName());
        DebugLog.log("🔄", "Context-Attribut ERSETZT: " + scae.getName() + " (alt: " + scae.getValue() + ", neu: " + newValue + ")");
    }
}
