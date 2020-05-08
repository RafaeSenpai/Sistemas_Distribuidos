import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


import static java.lang.System.exit;

public class BancoOLD {
    private String nomeBanco;
    private Map<Integer,Conta> contasBancarias;
    private LocalDateTime myDateObj;
    private String data_CriacaoConta;
    private Scanner sc = new Scanner(System.in);

    /**Construtor vazio*/
    public Banco(){
        this.nomeBanco = "nome_qualquer";
        this.contasBancarias = new HashMap<>();
    }


    /**Construtor parametrizado*/
    public Banco(String nome, Map<Integer,Conta> bd){
        this.nomeBanco = nome;
        this.contasBancarias = new HashMap<>();
    }


    /**Construtor copia*/
    public Banco(Banco bnk){
        this.nomeBanco = bnk.getNomeBanco();
        this.contasBancarias = bnk.getContasBancarias();
    }


    public String getNomeBanco(){
        return this.nomeBanco;
    }


    /**Devolve um objecto com a listagem de todas as contas existentes no banco <chave, valor>
     * Este metodo é usado no construtor copia
     * */
    public HashMap<Integer,Conta> getContasBancarias(){
        return new HashMap<>(this.contasBancarias);/*  <<<<<<<<<<<<<<<<<<<<------------------------------------------   Perceber se é deep ou shallow clone*/
    }


    /**Imprime uma listagem de todas as contas no banco, mas sem apresentar os movimentos*/
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


    public void setMovimentoEntreContas(float valor, int id_ct_origem, int id_ct_destino) {

        if (contasBancarias.get(id_ct_origem).getSaldoConta() >= valor) {
            Movimento newMovOrigem = new Movimento(valor, "Debito", contasBancarias.get(id_ct_origem).getTitularConta());
            Movimento newMovDestino = new Movimento(valor, "Credito", contasBancarias.get(id_ct_origem).getTitularConta());

            contasBancarias.get(id_ct_origem).getLstMovimentos().add(newMovOrigem);
            contasBancarias.get(id_ct_destino).getLstMovimentos().add(newMovDestino);
            contasBancarias.get(id_ct_origem).refreshSaldoConta(); /**   <<<------------------------------------------------------Confirmar se está a funcionar*/
            contasBancarias.get(id_ct_destino).refreshSaldoConta(); /**   <<<------------------------------------------------------Confirmar se está a funcionar*/
        }
    }


    public Banco clone(){
        return new Banco(this);
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


    /**Criar conta bancaria*/
    public void criarConta(){
        LocalDateTime dataObject;
        DateTimeFormatter Objectformat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String data;
        dataObject = LocalDateTime.now();
        data = dataObject.format(Objectformat);
        Scanner newSc = new Scanner(System.in);
        float valor_c;
        ArrayList<Movimento> newLstMovs = new ArrayList<>();

        System.out.println("Insira o nome do titular:");
        String nome_t = newSc.nextLine();
        System.out.println("Insira o montante de abertura:");
        valor_c = newSc.nextFloat();
        Conta conta = new Conta(data,nome_t,valor_c,newLstMovs,contasBancarias.size());
        contasBancarias.put(conta.getNumConta(),conta);
        contasBancarias.get(conta.getIdConta()).refreshSaldoConta();
        System.out.println(getConta(conta.getNumConta()));

        promptEnterKey();
    }


    /**Obter informação basica de conta  */
    public void getBasicCountInfo(){
        System.out.println("Insira o numero da conta:");
        int id = sc.nextInt();
        System.out.println(getConta(id));

        promptEnterKey();
    }


    /**Obter informação detalhada de conta  */
    public void getDetailCountInfo(){
        System.out.println("Insira o numero da conta:");
        int id = sc.nextInt();
        System.out.println(getContaAllInformation(id));

        promptEnterKey();
    }


    /**Listar todas as contas existentes    */
    public void getAllCounts(){
        this.contasBancarias.forEach((k,v)->System.out.println(v.toStringWithoutMovements()));

        promptEnterKey();
    }


    /**Listar movimentos de conta   */
    public void getAllCountMovements(){
        System.out.println("Insira o numero da conta:");
        int id = sc.nextInt();
        System.out.println(getMovimentosConta(id));

        promptEnterKey();
    }


    /**Fazer transação entre contas */
    public void makeTransaction(){
        System.out.println("Insira o valor a ser transacionado");
        float montante = sc.nextFloat();
        System.out.println("Insira o numero da conta de origem:");
        int id1 = sc.nextInt();
        System.out.println("Insira o numero da conta destino:");
        int id2 = sc.nextInt();

        setMovimentoEntreContas(montante, id1, id2);

        promptEnterKey();

    }


    /**Fazer depósito em conta  */
    public void setDeposito(){
        System.out.println("Montante a depositar:");
        float valor = sc.nextFloat();
        System.out.println("Indique o id da conta:");
        int id = sc.nextInt();
        Movimento newMov = new Movimento(valor, "Credito", "Proprio");
        contasBancarias.get(id).getLstMovimentos().add(newMov);

        promptEnterKey();
    }


    /**Fazer débito em conta  */
    public void setLevantamento(){
        System.out.println("Montante a levantar:");
        float valor = sc.nextFloat();
        System.out.println("Indique o id da conta:");
        int id = sc.nextInt();

        if(contasBancarias.get(id).getSaldoConta()-valor>=0) {
            Movimento newMov = new Movimento(valor, "Debito", "Proprio");
            contasBancarias.get(id).getLstMovimentos().add(newMov);
        }

        promptEnterKey();
    }


    /**Verificar existencia de conta    */
    public void verifExistencia(){
        System.out.println("Indique o id da conta:");
        int id = sc.nextInt();
        System.out.println("Resposta: " + contasBancarias.containsKey(id));

        promptEnterKey();
    }


    /**Numero de contas na instituição  */
    public int getNumCounts(){
        return this.contasBancarias.size();
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
        /*Criar bd a ser usada*/
        //Map<Integer,Conta> bd_contasBancarias = new HashMap<>(); /*<<<---   tenho duvidas de se devo ou nao inicializar o hashmap neste local, visto que a mesma é inicializada quando a pass para o construtor parametrizado do banco!!!!!*/

        //Banco bd_Banco = new Banco("Caixa CGD", contasBancarias);

        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.myDateObj = LocalDateTime.now();
        this.data_CriacaoConta = myDateObj.format(myFormatObj);


        /**O ciclo a baixo vai criar 100 contas bancarias*/
        for(int i=1; i<100; i++){
            this.myDateObj = LocalDateTime.now();
            Conta newCount = new Conta(data_CriacaoConta,nomeAleatorio(),0,new ArrayList<Movimento>(),contasBancarias.size());
            contasBancarias.put(newCount.getNumConta(), newCount);
        }


        //instância um objeto da classe Random usando o construtor básico
        Random gerador = new Random();
        /**O ciclo a baixo cria até 1000 transações nas contas bancárias, transações são feitas entre contas que já pertencem ao banco*/
        for(int j=1;j<gerador.nextInt(1000);j++){

            /**Vai gerar um valor aleatorio compreendido entre 0 e 1201 (max = 1200 + (0...0.9(9))*/
            float valor = gerador.nextInt(1200) + gerador.nextFloat();
            setMovimentoEntreContas(valor,gerador.nextInt(100),gerador.nextInt(100));
        }


        /**Atribui crédito a todas as contas*/
        /*
        for(int k=1; k<99; k++) {
            float valor = gerador.nextInt(5000) + gerador.nextFloat();
            int id = gerador.nextInt(99);
            Movimento mv = new Movimento(valor,"Credito","Proprio");
            contasBancarias.get(id).getLstMovimentos().add(mv);
          //  contasBancarias.get(id).refreshSaldoConta();
        }
        */


        /**Vai gerar 1000 registos de simples depositos e levantamentos pelas contas existentes*/
        for(int k=1; k<gerador.nextInt(1000); k++){
            float valor = gerador.nextInt(500) + gerador.nextFloat();
            int id = gerador.nextInt(99);
            Movimento mv;

            if(gerador.nextInt(2) == 1){
                /**Regista um credito de valor aleatorio até 500 numa conta cujo o seu id está compreendido entre os id 0 e 100*/
                mv = new Movimento(valor,"Credito","Proprio");
                contasBancarias.get(id).getLstMovimentos().add(mv);
                contasBancarias.get(id).refreshSaldoConta();
            }else{
                /**Regista um débito de valor aleatorio até 500 numa conta cujo o seu id está compreendido entre os id 0 e 100*/
                if(contasBancarias.get(id).getSaldoConta()-valor>=0) {
                    mv = new Movimento(valor,"Debito","Proprio");
                    contasBancarias.get(id).getLstMovimentos().add(mv);
                    contasBancarias.get(id).refreshSaldoConta();
                }
            }
        }
        menu();
    }

    public void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
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
                    criarConta();
                    break;
                case 2:
                    getBasicCountInfo();
                    break;
                case 3:
                    getDetailCountInfo();
                    break;
                case 4:
                    getAllCounts();
                    break;
                case 5:
                    getAllCountMovements();
                    break;
                case 6:
                    makeTransaction();
                    break;
                case 7:
                    setDeposito();
                    break;
                case 8:
                    setLevantamento();
                    break;
                case 9:
                    verifExistencia();
                    break;
                case 10:
                    System.out.println("Numero de cliente: " + this.getNumCounts());
                    promptEnterKey();
                    break;
                case 11:
                    exit(0);
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    public static void main(String[] args) {

        Banco myBanc = new Banco();

        myBanc.menu();
    }
}