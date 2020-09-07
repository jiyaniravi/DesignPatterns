package com.af.interpreter.handmade;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

interface Element{
    int eval();
}

class Integer implements Element{

    private int value;

    public Integer(int value) {
        this.value = value;
    }

    @Override
    public int eval() {
        return value;
    }
}

class BinaryOperation implements Element{

    public enum Type{
        ADDITION,
        SUBTRACTION
    }

    public Type type;
    public Element left, right;

    @Override
    public int eval() {
        switch (type){
            case ADDITION:
                return left.eval()+right.eval();
            case SUBTRACTION:
                return left.eval()-right.eval();
            default:
                return 0;
        }
    }
}

class Parser{
    static Element parse(List<Token> tokens){
        BinaryOperation result = new BinaryOperation();
        boolean haveLHS = false;

        for(int i=0; i<tokens.size(); ++i){
            Token token = tokens.get(i);
            switch (token.type) {
                case INTEGER:
                    final Integer integer = new Integer(java.lang.Integer.parseInt(token.text));
                    if (!haveLHS) {
                        result.left = integer;
                        haveLHS = true;
                    } else {
                        result.right = integer;
                    }
                    break;

                case PLUS:
                    result.type = BinaryOperation.Type.ADDITION;
                    break;

                case MINUS:
                    result.type = BinaryOperation.Type.SUBTRACTION;
                    break;

                case LPAREN:
                    int j = i; //Location to the right parenthesis
                    for (; j < tokens.size(); ++j) {
                        if (tokens.get(j).type == Token.Type.RPAREN) {
                            break;
                        }
                    }
                    List<Token> subExpression = tokens.stream()
                                                        .skip(i+1)
                                                            .limit(Math.abs(j-i-1))
                                                                .collect(Collectors.toList());
                    Element element = parse(subExpression);
                    if (!haveLHS) {
                        result.left = element;
                        haveLHS = true;
                    } else {
                        result.right = element;
                    }

                    i = j;
                    break;
            }
        }
        return result;
    }
}

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

        Element output = Parser.parse(tokens);
        System.out.println(input+" = "+output.eval());
    }
}
