package weidig.adrian.java_j2ee.Aufgabe1;

import weidig.adrian.java_j2ee.util.DebugLog;
import weidig.adrian.java_j2ee.util.DebugConsole;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * AUFGABE 1.3: LoginServlet mit Request-Weiterleitung
 * <p>
 * ANFORDERUNGEN:
 * - Zwei Servlets: LoginServlet und WelcomeServlet
 * - LoginServlet überprüft Benutzeranmeldung
 * - Bei erfolgreicher Anmeldung: Weiterleitung an WelcomeServlet
 * - WelcomeServlet zeigt Willkommensnachricht
 * <p>
 * VORGEHEN ZUR LÖSUNG:
 * <p>
 * SCHRITT 1: LoginServlet erstellen
 * - @WebServlet mit value="/login-servlet"
 * - doPost() Methode für Formular-Verarbeitung
 * <p>
 * SCHRITT 2: Credentials aus Request auslesen
 * - request.getParameter("username")
 * - request.getParameter("password")
 * <p>
 * SCHRITT 3: Validierung durchführen
 * - Prüfen ob Username und Passwort korrekt sind
 * - Beispiel: username="admin" und password="password123"
 * <p>
 * SCHRITT 4: Bei ERFOLG - Request weiterleiten
 * - request.setAttribute() → Daten an nächstes Servlet übergeben
 * - RequestDispatcher holen mit request.getRequestDispatcher()
 * - forward() aufrufen → Request an WelcomeServlet weiterleiten
 * <p>
 * SCHRITT 5: Bei FEHLER - Fehlermeldung anzeigen
 * - Direkt HTML-Fehlerseite ausgeben
 * - Oder zurück zum Login-Formular redirecten
 * <p>
 * WICHTIGE KONZEPTE:
 * - RequestDispatcher.forward(): Server-seitige Weiterleitung
 * - request.setAttribute(): Daten zwischen Servlets teilen
 * - POST-Methode: Für sensitive Daten wie Passwörter
 * - Unterschied forward vs redirect:
 * * forward: Interne Weiterleitung, URL ändert sich nicht
 * * redirect: Browser-Weiterleitung, URL ändert sich
 */
@WebServlet(name = "loginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {

    // KONSTANTEN für Test-Credentials
    // In Produktion würden diese aus Datenbank kommen
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "password123";

    @Override
    public void init() {
        DebugLog.log("🔧", "LoginServlet initialisiert (Aufgabe 1.3)");
    }

    /**
     * DOPOST-METHODE
     * Verarbeitet Login-Formular und leitet bei Erfolg weiter
     *
     * WICHTIG: POST statt GET für Login-Daten!
     * - GET: Parameter sichtbar in URL → unsicher
     * - POST: Parameter im Request-Body → sicherer
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DebugLog.log("🔐", "LoginServlet.doPost() - Login-Versuch");

        // SCHRITT 1: Credentials aus Request auslesen
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Debug-Log (ohne Passwort aus Sicherheitsgründen)
        DebugLog.log("👤", "Login-Versuch für Benutzer: " + username);

        // SCHRITT 2: Validierung
        boolean isValid = validateCredentials(username, password);

        if (isValid) {
            // ═══════════════════════════════════════════════════════════
            // ERFOLG: Credentials korrekt
            // ═══════════════════════════════════════════════════════════

            DebugLog.log("✅", "Login erfolgreich für: " + username);

            // SCHRITT 3: Daten für WelcomeServlet vorbereiten
            // setAttribute() speichert Daten im Request-Scope
            // Diese sind dann im nächsten Servlet verfügbar
            request.setAttribute("username", username);
            request.setAttribute("loginTime", System.currentTimeMillis());

            // SCHRITT 4: RequestDispatcher holen
            // Wichtig: Pfad muss mit "/" beginnen
            RequestDispatcher dispatcher = request.getRequestDispatcher("/welcome-servlet");

            // SCHRITT 5: Request weiterleiten (forward)
            // forward() übergibt Request und Response an WelcomeServlet
            // URL im Browser ändert sich NICHT
            dispatcher.forward(request, response);

            DebugLog.log("➡️", "Request an WelcomeServlet weitergeleitet");

        } else {
            // ═══════════════════════════════════════════════════════════
            // FEHLER: Credentials falsch
            // ═══════════════════════════════════════════════════════════

            DebugLog.log("❌", "Login fehlgeschlagen für: " + username);

            // Fehlerseite direkt ausgeben
            showErrorPage(response, username);
        }
    }

    /**
     * HILFSMETHODE: Credentials validieren
     * In Produktion würde hier Datenbank-Abfrage stattfinden
     *
     * @param username Eingegebener Benutzername
     * @param password Eingegebenes Passwort
     * @return true wenn Credentials korrekt, sonst false
     */
    private boolean validateCredentials(String username, String password) {
        // Null-Check
        if (username == null || password == null) {
            return false;
        }

        // Vergleich mit Test-Credentials
        return VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password);
    }

    /**
     * HILFSMETHODE: Fehlerseite ausgeben
     * Zeigt schöne Fehlerseite bei fehlgeschlagenem Login
     */
    private void showErrorPage(HttpServletResponse response, String username)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='de'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Login Fehlgeschlagen</title>");
        out.println("    <style>");
        out.println("        body {");
        out.println("            font-family: 'Segoe UI', Arial, sans-serif;");
        out.println("            display: flex;");
        out.println("            justify-content: center;");
        out.println("            align-items: center;");
        out.println("            min-height: 100vh;");
        out.println("            margin: 0;");
        out.println("            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);");
        out.println("        }");
        out.println("        .container {");
        out.println("            background: white;");
        out.println("            padding: 60px 80px;");
        out.println("            border-radius: 20px;");
        out.println("            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);");
        out.println("            text-align: center;");
        out.println("            max-width: 500px;");
        out.println("        }");
        out.println("        .error-icon {");
        out.println("            font-size: 4rem;");
        out.println("            margin-bottom: 20px;");
        out.println("        }");
        out.println("        h1 {");
        out.println("            color: #dc3545;");
        out.println("            font-size: 2rem;");
        out.println("            margin: 0 0 15px 0;");
        out.println("        }");
        out.println("        p {");
        out.println("            color: #666;");
        out.println("            margin-bottom: 30px;");
        out.println("        }");
        out.println("        .btn {");
        out.println("            display: inline-block;");
        out.println("            padding: 12px 30px;");
        out.println("            background: #667eea;");
        out.println("            color: white;");
        out.println("            text-decoration: none;");
        out.println("            border-radius: 8px;");
        out.println("            font-weight: 600;");
        out.println("            transition: background 0.3s;");
        out.println("        }");
        out.println("        .btn:hover {");
        out.println("            background: #5568d3;");
        out.println("        }");
        out.println("        .hint {");
        out.println("            background: #fff3cd;");
        out.println("            border: 1px solid #ffc107;");
        out.println("            padding: 15px;");
        out.println("            border-radius: 8px;");
        out.println("            margin-top: 20px;");
        out.println("            font-size: 0.9rem;");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");
        out.println("        <div class='error-icon'>🔒</div>");
        out.println("        <h1>Login Fehlgeschlagen</h1>");
        out.println("        <p>Benutzername oder Passwort sind falsch.</p>");

        if (username != null && !username.trim().isEmpty()) {
            out.println("        <p>Versuchter Benutzer: <strong>" + escapeHtml(username) + "</strong></p>");
        }

        out.println("        <a href='login-form.html' class='btn'>Erneut versuchen</a>");
        out.println("        <div class='hint'>");
        out.println("            💡 <strong>Hinweis:</strong> Korrekte Credentials sind<br>");
        out.println("            Benutzername: <code>admin</code><br>");
        out.println("            Passwort: <code>password123</code>");
        out.println("        </div>");
        out.println("    </div>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * HILFSMETHODE: HTML-Escaping
     * Verhindert XSS-Angriffe
     */
    private String escapeHtml(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    @Override
    public void destroy() {
        DebugLog.log("💀", "LoginServlet wird zerstört");
    }
}
