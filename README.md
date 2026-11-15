# Book Index Generator

A Java console application that reads a text file and builds a book-style index: an alphabetical list of words, each followed by the pages where it appears. Common words (stopwords) are filtered out.

All data structures are **singly linked lists implemented from scratch**. No Java Collections are used for storage.

Originally developed as the third assignment (T3) for the Algorithms and Data Structures course (2022).

## How It Works

1. The program loads a list of stopwords from `texts/StopWords-EN.txt`.
2. It reads the chosen text file line by line, treating every 40 lines as one page.
3. Each line is normalized (punctuation removed, converted to lowercase) and split into words.
4. Stopwords are counted and ignored. Every other word is inserted into a sorted linked list.
5. Each word keeps its own linked list of the pages where it occurs, without duplicates.

## Features

Once the index is built, an interactive menu lets you:

- Show the complete index in alphabetical order
- Show what percentage of the text is made up of stopwords
- Find the most frequent word (the one that appears on the most pages)
- Search for a specific word and see its pages

## Data Structures

| Class | Purpose |
|---|---|
| `SortedWordList` | Linked list of words kept in alphabetical order, using sorted insertion |
| `OccurrenceList` | Linked list of the page numbers where a word occurs |
| `StopwordList` | Linked list of words that are ignored during indexing |
| `TextFile` | Reads the input file line by line |
| `TextLine` | Normalizes a line and splits it into words |

## Technologies

- Java (no external dependencies)

## How to Run

Requires a JDK (Java 8 or later).

```bash
git clone <repo-url>
cd book-index-generator
javac *.java
java Main
```

When asked, enter the name of a file located in the `texts/` folder, for example:

```
sample.txt
```

To index your own text, add a `.txt` file to the `texts/` folder and enter its name when the program starts.

## Example Output

```
Word: node
Pages: [1, 2]

Word: dictionary
Pages: [2]
```
