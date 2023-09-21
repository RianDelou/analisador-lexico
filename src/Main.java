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

            allArgs += " J "; //talvez mudar o nome

        }

        for (int i = 0; i < allArgs.length(); i++) {

            analiseCharArgs = allArgs.charAt(i); // armazena o caracter atual.

            if (analiseCharArgs == ' ') { // caso o caracter analisador for igual a ' ', quer dizer que o primeiro
                                          // lexema foi formado //IMPORTANTE: CONDIÇÃO DO ULTIMO CARACTER DO CÓDIGO!!

                analisador.categorizarLexema(); // categorizando o lexema

            }

            if (analiseCharArgs != ' ') { // lexema é apenas a palavra!
                analisador.adicionarAoLexema(analiseCharArgs); // incrementa todos os caracteres para formar o lexema
            }

        }

        System.out.println(allArgs);

    }

}