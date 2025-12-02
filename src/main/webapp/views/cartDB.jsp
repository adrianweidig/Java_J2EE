<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Warenkorb - SQLite DB</title>
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
        .empty-message {
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
        .amount {
            text-align: center;
        }
        .price {
            color: #4CAF50;
            font-weight: 600;
        }
        .remove-btn {
            background: #dc3545;
            color: white;
            padding: 6px 12px;
            text-decoration: none;
            border-radius: 5px;
            display: inline-block;
            transition: all 0.3s ease;
        }
        .remove-btn:hover {
            background: #c82333;
        }
        .summary {
            background: #f9f9f9;
            padding: 20px;
            border-radius: 5px;
            margin-top: 30px;
            border-left: 4px solid #667eea;
        }
        .summary-row {
            display: flex;
            justify-content: space-between;
            padding: 10px 0;
            border-bottom: 1px solid #eee;
        }
        .summary-row:last-child {
            border-bottom: none;
        }
        .summary-row.total {
            font-size: 1.2rem;
            font-weight: 600;
            color: #667eea;
        }
        .summary-row.total span:last-child {
            color: #4CAF50;
        }
        .actions {
            display: flex;
            gap: 10px;
            margin-top: 20px;
        }
        .btn {
            flex: 1;
            padding: 12px 20px;
            text-decoration: none;
            border-radius: 5px;
            font-size: 1rem;
            transition: all 0.3s ease;
            display: inline-block;
            text-align: center;
            border: none;
            cursor: pointer;
            font-weight: 600;
        }
        .btn-shopping {
            background: #667eea;
            color: white;
        }
        .btn-shopping:hover {
            background: #764ba2;
        }
        .btn-clear {
            background: #dc3545;
            color: white;
        }
        .btn-clear:hover {
            background: #c82333;
        }
    </style>
</head>
<body>
<div class="container">
    <a href="${pageContext.request.contextPath}/" class="back-link">← Zurück zur Startseite</a>
    
    <h1>🛒 Warenkorb (SQLite-DB)</h1>
    <p class="subtitle">Ihr aktueller Warenkorb mit Büchern aus der SQLite-Datenbank</p>

    <c:if test="${empty cartItems}">
        <div class="empty-message">
            ℹ️ Ihr Warenkorb ist noch leer. 
            <a href="${pageContext.request.contextPath}/booksDB" style="color: #856404; text-decoration: underline;">Zu den Büchern</a>
        </div>
    </c:if>

    <c:if test="${not empty cartItems}">
        <table>
            <thead>
                <tr>
                    <th>Buch-ID</th>
                    <th>Titel</th>
                    <th>Preis pro Stück (EUR)</th>
                    <th class="amount">Menge</th>
                    <th class="price">Gesamtpreis (EUR)</th>
                    <th>Aktion</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${cartItems}">
                    <tr>
                        <td>${item.bookId}</td>
                        <td>${fn:escapeXml(item.title)}</td>
                        <td class="price">${item.price}</td>
                        <td class="amount">${item.quantity}</td>
                        <td class="price">${item.subtotal}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/cartDB?action=remove&id=${item.bookId}" class="remove-btn">Entfernen</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="summary">
            <div class="summary-row total">
                <span>Gesamtpreis:</span>
                <span>${total} EUR</span>
            </div>
        </div>

        <div class="actions">
            <a href="${pageContext.request.contextPath}/booksDB" class="btn btn-shopping">📚 Weiter einkaufen</a>
            <a href="${pageContext.request.contextPath}/cartDB?action=clear" class="btn btn-clear">🗑️ Warenkorb leeren</a>
        </div>
    </c:if>
</div>
</body>
</html>
