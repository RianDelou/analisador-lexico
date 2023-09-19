import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("----------código fonte----------");
        String argumentos = input.next();

        if (isNum(argumentos)) {
            if (isNumInt(argumentos)) {
                System.out.println("Número inteiro!");
            } else if (isNumDec(argumentos)) {
                System.out.println("Número decimal!");
            }
        }

    }

    public static boolean isNum(String argumentos) {
        if (argumentos.charAt(0) == '.' || argumentos.charAt(argumentos.length() - 1) == '.') { //caso padrão
            return false;
        }

        int count = 0;

        for (int i = 0; i < argumentos.length(); i++) {
            char analiseChar = argumentos.charAt(i);

              if (analiseChar == '.') {
                count++; 
    
                if (count > 1) {
                    return false;
                }

            }

            if (!(analiseChar >= '0' && analiseChar <= '9') && analiseChar != '.') {
                return false;
            } 
        }
        return true;
    }

    public static boolean isNumInt(String argumentos) {

        for (int i = 0; i < argumentos.length(); i++) {
            char analiseChar = argumentos.charAt(i);

            if (analiseChar == '.') {
                return false;
            } 

            }
        return true;
    }

    public static boolean isNumDec(String argumentos) {

        for (int i = 0; i < argumentos.length(); i++) {
            char analiseChar = argumentos.charAt(i);

            if (analiseChar == '.') {   
                return true;
            }
        }
        return false;
    }
    
 }