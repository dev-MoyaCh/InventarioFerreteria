import java.util.*;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class Inventario {
    private final Map<String, Producto> productos = new HashMap<>();
    private final List<Venta> historialVentas = new ArrayList<>();

    public void registrarIngreso(String id, String nombre, int cantidad, double precio) {
        Producto existente = productos.get(id);
        if (existente != null) {
            existente.agregarStock(cantidad);
        } else {
            productos.put(id, new Producto(id, nombre, cantidad, precio));
        }
    }

    public void registrarSalida(String id, int cantidad) {
        Producto p = obtenerProducto(id);
        p.descontarStock(cantidad);
    }

    /**
     * Registra venta de forma transaccional: valida TODO antes de modificar.
     */
    public Venta registrarVenta(List<ItemPedido> items) {
        // 1. Fase de validación (sin mutar estado)
        Map<String, Integer> stockRequerido = new HashMap<>();
        for (ItemPedido item : items) {
            Producto p = obtenerProducto(item.id);
            int nuevoRequerido = stockRequerido.getOrDefault(item.id, 0) + item.cantidad;
            if (p.getCantidad() < nuevoRequerido) {
                throw new StockInsuficienteException(p.getNombre(), p.getCantidad(), nuevoRequerido);
            }
            stockRequerido.put(item.id, nuevoRequerido);
        }

        // 2. Fase de ejecución (solo si pasa validación)
        Venta nuevaVenta = new Venta(generarIdVenta(), LocalDate.now());
        for (ItemPedido item : items) {
            Producto p = productos.get(item.id);
            p.descontarStock(item.cantidad);
            nuevaVenta.agregarDetalle(p.getId(), p.getNombre(), item.cantidad, p.getPrecio());
        }

        historialVentas.add(nuevaVenta);
        return nuevaVenta;
    }

    public Producto buscarPorId(String id) {
        return productos.get(id);
    }

    public List<Producto> obtenerInventario() {
        return Collections.unmodifiableList(new ArrayList<>(productos.values()));
    }

    public List<Venta> obtenerHistorialVentas() {
        return Collections.unmodifiableList(historialVentas);
    }

    private Producto obtenerProducto(String id) {
        return Optional.ofNullable(productos.get(id))
                .orElseThrow(() -> new ProductoNoEncontradoException(id));
    }

    private String generarIdVenta() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    // Clase auxiliar para armar pedidos
    public static class ItemPedido {
        public final String id;
        public final int cantidad;
        public ItemPedido(String id, int cantidad) {
            this.id = id;
            this.cantidad = cantidad;
        }
    }
}
