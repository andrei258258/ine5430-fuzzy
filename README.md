# Universidade Federal de Santa Catarina
## INE5430 - Controlador Fuzzy para sistema de AutoParking


## Alunos
Andrei Donati<br /> 
Igor Yamamoto<br /> 
Luís Felipe Pelison <br />
<br />
2017-2

## Proposta de trabalho (texto copiado do Moodle da disciplina INE5430)
Proposta de trabalho prático a nível de graduação sobre sistemas fuzzy, sendo disponibilizada a plataforma necessária para o desenvolvimento do trabalho. O trabalho proposto consiste em desenvolver o controlador fuzzy de um motorista para estacionar um caminhão de ré numa doca, utilizando os conhecimentos sobre sistemas fuzzy adquiridos durante as aulas.

A simulação será executada num programa em java já pronto com dois caminhões em pátios separados. Cada caminhão será controlado por uma porta diferente (4321 e 4322) por grupos diferentes, criando uma competição entre grupos no dia da apresentação. Quando o caminhão sair muito da tela ou ultrapassar a posição 1.0 no eixo y, a simulação será encerrada e uma pontuação será calculada para beneficiar quem parar o caminhão com o ângulo mais próximo de 90 graus, mais próximo do centro da doca ((x,y) = (0.5 , 1.0)) e utilizando menos passos.

As coordenadas x e y serão fornecidas entre 0 e 1.0, sendo x=0 a borda esquerda, x=1.0 a borda direita, y=0 o topo da tela e y=1.0 o fundo. A doca (alvo de estacionamento) está em (0.5,1.0). O controle do veículo será feito enviando um valor no intervalo [-1,1] para virar o volante, em -1.0 o volante é virado 30o para a esquerda e em 1.0 o volante é virado 30o para a direita (A cada passo é possível virar o caminhão no máximo 30 graus para ambos os lados. )

Para receber as coordenadas, enviar uma string "r\r\n" (caractere r  + carriage return + newline) ou simplesmente println("r") no java. As coordenadas retornarão numa string com os valores em double, na ordem x y e angulo, delimitadas por tab (\t). Será fornecido um programa em java que implementa o pooling das coordenadas e espera pela entrada do usuário para fornecer uma ação entre -1 e 1 para o volante do carrinho. É possível testar com esse programa digitando a ação desejada [-1,1] e implementar seu trabalho a partir dele.

O grupo deve determinar as entradas do controlador, os conjuntos fuzzy a serem empregados, e as regras sobre as quais será feita a inferência fuzzy. É recomendada a utilização da biblioteca JFuzzyLogic  http://jfuzzylogic.sourceforge.net , ou seu clone em C++/Qt http://sourceforge.net/projects/jfuzzyqt/

## Como usar

### Instalação 
Para executar o código do motorista, deve-ser ter o pacote http://jfuzzylogic.sourceforge.net/html/index.html instalado no pacote. 
Caso o IDE usada para executar o código seja Eclipse, pode-se fazer o download do core do pacote jfuzzy, e adicionar no projeto através:
Properties > Java Build Path > Libraries > Add JAR's

### Execução 
Executar server.java, que executará uma GUI com o trajeto do caminhão.
Executar RemoteDriver.java que será usado para leitura e execução do código FCL (fuzzy) que fará o papel de motorista do caminhão.

### Modificação do sistema Fuzzy
Para modificar o sistema fuzzy, basta alterar o arquivo fuzzy.fcl adicionando regras ou modificando os valores de fuzzyficação  

