package weidig.adrian.java_j2ee.util;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.List;

/**
 * Wiederverwendbare Debug-Konsole für Servlets
 *
 * VERWENDUNG:
 *
 * 1. In doGet/doPost vor dem HTML-Ende:
 *    DebugConsole.render(out, request);
 *
 * 2. Automatisch:
 *    - Ausklappbare Konsole unten rechts
 *    - Zeigt alle DebugLog Events
 *    - CSS und JavaScript enthalten
 *    - Clear-Funktion integriert
 */
public class DebugConsole {

    /**
     * Rendert die komplette Debug-Konsole
     *
     * @param out PrintWriter vom Response
     * @param request HttpServletRequest für Clear-Action
     */
    public static void render(PrintWriter out, HttpServletRequest request) {
        // Clear-Funktion
        if ("clearDebug".equals(request.getParameter("action"))) {
            DebugLog.clear();
            DebugLog.log("🧹", "Debug-Log manuell geleert");
        }

        renderCSS(out);
        renderHTML(out);
        renderJavaScript(out);
    }

    /**
     * CSS-Styles für die Konsole
     */
    private static void renderCSS(PrintWriter out) {
        out.println("<style>");
        out.println("/* Debug Console Styles */");
        out.println(".debug-console-container {");
        out.println("    position: fixed;");
        out.println("    bottom: 20px;");
        out.println("    right: 20px;");
        out.println("    z-index: 9999;");
        out.println("}");
        out.println(".debug-toggle {");
        out.println("    background: #1e1e1e;");
        out.println("    color: white;");
        out.println("    border: 2px solid #007acc;");
        out.println("    padding: 12px 20px;");
        out.println("    border-radius: 25px;");
        out.println("    cursor: pointer;");
        out.println("    font-size: 14px;");
        out.println("    font-weight: 600;");
        out.println("    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);");
        out.println("    transition: all 0.3s;");
        out.println("    font-family: 'Segoe UI', sans-serif;");
        out.println("}");
        out.println(".debug-toggle:hover {");
        out.println("    background: #007acc;");
        out.println("    transform: translateY(-2px);");
        out.println("    box-shadow: 0 6px 16px rgba(0, 0, 0, 0.4);");
        out.println("}");
        out.println(".debug-console {");
        out.println("    display: none;");
        out.println("    position: fixed;");
        out.println("    bottom: 80px;");
        out.println("    right: 20px;");
        out.println("    width: 600px;");
        out.println("    max-height: 500px;");
        out.println("    background: #1e1e1e;");
        out.println("    border: 2px solid #007acc;");
        out.println("    border-radius: 12px;");
        out.println("    box-shadow: 0 8px 32px rgba(0, 0, 0, 0.5);");
        out.println("    flex-direction: column;");
        out.println("    font-family: 'Consolas', 'Monaco', monospace;");
        out.println("    font-size: 13px;");
        out.println("}");
        out.println(".debug-header {");
        out.println("    background: #252526;");
        out.println("    padding: 12px 15px;");
        out.println("    border-bottom: 1px solid #3c3c3c;");
        out.println("    display: flex;");
        out.println("    justify-content: space-between;");
        out.println("    align-items: center;");
        out.println("}");
        out.println(".debug-header h3 {");
        out.println("    margin: 0;");
        out.println("    color: #4ec9b0;");
        out.println("    font-size: 16px;");
        out.println("}");
        out.println(".debug-actions {");
        out.println("    display: flex;");
        out.println("    gap: 8px;");
        out.println("}");
        out.println(".debug-btn {");
        out.println("    background: transparent;");
        out.println("    color: #cccccc;");
        out.println("    border: 1px solid #3c3c3c;");
        out.println("    padding: 6px 12px;");
        out.println("    border-radius: 4px;");
        out.println("    cursor: pointer;");
        out.println("    font-size: 12px;");
        out.println("    transition: all 0.2s;");
        out.println("    font-family: 'Segoe UI', sans-serif;");
        out.println("}");
        out.println(".debug-btn:hover {");
        out.println("    background: #3c3c3c;");
        out.println("    border-color: #007acc;");
        out.println("    color: white;");
        out.println("}");
        out.println(".debug-content {");
        out.println("    flex: 1;");
        out.println("    overflow-y: auto;");
        out.println("    padding: 10px;");
        out.println("    color: #d4d4d4;");
        out.println("}");
        out.println(".debug-event {");
        out.println("    padding: 6px 10px;");
        out.println("    margin: 3px 0;");
        out.println("    border-left: 3px solid #3c3c3c;");
        out.println("    background: rgba(255, 255, 255, 0.03);");
        out.println("    border-radius: 3px;");
        out.println("    font-size: 12px;");
        out.println("    line-height: 1.4;");
        out.println("    word-break: break-word;");
        out.println("}");
        out.println(".event-start { border-left-color: #4ec9b0; background: rgba(78, 201, 176, 0.1); }");
        out.println(".event-stop { border-left-color: #f48771; background: rgba(244, 135, 113, 0.1); }");
        out.println(".event-error { border-left-color: #f14c4c; background: rgba(241, 76, 76, 0.1); }");
        out.println(".event-success { border-left-color: #89d185; background: rgba(137, 209, 133, 0.1); }");
        out.println(".event-add { border-left-color: #dcdcaa; background: rgba(220, 220, 170, 0.1); }");
        out.println(".event-remove { border-left-color: #ce9178; background: rgba(206, 145, 120, 0.1); }");
        out.println(".event-session { border-left-color: #c586c0; background: rgba(197, 134, 192, 0.1); }");
        out.println(".event-request { border-left-color: #569cd6; background: rgba(86, 156, 214, 0.1); }");
        out.println(".debug-empty {");
        out.println("    text-align: center;");
        out.println("    padding: 40px;");
        out.println("    color: #858585;");
        out.println("    font-style: italic;");
        out.println("}");
        out.println(".debug-footer {");
        out.println("    background: #252526;");
        out.println("    padding: 8px 15px;");
        out.println("    border-top: 1px solid #3c3c3c;");
        out.println("    color: #858585;");
        out.println("    font-size: 11px;");
        out.println("}");
        out.println(".debug-content::-webkit-scrollbar {");
        out.println("    width: 10px;");
        out.println("}");
        out.println(".debug-content::-webkit-scrollbar-track {");
        out.println("    background: #1e1e1e;");
        out.println("}");
        out.println(".debug-content::-webkit-scrollbar-thumb {");
        out.println("    background: #3c3c3c;");
        out.println("    border-radius: 5px;");
        out.println("}");
        out.println(".debug-content::-webkit-scrollbar-thumb:hover {");
        out.println("    background: #007acc;");
        out.println("}");
        out.println("@media (max-width: 768px) {");
        out.println("    .debug-console {");
        out.println("        width: calc(100vw - 40px);");
        out.println("        right: 20px;");
        out.println("        left: 20px;");
        out.println("        max-height: 400px;");
        out.println("    }");
        out.println("    .debug-console-container {");
        out.println("        right: 10px;");
        out.println("        bottom: 10px;");
        out.println("    }");
        out.println("    .debug-toggle {");
        out.println("        padding: 10px 16px;");
        out.println("        font-size: 12px;");
        out.println("    }");
        out.println("}");
        out.println("</style>");
    }

    /**
     * HTML-Struktur der Konsole
     */
    private static void renderHTML(PrintWriter out) {
        out.println("<!-- Debug Console -->");
        out.println("<div class='debug-console-container'>");
        out.println("    <button class='debug-toggle' onclick='toggleDebugConsole()'>🐛 Debug Console</button>");
        out.println("    <div class='debug-console' id='debugConsole'>");
        out.println("        <div class='debug-header'>");
        out.println("            <h3>🔍 Live Debug Log</h3>");
        out.println("            <div class='debug-actions'>");
        out.println("                <button onclick='refreshDebugLog()' class='debug-btn' title='Neu laden'>🔄</button>");
        out.println("                <button onclick='clearDebugLog()' class='debug-btn' title='Log löschen'>🧹</button>");
        out.println("                <button onclick='toggleDebugConsole()' class='debug-btn' title='Schließen'>✖</button>");
        out.println("            </div>");
        out.println("        </div>");
        out.println("        <div class='debug-content' id='debugContent'>");

        // Events ausgeben
        List<String> events = DebugLog.getEvents();
        if (events.isEmpty()) {
            out.println("            <div class='debug-empty'>📭 Keine Events vorhanden</div>");
        } else {
            for (String event : events) {
                String eventClass = getEventClass(event);
                out.println("            <div class='" + eventClass + "'>" + escapeHtml(event) + "</div>");
            }
        }

        out.println("        </div>");
        out.println("        <div class='debug-footer'>");
        out.println("            <span>Events: " + events.size() + " / 100</span>");
        out.println("            <span style='float: right;'>Auto-Refresh: 5s</span>");
        out.println("        </div>");
        out.println("    </div>");
        out.println("</div>");
    }

    /**
     * JavaScript-Funktionen
     */
    private static void renderJavaScript(PrintWriter out) {
        out.println("<script>");
        out.println("let debugConsoleOpen = false;");
        out.println("let autoRefreshInterval = null;");
        out.println("");
        out.println("function toggleDebugConsole() {");
        out.println("    const console = document.getElementById('debugConsole');");
        out.println("    debugConsoleOpen = !debugConsoleOpen;");
        out.println("    console.style.display = debugConsoleOpen ? 'flex' : 'none';");
        out.println("    ");
        out.println("    if (debugConsoleOpen) {");
        out.println("        scrollDebugToBottom();");
        out.println("        if (!autoRefreshInterval) {");
        out.println("            autoRefreshInterval = setInterval(refreshDebugLog, 5000);");
        out.println("        }");
        out.println("    } else {");
        out.println("        if (autoRefreshInterval) {");
        out.println("            clearInterval(autoRefreshInterval);");
        out.println("            autoRefreshInterval = null;");
        out.println("        }");
        out.println("    }");
        out.println("}");
        out.println("");
        out.println("function refreshDebugLog() {");
        out.println("    const currentUrl = new URL(window.location.href);");
        out.println("    currentUrl.searchParams.delete('action');");
        out.println("    ");
        out.println("    fetch(currentUrl.toString())");
        out.println("        .then(response => response.text())");
        out.println("        .then(html => {");
        out.println("            const parser = new DOMParser();");
        out.println("            const doc = parser.parseFromString(html, 'text/html');");
        out.println("            const newContent = doc.getElementById('debugContent');");
        out.println("            if (newContent) {");
        out.println("                const oldScroll = document.getElementById('debugContent').scrollTop;");
        out.println("                const oldHeight = document.getElementById('debugContent').scrollHeight;");
        out.println("                document.getElementById('debugContent').innerHTML = newContent.innerHTML;");
        out.println("                const newHeight = document.getElementById('debugContent').scrollHeight;");
        out.println("                if (newHeight > oldHeight) {");
        out.println("                    scrollDebugToBottom();");
        out.println("                } else {");
        out.println("                    document.getElementById('debugContent').scrollTop = oldScroll;");
        out.println("                }");
        out.println("            }");
        out.println("        })");
        out.println("        .catch(err => console.error('Debug refresh failed:', err));");
        out.println("}");
        out.println("");
        out.println("function clearDebugLog() {");
        out.println("    if (confirm('Debug-Log wirklich löschen?')) {");
        out.println("        const url = new URL(window.location.href);");
        out.println("        url.searchParams.set('action', 'clearDebug');");
        out.println("        window.location.href = url.toString();");
        out.println("    }");
        out.println("}");
        out.println("");
        out.println("function scrollDebugToBottom() {");
        out.println("    const content = document.getElementById('debugContent');");
        out.println("    if (content) {");
        out.println("        content.scrollTop = content.scrollHeight;");
        out.println("    }");
        out.println("}");
        out.println("</script>");
    }

    /**
     * Bestimmt die CSS-Klasse basierend auf Event-Inhalt
     */
    private static String getEventClass(String event) {
        String baseClass = "debug-event";

        if (event.contains("🚀") || event.contains("GESTARTET")) {
            return baseClass + " event-start";
        } else if (event.contains("🛑") || event.contains("STOPP")) {
            return baseClass + " event-stop";
        } else if (event.contains("❌") || event.contains("FEHLER") || event.contains("Fehler")) {
            return baseClass + " event-error";
        } else if (event.contains("✅") || event.contains("ERFOLG") || event.contains("erfolgreich")) {
            return baseClass + " event-success";
        } else if (event.contains("📝") || event.contains("HINZUGEFÜGT")) {
            return baseClass + " event-add";
        } else if (event.contains("🗑️") || event.contains("ENTFERNT")) {
            return baseClass + " event-remove";
        } else if (event.contains("👤") || event.contains("SESSION")) {
            return baseClass + " event-session";
        } else if (event.contains("🌐") || event.contains("REQUEST")) {
            return baseClass + " event-request";
        }

        return baseClass;
    }

    /**
     * HTML-Escaping
     */
    private static String escapeHtml(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
