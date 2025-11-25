package weidig.adrian.java_j2ee.Aufgabe1;

import weidig.adrian.java_j2ee.util.DebugLog;
import weidig.adrian.java_j2ee.util.DebugConsole;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * AUFGABE 1.1: HelloServlet
 * <p>
 * ANFORDERUNGEN:
 * - Servlet namens "HelloServlet" erstellen
 * - Bei GET-Anfrage die Nachricht "Hello, World!" anzeigen
 * - Mit Annotationen einbinden (keine web.xml)
 * <p>
 * VORGEHEN ZUR LÖSUNG:
 * <p>
 * SCHRITT 1: Servlet-Klasse erstellen
 * - Klasse extends HttpServlet
 * - Package: weidig.adrian.java_j2ee.aufgaben
 * - Name: HelloWorldServlet (um Konflikt mit bestehendem HelloServlet zu vermeiden)
 * <p>
 * SCHRITT 2: @WebServlet Annotation hinzufügen
 * - name = "helloWorldServlet" (interner Name)
 * - value = "/hello-world" (URL-Mapping)
 * - Alternative: urlPatterns = {"/hello-world"}
 * <p>
 * SCHRITT 3: doGet() Methode überschreiben
 * - Wird automatisch bei GET-Anfragen aufgerufen
 * - Parameter: HttpServletRequest request, HttpServletResponse response
 * - throws IOException (Pflicht)
 * <p>
 * SCHRITT 4: Response konfigurieren
 * - setContentType("text/html;charset=UTF-8") für HTML-Ausgabe
 * - getWriter() um PrintWriter zu erhalten
 * <p>
 * SCHRITT 5: HTML ausgeben
 * - Einfache HTML-Struktur mit <!DOCTYPE>, <html>, <head>, <body>
 * - "Hello, World!" im Body anzeigen
 * <p>
 * SCHRITT 6: Testen
 * - Server starten
 * - Browser: http://localhost:8080/Java_J2EE-1.0-SNAPSHOT/hello-world
 * - Erwartung: "Hello, World!" wird angezeigt
 * <p>
 * WICHTIGE KONZEPTE:
 * - @WebServlet: Annotation für Servlet-Registrierung (seit Servlet 3.0)
 * - doGet(): Methode für HTTP GET-Requests
 * - PrintWriter: Für Text-Ausgabe an den Client
 * - Content-Type: Definiert Format der Response (text/html)
 */
@WebServlet(name = "helloWorldServlet", value = "/hello-world")
public class HelloWorldServlet extends HttpServlet {

    /**
     * INIT-METHODE (Optional)
     * Wird einmal beim Start des Servlets aufgerufen
     * Gut für Initialisierungen
     */
    @Override
    public void init() {
        DebugLog.log("🔧", "HelloWorldServlet initialisiert (Aufgabe 1.1)");
    }

    /**
     * DOGET-METHODE (Pflicht für Aufgabe)
     * Wird bei jeder GET-Anfrage aufgerufen
     *
     * @param request HttpServletRequest - Informationen über die Anfrage
     * @param response HttpServletResponse - Objekt zum Senden der Antwort
     * @throws IOException Wenn Ausgabe fehlschlägt
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        DebugLog.log("📝", "HelloWorldServlet.doGet() aufgerufen");

        // SCHRITT 1: Content-Type setzen
        // Teilt dem Browser mit, dass HTML-Inhalt kommt
        response.setContentType("text/html;charset=UTF-8");

        // SCHRITT 2: PrintWriter für Ausgabe holen
        // Mit diesem Objekt können wir Text an den Browser senden
        PrintWriter out = response.getWriter();

        // SCHRITT 3: HTML-Struktur aufbauen
        out.println("<!DOCTYPE html>");
        out.println("<html lang='de'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Aufgabe 1.1 - Hello World</title>");
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
        out.println("        h1 {");
        out.println("            color: #667eea;");
        out.println("            font-size: 3rem;");
        out.println("            margin: 0 0 20px 0;");
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
        out.println("        .back-link {");
        out.println("            display: inline-block;");
        out.println("            margin-top: 30px;");
        out.println("            color: #667eea;");
        out.println("            text-decoration: none;");
        out.println("            font-weight: 600;");
        out.println("        }");
        out.println("        .back-link:hover {");
        out.println("            text-decoration: underline;");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");

        // SCHRITT 4: Die eigentliche Aufgabe - "Hello, World!" anzeigen
        out.println("        <h1>Hello, World!</h1>");

        out.println("        <div class='badge'>✅ Aufgabe 1.1 gelöst</div>");
        out.println("        <p>Dieses Servlet wurde mit @WebServlet-Annotation registriert.</p>");
        out.println("        <a href='.' class='back-link'>← Zurück zur Startseite</a>");
        out.println("    </div>");
        out.println("</body>");

        // Debug-Konsole einbinden
        DebugConsole.render(out, request);

        out.println("</html>");

        DebugLog.log("✅", "Hello World Response gesendet");
    }

    /**
     * DESTROY-METHODE (Optional)
     * Wird beim Herunterfahren des Servlets aufgerufen
     * Gut für Aufräumarbeiten
     */
    @Override
    public void destroy() {
        DebugLog.log("💀", "HelloWorldServlet wird zerstört");
    }
}
