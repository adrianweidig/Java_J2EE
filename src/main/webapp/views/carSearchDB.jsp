<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Auto-Suche - SQLite DB</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 20px;
        }
        .container {
            max-width: 1000px;
            margin: 0 auto;
            background: white;
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
        }
        h1 {
            color: #667eea;
            margin-bottom: 10px;
        }
        .subtitle {
            color: #666;
            margin-bottom: 20px;
            font-size: 0.95rem;
        }
        .back-link {
            display: inline-block;
            margin-bottom: 20px;
            padding: 8px 16px;
            background: #f0f0f0;
            color: #333;
            text-decoration: none;
            border-radius: 5px;
            transition: all 0.3s ease;
        }
        .back-link:hover {
            background: #e0e0e0;
        }
        .search-form {
            background: #f9f9f9;
            padding: 20px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .form-group {
            display: flex;
            gap: 10px;
            margin-bottom: 10px;
        }
        .form-group input {
            flex: 1;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 1rem;
        }
        .form-group input:focus {
            outline: none;
            border-color: #667eea;
            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
        }
        .form-group button {
            padding: 10px 20px;
            background: #667eea;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 1rem;
            cursor: pointer;
            font-weight: 600;
            transition: all 0.3s ease;
        }
        .form-group button:hover {
            background: #764ba2;
        }
        .message {
            background: #fff3cd;
            border: 1px solid #ffc107;
            color: #856404;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .search-info {
            background: #d1ecf1;
            border: 1px solid #bee5eb;
            color: #0c5460;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th {
            background: #667eea;
            color: white;
            padding: 12px;
            text-align: left;
            font-weight: 600;
        }
        td {
            padding: 12px;
            border-bottom: 1px solid #eee;
        }
        tr:hover {
            background: #f9f9f9;
        }
        .price {
            color: #4CAF50;
            font-weight: 600;
        }
    </style>
</head>
<body>
<div class="container">
    <a href="${pageContext.request.contextPath}/" class="back-link">← Zurück zur Startseite</a>
    
    <h1>🚗 Auto-Suche (SQLite-DB)</h1>
    <p class="subtitle">Autos aus der SQLite-Datenbank durchsuchen</p>

    <div class="search-form">
        <form method="get" action="${pageContext.request.contextPath}/carsDB">
            <div class="form-group">
                <input type="text" name="brand" placeholder="Nach Marke suchen (z.B. Toyota, BMW)..." 
                       value="${fn:escapeXml(searchBrand)}" />
                <button type="submit">🔍 Suchen</button>
            </div>
        </form>
    </div>

    <c:if test="${not empty searchBrand}">
        <div class="search-info">
            ℹ️ Suchergebnisse für: <strong>"${fn:escapeXml(searchBrand)}"</strong>
        </div>
    </c:if>

    <c:if test="${empty cars}">
        <div class="message">
            ⚠️ Keine Autos gefunden 
            <c:if test="${not empty searchBrand}">
                für die Suche "<strong>${fn:escapeXml(searchBrand)}</strong>"
            </c:if>
        </div>
    </c:if>

    <c:if test="${not empty cars}">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Marke</th>
                    <th>Modell</th>
                    <th>Baujahr</th>
                    <th class="price">Preis (EUR)</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="car" items="${cars}">
                    <tr>
                        <td>${car.id}</td>
                        <td>${fn:escapeXml(car.brand)}</td>
                        <td>${fn:escapeXml(car.model)}</td>
                        <td>${car.year}</td>
                        <td class="price">${car.price}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>
