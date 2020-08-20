package com.af.solid.l;

class Rectangle{
    protected int height;
    protected int width;

    public Rectangle(int height, int width) {
        this.height = height;
        this.width = width;
    }

    Rectangle() { }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getArea(){
        return this.height*this.width;
    }

    public boolean isSquare(){
        return width == height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "height=" + height +
                ", width=" + width +
                '}';
    }
}

class Square extends Rectangle{

    public Square() {}

    public Square(int size) {
        height = width = size;
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }
}

class RectangleFactory{
    public static Rectangle newRectangle(int height, int width){
        return new Rectangle(width, height);
    }

    public  static  Rectangle newSquare(int side){
        return new Rectangle(side, side);
    }
}

public class Demo {

    static void useIt(Rectangle rectangle){
        int width = rectangle.getWidth();
        rectangle.setHeight(10);
        System.out.println("Expected : "+(width * 10)+", got : "+rectangle.getArea());
    }

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(2, 3);
        useIt(rectangle);

        Rectangle square = new Square();
        square.setWidth(5);
        useIt(square);
    }
}
