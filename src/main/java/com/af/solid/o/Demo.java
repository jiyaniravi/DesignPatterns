package com.af.solid.o;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

enum Color{
    RED,GREEN,BLUE
}

enum Size{
    SMALL, MEDIUM, LARGE, HUGE
}

class Product{
    private String name;
    private Color color;
    private Size size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Product(String name, Color color, Size size){
        this.name = name;
        this.color = color;
        this.size = size;
    }
}

class ProductFilter{
    public Stream<Product> filterByColor(List<Product> products, Color color){
        return products.stream().filter(p -> p.getColor() == color);
    }

    public Stream<Product> filterBySize(List<Product> products, Size size){
        return products.stream().filter(p -> p.getSize() == size);
    }

    public Stream<Product> filterByColorAndSize(List<Product> products, Color color, Size size){
        return products.stream().filter(p -> p.getColor() == color && p.getSize() == size);
    }
}

interface Specification<T>{
    boolean isSatisfied(T item);
}

interface Filter<T>{
    Stream<T> filter(List<T> items, Specification<T> specification);
}

class ColorSpecification implements Specification<Product>{
    private Color color;

    public ColorSpecification(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return this.color == item.getColor();
    }
}

class SizeSpecification implements Specification<Product>{

    private Size size;

    public SizeSpecification(Size size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return this.size == item.getSize();
    }
}

class AndSpecification<T> implements Specification<T>{

    private Specification<T> first, second;

    public AndSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(T item) {
        return first.isSatisfied(item) && second.isSatisfied(item);
    }
}

class BetterFilter implements Filter<Product>{

    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> specification) {
        return items.stream().filter(p -> specification.isSatisfied(p));
    }
}

public class Demo {
    public static void main(String[] args) {
        Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
        Product tree = new Product("Tree", Color.GREEN, Size.LARGE);
        Product house = new Product("House", Color.BLUE, Size.LARGE);
        List<Product> products = Stream.of(apple, tree, house).collect(Collectors.toList());

        ProductFilter filter = new ProductFilter();
        System.out.println("Green products (OLD implementation) : ");
        filter.filterByColor(products, Color.GREEN).forEach(
                                    product -> System.out.println(product.getName()+" : is green"));

        System.out.println("Green products (NEW implementation) : ");
        BetterFilter betterFilter = new BetterFilter();
        betterFilter.filter(products, new ColorSpecification(Color.BLUE)).forEach(
                                    product -> System.out.println(product.getName()+" : is blue"));

        System.out.println("And Specification Test : ");
        betterFilter.filter(products,
                new AndSpecification<>(
                        new ColorSpecification(Color.GREEN),
                        new SizeSpecification(Size.LARGE
                    ))).forEach(
                            product -> System.out.println(
                                            product.getName()+" : "+product.getColor()+" : "+product.getSize()));
    }
}
