package com.af.flyweight.word;

import java.util.*;

class Sentence
{
    private String plainText;
    private static List<WordToken> wordTokens = new ArrayList<>();

    public Sentence(){}

    public Sentence(String plainText)
    {
        this.plainText = plainText;
    }

    public WordToken getWord(int index)
    {
        index-=1;
        WordToken wordToken = new WordToken(index);
        wordTokens.add(wordToken);
        return wordToken;
    }

    @Override
    public String toString()
    {
        String[] words = plainText.split(" ");
        for(int i=0; i<words.length; i++){
            for(WordToken wt:wordTokens){
                if(wt.isCapitalize(i) && wt.capitalize){
                    words[i] = words[i].toUpperCase();
                }
            }
        }
        return String.join(" ", words).toString();
    }

    class WordToken
    {
        public boolean capitalize;
        public int index;

        public WordToken(int index) {
            this.index = index;
        }

        public boolean isCapitalize(int position){
            return (index == position);
        }
    }
}

public class Demo {
    public static void main(String[] args) {
        Sentence hello_world = new Sentence("hello world");
        hello_world.getWord(1).capitalize = true;
        System.out.println(hello_world);

        Sentence alpha_beta_gamma = new Sentence("alpha beta gamma");
        alpha_beta_gamma.getWord(1).capitalize = true;
        alpha_beta_gamma.getWord(3).capitalize = true;
        System.out.println(alpha_beta_gamma);
    }
}
