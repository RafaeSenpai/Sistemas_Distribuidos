import java.util.*;

import static java.lang.System.exit;

public class Banco{
    Map<Integer,Conta> bancoBD = new HashMap<>();
    Scanner sc = new Scanner(System.in);
    int id;
    float valor;

    /**
     * NOTA: FOI RETIRADO O static DA MAIN!
     *
     * Static serve para referenciar todos aqueles atributos/métodos de classe, ou seja, que
     * podem ser acedidos diretamente da definição da classe, sem precisar instanciar nenhum
     * objeto.
     * */

    public static void main(String[] args) {

        Banco myBanc = new Banco();

        myBanc.menu();
    }


    public void menu(){
        int opcao = 0;

        do {
            System.out.println("\n\n### FULLBANCK - Painel de utilizador ###");
            System.out.println("\n                  ===============================================");
            System.out.println("                  |     0 - Carregar contas bancarias           |");
            System.out.println("                  |     1 - Criar conta bancaria                |");
            System.out.println("                  |     2 - Consultar saldo de conta bancaria   |");
            System.out.println("                  |     3 - Consultar movimentos da conta       |");
            System.out.println("                  |     4 - Consultar débitos da conta          |");
            System.out.println("                  |     5 - Consultar créditos da conta         |");
            System.out.println("                  |     6 - Fazer depósito em conta             |");
            System.out.println("                  |     7 - Fazer débito em conta               |");
            System.out.println("                  |     8 - Verificar existencia de conta       |");
            System.out.println("                  |     9 - Sair                                |");
            System.out.println("                  ===============================================\n");
            System.out.print("\n");
            opcao = sc.nextInt();

            switch (opcao) {
                case 0:
                    povoarBD();
                    break;
                case 1:
                    break;
                case 2:
                    getSaldo();
                    break;
                case 3:
                    viewMoviments();
                    break;
                case 4:
                    viewDebitos();
                    break;
                case 5:
                    viewCreditos();
                    break;
                case 6:
                    setDeposito();
                    break;
                case 7:
                    setDebito();
                    break;
                case 8:
                    verifExistConta();
                    break;
                case 9:
                    exit(0);
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    public void povoarBD(){
        Conta contaA = new Conta(1);
        Conta contaB = new Conta(2);
        Conta contaC = new Conta(3);
        Conta contaD = new Conta(4);
        Conta contaE = new Conta(5);

        bancoBD.put(contaA.getNumConta(), contaA);
        bancoBD.put(contaB.getNumConta(), contaB);
        bancoBD.put(contaC.getNumConta(), contaC);
        bancoBD.put(contaD.getNumConta(), contaD);
        bancoBD.put(contaE.getNumConta(), contaE);

        menu();

    }


    public void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }


    /**Apresenta para o utiliador uma listagem de todos os movimentos da sua conta*/
    public void viewMoviments(){
        Scanner sc = new Scanner (System.in);
        System.out.println("Imnsira o numero de conta:");
        int id = sc.nextInt();

        System.out.println("****** Listagem de movimentos na conta ******");
        if(!bancoBD.get(id).getMovimentos().isEmpty()){
            bancoBD.get(id).getMovimentos().forEach(movimento -> {
                System.out.println("Tipo          Quantia");
                System.out.println(movimento.getTipoMovimento() + "   " + movimento.getMontante());
            });
        }else{
            System.out.println("\n\n--------- SEM MOVIMENTOS REGISTADOS ---------\n\n");
        }
        System.out.println("*********************************************");
    }


    /** Apresenta para ao utilizador uma listagem de todos os créditos feita na sua conta*/
    public void viewCreditos(){
        Scanner sc = new Scanner (System.in);
        System.out.println("Insira o número de conta:");
        int id = sc.nextInt();

        System.out.println("******Créditos - Valores******");
        bancoBD.get(id).getMovimentos().forEach(mv->{
            if(mv.getTipoMovimento().equals("Credito")){
                System.out.println(mv.getMontante());
            }
        });
    }

    /**Apresenta para o utilizador uma listagem de todos os debitos feitos na sua conta*/
    public void viewDebitos(){
        Scanner sc = new Scanner (System.in);
        System.out.println("Insira o número de conta:");
        int id = sc.nextInt();

        System.out.println("******Créditos - Valores******");
        bancoBD.get(id).getMovimentos().forEach(mv->{
            if(mv.getTipoMovimento().equals("Debito")){
                System.out.println(mv.getMontante());
            }
        });
    }


    public void verifExistConta(){
        System.out.println("Insira o código da conta:");
        id = sc.nextInt();
        if(bancoBD.containsKey(id)){
            System.out.println("A conta existe!");
        }else{
            System.out.println("A conta não existe!");
        }
        //promptEnterKey();
    }

    public void getSaldo(){
        System.out.println("Insira o código da conta:");
        id = sc.nextInt();
        System.out.println("Saldo: " + this.bancoBD.get(id).getSaldo());
        //promptEnterKey();
    }


    public void setDeposito() {
        System.out.println("Insira o código da conta:");
        id = sc.nextInt();
        System.out.println("Quantia:");
        valor = sc.nextFloat();
        this.bancoBD.get(id).setMovimento(valor,"Credito");
        //promptEnterKey();
    }


    public void setDebito() {
        System.out.println("Insira o código da conta:");
        id = sc.nextInt();
        System.out.println("Quantia:");
        valor = sc.nextFloat();
        this.bancoBD.get(id).setMovimento(valor,"Debito");
        //promptEnterKey();
    }
}
