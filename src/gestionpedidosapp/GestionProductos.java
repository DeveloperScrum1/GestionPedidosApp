package gestionpedidosapp;

import java.util.ArrayList;

public class GestionProductos {
    // Coleccion principal requerida
    private ArrayList<Producto> listaProductos;

    public GestionProductos() {
        this.listaProductos = new ArrayList<>();
    }

    // Operacion sobre la coleccion: Registrar con validacion
    public void agregarProducto(Producto p) {
        if (p == null) {
            throw new IllegalArgumentException("No se puede registrar un producto nulo.");
        }
        this.listaProductos.add(p);
    }

    // ============================================================
    // SOBRECARGA REQUERIDA: Metodos de busqueda sobre la coleccion
    // ============================================================

    // Metodo Sobrecargado 1: Busqueda exacta por ID numerico
    public Producto buscarProducto(int id) {
        for (Producto p : listaProductos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // Metodo Sobrecargado 2: Busqueda por Nombre (coincidencia de texto)
    public Producto buscarProducto(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }
        for (Producto p : listaProductos) {
            if (p.getNombre().equalsIgnoreCase(nombre.trim())) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public boolean estaVacia() {
        return listaProductos.isEmpty();
    }

    public int totalRegistrados() {
        return listaProductos.size();
    }

    public double calcularSubtotalGeneral() {
        double subtotal = 0.0;
        for (Producto p : listaProductos) {
            subtotal += p.getPrecio();
        }
        return subtotal;
    }

    public double calcularTotalGeneralConIgv() {
        double total = 0.0;
        for (Producto p : listaProductos) {
            total += p.calcularPrecioFinal();
        }
        return total;
    }
}