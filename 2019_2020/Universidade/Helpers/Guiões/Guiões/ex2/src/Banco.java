public class Banco {

    /** Contas do Banco **/
    private double[] contas;

    /**
     * Método construtor do Banco:
     *  -> Cria um banco com n contas
     * @param n
     */
    public Banco(int n){
        this.contas = new double[n];

        for(int i = 0; i < n; i++)
            contas[i] = 0;
    }

    /**
     * Consulta da conta n
     * @param n
     * @return montante da conta
     */
    public synchronized double consulta(int n){
        return this.contas[n];
    }

    /**
     * Credito de um determinado valor da conta n
     * @param n
     * @param valor
     */
    public synchronized void credito(int n, int valor){
        // this.contas[n] -= (this.contas[n] - valor >= 0) ? valor : 0;
        this.contas[n] -= valor;
    }

    /**
     * Debito de um determinado valor
     * @param n
     * @param valor
     */
    public synchronized void debito(int n, int valor){
        this.contas[n] += valor;
    }

}
