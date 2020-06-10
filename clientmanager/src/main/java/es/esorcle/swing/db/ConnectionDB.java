package es.esorcle.swing.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static Connection connection = null;

    private static ConnectionData connectionData;

    public static Connection getConnection(){
        return getConnection(connectionData);
    }

    public static Connection getConnection(ConnectionData data){
        connectionData = connectionData;
        try{
            if( connection== null ){

                String url = data.getConectionUrlMySql();
                String pwd= "";
                for (int i = 0; i < data.getPwd().length ; i++) {
                    pwd += data.getPwd()[i];
                }
                connection= DriverManager.getConnection(url,data.getUser(),pwd);
             System.out.println("Conexión establecida");
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return connection;
    }

    public static void close() {
            try {
                if(connection != null) {
                    connection.close();
                    System.out.println("Cerrada la conexión");
                }
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
}


