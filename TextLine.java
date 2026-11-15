/**
 *
 * @author Isabel H. Manssour
 */
public class TextLine {
    private String line;
    private String words[];
    private int wordIndex;

    /**
     * Receives the line string that will be stored.
     * @param text the line of text
     */
    public void setLine(String text) {
        line = text;
        line = line.replaceAll("\\t"," "); // replace tabs with blank spaces
        line = line.replaceAll(",",""); // remove commas
        line = line.replaceAll("\\.",""); // remove periods
        line = line.replaceAll("\\?",""); // remove question marks
        line = line.replaceAll("\\!",""); // remove exclamation marks
        line = line.replaceAll(":",""); // remove :
        line = line.replaceAll("'",""); // remove '
        line = line.replaceAll("--",""); // remove --
        line = line.replaceAll(";",""); // remove ;
        line = line.replaceAll("\\(",""); // remove (
        line = line.replaceAll("\\)",""); // remove )
        line = line.replaceAll("\"",""); // remove quotes
        line = line.toLowerCase().trim(); // convert to lowercase and trim the edges
        words = line.isEmpty() ? new String[0] : line.split("\\s+"); // split the string on whitespace, ignoring repeated spaces
        wordIndex = 0;
    }

    /**
     * Returns the next word of the line.
     * @return the word, or null if there are no more words.
     */
    public String getNextWord() {
      String word = null;
      if (wordIndex < words.length) {
          word = words[wordIndex];
          wordIndex++;
      }
      return word;
    }
}
