/**
 * This class stores the words of the index in alphabetical order.
 * @author Isabel H. Manssour
 */

public class SortedWordList {
    private Word head;
    private Word tail;
    private int count;
    private StopwordList stopwords;
    private int ignored;
    private Word mostFrequent;

    // Inner class
    private class Word {
        public String s;
        public OccurrenceList occurrences;
        public Word next;

        // The constructor already receives the page number of the first occurrence
        public Word(String str, int pageNumber){
            s = str;
            next = null;
            occurrences = new OccurrenceList();
            occurrences.add(pageNumber);
        }

        @Override
        public String toString() {
            return "Word: "+ this.s + "\nPages: " + this.occurrences.toString() + "\n\n";
    }}

    public SortedWordList(){
        head = null;
        tail = null;
        count = 0;
        stopwords = new StopwordList();
        ignored = 0;
        mostFrequent = null;
    }// Constructors

    public SortedWordList(StopwordList stopwordList){
        head = null;
        tail = null;
        count = 0;
        stopwords = stopwordList;
        ignored = 0;
        mostFrequent = null;
    }

    public int getStopwordCount(){
        return ignored;
    }

    public String getMostFrequent(){
        if (mostFrequent == null)
            return "The list is empty.";
        return mostFrequent.toString();
    }


    /**
     * Adds a word to the list, keeping it in alphabetical order.
     * Stopwords are ignored. If the word is already in the list, only the
     * page number is added to its list of occurrences.
     * @param str the word to be added
     * @param pageNumber the page where the word occurs
     */
    protected void add(String str, int pageNumber) {
        if(this.stopwords.contains(str)){
            ignored++;
            return;
        }

        Word previous = null;
        Word current = head;
        while(current != null && current.s.compareTo(str) < 0){
            previous = current;
            current = current.next;
        }

        if(current != null && current.s.equals(str)){
            current.occurrences.add(pageNumber);
            if(current.occurrences.size()>mostFrequent.occurrences.size()) mostFrequent = current;
            return;
        }

        Word newWord = new Word(str,pageNumber);
        newWord.next = current;
        if(previous == null){
            this.head = newWord;
        }else{
            previous.next = newWord;
        }
        if(current == null){
            this.tail = newWord;
        }
        if(mostFrequent == null){
            mostFrequent = newWord;
        }
        count++;
    }// adds a word to the list

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
        ignored = 0;
        mostFrequent = null;
    }

    /**
     * Returns the element at a given position of the list.
     * @param index the position in the list
     * @return the element at the specified position
     * @throws IndexOutOfBoundsException if (index < 0 || index >= size())
     */
    private Word get(int index) throws IndexOutOfBoundsException {
        if (index<0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        Word current = head;
        while (index>0){
            current = current.next;
            index--;
        }
        return current;
    }

    /**
     * Returns the position of the word received as parameter.
     * @param word the element to search for
     * @return the index if the word is in the list, or -1 if it is not
     */
    public int contains(String word) {
        Word current = head;
        int index = 0;
        while(current != null){
            if ((current.s).equals(word)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public String toString() {
        String result = "";
        Word current = head;
        while(current != null){
            result += current.toString();
            current = current.next;
        }
        return result;
    }


    public String getByWord(String str) {
        int index = contains(str.toLowerCase());
        if (index == -1)
            return "Word not found in the list.";
        return get(index).toString();
    }

}
