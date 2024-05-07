package model.dao;

import datasource.DataSource;

public class DAOFactory {

    public static ProductoDao createCocheDao(){
        return new ProductoDaoImpl(DataSource.obtenerConexion());
    }

}
