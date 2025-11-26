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

        /* Kategorie-Header */
        .category-header {
            color: white;
            font-size: 1.5rem;
            margin: 40px 0 20px 0;
            padding-bottom: 10px;
            border-bottom: 2px solid rgba(255, 255, 255, 0.3);
            animation: fadeInUp 0.8s ease;
        }

        .menu-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 25px;
            margin-bottom: 30px;
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

        .menu-card.exercise .badge {
            background: #ff9800;
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
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
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

    <!-- ═══════════════════════════════════════════════════════════ -->
    <!-- KATEGORIE: Aufgaben (Servlet Teil I) -->
    <!-- ═══════════════════════════════════════════════════════════ -->
    <h2 class="category-header">📚 Aufgaben - Java EE Servlets Teil I</h2>
    <div class="menu-grid">
        <!-- Aufgabe 1.1 -->
        <a href="hello-world" class="menu-card exercise">
            <span class="icon">👋</span>
            <h3>Aufgabe 1.1: HelloWorld</h3>
            <p>Einfaches GET-Servlet mit "Hello, World!" Ausgabe</p>
            <span class="badge">Aufgabe 1.1</span>
        </a>

        <!-- Aufgabe 1.2 -->
        <a href="greet-form.html" class="menu-card exercise">
            <span class="icon">🎯</span>
            <h3>Aufgabe 1.2: Greet Form</h3>
            <p>Formular mit Name-Eingabe und personalisierter Begrüßung</p>
            <span class="badge">Aufgabe 1.2</span>
        </a>

        <!-- Aufgabe 1.3 -->
        <a href="login-form.html" class="menu-card exercise">
            <span class="icon">🔐</span>
            <h3>Aufgabe 1.3: Login System</h3>
            <p>Login mit Request-Weiterleitung (forward) vom LoginServlet zum WelcomeServlet</p>
            <span class="badge">Aufgabe 1.3</span>
        </a>
    </div>

    <!-- AUFGABEN TEIL II -->
    <h2 class="category-header">📚 Aufgaben - Java EE Servlets Teil II</h2>
    <div class="menu-grid">
        <a href="info" class="menu-card exercise">
            <span class="icon">🛈</span>
            <h3>Aufgabe 2.1: Info</h3>
            <p>HTTP-Methode, Client-IP, User-Agent, URL, Serverzeit</p>
            <span class="badge">Aufgabe 2.1</span>
        </a>

        <a href="register" class="menu-card exercise">
            <span class="icon">📝</span>
            <h3>Aufgabe 2.2: Register</h3>
            <p>Formular mit Validierung (Name, Email)</p>
            <span class="badge">Aufgabe 2.2</span>
        </a>

        <a href="visits" class="menu-card exercise">
            <span class="icon">👁️</span>
            <h3>Aufgabe 2.3: Visits</h3>
            <p>Session-Besuchszähler mit Reset</p>
            <span class="badge">Aufgabe 2.3</span>
        </a>

        <a href="headers" class="menu-card exercise">
            <span class="icon">📋</span>
            <h3>Aufgabe 2.4: Headers</h3>
            <p>Request-Header lesen, Security-Header setzen</p>
            <span class="badge">Aufgabe 2.4</span>
        </a>

        <a href="user?id=1" class="menu-card exercise">
            <span class="icon">👤</span>
            <h3>Aufgabe 2.5: User</h3>
            <p>Parameter-Validierung mit Custom Error Page</p>
            <span class="badge">Aufgabe 2.5</span>
        </a>
    </div>


    <!-- ═══════════════════════════════════════════════════════════ -->
    <!-- KATEGORIE: Demo-Anwendungen -->
    <!-- ═══════════════════════════════════════════════════════════ -->
    <h2 class="category-header">🚀 Demo-Anwendungen</h2>
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
    </div>

    <!-- ═══════════════════════════════════════════════════════════ -->
    <!-- KATEGORIE: Erweiterte Features -->
    <!-- ═══════════════════════════════════════════════════════════ -->
    <h2 class="category-header">⚡ Erweiterte Features</h2>
    <div class="menu-grid">
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
            💡 Tipp: Alle Aufgaben-Servlets nutzen @WebServlet Annotationen
        </p>
    </div>
</div>
</body>
</html>
