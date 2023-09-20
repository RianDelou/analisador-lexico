import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        AnalisadorLexico analisador = new AnalisadorLexico(); // Instancia o objeto AnalisadorLexico

        System.out.println("Digite várias linhas de texto (pressione Enter após cada linha).");
        System.out.println("Digite uma linha em branco para encerrar a entrada:");

        StringBuilder inputText = new StringBuilder();
         String argumentos;
         String armazenar = "";
        while (true) {
            argumentos = input.nextLine(); // Lê uma linha de entrada

            armazenar += argumentos;
            if (argumentos.isEmpty()) {
                break; // Sai do loop se a linha estiver em branco
            }

            armazenar += " JUMPLINE ";
            inputText.append(argumentos).append("\n");
        }

        System.out.println(armazenar);

    }
}
