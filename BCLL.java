package com.stabgan.java;

/**
 * Bidirectional Circular Linked List for storing coordinate points
 */
public class BCLL {
    
    public Node head;
    public int size;
    
    public BCLL() {
        this.head = null;
        this.size = 0;
    }
    
    /**
     * Node class to represent a coordinate point
     */
    public class Node {
        public int x, y;
        public Node next, prev;
        
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.next = null;
            this.prev = null;
        }
    }
    
    /**
     * Append a new coordinate point to the list
     */
    public void append(int x, int y) {
        Node newNode = new Node(x, y);
        
        if (head == null) {
            head = newNode;
            head.next = head;
            head.prev = head;
        } else {
            Node tail = head.prev;
            
            // Insert new node between tail and head
            tail.next = newNode;
            newNode.prev = tail;
            newNode.next = head;
            head.prev = newNode;
        }
        
        size++;
    }
    
    /**
     * Get all points as an array for polygon drawing
     */
    public int[][] getAllPoints() {
        if (size == 0) return new int[0][2];
        
        int[][] points = new int[size][2];
        Node current = head;
        
        for (int i = 0; i < size; i++) {
            points[i][0] = current.x;
            points[i][1] = current.y;
            current = current.next;
        }
        
        return points;
    }
}