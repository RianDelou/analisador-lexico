import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        AnalisadorLexico analisador = new AnalisadorLexico(); // instanciando o objeto 
        String argumentos = "";
        char analiseCharArgs;
      

        System.out.println("----------código fonte----------");
        argumentos = input.nextLine(); // todos os argumentos digitados

        for (int i = 0; i < argumentos.length(); i++) {

            analiseCharArgs = argumentos.charAt(i); // armazena o caracter atual.

            if (analiseCharArgs == ' ') { // caso o caracter analisador for igual a ' ', quer dizer que o primeiro lexema foi formado //IMPORTANTE: CONDIÇÃO DO ULTIMO CARACTER DO CÓDIGO!!

                analisador.categorizarLexema(); // categorizando o lexema      

            }

            if (analiseCharArgs != ' ') { //lexema é apenas a palavra!
                analisador.adicionarAoLexema(analiseCharArgs); // incrementa todos os caracteres para formar o lexema
            } 

        }

    }

}