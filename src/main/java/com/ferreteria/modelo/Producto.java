package com.ferreteria.modelo;

import jakarta.persistence.*;

/**
 * Entity = una clase que el ORM sabe convertir en una tabla de base de datos.
 *
 * @Entity     le dice a Hibernate "esto se guarda en la BD"
 * @Table      nombre exacto de la tabla
 * @Id         marca el campo que identifica de forma única cada fila (PRIMARY KEY)
 * @Column     personaliza el nombre/tamaño de cada columna (opcional, Hibernate
 *             puede inferirlo solo, pero ser explícito es buena práctica)
 */
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @Column(name = "id", length = 10)
    private String id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "precio", nullable = false)
    private double precio;

    /**
     * Hibernate EXIGE un constructor vacío (sin argumentos).
     * Lo usa internamente para reconstruir objetos al leer de la BD.
     * Por eso es "protected": evita que lo usen por error en el resto del código,
     * pero Hibernate sí puede acceder a él.
     */
    protected Producto() {
    }

    public Producto(String id, String nombre, int cantidadInicial, double precio) {
        validarCantidad(cantidadInicial, "Cantidad inicial");
        validarPrecio(precio);
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidadInicial;
        this.precio = precio;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public double getPrecio() { return precio; }

    public void agregarStock(int cantidad) {
        validarCantidad(cantidad, "Cantidad a agregar");
        this.cantidad += cantidad;
    }

    public void descontarStock(int cantidad) {
        if (this.cantidad < cantidad) {
            throw new IllegalStateException(
                "Stock insuficiente para '" + nombre + "' (Disponible: "
                + this.cantidad + ", Solicitado: " + cantidad + ")");
        }
        this.cantidad -= cantidad;
    }

    private void validarCantidad(int valor, String campo) {
        if (valor <= 0) {
            throw new IllegalArgumentException("El " + campo + " debe ser mayor a cero.");
        }
    }

    private void validarPrecio(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-25s | %4d unid. | S/ %,.2f", id, nombre, cantidad, precio);
    }
}
