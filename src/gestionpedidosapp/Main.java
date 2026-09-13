package gestionpedidosapp;

import java.util.ArrayList;
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
            gestion.agregarProducto(new Producto("Teclado Mecanico", 180.00));
        } catch (Exception e) {
            System.out.println("Error en precarga: " + e.getMessage());
        }

        do {
            System.out.println("\n==========================================");
            System.out.println(" SISTEMA DE GESTION DE PEDIDOS V3 - UPN ");
            System.out.println("==========================================");
            System.out.println("1. Registrar producto con validacion");
            System.out.println("2. Listar productos de la coleccion");
            System.out.println("3. Buscar producto por ID (Sobrecarga A)");
            System.out.println("4. Buscar producto por Nombre (Sobrecarga B)");
            System.out.println("5. Buscar productos por rango de precio");
            System.out.println("6. Calcular importe total o con descuento");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opcion (1-7): ");

            // Aporte Integrante 6: Manejo integral de excepciones para evitar caídas del sistema
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

                        // Usa la sobrecarga del Integrante 3
                        gestion.agregarProducto(nombre, precio);
                        System.out.println(">> Producto registrado con exito.");

                    } catch (InputMismatchException e) {
                        System.out.println(">> [Error de Formato]: El precio debe ser un numero.");
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
                        System.out.print("Ingrese el ID a buscar: ");
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
                        System.out.println(">> [Error]: El ID debe ser un valor entero.");
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
                    System.out.println("\n--- BUSQUEDA POR RANGO DE PRECIOS ---");
                    try {
                        System.out.print("Ingrese precio minimo: S/. ");
                        double min = scanner.nextDouble();
                        System.out.print("Ingrese precio maximo: S/. ");
                        double max = scanner.nextDouble();
                        scanner.nextLine();

                        ArrayList<Producto> filtrados = gestion.buscarPorRangoPrecio(min, max);
                        if (filtrados.isEmpty()) {
                            System.out.println(">> No se encontraron productos en ese rango.");
                        } else {
                            System.out.println(">> Productos encontrados (" + filtrados.size() + "):");
                            for (Producto p : filtrados) {
                                p.mostrarDatos();
                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.println(">> [Error]: Ingrese valores numericos para el rango.");
                        scanner.nextLine();
                    } catch (IllegalArgumentException e) {
                        System.out.println(">> [Error de Validacion]: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println("\n--- CALCULO DE TOTALES ---");
                    if (gestion.estaVacia()) {
                        System.out.println("El pedido esta vacio.");
                    } else {
                        System.out.println("Items registrados: " + gestion.totalRegistrados());
                        System.out.println("Subtotal general: S/. " + String.format("%.2f", gestion.calcularSubtotalGeneral()));
                        System.out.println("Total con 18% IGV: S/. " + String.format("%.2f", gestion.calcularTotalGeneralConIgv()));

                        // Opción de descuento
                        try {
                            System.out.print("¿Desea simular descuento para un producto? (Ingrese ID o 0 para omitir): ");
                            int idDesc = scanner.nextInt();
                            if (idDesc > 0) {
                                Producto pDesc = gestion.buscarProducto(idDesc);
                                if (pDesc != null) {
                                    System.out.print("Ingrese % de descuento (0-100): ");
                                    double desc = scanner.nextDouble();
                                    double finalDesc = pDesc.calcularPrecioFinal(desc);
                                    System.out.println(">> Precio final con " + desc + "% de descuento + IGV: S/. " + String.format("%.2f", finalDesc));
                                } else {
                                    System.out.println(">> Producto no encontrado.");
                                }
                            }
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println(">> [Error]: Entrada numerica invalida.");
                            scanner.nextLine();
                        } catch (IllegalArgumentException e) {
                            System.out.println(">> [Error]: " + e.getMessage());
                        }
                    }
                    break;

                case 7:
                    System.out.println("\nCierre de sesion exitoso.");
                    break;

                default:
                    System.out.println(">> Opcion no valida. Seleccione entre 1 y 7.");
            }
        } while (opcion != 7);

        scanner.close();
    }
}