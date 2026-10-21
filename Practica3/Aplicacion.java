import java.util.Scanner;

// Clase base Juego
class Juego {
    protected int numeroDeVidas;
    protected int record;

    public Juego(int vidasIniciales) {
        this.numeroDeVidas = vidasIniciales;
        this.record = 0;
    }

    public void reiniciaPartida() {
        // Reinicia las vidas por defecto (por ejemplo, 3 vidas)
        this.numeroDeVidas = 3;
    }

    public void actualizaRecord() {
        // Si el récord actual es menor, se puede actualizar
        this.record++;
        System.out.println("¡Nuevo récord actualizado! Récord actual: " + this.record);
    }

    public boolean quitaVida() {
        this.numeroDeVidas--;
        if (this.numeroDeVidas > 0) {
            return true;
        } else {
            return false;
        }
    }
}

// Clase derivada JuegoAdivinaNumero
class JuegoAdivinaNumero extends Juego {
    protected int numeroAAdivinar;

    public JuegoAdivinaNumero(int vidas) {
        super(vidas);
    }

    public void juega() {
        Scanner scanner = new Scanner(System.in);
        reiniciaPartida(); // 1. Llama al método reiniciaPartida
        
        // 2. Genera aleatoriamente el número a adivinar (entre 0 y 10)
        this.numeroAAdivinar = (int) (Math.random() * 11);
        
        System.out.println("=== ¡JUEGO DE ADIVINAR EL NÚMERO! ===");

        while (true) {
            // 3. Muestra un mensaje pidiendo que adivine un número entre 0 y 10
            System.out.print("Adivina un número entre el 0 y el 10: ");
            int numeroIngresado = scanner.nextInt();

            // 4. Compara el número ingresado con el generado
            if (numeroIngresado == this.numeroAAdivinar) {
                System.out.println("¡Acertaste!!");
                actualizaRecord();
                break;
            } else {
                // Si es diferente, llama a quitaVida
                boolean quedanVidas = quitaVida();
                
                if (quedanVidas) {
                    // c' Aún le quedan vidas, indica si es mayor o menor
                    if (numeroIngresado < this.numeroAAdivinar) {
                        System.out.println("El número a adivinar es MAYOR. Inténtalo de nuevo");
                    } else {
                        System.out.println("El número a adivinar es MENOR. Inténtalo de nuevo");
                    }
                    System.out.println("Vidas restantes: " + this.numeroDeVidas);
                } else {
                    // d' Ya no le quedan vidas
                    System.out.println("Te has quedado sin vidas El número era: " + this.numeroAAdivinar);
                    break;
                }
            }
        }
    }
}

// Clase de Aplicación
public class Aplicacion {
    public static void main(String[] args) {
        JuegoAdivinaNumero miJuego = new JuegoAdivinaNumero(3);
        miJuego.juega();
    }
}
