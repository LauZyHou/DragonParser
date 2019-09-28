package main;

public class Main {
    public static void main(String[] args) {
        Lexer lex = new Laxer();
        Parser parse = new Parser(lex);
        parse.program();
        System.out.write('\n');
    }
}
