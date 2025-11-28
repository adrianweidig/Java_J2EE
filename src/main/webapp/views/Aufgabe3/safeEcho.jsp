<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aufgabe 3.2 - Safe Echo</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background: #f5f5f5;
        }
        .card {
            background: white;
            padding: 25px;
            border-radius: 10px;
            max-width: 800px;
            margin: 20px auto;
            box-shadow: 0 8px 24px rgba(0,0,0,0.15);
        }
        h2 {
            color: #667eea;
            margin-top: 0;
        }
        h3 {
            margin-top: 20px;
            color: #333;
        }
        .box {
            background: #f0f0f0;
            padding: 12px;
            border-radius: 6px;
            margin: 8px 0 15px 0;
            font-family: monospace;
            min-height: 30px;
            white-space: pre-wrap;
            word-break: break-all;
        }
        .unescaped {
            border-left: 4px solid #e91e63;
        }
        .escaped {
            border-left: 4px solid #4caf50;
        }
        .rules {
            background: #eefaf1;
            padding: 12px;
            border-radius: 6px;
            margin: 15px 0;
            font-family: monospace;
        }
        .rules div {
            margin: 3px 0;
        }
        .buttons {
            margin: 15px 0;
        }
        .buttons a {
            display: inline-block;
            margin: 5px 8px 5px 0;
            padding: 8px 14px;
            background: #ff9800;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 0.9rem;
        }
        .buttons a:hover {
            background: #e68900;
        }
        .back a {
            display: inline-block;
            margin-top: 20px;
            padding: 8px 14px;
            background: #667eea;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .back a:hover {
            background: #5568d3;
        }
        .info {
            background: #e3f2fd;
            padding: 10px;
            border-radius: 6px;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>

<%!
    // 1. Aufgabe: Escaping-Methode
    private String escapeHtml(String s) {
        if (s == null) return "";

        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;");
    }

    // Hilfsfunktion NUR für die Anzeige,
    // damit &lt; im Browser auch als "&lt;" sichtbar bleibt
    private String showAsText(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
%>

<%
    // WICHTIG: Erklärung für doppeltes Escaping!
    // ===========================================
    // 1. escapeHtml(q)      → '&' wird zu "&amp;" (logisch korrekt für HTML-Ausgabe)
    // 2. showAsText(escapedQ) → "&amp;" wird zu "&amp;amp;" (für Sichtbarkeit im Browser)
    //
    // BEISPIEL: q = "<script>"
    //   Schritt 1: escapeHtml("<script>")  → "&lt;script&gt;"
    //   Schritt 2: showAsText("&lt;script&gt;") → "&amp;lt;script&amp;gt;"
    //
    // Browser zeigt dann: <script> (als TEXT, nicht als Code!)
    // OHNE Schritt 2 würde Browser "&lt;" als "<" interpretieren!

    String q = request.getParameter("q");
    String escapedQ = escapeHtml(q);      // logisch korrektes Escaping
    String visibleEscapedQ = showAsText(escapedQ); // für Anzeige der Entities
%>

<div class="card">
    <h2>🛡️ Aufgabe 3.2: Safe Echo</h2>

    <div class="info">
        Diese Seite zeigt den Query-Parameter <strong>q</strong> einmal
        <strong>unescaped</strong> und einmal <strong>HTML-escaped</strong>.
    </div>

    <h3>1. Aktueller Wert von q</h3>
    <p><strong>Rohwert (aus URL):</strong></p>
    <div class="box">
        <%= (q == null ? "" : showAsText(q)) %></div>

    <h3>2. Ausgabe im Browser</h3>
    <p><strong>Unescaped (gefährlich, nur zu Demo-Zwecken):</strong></p>
    <div class="box unescaped">
        <%= (q == null ? "" : q) %></div>

    <p><strong>Escaped (sicher, mit escapeHtml(q)) – Entities sichtbar gemacht:</strong></p>
    <div class="box escaped">
        <%= visibleEscapedQ %></div>

    <h3>3. Escaping-Regeln</h3>
    <div class="rules">
        <div>&amp;  → &amp;amp;</div>
        <div>&lt;  → &amp;lt;</div>
        <div>&gt;  → &amp;gt;</div>
        <div>"   → &amp;quot;</div>
        <div>'   → &#x27;</div>
    </div>

    <h3>4. Test-Buttons</h3>
    <div class="buttons">
        <!-- Normaler Text -->
        <a href="safeEcho.jsp?q=normaler%20Text">q = normaler Text</a>

        <!-- Alle relevanten Zeichen: <>&"' -->
        <a href="safeEcho.jsp?q=%3C%3E%26%22%27">
            q = &lt;&gt;&amp;"'
        </a>

        <!-- Script-ähnlicher Payload -->
        <a href="safeEcho.jsp?q=%3Cscript%3Ealert%28%27XSS%27%29%3C%2Fscript%3E">
            q = &lt;script&gt;alert('XSS')&lt;/script&gt;
        </a>
    </div>

    <div class="back">
        <a href="../../index.jsp">← Zurück zum Menü</a>
    </div>
</div>

</body>
</html>
