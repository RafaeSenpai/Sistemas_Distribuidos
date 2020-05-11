package model.bean;

public class Categoria {

        private int id;
        private String descricao;


        public Categoria(){
        }

        public Categoria(String descricao) {
            this.descricao = descricao;
        }

        public int getId(){
            return this.id;
        }

        public void setId(int n){
            this.id = n;
         }

         public void setDescricao(String desc){
            this.descricao = desc;
         }

         public String getDescricao(){
            return this.descricao;
         }


}
