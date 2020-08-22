package com.af.singleton.innerclass;

class OuterClass{
    private OuterClass() {
        System.out.println("Lazy constructed by inner class");
    }

    private static class InnerClass{
        private static final OuterClass INSTANCE = new OuterClass();
    }

    public static OuterClass getInstance(){
        return InnerClass.INSTANCE;
    }
}

public class InnerStaticSingletonDemo {
    public static void main(String[] args) {
        OuterClass outerClass = OuterClass.getInstance();
    }
}
