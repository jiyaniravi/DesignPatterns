package com.af.singleton.monostate;

class CEO{
    private static String name;
    private static int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "CEO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class MonostateDemo {
    public static void main(String[] args) {
        CEO ceo1 = new CEO();
        ceo1.setName("Ravi Jiyani");
        ceo1.setAge(28);

        CEO ceo2 = new CEO();
        System.out.println(ceo2);
    }
}
