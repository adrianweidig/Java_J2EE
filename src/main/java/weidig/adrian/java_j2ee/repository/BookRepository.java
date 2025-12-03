package weidig.adrian.java_j2ee.repository;


import weidig.adrian.java_j2ee.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class BookRepository {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookstorePU");

    public static List<Book> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
        } finally {
            em.close();
        }
    }

    public static Book findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Book.class, id);
        } finally {
            em.close();
        }
    }

    public static void save(Book book) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (book.getId() == null) {
                em.persist(book);
            } else {
                em.merge(book);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }
}
