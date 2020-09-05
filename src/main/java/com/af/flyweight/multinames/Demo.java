package com.af.flyweight.multinames;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

class User{
    private String fullName;

    public User(String fullName) {
        this.fullName = fullName;
    }


}

class User2{
    static List<String> strings = new ArrayList<>();
    private int[] names;

    public User2(String fullName){
        Function<String, Integer> getOrAdd = (String s) -> {
            int index = strings.indexOf(s);
            if(index != -1){
                return index;
            }else{
                strings.add(s);
                return strings.size()-1;
            }
        };

        names = Arrays.stream(fullName.split(" "))
                        .mapToInt(s -> getOrAdd.apply(s))
                            .toArray();
    }

    public String getFullName(){
        StringBuilder fullName = new StringBuilder();
        for(int index:names){
            fullName.append(strings.get(index)).append(" ");
        }
        return fullName.toString();
    }
}

public class Demo {
    public static void main(String[] args) {
        /*User john_smith = new User("John Smith");
        User jane_smith = new User("Jane Smith");
        User john_jiyani = new User("John Jiyani");*/

        User2 john_smith = new User2("John Smith");     // 0:John, 1:Smith
        User2 jane_smith = new User2("Jane Smith");     // 2:Jane
        User2 john_jiyani = new User2("John Jiyani");   // 3:Jiyani

        System.out.println(jane_smith.getFullName());
    }
}
