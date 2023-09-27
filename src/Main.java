import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        AnalisadorLexico analisador = new AnalisadorLexico(); // instanciando o objeto
        String argumentos = "";
        String allArgs = "";
        char analiseCharArgs;

        System.out.println("----------código fonte----------");

        while (true) {
            argumentos = input.nextLine();

             allArgs += argumentos; // todos os argumentos

            if (argumentos.isEmpty()) {
                break; // sai do loop se a linha estiver em branco
            }

            allArgs += " \n "; // o \n tem que ter esse espaço entre eles, para que não bugue quando for analisar o comentário

        }

        analisador.setAllArgs(allArgs); // colocando todos os argumentos

        for (int i = 0; i < allArgs.length(); i++) {

            analiseCharArgs = allArgs.charAt(i); // armazena o caracter atual.
            analisador.setPosicionamento(i); // salvando a localização atual do char

            if (analiseCharArgs == ' ') { // caso o caracter analisador for igual a ' ', quer dizer que o primeiro
                                          // lexema foi formado //IMPORTANTE: CONDIÇÃO DO ULTIMO CARACTER DO CÓDIGO!! 
                                          
                analisador.categorizarLexema(); // categorizando o lexema
            
            } else {
                analisador.adicionarAoLexema(analiseCharArgs); // incrementa todos os caracteres para formar o lexema
            }  
            
            i = analisador.getPosicionamento(); // colocando o valor de i como o posicionamento, que seria necesasrio caso ache por exemplo um comentario até o final da linha.

        }

        System.out.println(allArgs);

    }

}