package facci.arcentales.runningfast.modelo;

public class TiempoDistacia {
    private String tiempo;
    private String distancia;

    public TiempoDistacia(){

    }

    public TiempoDistacia(String tiempo, String distancia) {
        this.tiempo = tiempo;
        this.distancia = distancia;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }
}
