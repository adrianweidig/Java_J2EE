<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aufgabe 3.3 - Fehler</title>
    <style>
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
        }
        .card {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
            max-width: 600px;
            margin: 20px auto;
        }
        h2 {
            color: #dc3545;
            border-bottom: 2px solid #dc3545;
            padding-bottom: 10px;
        }
        .error-message {
            background-color: #f8d7da;
            padding: 15px;
            border-left: 4px solid #dc3545;
            border-radius: 5px;
            color: #721c24;
            margin: 15px 0;
        }
        .info-box {
            background-color: #fff3cd;
            padding: 15px;
            border-left: 4px solid #ffc107;
            border-radius: 5px;
            color: #856404;
            margin: 15px 0;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 15px;
            background: #667eea;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background 0.3s;
        }
        a:hover {
            background: #5568d3;
        }
    </style>
</head>
<body>
<div class="card">
    <h2>❌ Aufgabe 3.3: Fehler</h2>

    <div class="error-message">
        <strong>⚠️ Validierungsfehler:</strong><br>
        ${errorMessage}
    </div>

    <div class="info-box">
        <strong>ℹ️ Gültige ID-Bereiche:</strong><br>
        - ID muss eine Zahl sein<br>
        - ID muss zwischen 1 und 9999 liegen<br>
        - Beispiel: de>/aufgabe3/user?id=123</code>
    </div>

    <a href="<%= request.getContextPath() %>/">← Zurück zum Menü</a>
</div>
</body>
</html>
