public class EcuacionCuadratica {
    private double a;
    private double b;
    private double c;

    public EcuacionCuadratica(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getDiscriminante() {
        return (this.b * this.b) - (4 * this.a * this.c);
    }

    public double getRaiz1() {
        double disc = getDiscriminante();
        if (disc < 0) {
            return 0;
        }
        return (-this.b + Math.sqrt(disc)) / (2 * this.a);
    }

    public double getRaiz2() {
        double disc = getDiscriminante();
        if (disc < 0) {
            return 0;
        }
        return (-this.b - Math.sqrt(disc)) / (2 * this.a);
    }
}