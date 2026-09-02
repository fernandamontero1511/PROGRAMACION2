import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EstadisticaEstructurada {

    // Método para calcular el promedio
    public static double promedio(List<Double> datos) {
        double suma = 0;
        for (double num : datos) {
            suma += num;
        }
        return suma / datos.size();
    }

    // Método para calcular la desviación estándar
    public static double desviacion(List<Double> datos) {
        double prom = promedio(datos);
        double sumaCuadrados = 0;
        
        for (double x : datos) {
            sumaCuadrados += Math.pow(x - prom, 2);
        }
        // len(datos) - 1 en Python es datos.size() - 1
        return Math.sqrt(sumaCuadrados / (datos.size() - 1));
    }

    // Programa Principal
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese los números separados por espacios: ");
        String entrada = scanner.nextLine();
        
        // Procesar la entrada de texto a una lista de números
        List<Double> numeros = new ArrayList<>();
        String[] partes = entrada.split("\\s+"); // Separa por cualquier cantidad de espacios
        for (String parte : partes) {
            if (!parte.isEmpty()) {
                numeros.add(Double.parseDouble(parte));
            }
        }

        // Mostrar resultados con formato (.2f y .5f)
        System.out.printf("El promedio es %.2f%n", promedio(numeros));
        System.out.printf("La desviación estándar es %.5f%n", desviacion(numeros));
        
        scanner.close();
    }
}

