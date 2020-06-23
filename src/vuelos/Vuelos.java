package vuelos;
import java.util.ArrayList;

public class Vuelos {

    private ArrayList<Vuelo> vuelos;


    public Vuelos(ArrayList<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    public Vuelos() {
    }

    public ArrayList<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(ArrayList<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }


    @Override
    public String toString() {
        return "Vuelos{" +
                "vuelos=" + vuelos +
                '}';
    }
}
