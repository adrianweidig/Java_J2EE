<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Aufgabe 4.4: Warenkorb</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f5f5f5; margin: 0; padding: 20px; }
        .container { max-width: 1000px; margin: 0 auto; background: white; padding: 20px; border-radius: 8px; }
        h1 { color: #333; }
        .empty { text-align: center; padding: 40px; color: #999; }
        .empty p { font-size: 18px; margin-bottom: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th { background: #4CAF50; color: white; padding: 12px; text-align: left; font-weight: bold; }
        td { padding: 12px; border-bottom: 1px solid #ddd; }
        tr:hover { background: #f9f9f9; }
        .summary { background: #fff3cd; padding: 20px; margin-top: 20px; border-radius: 4px; border-left: 4px solid #ffc107; }
        .summary-row { display: flex; justify-content: space-between; margin: 10px 0; }
        .summary-label { font-weight: bold; }
        .summary-value { text-align: right; }
        .total { font-size: 1.4em; font-weight: bold; color: #4CAF50; margin-top: 15px; padding-top: 15px; border-top: 2px solid #ddd; }
        a { color: #4CAF50; text-decoration: none; margin-right: 15px; padding: 10px 16px; display: inline-block; background: #f0f0f0; border-radius: 4px; transition: all 0.3s; }
        a:hover { text-decoration: underline; background: #e8e8e8; }
        .btn-primary { background: #4CAF50; color: white; }
        .btn-primary:hover { background: #45a049; }
        .btn-danger { background: #f44336; color: white; }
        .btn-danger:hover { background: #da190b; }
        .btn-secondary { background: #757575; color: white; }
        .btn-secondary:hover { background: #616161; }
    </style>
</head>
<body>
<div class="container">
    <h1>Aufgabe 4.4: Warenkorb</h1>

    <c:choose>
        <c:when test="${empty cartItems}">
            <div class="empty">
                <p>Ihr Warenkorb ist noch leer.</p>
                <a href="${pageContext.request.contextPath}/books" class="btn-primary">Zu den Büchern</a>
            </div>
        </c:when>
        <c:otherwise>
            <table>
                <thead>
                <tr>
                    <th>Buch-ID</th>
                    <th>Titel</th>
                    <th>Preis pro Stück (EUR)</th>
                    <th>Menge</th>
                    <th>Gesamtpreis (EUR)</th>
                    <th>Aktion</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${cartItems}">
                    <tr>
                        <td><c:out value="${item.bookId}"/></td>
                        <td><c:out value="${item.title}"/></td>
                        <td><c:out value="${item.price}"/></td>
                        <td style="text-align: center; font-weight: bold;"><c:out value="${item.quantity}"/></td>
                        <td style="font-weight: bold; color: #4CAF50;"><c:out value="${item.subtotal}"/></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/cart?action=remove&id=${item.bookId}" class="btn-danger" style="display: inline-block;" onclick="return confirm('Buch aus Warenkorb entfernen?');">Entfernen</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="summary">
                <div class="summary-row">
                    <span class="summary-label">Anzahl unterschiedliche Bücher:</span>
                    <span class="summary-value"><c:out value="${fn:length(cartItems)}"/></span>
                </div>
                <div class="summary-row">
                    <span class="summary-label">Gesamtmenge (Stück):</span>
                    <span class="summary-value">
                        <c:set var="totalQty" value="0"/>
                        <c:forEach var="item" items="${cartItems}">
                            <c:set var="totalQty" value="${totalQty + item.quantity}"/>
                        </c:forEach>
                        <c:out value="${totalQty}"/>
                    </span>
                </div>
                <div class="total">
                    <span class="summary-label">Gesamtpreis:</span>
                    <span class="summary-value"><c:out value="${total}"/> EUR</span>
                </div>
            </div>

            <p>
                <a href="${pageContext.request.contextPath}/books" class="btn-primary">+ Weitere Bücher</a>
                <a href="${pageContext.request.contextPath}/cart?action=clear" class="btn-danger" onclick="return confirm('Warenkorb komplett leeren?');">Korb leeren</a>
                <a href="${pageContext.request.contextPath}/" class="btn-secondary">Zurück</a>
            </p>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
