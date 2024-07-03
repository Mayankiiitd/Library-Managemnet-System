package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    public static ArrayList<Book> books = new ArrayList<>();
    public static ArrayList<Members> member = new ArrayList<>();
    public static ArrayList<Book> avialable_Book = new ArrayList<>();

    public static void display_books(){
        System.out.println("Available Books.... ");
        for (Book b1 : books)
        {
            System.out.println("Book ID: "+ b1.getBookID());
            System.out.println("Name: "+ b1.getTitle());
            System.out.println("Author: "+b1.getAuthor_name());
            System.out.println("No. of copies: "+b1.getNo_Of_copies());
            System.out.println("\n \n");

        }
    }

    public static void display_Available_books(){
        System.out.println("Available Books.... ");
        //            System.out.println("No. of copies: "+b1.getNo_Of_copies());
        for (Book b1 : avialable_Book) {
            System.out.println("Book ID: " + b1.getBookID());
            System.out.println("Name: " + b1.title);
            System.out.println("Author: " + b1.getAuthor_name());
            System.out.println("\n");
        }
    }
    public static int rertunBookArraySize(){
        return books.size();
    }

    public void display_Members(){
        System.out.println("\nMember list.... \n");
        int ind = 0;
        for(Members m : member){
            ind++;
            System.out.println(ind +".Name: "+ m.getName() +
                    "\n  Age: "+ m.getAge() +
                    "\n  Number: " +m.getNumber()+
                    "\n  Total fine: Rs."+m.getPenalty());
            System.out.printf("  %s%n" , m.listMyBooks(m));
        }
        System.out.println("\n \n");
    }

    public static boolean search_member(String num){
        for(Members M1 : member)
        {
            if(num.equals(M1.number))
            {
                return true;
            }
//            continue;
        }
        return false;
    }

    public static Members serch_listed_member(String num){
        Members tem = null;

        for(Members M1 : member)
        {
            if(num.equals(M1.number))
            {
                tem = M1;
            }
//            continue;
        }
        return tem;
    }

    public void add_Book(int bookID, String title, String author, int total_Copies){

        Book b1 = new Book(bookID, title, author, total_Copies);
        books.add(b1);
//        avialable_Book.add(b1);
        System.out.println("Book added successfully.... ");
    }

    public void remove_Book(int bookID)
    {
        int idx = -1;
        boolean search = false;
        for (Book i : avialable_Book)
        {
            idx += 1;
            if (bookID == i.bookID) {
                search = true;
                avialable_Book.remove(idx);
                break;
            }
            continue;
        }

        if(search){
            int idx2 = -1;
            for (Book j : books)
            {
                idx2 += 1;
                if (bookID == j.bookID) {
                    books.remove(idx2);
                    break;
                }
                continue;
            }
            System.out.println("Book Removed Successfully...");
        }
        else{
            System.out.println("Book doesn't found....");
        }
    }

    public void register_Member(String name, int age, String number){
        if(search_member(number)){
            System.out.println("Member already exixts...");
        }
        else{
            Members newMember = new Members(name, age, number, 0);
            member.add(newMember);
//            System.out.println("Member registered successfully...");
        }
    }

    public void remove_Member(String number)
    {
        boolean search = false;
        int idx = -1;
        for(Members M1 : member)
        {
            idx += 1;
            if(number.equals(M1.number))
            {
                search = true;
//                break;
            }
            //continue;
        }
        if(search)
        {
            member.remove(idx-1);
            System.out.println("Member removed successfully...");
        }
        else
        {
            System.out.println("Member doesn't exists...");
        }
    }

    public static boolean search_Book(int b_id){
        for (Book itr : books) {
            if (itr.bookID == b_id) {
                return true;
            }
        }
        return false;
    }
    public static boolean search_Available_Book(int b_id){
        for (Book itr : avialable_Book) {
            if (itr.bookID == b_id) {
                return true;
            }
        }
        return false;
    }
    

}


