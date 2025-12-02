package weidig.adrian.java_j2ee.Aufgabe2;

import weidig.adrian.java_j2ee.util.DebugConsole;
import weidig.adrian.java_j2ee.util.DebugLog;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * AUFGABE 2.3: VisitsServlet
 * - Zähler in Session
 * - Bei jedem Aufruf: count +1
 * - Parameter reset=1: Setzt count auf 0
 */
@WebServlet(name = "visitsServlet", value = "/visits")
public class VisitsServlet extends HttpServlet {

    @Override
    public void init() {
        DebugLog.log("🔧", "VisitsServlet initialisiert (Aufgabe 2.3)");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        DebugLog.log("👁️", "VisitsServlet.doGet()");

        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        Integer count = (Integer) session.getAttribute("visitCount");
        if (count == null) {
            count = 0;
        }

        String resetParam = request.getParameter("reset");
        if ("1".equals(resetParam)) {
            count = 0;
            DebugLog.log("🔄", "Zähler wurde zurückgesetzt");
        } else {
            count++;
        }

        session.setAttribute("visitCount", count);

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='de'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Aufgabe 2.3 - Besuchszähler</title>");
        out.println("    <style>");
        outputStyles(out);
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");
        out.println("        <h1>👁️ Besuchszähler</h1>");
        out.println("        <p class='subtitle'>Aufgabe 2.3</p>");
        out.println("        <div class='counter'>" + count + "</div>");
        out.println("        <p>Du hast diese Seite <strong>" + count + "x</strong> besucht!</p>");
        out.println("        <div class='links'>");
        out.println("            <a href='visits'>🔄 Erneut laden</a>");
        out.println("            <a href='visits?reset=1' class='reset'>🔴 Zurücksetzen</a>");
        out.println("            <a href='.' class='home'>Zur Startseite</a>");
        out.println("        </div>");
        out.println("    </div>");
        out.println("</body>");
        DebugConsole.render(out, request);
        out.println("</html>");
    }

    private void outputStyles(PrintWriter out) {
        out.println("        * { margin: 0; padding: 0; box-sizing: border-box; }");
        out.println("        body {");
        out.println("            font-family: 'Segoe UI', Arial, sans-serif;");
        out.println("            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);");
        out.println("            min-height: 100vh;");
        out.println("            display: flex;");
        out.println("            justify-content: center;");
        out.println("            align-items: center;");
        out.println("            padding: 20px;");
        out.println("        }");
        out.println("        .container {");
        out.println("            background: white;");
        out.println("            padding: 60px 80px;");
        out.println("            border-radius: 20px;");
        out.println("            box-shadow: 0 8px 32px rgba(0,0,0,0.2);");
        out.println("            text-align: center;");
        out.println("            max-width: 600px;");
        out.println("        }");
        out.println("        h1 { color: #667eea; margin-bottom: 10px; font-size: 2rem; }");
        out.println("        .subtitle { color: #999; margin-bottom: 30px; }");
        out.println("        .counter { font-size: 5rem; color: #764ba2; font-weight: bold; margin: 30px 0; }");
        out.println("        p { color: #666; margin-bottom: 30px; }");
        out.println("        .links { display: flex; gap: 15px; justify-content: center; flex-wrap: wrap; }");
        out.println("        a { padding: 12px 30px; background: #667eea; color: white; text-decoration: none; border-radius: 8px; font-weight: 600; }");
        out.println("        a:hover { background: #5568d3; }");
        out.println("        a.reset { background: #dc3545; }");
        out.println("        a.reset:hover { background: #c82333; }");
        out.println("        a.home { background: #999; }");
        out.println("        a.home:hover { background: #777; }");
    }

    @Override
    public void destroy() {
        DebugLog.log("💀", "VisitsServlet wird zerstört");
    }
}
