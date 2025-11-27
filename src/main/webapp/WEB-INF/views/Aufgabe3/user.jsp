<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aufgabe 3.3 - User Detail</title>
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
            color: #4CAF50;
            border-bottom: 2px solid #4CAF50;
            padding-bottom: 10px;
        }
        .user-info {
            background-color: #f0f8f4;
            padding: 15px;
            border-left: 4px solid #4CAF50;
            border-radius: 5px;
            margin: 10px 0;
        }
        .label {
            font-weight: bold;
            color: #333;
        }
        .value {
            color: #4CAF50;
            font-weight: bold;
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
    <h2>✅ Aufgabe 3.3: User Detail</h2>

    <div class="user-info">
        <div class="label">👤 User ID:</div>
        <div class="value">${userId}</div>
    </div>

    <div class="user-info">
        <div class="label">📝 Benutzername:</div>
        <div class="value">${userName}</div>
    </div>

    <div class="user-info">
        <div class="label">📧 E-Mail:</div>
        <div class="value">${email}</div>
    </div>

    <a href="<%= request.getContextPath() %>/">← Zurück zum Menü</a>
</div>
</body>
</html>
