package weidig.adrian.java_j2ee.Aufgabe2;

import weidig.adrian.java_j2ee.util.DebugConsole;
import weidig.adrian.java_j2ee.util.DebugLog;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * AUFGABE 2.5: UserServlet
 * - Parameter id: Zahl 1..9999
 * - Bei Fehler: HTTP 400 + custom HTML
 * - Bei Erfolg: "User #id geladen"
 */
@WebServlet(name = "userServlet", value = "/user")
public class UserServlet extends HttpServlet {

    @Override
    public void init() {
        DebugLog.log("🔧", "UserServlet initialisiert (Aufgabe 2.5)");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        DebugLog.log("👤", "UserServlet.doGet()");

        response.setContentType("text/html;charset=UTF-8");

        String idParam = request.getParameter("id");
        Integer userId = null;
        String error = null;

        if (idParam == null || idParam.trim().isEmpty()) {
            error = "Parameter 'id' ist erforderlich";
        } else {
            try {
                userId = Integer.parseInt(idParam);
                if (userId < 1 || userId > 9999) {
                    error = "User ID muss zwischen 1 und 9999 liegen";
                }
            } catch (NumberFormatException e) {
                error = "User ID muss eine Zahl sein";
            }
        }

        PrintWriter out = response.getWriter();

        if (error != null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            outputError(out, request, error);
        } else {
            outputSuccess(out, request, userId);
        }
    }

    private void outputError(PrintWriter out, HttpServletRequest request, String error) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang='de'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <title>HTTP 400 - Fehler</title>");
        out.println("    <style>");
        outputStyles(out);
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");
        out.println("        <div class='error-box'>");
        out.println("            <h1>❌ HTTP 400 - Bad Request</h1>");
        out.println("            <p class='error'>" + error + "</p>");
        out.println("            <p><code>/user?id=123</code></p>");
        out.println("            <a href='user?id=1' class='btn'>Beispiel: User #1</a>");
        out.println("            <a href='.' class='btn secondary'>Startseite</a>");
        out.println("        </div>");
        out.println("    </div>");
        out.println("</body>");
        DebugConsole.render(out, request);
        out.println("</html>");
    }

    private void outputSuccess(PrintWriter out, HttpServletRequest request, Integer userId) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang='de'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <title>User #" + userId + "</title>");
        out.println("    <style>");
        outputStyles(out);
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");
        out.println("        <div class='success-box'>");
        out.println("            <h1>✅ User #" + userId + " geladen</h1>");
        out.println("            <p>User mit ID <strong>" + userId + "</strong> erfolgreich geladen.</p>");
        out.println("            <a href='user?id=" + (userId > 1 ? userId - 1 : 1) + "' class='btn'>← Vorheriger</a>");
        out.println("            <a href='user?id=" + (userId < 9999 ? userId + 1 : 9999) + "' class='btn'>Nächster →</a>");
        out.println("            <a href='.' class='btn secondary'>Startseite</a>");
        out.println("        </div>");
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
        out.println("            display: flex;");
        out.println("            justify-content: center;");
        out.println("            align-items: center;");
        out.println("            padding: 20px;");
        out.println("        }");
        out.println("        .container { max-width: 600px; width: 100%; }");
        out.println("        .error-box, .success-box {");
        out.println("            background: white;");
        out.println("            padding: 40px;");
        out.println("            border-radius: 15px;");
        out.println("            box-shadow: 0 8px 32px rgba(0,0,0,0.2);");
        out.println("            text-align: center;");
        out.println("        }");
        out.println("        h1 { color: #667eea; margin-bottom: 20px; }");
        out.println("        p { color: #666; margin-bottom: 20px; }");
        out.println("        code { background: #f5f5f5; padding: 5px 10px; border-radius: 4px; }");
        out.println("        .btn { display: inline-block; padding: 12px 30px; background: #667eea; color: white; text-decoration: none; border-radius: 8px; margin: 10px 5px; }");
        out.println("        .btn:hover { background: #5568d3; }");
        out.println("        .btn.secondary { background: #999; }");
        out.println("        .btn.secondary:hover { background: #777; }");
    }

    @Override
    public void destroy() {
        DebugLog.log("💀", "UserServlet wird zerstört");
    }
}
