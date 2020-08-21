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

class PersonBuilder <SELF extends PersonBuilder<SELF>>{
    protected com.af.builder.faceted.Person person = new com.af.builder.faceted.Person();

    public SELF withName(String name){
        person.name = name;
        return self();
    }

    public SELF self(){
        return (SELF)this;
    }

    public com.af.builder.faceted.Person build(){
        return person;
    }
}

class EmployeeBuilder extends PersonBuilder<EmployeeBuilder>{
    public EmployeeBuilder withPosition(String position){
        person.position = position;
        return this;
    }
}

public class FluentBuilderRecursiveGenericDemo {
    public static void main(String[] args) {
        com.af.builder.faceted.Person person = new EmployeeBuilder().withName("Ravi").withPosition("Technical Mentor").build();
        System.out.println(person);
    }
}
