package model.dao;

import connection.ConnectionFactory;
import model.bean.Categoria;
import model.bean.Produto;

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


public class ProdutoDAO {

    private Connection con = null;


    public ProdutoDAO(){
        con = ConnectionFactory.getConnection();
    }

    public boolean save(Produto prod){
        String sql = "INSERT INTO produto (descricao, qtd, valor,  categoria_id) VALUES (?,?,?,?)";
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1,prod.getDescricao());
            stmt.setInt(2,prod.getQuantidade());
            stmt.setDouble(3,prod.getValor());
            /*Abaixo trata-se de inserir a chave estrangeira*/
            stmt.setInt(4,prod.getCategoria().getId());

            stmt.executeUpdate();
            return true;
        }catch(SQLException ex){
            System.err.println("Erro: " + ex);
            return false;
        }finally {
            ConnectionFactory.closeConnection(con,stmt);
        }
    }



    /**Consulta entre duas tabelas fazendo a COMPOSIÇÃO*/
    /**
     * Método que faz a consulta os dados da base de dados
     * */
    public List<Produto> myfindAll(){
        /**
         * FK - Chave Estrangeira
         * - Abaixo, recurso à composição
         * */
       String sql = "SELECT p.id AS pid, p.descricao AS produtoDescricao, qtd, valor, categoria_id, c.id AS cid, c.descricao AS categoriaDescricao FROM produto p INNER JOIN categoria c ON c.id = p.categoria_id";
        /**
         * IMPORTANTE - ACONSELHÁVEL
         * Abaixo, outra instrução sql , que devolve o mesmo resultado mas com muito menos código, pois recorre a uma view previamente criada no Sistema de gestão de base de dados(SGDB)

            String sql = "Select * from viewFeitaNoMyAdmin";
         */
//      String sql = "Select * from viewFeitaNoMyAdmin";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while(rs.next()){

                Produto prod = new Produto();

                prod.setId(rs.getInt("pid"));
                prod.setDescricao(rs.getString("produtoDescricao"));
                prod.setQuantidade(rs.getInt("qtd"));
                prod.setValor(rs.getDouble("valor"));


                Categoria cat = new Categoria();

                cat.setId(rs.getInt("categoria_id"));
                cat.setDescricao(rs.getString("categoriaDescricao"));
                prod.setCategoria(cat);

                produtos.add(prod);
            }
        }catch(SQLException ex){
            System.err.println("Erro " + ex);
        }finally {
            ConnectionFactory.closeConnection(con,stmt,rs);
        }
        return produtos;
    }




    /**
     * Método responsavel pela atualização de informação na base de dados*/
    public boolean myUpdate(Produto prod){
        String sql = "UPDATE produto SET descricao = ?, qtd = ?, valor = ?, categoria_id = ? WHERE id = ?";

        PreparedStatement stmt = null;


        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1,prod.getDescricao());
            stmt.setInt(2,prod.getQuantidade());
            stmt.setDouble(3,prod.getValor());
            stmt.setInt(4,prod.getCategoria().getId());
            stmt.setInt(5,prod.getId());
            stmt.executeUpdate();
            return true;
        }catch(SQLException ex){
            System.err.println("Erro: " + ex);
            return false;
        }finally {
            ConnectionFactory.closeConnection(con,stmt);
        }
    }

}
