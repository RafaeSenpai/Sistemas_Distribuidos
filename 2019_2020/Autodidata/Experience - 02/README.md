<img src="../READMEs_sorces/Multithreading-Java.png" alt="Sistemas Distribuidos - Rafael Alves" align="center"/>

<h1 align="center">Part 2<br>Basic Thread Synchronization</h1>
<br>
<h2>Sincronização das Threads em java</h2><br>

<p>- Como partilhar a mesma informação entre threads diferentes<br></p>
<p>- Como terminar um processo corretamente uma thread<br></p>
<p>- Podemos considerar a existência duas threads neste codigo em que a <strong><em>main(String[] args)</em></strong> é uma thread e que a mesma quando corre a linha de codigo: <br></p>

```javascript
    proc1.start()
```
<br>
...executa outra thread em simultaneo <strong><em>(class Processor extends ...).</em></strong><br></p>
<p>- Temos duas threads ambas a aceder á mesma variavel <strong><em>(running)</em></strong><br></p>
<p><strong>- Em alguns sistemas, uma thread nao espera que outras threads modifiquem o estado de alguma informação, isto leva a uma interpretação de que o que      está a acontecer é que a variavel <em>running</em> que está constantemente a ser verificada na função <em>run()</em> seja uma cópia da mesma variavel declarada     inicialmente na <em>class Processor()</em> e que é iniciada com valor a True. <br>Esta situação leva a que mesmo ao alterar o valor da variavel <em>running</em>    atravéz da função <strong><em>public void shutdown()</em></strong>, quando essa mesma variavel for novamente verificada (no metodo <em>run()</em>) não será         verificada nenhuma alteração, isto é, a variavel "running" manteve sempre o seu valor igual a True. Para resolver esta situação, aquando a declaração da            variavel running esta deve ser declarada como: <br>

```javascript
    private volatile boolean running = true;
```
<br>
Desta forma temos a garantia de que a variavel que está em constante verificação é realmente alterada.</strong></p><br>
<p>- Definir uma variavel como <em>volatile<em> salvaguarda o caso das threads fazerem chashing de variaveis, o que acontece naturalmente quando as variaveis apenas são alteradas sempre pela mesma thread.<br></p>


<p>- Se quisermos alterar uma variavel noutra thread teremos que usar <em>volatile</em><br></p>


    - Quere-se alguma forma de parar até chamar-mos o metodo de paragem (public void Shutdown) darmos ordem de paragem ao metodo que se encontra a correr
    - Na main, definir um genero de pausa até que se dê ordem de término ao Metodo que se encontra a correr para isso vai-se usar   
```javascript
        . main(String[] args)
        . proc1.start()
        . class Processor extends Thread { ...
        . class Processor()
        . public void Shutdown()
        . running
        . volatile
        . private volatile boolean running = true;
```
   
Trata-se de uma forma de que pode ser usada para se quisermos terminar uma thread ou multiplas threads corretamente a partir de outra thread 