<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aufgabe 4.4: Warenkorb</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f5f5f5; margin: 0; padding: 20px; }
        .container { max-width: 1000px; margin: 0 auto; background: white; padding: 20px; border-radius: 8px; }
        h1 { color: #333; }
        .empty { text-align: center; padding: 40px; color: #999; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th { background: #4CAF50; color: white; padding: 10px; text-align: left; }
        td { padding: 10px; border-bottom: 1px solid #ddd; }
        tr:hover { background: #f9f9f9; }
        .summary { background: #fff3cd; padding: 15px; border-radius: 4px; margin-top: 20px; }
        .total { font-size: 1.2em; font-weight: bold; color: #4CAF50; margin-top: 10px; }
        a { color: #4CAF50; text-decoration: none; padding: 8px 16px; display: inline-block; margin-right: 10px; }
        a:hover { text-decoration: underline; }
        .button-group { margin-top: 20px; }
        .btn-danger { background: #f44336; color: white; }
        .btn-danger:hover { background: #da190b; }
    </style>
</head>
<body>
<div class="container">
    <h1>Aufgabe 4.4: Warenkorb</h1>

    <c:choose>
        <c:when test="${empty cartItems}">
            <div class="empty">
                <p>Ihr Warenkorb ist noch leer.</p>
                <a href="${pageContext.request.contextPath}/books">Zu den Büchern</a>
            </div>
        </c:when>
        <c:otherwise>
            <table>
                <thead>
                <tr>
                    <th>Buch-ID</th>
                    <th>Titel</th>
                    <th>Preis (EUR)</th>
                    <th>Menge</th>
                    <th>Gesamt</th>
                    <th>Aktion</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${cartItems}">
                    <tr>
                        <td><c:out value="${item.bookId}"/></td>
                        <td><c:out value="${item.title}"/></td>
                        <td><c:out value="${item.price}"/></td>
                        <td><c:out value="${item.quantity}"/></td>
                        <td><c:out value="${item.subtotal}"/></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/cart?action=remove&id=<c:out value="${item.bookId}"/>" class="btn-danger" onclick="return confirm('Entfernen?');">Entfernen</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="summary">
                <p>Anzahl Positionen: <strong><c:out value="${cartItems.size()}"/></strong></p>
                <div class="total">Gesamtpreis: <c:out value="${total}"/> EUR</div>
            </div>

            <div class="button-group">
                <a href="${pageContext.request.contextPath}/books">+ Weitere Bücher</a>
                <a href="${pageContext.request.contextPath}/cart?action=clear" onclick="return confirm('Warenkorb leeren?');">Korb leeren</a>
                <a href="${pageContext.request.contextPath}/">Zurück</a>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
