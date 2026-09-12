package gestionpedidosapp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestionProductos gestion = new GestionProductos();
        int opcion = 0;

        // Precarga de prueba
        try {
            gestion.agregarProducto(new Producto("Laptop Gamer", 3450.00));
            gestion.agregarProducto(new Producto("Mouse Optico", 55.00));
        } catch (Exception e) {
            System.out.println("Error en precarga: " + e.getMessage());
        }

        do {
            System.out.println("\n==========================================");
            System.out.println(" SISTEMA DE GESTION DE PEDIDOS V3 - UPN ");
            System.out.println("==========================================");
            System.out.println("1. Registrar producto");
            System.out.println("2. Listar productos de la coleccion");
            System.out.println("3. Buscar producto por ID (Sobrecarga A)");
            System.out.println("4. Buscar producto por Nombre (Sobrecarga B)");
            System.out.println("5. Calcular importe total acumulado");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion (1-6): ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer
            } catch (InputMismatchException e) {
                System.out.println(">> Error: Debe ingresar un numero entero para la opcion.");
                scanner.nextLine();
                opcion = 0;
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n--- REGISTRO DE PRODUCTO ---");
                    try {
                        System.out.print("Ingrese nombre: ");
                        String nombre = scanner.nextLine();

                        System.out.print("Ingrese precio unitario (S/.): ");
                        double precio = scanner.nextDouble();
                        scanner.nextLine();

                        // Construccion que dispara IllegalArgumentException si los datos son invalidos
                        Producto nuevo = new Producto(nombre, precio);
                        gestion.agregarProducto(nuevo);
                        System.out.println(">> Producto registrado con exito.");

                    } catch (InputMismatchException e) {
                        System.out.println(">> [Error de Formato]: Ingrese un valor numerico valido para el precio.");
                        scanner.nextLine();
                    } catch (IllegalArgumentException e) {
                        System.out.println(">> [Error de Validacion]: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("\n--- LISTA DE PRODUCTOS ---");
                    if (gestion.estaVacia()) {
                        System.out.println("No hay productos registrados en la coleccion.");
                    } else {
                        for (Producto p : gestion.getListaProductos()) {
                            p.mostrarDatos();
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n--- BUSQUEDA POR ID (Sobrecarga int) ---");
                    try {
                        System.out.print("Ingrese el ID numerico a buscar: ");
                        int idBuscar = scanner.nextInt();
                        scanner.nextLine();

                        Producto encontradoId = gestion.buscarProducto(idBuscar);
                        if (encontradoId != null) {
                            System.out.println(">> Producto encontrado:");
                            encontradoId.mostrarDatos();
                        } else {
                            System.out.println(">> No existe producto con el ID: " + idBuscar);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println(">> [Error de Entrada]: El ID debe ser un numero entero.");
                        scanner.nextLine();
                    }
                    break;

                case 4:
                    System.out.println("\n--- BUSQUEDA POR NOMBRE (Sobrecarga String) ---");
                    System.out.print("Ingrese el nombre exacto a buscar: ");
                    String nombreBuscar = scanner.nextLine();

                    Producto encontradoNom = gestion.buscarProducto(nombreBuscar);
                    if (encontradoNom != null) {
                        System.out.println(">> Producto encontrado:");
                        encontradoNom.mostrarDatos();
                    } else {
                        System.out.println(">> No existe producto con el nombre: " + nombreBuscar);
                    }
                    break;

                case 5:
                    System.out.println("\n--- CALCULO DE TOTALES ---");
                    if (gestion.estaVacia()) {
                        System.out.println("El pedido esta vacio.");
                    } else {
                        System.out.println("Items registrados: " + gestion.totalRegistrados());
                        System.out.println("Subtotal general: S/. " + String.format("%.2f", gestion.calcularSubtotalGeneral()));
                        System.out.println("Total con 18% IGV: S/. " + String.format("%.2f", gestion.calcularTotalGeneralConIgv()));
                    }
                    break;

                case 6:
                    System.out.println("\nCierre de sesion exitoso.");
                    break;

                default:
                    System.out.println(">> Opcion fuera de rango. Seleccione entre 1 y 6.");
            }
        } while (opcion != 6);

        scanner.close();
    }
}