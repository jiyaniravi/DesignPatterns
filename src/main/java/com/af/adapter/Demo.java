package com.af.adapter;

import java.util.*;
import java.util.function.Consumer;

class Point{
    public int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class Line{
    public Point start, end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return start.equals(line.start) &&
                end.equals(line.end);
    }

    @Override
    public int hashCode() {
        int result = start != null? start.hashCode():0;
        result = 31 * result + (end != null ? end.hashCode():0);
        return result;
    }
}

class VectorObject extends ArrayList<Line>{

}

class VectorRectangle extends VectorObject{
    public VectorRectangle(int x, int y, int height, int width){
        add(new Line(new Point(x,y), new Point(x+width,y)));
        add(new Line(new Point(x+width,y), new Point(x+width,y+height)));
        add(new Line(new Point(x+width,y+height), new Point(x,y+height)));
        add(new Line(new Point(x,y+height), new Point(x,y)));
    }
}

class LineToPointAdapter implements Iterable<Point>{

    private static int count = 0;
    private static Map<Integer, List<Point>> lineCache= new HashMap<>();
    private int hash;

    public LineToPointAdapter(Line line) {

        hash = line.hashCode();
        if(lineCache.containsKey(hash)) return;

        System.out.println(
                String.format("%d: Generating points for line [%d,%d]-[%d,%d] (with caching)",
                        ++count, line.start.x, line.start.y, line.end.x, line.end.y));

        ArrayList<Point> points = new ArrayList<>();

        int left = Math.min(line.start.x, line.end.x);
        int right = Math.max(line.start.x, line.end.x);
        int top = Math.min(line.start.y, line.end.y);
        int bottom = Math.max(line.start.y, line.end.y);
        int dx = right - left;
        int dy = line.end.y - line.start.y;

        if (dx == 0)
        {
            for (int y = top; y <= bottom; ++y)
            {
                points.add(new Point(left, y));
            }
        }
        else if (dy == 0)
        {
            for (int x = left; x <= right; ++x)
            {
                points.add(new Point(x, top));
            }
        }

        lineCache.put(hash, points);
    }

    @Override
    public Iterator<Point> iterator() {
        return lineCache.get(hash).iterator();
    }

    @Override
    public void forEach(Consumer<? super Point> action) {
        lineCache.get(hash).forEach(action);
    }

    @Override
    public Spliterator<Point> spliterator() {
        return lineCache.get(hash).spliterator();
    }
}

public class Demo {

    private final static List<VectorObject> vectorObjects = new ArrayList<>(Arrays.asList(
       new VectorRectangle(1,1,10,10),
       new VectorRectangle(3,3,6,6)
    ));

    public static void drawPoint(Point point){
        System.out.println(".");
    }

    private static void draw(){
        for(VectorObject vectorObject:vectorObjects){
            for(Line line:vectorObject){
                LineToPointAdapter adapter = new LineToPointAdapter(line);
                adapter.forEach(Demo::drawPoint);
            }
        }
    }

    public static void main(String[] args) {
        draw();
        draw();
    }
}
