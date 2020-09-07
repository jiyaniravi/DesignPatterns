package com.af.mediator.chatroom;

import java.util.ArrayList;
import java.util.List;

class Person{
    private String name;
    private ChatRoom room;
    private List<String> chatLog = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChatRoom getRoom() {
        return room;
    }

    public void setRoom(ChatRoom room) {
        this.room = room;
    }

    public void receive(String sender, String message){
        String s = sender+" : '"+message+"'";
        System.out.println("["+name+"'s chat session : "+s);
        chatLog.add(s);
    }

    public void say(String message){
        room.broadcast(name, message);
    }

    public void privateMessage(String who, String message){
        room.message(name, who, message);
    }
}

class ChatRoom{
    private List<Person> people = new ArrayList<>();

    public void join(Person p){
        String message = p.getName()+" joins the room!";
        broadcast("Room", message);
        p.setRoom(this);
        people.add(p);
    }

    public void broadcast(String senderName, String message){
        for(Person p : people){
            if(!p.getName().equals(senderName)){
                p.receive(senderName, message);
            }
        }
    }

    public void message(String sender, String receiver, String message){
        people.stream()
                .filter(p -> p.getName().equals(receiver))
                    .findFirst()
                        .ifPresent(p -> p.receive(sender, message));
    }
}

public class Demo {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();

        Person john = new Person("John");
        Person jane = new Person("Jane");

        chatRoom.join(john);
        chatRoom.join(jane);

        john.say("Hi All");
        jane.say("Hey John!");

        Person ravi = new Person("Ravi");
        chatRoom.join(ravi);
        ravi.say("Hey All! How are you?");

        jane.privateMessage(ravi.getName(), "Hey Ravi! After long time?");
    }
}
