import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class


public class Movimento {
    /**
     * Variaveis de class - valores que sejam comuns a todas os objectos instância
     */
    private LocalDateTime myDateObj;
    private DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private String data_mov;
    private float montante;
    private String tipoMovimento;
    private String autor;/** <-- Identificador do autor que fez este movimento*/



    /**
     * Construtor vazio
     */
    public Movimento() {
        this.myDateObj = LocalDateTime.now();
        this.data_mov = myDateObj.format(myFormatObj);
        this.montante = 0;
        this.tipoMovimento = "Não válido";
        this.autor = "Interno";
    }



    /**
     * Construtor parametrizado
     */
    public Movimento(float valor, String tipo, String nome) {
        this.myDateObj = LocalDateTime.now();
        this.data_mov = myDateObj.format(myFormatObj);
        this.montante = valor;
        this.tipoMovimento = tipo;
        this.autor = nome;
    }



    /**
     * Construtor cópia
     */
    public Movimento(Movimento mov) {
        this.data_mov = mov.getDatMovimento();
        this.montante = mov.getValorMovimento();
        this.tipoMovimento = mov.getTipoMovimento();
        this.autor = mov.getAutorMovimento();
    }



    /**
     * Opta-se por devolver um objecto do mesmo tipo de dados e não Object como é a definição padrão do Java.
     *
     * A utilização de clone() permite que seja possível preservarmos o encapsulamento dos objectos, desde que:
     *      - seja feita uma cópia dos objectos à entrada dos métodos
     *      - seja devolvida uma cópia dos objectos e não o apontador para os mesmos
     *
     * DEEP CLONE: cópia em que nenhum objecto partilha endereços com outro
     *
     * */
    public Movimento clone(){
        return new Movimento(this);
    }



    /**
     *  Strings são objectos imutáveis, logo não crescem, o que as torna muito ineficientes
     *  Uma forma mais eficiente, na medida em que as concatenações de Strings são muito pesadas
     *  a melhor versão do toString é a que se encontra imediatamente a baixo
     * */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.data_mov);
        sb.append("      ");
        sb.append(this.tipoMovimento);
        sb.append("                 ");
        sb.append(this.autor);
        sb.append("                   ");
        sb.append(this.montante);
        sb.append("\n");

        return sb.toString();
    }



    public String getDatMovimento(){
        return this.data_mov;
    }



    public float getValorMovimento(){
        return this.montante;
    }



    public String getTipoMovimento(){
        return this.tipoMovimento;
    }



    public String getAutorMovimento(){
        return this.autor;
    }



    public boolean equals(Object mv) {
        /*Caso se trate de um objecto que aponta para o mesmo endereço*/
        if (this == mv){
            return true;
        }
        /*Verifica se o objecto recebido é do mesmo tipo e caso tal se confirme, então verifica-se se todos os atributos
        do objecto recebido são iguais aos atributos do objecto ao qual se está a comparar*/
        if((mv == null) || (this.getClass() != mv.getClass())){
            return false;
        }else{
            Movimento a = (Movimento) mv;
            return (this.tipoMovimento.equals(a.getTipoMovimento()) &&
                    this.montante == a.getValorMovimento() &&
                    this.data_mov.equals(a.getDatMovimento()) &&
                    this.autor.equals(a.getAutorMovimento()));
        }
    }
}