package gestionpedidosapp;

public class Producto {
    private static int contadorProductos = 0;
    
    private int id;
    private String nombre;
    private double precio;

    public Producto(int id, String nombre, double precio) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor a 0.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0.");
        }

        this.id = id;
        this.nombre = nombre.trim();
        this.precio = precio;
        contadorProductos++;
    }

    public Producto(String nombre, double precio) {
        this(++contadorProductos, nombre, precio);
    }

    public static int getContadorProductos() {
        return contadorProductos;
    }

    // Cálculo básico con 18% IGV
    public double calcularPrecioFinal() {
        return this.precio * 1.18;
    }

    // Aporte Integrante 4: Sobrecarga de método para cálculo con descuento comercial
    public double calcularPrecioFinal(double porcentajeDescuento) {
        if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            throw new IllegalArgumentException("El porcentaje de descuento debe estar entre 0 y 100.");
        }
        double precioDescontado = this.precio * (1 - (porcentajeDescuento / 100.0));
        return precioDescontado * 1.18;
    }

    public void mostrarDatos() {
        System.out.println(String.format("ID: %-4d | Nombre: %-22s | Precio base: S/. %-8.2f | Total c/IGV: S/. %-8.2f",
                id, nombre, precio, calcularPrecioFinal()));
    }

    public int getId() { return id; }
    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("El ID debe ser mayor a 0.");
        this.id = id;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) throw new IllegalArgumentException("El nombre no puede estar vacio.");
        this.nombre = nombre.trim();
    }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) {
        if (precio <= 0) throw new IllegalArgumentException("El precio debe ser mayor a 0.");
        this.precio = precio;
    }
}