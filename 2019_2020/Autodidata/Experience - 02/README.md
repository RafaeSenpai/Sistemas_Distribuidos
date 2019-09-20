<img src="../READMEs_sorces/Multithreading-Java.png" alt="Sistemas Distribuidos - Rafael Alves" align="center"/>

<h1 align="center">Part 2<br>Basic Thread Synchronization</h1>
<br>
<h2>Sincronização das Threads em java</h2>

<p><strong>App1</strong> - 1º metodo para iniciar threads.<br>
                           Exemplo em que ambos os loops estão a correr ao mesmo tempo em threads diferentes, mas apresentando valores repetidos.<br>
                           <em>&emsp;Obs: uso de threads mas sem tratamento das mesmas;</p></em>
<br>

<p>- Como partilhar a mesma informação entre threads diferentes<br></p>
<p>- como terminar um processo corretamente <br></p>
<p>- Podemos considerar de duas threads neste codigo em que a <em>main(String[] args)</em> é uma thread e que a mesma quando corre a linha de codigo: 
<em>&emsp;&emsp;&emsp;proc1.start()</em> <br>
...executa outra thread em simultaneo <em>(class Processor extends ...)</em><br><br></p>
<p>- Temos duas threads ambas a aceder á mesma variavel (running)<br></p>
<p><strong>- Em alguns sistemas, uma thread nao espera que outras threads modifiquem o estado de alguma informação, isto leva a uma interpretação de que o que está a acontecer é que a variavel <em>running</em> que está constantemente a ser verificada na função <em>run()</em> seja uma cópia da mesma variavel declarada inicialmente na <em>class Processor()</em> e que é iniciada com valor a True. ESta situação leva a que mesmo ao alterar o valor da variavel <em>running</em> atravéz da função <em>public void shutdown()</em>, quando essa mesma variavel for novamente verificada (no metodo <em>run()</em>) não será verificada nenhuma alteração, isto é, a variavel "running" manteve sempre o seu valor igual a True. Para resolver esta situação, aquando a declaração da variavel running esta deve ser declarada como: <br>&emsp;&emsp;&emsp;<em>private volatile boolean running = true;</em><br>
Desta forma temos a garantia de que a variavel que está em constante verificação é realmente alterada.<strong><br></p><br><br>
<p>- Definir uma variavel como <em>volatile<em> salvaguarda o caso das threads fazerem chashing de variaveis, o que acontece naturalmente quando as variaveis apenas são alteradas sempre pela mesma thread.<br></p>


- Se quisermos alterar uma variavel noutra thread teremos que usar <em>volatile</em><br></p>



- Na main, definir um genero de pausa até que se dê ordem de término ao Metodo que se encontra a correr 
    Para isso vai-se usar   



    Quer alguma maneira de parar até chamar-mos o metodo de paragem (public void Shutdown) darmos ordem de paragem ao metodo que se encontra a correr

    scanner é uma class de scan input streams que será usada para detetar uma nova linha 


TRata-se de uma forma de que pode ser usada para se quisermos terminar uma thread ou multiplas threads corretamente a partir de outra thread 