package com.af.strategy.statick;

import java.util.List;
import java.util.function.Supplier;

enum OutputFormat{
    MARKDOWN, HTML
}

interface ListStrategy{
    default void start(StringBuilder sb) {}
    void addListItem(StringBuilder sb, String item);
    default void end(StringBuilder sb) {}
}

class MarkdownListStrategy implements ListStrategy {
    @Override
    public void addListItem(StringBuilder sb, String item) {
        sb.append("* "+item).append(System.lineSeparator());
    }
}

class HTMLListStrategy implements ListStrategy {

    @Override
    public void start(StringBuilder sb) {
        sb.append("<ul>").append(System.lineSeparator());
    }

    @Override
    public void addListItem(StringBuilder sb, String item) {
        sb.append("    <li>").append(item).append("</li>").append(System.lineSeparator());
    }

    @Override
    public void end(StringBuilder sb) {
        sb.append("</ul>").append(System.lineSeparator());
    }
}

class TextProcessor <LS extends ListStrategy>{
    private StringBuilder sb = new StringBuilder();
    private LS listStrategy;

    public TextProcessor(Supplier<? extends LS> constructor){
        listStrategy = constructor.get();
    }

    public void appendList(List<String> items){
        listStrategy.start(sb);
        for(String item:items)
            listStrategy.addListItem(sb, item);
        listStrategy.end(sb);
    }

    public void clear(){
        sb.setLength(0);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}

public class Demo {
    public static void main(String[] args) {
        TextProcessor<MarkdownListStrategy> markupTP = new TextProcessor<>(MarkdownListStrategy::new);
        markupTP.appendList(List.of("India", "USA", "Russia"));
        System.out.println(markupTP);

        TextProcessor<HTMLListStrategy> htmlTP = new TextProcessor<>(HTMLListStrategy::new);
        htmlTP.appendList(List.of("Gujarat", "Maharashtra", "Punjab"));
        System.out.println(htmlTP);
    }
}
