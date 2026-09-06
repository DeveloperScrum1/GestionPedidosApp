package gestionpedidosapp;

public class Producto {
    // Variable estatica para autogenerar correlativos y contar productos creados
    private static int contadorProductos = 0;
    
    private int id;
    private String nombre;
    private double precio;

    // Constructor principal
    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        contadorProductos++;
    }

    // Sobrecarga de constructor: crea un producto asignando ID automatico mediante static
    public Producto(String nombre, double precio) {
        this(++contadorProductos, nombre, precio);
    }

    // Metodo estatico para consultar el total acumulado
    public static int getContadorProductos() {
        return contadorProductos;
    }

    // Sobrecarga de metodos: calcular precio con IGV (18%)
    public double calcularPrecioFinal() {
        return this.precio * 1.18;
    }

    // Sobrecarga de metodos: calcular precio aplicando porcentaje de descuento
    public double calcularPrecioFinal(double porcentajeDescuento) {
        double subtotalConDescuento = this.precio * (1 - (porcentajeDescuento / 100));
        return subtotalConDescuento * 1.18;
    }

    public void mostrarDatos() {
        System.out.println("ID: " + id + " | Nombre: " + nombre + " | Precio base: S/. " + precio + " | Total (+18% IGV): S/. " + String.format("%.2f", calcularPrecioFinal()));
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
}