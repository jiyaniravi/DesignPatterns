package com.af.iterator.tree;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

class Node<T>{
    public T value;
    public Node<T> left, right, parent;

    public Node(T value) {
        this.value = value;
    }

    public Node(T value, Node<T> left, Node<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;

        left.parent = right.parent = this;
    }
}

class InOrderIterator<T> implements Iterator<T>{

    private Node<T> root;
    private Node<T> current;
    private boolean yieldedStart;

    public InOrderIterator(Node<T> root) {
        this.root = current = root;

        while(current.left != null){
            current = current.left;
        }
    }

    private boolean hasRightMostParent(Node<T> node){
        if(node.parent == null){
            return false;
        }else{
            return (node == node.parent.left || hasRightMostParent(node.parent));
        }
    }

    @Override
    public boolean hasNext() {
        return current.left != null || current.right != null || hasRightMostParent(current);
    }

    @Override
    public T next() {
        if (!yieldedStart)
        {
            yieldedStart = true;
            return current.value;
        }

        if (current.right != null)
        {
            current = current.right;
            while (current.left != null)
                current = current.left;
        }
        else
        {
            Node<T> p = current.parent;
            while (p != null && current == p.right)
            {
                current = p;
                p = p.parent;
            }
            current = p;
        }
        return current.value;
    }
}

class BinaryTree<T> implements Iterable<T>{

    private Node<T> root;

    public BinaryTree(Node<T> root) {
        this.root = root;
    }

    @Override
    public Iterator iterator() {
        return new InOrderIterator(this.root);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        for(T item: this){
            action.accept(item);
        }
    }

    @Override
    public Spliterator spliterator() {
        return null;
    }
}

public class Demo {
    public static void main(String[] args) {
        Node<Integer> root = new Node<>(1, new Node<>(2), new Node<>(3));

        InOrderIterator<Integer> inOrderIterator = new InOrderIterator<>(root);
        while(inOrderIterator.hasNext()){
            System.out.print(""+inOrderIterator.next()+", ");
        }

        System.out.println();

        BinaryTree<Integer> bt = new BinaryTree<>(root);
        for(int n:bt){
            System.out.print(""+n+", ");
        }
    }
}
