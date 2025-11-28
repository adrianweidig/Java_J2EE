package weidig.adrian.java_j2ee.Aufgabe2;

import weidig.adrian.java_j2ee.util.DebugConsole;
import weidig.adrian.java_j2ee.util.DebugLog;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * AUFGABE 2.1: InfoServlet
 *
 * ANFORDERUNGEN:
 * - Servlet unter /info
 * - Gibt HTML-Seite aus mit:
 *   * HTTP-Methode (GET/POST)
 *   * Client-IP
 *   * User-Agent
 *   * Request-URL
 *   * Aktuelle Serverzeit
 *
 * VORGEHEN:
 * SCHRITT 1: @WebServlet Annotation mit value="/info"
 * SCHRITT 2: doGet() und doPost() implementieren
 * SCHRITT 3: Request-Informationen auslesen
 * SCHRITT 4: HTML-Seite formatieren
 */
@WebServlet(name = "infoServlet", value = "/info")
public class InfoServlet extends HttpServlet {

    @Override
    public void init() {
        DebugLog.log("🔧", "InfoServlet initialisiert (Aufgabe 2.1)");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        handleRequest(request, response, "GET");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        handleRequest(request, response, "POST");
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response,
                               String method) throws IOException {

        DebugLog.log("ℹ️", "InfoServlet.handleRequest() - Methode: " + method);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Daten sammeln
        String httpMethod = request.getMethod();
        String clientIp = getClientIp(request);
        String userAgent = request.getHeader("User-Agent");
        String requestUrl = request.getRequestURL().toString();
        String queryString = request.getQueryString();
        if (queryString != null) {
            requestUrl += "?" + queryString;
        }
        String serverTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());

        DebugLog.log("📊", "Info gesammelt - IP: " + clientIp + ", Method: " + httpMethod);

        // HTML ausgeben
        out.println("<!DOCTYPE html>");
        out.println("<html lang='de'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Aufgabe 2.1 - Server Info</title>");
        out.println("    <style>");
        outputStyles(out);
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");
        out.println("        <h1>🛈 Server Informationen</h1>");
        out.println("        <p class='subtitle'>Aufgabe 2.1</p>");

        out.println("        <div class='info-card'>");
        out.println("            <h2>📍 Request-Daten</h2>");
        out.println("            <table>");
        out.println("                <tr>");
        out.println("                    <td><strong>HTTP-Methode:</strong></td>");
        out.println("                    <td><code>" + httpMethod + "</code></td>");
        out.println("                </tr>");
        out.println("                <tr>");
        out.println("                    <td><strong>Client-IP:</strong></td>");
        out.println("                    <td><code>" + escapeHtml(clientIp) + "</code></td>");
        out.println("                </tr>");
        out.println("                <tr>");
        out.println("                    <td><strong>User-Agent:</strong></td>");
        out.println("                    <td><code>" + escapeHtml(userAgent != null ? userAgent : "N/A") + "</code></td>");
        out.println("                </tr>");
        out.println("                <tr>");
        out.println("                    <td><strong>Request-URL:</strong></td>");
        out.println("                    <td><code>" + escapeHtml(requestUrl) + "</code></td>");
        out.println("                </tr>");
        out.println("                <tr>");
        out.println("                    <td><strong>Serverzeit:</strong></td>");
        out.println("                    <td><code>" + serverTime + "</code></td>");
        out.println("                </tr>");
        out.println("            </table>");
        out.println("        </div>");

        out.println("        <div class='info-card'>");
        out.println("            <h2>🔗 Links</h2>");
        out.println("            <a href='.'>← Zur Startseite</a> | ");
        out.println("            <a href='info?test=parameter'>Teste mit Parameter</a>");
        out.println("        </div>");

        out.println("    </div>");
        out.println("</body>");

        DebugConsole.render(out, request);

        out.println("</html>");

        DebugLog.log("✅", "Info-Seite gesendet");
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty()) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty()) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        }
        return ip.split(",")[0];
    }

    private void outputStyles(PrintWriter out) {
        out.println("        * { margin: 0; padding: 0; box-sizing: border-box; }");
        out.println("        body {");
        out.println("            font-family: 'Segoe UI', Arial, sans-serif;");
        out.println("            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);");
        out.println("            min-height: 100vh;");
        out.println("            padding: 20px;");
        out.println("        }");
        out.println("        .container { max-width: 800px; margin: 0 auto; }");
        out.println("        h1 {");
        out.println("            color: white;");
        out.println("            text-align: center;");
        out.println("            margin-bottom: 10px;");
        out.println("            font-size: 2.5rem;");
        out.println("        }");
        out.println("        .subtitle {");
        out.println("            color: rgba(255,255,255,0.7);");
        out.println("            text-align: center;");
        out.println("            margin-bottom: 30px;");
        out.println("        }");
        out.println("        .info-card {");
        out.println("            background: white;");
        out.println("            padding: 25px;");
        out.println("            margin-bottom: 20px;");
        out.println("            border-radius: 10px;");
        out.println("            box-shadow: 0 8px 32px rgba(0,0,0,0.2);");
        out.println("        }");
        out.println("        .info-card h2 {");
        out.println("            color: #667eea;");
        out.println("            margin-bottom: 15px;");
        out.println("            font-size: 1.5rem;");
        out.println("        }");
        out.println("        table { width: 100%; border-collapse: collapse; }");
        out.println("        td { padding: 10px; border-bottom: 1px solid #eee; }");
        out.println("        td:first-child { width: 200px; color: #666; }");
        out.println("        code {");
        out.println("            background: #f5f5f5;");
        out.println("            padding: 5px 10px;");
        out.println("            border-radius: 4px;");
        out.println("            font-family: monospace;");
        out.println("            color: #333;");
        out.println("        }");
        out.println("        a { color: #667eea; text-decoration: none; font-weight: 600; }");
        out.println("        a:hover { text-decoration: underline; }");
    }

    private String escapeHtml(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }

    @Override
    public void destroy() {
        DebugLog.log("💀", "InfoServlet wird zerstört");
    }
}
