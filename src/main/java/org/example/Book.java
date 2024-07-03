package org.example;
import java.util.Date;
public class Book {
    int bookID;
//    String name;
    String title;
    String author_name;
    int no_Of_copies;

    long issue_time;

    public Book(int bookID, String title, String author_name, int no_Of_copies) {
        this.bookID = bookID;
        //this.name = name;
        this.title = title;
        this.author_name = author_name;
        this.no_Of_copies = no_Of_copies;
    }

    public Book(){

    }

    public String getTitle() {
        return title;
    }

    public int getBookID() {
        return bookID;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public int getNo_Of_copies() {
        return no_Of_copies;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public void setNo_Of_copies(int no_Of_copies) {
        this.no_Of_copies = no_Of_copies;
    }
}
