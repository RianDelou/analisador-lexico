import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalisadorLexico {

    private int posicionamento = 0;
    private String allArgs;
    private String lexema = "";
    private String token = "";
    private ArrayList<String> palavrasReservadas = new ArrayList<String>();
    private ArrayList<Character> simbolosEspeciais = new ArrayList<Character>();
    private ArrayList<String> Operadores = new ArrayList<String>();
    private Pattern identificador = Pattern.compile("[a-zA-Z][a-zA-Z0-9]*");
    private ArrayList<String> tabelaSimbolos = new ArrayList<String>();

    public AnalisadorLexico() {

        // TODAS AS PALARAS RESERVADAS
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

        // TODOS OS SIMBOLOS ESPECIAIS
        this.simbolosEspeciais.add('(');
        this.simbolosEspeciais.add(')');
        this.simbolosEspeciais.add('[');
        this.simbolosEspeciais.add(']');
        this.simbolosEspeciais.add('{');
        this.simbolosEspeciais.add('}');
        this.simbolosEspeciais.add(',');
        this.simbolosEspeciais.add(';');
        this.simbolosEspeciais.add('.');

        // TODOS OPERADORES
        this.Operadores.add("=");
        this.Operadores.add("+");
        this.Operadores.add("++");
        this.Operadores.add("-");
         this.Operadores.add("--");
        this.Operadores.add("*");
        this.Operadores.add("/");
        this.Operadores.add("%");
        this.Operadores.add("&&");
        this.Operadores.add("||");
        this.Operadores.add("!");
        this.Operadores.add(">");
        this.Operadores.add(">=");
        this.Operadores.add("<");
        this.Operadores.add("<=");
        this.Operadores.add("!=");
        this.Operadores.add("==");
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

    public ArrayList<Character> getSimbolosEspeciais() {
        return simbolosEspeciais;
    }

    public void setSimbolosEspeciais(ArrayList<Character> simbolosEspeciais) {
        this.simbolosEspeciais = simbolosEspeciais;
    }

    public void adicionarAoLexema(char caracter) {
        this.lexema += caracter;
    }

    public void categorizarLexema() {

        if (lexema.isEmpty()) { // caso padrão
            return;
        }

        if (isComentario(lexema)) { // depois mudar na main pra nao pegar mais a linha do comentario

            for (int i = this.posicionamento; this.allArgs.charAt(i) != '\n'; i++) { // adicionando tudo até o final de
                                                                                     // uma linha
                this.posicionamento = i;
                this.lexema += this.allArgs.charAt(i);
            }

            System.out.println(this.lexema + " Comentario!");
            this.lexema = "";
        }

        if (isOperador(lexema)) {
            System.out.println(this.lexema + " Operador!");
            lexema = "";
        } else if (isNum(lexema)) {

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

        } else if (isIdentificador(lexema)) { // obs: se for um numero primeiro e depois uma letra ele nao identifica como identificador e também se tiver "teste_a" ou "_a" ele nao aceita
            System.out.println(this.lexema + " Identificador!");
            this.lexema = "";
        } else if (isConstanteDeTexto(lexema)) {

            System.out.println(this.lexema + " Constante de texto!");
            this.lexema = "";

        }

        if (lexema.equals("\n")) { // quebra de linha
            this.lexema = "";
        }

        this.lexema = ""; // TIRAR ISSO DEPOIS
    }

    private boolean isNum(String lexema) {

        if (lexema.isEmpty() || lexema.charAt(0) == '.' || lexema.charAt(lexema.length() - 1) == '.') { // caso
                                                                                                        // padrão
            return false;
        }

        int count = 0;

        for (int i = 0; i < lexema.length(); i++) {
            char analiseChar = lexema.charAt(i);

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

        if (lexema.isEmpty()) { // caso padrão
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

        if (lexema.isEmpty()) { // caso padrão
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

        if (lexema.isEmpty()) { // caso padrão
            return false;
        }

        return this.palavrasReservadas.contains(lexema);
    }

    public boolean isConstanteDeTexto(String lexema) {

        if (lexema.isEmpty()) {
            return false;
        }

        int aux = 0;

        for (int i = 0; i < lexema.length(); i++) { // PRIMEIRO CASO (não tem espaço)
            char analiseChar = lexema.charAt(i);

            if (analiseChar == '"') {
                aux += 1;
            }

            if (aux == 2) {
                return true;
            }

        }

        if (aux == 1) {

            for (int j = this.posicionamento; j < allArgs.length(); j++) { // SEGUNDO CASO (tem espaço)
                this.posicionamento = j;
                this.lexema += this.allArgs.charAt(j);
                if (this.allArgs.charAt(j) == '"') {
                    return true; // quando for == ele vai sair
                }
            }

        }

        return false;
    }

    public boolean isComentario(String lexema) {

        if (lexema.isEmpty()) { // caso padrão
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

    public boolean isOperador(String lexema) {
          if (lexema.isEmpty()) { // caso padrão
            return false;
        }

        return this.Operadores.contains(lexema);
    }

    public boolean isSimboloEspecial(char lexema) { // simbolo especial é utilizado para categorizar lexemas, por isso
                                                    // eu naão utilizo essa função nessa classe, apenas na main

        if (lexema == ' ') { // caso padrão
            return false;
        }

        return this.simbolosEspeciais.contains(lexema);

    }

    public boolean isIdentificador(String lexema) {
        Matcher matcher = identificador.matcher(lexema); //Macher é uma classe dentro da biblioteca do java que eu posso utilizar quando eu implemento a classe Pattern, para identificar a expressão regular colocada.

        return matcher.matches();

    }

}