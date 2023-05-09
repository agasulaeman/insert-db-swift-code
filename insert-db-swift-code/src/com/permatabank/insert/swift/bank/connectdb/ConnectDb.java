package com.permatabank.insert.swift.bank.connectdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDb {

    public Connection connectDatabase() {

        String url = "jdbc:db2://10.87.248.72:50000/KMFUATDB";
        Connection conn = null;
        String userName = "db2inst1";
        String password = "db2inst1";
        try {
            DriverManager.registerDriver(new com.ibm.db2.jcc.DB2Driver());
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}
