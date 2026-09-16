// Archivo: Vector.java
class Vector {
    private double x, y, z;

    public Vector() {
        this(0, 0, 0);
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }

    // Módulo o magnitud del vector: |a| = sqrt(x^2 + y^2 + z^2)
    public double modulo() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    // Producto punto (a · b)
    public double productoPunto(Vector v) {
        return this.x * v.x + this.y * v.y + this.z * v.z;
    }

    // Producto cruz (a x b)
    public Vector productoCruz(Vector v) {
        double cx = this.y * v.z - this.z * v.y;
        double cy = this.z * v.x - this.x * v.z;
        double cz = this.x * v.y - this.y * v.x;
        return new Vector(cx, cy, cz);
    }

    // Suma de vectores (a + b)
    public Vector sumar(Vector v) {
        return new Vector(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    // Resta de vectores (a - b)
    public Vector restar(Vector v) {
        return new Vector(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    // Multiplicación por un escalar r · a
    public Vector multiplicarEscalar(double r) {
        return new Vector(this.x * r, this.y * r, this.z * r);
    }

    @Override
    public String toString() {
        return String.format("(%.2f, %.2f, %.2f)", x, y, z);
    }
}


// Archivo: AlgebraVectorial.java
public class AlgebraVectorial {
    private Vector a;
    private Vector b;

    // Sobrecarga de Constructores
    public AlgebraVectorial() {
        this(new Vector(), new Vector());
    }

    public AlgebraVectorial(Vector a, Vector b) {
        this.a = a;
        this.b = b;
    }

    // ========================================================
    // SOBRECARGA DE MÉTODOS DE PERPENDICULARIDAD
    // ========================================================

    // c) Perpendicular si a · b = 0 (Método por defecto)
    public boolean esPerpendicular() {
        return esPerpendicular(this.a, this.b);
    }

    public boolean esPerpendicular(Vector a, Vector b) {
        return Math.abs(a.productoPunto(b)) < 1e-6;
    }

    // Sobrecarga según el criterio geométrico (utilizando un código/opción):
    // 1: |a + b| = |a - b|
    // 2: |a - b| = |b - a|
    // 3: a · b = 0
    // 4: |a + b|^2 = |a|^2 + |b|^2
    public boolean esPerpendicular(int criterio) {
        double tol = 1e-6; // Tolerancia para comparar números de punto flotante
        switch (criterio) {
            case 1: // a) |a + b| = |a - b|
                return Math.abs(a.sumar(b).modulo() - a.restar(b).modulo()) < tol;

            case 2: // b) |a - b| = |b - a|
                return Math.abs(a.restar(b).modulo() - b.restar(a).modulo()) < tol;

            case 3: // c) a · b = 0
                return Math.abs(a.productoPunto(b)) < tol;

            case 4: // d) |a + b|^2 = |a|^2 + |b|^2 (Teorema de Pitágoras)
                double modSumaCuadrado = Math.pow(a.sumar(b).modulo(), 2);
                double sumaModulosCuadrado = Math.pow(a.modulo(), 2) + Math.pow(b.modulo(), 2);
                return Math.abs(modSumaCuadrado - sumaModulosCuadrado) < tol;

            default:
                return esPerpendicular();
        }
    }

    // ========================================================
    // SOBRECARGA DE MÉTODOS DE PARALELISMO
    // ========================================================

    // f) Paralela si a x b = 0 (Producto cruz nulo)
    public boolean esParalela() {
        return Math.abs(a.productoCruz(b).modulo()) < 1e-6;
    }

    // e) Paralela si a = r * b (proporcional por un escalar r)
    public boolean esParalela(double r) {
        Vector rb = b.multiplicarEscalar(r);
        return Math.abs(a.restar(rb).modulo()) < 1e-6;
    }

    // ========================================================
    // SOBRECARGA DE PROYECCIÓN Y COMPONENTE
    // ========================================================

    // g) Proyección ortogonal de a sobre b: Proy_b(a) = ((a · b) / |b|^2) * b
    public Vector proyeccion() {
        return proyeccion(this.a, this.b);
    }

    public Vector proyeccion(Vector a, Vector b) {
        double escalar = a.productoPunto(b) / Math.pow(b.modulo(), 2);
        return b.multiplicarEscalar(escalar);
    }

    // h) Componente de a en la dirección de b: Comp_b(a) = (a · b) / |b|
    public double componente() {
        return componente(this.a, this.b);
    }

    public double componente(Vector a, Vector b) {
        return a.productoPunto(b) / b.modulo();
    }

    // ========================================================
    // PROGRAMA DE PRUEBA (MAIN)
    // ========================================================
    public static void main(String[] args) {
        // Vectores de prueba perpendiculares: a = (1, 0, 0), b = (0, 1, 0)
        Vector v1 = new Vector(1, 0, 0);
        Vector v2 = new Vector(0, 1, 0);

        AlgebraVectorial alg1 = new AlgebraVectorial(v1, v2);

        System.out.println("--- PRUEBAS CON VECTORES PERPENDICULARES ---");
        System.out.println("Vector a: " + v1);
        System.out.println("Vector b: " + v2);
        System.out.println("a) |a + b| = |a - b| ?: " + alg1.esPerpendicular(1));
        System.out.println("b) |a - b| = |b - a| ?: " + alg1.esPerpendicular(2));
        System.out.println("c) a · b = 0 ?: " + alg1.esPerpendicular(3));
        System.out.println("d) |a + b|^2 = |a|^2 + |b|^2 ?: " + alg1.esPerpendicular(4));

        // Vectores de prueba paralelos: a = (2, 4, 6), b = (1, 2, 3) con r = 2.0
        Vector v3 = new Vector(2, 4, 6);
        Vector v4 = new Vector(1, 2, 3);

        AlgebraVectorial alg2 = new AlgebraVectorial(v3, v4);

        System.out.println("\n--- PRUEBAS CON VECTORES PARALELOS ---");
        System.out.println("Vector a: " + v3);
        System.out.println("Vector b: " + v4);
        System.out.println("e) a = 2.0 * b ?: " + alg2.esParalela(2.0));
        System.out.println("f) a x b = 0 ?: " + alg2.esParalela());

        // Proyección y Componente
        System.out.println("\n--- PROYECCIÓN Y COMPONENTE ---");
        System.out.println("g) Proyección de a sobre b: " + alg2.proyeccion());
        System.out.printf("h) Componente de a en b: %.4f\n", alg2.componente());
    }
}