package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {
    @Override
    public Connection createConnection() {
        Connection connection;
        try (FileInputStream file = new FileInputStream("h2database.properties")) {
            Properties properties = new Properties();
            Class.forName(properties.getProperty("jdbc_driver"));
            connection = DriverManager.getConnection(
                    properties.getProperty("db_url"), properties.getProperty("user"), properties.getProperty("password")
            );
            properties.load(file);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}

