package com.af.strategy.dynamic;

import java.util.List;

enum OutputFormat{
    MARKDOWN, HTML
}

interface ListStrategy{
    default void start(StringBuilder sb) {}
    void addListItem(StringBuilder sb, String item);
    default void end(StringBuilder sb) {}
}

class MarkdownListStrategy implements ListStrategy{
    @Override
    public void addListItem(StringBuilder sb, String item) {
        sb.append("* "+item).append(System.lineSeparator());
    }
}

class HTMLListStrategy implements ListStrategy{

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

class TextProcessor{
    private StringBuilder sb = new StringBuilder();
    private ListStrategy listStrategy;

    public TextProcessor(OutputFormat format){
        setOutputFormat(format);
    }

    public void setOutputFormat(OutputFormat format) {
        switch (format){
            case MARKDOWN:
                    listStrategy = new MarkdownListStrategy();
                break;
            case HTML:
                    listStrategy = new HTMLListStrategy();
                break;
        }
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
        TextProcessor textProcessor = new TextProcessor(OutputFormat.MARKDOWN);
        textProcessor.appendList(List.of("India", "USA", "Russia"));
        System.out.println(textProcessor);

        textProcessor.clear();
        textProcessor.setOutputFormat(OutputFormat.HTML);
        textProcessor.appendList(List.of("Gujarat", "Maharashtra", "Punjab"));
        System.out.println(textProcessor);
    }
}
