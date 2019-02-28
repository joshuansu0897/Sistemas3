package ds3.practicabasededatos.config;

import java.util.HashMap;

public class Configuracion {

    private static Configuracion instance = null;

    private final HashMap<String, String> configuraciones = new HashMap<>();

    public static Configuracion getInstance() throws Exception {
        if (instance == null) {
            instance = new Configuracion();
        }
        return instance;
    }

    private Configuracion() {
        configuraciones.put("servidor", "localhost");
        configuraciones.put("puerto", "3306");
        configuraciones.put("tipoBaseDeDatos", "MySQL");
        configuraciones.put("nombreBaseDeDatos", "DS3");
        configuraciones.put("usuario", "root");
        configuraciones.put("password", "example");
    }

    public String getConfiguracion(String key) {
        try {
            return configuraciones.get(key);
        } catch (Exception e) {
            return "";
        }
    }

    public int getSizeConf() {
        return configuraciones.size();
    }
}
