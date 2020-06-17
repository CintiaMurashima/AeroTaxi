package rutas;

import com.company.aviones.Avion;

import java.util.ArrayList;

public class Ciudades {
    private ArrayList<String> ciudades;
    public Ciudades() {
    }

    public Ciudades(ArrayList<String> ciudades) {
        this.ciudades = ciudades;
    }

    public ArrayList<String> getCiudades() {
        return ciudades;
    }

    public void setCiudades(ArrayList<String> ciudades) {
        this.ciudades = ciudades;
    }


}
