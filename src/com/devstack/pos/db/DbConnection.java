package com.devstack.pos.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Using Singleton Design pattern
public class DbConnection {
    private static DbConnection dbconnection;
    private Connection connection;

    private DbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/robotikka",
                "root", "1234");
    }

    public static DbConnection getInstance() throws SQLException, ClassNotFoundException {
        return (dbconnection == null) ? dbconnection = new DbConnection() : dbconnection;
    }
    public Connection getConnection(){
        return connection;
    }

}
