<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aufgabe 3.1 - Hello JSP</title>
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
            color: #667eea;
            border-bottom: 2px solid #667eea;
            padding-bottom: 10px;
        }
        p {
            background-color: #f9f9f9;
            padding: 15px;
            border-left: 4px solid #667eea;
            border-radius: 5px;
            margin: 15px 0;
        }
        strong {
            color: #333;
        }
        .info {
            color: #667eea;
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
    <h2>📅 Aufgabe 3.1: Hello JSP</h2>

    <%
        LocalDateTime now = LocalDateTime.now();
    %>

    <p>
        <strong>Aktuelle Serverzeit:</strong><br>
        <span class="info"><%= now %></span>
    </p>

    <p>
        <strong>Context-Path der WebApp:</strong><br>
        <span class="info"><%= request.getContextPath() %></span>
    </p>

    <a href="<%= request.getContextPath() %>/">← Zurück zum Menü</a>
</div>
</body>
</html>
