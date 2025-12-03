<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bücher - JPA/Hibernate + SQLite</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 30px 20px;
            color: #333;
        }

        .container {
            max-width: 1100px;
            margin: 0 auto;
        }

        .card {
            background: white;
            border-radius: 12px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.12);
            overflow: hidden;
            transition: box-shadow 0.3s ease;
        }

        .card:hover {
            box-shadow: 0 12px 48px rgba(0, 0, 0, 0.15);
        }

        .card-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 30px;
            text-align: center;
        }

        .card-header h1 {
            font-size: 2.2rem;
            font-weight: 700;
            margin-bottom: 8px;
            letter-spacing: -0.5px;
        }

        .card-header .subtitle {
            font-size: 0.95rem;
            opacity: 0.95;
            font-weight: 400;
        }

        .card-body {
            padding: 40px;
        }

        .header-actions {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
            flex-wrap: wrap;
            gap: 15px;
        }

        .back-link {
            display: inline-flex;
            align-items: center;
            gap: 8px;
            padding: 10px 16px;
            background: #f0f2f5;
            color: #333;
            text-decoration: none;
            border-radius: 6px;
            font-weight: 500;
            font-size: 0.9rem;
            transition: all 0.3s ease;
            border: 1px solid #e0e0e0;
        }

        .back-link:hover {
            background: #e8ebf0;
            border-color: #667eea;
            color: #667eea;
        }

        .stats {
            display: flex;
            gap: 20px;
            margin-bottom: 30px;
        }

        .stat-box {
            flex: 1;
            background: #f8f9fa;
            padding: 15px 20px;
            border-radius: 8px;
            border-left: 4px solid #667eea;
        }

        .stat-label {
            font-size: 0.85rem;
            color: #666;
            font-weight: 600;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        .stat-value {
            font-size: 1.8rem;
            color: #667eea;
            font-weight: 700;
            margin-top: 5px;
        }

        .alert {
            padding: 16px 20px;
            border-radius: 8px;
            margin-bottom: 20px;
            display: flex;
            align-items: center;
            gap: 12px;
            font-weight: 500;
        }

        .alert-info {
            background: #e7f3ff;
            border: 1px solid #91d5ff;
            color: #0050b3;
        }

        .alert-warning {
            background: #fffbe6;
            border: 1px solid #ffe58f;
            color: #ad6800;
        }

        .alert-success {
            background: #f6ffed;
            border: 1px solid #b7eb8f;
            color: #274e0e;
        }

        .table-wrapper {
            overflow-x: auto;
            border-radius: 8px;
            border: 1px solid #e0e0e0;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 0;
        }

        thead {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        }

        th {
            color: white;
            padding: 16px 20px;
            text-align: left;
            font-weight: 600;
            font-size: 0.9rem;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        td {
            padding: 16px 20px;
            border-bottom: 1px solid #f0f0f0;
            font-size: 0.95rem;
        }

        tbody tr {
            transition: all 0.2s ease;
            background: white;
        }

        tbody tr:hover {
            background: #f8f9ff;
            box-shadow: inset 0 0 10px rgba(102, 126, 234, 0.05);
        }

        tbody tr:last-child td {
            border-bottom: none;
        }

        .id-col {
            font-weight: 700;
            color: #667eea;
            width: 70px;
        }

        .title-col {
            font-weight: 600;
            color: #1a1a1a;
        }

        .author-col {
            color: #666;
            font-style: italic;
        }

        .price-col {
            font-weight: 700;
            color: #4CAF50;
            font-size: 1.05rem;
            text-align: right;
        }

        .empty-state {
            text-align: center;
            padding: 80px 30px;
        }

        .empty-state-icon {
            font-size: 4rem;
            margin-bottom: 20px;
            opacity: 0.5;
        }

        .empty-state h2 {
            font-size: 1.5rem;
            color: #333;
            margin-bottom: 10px;
            font-weight: 600;
        }

        .empty-state p {
            color: #666;
            font-size: 0.95rem;
        }

        .button-group {
            display: flex;
            gap: 12px;
            margin-top: 20px;
            justify-content: center;
        }

        .btn {
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 6px;
            font-weight: 600;
            font-size: 0.9rem;
            transition: all 0.3s ease;
            display: inline-block;
            border: none;
            cursor: pointer;
            text-align: center;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        .btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
        }

        .btn-primary {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }

        .btn-primary:hover {
            opacity: 0.9;
        }

        .btn-success {
            background: #4CAF50;
            color: white;
        }

        .btn-success:hover {
            background: #45a049;
        }

        .btn-secondary {
            background: #f0f2f5;
            color: #333;
            border: 1px solid #e0e0e0;
        }

        .btn-secondary:hover {
            background: #e8ebf0;
            border-color: #667eea;
        }

        .footer {
            text-align: center;
            margin-top: 40px;
            padding: 20px;
            color: white;
            font-size: 0.9rem;
            opacity: 0.8;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            .card-header h1 {
                font-size: 1.6rem;
            }

            .card-body {
                padding: 20px;
            }

            .header-actions {
                flex-direction: column;
            }

            .back-link {
                width: 100%;
                justify-content: center;
            }

            .stats {
                flex-direction: column;
            }

            th, td {
                padding: 12px 15px;
                font-size: 0.85rem;
            }

            .price-col {
                text-align: left;
            }

            .button-group {
                flex-direction: column;
            }

            .btn {
                width: 100%;
            }

            .id-col {
                width: 50px;
            }
        }

        /* Dark Mode */
        @media (prefers-color-scheme: dark) {
            body {
                background: linear-gradient(135deg, #1a1a2e 0%, #16213e 100%);
                color: #e0e0e0;
            }

            .card {
                background: #0f3460;
                color: #e0e0e0;
            }

            .back-link {
                background: #1a4d7a;
                color: #e0e0e0;
                border-color: #667eea;
            }

            .back-link:hover {
                background: #1f5a8f;
            }

            .stat-box {
                background: #1a4d7a;
                color: #e0e0e0;
            }

            td {
                border-bottom-color: #1a4d7a;
            }

            tbody tr {
                background: #0f3460;
            }

            tbody tr:hover {
                background: #1a4d7a;
            }

            .author-col {
                color: #aaa;
            }

            .empty-state h2 {
                color: #e0e0e0;
            }

            .empty-state p {
                color: #aaa;
            }

            .btn-secondary {
                background: #1a4d7a;
                color: #e0e0e0;
                border-color: #667eea;
            }

            .btn-secondary:hover {
                background: #1f5a8f;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="card">
        <div class="card-header">
            <h1>📚 Bücherverwaltung</h1>
            <p class="subtitle">JPA/Hibernate + SQLite Integration</p>
        </div>

        <div class="card-body">
            <div class="header-actions">
                <a href="<%= request.getContextPath() %>/" class="back-link">← Zurück zur Startseite</a>
                <div style="text-align: right; font-weight: 600; color: #667eea;">
                    <c:if test="${not empty books}">
                        ${books.size()} Buch<c:if test="${books.size() != 1}">bücher</c:if>
                    </c:if>
                </div>
            </div>

            <c:choose>
                <c:when test="${not empty books}">
                    <div class="stats">
                        <div class="stat-box">
                            <div class="stat-label">📊 Gesamtzahl</div>
                            <div class="stat-value">${books.size()}</div>
                        </div>
                    </div>

                    <div class="alert alert-info">
                        <span>ℹ️</span>
                        <span>Alle Daten werden über JPA/Hibernate aus SQLite geladen</span>
                    </div>

                    <div class="table-wrapper">
                        <table>
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Titel</th>
                                <th>Autor</th>
                                <th>Preis</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="book" items="${books}">
                                <tr>
                                    <td class="id-col">#${book.id}</td>
                                    <td class="title-col">${book.title}</td>
                                    <td class="author-col">${book.author}</td>
                                    <td class="price-col">${book.price}€</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="empty-state">
                        <div class="empty-state-icon">📭</div>
                        <h2>Keine Bücher gefunden</h2>
                        <p>Die Datenbank ist leer oder es traten Fehler auf.</p>
                        <div class="button-group">
                            <a href="<%= request.getContextPath() %>/" class="btn btn-secondary">← Zurück</a>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <div class="footer">
        <p>🔧 J2EE | JPA/Hibernate | SQLite | Java 1.8</p>
    </div>
</div>
</body>
</html>