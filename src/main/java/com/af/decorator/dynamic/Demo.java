package com.af.decorator.dynamic;

interface Shape{
    String info();
}

class Circle implements Shape{
    private float radius;

    public Circle(){}

    public Circle(float radius) {
        this.radius = radius;
    }

    public void resize(float factor){
        radius*=factor;
    }

    @Override
    public String info() {
        return "A circle of radius : "+radius;
    }
}

class Square implements Shape{
    private float side;

    public Square(){}

    public Square(float side) {
        this.side = side;
    }

    @Override
    public String info() {
        return "A square of side : "+side;
    }
}

class ColoredShape implements Shape{

    private Shape shape;
    private String color;

    public ColoredShape(Shape shape, String color) {
        this.shape = shape;
        this.color = color;
    }

    @Override
    public String info() {
        return this.shape.info()+" has the color : "+color;
    }
}

class TransparentShape implements Shape{

    private Shape shape;
    private int transparency;

    public TransparentShape(Shape shape, int transperency) {
        this.shape = shape;
        this.transparency = transperency;
    }

    @Override
    public String info() {
        return this.shape.info()+" has transparency : "+this.transparency +"%";
    }
}

public class Demo {
    public static void main(String[] args) {
        Circle circle = new Circle(10);
        System.out.println(circle.info());

        ColoredShape blueSquare = new ColoredShape(new Square(20), "blue");
        System.out.println(blueSquare.info());

        TransparentShape greenTransparentCircle = new TransparentShape(new ColoredShape(new Circle(5), "green"), 15);
        System.out.println(greenTransparentCircle.info());
    }
}
