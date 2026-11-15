

/**
 * This class stores the numbers of the pages where a word occurs.
 * @author Isabel H. Manssour
 */
public class OccurrenceList {

    // Inner class Node
    private class Node {
        public int pageNumber;
        public Node next;
        public Node(int n) {
            pageNumber = n;
            next = null;
        }
    }

    // Attributes
    private Node head;
    private Node tail;
    private int count;

    // Methods
    public OccurrenceList() {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Returns true if the list contains no elements.
     * @return true if the list contains no elements
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Returns the number of elements in the list.
     * @return the number of elements in the list
     */
    public int size() {
        return count;
    }

    /**
     * Empties the list
     */
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Adds a page number to the end of the list, if it has not
     * been added yet.
     * @param pageNumber page number to be added to the end of the list
     * @return true if the page number received as parameter was added
     * to the end of the list, and false otherwise.
     */
    public boolean add(int pageNumber)  {
        if(count == 0){
            Node newNode = new Node(pageNumber);
            this.tail = newNode;
            this.head = newNode;
            count++;
            return true;
        }
        if(contains(pageNumber)){
            return false;
        }

        Node newNode = new Node(pageNumber);
        this.tail.next = newNode;
        this.tail = newNode;
        count++;
        return true;
    }

    /**
     * Returns the element at a given position of the list.
     * @param index the position in the list
     * @return the element at the specified position
     * @throws IndexOutOfBoundsException if (index < 0 || index >= size())
     */
    public Integer get(int index) {
        if (index<0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        Node current = head;
        while (index>0){
            current = current.next;
            index--;
        }
        return current.pageNumber;
    }

    /**
     * Returns true if the list contains the page number received
     * as parameter.
     * @param pageNumber the element to search for
     * @return true if the list contains the specified element
     */
    public boolean contains(int pageNumber) {
        Node current = head;
        while(current != null){
            if (current.pageNumber == pageNumber) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";
        String result = "[";
        Node current = head;
        while(current != null){
            result += current.pageNumber + ", ";
            current = current.next;
        }
        return result.substring(0, result.length()-2) + "]";
    }
}
