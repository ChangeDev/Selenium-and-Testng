package com.taoqi.datastruct;

import java.util.function.Consumer;

/**
 * @description:
 * @author: ChangFeng
 * @create: 2018-04-26 14:27
 **/
public class NodeList<T extends Comparable> {

    private Node<T> head;

    private int size;

    public NodeList() {
        this.size = 0;
    }

    // using Bubble Sort
    public void sort() {
        Node<T> currNode = null, nextNode = null;
        for (currNode = head; currNode.next != null; currNode = currNode.next) {
            for (nextNode = head; nextNode.next != null; nextNode = nextNode.next) {
                Node<T> cNode = nextNode;
                Node<T> nNode = nextNode.next;
                Comparable c = (Comparable) cNode.data;
                Comparable n = (Comparable) nNode.data;
                if (c.compareTo(n) > 0) {
                    T temp = cNode.data;
                    cNode.data = nNode.data;
                    nNode.data = cNode.data;
                }
            }
        }
    }

    public void add(T value) {

        if (null == value) {
            return;
        }

        Node<T> newNode = new Node<T>(value);

        Node<T> tempNode = head;
        if (null == tempNode) {
            head = newNode;
            return;
        }
        while (tempNode.next != null) {
            tempNode = tempNode.next;
        }
        tempNode.next = newNode;
        size++;
    }

    public void add(T value, int index) {
        this.rangeCheck(index);
        int currPos = 0;
        Node<T> insertNode = new Node(value);
        Node<T> tempNode = head;
        while (tempNode.next != null) {
            if (currPos + 1 == index) {
                insertNode.next = tempNode.next;
                tempNode.next = insertNode;
                return;
            }
            currPos++;
            tempNode = tempNode.next;
        }
    }

    private Node<T> get(int index) {
        this.rangeCheck(index);
        int currPos = 0;
        Node<T> currNode = head;
        while (currNode.next != null) {
            if (currPos + 1 == index) {
                return currNode;
            }
            currPos++;
            currNode = currNode.next;
        }
        return null;
    }

    public T remove(int index) {
        this.rangeCheck(index);
        Node<T> tempNode = head;
        int currPos = 0;
        while (tempNode.next != null) {
            if (currPos + 1 == index) {
                //remove
                Node<T> deleteNode = tempNode.next;
                tempNode.next = deleteNode.next;
                T value = deleteNode.data;
                deleteNode = null;
                return value;
            }
            currPos++;
            tempNode = tempNode.next;
        }
        return null;
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException(index + "");
    }

    public void foreach(Consumer<T> consumer) {
        Node<T> tempNode = head;
        while (tempNode != null) {
            consumer.accept(tempNode.data);
            tempNode = tempNode.next;
        }
    }

    public int size() {
        return this.size;
    }

    class Node<T> {

        T data;

        Node<T> next;

        public Node(T value) {
            this.data = value;
        }

        public Node(T value, Node<T> next) {
            this.data = value;
            this.next = next;
        }

    }

}