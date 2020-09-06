package com.af.interpreter.handmade;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Token{

    public enum Type{
        INTEGER,
        PLUS,
        MINUS,
        LPAREN,
        RPAREN
    }

    public Type type;
    public String text;

    public Token(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return "`"+text+"`";
    }
}

class Lexer{
    static List<Token> lex(String input){
        final ArrayList<Token> tokens = new ArrayList<>();
        for(int i=0; i<input.length();++i){
            char c = input.charAt(i);
            switch (c){
                case '+':
                    tokens.add(new Token(Token.Type.PLUS, "+"));
                    break;
                case '-':
                    tokens.add(new Token(Token.Type.MINUS, "-"));
                    break;
                case '(':
                    tokens.add(new Token(Token.Type.LPAREN, "("));
                    break;
                case ')':
                    tokens.add(new Token(Token.Type.RPAREN, ")"));
                    break;
                default :
                    StringBuilder operandBuilder = new StringBuilder(""+c);
                    for(int j=i+1;j<input.length();j++){
                        char c1 = input.charAt(j);
                        if(Character.isDigit(c1)){
                            operandBuilder.append(c1);
                            i++;
                        }else{
                            tokens.add(new Token(Token.Type.INTEGER, operandBuilder.toString()));
                            break;
                        }
                    }
                    break;
            }
        }
        return tokens;
    }
}

public class Demo {
    public static void main(String[] args) {
        String input = "(13+4)-(12+1)";
        List<Token> tokens = Lexer.lex(input);
        System.out.println(tokens.stream().map(t -> t.toString()).collect(Collectors.joining("\n")));
    }
}
