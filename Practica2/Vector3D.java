// Archivo: Vector3D.java
public class Vector3D {
    // Componentes del vector tridimensional: a = (a1, a2, a3)
    private double a1;
    private double a2;
    private double a3;

    // Sobrecarga de Constructores
    public Vector3D() {
        this(0.0, 0.0, 0.0);
    }

    public Vector3D(double a1, double a2, double a3) {
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
    }

    // Métodos Getter
    public double getA1() { return a1; }
    public double getA2() { return a2; }
    public double getA3() { return a3; }

    // a) Suma de dos vectores a y b: c = a + b = (a1 + b1, a2 + b2, a3 + b3)
    public Vector3D sumar(Vector3D b) {
        return new Vector3D(this.a1 + b.getA1(), this.a2 + b.getA2(), this.a3 + b.getA3());
    }

    // Método auxiliar para restar vectores
    public Vector3D restar(Vector3D b) {
        return new Vector3D(this.a1 - b.getA1(), this.a2 - b.getA2(), this.a3 - b.getA3());
    }

    // b) Multiplicación de un escalar r por un vector a: b = r * a = (r * a1, r * a2, r * a3)
    public Vector3D multiplicarEscalar(double r) {
        return new Vector3D(r * this.a1, r * this.a2, r * this.a3);
    }

    // c) Longitud (magnitud) de un vector a: |a| = sqrt(a1^2 + a2^2 + a3^2)
    public double longitud() {
        return Math.sqrt(Math.pow(this.a1, 2) + Math.pow(this.a2, 2) + Math.pow(this.a3, 2));
    }

    // d) Normal de un vector a: b = a / |a| = (a1 / |a|, a2 / |a|, a3 / |a|)
    public Vector3D normal() {
        double mod = this.longitud();
        if (mod == 0) {
            throw new ArithmeticException("No se puede normalizar un vector nulo (longitud 0).");
        }
        return new Vector3D(this.a1 / mod, this.a2 / mod, this.a3 / mod);
    }

    // e) Producto escalar de a y b: a · b = a1*b1 + a2*b2 + a3*b3
    public double productoEscalar(Vector3D b) {
        return this.a1 * b.getA1() + this.a2 * b.getA2() + this.a3 * b.getA3();
    }

    // f) Producto vectorial (cruz) de a y b: a x b = (a2*b3 - a3*b2, a3*b1 - a1*b3, a1*b2 - a2*b1)
    public Vector3D productoCruz(Vector3D b) {
        double c1 = this.a2 * b.getA3() - this.a3 * b.getA2();
        double c2 = this.a3 * b.getA1() - this.a1 * b.getA3();
        double c3 = this.a1 * b.getA2() - this.a2 * b.getA1();
        return new Vector3D(c1, c2, c3);
    }

    // Figura 1: a es perpendicular a b si las diagonales del paralelogramo son iguales: |a + b| == |a - b|
    public boolean esPerpendicular(Vector3D b) {
        double longitudSuma = this.sumar(b).longitud();
        double longitudResta = this.restar(b).longitud();
        return Math.abs(longitudSuma - longitudResta) < 1e-6; // Tolerancia para números de punto flotante
    }

    // Figura 2: Proyección ortogonal de a sobre b: Proy_b(a) = ((a · b) / |b|^2) * b
    public Vector3D proyeccionEn(Vector3D b) {
        double escalar = this.productoEscalar(b) / Math.pow(b.longitud(), 2);
        return b.multiplicarEscalar(escalar);
    }

    // Figura 2: Componente de a en la dirección de b: Comp_b(a) = (a · b) / |b|
    public double componenteEn(Vector3D b) {
        return this.productoEscalar(b) / b.longitud();
    }

    @Override
    public String toString() {
        return String.format("(%.2f, %.2f, %.2f)", a1, a2, a3);
    }

    // ========================================================
    // PROGRAMA DE PRUEBA (MAIN)
    // ========================================================
    public static void main(String[] args) {
        Vector3D a = new Vector3D(3.0, 4.0, 0.0);
        Vector3D b = new Vector3D(0.0, 5.0, 0.0);

        System.out.println("============================================");
        System.out.println("     OPERACIONES DE ALGEBRA VECTORIAL       ");
        System.out.println("============================================");
        System.out.println("Vector a = " + a);
        System.out.println("Vector b = " + b + "\n");

        // a) Suma
        System.out.println("a) Suma (c = a + b): " + a.sumar(b));

        // b) Escalado
        double r = 2.5;
        System.out.println("b) Escalar (" + r + " * a): " + a.multiplicarEscalar(r));

        // c) Longitud
        System.out.printf("c) Longitud de a (|a|): %.4f\n", a.longitud());

        // d) Normal
        System.out.println("d) Vector Normal de a: " + a.normal());

        // e) Producto Escalar
        System.out.printf("e) Producto Escalar (a · b): %.2f\n", a.productoEscalar(b));

        // f) Producto Cruz
        System.out.println("f) Producto Vectorial (a x b): " + a.productoCruz(b));

        // Prueba Figura 1: Perpendicularidad por diagonales
        Vector3D u = new Vector3D(1.0, 0.0, 0.0);
        Vector3D v = new Vector3D(0.0, 1.0, 0.0);
        System.out.println("\n--- EVALUACIÓN DE PERPENDICULARIDAD (Figura 1) ---");
        System.out.println("u = " + u + " | v = " + v);
        System.out.println("¿Son perpendiculares? (|u + v| == |u - v|): " + (u.esPerpendicular(v) ? "SÍ" : "NO"));

        // Prueba Figura 2: Proyección y Componente
        System.out.println("\n--- PROYECCIÓN Y COMPONENTE (Figura 2) ---");
        System.out.println("Proyección de a sobre b (Proy_b a): " + a.proyeccionEn(b));
        System.out.printf("Componente de a en b (Comp_b a): %.4f\n", a.componenteEn(b));
    }
}