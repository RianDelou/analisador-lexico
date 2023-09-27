import java.util.ArrayList;
import java.util.regex.Pattern;

public class AnalisadorLexico {

    private int posicionamento = 0;
    private String allArgs;
    private String lexema = "";
    private String lexemaAux = "";
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

    public String getLexemaAux() {
        return lexemaAux;
    }

    public void setLexemaAux(String lexemaAux) {
        this.lexemaAux = lexemaAux;
    }

    public int getPosicionamento() {
        return posicionamento;
    }

    public void setPosicionamento(int posicionamento) {
        this.posicionamento = posicionamento;
    }

    public String getAllArgs() {
        return allArgs;
    }

    public void setAllArgs(String allArgs) {
        this.allArgs = allArgs;
    }

    public ArrayList<String> getPalavrasReservadas() {
        return palavrasReservadas;
    }

    public void setPalavrasReservadas(ArrayList<String> palavrasReservadas) {
        this.palavrasReservadas = palavrasReservadas;
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

        if (isComentario(lexema)) { // depois mudar na main pra nao pegar mais a linha do comentario

            for (int i = this.posicionamento; this.allArgs.charAt(i) != '\n'; i++) { // adicionando tudo até o final de
                                                                                     // uma linha
                this.posicionamento = i;
                this.lexema += this.allArgs.charAt(i);
            }

            System.out.println(this.lexema + " Comentario!");
            this.lexema = "";
        }

        if (isNum(lexema)) {

            if (isNumInt(lexema)) {
                System.out.println(this.lexema + " Número inteiro!");
                this.lexema = "";
            } else if (isNumDec(lexema)) {
                System.out.println(this.lexema + " Número decimal!");
                this.lexema = "";
            }

        } else if (isPalavraReservada(lexema)) { // primeiro verificar se tem palavra reservada

            System.out.println(this.lexema + " Palavra Reservada!");
            this.lexema = "";

        } else if (isConstanteDeTexto(lexema)) { // PRECISA DO LEXEMAAUX (!!!!!!!!!!!!!!!)

            System.out.println(this.lexemaAux + " Constante de texto!");
            this.lexemaAux = "";
            
        }

        if (lexema.equals("\n")) { // quebra de linha
            this.lexema = "";
        }

        this.lexema = ""; // TIRAR ISSO DEPOIS
    }

    private boolean isNum(String argumentos) {

        if (argumentos == "" || argumentos.charAt(0) == '.' || argumentos.charAt(argumentos.length() - 1) == '.') { // caso
                                                                                                                    // padrão
            return false;
        }

        int count = 0;

        for (int i = 0; i < argumentos.length(); i++) {
            char analiseChar = argumentos.charAt(i);

            if (analiseChar == '.') {
                count++;

                if (count == 2) {
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

        if (lexema == "") { // caso padrão
            return false;
        }

        for (int i = 0; i < lexema.length(); i++) {
            char analiseChar = lexema.charAt(i);

            if (analiseChar == '.') {
                return false;
            }

        }
        return true;
    }

    private boolean isNumDec(String lexema) {

        if (lexema == "") { // caso padrão
            return false;
        }

        for (int i = 0; i < lexema.length(); i++) {
            char analiseChar = lexema.charAt(i);

            if (analiseChar == '.') {
                return true;
            }
        }
        return false;
    }

    public boolean isPalavraReservada(String lexema) {

        if (lexema == "") { // caso padrão
            return false;
        }

        if (this.palavrasReservadas.contains(lexema)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isConstanteDeTexto(String lexema) {

        if (lexema == "") {
            return false;
        }

        this.lexemaAux = "";

        for (int i = 0; i < lexema.length(); i++) {
            char analiseChar = lexema.charAt(i);

            if (analiseChar == '"') {

                for (int j = i; j < lexema.length(); j++) {

                    this.lexemaAux += lexema.charAt(j);
                    analiseChar = lexema.charAt(j);

                    lexema.replace(lexema.charAt(j), ' ');

                    if (j > i) {
                        if (analiseChar == '"') {
                            return true;
                        }
                    }

                }

            }

        }

        return false;
    }

    public boolean isComentario(String lexema) {

        if (lexema == "") { // caso padrão
            return false;
        }

        int aux = 0;

        for (int i = 0; i < lexema.length(); i++) {
            char analiseChar = lexema.charAt(i);

            if (analiseChar == '/') {

                aux += 1;

            } else if (analiseChar != '/') {
                aux -= 1;
            }

            if (aux == 2) {
                return true;
            }

        }

        return false;
    }

    public boolean isOperador() {

    }

    public boolean isSimboloEspecial() {

    }

    public boolean isIdentificador(String lexema) {

    }

}