package dataBase;

import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseHelper {

    private String dataBaseName = "";
    private String tableName = "";
    private MySQLConnection mySQLConnection = new MySQLConnection(getDataBaseName());

    public void createConnection() {
        mySQLConnection.createConnection();
    }

    public void createDatabase(String dataBaseName) {
        this.dataBaseName = dataBaseName;
        mySQLConnection.createDatabase(dataBaseName);
    }

    public void createTable(String tableName, String queuery) {
        this.tableName = tableName;
        mySQLConnection.createTable(tableName, queuery);
    }

    public void useDataBase(String databaseName) throws SQLException {
        mySQLConnection.getStatement().executeUpdate("use " + databaseName);
    }

    public Statement getStatement() {
        return mySQLConnection.getStatement();
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
