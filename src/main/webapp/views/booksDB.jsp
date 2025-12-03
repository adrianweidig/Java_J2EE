<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bücherliste - SQLite DB</title>
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
        .message {
            background: #fff3cd;
            border: 1px solid #ffc107;
            color: #856404;
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
        .action-links {
            display: flex;
            gap: 10px;
        }
        .btn {
            padding: 6px 12px;
            text-decoration: none;
            border-radius: 5px;
            font-size: 0.9rem;
            transition: all 0.3s ease;
            display: inline-block;
        }
        .btn-details {
            background: #667eea;
            color: white;
        }
        .btn-details:hover {
            background: #764ba2;
        }
        .btn-cart {
            background: #4CAF50;
            color: white;
        }
        .btn-cart:hover {
            background: #45a049;
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

    <h1>📚 Bücherliste (SQLite-DB)</h1>
    <p class="subtitle">Alle Bücher aus der SQLite-Datenbank (j2ee.sqlite)</p>

    <c:if test="${empty books}">
        <div class="message">
            ⚠️ Keine Bücher vorhanden.
        </div>
    </c:if>

    <c:if test="${not empty books}">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Titel</th>
                    <th>Autor</th>
                    <th>Preis (EUR)</th>
                    <th>Aktionen</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="book" items="${books}">
                    <tr>
                        <td>${book.id}</td>
                        <td>${fn:escapeXml(book.title)}</td>
                        <td>${fn:escapeXml(book.author)}</td>
                        <td class="price">${book.price}</td>
                        <td>
                            <div class="action-links">
                                <a href="${pageContext.request.contextPath}/bookDB?id=${book.id}" class="btn btn-details">Details</a>
                                <a href="${pageContext.request.contextPath}/cartDB?action=add&id=${book.id}" class="btn btn-cart">+ Korb</a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>
