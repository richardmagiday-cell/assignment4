package assignment4;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Name: Richard Magiday
 * PID: 5532600
 * NID: ri610670
 * Assignment: COP 3330 - Programming Assignment 4 (Shrinking Linked List)
 *
 * LinkedList class implementing a singly linked list of integers.
 * Reads a file and keeps only positive numbers, applies the shrinking rule
 * (X[i] == X[i-1] * 2 + 7 triggers a node replacement with a count value),
 * and writes the resulting list to an output file.
 */

public class LinkedList {

    private Node head;  // first node in the list

    // Default constructor
    public LinkedList() {
        head = null;
    }

    // Adds a new node to the end of the list
    public void append(int item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    // Reads the file and adds only positive numbers to the list
    public void construct(String fname) {
        try (Scanner scanner = new Scanner(new File(fname))) {
            while (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                if (num > 0)
                    append(num);
            }
        } catch (IOException e) {
            System.out.println("Error: could not read file " + fname);
            System.exit(1);
        }
    }

    // Applies shrinking rule: if X[i] == X[i-1] * 2 + 7, remove both and insert count.
    // A final count node is appended at the end unless the last pair was already shrunk.
    public void process() {
        int count = -100;           // replacement value, decrements each shrink
        Node prevPrev = null;       // node before prev, needed to re-link after deletion
        Node prev = null;           // previous node
        Node current = head;        // current node
        boolean lastWasShrink = false;

        while (current != null) {
            if (prev != null && current.getItem() == prev.getItem() * 2 + 7) {
                // Shrink: replace prev and current with a single count node
                Node newNode = new Node(count);
                count++;
                newNode.setNext(current.getNext());
                if (prevPrev == null)
                    head = newNode;     // prev was the head
                else
                    prevPrev.setNext(newNode);
                prev = newNode;
                current = newNode.getNext();
                lastWasShrink = true;
            } else {
                // Normal advance
                prevPrev = prev;
                prev = current;
                current = current.getNext();
                lastWasShrink = false;
            }
        }

        if (!lastWasShrink)
            append(count);  // add final count node if list didn't end on a shrink
    }

    // Writes list elements to file, space-separated, no trailing space or newline
    public void printToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            Node current = head;
            StringBuilder sb = new StringBuilder();
            while (current != null) {
                sb.append(current.getItem());
                if (current.getNext() != null)
                    sb.append(" ");
                current = current.getNext();
            }
            writer.print(sb.toString());
            System.out.println(fileName + " is created.");
        } catch (IOException e) {
            System.out.println("Error: could not write file " + fileName);
            System.exit(1);
        }
    }
}
