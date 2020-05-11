package model.bean;

public class Produto {
    private int id;
    private String descricao;
    private int qtd;
    private double valor;
    private Categoria categoria;

    /**
     * Construtores (exceto o de cópia)
     * */
    public Produto(){
    }

    public Produto(String desc, int n, double val, Categoria cat){
        this.descricao = desc;
        this.qtd = n;
        this.valor = val;
        this.categoria = cat;
    }

    /**
     * Getters
     * */
    public int getId(){
        return this.id;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public int getQuantidade(){
        return this.qtd;
    }

    public double getValor(){
        return this.valor;
    }

    public Categoria getCategoria(){
        return this.categoria;
    }



    /**
     * Setters
     * */
    public void setId(int x){
        this.id = x;
    }

    public void setQuantidade(int x){
        this.qtd = x;
    }

    public void setValor(double x){
        this.valor=x;
    }

    public void setDescricao(String desc){
        this.descricao = desc;
    }

    public void setCategoria(Categoria cat){
        this.categoria = cat;
    }

}
