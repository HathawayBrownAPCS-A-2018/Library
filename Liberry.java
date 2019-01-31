/**
 * This class represents a Liberry, which contains a list of Books
 * @author Linda Yu + APCSA
 * @version THE BEST VERSION
 */
import java.util.Scanner;
import java.io.*;
public class Liberry
{
    private Book[] bookList;
    private int numBooks;
    public static final int MAX_BOOKS = 100;
    public void printTitles()
    {
        System.out.println("Your library has " + numBooks + " books.");
        for (int i = 0; i < numBooks; i ++)
        {
            System.out.println(bookList[i].getTitle());
        }
    }
    public void addBook (Book b)
    {
        bookList[numBooks] = b;
        numBooks ++;
    }
    public void fillFromKeyboard()
    {
        String resp = "Y";
        Scanner kbd = new Scanner (System.in);
        while (resp.charAt(0) == 'Y' || resp.charAt(0) == 'y')
        {
            addBook(Book.getBookFromKeyboard());
            System.out.print("Add another book? ");
            resp = kbd.nextLine();
        }
        kbd.close();
    }
    /**
     * Constructor initializes the array to 0 Books
     */
    public Liberry()
    {
        bookList = new Book[MAX_BOOKS];
        numBooks = 0;
    }
    /**
     * Fills the Library from a data file.
     * Precondition:  file BookList.tsv exists and is formatted as a 
     *    tab-separated value file (.tsv).  
     *    Fields: timestamp, title, author, date, pages
     *    BookList.tsv contains at most MAX_BOOKS books
     * Postcondition:  array bookList holds all of the Books in the file.
     */
    private void fillFromFile ()
    {
      // open the file
      File file = new File("BookList.tsv");
      Scanner input = null;
      try
      {
          input = new Scanner(file);
      }
      catch (FileNotFoundException ex)
      {
          System.out.println("*** Cannot open BookList.tsv ***");
          System.exit(1);        // quit the program
      } 

      // read the Books from the file, one per line
      while (input.hasNext())
      {
          String nextBook = input.nextLine();
          System.out.println (nextBook);
          addBook(getBookFromString(nextBook));
      }      
      input.close();
      
    }
    public Book getBookFromString(String s)
    {
        String temp = s;
        temp = temp.substring(temp.indexOf("\t")+1);
        String theTitle = temp.substring(0,temp.indexOf("\t"));
        temp = temp.substring(temp.indexOf("\t")+1);
        String theAuthor = temp.substring(0,temp.indexOf("\t"));
        temp = temp.substring(temp.indexOf("\t")+1);
        int thePublicationYear;
        try {
            thePublicationYear = Integer.parseInt(temp.substring(0, temp.indexOf("\t")));
 
            }
        catch (NumberFormatException e)
           {
              return null;
           }
        temp = temp.substring(temp.indexOf("\t")+1);
        int theNumPages;
        try {
            theNumPages = Integer.parseInt(temp);
 
             }
         catch (NumberFormatException e)
            {
             return null;
             }
       
        Book b = new Book(theTitle, theAuthor, thePublicationYear, theNumPages);
        return b;
    }
    public int findIndex(Book b)
    {
        return 0;
    }
    
    public static void main (String[] args)
    {
        Liberry myBooks = new Liberry();
        myBooks.fillFromFile();
        myBooks.printTitles();
    }
}