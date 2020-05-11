package model.dao;

import connection.ConnectionFactory;
import model.bean.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * CRUD - Create(save); Read, Update, Delete
 *
 * DAO
 *
 * Camada de persistência
 *
 * LINK: https://pt.wikipedia.org/wiki/Objeto_de_acesso_a_dados
 * */

public class CategoriaDAO {

    private Connection con = null;


    public CategoriaDAO(){
        con = ConnectionFactory.getConnection();
    }


    /**
     * Método responsavel por guardar uma nova categoria na respetiva tabela da base de dados*/
    public boolean save(Categoria categoria){
        /*
        String sql = "INSERT INTO <tabela> (atributo) VALUES (local na string onde será passado um valor, valor este, que irá compor a string referente á instrução sql)"
        */
        String sql = "INSERT INTO categoria (descricao) VALUES (?)";
        PreparedStatement stmt = null;


        try {
            /*Caso funcione tudo corretamente*/
            stmt = con.prepareStatement(sql);
            /*Abaixo, getString porque na base de dados é uma string*/
            stmt.setString(1,categoria.getDescricao());
            /*executeUpdate é responsavel pelo insert, update e delete*/
            stmt.executeUpdate();
            return true;
        }catch(SQLException ex){
            /*Em caso de erro*/
            System.err.println("Erro: " + ex);
            return false;
        }finally {
            /*
            * - Independentemente de dar ou nao erro a conexão será fechada
            * - Termina a ligação 'con', 'stmt'
            */
            ConnectionFactory.closeConnection(con,stmt);
        }
    }


    /**
     * Método responsavel pela atualização de informação na base de dados*/
    public boolean myUpdate(Categoria categoria){
        String sql = "UPDATE categoria SET descricao = ? WHERE id = ?";
        PreparedStatement stmt = null;


        try {
            stmt = con.prepareStatement(sql);
            /*
            * Os '?' sao referências aos valores que vão ser colocados no prepareStatement de forma a integrar e completar a instrução sql abaixo
            *
            * String sql = "UPDATE categoria SET descricao = ? WHERE id = ?";
            *                                                ^            ^
            *                                                |            |
            *                                                |            *--- 2º valor/posição
            *                                                |
            *                                                *---- 1º valor/posição
            *
            * stmt.setString(1,categoria.getDescricao());
            *                ^
            *                |
            *                *--- 1º valor passado ao prepareStatement que diz respeito ao 1º '?' existente na instrução sql acima
            *
            *
            * stmt.setInt(2,categoria.getId());
            *             ^
            *             |
            *             *--- 2º valor passado ao prepareStatement que diz respeito ao 1º '?' existente na instrução sql acima
            *
            *
            *
            *   Obs.:
            *       stmt.setString(<posição, na instrução sql, onde se encontra o '?'>, <parâmetro que se quer passar na posição indicada>)
            *
            *   Nota: as posições começam no valor '1'
            * */
            stmt.setString(1,categoria.getDescricao());
            stmt.setInt(2,categoria.getId());
            stmt.executeUpdate();
            return true;
        }catch(SQLException ex){
            System.err.println("Erro: " + ex);
            return false;
        }finally {
            ConnectionFactory.closeConnection(con,stmt);
        }
    }


    /**
     * Método responsavel pela eliminação de uma categoria da base de dados
     * */
    public boolean myDelete(Categoria cat){

        String sql = "DELETE FROM categoria WHERE id = ?";
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,cat.getId());
            stmt.executeUpdate();
            return true;
        }catch(SQLException ex){
            /*Em caso de erro*/
            System.err.println("Erro: " + ex);
            return false;
        }finally {
            ConnectionFactory.closeConnection(con,stmt);
        }
    }


    /**
     * Método que faz a consulta os dados da base de dados
     * */
    public List<Categoria> myfindAll(){

        String sql = "SELECT * FROM categoria";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Categoria> categorias = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while(rs.next()){
                Categoria categoria = new Categoria();
                categoria.setDescricao(rs.getString("descricao"));
                categorias.add(categoria);
            }
        }catch(SQLException ex){
            System.err.println("Erro " + ex);
        }finally {
            ConnectionFactory.closeConnection(con,stmt,rs);
        }
        return categorias;
    }
}
