import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ContaOLD {

    /**Variaveis de class - valores que sejam comuns a todas os objectos instância*/
    private LocalDateTime myDateObj;
    private DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private String data_CriacaoConta;
    private int numConta;
    private String titular;
    private float saldo;
    /**
     * Diferença de declarar ArrayList e List para listas em Java?
     * LINK: https://pt.stackoverflow.com/questions/73633/arraylist-x-list
     *
     * Obs.:
     *      - Utilizar List sempre que se precise de manter ordem
     *      - O método add não testa se o objecto existe na colecção
     *      - O método contains verifica sempre o resultado de equals
     *      - Implementação utilizada: ArrayList<E>
     * Nota: Com uma lista definida como List só se pode chamar métodos e membros que pertencem a List
     * */
    private List<Movimento> lstMovimentos;

    /**Construtor vazio*/
    public Conta(){
        this.myDateObj = LocalDateTime.now();
        this.data_CriacaoConta = myDateObj.format(myFormatObj);
        /**
         * Nota: Comm a lista inicializada como ArrayList pode-se chamar métodos e membros que pertencem a List e ArrayList
         * */
        this.lstMovimentos = new ArrayList<>();
        this.numConta = -1;
        this.titular = "NONE";
        this.saldo = 0;
    }

    /**Contrutor parametrizado*/
    public Conta(String data, String nome, float valor, ArrayList<Movimento> list, int id){
        this.data_CriacaoConta = data;
        this.titular = nome;
        this.saldo = valor;
        this.lstMovimentos = list; /* <<<<<---- fazer aqui um metodo de colone da lista de input para lstMovimentos*/
        this.numConta = id;
    }

    /**Construtor cópia*/
    public Conta(Conta ct){
        this.data_CriacaoConta = ct.getDataCriacaoConta();
        this.numConta = ct.getIdConta();
        this.titular = ct.getTitularConta();
        this.saldo = ct.getSaldoConta();
        this.lstMovimentos = ct.getLstMovimentos(); /* <<<<<---- fazer aqui um metodo de colone da lista de input para lstMovimentos*/
    }

    public int getIdConta(){
        return this.numConta;
    }

    public String getDataCriacaoConta(){
        return this.data_CriacaoConta;
    }

    public int getNumConta(){
        return this.numConta;
    }

    public String getTitularConta(){
        return this.titular;
    }

    public float getSaldoConta(){
        return this.saldo;
    }

    public List<Movimento> getLstMovimentos(){
        return this.lstMovimentos;
    }

    public void setTitular(String nome){
        this.titular = nome;
    }


    /*
    //--------------------------------------------------------confirmar se está a fucnionar corretamente
    //Consoante seja credito ou debito, atualiza o saldo da conta
    public void refreshSaldoConta(){

        lstMovimentos.forEach(movimento -> {
            if(movimento.getTipoMovimento().equals("Credito")){
                this.saldo += movimento.getValorMovimento();
            }else{
                this.saldo -= movimento.getValorMovimento();
            }
        });
    }
*/


    public Conta clone(){
        return new Conta(this);
    }

    private Boolean comparalistasMovimentos(ArrayList<Movimento> list){ /* <<<------ Confirmar se está a funcionar este metodo de comparação*/
        if(this.lstMovimentos == list){
            return true;
        }

        if(list == null){
            return false;
        }

        if(this.lstMovimentos.size() != list.size()){
            return false;
        }else {
            /**
             * *  Tatica usada para confirmar se a lista de movimentos são iguais:
             *       1º É criada uma copia da lista recebida
             *       2º é feita a remoção, na lista copiada, de todas as ocorrencias de elementos da lista instanciada
             *       3º se todos os elementos da copia tiverem sido removidos então significa que a cópia era igual á lista instanciada (nao se salvaguarda o caso de que a lista instanciada pode ter mais movimentos que a lista copiada)
             *       4º se a após as remoções a lista instanciada tiver tamanho zero então devolve true ou false caso contrario
             *
             * */
            List<Movimento> temp = new ArrayList<>(list);
            temp.removeAll(this.lstMovimentos);
            return temp.size() == 0;
        }
    }

    public boolean equals(Object ct) {
    /*Caso se trate de um objecto que aponta para o mesmo endereço*/
    if (this == ct){
        return true;
    }
    /*Verifica se o objecto recebido é do mesmo tipo e caso tal se confirme, então verifica-se se todos os atributos
    do objecto recebido são iguais aos atributos do objecto ao qual se está a comparar*/
    if((ct == null) || (this.getClass() != ct.getClass())){
        return false;
    }else{
        Conta a = (Conta) ct;
        return (this.titular.equals(a.getTitularConta()) &&
                this.saldo == a.getSaldoConta() &&
                this.data_CriacaoConta.equals(a.getDataCriacaoConta()) &&
                this.lstMovimentos.equals(a.getLstMovimentos()) &&
                this.numConta == a.getNumConta() &&
                comparalistasMovimentos((ArrayList<Movimento>) ((Conta) ct).getLstMovimentos()));/* <<<---- fazer um equals decente para comparar estas duas listas*/
    }

    }

    /**
     *  Strings são objectos imutáveis, logo não crescem, o que as torna muito ineficientes
     *  Uma forma mais eficiente, na medida em que as concatenações de Strings são muito pesadas,
     *  é a versão do toString é a que se encontra imediatamente a baixo
     * */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Data de criação de conta: ");
        sb.append(this.data_CriacaoConta);
        sb.append("\n");
        sb.append("Numero de conta: ");
        sb.append(this.numConta);
        sb.append("\n");
        sb.append("Titular: ");
        sb.append(this.titular);
        sb.append("\n");
        sb.append("Saldo bancário: ");
        sb.append(this.saldo);
        sb.append("\n============================== Movimentos na conta ==============================\n");
        sb.append("Data                    Tipo                    Origem                   Montante\n");
        sb.append("---------------------------------------------------------------------------------\n");
        for(Movimento mov: this.lstMovimentos){
            sb.append(mov.toString());
            sb.append("\n");
        }
        sb.append("======================================= Fim =====================================\n");

        return sb.toString();
    }

    public String toStringWithoutMovements() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n======================== Ficha de cliente ========================\n");
        sb.append("Data de criação de conta: ");
        sb.append(this.data_CriacaoConta);
        sb.append("\n");
        sb.append("Numero de conta: ");
        sb.append(this.numConta);
        sb.append("\n");
        sb.append("Titular: ");
        sb.append(this.titular);
        sb.append("\n");
        sb.append("Saldo bancário: ");
        sb.append(this.saldo);
        sb.append("\n===================================================================\n");

        return sb.toString();
    }

    public String toStringOnlyMovimentos(){
        StringBuilder sb = new StringBuilder();

        sb.append("=============================== Movimentos na conta ==============================\n");
        sb.append("Data                    Tipo                    Origem                    Montante\n");
        sb.append("----------------------------------------------------------------------------------\n");
        for(Movimento mov: this.lstMovimentos){
            sb.append(mov.toString());
            sb.append("\n");
        }
        sb.append("======================================== Fim =====================================\n");

        return sb.toString();
    }

}
