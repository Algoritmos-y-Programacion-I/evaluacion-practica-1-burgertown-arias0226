package ui;
import java.util.Scanner;

public class BurgerTown {

    public static Scanner reader;
    public static double[] precios;
    public static int[] unidades;

    public static void main(String[] args) {

        inicializarGlobales();
        menu();
    }

    /**
     * Descripción: Este método se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: El Scanner reader queda inicializado
    */
    public static void inicializarGlobales() {
        reader = new Scanner(System.in);
    }

    /**
     * Descripción: Este método se encarga de desplegar el menú al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
    */
    public static void menu() {

        System.out.println("¡Bienvenido a BurgerTown!");
        establecerCantidadVendida();

        boolean salir = false;

        do {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada plato vendido en el día");
            System.out.println("2. Calcular la cantidad total de platos vendidos en el día");
            System.out.println("3. Calcular el precio promedio de los platos vendidos en el día");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el día");
            System.out.println("5. Consultar el número de platos que han superado un límite mínimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opción a ejecutar");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de platos vendidos en el día fue de: " + calcularTotalPlatosVendidos());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de los platos vendidos en el día fue de: " + calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el día fueron: " + calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el límite mínimo de ventas a analizar:");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe los " + precios.length + " platos vendidos en el día, " + consultarPlatosSobreLimite(limite) + " superaron el límite mínimo de ventas de " + limite);
                    break;
                case 6:
                    System.out.println("\n¡Gracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;
                default:
                    System.out.println("\nOpción inválida, intenta nuevamente.");
                    break;
            }

        } while (!salir);
    }

    /**
     * Descripción: Pregunta al usuario el número de platos vendidos e inicializa los arreglos de precios y cantidades.
     * pre: El Scanner reader debe estar inicializado.
     * pos: Los arreglos precios y unidades quedan inicializados.
     */
    public static void establecerCantidadVendida() {
        System.out.println("\nDigite el número de platos diferentes vendidos en el día:");
        int platos = reader.nextInt();
        precios = new double[platos];
        unidades = new int[platos];
    }

    /**
     * Descripción: Solicita al usuario el precio y cantidad de cada plato vendido.
     * pre: Los arreglos precios y unidades deben estar inicializados.
     * pos: Los arreglos quedan llenos con los datos proporcionados por el usuario.
     */
    public static void solicitarDatos() {
        for (int i = 0; i < precios.length; i++) {
            System.out.println("Digite el precio del plato " + (i + 1) + ":");
            precios[i] = reader.nextDouble();
            System.out.println("Digite la cantidad vendida del plato " + (i + 1) + ":");
            unidades[i] = reader.nextInt();
        }
    }

    /**
     * Descripción: Calcula la cantidad total de platos vendidos.
     * pre: Los arreglos precios y unidades deben estar inicializados.
     * @return cantidad total de platos vendidos.
     */
    public static int calcularTotalPlatosVendidos() {
        int total = 0;
        for (int i = 0; i < unidades.length; i++) {
            total += unidades[i];
        }
        return total;
    }

    /**
     * Descripción: Calcula el precio promedio de los platos vendidos en el día.
     * pre: Los arreglos precios y unidades deben estar inicializados.
     * @return precio promedio de los platos.
     */
    public static double calcularPrecioPromedio() {
        double sumaPrecios = 0;
        int totalPlatos = calcularTotalPlatosVendidos();
        for (int i = 0; i < precios.length; i++) {
            sumaPrecios += precios[i] * unidades[i];
        }
        return totalPlatos > 0 ? sumaPrecios / totalPlatos : 0;
    }

    /**
     * Descripción: Calcula el total de dinero recaudado durante el día.
     * pre: Los arreglos precios y unidades deben estar inicializados.
     * @return ventas totales en dinero.
     */
    public static double calcularVentasTotales() {
        double totalVentas = 0;
        for (int i = 0; i < precios.length; i++) {
            totalVentas += precios[i] * unidades[i];
        }
        return totalVentas;
    }

    /**
     * Descripción: Consulta cuántos platos superaron un límite mínimo de ventas.
     * pre: Los arreglos precios y unidades deben estar inicializados.
     * @param limite límite mínimo de ventas.
     * @return cantidad de platos que superaron el límite.
     */
    public static int consultarPlatosSobreLimite(double limite) {
        int contador = 0;
        for (int i = 0; i < precios.length; i++) {
            double ventaPlato = precios[i] * unidades[i];
            if (ventaPlato > limite) {
                contador++;
            }
        }
        return contador;
    }
}