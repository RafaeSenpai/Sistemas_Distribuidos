import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


import static java.lang.System.exit;

public class Banco {
    private String nomeBanco;
    private Map<Integer, Conta> contasBancarias;
    private LocalDateTime myDateObj;
    private String data_CriacaoConta;
    private Scanner sc = new Scanner(System.in);
    private LocalDateTime dataObject;
    private DateTimeFormatter Objectformat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");


    /**
     * Construtor vazio
     */
    public Banco() {
        this.nomeBanco = "nome_qualquer";
        this.contasBancarias = new HashMap<>();
    }


    /**
     * Construtor parametrizado
     */
    public Banco(String nome, Map<Integer, Conta> bd) {
        this.nomeBanco = nome;
        this.contasBancarias = new HashMap<>();
    }


    /**
     * Construtor copia
     */
    public Banco(Banco bnk) {
        this.nomeBanco = bnk.getNomeBanco();
        this.contasBancarias = bnk.getContasBancarias();
    }


    public String getNomeBanco() {
        return this.nomeBanco;
    }


    /**Devolve um objecto com a listagem de todas as contas existentes no banco <chave, valor>
     * Este metodo é usado no construtor copia
     * */
    public HashMap<Integer,Conta> getContasBancarias(){
        return new HashMap<>(this.contasBancarias);/*  <<<<<<<<<<<<<<<<<<<<------------------------------------------   Perceber se é deep ou shallow clone*/
    }


    /**Devolve uma copia da lista de todas as contas bancarias, este metodo é auxiliar ao metodo equals*/
    public ArrayList<Conta> getAllBankCounts(){
        return new ArrayList<>(this.contasBancarias.values());
    }


    /**Dado o numero de conta devolve informações basicas do cliente*/
    public String getConta(int id){
        return this.contasBancarias.get(id).toStringWithoutMovements();
    }


    /**Dado o numero de conta devolve toda a informação da mesma (com a lista de movimentos)*/
    public String getContaAllInformation(int id){
        /*<<<--------------------------------------------------------------------------------------------------------   Entre o que está comentado e o return valido neste metodo, qual deles é que pode vir a comprometer o encapsulamento???
       Map<Integer, Conta> newBD = new HashMap<>(this.contasBancarias);
       newBD.putAll(this.contasBancarias);
       return newBD.get(id).toString();
       */

        return this.contasBancarias.get(id).toString();
    }


    /**Dado um numero de conta devolve apenas os movimentos da mesma*/
    public String getMovimentosConta(int id){
        return this.contasBancarias.get(id).toStringOnlyMovimentos();
    }


    /**Numero de contas na instituição  */
    public int getNumCounts(){
        return this.contasBancarias.size();
    }


    /**Obter informação basica de conta  */
    public void getBasicCountInfo(){
        System.out.println("Insira o numero da conta:");
        int id = sc.nextInt();
        System.out.println(getConta(id));
    }


    /**Obter informação detalhada de conta  */
    public void getDetailCountInfo(){
        System.out.println("Insira o numero da conta:");
        int id = sc.nextInt();
        System.out.println(getContaAllInformation(id));
    }


    /**Listar todas as contas existentes    */
    public void getAllCounts(){
        this.contasBancarias.forEach((k,v)->System.out.println(v.toStringWithoutMovements()));
    }


    /**Listar movimentos de conta   */
    public void getAllCountMovements(){
        System.out.println("Insira o numero da conta:");
        int id = sc.nextInt();
        System.out.println(getMovimentosConta(id));
    }


    /**Verificar existencia de conta    */
    public void verifExistencia(){
        System.out.println("Indique o id da conta:");
        int id = sc.nextInt();
        System.out.println("Resposta: " + contasBancarias.containsKey(id));
    }


    /**Efetua o movimento de uma determinada quantia entre duas determinadas contas bancarias, este metodo é auxiliar ao metodo makeTransaction()*/
    public void setMovimentoEntreContas(float valor, int id_ct_origem, int id_ct_destino) {

        if(this.contasBancarias.get(id_ct_origem).getSaldoConta()-valor >= 0){
            this.contasBancarias.get(id_ct_origem).makeMovimento(valor,"Debito/Transferencia",this.contasBancarias.get(id_ct_origem).getTitularConta());
            this.contasBancarias.get(id_ct_destino).makeMovimento(valor,"Credito/Transferencia",this.contasBancarias.get(id_ct_origem).getTitularConta());
        }
    }


    /**Metodo responsavel por efetuar transação entre contas*/
    public void setTransaction(){
        System.out.println("Insira o valor a ser transacionado");
        float montante = sc.nextFloat();
        System.out.println("Insira o numero da conta de origem:");
        int id1 = sc.nextInt();
        System.out.println("Insira o numero da conta destino:");
        int id2 = sc.nextInt();

        setMovimentoEntreContas(montante, id1, id2);
    }


    /**Metodo responsavel pelo processo de deposito*/
    public void setDeposito(){
        System.out.println("Indique o id da conta:");
        int id = sc.nextInt();
        System.out.println("Montante a depositar:");
        float valor = sc.nextFloat();

        this.contasBancarias.get(id).makeMovimento(valor,"Credito",this.contasBancarias.get(id).getTitularConta());
    }


    /**Metodo responsavel pelo processo de levantamento*/
    public void setLevantamento(){
        System.out.println("Indique o id da conta:");
        int id = sc.nextInt();
        System.out.println("Montante a levantar:");
        float valor = sc.nextFloat();

        this.contasBancarias.get(id).makeMovimento(valor,"Debito",this.contasBancarias.get(id).getTitularConta());
    }


    /**Criar conta bancaria*/
    public void criarConta(){
        dataObject = LocalDateTime.now();
        String data = dataObject.format(Objectformat);

        Scanner newSc = new Scanner(System.in);
        float valor_c;

        System.out.println("Insira o nome do titular:");
        String nome_t = newSc.nextLine();
        System.out.println("Insira o montante de abertura:");
        valor_c = newSc.nextFloat();

        Conta conta = new Conta(data,nome_t,valor_c,contasBancarias.size());
        this.contasBancarias.put(conta.getNumConta(),conta);

        System.out.println(getConta(conta.getNumConta()));
    }

    /**Responsavel por fazer a limpesa do terminal*/
    public void clearTerminal(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    public void menu(){
        int opcao = 0;

        do {
            System.out.println("\n\n                ######## FULLBANCK - Painel de Utilizador #######\n");
            System.out.println("\n                  =================================================\n");
            System.out.println("                  |     0 - Carregar contas bancarias              |");
            System.out.println("                  |     1 - Criar conta bancaria                   |");
            System.out.println("                  |     2 - Obter informação basica de conta       |");
            System.out.println("                  |     3 - Obter informação detalhada de conta    |");
            System.out.println("                  |     4 - Listar todas as contas existentes      |");
            System.out.println("                  |     5 - Listar movimentos de conta             |");
            System.out.println("                  |     6 - Fazer transação entre contas           |");
            System.out.println("                  |     7 - Fazer depósito em conta                |");
            System.out.println("                  |     8 - Fazer débito em conta                  |");
            System.out.println("                  |     9 - Verificar existencia de conta          |");
            System.out.println("                  |     10 - Numero de contas na instituição       |");
            System.out.println("                  |     11 - Sair                                  |");
            System.out.println("                  =================================================\n\n");

            opcao = sc.nextInt();

            switch (opcao) {
                case 0:
                    povoarBD();
                    break;
                case 1:
                    clearTerminal();
                    criarConta();
                    break;
                case 2:
                    clearTerminal();
                    getBasicCountInfo();
                    break;
                case 3:
                    clearTerminal();
                    getDetailCountInfo();
                    break;
                case 4:
                    clearTerminal();
                    getAllCounts();
                    break;
                case 5:
                    clearTerminal();
                    getAllCountMovements();
                    break;
                case 6:
                    clearTerminal();
                    setTransaction();
                    break;
                case 7:
                    clearTerminal();
                    setDeposito();
                    break;
                case 8:
                    clearTerminal();
                    setLevantamento();
                    break;
                case 9:
                    clearTerminal();
                    verifExistencia();
                    break;
                case 10:
                    clearTerminal();
                    System.out.println("Numero de cliente: " + this.getNumCounts());
                    break;
                case 11:
                    exit(0);
                    break;
                default:
                    clearTerminal();
                    System.out.println("Opção Inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    public static void main(String[] args) {

        Banco myBanc = new Banco();

        myBanc.menu();
    }

    public Banco clone(){
        return new Banco(this);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Nome da instituição: ");
        sb.append(this.getNomeBanco());
        sb.append("\n");
        sb.append("Numero de contas:");
        sb.append(this.getNumCounts());
        sb.append("\n*=================== Listagem de clientes ======================\n");
        this.contasBancarias.forEach((k,v) -> sb.append(v.toStringWithoutMovements()));/* <<<<<<<<<<<<<------------------confirmar se está a funcionar*/
        sb.append("\n*===============================================================\n");
        return sb.toString();
    }

    private Boolean comparalistasContas(ArrayList<Conta> list){ /* <<<------ Confirmar se está a funcionar este metodo de comparação*/
        if(this.contasBancarias.values() == list){
            return true;
        }

        if(list == null){
            return false;
        }

        if(list.size() != this.contasBancarias.values().size()){
            return false;
        }else {
            /**
             * *  Tatica usada para confirmar se a lista de movimentos são iguais:
             *       1º É criada uma copia da lista recebida
             *       2º é feita a remoção, na lista copiada, de todas as ocorrencias de elementos da lista instanciada
             *       3º se todos os elementos da copia tiverem sido removidos então significa que a cópia era igual á lista instanciada(nao se salvaguarda o caso de que a lista instanciada pode ter mais movimentos que a lista copiada)
             *       4º se a após as remoções a lista instanciada tiver tamanho zero então devolve true ou false caso contrario
             *
             * */
            List<Conta> temp = new ArrayList<>(list);
            temp.removeAll(this.contasBancarias.values());
            return temp.size() == 0;
        }
    }

    public boolean equals(Object bnk){
        /*Caso se trate de um objecto que aponta para o mesmo endereço*/
        if (this == bnk){
            return true;
        }
    /*Verifica se o objecto recebido é do mesmo tipo e caso tal se confirme, então verifica-se se todos os atributos
    do objecto recebido são iguais aos atributos do objecto ao qual se está a comparar*/
        if((bnk == null) || (this.getClass() != bnk.getClass())){
            return false;
        }else{
            Banco a = (Banco) bnk;
            return (this.nomeBanco.equals(a.getNomeBanco()) &&
                    this.getNumCounts() == a.getNumCounts() &&
                    comparalistasContas(((Banco) bnk).getAllBankCounts()));
        }
    }

    /**Criar nomes aleatoriamente   */
    final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghejklmnopqrstuvxywz";
    final java.util.Random rand = new java.util.Random();
    final Set<String> identifiers = new HashSet<String>();
    public String nomeAleatorio() {
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = rand.nextInt(5)+5;
            for(int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if(identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }


    /**Carregar contas bancarias*/
    public void povoarBD(){
        int nMAX_COUNTS = 10;
        int nMAX_TRANSFRS = 10;
        int int_MAX_VAL_CREDIT = 5000;
        int nMAX_DEP_AND_CRED = 10;


        //instância um objeto da classe Random usando o construtor básico
        Random gerador = new Random();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.myDateObj = LocalDateTime.now();
        this.data_CriacaoConta = myDateObj.format(myFormatObj);


        /**Criar contas bancarias*/
        for(int i=0; i<nMAX_COUNTS; i++){
            this.myDateObj = LocalDateTime.now();
            Conta newCount = new Conta(data_CriacaoConta,nomeAleatorio(),0,contasBancarias.size());
            contasBancarias.put(newCount.getNumConta(), newCount);
        }

        /**Atribui crédito a todas as contas*/
        for(int k=0; k<nMAX_COUNTS; k++) {
            float valor = gerador.nextInt(5000) + gerador.nextFloat();
            Movimento mv = new Movimento(valor,"Credito","Proprio");
            this.contasBancarias.get(k).makeMovimento(valor, "Val. Abertura",this.contasBancarias.get(k).getTitularConta());
        }

        /**O ciclo a baixo cria transações nas contas bancárias, transações são feitas entre contas que já pertencem ao banco*/
        for(int j=0; j<nMAX_TRANSFRS; j++){
            Random gerador2 = new Random();
            /**Vai gerar um valor aleatorio compreendido entre 0 e 1201 (max = 1200 + (0...0.9(9))*/
            float valor = gerador2.nextInt(int_MAX_VAL_CREDIT) + gerador2.nextFloat();
            setMovimentoEntreContas(valor,gerador2.nextInt(nMAX_COUNTS),gerador2.nextInt(nMAX_COUNTS));
        }

        /**Vai gerar registos de simples depositos e levantamentos pelas contas existentes*/
        for(int k=0; k<gerador.nextInt(nMAX_DEP_AND_CRED); k++){
            float valor = gerador.nextInt(int_MAX_VAL_CREDIT) + gerador.nextFloat();
            int id = gerador.nextInt(nMAX_COUNTS);

            if(gerador.nextInt(2) == 1){
                /**Regista um credito de valor aleatorio numa conta cujo o seu id está compreendido entre determinada gama de ids */
                this.contasBancarias.get(id).makeMovimento(valor,"Credito",this.contasBancarias.get(id).getTitularConta());
            }else{
                /**Regista um débito de valor aleatorio numa conta cujo o seu id está compreendido entre determinada gama de ids*/
                this.contasBancarias.get(id).makeMovimento(valor,"Debito",this.contasBancarias.get(id).getTitularConta());
            }
        }
        menu();
    }

}