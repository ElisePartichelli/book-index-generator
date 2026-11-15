public class StopwordList {

        // Inner class Node
        private class Node {
            public String stopword;
            public Node next;
            public Node(String stopword) {
                this.stopword = stopword;
                next = null;
            }
        }

        // Attributes
        private Node head;
        private Node tail;
        private int count;

        // Methods
        public StopwordList() {
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
         * Adds a stopword to the end of the list, if it has not been added yet.
         * @param stopword the stopword to be added
         * @return true if the stopword was added, and false otherwise
         */
        public boolean add(String stopword)  {
            if(count == 0){
                Node newNode = new Node(stopword);
                this.tail = newNode;
                this.head = newNode;
                count++;
                return true;
            }
            if(contains(stopword)){
                return false;
            }

            Node newNode = new Node(stopword);
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
        public String get(int index) {
            if (index<0 || index >= size()) {
                throw new IndexOutOfBoundsException();
            }
            Node current = head;
            while (index>0){
                current = current.next;
                index--;
            }
            return current.stopword;
        }

        /**
         * Returns true if the list contains the stopword received
         * as parameter.
         * @param stopword the element to search for
         * @return true if the list contains the specified element
         */
        public boolean contains(String stopword) {
            Node current = head;
            while(current != null){
                if (current.stopword.equals(stopword)) {
                    return true;
                }
                current = current.next;
            }
            return false;
        }

        @Override
        public String toString() {
            String result = "[";
            Node current = head;
            while(current != null){
                result += current.stopword;
                if (current.next != null)
                    result += ", ";
                current = current.next;
            }
            return result + "]";
        }

}
