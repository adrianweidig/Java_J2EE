package weidig.adrian.java_j2ee.Aufgabe4;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import weidig.adrian.java_j2ee.Aufgabe4.model.Book;
import weidig.adrian.java_j2ee.Aufgabe4.repository.BookRepository;

/**
 * AUFGABE 4.4: CartServlet
 * Standard: Servlet 4.0 mit @WebServlet Annotation
 * Pattern: Session-Management mit innerer Klasse
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CART_SESSION_KEY = "cart";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Session holen/erstellen
        HttpSession session = request.getSession(true);

        // Warenkorb aus Session laden oder initialisieren
        @SuppressWarnings("unchecked")
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute(CART_SESSION_KEY, cart);
        }

        // Action verarbeiten
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            addToCart(request, cart);
        } else if ("remove".equals(action)) {
            removeFromCart(request, cart);
        } else if ("clear".equals(action)) {
            cart.clear();
        }

        // Session aktualisieren
        session.setAttribute(CART_SESSION_KEY, cart);

        // Gesamtpreis berechnen
        double total = 0;
        for (CartItem item : cart.values()) {
            total += item.getPrice() * item.getQuantity();
        }

        request.setAttribute("total", String.format("%.2f", total));
        request.setAttribute("cartItems", cart.values());

        request.getRequestDispatcher("/views/Aufgabe4/cart.jsp")
                .forward(request, response);
    }

    private void addToCart(HttpServletRequest request, Map<Integer, CartItem> cart) {
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int bookId = Integer.parseInt(idParam);
                BookRepository bookRepo = new BookRepository();
                Book book = bookRepo.getBook(bookId);

                if (book != null) {
                    if (cart.containsKey(bookId)) {
                        CartItem item = cart.get(bookId);
                        item.setQuantity(item.getQuantity() + 1);
                    } else {
                        CartItem item = new CartItem(book.getId(), book.getTitle(),
                                book.getPrice(), 1);
                        cart.put(bookId, item);
                    }
                }
            } catch (NumberFormatException e) {
                // Ignorieren
            }
        }
    }

    private void removeFromCart(HttpServletRequest request, Map<Integer, CartItem> cart) {
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int bookId = Integer.parseInt(idParam);
                cart.remove(bookId);
            } catch (NumberFormatException e) {
                // Ignorieren
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Innere Klasse: CartItem
     * Speichert eine Position im Warenkorb
     */
    public static class CartItem {
        private int bookId;
        private String title;
        private double price;
        private int quantity;

        public CartItem(int bookId, String title, double price, int quantity) {
            this.bookId = bookId;
            this.title = title;
            this.price = price;
            this.quantity = quantity;
        }

        public int getBookId() { return bookId; }
        public String getTitle() { return title; }
        public double getPrice() { return price; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
        public double getSubtotal() { return price * quantity; }
    }
}
