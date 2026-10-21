class Sensor {
    protected String tipo;
    protected String id;
    protected boolean encendido;

    public Sensor(String id) {
        this.tipo = "Genérico";
        this.id = id;
        this.encendido = false;
    }

    public void activar() {
        this.encendido = true;
        System.out.println("Sensor " + id + " activado.");
    }

    public void desactivar() {
        this.encendido = false;
        System.out.println("Sensor " + id + " desactivado.");
    }
}

// Clase Camara que extiende de Sensor
class Camara extends Sensor {
    protected String resolucion;

    public Camara(String id, String resolucion) {
        super(id);
        this.tipo = "Cámara";
        this.resolucion = resolucion;
    }
}

// Clase SensorLuzLaser que extiende de Sensor
class SensorLuzLaser extends Sensor {
    protected int alcance;

    public SensorLuzLaser(String id, int alcance) {
        super(id);
        this.tipo = "Luz Laser";
        this.alcance = alcance;
    }
}

// Vehículo Autónomo (En Java se modela heredando de una principal y componiendo la otra,
// o usando interfaces para cumplir con la herencia múltiple conceptual del diagrama)
class VehiculoAutonomo extends Camara {
    private SensorLuzLaser sensorLaser; // Componente adicional para simular la herencia múltiple del diagrama

    public VehiculoAutonomo(String id, String resolucion, int alcance) {
        super(id, resolucion);
        this.tipo = "Vehículo Autónomo";
        this.sensorLaser = new SensorLuzLaser(id + "-laser", alcance);
    }

    public void mostrarDatosVehiculo() {
        System.out.println("Tipo: " + this.tipo);
        System.out.println("ID: " + this.id);
        System.out.println("Resolución de Cámara: " + this.resolucion);
        System.out.println("Alcance de Láser: " + this.sensorLaser.alcance);
    }
}

// Clase de prueba para el Sistema
public class MainVehiculo {
    public static void main(String[] args) {
        VehiculoAutonomo miVehiculo = new VehiculoAutonomo("VA-001", "4K", 100);
        miVehiculo.mostrarDatosVehiculo();
        miVehiculo.activar();
    }
}