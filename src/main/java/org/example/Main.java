package org.example;

import java.util.*;

/*
* Assumptions:
* 1. While adding a book in library, we assume that user know the book id that user want to add. If book is present in
*    then we simply take number copies as input and increase the number of copies of the book that is already present by
*    given book id, otherwise we simply take author name and title and create a new book and add them in the library.
*
* 2. In this code we are calculating the fine of a particular member at the time when the user return the book.
*/

public class Main {
    public static void main(String[] args)
    {
        System.out.println("Hello world!");
        Library l1 = new Library();
        Scanner sc = new Scanner(System.in);

        boolean fl = true;
        while (fl) {
            System.out.println("Library portal initialized.....\n1. Enter as a librarian\n2. Enter as a member\n3. Exit");
            System.out.println("__________________________________________________");
            String opt = sc.next();
            sc.nextLine();

            while (true) {
                // Enter as a librarian.
                if ("1".equals(opt)) {
                    System.out.println("""
                            1. Register a member
                            2. Remove a member
                            3. Add a book
                            4. Remove a book
                            5. View all members along with their books and fines to be paid
                            6. View all books
                            7. Back
                            ____________________________________________""");
                    String pre = sc.next();
                    if ("1".equals(pre)) {
                        do {
                            System.out.print("Name: ");
                            String na = sc.nextLine();
                            sc.nextLine();
                            System.out.print("Phone no: ");
                            String no = sc.next();
                            System.out.print("Age: ");

                            if(sc.hasNextInt()){
                                int ag = sc.nextInt();
                                l1.register_Member(na, ag, no);
                                System.out.println("Member registered successfully...");
                                break;
                            }else{
                                System.out.println("Invalid age!");
                                sc.nextLine();
                                break;
                            }

                        } while (true);


                    } else if ("2".equals(pre)) {
                        System.out.print("Enter the phone no: ");
                        String no = sc.next();
                        l1.remove_Member(no);
                    }
                    else if ("3".equals(pre))
                    {
                        //Assumption: if the given book id is already exists then we simply increase the number of copies of that book;
                        //else we form a new book object and add this book in the library;

                        System.out.println("Enter book title: ");
                        String title = sc.nextLine();
                        sc.nextLine();
                        System.out.println("Enter Author name: ");
                        String authorName = sc.nextLine();
//                        sc.next();
                        System.out.println("Enter number of copies: ");
                        if(sc.hasNextInt()){
                            int copies = sc.nextInt();
                            int id = Library.rertunBookArraySize();

                            for(int i = id+1; i<=id+copies; i++){
                                Book b = new Book(i, title, authorName, 1);
                                Library.avialable_Book.add(b);
                                Library.books.add(b);
                            }

                        }

                    } else if ("4".equals(pre)) {
                        System.out.print("Enter book id which you want to delete: ");
                        int bid = sc.nextInt();
                        l1.remove_Book(bid);
                    } else if ("5".equals(pre)) {
                        l1.display_Members();
                    } else if ("6".equals(pre)) {
                        Library.display_books();
                    } else if ("7".equals(pre)) {
                        break;

                    } else {
                        System.out.println("\nEnter a valid option!\n\n");
                    }
                }

                // Enter as a member.
                else if ("2".equals(opt)) {

                    System.out.print("Enter phone no: ");
                    String num = sc.next();

                    if (!Library.search_member(num)) {
                        System.out.println("Member with Phone No: " + num + " doesn't exist.\n");
                        System.out.println("Please, " + num + " first register yourself! \n \n");
                        break;
                    } else {
                        Members m1 = Library.serch_listed_member(num);
                        System.out
                                .println("Welcome " + m1.getNumber() + " with Member ID: <" + m1.getMember_ID() + ">\n");
                        System.out.println("""
                                1. List Available Books
                                2. List My Books
                                3. Issue Book
                                4. Return book
                                5. Pay Fine
                                6. Back
                                ________________________________________""");
                        String option = sc.next();
                        if (option.equals("1")) {
                            Library.display_Available_books();
                        }
                        else if (option.equals("2")) {
                            System.out.println("Issued books are: \n");
                            m1.listMyBooks(m1);
                        }
                        else if (option.equals("3")) {
                            m1.issueBook(num);
                            // System.out.println("Book issued successfully");
                        }
                        else if (option.equals("4")) {
                            // Return fine;
//                            System.out.println("Enter Book ID: ");
//                            int x = sc.nextInt();
                            m1.returnBook(num);
                            //System.out.println("Book return successfully!\n");
                            break;
                        }
                        else if (option.equals("5")) {
                            // Pay fine;
                            Members m = Library.serch_listed_member(num);
                            m.pay_Fine(m);
                        }
                        else if (option.equals("6")) {
                            break;
                        } else {
                            System.out.println("Enter a valid option!");
//                            break;
                        }
                    }
                }

                // Exit.
                else if ("3".equals(opt)) {
                    System.out.println("Thanks for visiting!");
                    fl = false;
                    break;
                } else {
                    System.out.println("\nPlease choose a valid option\n\n");
                    break;
                }
            }
        }

    }
}