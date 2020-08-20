package com.af.solid.d;

import java.util.ArrayList;

enum Relationship{
    PARENT,
    CHILD,
    SIBLING
}

class Person{
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Relationships{
    //private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<Object>();

    public void addParentAndChild(Person parent, Person child){
        //relations.add();
    }
}

public class Demo {
    public static void main(String[] args) {
        switch(5){
            case 1,5 -> System.out.println("1,5");
            case 2   -> System.out.println("2");
        }
    }
}
