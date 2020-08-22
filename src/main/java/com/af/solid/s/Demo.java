package com.af.solid.s;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class Journal{
    private final List<String> entries = new ArrayList<String>();
    private static int count = 0;

    public void addEntry(String text){
        entries.add(""+(++count)+" : "+text);
    }

    public void removeEntry(int index){
        entries.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }
}

class Persistence{
    public void saveToFile(Journal journal, String fileName, boolean overwrite) throws FileNotFoundException {
        if(overwrite && new File(fileName).exists()){
            try(PrintStream ps = new PrintStream(fileName)){
                ps.println(journal.toString());
            }
        }
    }
}

public class Demo {
    public static void main(String[] args) throws IOException {
        Journal j = new Journal();
        j.addEntry("I played guitar today !");
        j.addEntry("I watched a video.");

        System.out.println(j);

        String fileName = "c:\\RAVI\\DesignPatterns\\temp\\journal.txt";
        Persistence persistence = new Persistence();
        persistence.saveToFile(j, fileName,true);

        Runtime.getRuntime().exec("notepad "+fileName);
    }
}
