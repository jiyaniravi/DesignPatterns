package com.af.singleton.enumbased;

import java.io.*;

enum EnumBasedSingleton{
    INSTANCE;

    EnumBasedSingleton(){
        value = 50;
    }

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}

public class EnumBasedDemo {

    public static void writeObject(EnumBasedSingleton basicSingleton, String fileName){
        try(FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);){

            objectOutputStream.writeObject(basicSingleton);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static EnumBasedSingleton readObject(String fileName){
        try(FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){

            return (EnumBasedSingleton)objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        /*EnumBasedSingleton singleton = EnumBasedSingleton.INSTANCE;
        singleton.setValue(25);
        System.out.println(singleton.getValue());*/

        String file = "enumBasedSingleton.bin";
        //writeObject(EnumBasedSingleton.INSTANCE, file);
        EnumBasedSingleton singleton1 = readObject(file);

        System.out.println(singleton1.getValue());
    }
}
