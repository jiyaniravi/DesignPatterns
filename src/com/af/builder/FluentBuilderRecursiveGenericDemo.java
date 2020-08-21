package com.af.builder;

class Person{
    protected String name;
    protected String position;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}

class PersonBuilder{
    Person person = new Person();

    public PersonBuilder withName(String name){
        person.name = name;
        return this;
    }

    public Person build(){
        return person;
    }
}

public class FluentBuilderRecursiveGenericDemo {
    public static void main(String[] args) {
        Person person = new PersonBuilder().withName("Ravi").build();
        System.out.println(person);
    }
}
