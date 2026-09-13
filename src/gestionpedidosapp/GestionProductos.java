package gestionpedidosapp;

import java.util.ArrayList;

public class GestionProductos {
    private ArrayList<Producto> listaProductos;

    public GestionProductos() {
        this.listaProductos = new ArrayList<>();
    }

    public void agregarProducto(Producto p) {
        if (p == null) {
            throw new IllegalArgumentException("No se puede registrar un producto nulo.");
        }
        this.listaProductos.add(p);
    }

    // Aporte Integrante 3: Sobrecarga del método agregarProducto
    public void agregarProducto(String nombre, double precio) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es requerido.");
        }
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero.");
        }
        Producto nuevo = new Producto(nombre, precio);
        this.agregarProducto(nuevo);
    }

    // Aporte Integrante 2: Validación de duplicidad con excepción de negocio
    public void registrarConValidacion(Producto p) throws Exception {
        if (p == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        if (buscarProducto(p.getId()) != null) {
            throw new Exception("Conflicto de negocio: Ya existe un producto con el ID " + p.getId());
        }
        this.agregarProducto(p);
    }

    public Producto buscarProducto(int id) {
        for (Producto p : listaProductos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

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

    // Aporte Integrante 5: Búsqueda y filtrado por rango de precios en la colección
    public ArrayList<Producto> buscarPorRangoPrecio(double min, double max) {
        if (min < 0 || max < min) {
            throw new IllegalArgumentException("El rango de precios es invalido (el minimo no puede superar al maximo).");
        }
        ArrayList<Producto> filtrados = new ArrayList<>();
        for (Producto p : listaProductos) {
            if (p.getPrecio() >= min && p.getPrecio() <= max) {
                filtrados.add(p);
            }
        }
        return filtrados;
    }

    // Aporte Integrante 7: Eliminación segura de un producto por ID con validación de existencia
    public boolean eliminarProducto(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID para eliminar debe ser mayor a 0.");
        }
        Producto encontrado = buscarProducto(id);
        if (encontrado == null) {
            throw new IllegalArgumentException("No se puede eliminar: No existe producto con el ID " + id);
        }
        return listaProductos.remove(encontrado);
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