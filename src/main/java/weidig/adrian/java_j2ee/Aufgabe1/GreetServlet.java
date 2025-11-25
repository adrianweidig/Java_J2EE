package weidig.adrian.java_j2ee.Aufgabe1;

import weidig.adrian.java_j2ee.util.DebugConsole;
import weidig.adrian.java_j2ee.util.DebugLog;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * AUFGABE 1.2: GreetServlet
 * <p>
 * ANFORDERUNGEN:
 * - HTML-Formular mit Eingabefeld für Namen und Senden-Button
 * - Servlet empfängt den eingegebenen Namen
 * - Zeigt Nachricht "Hello, [Name]!" an
 * <p>
 * VORGEHEN ZUR LÖSUNG:
 * <p>
 * TEIL A: HTML-Formular erstellen (greet-form.html)
 * SCHRITT 1: Formular mit <form> Tag
 * - action="greet-servlet" → Ziel-URL
 * - method="GET" → HTTP-Methode
 * <p>
 * SCHRITT 2: Eingabefeld hinzufügen
 * - <input type="text" name="name">
 * - name="name" ist wichtig → wird als Parameter-Name verwendet
 * <p>
 * SCHRITT 3: Submit-Button
 * - <button type="submit">Senden</button>
 * <p>
 * TEIL B: GreetServlet erstellen
 * SCHRITT 1: Servlet-Klasse mit @WebServlet
 * - value = "/greet-servlet" (muss mit form action übereinstimmen)
 * <p>
 * SCHRITT 2: doGet() implementieren
 * - request.getParameter("name") → liest den Parameter aus
 * - Gibt den Namen mit "Hello, [Name]!" aus
 * <p>
 * SCHRITT 3: Fehlerbehandlung
 * - Was wenn kein Name eingegeben wurde?
 * - Standardwert oder Fehlermeldung anzeigen
 * <p>
 * WICHTIGE KONZEPTE:
 * - request.getParameter(): Liest Form-Parameter
 * - HTML Form action: Definiert Ziel-Servlet
 * - GET vs POST: GET für einfache Abfragen, POST für Datenübermittlung
 * - Parameter-Validierung: Immer prüfen ob Parameter vorhanden ist
 */
@WebServlet(name = "greetServlet", value = "/greet-servlet")
public class GreetServlet extends HttpServlet {

    @Override
    public void init() {
        DebugLog.log("🔧", "GreetServlet initialisiert (Aufgabe 1.2)");
    }

    /**
     * DOGET-METHODE
     * Empfängt den Namen aus dem Formular und zeigt Begrüßung an
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        DebugLog.log("📝", "GreetServlet.doGet() aufgerufen");

        // SCHRITT 1: Parameter aus Request auslesen
        // request.getParameter("name") liest den Wert des "name"-Feldes
        // Gibt null zurück wenn Parameter nicht existiert
        String name = request.getParameter("name");

        DebugLog.log("📋", "Empfangener Name: " + (name != null ? name : "[leer]"));

        // SCHRITT 2: Validierung
        // Prüfen ob Name vorhanden und nicht leer ist
        boolean hasName = name != null && !name.trim().isEmpty();

        // SCHRITT 3: Response vorbereiten
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // SCHRITT 4: HTML ausgeben
        out.println("<!DOCTYPE html>");
        out.println("<html lang='de'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Aufgabe 1.2 - Begrüßung</title>");
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
        out.println("        h1 {");
        out.println("            color: #667eea;");
        out.println("            font-size: 2.5rem;");
        out.println("            margin: 0 0 20px 0;");
        out.println("        }");
        out.println("        .name {");
        out.println("            color: #764ba2;");
        out.println("            font-weight: bold;");
        out.println("        }");
        out.println("        .badge {");
        out.println("            background: #667eea;");
        out.println("            color: white;");
        out.println("            padding: 8px 16px;");
        out.println("            border-radius: 20px;");
        out.println("            font-size: 0.9rem;");
        out.println("            display: inline-block;");
        out.println("            margin-top: 20px;");
        out.println("        }");
        out.println("        .error {");
        out.println("            background: #ff4444;");
        out.println("            color: white;");
        out.println("            padding: 15px;");
        out.println("            border-radius: 10px;");
        out.println("            margin-bottom: 20px;");
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

        // SCHRITT 5: Bedingte Ausgabe
        // Entweder Begrüßung oder Fehlermeldung
        if (hasName) {
            // ERFOLG: Name wurde übergeben
            out.println("        <h1>Hello, <span class='name'>" + escapeHtml(name) + "</span>!</h1>");
            out.println("        <div class='badge'>✅ Aufgabe 1.2 gelöst</div>");

            DebugLog.log("✅", "Begrüßung für '" + name + "' angezeigt");
        } else {
            // FEHLER: Kein Name übergeben
            out.println("        <div class='error'>❌ Fehler: Kein Name angegeben!</div>");
            out.println("        <h1>Bitte gib einen Namen ein</h1>");

            DebugLog.log("⚠️", "Kein Name übergeben");
        }

        out.println("        <div class='links'>");
        out.println("            <a href='greet-form.html'>← Zurück zum Formular</a>");
        out.println("            <a href='.'>Zur Startseite</a>");
        out.println("        </div>");
        out.println("    </div>");
        out.println("</body>");

        DebugConsole.render(out, request);

        out.println("</html>");
    }

    /**
     * HILFSMETHODE: HTML-Escaping
     * Verhindert XSS (Cross-Site Scripting) Angriffe
     * Konvertiert: < zu &lt;, > zu &gt;, etc.
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
        DebugLog.log("💀", "GreetServlet wird zerstört");
    }
}
