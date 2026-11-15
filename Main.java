import java.util.Scanner;



/**
 * Class that starts the application.
 * @author Isabel H. Manssour
 */
public class Main {
    private static String[] options = {"1- Show the whole index (in alphabetical order)",
                        "2- Show the percentage of stopwords in the text",
                        "3- Find the most frequent word, that is, the one with the most occurrences",
                        "4- Search for a word",
                        "5- Exit"};

    private static final int LINES_PER_PAGE = 40;
    private static int wordCount = 0;
    public static void main(String[] args) throws Exception {

    Scanner readMenu = new Scanner(System.in);
    int lineCount = 0;
    int pageNumber = 1;


    TextFile file = new TextFile(); // object that manages the file
    TextLine textLine = new TextLine(); // object that manages a line
    String l;

    StopwordList stopwords = loadStopwords("StopWords-EN.txt");
    SortedWordList wordList = new SortedWordList(stopwords); // the index itself

    System.out.println("Enter the name of the text file to process: ");
    file.open("texts/"+ readMenu.nextLine());
    //file.open(args[0]); // use this line to read the file name from the command line
    do  // loop over each line of the file
    {
        l = file.getNextLine();
        if (l==null) // end of the file?
           break;
        lineCount++; // count one more line read from the file

        textLine.setLine(l); // set the text of the line
        do // loop over each word of a line
        {
            String word = textLine.getNextWord(); // get the next word of the line
            if (word == null)// end of the line
            {
                break;
            }
            wordCount++;
            wordList.add(word, pageNumber);
         } while (true);

        if (lineCount == LINES_PER_PAGE) // reached the end of the page?
        {
            lineCount = 0;
            pageNumber++;
        }

    } while (true);



    menu(readMenu, wordList);

    file.close();
    readMenu.close();
    }

    private static void menu(Scanner scanner, SortedWordList wordList){
        int option = 0;
        while (option!=5){
            printMenu();
            try {
                option = scanner.nextInt();
                switch (option){
                    case 1: System.out.println(wordList.toString()); break;
                    case 2: printStopwordPercentage(wordList); break;
                    case 3: System.out.println(wordList.getMostFrequent()); break;
                    case 4: searchWord(scanner, wordList);break;
                    case 5: return;
                }
            }
            catch (Exception ex){
                System.out.println("Please enter an integer value between 1 and " + options.length);
                scanner.next();
            }
        }
    }

    private static void printStopwordPercentage(SortedWordList wordList) {
        if (wordCount == 0) {
            System.out.println("The text has no words.");
            return;
        }
        double percentage = (double)wordList.getStopwordCount()/(double)wordCount*100;
        System.out.printf("%d stopwords in the text, out of a total of %d words. They represent %.2f%% of the text%n",
                          wordList.getStopwordCount(), wordCount, percentage);
    }

    private static void searchWord(Scanner scanner, SortedWordList wordList) {
        System.out.println("Enter the word to search for: ");
        System.out.println(wordList.getByWord(scanner.next()));
    }

    private static void printMenu(){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Choose an option: ");
    }

    private static StopwordList loadStopwords(String fileName) throws Exception{
        TextFile stopwordsFile = new TextFile(); // object that manages the file
        stopwordsFile.open("texts/"+fileName); // all text files are expected to be in the texts folder
        String l = stopwordsFile.getNextLine();
        StopwordList stopwordList = new StopwordList();

        while(l!=null){
            String stopword = l.trim().toLowerCase();
            if (!stopword.isEmpty())
                stopwordList.add(stopword); // in a stopword list, each line is one word
            l = stopwordsFile.getNextLine();
        }
        stopwordsFile.close();

    return stopwordList;
    }

}
