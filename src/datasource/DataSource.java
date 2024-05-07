package datasource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataSource {

    private static Connection conn = null;

    public static Connection obtenerConexion() {
        if (conn == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("url");
                conn = DriverManager.getConnection(url, props);
            } catch (SQLException e) {
                throw new DataSourceException(e.getMessage());
            }

        }
        return conn;
    }

    public static void cerrarConexion() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DataSourceException(e.getMessage());
            }

        }
    }

    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("Tienda.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DataSourceException(e.getMessage());
        }
    }

    public static void cerrarStatement(Statement st) {
        if(st!=null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DataSourceException(e.getMessage());
            }
        }
    }

    public static void cerrarResulset(ResultSet rs) {
        if(rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DataSourceException(e.getMessage());
            }
        }
    }
}