package weidig.adrian.java_j2ee.Aufgabe2;

import weidig.adrian.java_j2ee.util.DebugLog;
import weidig.adrian.java_j2ee.util.DebugConsole;

import java.io.*;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * AUFGABE 2.2: RegisterServlet
 * - GET: Zeigt Formular
 * - POST: Validiert Daten (Name min. 2 Zeichen, Email mit @ und .)
 * - Bei Fehler: Formular mit Fehler
 * - Bei Erfolg: Bestätigungsseite
 */
@WebServlet(name = "registerServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    public void init() {
        DebugLog.log("🔧", "RegisterServlet initialisiert (Aufgabe 2.2)");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        DebugLog.log("📝", "RegisterServlet.doGet()");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        outputForm(out, request, null, null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        DebugLog.log("✉️", "RegisterServlet.doPost()");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");

        Map<String, String> errors = validateRegistration(name, email);

        if (!errors.isEmpty()) {
            DebugLog.log("❌", "Validierungsfehler");
            outputForm(out, request, errors, new HashMap<String, String>() {{
                put("name", name != null ? name : "");
                put("email", email != null ? email : "");
            }});
        } else {
            DebugLog.log("✅", "Registrierung erfolgreich");
            outputSuccess(out, request, name, email);
        }
    }

    private Map<String, String> validateRegistration(String name, String email) {
        Map<String, String> errors = new HashMap<>();

        if (name == null || name.trim().isEmpty()) {
            errors.put("name", "Name ist erforderlich");
        } else if (name.trim().length() < 2) {
            errors.put("name", "Name muss mindestens 2 Zeichen lang sein");
        }

        if (email == null || email.trim().isEmpty()) {
            errors.put("email", "E-Mail ist erforderlich");
        } else if (!email.contains("@") || !email.contains(".")) {
            errors.put("email", "E-Mail muss @ und . enthalten");
        }

        return errors;
    }

    private void outputForm(PrintWriter out, HttpServletRequest request,
                            Map<String, String> errors, Map<String, String> formData) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang='de'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Aufgabe 2.2 - Registrierung</title>");
        out.println("    <style>");
        outputFormStyles(out);
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");
        out.println("        <div class='form-box'>");
        out.println("            <h1>📝 Registrierung</h1>");
        out.println("            <p class='subtitle'>Aufgabe 2.2</p>");

        if (errors != null && !errors.isEmpty()) {
            out.println("            <div class='error-box'>");
            out.println("                <h3>❌ Fehler:</h3>");
            out.println("                <ul>");
            for (String error : errors.values()) {
                out.println("                    <li>" + error + "</li>");
            }
            out.println("                </ul>");
            out.println("            </div>");
        }

        out.println("            <form method='POST' action='register'>");
        out.println("                <div class='form-group'>");
        out.println("                    <label for='name'>Name *</label>");
        out.println("                    <input type='text' id='name' name='name' value='" +
                (formData != null ? formData.getOrDefault("name", "") : "") + "' required>");
        out.println("                </div>");
        out.println("                <div class='form-group'>");
        out.println("                    <label for='email'>E-Mail *</label>");
        out.println("                    <input type='email' id='email' name='email' value='" +
                (formData != null ? formData.getOrDefault("email", "") : "") + "' required>");
        out.println("                </div>");
        out.println("                <button type='submit' class='btn'>Registrieren</button>");
        out.println("            </form>");
        out.println("            <a href='.' class='back-link'>← Zur Startseite</a>");
        out.println("        </div>");
        out.println("    </div>");
        out.println("</body>");
        DebugConsole.render(out, request);
        out.println("</html>");
    }

    private void outputSuccess(PrintWriter out, HttpServletRequest request,
                               String name, String email) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang='de'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Registrierung Erfolgreich</title>");
        out.println("    <style>");
        outputFormStyles(out);
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");
        out.println("        <div class='form-box success'>");
        out.println("            <h1>✅ Registrierung erfolgreich!</h1>");
        out.println("            <p><strong>Name:</strong> " + escapeHtml(name) + "</p>");
        out.println("            <p><strong>E-Mail:</strong> " + escapeHtml(email) + "</p>");
        out.println("            <a href='register' class='btn'>Erneut registrieren</a> | ");
        out.println("            <a href='.' class='back-link'>Zur Startseite</a>");
        out.println("        </div>");
        out.println("    </div>");
        out.println("</body>");
        DebugConsole.render(out, request);
        out.println("</html>");
    }

    private void outputFormStyles(PrintWriter out) {
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
        out.println("        .container { width: 100%; max-width: 500px; }");
        out.println("        .form-box {");
        out.println("            background: white;");
        out.println("            padding: 40px;");
        out.println("            border-radius: 15px;");
        out.println("            box-shadow: 0 8px 32px rgba(0,0,0,0.2);");
        out.println("        }");
        out.println("        .form-box.success { background: #d4edda; border: 2px solid #28a745; }");
        out.println("        h1 { color: #667eea; margin-bottom: 10px; text-align: center; }");
        out.println("        .form-box.success h1 { color: #155724; }");
        out.println("        .subtitle { text-align: center; color: #999; margin-bottom: 25px; font-size: 0.9rem; }");
        out.println("        .error-box {");
        out.println("            background: #f8d7da;");
        out.println("            border: 1px solid #f5c6cb;");
        out.println("            padding: 15px;");
        out.println("            border-radius: 8px;");
        out.println("            margin-bottom: 20px;");
        out.println("        }");
        out.println("        .error-box h3 { color: #721c24; margin-bottom: 10px; }");
        out.println("        .error-box ul { margin-left: 20px; color: #721c24; }");
        out.println("        .form-group { margin-bottom: 20px; }");
        out.println("        label { display: block; margin-bottom: 8px; font-weight: 600; color: #333; }");
        out.println("        input { width: 100%; padding: 12px; border: 2px solid #ddd; border-radius: 8px; font-size: 1rem; }");
        out.println("        input:focus { outline: none; border-color: #667eea; }");
        out.println("        .btn { width: 100%; padding: 12px; background: #667eea; color: white; border: none; border-radius: 8px; font-size: 1.1rem; font-weight: 600; cursor: pointer; }");
        out.println("        .btn:hover { background: #5568d3; }");
        out.println("        .back-link { display: block; text-align: center; margin-top: 20px; color: #667eea; text-decoration: none; }");
        out.println("        .back-link:hover { text-decoration: underline; }");
        out.println("        p { margin: 10px 0; }");
    }

    private String escapeHtml(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    @Override
    public void destroy() {
        DebugLog.log("💀", "RegisterServlet wird zerstört");
    }
}
