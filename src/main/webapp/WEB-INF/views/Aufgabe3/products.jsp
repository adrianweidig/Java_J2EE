<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aufgabe 3.4 - Produkte</title>
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
            max-width: 800px;
            margin: 20px auto;
        }
        h2 {
            color: #667eea;
            border-bottom: 2px solid #667eea;
            padding-bottom: 10px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th {
            background-color: #667eea;
            color: white;
            padding: 12px;
            text-align: left;
            font-weight: 600;
        }
        td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
        }
        tr:hover {
            background-color: #f9f9f9;
        }
        tr:nth-child(even) {
            background-color: #f0f0f0;
        }
        .id {
            text-align: left;
            font-weight: bold;
            color: #667eea;
        }
        .price {
            text-align: left;
            font-weight: bold;
            color: #4CAF50;
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
    <h2>📦 Aufgabe 3.4: Produktliste</h2>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Produktname</th>
            <th>Preis (€)</th>
        </tr>
        </thead>
        <tbody>
        <%
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> products = (List<Map<String, Object>>) request.getAttribute("products");

            if (products != null && !products.isEmpty()) {
                for (Map<String, Object> product : products) {
        %>
        <tr>
            <td class="id"><%= product.get("id") %></td>
            <td><%= product.get("name") %></td>
            <td class="price"><%= String.format("%.2f", product.get("price")) %> €</td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="3" style="text-align: center; color: #999;">Keine Produkte verfügbar</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <a href="<%= request.getContextPath() %>/">← Zurück zum Menü</a>
</div>
</body>
</html>
