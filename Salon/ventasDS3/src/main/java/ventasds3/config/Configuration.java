package ventasds3.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Configuration {

    private static final File ubicacion = new File("Configuracion" + File.separator + "config.json");

    private static Configuration instance = null;

    private final HashMap<String, String> configuraciones = new HashMap<>();

    public static Configuration getInstance() throws Exception {
        if (instance == null) {
            instance = new Configuration(ubicacion);
        }
        return instance;
    }

    private Configuration(File json) {
        try {
            if (json != null && json.exists()) {
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(new FileReader(json));
                JSONObject jsonObject = (JSONObject) obj;

                for (Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext();) {
                    String key = (String) iterator.next();
                    configuraciones.put(key, jsonObject.get(key).toString());
                }
            }
        } catch (IOException | ParseException e) {
            System.out.println("Error al Crear la configuracion:" + e.getMessage());
        }
    }

    public String getConfiguracion(String key) {
        try {
            return configuraciones.get(key);
        } catch (Exception e) {
            System.out.println("Error al busca " + key + " en configuraciones: " + e.getMessage());
            return "";
        }
    }

    public int getSizeConf() {
        return configuraciones.size();
    }
}
