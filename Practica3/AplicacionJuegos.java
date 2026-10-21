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
        this.numeroDeVidas = 3;
    }

    public void actualizaRecord() {
        this.record++;
        System.out.println("¡Nuevo récord actualizado! Récord: " + this.record);
    }

    public boolean quitaVida() {
        this.numeroDeVidas--;
        return this.numeroDeVidas > 0;
    }
}

// Clase JuegoAdivinaNumero
class JuegoAdivinaNumero extends Juego {
    protected int numeroAAdivinar;

    public JuegoAdivinaNumero(int vidas) {
        super(vidas);
    }

    public boolean validaNumero(int numero) {
        if (numero >= 0 && numero <= 10) {
            return true;
        }
        return false;
    }

    // Método para generar el número secreto normal
    protected void generaNumeroSecreto() {
        this.numeroAAdivinar = (int) (Math.random() * 11);
    }

    public void juega() {
        Scanner scanner = new Scanner(System.in);
        reiniciaPartida();
        generaNumeroSecreto();
        
        System.out.println("\n=== NUEVA PARTIDA DE ADIVINANZA ===");

        while (true) {
            System.out.print("Ingresa un número entre el 0 y el 10: ");
            int numeroIngresado = scanner.nextInt();

            if (!validaNumero(numeroIngresado)) {
                System.out.println("Error: El número debe estar entre 0 y 10.");
                continue;
            }

            if (numeroIngresado == this.numeroAAdivinar) {
                System.out.println("¡Acertaste!!");
                actualizaRecord();
                break;
            } else {
                if (quitaVida()) {
                    if (numeroIngresado < this.numeroAAdivinar) {
                        System.out.println("El número es MAYOR.");
                    } else {
                        System.out.println("El número es MENOR.");
                    }
                    System.out.println("Vidas restantes: " + this.numeroDeVidas);
                } else {
                    System.out.println("¡Game Over! El número secreto era: " + this.numeroAAdivinar);
                    break;
                }
            }
        }
    }
}

// Clase JuegoAdivinaPar
class JuegoAdivinaPar extends JuegoAdivinaNumero {
    public JuegoAdivinaPar(int vidas) {
        super(vidas);
    }

    @Override
    public boolean validaNumero(int numero) {
        if (super.validaNumero(numero)) {
            if (numero % 2 == 0) {
                return true;
            } else {
                System.out.println("¡Error! Debes ingresar un número PAR.");
                return false;
            }
        }
        System.out.println("¡Error! Fuera del rango (0-10).");
        return false;
    }

    @Override
    protected void generaNumeroSecreto() {
        // Se repite hasta que el número aleatorio sea PAR obligatoriamente
        do {
            this.numeroAAdivinar = (int) (Math.random() * 11);
        } while (this.numeroAAdivinar % 2 != 0);
    }
}

// Clase JuegoAdivinaImpar
class JuegoAdivinaImpar extends JuegoAdivinaNumero {
    public JuegoAdivinaImpar(int vidas) {
        super(vidas);
    }

    @Override
    public boolean validaNumero(int numero) {
        if (super.validaNumero(numero)) {
            if (numero % 2 != 0) {
                return true;
            } else {
                System.out.println("¡Error! Debes ingresar un número IMPAR.");
                return false;
            }
        }
        System.out.println("¡Error! Fuera del rango (0-10).");
        return false;
    }

    @Override
    protected void generaNumeroSecreto() {
        // Se repite hasta que el número aleatorio sea IMPAR obligatoriamente
        do {
            this.numeroAAdivinar = (int) (Math.random() * 11);
        } while (this.numeroAAdivinar % 2 == 0);
    }
}

// Clase Aplicación principal
public class AplicacionJuegos {
    public static void main(String[] args) {
        JuegoAdivinaNumero juegoNormal = new JuegoAdivinaNumero(3);
        JuegoAdivinaPar juegoPar = new JuegoAdivinaPar(3);
        JuegoAdivinaImpar juegoImpar = new JuegoAdivinaImpar(3);

        System.out.println("--- JUEGO NORMAL ---");
        juegoNormal.juega();

        System.out.println("\n--- JUEGO DE NÚMEROS PARES ---");
        juegoPar.juega();

        System.out.println("\n--- JUEGO DE NÚMEROS IMPARES ---");
        juegoImpar.juega();
    }
}