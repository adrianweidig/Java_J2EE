# 📚 Java EE 8 Lernprojekt - Bookstore Application

> ab 04.12.2025 keine Änderungen mehr - aktueller Freeze-Zustand und keine weitere Bearbeitung


[![Java](https://img.shields.io/badge/Java-8%20(Update%20144%2B)-orange.svg)](https://adoptium.net/)
[![Jakarta EE](https://img.shields.io/badge/Jakarta%20EE-8-blue.svg)](https://jakarta.ee/)
[![GlassFish](https://img.shields.io/badge/GlassFish-5.1.0-green.svg)](https://glassfish.org/)
[![Build Tool](https://img.shields.io/badge/Build-Gradle-02303A.svg)](https://gradle.org/)
[![License](https://img.shields.io/badge/License-Educational-yellow.svg)]()

> **Ein vollständiges Java EE 8 Lernprojekt** basierend auf dem Lehrgang "Professionell entwickeln mit Java EE 8" (
> Rheinwerk Verlag) und dem Bundeswehr-Lehrgang 8606 "Java für Fortgeschrittene Teil 2".

---

## 📖 Über dieses Projekt

Dieses Repository enthält eine **vollständige Java EE 8 Enterprise-Anwendung**, die als **Bücherverwaltungssystem (
Bookstore)** implementiert wurde. Das Projekt dient als:

- ✅ **Lernvorlage** für Java EE 8 Entwicklung
- ✅ **Praktische Referenz** für alle wichtigen Java EE 8 Technologien
- ✅ **Kommentierter Code** - jede Zeile ist ausführlich erklärt
- ✅ **Best Practices** für Enterprise-Anwendungen
- ✅ **Vollständige Dokumentation** von Setup bis Deployment

### 🎯 Projektziel

Das Ziel ist es, komplexe Java EE-Anwendungen unter Verwendung bereits bestehender Software-Komponenten (JavaBeans) zu
entwickeln und dabei:

- JavaBeans als intelligente Frontend-Komponenten zu Datenbankservern einzusetzen
- Verteilte Applikationen zu erstellen
- Die Java Persistence API (JPA) für komplexere Datenbank-Szenarien zu nutzen
- Moderne Enterprise-Patterns zu verstehen und anzuwenden

---

## 🏗️ Architektur

Das Projekt folgt dem **mehrschichtigen Aufbau** einer modernen Java EE-Anwendung:

```
┌─────────────────────────────────────────────┐
│   Präsentationsschicht (JSF / JSP)          │  ← Webseiten
├─────────────────────────────────────────────┤
│   Steuerungsschicht (Servlets / Beans)      │  ← Controller
├─────────────────────────────────────────────┤
│   Geschäftslogik (EJB / Services)           │  ← Business Logic
├─────────────────────────────────────────────┤
│   Persistenzschicht (JPA / JDBC)            │  ← Datenbankzugriff
├─────────────────────────────────────────────┤
│   Datenbank (H2 / Oracle)                   │  ← Datenspeicherung
└─────────────────────────────────────────────┘
```

### 📁 Projektstruktur

```
bookstore/
├── build.gradle                  # Gradle Build-Konfiguration
├── settings.gradle               # Gradle Projekt-Einstellungen
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/bookstore/
│   │   │       ├── model/        # Entities (JPA)
│   │   │       ├── repository/   # Datenbankzugriff
│   │   │       ├── service/      # Geschäftslogik (EJB)
│   │   │       ├── rest/         # REST APIs (JAX-RS)
│   │   │       └── web/          # Managed Beans (JSF)
│   │   ├── resources/
│   │   │   └── META-INF/
│   │   │       └── persistence.xml
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   ├── web.xml
│   │       │   ├── faces-config.xml
│   │       │   └── beans.xml
│   │       ├── index.xhtml       # Startseite
│   │       └── resources/        # CSS, Bilder, JS
│   └── test/
│       └── java/                 # Unit Tests (JUnit 5)
└── README.md                     # Diese Datei
```

---

## 🚀 Technologie-Stack

### 🔧 Kern-Technologien

| Technologie     | Version         | Beschreibung              |
|-----------------|-----------------|---------------------------|
| **Java SE**     | 8 (Update 144+) | Programmiersprache        |
| **Jakarta EE**  | 8.0             | Enterprise Edition        |
| **GlassFish**   | 5.1.0           | Application Server        |
| **Gradle**      | 7.x             | Build-Tool                |
| **H2 Database** | 2.2.224         | Embedded Datenbank (Test) |

### 📦 Java EE 8 Spezifikationen

Dieses Projekt nutzt folgende Java EE 8 APIs:

#### Web-Technologien

- **Servlets 4.0** - HTTP Request/Response Verarbeitung
- **JSP 2.3** - Java Server Pages
- **JSF 2.3** - Java Server Faces (Facelets)
- **WebSockets 1.1** - Bidirektionale Kommunikation
- **Expression Language 3.0** - Template-Sprache

#### Business-Komponenten

- **EJB 3.2** - Enterprise JavaBeans
- **CDI 2.0** - Dependency Injection
- **Bean Validation 2.0** - Validierung

#### Persistenz

- **JPA 2.2** - Java Persistence API
- **JDBC 4.2** - Datenbankverbindungen
- **JTA 1.2** - Transaktionsverwaltung

#### Web Services

- **JAX-RS 2.1** - RESTful Web Services
- **JAX-WS 2.2** - SOAP Web Services
- **JSON-P 1.1** - JSON Processing
- **JSON-B 1.0** - JSON Binding

#### Weitere APIs

- **JMS 2.0** - Messaging
- **JavaMail 1.6** - E-Mail

---

## 📋 Voraussetzungen

### System-Anforderungen

- **Betriebssystem:** Windows 10/11, macOS 10.14+, Linux (Ubuntu 20.04+)
- **RAM:** Mindestens 4 GB (8 GB empfohlen)
- **Festplatte:** 2 GB freier Speicherplatz

### Software-Anforderungen

| Software          | Version            | Download                                             |
|-------------------|--------------------|------------------------------------------------------|
| **JDK**           | Java 8 Update 144+ | [Adoptium](https://adoptium.net/)                    |
| **GlassFish**     | 5.1.0              | [GlassFish Download](https://glassfish.org/download) |
| **IntelliJ IDEA** | Ultimate 2023+     | [JetBrains](https://www.jetbrains.com/idea/)         |
| **Gradle**        | 7.x (via Wrapper)  | Automatisch enthalten                                |

### 🎓 Erforderliche Kenntnisse

Dieses Projekt setzt voraus:

✅ **Java-Grundlagen** (Lehrgang 8604)

- Objektorientierte Programmierung
- Vererbung, Interfaces, Polymorphie
- Collections, Exceptions, I/O

✅ **Java für Fortgeschrittene Teil 1** (Lehrgang 8605)

- JDBC
- Threads
- Netzwerkprogrammierung

✅ **Grundkenntnisse in:**

- HTML/CSS
- SQL
- HTTP-Protokoll

---

## 🛠️ Installation & Setup

### Schritt 1: Repository klonen

```bash
git clone https://github.com/dein-username/bookstore.git
cd bookstore
```

### Schritt 2: JDK installieren

**Windows:**

1. Download JDK 8 Update 144+ von [Adoptium](https://adoptium.net/)
2. Installieren mit Standard-Einstellungen
3. Umgebungsvariablen setzen:
   ```
   JAVA_HOME=C:\Program Files\Java\jdk1.8.0_xxx
   PATH=%JAVA_HOME%\bin;%PATH%
   ```
4. Prüfen: `java -version` in CMD

**Mac / Linux:**

```bash
# Ubuntu/Debian
sudo apt-get install openjdk-8-jdk

# macOS (via Homebrew)
brew install openjdk@8

# Prüfen
java -version
```

### Schritt 3: GlassFish Server installieren

1. **Download GlassFish 5.1.0:**
    - https://glassfish.org/download
    - "Eclipse GlassFish 5.1.0 - Jakarta EE Platform, 8"

2. **Entpacken:**
   ```bash
   # Windows
   Entpacken nach: C:\glassfish5
   
   # Mac / Linux
   unzip glassfish-5.1.0.zip
   sudo mv glassfish5 /opt/glassfish5
   ```

3. **Testen:**
   ```bash
   # Windows
   cd C:\glassfish5\bin
   asadmin version
   
   # Mac / Linux
   cd /opt/glassfish5/bin
   ./asadmin version
   ```

### Schritt 4: IntelliJ IDEA konfigurieren

1. **IntelliJ IDEA Ultimate öffnen**

2. **Projekt importieren:**
   ```
   File → Open → bookstore/build.gradle auswählen
   → "Open as Project"
   ```

3. **GlassFish Server hinzufügen:**
   ```
   Run → Edit Configurations
   → [+] → GlassFish Server → Local
   → Application Server: [Configure...]
   → GlassFish Home: C:\glassfish5 (oder /opt/glassfish5)
   → [OK]
   ```

4. **Deployment konfigurieren:**
   ```
   Tab: "Deployment"
   → [+] → Artifact
   → bookstore:war exploded
   → Application context: /bookstore
   → [Apply] → [OK]
   ```

### Schritt 5: Projekt bauen

```bash
# Gradle Wrapper nutzen (empfohlen)
./gradlew clean build

# Oder (Windows)
gradlew.bat clean build
```

### Schritt 6: Anwendung starten

**In IntelliJ:**

1. GlassFish-Konfiguration auswählen (oben rechts)
2. Grünen Play-Button ▶️ klicken
3. Browser öffnet automatisch: `http://localhost:8080/bookstore`

**Manuell (Kommandozeile):**

```bash
# GlassFish starten
cd /opt/glassfish5/bin
./asadmin start-domain

# WAR deployen
./asadmin deploy /pfad/zu/bookstore.war

# Im Browser öffnen
http://localhost:8080/bookstore
```

---

## 🎓 Lehrgangsthemen

Dieses Projekt deckt folgende Themen des Lehrgangs ab:

### 1️⃣ Einführung in J2EE

- Java EE 8 Überblick
- Architektur und Komponenten
- Mehrschichtige Anwendungen

### 2️⃣ Entwicklungsumgebung

- JDK Installation
- GlassFish Server Setup
- Eclipse/IntelliJ Integration
- Maven/Gradle Build-Tools

### 3️⃣ Servlets

- HTTP Request/Response
- Lebenszyklus
- Session Management
- Filter und Listener

### 4️⃣ Java Server Pages (JSP)

- JSP-Syntax
- Expression Language
- JSTL (Java Standard Tag Library)
- Custom Tags

### 5️⃣ Java Persistence API (JPA)

- Entity-Klassen
- EntityManager
- JPQL (Java Persistence Query Language)
- Relationships (1:1, 1:n, n:m)
- Transaktionen

### 6️⃣ Enterprise JavaBeans (EJB)

- Stateless Session Beans
- Stateful Session Beans
- Singleton Session Beans
- Message-Driven Beans

### 7️⃣ Java Server Faces (JSF)

- Facelets
- Managed Beans
- Navigation
- Konverter und Validatoren
- Ajax

### 8️⃣ Praktische Übungen

- CRUD-Operationen
- Formularverarbeitung
- Datenbankintegration
- REST APIs

---

## 📚 Lehrmaterial & Quellen

Dieses Projekt basiert auf:

### 📖 Hauptquelle

**"Professionell entwickeln mit Java EE 8"**

- Autor: Alexander Salvanos
- Verlag: Rheinwerk Verlag
- ISBN: 978-3-8362-5430-4
- [Verlagsseite](https://www.rheinwerk-verlag.de/4243)

### 🎓 Lehrgang

**Bundeswehr-Lehrgang 8606**

- "Java für Fortgeschrittene Teil 2"
- Programmierung mit Java J2EE
- Dauer: 10 Tage
- Bundesamt für Ausrüstung, Informationstechnik und Nutzung

### 🌐 Offizielle Dokumentationen

- [Jakarta EE 8 Specification](https://jakarta.ee/specifications/platform/8/)
- [GlassFish Documentation](https://glassfish.org/documentation)
- [JPA 2.2 Specification](https://jcp.org/en/jsr/detail?id=338)
- [JSF 2.3 Specification](https://jcp.org/en/jsr/detail?id=372)

---

## 💻 Verwendung

### Anwendung starten

```bash
# 1. GlassFish starten
cd /opt/glassfish5/bin
./asadmin start-domain

# 2. Browser öffnen
http://localhost:8080/bookstore
```

### Datenbank-Konsole (H2)

```
URL: http://localhost:8080/bookstore/h2-console
JDBC URL: jdbc:h2:mem:bookstore
Username: sa
Password: (leer)
```

### Admin-Konsole (GlassFish)

```
URL: http://localhost:4848
Username: admin
Password: (leer - Standard)
```

---

## 🧪 Tests ausführen

```bash
# Alle Tests ausführen
./gradlew test

# Nur spezifischen Test
./gradlew test --tests BookRepositoryTest

# Mit Testbericht
./gradlew test jacocoTestReport
```

Testberichte finden sich unter: `build/reports/tests/test/index.html`

---

## 📝 Code-Beispiele

### Entity-Klasse (JPA)

```java
/**
 * Book Entity - Repräsentiert ein Buch in der Datenbank
 * 
 * @Entity = Diese Klasse wird zu einer Datenbanktabelle
 * @Table = Name der Tabelle ist "BOOK"
 */
@Entity
@Table(name = "BOOK")
public class Book implements Serializable {
    
    // Primärschlüssel mit automatischer ID-Generierung
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Titel des Buches (max. 200 Zeichen, darf nicht null sein)
    @Column(length = 200, nullable = false)
    private String title;
    
    // Beschreibung (großer Text, bis 2000 Zeichen)
    @Column(length = 2000)
    private String description;
    
    // Preis (Dezimalzahl mit 2 Nachkommastellen)
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    
    // Many-to-One: Viele Bücher gehören zu einem Autor
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    
    // Getter und Setter...
}
```

### REST-Endpunkt (JAX-RS)

```java
/**
 * REST API für Bücher
 * 
 * Erreichbar unter: http://localhost:8080/bookstore/api/books
 */
@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    
    @Inject
    private BookService bookService;
    
    // GET /api/books → Alle Bücher abrufen
    @GET
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }
    
    // GET /api/books/{id} → Ein Buch nach ID abrufen
    @GET
    @Path("/{id}")
    public Response getBook(@PathParam("id") Long id) {
        Book book = bookService.findById(id);
        if (book != null) {
            return Response.ok(book).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    // POST /api/books → Neues Buch erstellen
    @POST
    public Response createBook(Book book) {
        bookService.create(book);
        return Response.status(Response.Status.CREATED).build();
    }
}
```

---

## 🤝 Beitragen

Verbesserungen und Beiträge sind willkommen!

1. Fork das Repository
2. Erstelle einen Feature-Branch (`git checkout -b feature/AmazingFeature`)
3. Committe deine Änderungen (`git commit -m 'Add some AmazingFeature'`)
4. Push zum Branch (`git push origin feature/AmazingFeature`)
5. Öffne einen Pull Request

---

## 🐛 Bekannte Probleme & Lösungen

### Problem: "Port 8080 already in use"

**Lösung:**

```bash
# Prozess finden
netstat -ano | findstr 8080

# Prozess beenden
taskkill /PID <PID> /F

# Oder anderen Port nutzen (in GlassFish Konfiguration)
```

### Problem: "java.lang.ClassNotFoundException"

**Lösung:**

```bash
# Gradle Dependencies neu laden
./gradlew clean build --refresh-dependencies

# In IntelliJ: File → Invalidate Caches / Restart
```

### Problem: "No valid GlassFish home"

**Lösung:**

- Prüfe Pfad (keine Leerzeichen!)
- Richtig entpackt? (Ordner muss `bin/`, `glassfish/`, `mq/` enthalten)
- GlassFish Home = Hauptordner (nicht `glassfish/` Subfolder!)

---

## 📧 Kontakt & Support

- **GitHub Issues:** [Repository Issues](https://github.com/dein-username/bookstore/issues)
- **Diskussionen:** [GitHub Discussions](https://github.com/dein-username/bookstore/discussions)

---

## 📄 Lizenz

Dieses Projekt dient ausschließlich zu **Bildungszwecken** und ist Teil eines Lehrgangs.

```
Educational License - Nur für Lernzwecke
© 2025 - Bundeswehr Lehrgang 8606
Basierend auf "Professionell entwickeln mit Java EE 8" (Rheinwerk Verlag)
```

---

## 🙏 Danksagungen

- **Alexander Salvanos** - Autor des Buchs "Professionell entwickeln mit Java EE 8"
- **Rheinwerk Verlag** - Veröffentlichung des Lehrbuchs
- **Bundesamt für Ausrüstung, IT und Nutzung der Bundeswehr** - Lehrgang 8606
- **Eclipse Foundation** - GlassFish Server
- **Jakarta EE Community** - Spezifikationen und Unterstützung

---

## 📊 Projekt-Status

✅ **Fertiggestellt:** Grundstruktur, Servlets, JSP, JPA
🚧 **In Arbeit:** EJB-Integration, JSF-Facelets
📅 **Geplant:** REST APIs, WebSockets, Security

---

## 🗺️ Roadmap

- [x] Projekt-Setup (Gradle, GlassFish)
- [x] Servlet-Implementierung
- [x] JSP-Views
- [x] JPA Entities & Repository
- [ ] EJB Session Beans
- [ ] JSF Facelets
- [ ] REST API (JAX-RS)
- [ ] Security (JAAS)
- [ ] Unit Tests (JUnit 5)
- [ ] Integration Tests

---

**Happy Coding! 🚀**

*"Es ist nicht genug, zu wissen, man muss auch anwenden."* - Johann Wolfgang von Goethe
