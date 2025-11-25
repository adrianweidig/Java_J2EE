package weidig.adrian.java_j2ee;

import weidig.adrian.java_j2ee.util.DebugConsole;
import weidig.adrian.java_j2ee.util.DebugLog;
import weidig.adrian.java_j2ee.listener.SessionLifecycleListener;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * HelloServlet - Demonstriert alle Listener-Events
 * <p>
 * LIFECYCLE EVENTS die durch dieses Servlet ausgelöst werden:
 * <p>
 * 1. BEIM APPLICATION START (nur einmal):
 * ✅ AppLifecycleListener.contextInitialized()
 * ✅ AppAttributeListener.attributeAdded() (für startTime, appVersion)
 * <p>
 * 2. BEIM ERSTEN REQUEST (pro Benutzer):
 * ✅ SessionLifecycleListener.sessionCreated()
 * <p>
 * 3. BEI JEDEM REQUEST:
 * ✅ RequestLifecycleListener.requestInitialized()
 * ✅ RequestAttributeListener.attributeAdded() (wenn setAttribute aufgerufen wird)
 * ✅ RequestLifecycleListener.requestDestroyed()
 * <p>
 * 4. WENN SESSION-ATTRIBUTE GESETZT WERDEN:
 * ✅ SessionAttributeListener.attributeAdded()
 * ✅ SessionAttributeListener.attributeReplaced() (bei erneutem setAttribute)
 * <p>
 * 5. BEIM SESSION-TIMEOUT (nach 30 Min Inaktivität):
 * ✅ SessionAttributeListener.attributeRemoved() (für alle Session-Attribute)
 * ✅ SessionLifecycleListener.sessionDestroyed()
 * <p>
 * 6. BEIM APPLICATION STOP (nur einmal):
 * ✅ AppAttributeListener.attributeRemoved() (für alle Context-Attribute)
 * ✅ AppLifecycleListener.contextDestroyed()
 */
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    private String message;

    @Override
    public void init() {
        message = "Das ist das KIT Testprojekt für J2EE";
        DebugLog.log("🔧", "HelloServlet initialisiert");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        DebugLog.log("📍", "HelloServlet.doGet() ausgeführt");

        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        String sessionId = session.getId().substring(0, 8);
        DebugLog.log("🔑", "Session abgerufen: " + sessionId + "...");

        Integer visitCount = (Integer) session.getAttribute("visitCount");
        if (visitCount == null) {
            visitCount = 1;
            DebugLog.log("👤", "Erster Besuch");
        } else {
            visitCount++;
            DebugLog.log("🔄", "Besuch #" + visitCount);
        }
        session.setAttribute("visitCount", visitCount);
        session.setAttribute("lastVisit", System.currentTimeMillis());

        request.setAttribute("currentTime", System.currentTimeMillis());
        request.setAttribute("requestMethod", request.getMethod());

        Long startTime = (Long) getServletContext().getAttribute("startTime");
        String appVersion = (String) getServletContext().getAttribute("appVersion");
        long uptime = (System.currentTimeMillis() - startTime) / 1000;

        // ═══════════════════════════════════════════════════════════
        // HTML AUSGABE mit Live-Events
        // ═══════════════════════════════════════════════════════════
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <title>KIT J2EE - Listener Events</title>");
        out.println("    <meta http-equiv='refresh' content='5'>"); // Auto-Reload alle 5 Sekunden
        out.println("    <style>");
        out.println("        body {");
        out.println("            font-family: 'Segoe UI', monospace, sans-serif;");
        out.println("            padding: 20px;");
        out.println("            background: #1e1e1e;");
        out.println("            color: #d4d4d4;");
        out.println("        }");
        out.println("        .container { max-width: 1200px; margin: 0 auto; }");
        out.println("        h1 { color: #4ec9b0; }");
        out.println("        .info {");
        out.println("            background: #252526;");
        out.println("            padding: 15px;");
        out.println("            margin: 10px 0;");
        out.println("            border-left: 3px solid #007acc;");
        out.println("            border-radius: 3px;");
        out.println("        }");
        out.println("        .events {");
        out.println("            background: #1e1e1e;");
        out.println("            border: 1px solid #3c3c3c;");
        out.println("            padding: 15px;");
        out.println("            max-height: 500px;");
        out.println("            overflow-y: auto;");
        out.println("            font-family: 'Consolas', monospace;");
        out.println("            font-size: 13px;");
        out.println("        }");
        out.println("        .event {");
        out.println("            padding: 5px;");
        out.println("            margin: 2px 0;");
        out.println("            border-bottom: 1px solid #2d2d2d;");
        out.println("        }");
        out.println("        .refresh { color: #4ec9b0; font-size: 12px; }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");
        out.println("        <h1>🎉 " + message + "</h1>");

        out.println("        <div class='info'>");
        out.println("            <strong>Application Info:</strong><br>");
        out.println("            Version: " + appVersion + "<br>");
        out.println("            Uptime: " + uptime + " Sekunden<br>");
        out.println("            Aktive Sessions: " + SessionLifecycleListener.getActiveSessionCount());
        out.println("        </div>");

        out.println("        <div class='info'>");
        out.println("            <strong>Session Info:</strong><br>");
        out.println("            Besuche: " + visitCount + "<br>");
        out.println("            Session-ID: " + sessionId + "...");
        out.println("        </div>");

        out.println("        <h2>📊 Live Listener Events</h2>");
        out.println("        <p class='refresh'>⟳ Auto-Refresh alle 5 Sekunden | <a href='?action=clear' style='color:#4ec9b0'>Clear Log</a></p>");

        // Clear-Funktion
        if ("clear".equals(request.getParameter("action"))) {
            DebugLog.clear();
            DebugLog.log("🧹", "Event-Log geleert");
        }

        out.println("        <div class='events'>");
        for (String event : DebugLog.getEvents()) {
            out.println("            <div class='event'>" + event + "</div>");
        }
        out.println("        </div>");

        out.println("        <p style='margin-top: 20px; font-size: 12px; color: #858585;'>");
        out.println("            💡 Die Events werden automatisch aktualisiert. Öffne mehrere Browser-Tabs, um Session-Events zu sehen!");
        out.println("        </p>");

        out.println("    </div>");
        out.println("</body>");

        DebugConsole.render(out, request);

        out.println("</html>");

        DebugLog.log("📍", "HelloServlet.doGet() beendet");
    }

    @Override
    public void destroy() {
        DebugLog.log("💀", "HelloServlet wird zerstört");
    }
}
