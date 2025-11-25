package weidig.adrian.java_j2ee.Aufgabe1;

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
 * AUFGABE 1.3: WelcomeServlet (Teil 2)
 * <p>
 * ANFORDERUNGEN:
 * - Empfängt weitergeleiteten Request vom LoginServlet
 * - Zeigt Willkommensnachricht an
 * - Nutzt Daten die im Request-Scope gespeichert wurden
 * <p>
 * VORGEHEN ZUR LÖSUNG:
 * <p>
 * SCHRITT 1: WelcomeServlet erstellen
 * - @WebServlet mit value="/welcome-servlet"
 * - WICHTIG: URL muss mit der im LoginServlet übereinstimmen
 * <p>
 * SCHRITT 2: doPost() implementieren
 * - LoginServlet nutzt POST, also muss WelcomeServlet auch POST verarbeiten
 * - Alternativ: doGet() UND doPost() implementieren
 * <p>
 * SCHRITT 3: Daten aus Request auslesen
 * - request.getAttribute("username") → vom LoginServlet gesetzt
 * - request.getAttribute("loginTime") → vom LoginServlet gesetzt
 * - getAttribute() gibt Object zurück → casten nötig
 * <p>
 * SCHRITT 4: Willkommensnachricht ausgeben
 * - Personalisierte Begrüßung mit Benutzernamen
 * - Optional: Zeitstempel oder andere Infos anzeigen
 * <p>
 * WICHTIGE KONZEPTE:
 * - Request-Scope: Daten gelten nur für diesen Request
 * - getAttribute(): Liest vom LoginServlet gesetzte Attribute
 * - Unterschied zu getParameter():
 * * getParameter(): Liest Form/URL-Parameter (String)
 * * getAttribute(): Liest Servlet-Attribute (Object)
 * - Type-Casting: getAttribute() gibt Object zurück
 */
@WebServlet(name = "welcomeServlet", value = "/welcome-servlet")
public class WelcomeServlet extends HttpServlet {

    @Override
    public void init() {
        DebugLog.log("🔧", "WelcomeServlet initialisiert (Aufgabe 1.3)");
    }

    /**
     * DOPOST-METHODE
     * Empfängt weitergeleiteten Request vom LoginServlet
     * <p>
     * WICHTIG: LoginServlet nutzt forward(), nicht redirect()
     * - forward() behält HTTP-Methode bei (POST bleibt POST)
     * - redirect() würde GET verwenden
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        DebugLog.log("👋", "WelcomeServlet.doPost() - Willkommensseite wird angezeigt");

        // SCHRITT 1: Daten aus Request-Scope auslesen
        // getAttribute() gibt Object zurück → muss gecastet werden
        String username = (String) request.getAttribute("username");
        Long loginTime = (Long) request.getAttribute("loginTime");

        // SCHRITT 2: Validierung
        // Was wenn keine Daten vorhanden? (Direktaufruf ohne Login)
        if (username == null) {
            DebugLog.log("⚠️", "WelcomeServlet ohne Login aufgerufen");
            showAccessDenied(response);
            return;
        }

        DebugLog.log("✅", "Willkommensseite für " + username + " wird angezeigt");

        // SCHRITT 3: Zeitstempel formatieren
        String loginTimeFormatted = "unbekannt";
        if (loginTime != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            loginTimeFormatted = sdf.format(new Date(loginTime));
        }

        // SCHRITT 4: Response vorbereiten
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // SCHRITT 5: HTML ausgeben
        out.println("<!DOCTYPE html>");
        out.println("<html lang='de'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Willkommen</title>");
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
        out.println("            max-width: 600px;");
        out.println("        }");
        out.println("        .welcome-icon {");
        out.println("            font-size: 4rem;");
        out.println("            margin-bottom: 20px;");
        out.println("        }");
        out.println("        h1 {");
        out.println("            color: #667eea;");
        out.println("            font-size: 2.5rem;");
        out.println("            margin: 0 0 10px 0;");
        out.println("        }");
        out.println("        .username {");
        out.println("            color: #764ba2;");
        out.println("            font-weight: bold;");
        out.println("        }");
        out.println("        .info-box {");
        out.println("            background: #f8f9fa;");
        out.println("            padding: 20px;");
        out.println("            border-radius: 10px;");
        out.println("            margin: 30px 0;");
        out.println("            text-align: left;");
        out.println("        }");
        out.println("        .info-box h3 {");
        out.println("            color: #667eea;");
        out.println("            margin: 0 0 15px 0;");
        out.println("            font-size: 1.2rem;");
        out.println("        }");
        out.println("        .info-item {");
        out.println("            margin: 10px 0;");
        out.println("            color: #666;");
        out.println("        }");
        out.println("        .info-item strong {");
        out.println("            color: #333;");
        out.println("        }");
        out.println("        .badge {");
        out.println("            background: #28a745;");
        out.println("            color: white;");
        out.println("            padding: 8px 16px;");
        out.println("            border-radius: 20px;");
        out.println("            font-size: 0.9rem;");
        out.println("            display: inline-block;");
        out.println("            margin-top: 20px;");
        out.println("        }");
        out.println("        .links {");
        out.println("            margin-top: 30px;");
        out.println("            display: flex;");
        out.println("            gap: 20px;");
        out.println("            justify-content: center;");
        out.println("        }");
        out.println("        a {");
        out.println("            color: #667eea;");
        out.println("            text-decoration: none;");
        out.println("            font-weight: 600;");
        out.println("        }");
        out.println("        a:hover {");
        out.println("            text-decoration: underline;");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");
        out.println("        <div class='welcome-icon'>🎉</div>");
        out.println("        <h1>Willkommen, <span class='username'>" + escapeHtml(username) + "</span>!</h1>");
        out.println("        <p>Du hast dich erfolgreich angemeldet.</p>");

        out.println("        <div class='info-box'>");
        out.println("            <h3>📊 Session-Informationen</h3>");
        out.println("            <div class='info-item'>");
        out.println("                <strong>Benutzername:</strong> " + escapeHtml(username));
        out.println("            </div>");
        out.println("            <div class='info-item'>");
        out.println("                <strong>Login-Zeit:</strong> " + loginTimeFormatted);
        out.println("            </div>");
        out.println("            <div class='info-item'>");
        out.println("                <strong>Request-Methode:</strong> " + request.getMethod());
        out.println("            </div>");
        out.println("            <div class='info-item'>");
        out.println("                <strong>Request-URL:</strong> " + request.getRequestURI());
        out.println("            </div>");
        out.println("        </div>");

        out.println("        <div class='badge'>✅ Aufgabe 1.3 gelöst</div>");
        out.println("        <p style='margin-top: 20px; color: #666; font-size: 0.9rem;'>");
        out.println("            ℹ️ Dieser Request wurde vom LoginServlet weitergeleitet (forward)");
        out.println("        </p>");

        out.println("        <div class='links'>");
        out.println("            <a href='login-form.html'>← Erneut anmelden</a>");
        out.println("            <a href='.'>Zur Startseite</a>");
        out.println("        </div>");
        out.println("    </div>");
        out.println("</body>");

        DebugConsole.render(out, request);

        out.println("</html>");
    }

    /**
     * HILFSMETHODE: Access Denied Seite
     * Wird angezeigt wenn jemand direkt /welcome-servlet aufruft
     * ohne vorher durch LoginServlet zu gehen
     */
    private void showAccessDenied(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='de'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <title>Zugriff verweigert</title>");
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
        out.println("        }");
        out.println("        .icon { font-size: 4rem; margin-bottom: 20px; }");
        out.println("        h1 { color: #dc3545; }");
        out.println("        a {");
        out.println("            display: inline-block;");
        out.println("            margin-top: 20px;");
        out.println("            padding: 12px 30px;");
        out.println("            background: #667eea;");
        out.println("            color: white;");
        out.println("            text-decoration: none;");
        out.println("            border-radius: 8px;");
        out.println("            font-weight: 600;");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");
        out.println("        <div class='icon'>🚫</div>");
        out.println("        <h1>Zugriff verweigert</h1>");
        out.println("        <p>Du musst dich zuerst anmelden.</p>");
        out.println("        <a href='login-form.html'>Zum Login</a>");
        out.println("    </div>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * HILFSMETHODE: HTML-Escaping
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
        DebugLog.log("💀", "WelcomeServlet wird zerstört");
    }
}
