<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Aufgabe 4.2: Buch-Details</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f5f5f5; margin: 0; padding: 20px; }
        .container { max-width: 600px; margin: 0 auto; background: white; padding: 20px; border-radius: 8px; }
        .error { background: #ffebee; color: #c62828; padding: 15px; border-radius: 4px; }
        .details { background: #f5f5f5; padding: 20px; margin-top: 20px; }
        .detail-row { margin: 10px 0; }
        a { color: #4CAF50; text-decoration: none; margin-right: 10px; }
        a:hover { text-decoration: underline; }
    </style>
</head>
<body>
<div class="container">
    <h1>Aufgabe 4.2: Buch-Details</h1>

    <c:choose>
        <c:when test="${not empty error}">
            <div class="error">
                <strong><c:out value="${error}"/></strong>
            </div>
            <p>
                <a href="${pageContext.request.contextPath}/book?id=1">Buch mit ID 1 anzeigen</a>
                <a href="${pageContext.request.contextPath}/books">Zur Liste</a>
            </p>
        </c:when>
        <c:otherwise>
            <div class="details">
                <div class="detail-row">
                    <strong>ID:</strong> <c:out value="${book.id}"/>
                </div>
                <div class="detail-row">
                    <strong>Titel:</strong> <c:out value="${book.title}"/>
                </div>
                <div class="detail-row">
                    <strong>Autor:</strong> <c:out value="${book.author}"/>
                </div>
                <div class="detail-row">
                    <strong>Preis:</strong> <c:out value="${book.price}"/> EUR
                </div>
            </div>
            <p>
                <a href="${pageContext.request.contextPath}/cart?action=add&id=${book.id}">In Korb</a>
                <a href="${pageContext.request.contextPath}/books">Zurück</a>
            </p>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
