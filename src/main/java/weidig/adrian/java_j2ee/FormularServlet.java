package weidig.adrian.java_j2ee;

import weidig.adrian.java_j2ee.util.DebugConsole;
import weidig.adrian.java_j2ee.util.DebugLog;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * FormularServlet - Demonstriert Formularverarbeitung in J2EE
 * <p>
 * FUNKTIONEN:
 * - Formular-Anzeige (GET)
 * - Formular-Verarbeitung (POST)
 * - Server-seitige Validierung
 * - Error-Handling
 * - Multi-Select und Checkboxen
 * - File-Upload Simulation
 * - Session-basierte Datenspeicherung
 * - Erfolgs- und Fehlermeldungen
 * <p>
 * VALIDIERUNGS-REGELN:
 * - Name: Pflichtfeld, min. 2 Zeichen
 * - E-Mail: Pflichtfeld, valides Format
 * - Alter: Optional, zwischen 1-150
 * - Geschlecht: Pflichtfeld
 * - Interessen: Mindestens 1 ausgewählt
 * - Nachricht: Pflichtfeld, max. 500 Zeichen
 */
@WebServlet(name = "formularServlet", value = "/formular-servlet")
public class FormularServlet extends HttpServlet {

    @Override
    public void init() {
        DebugLog.log("🔧", "FormularServlet initialisiert");
    }

    /**
     * GET - Zeigt das Formular an
     * Wird aufgerufen wenn User die Seite das erste Mal besucht
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        DebugLog.log("📝", "FormularServlet.doGet() - Formular wird angezeigt");

        response.setContentType("text/html;charset=UTF-8");

        // Session für Fehler und Erfolgsmeldungen
        HttpSession session = request.getSession();

        PrintWriter out = response.getWriter();
        outputHTML(out, request, session, null, null);
    }

    /**
     * POST - Verarbeitet das ausgefüllte Formular
     * Wird aufgerufen wenn User auf "Absenden" klickt
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        DebugLog.log("✉️", "FormularServlet.doPost() - Formular wird verarbeitet");

        // UTF-8 Encoding für Umlaute
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // SCHRITT 1: Formulardaten auslesen

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String alterStr = request.getParameter("alter");
        String geschlecht = request.getParameter("geschlecht");
        String[] interessen = request.getParameterValues("interessen");
        String land = request.getParameter("land");
        String nachricht = request.getParameter("nachricht");
        String newsletter = request.getParameter("newsletter");

        DebugLog.log("📋", "Formulardaten empfangen: Name=" + name + ", Email=" + email);


        // SCHRITT 2: Validierung

        Map<String, String> errors = validateForm(name, email, alterStr, geschlecht, interessen, nachricht);

        HttpSession session = request.getSession();

        // Wenn Fehler vorhanden, Formular erneut anzeigen
        if (!errors.isEmpty()) {
            DebugLog.log("❌", "Validierungsfehler: " + errors.size() + " Fehler gefunden");

            // Eingaben für erneute Anzeige speichern
            Map<String, String> formData = new HashMap<>();
            formData.put("name", name != null ? name : "");
            formData.put("email", email != null ? email : "");
            formData.put("alter", alterStr != null ? alterStr : "");
            formData.put("geschlecht", geschlecht != null ? geschlecht : "");
            formData.put("land", land != null ? land : "");
            formData.put("nachricht", nachricht != null ? nachricht : "");
            formData.put("newsletter", newsletter != null ? "checked" : "");

            if (interessen != null) {
                formData.put("interessen", String.join(",", interessen));
            }

            PrintWriter out = response.getWriter();
            outputHTML(out, request, session, errors, formData);
            return;
        }


        // SCHRITT 3: Daten verarbeiten (erfolgreich validiert)

        DebugLog.log("✅", "Formular erfolgreich validiert und verarbeitet");

        // In Session speichern (simuliert Datenbank)
        session.setAttribute("formular_name", name);
        session.setAttribute("formular_email", email);
        session.setAttribute("formular_timestamp", new Date().toString());

        // Erfolgsseite anzeigen
        response.sendRedirect(request.getContextPath() + "/formular-servlet?success=true");
    }

    /**
     * Validiert alle Formularfelder
     *
     * @return Map mit Fehlermeldungen (leer wenn alles OK)
     */
    private Map<String, String> validateForm(String name, String email, String alterStr, String geschlecht, String[] interessen, String nachricht) {
        Map<String, String> errors = new HashMap<>();

        // Name Validierung
        if (name == null || name.trim().isEmpty()) {
            errors.put("name", "Name ist ein Pflichtfeld");
        } else if (name.trim().length() < 2) {
            errors.put("name", "Name muss mindestens 2 Zeichen lang sein");
        }

        // E-Mail Validierung
        if (email == null || email.trim().isEmpty()) {
            errors.put("email", "E-Mail ist ein Pflichtfeld");
        } else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            errors.put("email", "Ungültiges E-Mail-Format");
        }

        // Alter Validierung (optional)
        if (alterStr != null && !alterStr.trim().isEmpty()) {
            try {
                int alter = Integer.parseInt(alterStr);
                if (alter < 1 || alter > 150) {
                    errors.put("alter", "Alter muss zwischen 1 und 150 liegen");
                }
            } catch (NumberFormatException e) {
                errors.put("alter", "Alter muss eine Zahl sein");
            }
        }

        // Geschlecht Validierung
        if (geschlecht == null || geschlecht.trim().isEmpty()) {
            errors.put("geschlecht", "Bitte wählen Sie ein Geschlecht");
        }

        // Interessen Validierung
        if (interessen == null || interessen.length == 0) {
            errors.put("interessen", "Bitte wählen Sie mindestens ein Interesse");
        }

        // Nachricht Validierung
        if (nachricht == null || nachricht.trim().isEmpty()) {
            errors.put("nachricht", "Nachricht ist ein Pflichtfeld");
        } else if (nachricht.length() > 500) {
            errors.put("nachricht", "Nachricht darf maximal 500 Zeichen lang sein");
        }

        return errors;
    }

    /**
     * Generiert die HTML-Ausgabe
     */
    private void outputHTML(PrintWriter out, HttpServletRequest request, HttpSession session, Map<String, String> errors, Map<String, String> formData) {

        // Erfolgsmeldung prüfen
        boolean showSuccess = session.getAttribute("formular_name") != null;

        out.println("<!DOCTYPE html>");
        out.println("<html lang='de'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Formular Demo - KIT J2EE</title>");
        out.println("    <style>");
        outputCSS(out);
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");

        // Header
        out.println("        <div class='header'>");
        out.println("            <h1>📝 Formular Verarbeitung</h1>");
        out.println("            <p>Demonstriert Validierung, Error-Handling und Session-Management</p>");
        out.println("            <a href='.' class='back-link'>← Zurück zur Startseite</a>");
        out.println("        </div>");

        // Erfolgsmeldung
        if (showSuccess && errors == null) {
            out.println("        <div class='success-message'>");
            out.println("            <h2>✅ Formular erfolgreich übermittelt!</h2>");
            out.println("            <p><strong>Name:</strong> " + session.getAttribute("formular_name") + "</p>");
            out.println("            <p><strong>E-Mail:</strong> " + session.getAttribute("formular_email") + "</p>");
            out.println("            <p><strong>Zeitstempel:</strong> " + session.getAttribute("formular_timestamp") + "</p>");
            out.println("            <a href='formular-servlet' class='btn'>Neues Formular</a>");
            out.println("        </div>");

            // Session-Daten nach Anzeige löschen
            session.removeAttribute("formular_name");
            session.removeAttribute("formular_email");
            session.removeAttribute("formular_timestamp");
        } else {
            // Formular anzeigen
            outputForm(out, errors, formData);
        }

        out.println("    </div>");
        out.println("</body>");
        DebugConsole.render(out, request);
        out.println("</html>");
    }

    /**
     * Generiert das Formular
     */
    private void outputForm(PrintWriter out, Map<String, String> errors, Map<String, String> formData) {

        // Fehlerzusammenfassung
        if (errors != null && !errors.isEmpty()) {
            out.println("        <div class='error-summary'>");
            out.println("            <h3>❌ Bitte korrigieren Sie folgende Fehler:</h3>");
            out.println("            <ul>");
            for (String error : errors.values()) {
                out.println("                <li>" + error + "</li>");
            }
            out.println("            </ul>");
            out.println("        </div>");
        }

        out.println("        <form method='POST' action='formular-servlet' class='form'>");

        // Name
        out.println("            <div class='form-group" + (hasError(errors, "name") ? " error" : "") + "'>");
        out.println("                <label for='name'>Name *</label>");
        out.println("                <input type='text' id='name' name='name' ");
        out.println("                       value='" + getValue(formData, "name") + "' ");
        out.println("                       placeholder='Max Mustermann' required>");
        if (hasError(errors, "name")) {
            out.println("                <span class='error-message'>" + errors.get("name") + "</span>");
        }
        out.println("            </div>");

        // E-Mail
        out.println("            <div class='form-group" + (hasError(errors, "email") ? " error" : "") + "'>");
        out.println("                <label for='email'>E-Mail *</label>");
        out.println("                <input type='email' id='email' name='email' ");
        out.println("                       value='" + getValue(formData, "email") + "' ");
        out.println("                       placeholder='max@example.com' required>");
        if (hasError(errors, "email")) {
            out.println("                <span class='error-message'>" + errors.get("email") + "</span>");
        }
        out.println("            </div>");

        // Alter
        out.println("            <div class='form-group" + (hasError(errors, "alter") ? " error" : "") + "'>");
        out.println("                <label for='alter'>Alter (optional)</label>");
        out.println("                <input type='number' id='alter' name='alter' ");
        out.println("                       value='" + getValue(formData, "alter") + "' ");
        out.println("                       placeholder='25' min='1' max='150'>");
        if (hasError(errors, "alter")) {
            out.println("                <span class='error-message'>" + errors.get("alter") + "</span>");
        }
        out.println("            </div>");

        // Geschlecht (Radio)
        out.println("            <div class='form-group" + (hasError(errors, "geschlecht") ? " error" : "") + "'>");
        out.println("                <label>Geschlecht *</label>");
        out.println("                <div class='radio-group'>");
        out.println("                    <label><input type='radio' name='geschlecht' value='m' " + (isChecked(formData, "geschlecht", "m") ? "checked" : "") + "> Männlich</label>");
        out.println("                    <label><input type='radio' name='geschlecht' value='w' " + (isChecked(formData, "geschlecht", "w") ? "checked" : "") + "> Weiblich</label>");
        out.println("                    <label><input type='radio' name='geschlecht' value='d' " + (isChecked(formData, "geschlecht", "d") ? "checked" : "") + "> Divers</label>");
        out.println("                </div>");
        if (hasError(errors, "geschlecht")) {
            out.println("                <span class='error-message'>" + errors.get("geschlecht") + "</span>");
        }
        out.println("            </div>");

        // Interessen (Checkboxen)
        out.println("            <div class='form-group" + (hasError(errors, "interessen") ? " error" : "") + "'>");
        out.println("                <label>Interessen * (mehrfach möglich)</label>");
        out.println("                <div class='checkbox-group'>");
        out.println("                    <label><input type='checkbox' name='interessen' value='programmierung' " + (isInterestChecked(formData, "programmierung") ? "checked" : "") + "> Programmierung</label>");
        out.println("                    <label><input type='checkbox' name='interessen' value='design' " + (isInterestChecked(formData, "design") ? "checked" : "") + "> Design</label>");
        out.println("                    <label><input type='checkbox' name='interessen' value='datenbanken' " + (isInterestChecked(formData, "datenbanken") ? "checked" : "") + "> Datenbanken</label>");
        out.println("                    <label><input type='checkbox' name='interessen' value='netzwerke' " + (isInterestChecked(formData, "netzwerke") ? "checked" : "") + "> Netzwerke</label>");
        out.println("                </div>");
        if (hasError(errors, "interessen")) {
            out.println("                <span class='error-message'>" + errors.get("interessen") + "</span>");
        }
        out.println("            </div>");

        // Land (Select)
        out.println("            <div class='form-group'>");
        out.println("                <label for='land'>Land</label>");
        out.println("                <select id='land' name='land'>");
        out.println("                    <option value=''>Bitte wählen...</option>");
        out.println("                    <option value='de' " + (isSelected(formData, "land", "de") ? "selected" : "") + ">Deutschland</option>");
        out.println("                    <option value='at' " + (isSelected(formData, "land", "at") ? "selected" : "") + ">Österreich</option>");
        out.println("                    <option value='ch' " + (isSelected(formData, "land", "ch") ? "selected" : "") + ">Schweiz</option>");
        out.println("                </select>");
        out.println("            </div>");

        // Nachricht (Textarea)
        out.println("            <div class='form-group" + (hasError(errors, "nachricht") ? " error" : "") + "'>");
        out.println("                <label for='nachricht'>Nachricht * (max. 500 Zeichen)</label>");
        out.println("                <textarea id='nachricht' name='nachricht' rows='5' ");
        out.println("                          placeholder='Ihre Nachricht...' required>" + getValue(formData, "nachricht") + "</textarea>");
        if (hasError(errors, "nachricht")) {
            out.println("                <span class='error-message'>" + errors.get("nachricht") + "</span>");
        }
        out.println("            </div>");

        // Newsletter (Checkbox)
        out.println("            <div class='form-group'>");
        out.println("                <label class='checkbox-single'>");
        out.println("                    <input type='checkbox' name='newsletter' value='ja' " + (getValue(formData, "newsletter").equals("checked") ? "checked" : "") + ">");
        out.println("                    Ich möchte den Newsletter erhalten");
        out.println("                </label>");
        out.println("            </div>");

        // Submit Button
        out.println("            <div class='form-actions'>");
        out.println("                <button type='submit' class='btn btn-primary'>Absenden</button>");
        out.println("                <button type='reset' class='btn btn-secondary'>Zurücksetzen</button>");
        out.println("            </div>");

        out.println("        </form>");

        // Info-Box
        out.println("        <div class='info-box'>");
        out.println("            <h3>ℹ️ Formular-Features</h3>");
        out.println("            <ul>");
        out.println("                <li>✅ Server-seitige Validierung</li>");
        out.println("                <li>✅ Error-Handling mit Fehlermeldungen</li>");
        out.println("                <li>✅ Session-basierte Datenspeicherung</li>");
        out.println("                <li>✅ Eingaben bleiben bei Fehler erhalten</li>");
        out.println("                <li>✅ UTF-8 Support für Umlaute</li>");
        out.println("                <li>✅ Responsive Design</li>");
        out.println("            </ul>");
        out.println("        </div>");
    }

    /**
     * CSS-Styles
     */
    private void outputCSS(PrintWriter out) {
        out.println("        * { margin: 0; padding: 0; box-sizing: border-box; }");
        out.println("        body {");
        out.println("            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;");
        out.println("            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);");
        out.println("            min-height: 100vh;");
        out.println("            padding: 20px;");
        out.println("        }");
        out.println("        .container { max-width: 800px; margin: 0 auto; }");
        out.println("        .header {");
        out.println("            text-align: center;");
        out.println("            color: white;");
        out.println("            margin-bottom: 30px;");
        out.println("        }");
        out.println("        .header h1 { font-size: 2.5rem; margin-bottom: 10px; }");
        out.println("        .back-link {");
        out.println("            color: white;");
        out.println("            text-decoration: none;");
        out.println("            display: inline-block;");
        out.println("            margin-top: 10px;");
        out.println("            opacity: 0.9;");
        out.println("        }");
        out.println("        .back-link:hover { opacity: 1; }");
        out.println("        .form, .success-message, .error-summary, .info-box {");
        out.println("            background: white;");
        out.println("            padding: 30px;");
        out.println("            border-radius: 15px;");
        out.println("            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);");
        out.println("            margin-bottom: 20px;");
        out.println("        }");
        out.println("        .success-message { background: #d4edda; border: 2px solid #28a745; }");
        out.println("        .success-message h2 { color: #155724; margin-bottom: 15px; }");
        out.println("        .error-summary { background: #f8d7da; border: 2px solid #dc3545; }");
        out.println("        .error-summary h3 { color: #721c24; margin-bottom: 10px; }");
        out.println("        .error-summary ul { margin-left: 20px; color: #721c24; }");
        out.println("        .form-group { margin-bottom: 20px; }");
        out.println("        .form-group.error input, .form-group.error textarea, .form-group.error select {");
        out.println("            border-color: #dc3545;");
        out.println("        }");
        out.println("        label { display: block; margin-bottom: 5px; font-weight: 600; color: #333; }");
        out.println("        input[type='text'], input[type='email'], input[type='number'], select, textarea {");
        out.println("            width: 100%;");
        out.println("            padding: 10px;");
        out.println("            border: 2px solid #ddd;");
        out.println("            border-radius: 8px;");
        out.println("            font-size: 1rem;");
        out.println("            transition: border-color 0.3s;");
        out.println("        }");
        out.println("        input:focus, textarea:focus, select:focus {");
        out.println("            outline: none;");
        out.println("            border-color: #667eea;");
        out.println("        }");
        out.println("        .radio-group, .checkbox-group { display: flex; flex-direction: column; gap: 8px; }");
        out.println("        .radio-group label, .checkbox-group label {");
        out.println("            font-weight: normal;");
        out.println("            display: flex;");
        out.println("            align-items: center;");
        out.println("            gap: 8px;");
        out.println("        }");
        out.println("        .checkbox-single { font-weight: normal; display: flex; align-items: center; gap: 8px; }");
        out.println("        .error-message { color: #dc3545; font-size: 0.875rem; margin-top: 5px; display: block; }");
        out.println("        .form-actions { display: flex; gap: 10px; margin-top: 30px; }");
        out.println("        .btn {");
        out.println("            padding: 12px 30px;");
        out.println("            border: none;");
        out.println("            border-radius: 8px;");
        out.println("            font-size: 1rem;");
        out.println("            cursor: pointer;");
        out.println("            transition: all 0.3s;");
        out.println("            text-decoration: none;");
        out.println("            display: inline-block;");
        out.println("        }");
        out.println("        .btn-primary {");
        out.println("            background: #667eea;");
        out.println("            color: white;");
        out.println("        }");
        out.println("        .btn-primary:hover { background: #5568d3; }");
        out.println("        .btn-secondary {");
        out.println("            background: #6c757d;");
        out.println("            color: white;");
        out.println("        }");
        out.println("        .btn-secondary:hover { background: #545b62; }");
        out.println("        .info-box { background: #d1ecf1; border: 2px solid #17a2b8; }");
        out.println("        .info-box h3 { color: #0c5460; margin-bottom: 10px; }");
        out.println("        .info-box ul { margin-left: 20px; color: #0c5460; }");
        out.println("        @media (max-width: 768px) {");
        out.println("            .form, .success-message { padding: 20px; }");
        out.println("            .form-actions { flex-direction: column; }");
        out.println("            .btn { width: 100%; }");
        out.println("        }");
    }

    // Hilfsmethoden
    private boolean hasError(Map<String, String> errors, String field) {
        return errors != null && errors.containsKey(field);
    }

    private String getValue(Map<String, String> formData, String field) {
        if (formData == null) return "";
        return formData.getOrDefault(field, "");
    }

    private boolean isChecked(Map<String, String> formData, String field, String value) {
        return getValue(formData, field).equals(value);
    }

    private boolean isSelected(Map<String, String> formData, String field, String value) {
        return getValue(formData, field).equals(value);
    }

    private boolean isInterestChecked(Map<String, String> formData, String value) {
        String interests = getValue(formData, "interessen");
        return interests.contains(value);
    }

    @Override
    public void destroy() {
        DebugLog.log("💀", "FormularServlet wird zerstört");
    }
}
