package com.af.factory;

class Point {
    private double x;
    private double y;

    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static class Factory{
        public static Point getCartesianPoint(double a, double b){
            return new Point(a, b);
        }

        public static Point getPolarPoint(double rho, double theta){
            return new Point(rho*Math.cos(theta), rho*Math.sin(theta));
        }
    }
}

public class FactoryDemo {
    public static void main(String[] args) {
        Point cartesianPoint = Point.Factory.getCartesianPoint(2, 3);
    }
}
