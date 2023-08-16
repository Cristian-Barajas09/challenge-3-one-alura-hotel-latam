package com.latam.alura.hotel.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionFactory {
    private DataSource dataSource;

    public ConexionFactory(){

        var pollDataSource = new ComboPooledDataSource();
        pollDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel_alura?useTimeZone=true&serverTimeZone=UTC");
        pollDataSource.setUser("root");
        pollDataSource.setPassword("cb300804");
        pollDataSource.setMaxPoolSize(10);
        this.dataSource = pollDataSource;
    }

    public Connection recuperaConexion(){
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
