package assignment4;

/*
 * Name: Richard Magiday
 * PID: 5532600
 * NID: ri610670
 * Assignment: COP 3330 - Programming Assignment 4 (Shrinking Linked List)
 *
 * Node class for a singly linked list.
 * Stores an integer item and a reference to the next node.
 * No setItem method is provided — node contents cannot be changed after creation.
 */

public class Node {

    private final int item;   // value stored in this node
    private Node next;  // next node in the list

    // Constructor
    public Node(int item) {
        this.item = item;
        this.next = null;
    }

    public int getItem() {
        return item;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
