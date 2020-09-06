package com.af.chain_of_responsibility.broker;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class Event<Args>{

    private int index = 0;
    private Map<Integer, Consumer<Args>> handlers = new HashMap<>();

    public int subscribe(Consumer<Args> handler){
        int i=index;
        handlers.put(index++, handler);
        return i;
    }

    public void unsubscribe(int key){
        handlers.remove(key);
    }

    public void fire(Args args){
        for(Consumer<Args> consumer: handlers.values()){
            consumer.accept(args);
        }
    }
}

class Query{
    public String creatureName;

    enum Argument{
        ATTACK,
        DEFENSE
    }

    public Argument argument;
    public int result;

    public Query(String creatureName, Argument argument, int result) {
        this.creatureName = creatureName;
        this.argument = argument;
        this.result = result;
    }
}

class Game{
    public Event<Query> queries = new Event<>();
}

class Creature{
    private Game game;
    public String name;
    public int defaultAttack, defaultDefense;

    public Creature(Game game, String name, int defaultAttack, int defaultDefense) {
        this.game = game;
        this.name = name;
        this.defaultAttack = defaultAttack;
        this.defaultDefense = defaultDefense;
    }

    int getAttack(){
        Query attackQuery = new Query(name, Query.Argument.ATTACK, defaultAttack);
        game.queries.fire(attackQuery);
        return attackQuery.result;
    }

    int getDefense(){
        Query defenseQuery = new Query(name, Query.Argument.DEFENSE, defaultDefense);
        game.queries.fire(defenseQuery);
        return defenseQuery.result;
    }

    @Override
    public String toString() {
        return "Creature{" +
                ", name='" + name + '\'' +
                ", Attack=" + getAttack() +
                ", Defense=" + getDefense() +
                '}';
    }
}

class CreatureModifier{
    protected Game game;
    protected Creature creature;

    public CreatureModifier(Game game, Creature creature) {
        this.game = game;
        this.creature = creature;
    }
}

class DoubleAttackModifier extends CreatureModifier implements AutoCloseable{

    private final int token;

    public DoubleAttackModifier(Game game, Creature creature) {
        super(game, creature);
        token = game.queries.subscribe(q -> {
            if(q.creatureName.equals(creature.name) && q.argument == Query.Argument.ATTACK){
                q.result *= 2;
            }
        });
    }

    @Override
    public void close(){
        game.queries.unsubscribe(token);
    }
}

class IncreasedDefenseModifier extends CreatureModifier implements AutoCloseable{

    private final int token;

    public IncreasedDefenseModifier(Game game, Creature creature) {
        super(game, creature);
        token = game.queries.subscribe(q ->{
           if(q.creatureName.equals(creature.name) && q.argument == Query.Argument.DEFENSE){
               q.result += 3;
           }
        });
    }

    @Override
    public void close(){
        game.queries.unsubscribe(token);
    }
}

public class Demo {
    public static void main(String[] args) {
        Game game = new Game();
        Creature strong_goblin = new Creature(game,"Strong Goblin", 2, 2);
        System.out.println(strong_goblin);

        IncreasedDefenseModifier increasedDefenseModifier = new IncreasedDefenseModifier(game, strong_goblin);
        DoubleAttackModifier doubleAttackModifier = new DoubleAttackModifier(game, strong_goblin);

        try(doubleAttackModifier){
            System.out.println(strong_goblin);
        }

        System.out.println(strong_goblin);
        increasedDefenseModifier.close();
        System.out.println(strong_goblin);
    }
}
