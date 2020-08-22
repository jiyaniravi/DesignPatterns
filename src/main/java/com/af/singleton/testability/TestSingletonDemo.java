package com.af.singleton.testability;

import com.google.common.collect.Iterables;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

class SingletonDatabase{
    private Dictionary<String, Integer> capitals = new Hashtable<>();
    private static int instanceCount = 0;
    public static int getInstanceCount(){ return  instanceCount; }

    private SingletonDatabase() {
        instanceCount++;
        System.out.println("Initializing Database");

        File file = new File(SingletonDatabase.class
                                                    .getProtectionDomain()
                                                    .getCodeSource()
                                                    .getLocation()
                                                    .getPath());

        Path fullPath = Paths.get(file.getPath(), "capitals.txt");

        try{
                List<String> lines = Files.readAllLines(fullPath);
                Iterables.partition(lines, 2)
                        .forEach(kv -> capitals.put(kv.get(0).trim(), Integer.parseInt(kv.get(1))));

        }catch (IOException io){
            System.out.println(io);
        }
    }

    private static final SingletonDatabase INSTANCE = new SingletonDatabase();

    public static SingletonDatabase getInstance(){
        return INSTANCE;
    }

    public int getPopulation(String name){
        return capitals.get(name);
    }
}

class SingletonRecordFinder{
    public int getTotalPoplation(List<String> names){
        int result = 0;
        for (String name: names){
            result+=SingletonDatabase.getInstance().getPopulation(name);
        }
        return result;
    }
}

public class TestSingletonDemo {
    public static void main(String[] args) {
        SingletonRecordFinder rf = new SingletonRecordFinder();
        int tp = rf.getTotalPoplation(List.of("Seoul", "Mexico City"));
        System.out.println("Total population : "+tp);
    }
}
