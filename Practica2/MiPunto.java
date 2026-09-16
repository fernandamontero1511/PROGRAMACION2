// Archivo: MiPunto.java
public class MiPunto {
    // a) Atributos privados para mantener el encapsulamiento
    private double x;
    private double y;

    // b) Constructor sin argumentos (crea el punto (0, 0))
    public MiPunto() {
        this(0.0, 0.0); // Llama al constructor parametrizado
    }

    // c) Constructor con coordenadas especificadas
    public MiPunto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // a) Métodos getter
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // d) Distancia hacia un objeto de tipo MiPunto
    public double distancia(MiPunto otroPunto) {
        return distancia(otroPunto.getX(), otroPunto.getY());
    }

    // e) Distancia hacia coordenadas x e y especificadas (Fórmula de la distancia euclidiana)
    public double distancia(double x, double y) {
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }

    // Programa de prueba
    public static void main(String[] args) {
        // Crear punto (0, 0) usando el constructor por defecto
        MiPunto p1 = new MiPunto();

        // Crear punto (10, 30.5) usando el constructor con argumentos
        MiPunto p2 = new MiPunto(10.0, 30.5);

        // Calcular la distancia entre ambos puntos
        double dist = p1.distancia(p2);

        // Mostrar resultados
        System.out.println("Punto 1: (" + p1.getX() + ", " + p1.getY() + ")");
        System.out.println("Punto 2: (" + p2.getX() + ", " + p2.getY() + ")");
        System.out.printf("La distancia entre los puntos es: %.4f\n", dist);
    }
}