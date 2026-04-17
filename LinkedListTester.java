package assignment4;

import java.io.File;
import java.util.Scanner;

/*
 * Name: Richard Magiday
 * PID: 5532600
 * NID: ri610670
 * Assignment: COP 3330 - Programming Assignment 4 (Shrinking Linked List)
 *
 * This program reads integers from a user-specified file and builds a singly
 * linked list containing only the positive values. It then processes the list
 * by applying a shrinking rule: whenever X[i] equals X[i-1] * 2 + 7, both
 * nodes are removed and replaced with a single node holding a count value
 * (starting at -100 and decrementing). A final count node is appended at the
 * end of the traversal. The resulting list is written to a new file prefixed
 * with "processed".
 */

public class LinkedListTester {

    public static void main(String[] args) {
        String fileName;
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter the data file name: ");
            fileName = input.next();
        }

        // Prepend "processed" to just the filename, keeping the directory if any
        File inputFile = new File(fileName);
        String dir = inputFile.getParent();
        String outputFileName = (dir != null ? dir + File.separator : "") + "processed" + inputFile.getName();

        LinkedList list = new LinkedList();
        list.construct(fileName);
        list.process();
        list.printToFile(outputFileName);
    }
}
