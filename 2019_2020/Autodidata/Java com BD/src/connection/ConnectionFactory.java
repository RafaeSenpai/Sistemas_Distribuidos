package connection;

import java.rmi.ServerError;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    /**Para se saber qual é o driver, basta ir á biblioteca que se adicionou, do mysql, e...
     * -> mysql-connector-java-5.1.40.jar
     * --> com.mysql
     * --->jdbc
     * ---->Driver
     **/
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    /**Este endereço encontra-se na documentação do mysql.
     * Obs: se for com oracle será diferente*/
    private static final String URL = "jdbc:mysql://localhost:3306/myFirstBD";
    private static final String USER = "root";
    private static final String PASS = "";

    /**Método responsavel por aplicar a ligação a bd com os parâmetros acima*/
    public static Connection getConnection(){
        //carregar o drive acima definido
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL,USER,PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão!",ex);
        }
    }

    /**Método para fechar o ligação á base de dados com segurança*/
    public static void closeConnection(Connection con){
        if(con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Erro: " + e);
            }
        }
    }

    /**Método para fechar o statement e a ligação a base de dados*/
    public static void closeConnection(Connection con, PreparedStatement stmt){

        if(stmt != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Erro: " + e);
            }
        }
        closeConnection(con);
    }

    /**Método para fechar resultSet, o statement e a ligação a base de dados*/
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){

        if(rs != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Erro: " + e);
            }
        }
        closeConnection(con,stmt);
    }
}
