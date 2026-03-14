/**
 * Bidirectional Circular Linked List for storing 2D coordinate points.
 * Used by the AWT polygon drawing application to maintain an ordered
 * collection of vertices that form a closed polygon.
 */
public class BCLL {

    /** Node representing a single point in the linked list. */
    static class Node {
        int x, y;
        Node next, prev;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    Node head;
    int size;

    public BCLL() {
        head = null;
        size = 0;
    }

    /**
     * Appends a new coordinate point to the circular list.
     * Maintains bidirectional circular links so that
     * head.prev always points to the last inserted node.
     */
    public void append(int x, int y) {
        Node newNode = new Node(x, y);
        if (head == null) {
            newNode.next = newNode;
            newNode.prev = newNode;
            head = newNode;
        } else {
            Node tail = head.prev;
            tail.next = newNode;
            newNode.prev = tail;
            newNode.next = head;
            head.prev = newNode;
        }
        size++;
    }
}
