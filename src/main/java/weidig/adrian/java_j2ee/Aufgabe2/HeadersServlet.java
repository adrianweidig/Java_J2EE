package weidig.adrian.java_j2ee.Aufgabe2;

import weidig.adrian.java_j2ee.util.DebugLog;
import weidig.adrian.java_j2ee.util.DebugConsole;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * AUFGABE 2.4: HeadersServlet
 * - Liest: Accept-Language, User-Agent, Referer
 * - Setzt Security-Header
 */
@WebServlet(name = "headersServlet", value = "/headers")
public class HeadersServlet extends HttpServlet {

    @Override
    public void init() {
        DebugLog.log("🔧", "HeadersServlet initialisiert (Aufgabe 2.4)");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        DebugLog.log("📋", "HeadersServlet.doGet()");

        String acceptLanguage = request.getHeader("Accept-Language");
        String userAgent = request.getHeader("User-Agent");
        String referer = request.getHeader("Referer");

        response.setHeader("X-Content-Type-Options", "nosniff");
        response.setHeader("X-Frame-Options", "DENY");
        response.setHeader("X-XSS-Protection", "1; mode=block");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='de'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Aufgabe 2.4 - HTTP Header</title>");
        out.println("    <style>");
        outputStyles(out);
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");
        out.println("        <h1>📋 HTTP Header Analyse</h1>");
        out.println("        <p class='subtitle'>Aufgabe 2.4</p>");

        out.println("        <div class='section'>");
        out.println("            <h2>📩 Request-Header</h2>");
        out.println("            <table>");
        out.println("                <tr><td>Accept-Language</td><td>" + (acceptLanguage != null ? acceptLanguage : "N/A") + "</td></tr>");
        out.println("                <tr><td>User-Agent</td><td>" + (userAgent != null ? escapeHtml(userAgent) : "N/A") + "</td></tr>");
        out.println("                <tr><td>Referer</td><td>" + (referer != null ? referer : "N/A") + "</td></tr>");
        out.println("            </table>");
        out.println("        </div>");

        out.println("        <div class='section'>");
        out.println("            <h2>🔒 Response-Header (Security)</h2>");
        out.println("            <table>");
        out.println("                <tr><td>X-Content-Type-Options</td><td>nosniff</td></tr>");
        out.println("                <tr><td>X-Frame-Options</td><td>DENY</td></tr>");
        out.println("                <tr><td>X-XSS-Protection</td><td>1; mode=block</td></tr>");
        out.println("            </table>");
        out.println("        </div>");

        out.println("        <a href='.' class='btn'>← Zur Startseite</a>");
        out.println("    </div>");
        out.println("</body>");
        DebugConsole.render(out, request);
        out.println("</html>");
    }

    private void outputStyles(PrintWriter out) {
        out.println("        * { margin: 0; padding: 0; box-sizing: border-box; }");
        out.println("        body {");
        out.println("            font-family: 'Segoe UI', Arial, sans-serif;");
        out.println("            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);");
        out.println("            min-height: 100vh;");
        out.println("            padding: 20px;");
        out.println("        }");
        out.println("        .container { max-width: 1000px; margin: 0 auto; }");
        out.println("        h1 { color: white; text-align: center; margin-bottom: 10px; font-size: 2.5rem; }");
        out.println("        .subtitle { color: rgba(255,255,255,0.7); text-align: center; margin-bottom: 30px; }");
        out.println("        .section { background: white; padding: 25px; margin-bottom: 20px; border-radius: 10px; box-shadow: 0 8px 32px rgba(0,0,0,0.2); }");
        out.println("        h2 { color: #667eea; margin-bottom: 20px; }");
        out.println("        table { width: 100%; border-collapse: collapse; }");
        out.println("        td { padding: 12px; border-bottom: 1px solid #eee; }");
        out.println("        td:first-child { width: 200px; color: #666; font-weight: 600; }");
        out.println("        .btn { display: inline-block; padding: 12px 30px; background: #667eea; color: white; text-decoration: none; border-radius: 8px; margin-top: 20px; }");
        out.println("        .btn:hover { background: #5568d3; }");
    }

    private String escapeHtml(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    @Override
    public void destroy() {
        DebugLog.log("💀", "HeadersServlet wird zerstört");
    }
}
