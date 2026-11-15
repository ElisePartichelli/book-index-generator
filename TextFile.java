/**
 * Class responsible for reading the text file.
 * @author Isabel H. Manssour
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextFile {
    private BufferedReader reader;

    /*
    * Opens the file that contains the text
    */
    public void open(String fileName){
        Path path1 = Paths.get(fileName);
        try {
           reader = Files.newBufferedReader(path1, Charset.defaultCharset());
        } catch (IOException e) {
            System.err.format("Error reading file: %s%n", e);
        }
    }

    /**
     * Returns the next line of the file. If the end of the file was reached,
     * returns null.
     * @return the next line of the file.
     */
    public String getNextLine()  {
        if (reader == null)
            return null;
        try {
            String line = null;
            if( (line = reader.readLine()) != null)
                return line;
        } catch (IOException e) {
            System.err.format("Error reading file: %s%n", e);
        }
        return null;
    }

    /**
     * Closes the file.
     */
    public void close() {
        if (reader == null)
            return;
        try {
            reader.close();
        } catch (IOException e) {
            System.err.format("Error closing file: %s%n", e);
        }
    }
}
