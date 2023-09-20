import java.util.ArrayList;
import java.util.regex.Pattern;

public class AnalisadorLexico {
    private String lexema = "";
    private String token = "";
    private ArrayList<String> palavrasReservadas = new ArrayList<String>();
    private Pattern identificador = Pattern.compile("[a-zA-Z][a-zA-Z0-9]*");
    private ArrayList<String> tabelaSimbolos = new ArrayList<String>();

    public AnalisadorLexico() {
        this.palavrasReservadas.add("int");
        this.palavrasReservadas.add("float");
        this.palavrasReservadas.add("char");
        this.palavrasReservadas.add("boolean");
        this.palavrasReservadas.add("void");
        this.palavrasReservadas.add("if");
        this.palavrasReservadas.add("else");
        this.palavrasReservadas.add("for");
        this.palavrasReservadas.add("while");
        this.palavrasReservadas.add("scanf");
        this.palavrasReservadas.add("println");
        this.palavrasReservadas.add("main");
        this.palavrasReservadas.add("return");
    }

    public Pattern getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Pattern identificador) {
        this.identificador = identificador;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ArrayList<String> getTabelaSimbolos() {
        return tabelaSimbolos;
    }

    public void setTabelaSimbolos(ArrayList<String> tabelaSimbolos) {
        this.tabelaSimbolos = tabelaSimbolos;
    }

    public void adicionarAoLexema(char caracter) {
        this.lexema += caracter;
    }

    public void categorizarLexema() {

        if (isNum(lexema)) {
                    
            if (isNumInt(lexema)) {
                System.out.println(this.lexema+" Número inteiro!");
                this.lexema = "";
            } else if (isNumDec(lexema)) {
                System.out.println(this.lexema+" Número decimal!");
                this.lexema = "";
            }

        } else if  (isPalavraReservada(lexema)) { //primeiro verificar se tem palavra reservada 
            System.out.println(this.lexema+" Palavra Reservada!");
            this.lexema = "";
        }

    }

    private boolean isNum(String argumentos) { //FALTA IDENTIFICAR NUMEROS NEGATIVOS E POSITIVOS
        if (argumentos.charAt(0) == '.' || argumentos.charAt(argumentos.length() - 1) == '.') { // caso padrão
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

    private boolean isNumInt(String lexema) {

        for (int i = 0; i < lexema.length(); i++) {
            char analiseChar = lexema.charAt(i);

            if (analiseChar == '.') {
                return false;
            }

        }
        return true;
    }

    private boolean isNumDec(String lexema) {

        for (int i = 0; i < lexema.length(); i++) {
            char analiseChar = lexema.charAt(i);

            if (analiseChar == '.') {
                return true;
            }
        }
        return false;
    }

    public boolean isPalavraReservada(String lexema) {
        if (this.palavrasReservadas.contains(lexema)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isConstanteDeTexto(String lexema) {
        
    }

    public boolean isComentarios(String lexema) {

    }

    public boolean isOperadores() {

    }

    public boolean isSimbolosEspeciais() {

    }

    public boolean isIdentificador(String lexema) {

    }

}
