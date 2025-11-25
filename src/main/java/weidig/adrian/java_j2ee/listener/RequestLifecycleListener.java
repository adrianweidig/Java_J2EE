package weidig.adrian.java_j2ee.listener;

import weidig.adrian.java_j2ee.util.DebugLog;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Überwacht jeden einzelnen HTTP-Request
 * - Wird bei Beginn jedes Requests aufgerufen
 * - Wird am Ende jedes Requests aufgerufen
 * Nützlich für: Logging, Performance-Messung, Security-Checks
 */
@WebListener
public class RequestLifecycleListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();

        long startTime = System.currentTimeMillis();
        request.setAttribute("requestStartTime", startTime);

        DebugLog.log("🌐", "REQUEST: " + request.getMethod() + " " + request.getRequestURI() +
                " (IP: " + request.getRemoteAddr() + ")");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();

        Long startTime = (Long) request.getAttribute("requestStartTime");
        if (startTime != null) {
            long duration = System.currentTimeMillis() - startTime;
            DebugLog.log("✅", "REQUEST beendet (" + duration + " ms): " + request.getRequestURI());
        }
    }
}
