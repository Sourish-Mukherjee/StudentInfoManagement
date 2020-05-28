package dataBase;

import java.sql.*;

public class MySQLConnection {

    private String driverName = "";
    private String url = "";
    private String userName = "";
    private String password = "";
    private String tableName = "";
    private String databaseName = "";
    private Statement st = null;

    protected MySQLConnection() {
    }

    protected MySQLConnection(String databaseName) throws Exception {
        this("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/" + databaseName,
                "root", "123456");
    }

    protected MySQLConnection(String driverName, String url, String userName,
            String password) throws Exception {
        this.driverName = driverName;
        this.url = url;
        this.userName = userName;
        this.password = password;
        createConnection();
    }

    protected final void createConnection() throws Exception {
        Connection conn;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, userName, password);
            st = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Server Could Not Be Connected!");
            System.out.println(e.getMessage());
            throw new Exception("Error in createConnection Method");
        }
    }

    protected final void createDatabase(String dataBaseName) {
        try {
            st.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dataBaseName);
        } catch (SQLException e) {
            System.out.println("Failed To Create DataBase!");
            System.out.println(e.getMessage());
        }
    }

    protected void createTable(String tableName, String query) {
        try {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS " + tableName + " ( " + query + " )");
            this.tableName = tableName;
        } catch (SQLException e) {
            System.out.println("COULD NOT CREATE TABLE!");
            System.out.println(e.getMessage());
        }
    }

    protected Statement getStatement() {
        return this.st;
    }

    protected String getTableName() {
        return tableName;
    }

    protected void setTableName(String tableName) {
        this.tableName = tableName;
    }

    protected String getDatabaseName() {
        return databaseName;
    }

    protected void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}
