package gestionpedidosapp;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Producto> listaProductos = new ArrayList<>();
        int opcion = 0;

        do {
            System.out.println("\n==========================================");
            System.out.println("   SISTEMA DE GESTION DE PEDIDOS - UPN   ");
            System.out.println("==========================================");
            System.out.println("1. Registrar nuevo producto al pedido");
            System.out.println("2. Mostrar productos del pedido");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");

            // Validacion de entrada numerica
            while (!scanner.hasNextInt()) {
                System.out.println("Error: Ingrese un numero valido.");
                scanner.next();
                System.out.print("Seleccione una opcion: ");
            }
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.println("\n--- REGISTRO DE PRODUCTO ---");
                    System.out.print("Ingrese ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Ingrese nombre del producto: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Ingrese precio unitario (S/.): ");
                    double precio = scanner.nextDouble();
                    scanner.nextLine();

                    // Creacion de objeto Producto y agregado a la coleccion
                    listaProductos.add(new Producto(id, nombre, precio));
                    System.out.println(">> Producto registrado con exito.");
                    break;

                case 2:
                    System.out.println("\n--- LISTA DE PRODUCTOS DEL PEDIDO ---");
                    if (listaProductos.isEmpty()) {
                        System.out.println("No hay productos registrados en el pedido.");
                    } else {
                        for (Producto p : listaProductos) {
                            p.mostrarDatos();
                        }
                    }
                    break;

                case 3:
                    System.out.println("\nCierre de sesion exitoso. ¡Gracias por su preferencia!");
                    break;

                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        } while (opcion != 3);

        scanner.close();
    }
}