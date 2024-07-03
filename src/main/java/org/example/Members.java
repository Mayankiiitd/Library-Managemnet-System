package org.example;

import javax.sound.midi.Soundbank;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Scanner;

public class Members {
    String name;
    int age;
    String number;
    static int member_ID_count;

    public void setMember_ID(int member_ID) {
        this.member_ID = member_ID;
    }

    int member_ID = 0;

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    double penalty = 0;
    public List<Book> borrowed_Book;

    public Members(String name, int age, String number, int penalty) {
        this.name = name;
        this.age = age;
        this.number = number;
        this.penalty = penalty;
        this.borrowed_Book = new ArrayList<>();
        member_ID_count++;
        this.member_ID = member_ID_count;
    }

    public List<Book> getBorrowed_Book() {
        return borrowed_Book;
    }
    public void Display_My_Books(Members mem){
        for(Book m : mem.borrowed_Book){
            System.out.println("[Book Id: " + m.bookID + ", Author: "+ m.getAuthor_name() + "]\n");
        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getMember_ID() {
        return member_ID;
    }

    public void issueBook(String num)
    {
        Scanner ipt = new Scanner(System.in);
        if(Library.search_member(num)){
            Members m = Library.serch_listed_member(num);
            if(m.borrowed_Book.size() >= 2){
                System.out.println("You are not allow to borrow book, because you have already borrowed two books\n");
                return;
            }
            if(penalty > 0){
                System.out.println("Please pay fine which is Rs. "+ m.getPenalty());
                return;
            }
            else{
                System.out.println("You are allow to borrow book....\n");
                Library.display_Available_books();
                System.out.println("Enter only integer book id: ");
                int input_id = ipt.nextInt();

                boolean fl = false;
                if(Library.search_Available_Book(input_id)){
                    Book temp_Book = new Book();
                    for (Book itr : Library.books){
                        if(itr.bookID == input_id && itr.no_Of_copies > 0){
                            temp_Book = itr;
                            itr.no_Of_copies -= 1;
                            m.borrowed_Book.add(temp_Book);
                            temp_Book.issue_time = System.currentTimeMillis();
                            fl = true;
                            break;
                        }
                    }
                    for (Book itr : Library.avialable_Book){
                        if(itr.bookID == input_id){
                            temp_Book = itr;
                            Library.avialable_Book.remove(itr);
                            break;
                        }
                    }
                }
                if(fl == false){
                    System.out.println("copy of book is not available!");
                    return;
                }
                if(fl == true){
                    System.out.println("Book issued successfully");
                }
                else{
                    System.out.println("Please enter valid book id!");
                }
            }

        }
    }

    public Object listMyBooks(Members m){
        System.out.print("Borrowed books are: ");
        for(Book item : m.borrowed_Book){
            System.out.println("[Book Name: " + item.getTitle() +", Book Id: " +item.getBookID() +", Author: " + item.author_name + "]");
        }
        return null;
    }

    public boolean search_My_Book(int searchId, Members m1){
        for(Book index : m1.borrowed_Book){
            if(index.bookID == searchId){
                return true;
            }
        }
        return false;
    }
    public void returnBook(String num){
        Scanner sc = new Scanner(System.in);
        Members temp = Library.serch_listed_member(num);
        temp.Display_My_Books(temp);
        System.out.print("Enter book ID that you want to return");
        int searcID = sc.nextInt();

        Members dem = Library.serch_listed_member(num);
        if(search_My_Book(searcID, dem)){
            boolean res = false;
            long startDate = System.currentTimeMillis();
            for(Book itr : Library.books){
                if(itr.getBookID() == searcID){
                    itr.no_Of_copies = itr.no_Of_copies + 1;
                    res = true;
                    startDate = itr.issue_time;
                    break;
                }
            }
            for(Book it : dem.borrowed_Book)
            {
                if(it.bookID == searcID){
                    dem.borrowed_Book.remove(it);
                    res = true;
                    break;
                }
            }
            System.out.println("Book returned successfully!");
            //Date end_time = new Date();
            long overTime = ((System.currentTimeMillis() - startDate)/(1000));
            double fine = (overTime-10)*3;

            if(fine > 0)
            {
                dem.penalty = dem.penalty + fine;
                System.out.println("Your penalty is Rs. "+ dem.penalty);
                System.out.println("Do you want to pay fine ? (y/n)");
                String op = sc.next();
                if(op.equals("Y") || op.equals("y"))
                {
                    for(Members m : Library.member)
                    {
                        if(m.number == dem.number)
                        {
                            m.setPenalty(0);
                        }
                    }
                    System.out.println("Fine payed successfully!");
                }
                else if (op.equals("N") || op.equals("n"))
                {
                    return;
                }
                else
                {
                    System.out.println("Please select a valid option!");
                }

            }
        }
    }

    public void pay_Fine(Members m){
        System.out.println("You are fine is: Rs. " + m.getPenalty());
        if(m.getPenalty() > 0){
            System.out.println("Do you want to pay fine ? (y/n)");
            Scanner s = new Scanner(System.in);
            String ch = s.next();
            if((ch.equals("Y")) || (ch.equals("y"))){
                for(Members m1 : Library.member)
                {
                    if(m1.number.equals(m.number))
                    {
                        m1.setPenalty(0);
                    }
                }
                System.out.println("Fine payed successfully!");
                return;
            }
            else if((ch.equals("N")) || (ch.equals("n"))){
                return;
            }
            else{
                System.out.println("Select valid option!");
            }
        }
        else {
            return;
        }

    }
}

