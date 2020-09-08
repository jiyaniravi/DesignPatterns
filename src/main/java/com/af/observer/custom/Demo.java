package com.af.observer.custom;

import java.util.ArrayList;
import java.util.List;

class PropertyChangedEventArgs<T>{
    public T source;
    public String propertyName;
    public Object newValue;

    public PropertyChangedEventArgs(T source, String propertyName, Object newValue) {
        this.source = source;
        this.propertyName = propertyName;
        this.newValue = newValue;
    }
}

interface Observer<T>{
    void handle(PropertyChangedEventArgs<T> args);
}

class Observable<T>{
    private List<Observer<T>> observers = new ArrayList<>();

    public void subscribe(Observer<T> observer){
        observers.add(observer);
    }

    protected void propertyChanged(T source, String propertyName, Object newValue){
        for(Observer<T> observer: observers){
            observer.handle(new PropertyChangedEventArgs<T>(source, propertyName, newValue));
        }
    }
}

class Person extends Observable{
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(this.age == age) return;

        this.age = age;
        System.out.println("Set age to : "+age);
        propertyChanged(this, "age", age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Relative extends Person implements Observer<Person>{

    private String relation;

    public Relative(String relation) {
        this.relation = relation;
    }

    @Override
    public void handle(PropertyChangedEventArgs<Person> args) {
        if(args.propertyName.equalsIgnoreCase("age") &&
                (Integer) args.newValue == 50){
            System.out.println("Mother notified that her son is of half century now!");
        }
    }
}

public class Demo {
    public static void main(String[] args) {
        new Demo();
    }

    public Demo(){
        Person person = new Person();
        person.setName("Ravi");
        person.setAge(30);

        Relative relative = new Relative("Mother");
        relative.setName("Daya ben");
        relative.setAge(50);

        person.subscribe(relative);

        for(int i=30;i<60;i++){
            person.setAge(i);
        }
    }
}
