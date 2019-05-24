package proyecto.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Configuration {

    private final static Logger logger = Logger.getLogger(Configuration.class);

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
            logger.error("Error al Crear la configuracion.", e);
        }
    }

    public String getConfiguracion(String key) {
        try {
            return configuraciones.get(key);
        } catch (Exception e) {
            logger.error("Error al busca en configuraciones: " + key, e);
            return "";
        }
    }

    public int getSizeConf() {
        return configuraciones.size();
    }
}
