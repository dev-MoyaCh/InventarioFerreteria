public class ProductoNoEncontradoException extends RuntimeException {
    public ProductoNoEncontradoException(String id) {
        super("Producto no encontrado con ID: " + id);
    }
}

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String nombre, int disponible, int solicitado) {
        super("Stock insuficiente para '" + nombre + "' (Disponible: " + disponible + ", Solicitado: " + solicitado + ")");
    }
}

public class EntradaInvalidaException extends RuntimeException {
    public EntradaInvalidaException(String mensaje) {
        super(mensaje);
    }
}
