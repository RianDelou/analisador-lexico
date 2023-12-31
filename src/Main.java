import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        File arquivo = new File("/home/riaan/codigofonte.txt"); // pega arquivo.txt

        Scanner input = new Scanner(arquivo);
        AnalisadorLexico analisador = new AnalisadorLexico(); // instanciando o objeto
        String argumentos = "";
        String allArgs = "";
        char analiseCharArgs;

        System.out.println("----------código fonte----------");

        while (input.hasNextLine()) { // LEMBRAR DAQUELE IF DE UMA LINHA !!!
            argumentos = input.nextLine();

            allArgs += argumentos; // todos os argumentos

            allArgs += " \n "; // o \n tem que ter esse espaço entre eles, para que não bugue quando for analisar o comentário

        }

        analisador.setAllArgs(allArgs); // colocando todos os argumentos

        for (int i = 0; i < allArgs.length(); i++) {

            analiseCharArgs = allArgs.charAt(i); // armazena o caracter atual.
            analisador.setPosicionamento(i); // salvando a localização atual do char

            if (analiseCharArgs == ' ' || analisador.isSimboloEspecial(analiseCharArgs)) { // caso o caracter analisador for igual a ' ', ou igual a simbolo especial quer dizer que o primeiro
                                          // lexema foi formado //IMPORTANTE: CONDIÇÃO DO ULTIMO CARACTER DO CÓDIGO!! 

                analisador.categorizarLexema(); // categorizando o lexema

                if (analisador.isSimboloEspecial(analiseCharArgs)) {  //se for igual apenas a simbolo especial, adicione o simbolo especial
                    
                    analisador.getListaTokens().add("(SimboloEsp, "+analiseCharArgs+" )");
                }
            
            } else {
                analisador.adicionarAoLexema(analiseCharArgs); // incrementa todos os caracteres para formar o lexema
            }  
            
            i = analisador.getPosicionamento(); // colocando o valor de i como o posicionamento, que seria necesasrio caso ache por exemplo um comentario até o final da linha.

        }

        analisador.imprimirListas();
        System.out.println("\ntodos os argumentos: \n"+allArgs+"\n");


    }

}