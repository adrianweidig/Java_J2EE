<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KIT J2EE Projekt - Hauptmenü</title>
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
            max-width: 1200px;
            margin: 0 auto;
        }

        .header {
            text-align: center;
            color: white;
            margin-bottom: 40px;
            animation: fadeInDown 0.8s ease;
        }

        .header h1 {
            font-size: 3rem;
            margin-bottom: 10px;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
        }

        .header p {
            font-size: 1.2rem;
            opacity: 0.9;
        }

        .menu-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 25px;
            animation: fadeInUp 0.8s ease 0.2s both;
        }

        .menu-card {
            background: rgba(255, 255, 255, 0.95);
            border-radius: 15px;
            padding: 30px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
            transition: all 0.3s ease;
            cursor: pointer;
            text-decoration: none;
            color: inherit;
            display: block;
            position: relative;
            overflow: hidden;
        }

        .menu-card::before {
            content: '';
            position: absolute;
            top: 0;
            left: -100%;
            width: 100%;
            height: 100%;
            background: linear-gradient(90deg, transparent, rgba(102, 126, 234, 0.1), transparent);
            transition: left 0.5s ease;
        }

        .menu-card:hover::before {
            left: 100%;
        }

        .menu-card:hover {
            transform: translateY(-10px);
            box-shadow: 0 12px 48px rgba(0, 0, 0, 0.3);
        }

        .menu-card .icon {
            font-size: 3rem;
            margin-bottom: 15px;
            display: block;
        }

        .menu-card h3 {
            color: #667eea;
            font-size: 1.5rem;
            margin-bottom: 10px;
        }

        .menu-card p {
            color: #666;
            font-size: 0.95rem;
            line-height: 1.6;
        }

        .menu-card .badge {
            display: inline-block;
            background: #667eea;
            color: white;
            padding: 4px 12px;
            border-radius: 20px;
            font-size: 0.75rem;
            margin-top: 10px;
            font-weight: 600;
        }

        .menu-card.new .badge {
            background: #4CAF50;
        }

        .menu-card.coming-soon {
            opacity: 0.7;
            cursor: not-allowed;
        }

        .menu-card.coming-soon .badge {
            background: #999;
        }

        .footer {
            text-align: center;
            color: white;
            margin-top: 50px;
            padding: 20px;
            opacity: 0.8;
            animation: fadeIn 1s ease 0.5s both;
        }

        @keyframes fadeInDown {
            from {
                opacity: 0;
                transform: translateY(-30px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(30px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            .header h1 {
                font-size: 2rem;
            }

            .header p {
                font-size: 1rem;
            }

            .menu-grid {
                grid-template-columns: 1fr;
                gap: 20px;
            }

            .menu-card {
                padding: 25px;
            }
        }

        /* Dark Mode Support */
        @media (prefers-color-scheme: dark) {
            .menu-card {
                background: rgba(38, 40, 40, 0.95);
                color: #d4d4d4;
            }

            .menu-card h3 {
                color: #8b9aff;
            }

            .menu-card p {
                color: #b0b0b0;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>🎓 KIT J2EE Projekt</h1>
        <p>Willkommen zum Java Enterprise Edition Lernprojekt</p>
    </div>

    <div class="menu-grid">
        <!-- HelloServlet -->
        <a href="hello-servlet" class="menu-card">
            <span class="icon">🎉</span>
            <h3>Hello Servlet</h3>
            <p>Demo-Servlet mit Listener-Events, Session-Tracking und Live-Event-Log</p>
            <span class="badge new">Live Events</span>
        </a>

        <!-- FormularServlet -->
        <a href="formular-servlet" class="menu-card">
            <span class="icon">📝</span>
            <h3>Formular Servlet</h3>
            <p>Formular-Verarbeitung mit Validierung und Daten-Handling</p>
            <span class="badge">Formulare</span>
        </a>

        <!-- Listener Demo -->
        <a href="listener-demo" class="menu-card coming-soon">
            <span class="icon">👂</span>
            <h3>Listener Demo</h3>
            <p>Interaktive Demonstration aller Servlet-Listener</p>
            <span class="badge">Bald verfügbar</span>
        </a>

        <!-- Session Management -->
        <a href="session-demo" class="menu-card coming-soon">
            <span class="icon">🔐</span>
            <h3>Session Management</h3>
            <p>Session-Handling, Login/Logout und User-Tracking</p>
            <span class="badge">Bald verfügbar</span>
        </a>

        <!-- Database Demo -->
        <a href="database-demo" class="menu-card coming-soon">
            <span class="icon">🗄️</span>
            <h3>Datenbank Demo</h3>
            <p>JPA Entities, CRUD-Operationen und Persistence</p>
            <span class="badge">Bald verfügbar</span>
        </a>

        <!-- REST API -->
        <a href="api/docs" class="menu-card coming-soon">
            <span class="icon">🚀</span>
            <h3>REST API</h3>
            <p>RESTful Web Services mit JAX-RS</p>
            <span class="badge">Bald verfügbar</span>
        </a>

        <!-- JSF Demo -->
        <a href="jsf-demo.xhtml" class="menu-card coming-soon">
            <span class="icon">🎨</span>
            <h3>JSF Facelets</h3>
            <p>JavaServer Faces mit Facelets Templates</p>
            <span class="badge">Bald verfügbar</span>
        </a>

        <!-- EJB Demo -->
        <a href="ejb-demo" class="menu-card coming-soon">
            <span class="icon">🏢</span>
            <h3>EJB Session Beans</h3>
            <p>Enterprise JavaBeans und Business Logic</p>
            <span class="badge">Bald verfügbar</span>
        </a>

        <!-- Admin Console Link -->
        <a href="http://localhost:4848" target="_blank" class="menu-card" style="border: 2px solid #ffd700;">
            <span class="icon">⚙️</span>
            <h3>GlassFish Admin</h3>
            <p>Server-Administration und Monitoring</p>
            <span class="badge" style="background: #ffd700; color: #333;">External</span>
        </a>
    </div>

    <div class="footer">
        <p>
            <strong>KIT Java EE 8 Projekt</strong> |
            GlassFish 5.1.0 |
            Java 11 |
            Version 1.0-SNAPSHOT
        </p>
        <p style="margin-top: 10px; font-size: 0.9rem;">
            💡 Tipp: Prüfe die Server-Logs für detaillierte Listener-Events
        </p>
    </div>
</div>
</body>
</html>
