package model.dao;

import model.bean.Categoria;
import org.junit.Ignore;
import org.junit.Test;
/*A biblioteca abaixo é necessária por causa do metodo 'fail'*/
import static org.junit.Assert.*;


/**
 * Testes usado JUnit
 * */

public class CategoriaDAOTest {

    public CategoriaDAOTest(){
    }

    /*vai se testar o inserir produto*/
    @Test
  //  @Ignore //<<<--- ao manter ativo a flag '@Ignore' ao correr os testes este mesmo teste não será tido em conta, caso o objetivo seja o contrário, apenas elimina-se ou comenta-se a flag
    public void inserir(){

        Categoria cat =  new Categoria("Roupas");
        CategoriaDAO dao =  new CategoriaDAO();

        if(dao.save(cat)){
            System.out.println("Guardado com sucesso!");
        }else{
            fail("Erro ao guardar");
        }
    }

    @Test
 //   @Ignore
    public void listar(){
        CategoriaDAO dao = new CategoriaDAO();

        for(Categoria c: dao.myfindAll()){
            System.out.println("Descrição: " + c.getDescricao());
        }
    }

    @Test
   // @Ignore
    public void actualizar(){
        Categoria cat = new Categoria("Roupa");
        cat.setId(19);// <<---id da categoria (na BD) a ser alterada
        CategoriaDAO dao = new CategoriaDAO();

        if(dao.myUpdate(cat)) {
            System.out.println("Atualização realizada com sucesso!");
        }else{
            fail("Erro a guardar a informação atualizada");
        }
    }


    @Test
    public void eliminar(){
        Categoria cat = new Categoria();
        cat.setId(19);// <<---id da categoria (na BD) a ser eliminada

        CategoriaDAO dao = new CategoriaDAO();

        if(dao.myDelete(cat)){
            System.out.println("Eliminado com sucesso!");
        }else{
            fail("Erro na eliminação da categoria pretendida!");
        }
    }
}