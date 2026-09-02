import java.lang.System;

public class Cronometro {
    private long inicia;
    private long finaliza;

    // Constructor sin argumentos: inicializa inicia con la hora actual
    public Cronometro() {
        this.inicia = System.currentTimeMillis();
    }

    // Getters
    public long getInicia() {
        return this.inicia;
    }

    public long getFinaliza() {
        return this.finaliza;
    }

    // Restablece inicia a la hora actual
    public void inicia() {
        this.inicia = System.currentTimeMillis();
    }

    // Establece finaliza a la hora actual
    public void detener() {
        this.finaliza = System.currentTimeMillis();
    }

    // Retorna el tiempo transcurrido en milisegundos
    public long lapsoDeTiempo() {
        return this.finaliza - this.inicia;
    }
}