/*
* Programa Java que substitua o comando ‘echo’:
*   Ler linha a linha da consola e imprimir para stdout
*   InputStreamReader, BufferedReader
*
* A classe InputStream faz a leitura de dados binários, não importa a fonte
* (ex.: FileInputStream para ler arquivos, ByteArrayInputStream para ler de
* um array de bytes, socket.getInputStram() para ler de um soquete, System.in
* para ler do console, etc). Já a classe Reader faz a leitura de dados textuais,
* ou seja, strings compostas de caracteres Unicode (code units de 16 bits,
* incluindo surrogate pairs).

* A função do InputStreamReader é servir como um adaptador (Adapter) entre as duas
* classes - lê bytes de um lado, converte em caracteres do outro, através do uso
* de uma codificação de caracteres (encoding). Ou seja, ele é um Reader que recebe
* um InputStream na construção, consumindo dados desse stream e apresentando-os
* como caracteres para o consumidor.

*Enquanto um Reader é uma classe "de mais baixo nível" (sua função é ler caracteres,
* não mais, não menos) o Scanner é uma classe mais especializada, destinada a
* interpretar um texto subjacente de diversas formas (ex.: uma sequência de dígitos
* pode ser um número, true pode ser um boolean), inclusive usando expressões
* regulares. Sua função não é tratar streams, inclusive ela delega essa
* responsabilidade para uma classe especializada - como o InputStream ou o Reader.
* */

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class EchoBrother {

    public static void main (String[] args) {

        //  Abre o input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //criação e inicialização da variavel responavel por armazenar o conteudo a ser apresentado no terminal
        String linha = null;

        try {
            linha = br.readLine();
        } catch (IOException ioe) {
            System.out.println("IO erro tentando ler o nome");
            // System.exit(1) - é usado para terminar o programa quando este termina com erros
            System.exit(1);
        }

        System.out.println(linha);
        // System.exit(0) - é usado para certificar que o programa mesmo este tendo corrido bem seja terminado definitivamente
        System.exit(0);

    }

}