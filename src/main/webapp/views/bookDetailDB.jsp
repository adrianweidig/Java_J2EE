<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buch-Details - SQLite DB</title>
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
            max-width: 700px;
            margin: 0 auto;
            background: white;
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
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
        .error-message {
            background: #f8d7da;
            border: 1px solid #f5c6cb;
            color: #721c24;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .success-message {
            background: #d4edda;
            border: 1px solid #c3e6cb;
            color: #155724;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
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
        .book-details {
            background: #f9f9f9;
            padding: 20px;
            border-radius: 5px;
            border-left: 4px solid #667eea;
            margin: 20px 0;
        }
        .detail-row {
            display: flex;
            padding: 10px 0;
            border-bottom: 1px solid #eee;
        }
        .detail-row:last-child {
            border-bottom: none;
        }
        .detail-label {
            font-weight: 600;
            color: #667eea;
            width: 120px;
        }
        .detail-value {
            flex: 1;
            color: #333;
        }
        .price {
            color: #4CAF50;
            font-size: 1.3rem;
            font-weight: 600;
        }
        .actions {
            display: flex;
            gap: 10px;
            margin-top: 30px;
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
        .btn-back {
            background: #f0f0f0;
            color: #333;
        }
        .btn-back:hover {
            background: #e0e0e0;
        }
        .btn-cart {
            background: #4CAF50;
            color: white;
        }
        .btn-cart:hover {
            background: #45a049;
        }
    </style>
</head>
<body>
<div class="container">
    <a href="${pageContext.request.contextPath}/booksDB" class="back-link">← Zurück zur Bücherliste</a>
    
    <h1>📖 Buch-Details (SQLite-DB)</h1>
    <p class="subtitle">Detailinformationen aus der SQLite-Datenbank</p>

    <c:if test="${not empty error}">
        <div class="error-message">
            ❌ ${fn:escapeXml(error)}
        </div>
    </c:if>

    <c:if test="${not empty book}">
        <div class="book-details">
            <div class="detail-row">
                <span class="detail-label">Buch-ID:</span>
                <span class="detail-value">${book.id}</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Titel:</span>
                <span class="detail-value">${fn:escapeXml(book.title)}</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Autor:</span>
                <span class="detail-value">${fn:escapeXml(book.author)}</span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Preis:</span>
                <span class="detail-value price">${book.price} EUR</span>
            </div>
        </div>

        <div class="actions">
            <a href="${pageContext.request.contextPath}/booksDB" class="btn btn-back">← Zur Liste</a>
            <a href="${pageContext.request.contextPath}/cartDB?action=add&id=${book.id}" class="btn btn-cart">🛒 In Warenkorb</a>
        </div>
    </c:if>

    <c:if test="${empty book && empty error}">
        <div class="success-message">
            ℹ️ Laden Sie ein Buch, um Details anzuzeigen.
        </div>
    </c:if>
</div>
</body>
</html>
