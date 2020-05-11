package model.dao;

import model.bean.Categoria;
import model.bean.Produto;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

/**
 * Testes usado JUnit
 * */
public class ProdutoDAOTest {

    public ProdutoDAOTest(){
    }

    @Test
    //@Ignore
    public void inserir(){
        Produto prod = new Produto();
        Categoria cat = new Categoria();
        cat.setId(1); //<<<---- desta forma é selecionada da base de dados a categoria com o id=1
        prod.setDescricao("Bacalhau");
        prod.setQuantidade(20);
        prod.setValor(10);
        prod.setCategoria(cat);


        ProdutoDAO dao = new ProdutoDAO();

        if(dao.save(prod)){
            System.out.println("Produto inserido com sucesso");
        }else{
            fail("Erro ao inserir o produto!");
        }
    }


    @Test
    public void listar(){
        ProdutoDAO dao = new ProdutoDAO();

        for(Produto p: dao.myfindAll()){
            System.out.println("Descrição Porduto: " + p.getDescricao() + "  Descrição Categoria: " + p.getCategoria().getDescricao());
        }
    }

    @Test
    public void atualizar(){
        Categoria cat = new Categoria();
        cat.setId(18); //<<<---- desta forma é selecionada da base de dados a categoria com o id=1
        Produto prod = new Produto();
        prod.setDescricao("Tomate");
        prod.setQuantidade(20);
        prod.setValor(100);
        prod.setCategoria(cat);

        prod.setId(3);
        ProdutoDAO dao = new ProdutoDAO();

        if(dao.myUpdate(prod)){
            System.out.println("Produto atualizado com sucesso");
        }else{
            fail("Erro ao inserir o produto!");
        }
    }
}