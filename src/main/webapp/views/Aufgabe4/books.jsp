<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aufgabe 4.1: Bücherliste</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f5f5f5; margin: 0; padding: 20px; }
        .container { max-width: 1000px; margin: 0 auto; background: white; padding: 20px; border-radius: 8px; }
        h1 { color: #333; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th { background: #4CAF50; color: white; padding: 10px; text-align: left; }
        td { padding: 10px; border-bottom: 1px solid #ddd; }
        tr:hover { background: #f9f9f9; }
        a { color: #4CAF50; text-decoration: none; margin-right: 10px; }
        a:hover { text-decoration: underline; }
        .back { display: inline-block; margin-top: 20px; padding: 10px 20px; background: #4CAF50; color: white; border-radius: 4px; }
    </style>
</head>
<body>
<div class="container">
    <h1>Aufgabe 4.1: Bücherliste</h1>

    <c:choose>
        <c:when test="${empty books}">
            <p style="color: red;">Keine Bücher vorhanden.</p>
        </c:when>
        <c:otherwise>
            <p>Gefundene Bücher: <c:out value="${books.size()}"/></p>
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
                        <td><c:out value="${book.id}"/></td>
                        <td><c:out value="${book.title}"/></td>
                        <td><c:out value="${book.author}"/></td>
                        <td><c:out value="${book.price}"/></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/book?id=<c:out value="${book.id}"/>">Details</a>
                            <a href="${pageContext.request.contextPath}/cart?action=add&id=<c:out value="${book.id}"/>">+ Korb</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

    <a href="${pageContext.request.contextPath}/" class="back">← Zurück</a>
</div>
</body>
</html>