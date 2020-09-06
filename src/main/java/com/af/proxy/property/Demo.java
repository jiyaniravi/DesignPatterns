package com.af.proxy.property;

import java.util.Objects;

class Property<T>{
    private T value;

    public Property(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        //Logging API or any other API
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property<?> property = (Property<?>) o;
        return Objects.equals(value, property.value);
    }

    @Override
    public int hashCode() {
        return value!=null?Objects.hash(value):0;
    }
}

class Creature{
    private Property<Integer> agility = new Property<>(10);

    public void setAgility(int value){
        agility.setValue(value);
    }

    public int getAgility(){
        return agility.getValue();
    }
}

public class Demo {
    public static void main(String[] args) {
        Creature creature = new Creature();
        creature.setAgility(21);
    }
}
