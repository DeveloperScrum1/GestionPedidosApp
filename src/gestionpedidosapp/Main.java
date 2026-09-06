package gestionpedidosapp;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Producto> listaProductos = new ArrayList<>();
        int opcion = 0;

        do {
            System.out.println("\n==========================================");
            System.out.println(" SISTEMA DE GESTION DE PEDIDOS V2 - UPN ");
            System.out.println("==========================================");
            System.out.println("1. Registrar producto al pedido");
            System.out.println("2. Listar productos del pedido");
            System.out.println("3. Calcular importe total acumulado");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");

            // Manejo de errores con try-catch para evitar caidas por entrada incorrecta
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpieza de buffer
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un numero valido.");
                scanner.nextLine();
                opcion = 0;
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n--- REGISTRO DE PRODUCTO ---");
                    System.out.print("Ingrese nombre: ");
                    String nombre = scanner.nextLine();

                    double precio = 0.0;
                    boolean precioValido = false;
                    while (!precioValido) {
                        try {
                            System.out.print("Ingrese precio unitario (S/.): ");
                            precio = scanner.nextDouble();
                            scanner.nextLine();
                            if (precio <= 0) {
                                System.out.println("Error: El precio debe ser mayor a 0.");
                            } else {
                                precioValido = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error de entrada: Ingrese un valor numerico.");
                            scanner.nextLine();
                        }
                    }

                    // Se aprovecha la sobrecarga del constructor con ID correlativo estatico
                    listaProductos.add(new Producto(nombre, precio));
                    System.out.println(">> Producto registrado con exito.");
                    break;

                case 2:
                    System.out.println("\n--- LISTA DE PRODUCTOS ---");
                    if (listaProductos.isEmpty()) {
                        System.out.println("No hay productos registrados.");
                    } else {
                        for (Producto p : listaProductos) {
                            p.mostrarDatos();
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n--- CALCULO DE TOTALES ---");
                    if (listaProductos.isEmpty()) {
                        System.out.println("El pedido esta vacio.");
                    } else {
                        double subtotal = 0.0;
                        double totalConIgv = 0.0;
                        for (Producto p : listaProductos) {
                            subtotal += p.getPrecio();
                            totalConIgv += p.calcularPrecioFinal();
                        }
                        System.out.println("Items registrados: " + Producto.getContadorProductos());
                        System.out.println("Subtotal general: S/. " + String.format("%.2f", subtotal));
                        System.out.println("Total a pagar (con 18% IGV): S/. " + String.format("%.2f", totalConIgv));
                    }
                    break;

                case 4:
                    System.out.println("\nCierre de sesion exitoso. ¡Gracias por su preferencia!");
                    break;

                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        } while (opcion != 4);

        scanner.close();
    }
}