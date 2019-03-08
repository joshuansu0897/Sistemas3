package practicabasededatos.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private final String url;
    private final String usuario;
    private final String password;

    protected DB(String host, String inst, String port, String db, String usuario, String password) throws Exception {
        switch (inst) {
            case "MySQL":
                Class.forName("com.mysql.jdbc.Driver");
                url = "jdbc:mysql://" + host + ":" + port + "/" + db;
                break;
            case "PostgreSQL":
                Class.forName("org.postgresql.Driver");
                url = "jdbc:postgresql://" + host + ":" + port + "/" + db;
                break;
            default:
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String aux = "jdbc:sqlserver://" + host + ":" + port;
                if (!inst.equals("")) {
                    aux += "\\" + inst;
                }
                aux += ";databaseName=" + db;
                url = aux;
                break;
        }
        this.usuario = usuario;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getUrl(), getUss(), getPwd());
    }

    public String getUrl() {
        return url;
    }

    public String getUss() {
        return usuario;
    }

    public String getPwd() {
        return password;
    }
}
