package com.af.iterator.array_backed;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

class SimpleCreature{

    private int strength, agility, intelligence;

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int max(){
        return Math.max(strength, Math.max(agility, intelligence));
    }

    public int sum(){
        return strength + agility + intelligence;
    }

    public double average(){
        return sum()/3.0;
    }
}

class Creature implements Iterable<Integer>{

    private int[] states = new int[3];

    private final static int STRENGTH = 0;
    public int getStrength(){ return states[STRENGTH]; }
    public void setStrength(int strength){
        states[STRENGTH]=strength;
    }

    private final static int AGILITY = 1;
    public int getAgility(){ return states[AGILITY]; }
    public void setAgility(int agility){
        states[AGILITY]=agility;
    }

    private final static int INTELLIGENCE = 2;
    public int getIntelligence(){ return states[INTELLIGENCE]; }
    public void setIntelligence(int intelligence){
        states[INTELLIGENCE]=intelligence;
    }

    public int sum(){
        return IntStream.of(states).sum();
    }

    public int max(){
        return IntStream.of(states).max().getAsInt();
    }

    public double average(){
        return IntStream.of(states).average().getAsDouble();
    }

    @Override
    public Iterator<Integer> iterator() {
        return IntStream.of(states).iterator();
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        for(int x: states){
            action.accept(x);
        }
    }

    @Override
    public Spliterator<Integer> spliterator() {
        return IntStream.of(states).spliterator();
    }
}

public class Demo {
    public static void main(String[] args) {
        Creature creature = new Creature();
        creature.setStrength(17);
        creature.setAgility(12);
        creature.setIntelligence(13);

        System.out.println("Max : "+creature.max());
        System.out.println("Sum : "+creature.sum());
        System.out.println("Average : "+creature.average());
    }
}
