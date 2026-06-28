package com.ferreteria.dao;

import com.ferreteria.modelo.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * DAO = Data Access Object.
 * Es la única clase que "habla" directamente con la base de datos.
 * El resto del programa nunca escribe SQL: solo llama a estos métodos.
 *
 * EntityManager = el objeto de JPA/Hibernate que ejecuta las operaciones reales
 * (guardar, buscar, actualizar, eliminar) contra la base de datos.
 */
public class ProductoDAO {

    private final EntityManagerFactory emf;

    public ProductoDAO() {
        // La contraseña de MySQL NUNCA se escribe en el código ni en persistence.xml
        // (eso se sube a GitHub). Se lee de una variable de entorno del sistema.
        String password = System.getenv("MYSQL_PASSWORD");
        if (password == null) {
            throw new IllegalStateException(
                "Falta la variable de entorno MYSQL_PASSWORD. " +
                "Configúrala antes de ejecutar el programa (ver README)."
            );
        }

        Map<String, String> overrides = new HashMap<>();
        overrides.put("jakarta.persistence.jdbc.password", password);

        // Lee la configuración de persistence.xml (la unidad "inventarioPU")
        // y sobreescribe solo la contraseña con el valor de la variable de entorno.
        this.emf = Persistence.createEntityManagerFactory("inventarioPU", overrides);
    }

    /**
     * Guarda un producto nuevo, o actualiza uno existente si ya tiene ese ID.
     * "merge" hace ambas cosas: si el ID no existe, inserta (INSERT);
     * si ya existe, actualiza (UPDATE).
     */
    public void guardar(Producto producto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(producto);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Busca un producto por su ID. Retorna Optional porque puede no existir.
     */
    public Optional<Producto> buscarPorId(String id) {
        EntityManager em = emf.createEntityManager();
        try {
            Producto p = em.find(Producto.class, id);
            return Optional.ofNullable(p);
        } finally {
            em.close();
        }
    }

    /**
     * Retorna todos los productos guardados en la base de datos.
     * JPQL es como SQL, pero trabaja con clases Java en vez de tablas.
     */
    public List<Producto> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Producto p ORDER BY p.id", Producto.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Elimina un producto por su ID. No hace nada si no existe.
     */
    public void eliminar(String id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Producto p = em.find(Producto.class, id);
            if (p != null) {
                em.remove(p);
            }
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Cierra la conexión con la base de datos. Llamar al terminar el programa.
     */
    public void cerrar() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
