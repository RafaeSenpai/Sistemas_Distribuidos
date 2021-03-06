<img src="../READMEs_sorces/Multithreading-Java.png" alt="Sistemas Distribuidos - Rafael Alves" align="center"/>

<h1 align="center">Part 3<br>The Synchronized Keyword</h1>
<br>
<h2>Sincronização das Threads em java - Synchronized</h2><br>


<p> - Apresentação de um a forma de garantir que quando uma thread está a aceder a um recurso nenhuma outra thread consegue aceder a esse mesmo recurso.</p>

<p> - Uma simples forma de resolver o problema a cima seria tornar a variavel num <em>atomic integer</em> porque este tipo de variavel possui um metodo de incremento num só passo, isto é:<br>

Incremento é mais que um passo:
```javascript
	count = count + 1;
```
obs.: 
> 1º Primeiro é inicializada a variavel count;<br>
> 2º É criada uma copia dessa variavel e incrmentada 1 unidade á mesma;<br>
> 3º O resultado obtido é guardado na variavel count original.

<br>
Incremento em um passo:

```javascript
	count++;
```
obs.: 
> É inicializada a variavel incrementando uma unidade ao seu valor.




<br><br><p>- Neste caso o <strong><em>volatile</em></strong> não irá resolver a situação porque o problema não está relacionado com questões de <em>caching</em>, mas sim com a sincronização das threads que estão a executar o trabalho.</p>

<p>- A solução está em criar o metodo que queremos que as threads executem, mas atribuindo a esse mesmo metodo a flag synchronized.</p>

```javascript
    public synchronized void increment(){
        ...
    }
```

<p>- O que o synchronized faz é que cada objeto em java possui um bloqueador, bloqueador esse que desempenha o seguinte papel...<br>

                ...Quando uma thread tenta aceder a algo que esteja a ser usado 
                por outra thread o syncronize coloca a thread que está a querer 
                aceder em espera enquanto a thread que já se encontra a trabalhar 
                não terminar o seu trabalho. Quando a thread, que está a aceder 
                a um recurso finaliza as suas operações sobre esse mesmo recurso, 
                este fica disponivel para outra thread e quem é responsavel por 
                esse controlo de acesso a recursos de maneira ordeira é o 
                                synchronized.

<p>- Graças ao papel desempenhado pelo synchronized não é necessário usar-se a flag <em>volatile</em> nas variaveis acedidas por varias threads, pois com o synchronized, os recursos são acedidos apenas por uma thread de cada vez sem nenhuma intrusão por parte de outro agente externo , logo não haverá problemas de caching a ser tratados</p>