<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Aufgabe 4.3: Auto-Suche</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f5f5f5; margin: 0; padding: 20px; }
        .container { max-width: 1100px; margin: 0 auto; background: white; padding: 20px; border-radius: 8px; }
        h1 { color: #333; }
        .search-form { background: #f9f9f9; padding: 20px; margin-bottom: 20px; border-radius: 4px; }
        .form-row { display: flex; gap: 20px; margin-bottom: 15px; flex-wrap: wrap; }
        .form-group { flex: 1; min-width: 200px; }
        label { display: block; font-weight: bold; margin-bottom: 5px; color: #333; }
        input { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; font-size: 14px; }
        input:focus { outline: none; border-color: #4CAF50; box-shadow: 0 0 5px rgba(76, 175, 80, 0.3); }
        .button-row { display: flex; gap: 10px; flex-wrap: wrap; }
        button { padding: 12px 24px; font-size: 14px; border: none; border-radius: 4px; cursor: pointer; font-weight: bold; transition: background 0.3s; }
        .btn-search { background: #4CAF50; color: white; }
        .btn-search:hover { background: #45a049; }
        .btn-reset { background: #f44336; color: white; }
        .btn-reset:hover { background: #da190b; }
        .error-message { background: #ffebee; color: #c62828; padding: 12px; margin: 10px 0; border-radius: 4px; border-left: 4px solid #c62828; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th { background: #4CAF50; color: white; padding: 12px; text-align: left; font-weight: bold; }
        td { padding: 12px; border-bottom: 1px solid #ddd; }
        tr:hover { background: #f9f9f9; }
        .result-info { background: #e8f5e9; color: #2e7d32; padding: 12px; margin: 20px 0 15px 0; border-radius: 4px; border-left: 4px solid #4CAF50; font-weight: bold; }
        .no-results { text-align: center; padding: 30px; color: #999; font-style: italic; }
        a { color: #4CAF50; text-decoration: none; display: inline-block; margin-top: 20px; padding: 10px 20px; background: #f0f0f0; border-radius: 4px; }
        a:hover { text-decoration: underline; background: #e8e8e8; }
    </style>
</head>
<body>
<div class="container">
    <h1>Aufgabe 4.3: Auto-Suche</h1>

    <form id="searchForm" method="GET" action="${pageContext.request.contextPath}/cars" class="search-form">
        <div class="form-row">
            <div class="form-group">
                <label for="brand">Marke:</label>
                <input type="text" id="brand" name="brand" value="${inputBrand}" placeholder="z.B. BMW"/>
            </div>
            <div class="form-group">
                <label for="yearFrom">Baujahr ab:</label>
                <input type="number" id="yearFrom" name="yearFrom" value="${inputYearFrom}" placeholder="z.B. 2018"/>
            </div>
            <div class="form-group">
                <label for="maxPrice">Maximalpreis (EUR):</label>
                <input type="number" id="maxPrice" name="maxPrice" step="0.01" value="${inputMaxPrice}" placeholder="z.B. 45000"/>
            </div>
        </div>
        <div class="button-row">
            <button type="submit" class="btn-search">Suchen</button>
            <button type="button" class="btn-reset" onclick="resetFormAndReload();">Zurücksetzen</button>
        </div>
    </form>

    <script>
        function resetFormAndReload() {
            // Alle Input-Felder löschen
            document.getElementById('brand').value = '';
            document.getElementById('yearFrom').value = '';
            document.getElementById('maxPrice').value = '';

            // Zur Startseite des Servlets navigieren (ohne Parameter)
            window.location.href = '${pageContext.request.contextPath}/cars';
        }
    </script>

    <c:forEach var="error" items="${errors}">
        <div class="error-message">
            <strong>⚠ Fehler:</strong> <c:out value="${error}"/>
        </div>
    </c:forEach>

    <c:choose>
        <c:when test="${not empty cars}">
            <div class="result-info">✓ <c:out value="${cars.size()}"/> Auto(s) gefunden</div>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Marke</th>
                    <th>Baujahr</th>
                    <th>Preis (EUR)</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="car" items="${cars}">
                    <tr>
                        <td><c:out value="${car.id}"/></td>
                        <td><c:out value="${car.brand}"/></td>
                        <td><c:out value="${car.year}"/></td>
                        <td><c:out value="${car.price}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:when test="${empty errors}">
            <div class="no-results">
                Starten Sie eine Suche oben, um verfügbare Autos anzuzeigen.
            </div>
        </c:when>
    </c:choose>

    <a href="${pageContext.request.contextPath}/">← Zurück zur Startseite</a>
</div>
</body>
</html>
