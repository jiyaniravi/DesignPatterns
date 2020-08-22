package com.af.singleton.serialization;

import java.io.*;

class BasicSingleton implements Serializable{

    private BasicSingleton(){}

    private static final BasicSingleton INSTANCE = new BasicSingleton();

    public static BasicSingleton getInstance(){
        return INSTANCE;
    }

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

public class OvercomeSerialization {

    public static void writeObject(BasicSingleton basicSingleton, String fileName){
        try(FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);){

            objectOutputStream.writeObject(basicSingleton);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BasicSingleton readObject(String fileName){
        try(FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){

            return (BasicSingleton)objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        BasicSingleton basicSingleton = BasicSingleton.getInstance();
        basicSingleton.setValue(5);

        String file = "basicSingleton.bin";
        writeObject(basicSingleton, file);

        BasicSingleton basicSingleton1 = readObject(file);
        basicSingleton1.setValue(10);

        System.out.println(basicSingleton == basicSingleton1);
        System.out.println(basicSingleton.getValue());
        System.out.println(basicSingleton1.getValue());
    }
}
