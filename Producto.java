public class Producto {
    private final String id;
    private final String nombre;
    private int cantidad;
    private double precio;

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

    

    private void validarCantidad(int valor, String campo) {
        if (valor <= 0) {
            throw new EntradaInvalidaException("El " + campo + " debe ser mayor a cero.");
        }
    }

    private void validarPrecio(double valor) {
        if (valor < 0) {
            throw new EntradaInvalidaException("El precio no puede ser negativo.");
        }
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-25s | %4d unid. | $%,.2f", id, nombre, cantidad, precio);
    }
}
