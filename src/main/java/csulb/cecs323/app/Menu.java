package csulb.cecs323.app;

import csulb.cecs323.model.*;

import java.util.Scanner;

public class Menu {
    Menu(){

    }

    public void displayOptions(){
        System.out.println("1. Add new objects: \n" +
                "2. List information \n" +
                "3. Delete a book \n" +
                "4. Update a book\n" +
                "5. List primary keys");
    }

    public void addEntity(Scanner in){
        System.out.println("1. Add new Authoring Entity\n" +
                "2. Add a new publisher\n" +
                "3. Add a new book");
    }

    public void addAuthoringEntity(Scanner in){
        System.out.println("Add an authoring entity: ");
        System.out.println("1. Add WritingGroup\n" +
                "2. Add Individual Author\n" +
                "3. Add Ad Hoc Team\n" +
                "4. Add an individual author to an existing Ad Hoc Team");
    }

/*
    int menuChoice = booksApp.checkInput(in,1,5);
      switch(menuChoice)
    {
        //add something
        case 1:
        {

            int addSomethingChoice = booksApp.checkInput(in,1,3);
            switch(addSomethingChoice)
            {
                //add AE
                case 1:
                {

                    int aeType = booksApp.checkInput(in,1,4);
                    switch(aeType)
                    {
                        // Add writing group
                        case 1:
                        {
                            System.out.println("Add a WritingGroup! : ");

                            try{
                                System.out.println("Name: ");
                                String name = booksApp.checkStringLength(in,80);

                                System.out.println("Email: ");
                                String email = booksApp.checkStringLength(in,30);

                                System.out.println("Head Writer: ");
                                String HW = booksApp.checkStringLength(in,80);

                                System.out.println("Year formed:  ");
                                int year = in.nextInt();

                                WritingGroup w = new WritingGroup(email,name,HW,year);
                                BooksApp.entityManager.persist(w);
                                BooksApp.entityManager.flush();


                            }catch (Exception e)
                            {System.out.println("Error. Already in database or incorrect information. Please Try again Later.");
                            }

                            //make WG
                            //ask for name, email,
                            //head writer
                            //year
                            break;
                        }
                        // Add inidividual author
                        case 2:
                        {
                            //make IA
                            //ask name, email
                            System.out.println("Add an Individual Author! : ");

                            try{

                                System.out.println("Name: ");
                                String name = booksApp.checkStringLength(in,80);

                                System.out.println("Email: ");
                                String email = booksApp.checkStringLength(in,30);

                                IndividualAuthors w = new IndividualAuthors(email,name);
                                BooksApp.entityManager.persist(w);
                                BooksApp.entityManager.flush();


                            }catch (Exception e)
                            {System.out.println("Error. Already in database or incorrect information. Please Try again Later.");
                            }

                            break;
                        }
                        // Add ad hoc team
                        case 3:
                        {
                            //make AHT
                            //ask name, email
                            System.out.println("Add an Ad Hoc Team Group! : ");

                            try{
                                System.out.println("Name: ");
                                String name = booksApp.checkStringLength(in,80);

                                System.out.println("Email: ");
                                String email = booksApp.checkStringLength(in,30);

                                AdHocTeams w = new AdHocTeams(email,name);
                                BooksApp.entityManager.persist(w);
                                BooksApp.entityManager.flush();

                            }catch (Exception e)
                            {System.out.println("Error. Already in database or incorrect information. Please Try again Later.");
                            }

                            break;
                        }
                        // Add author to existing ad hoc team
                        case 4:
                        {
                            System.out.println("Add individual author to ad hoc teams: ");

                            if (individualAuthorsList.size() == 0){
                                System.out.println("Empty individual authors list. Please add an individual author first");
                                break;
                            }
                            if (AHTList.size() == 0){
                                System.out.println("Empty aht list. Please add an ad hoc team first.");
                                break;
                            }

                            try {

                                System.out.println("Select an indivdual Author: ");
                                AuthoringEntity ia = booksApp.selectAE(in,individualAuthorsList);

                                System.out.println("Select an AdHocTeam: ");
                                AuthoringEntity aht = booksApp.selectAE(in, AHTList);

                                Ad_Hoc_Teams_Member newMember = new Ad_Hoc_Teams_Member((AdHocTeams) aht, (IndividualAuthors) ia);

                                entityManager.persist(newMember);
                                entityManager.flush();
                            }catch (Exception e)
                            {
                                System.out.println("Error. Already in database or incorrect information. Please Try again Later.");
                            }

                            break;
                        }
                    }
                    break;
                }
                //add publisher
                case 2:
                {
                    System.out.println("Add a publisher: ");


                    //need to error handle. might put in a function.
                    //ask for name

                    //ask for phone

                    //ask for email
                    try {

                        System.out.println("Name: ");
                        String name = booksApp.checkStringLength(in,80);

                        System.out.println("Phone: ");
                        String phone = booksApp.checkStringLength(in,24);

                        System.out.println("Email: ");
                        String email = booksApp.checkStringLength(in,80);

                        Publisher w = new Publisher(name,phone,email);
                        BooksApp.entityManager.persist(w);
                        BooksApp.entityManager.flush();

                    }catch (Exception e)
                    {System.out.println("Error. Already in database or incorrect information. Please Try again Later.");
                    }


                    break;
                }
                //add book
                case 3:
                {
                    System.out.println("Add a book: ");
                    //put list of publishers
                    if (publisherList.size() == 0){
                        System.out.println("Empty publisher list, so add a publisher first! Thank you!");
                        break;
                    }
//
                    if (AEList.size() == 0){
                        System.out.println("Empty AE list, so add an authoring entity first! Thank you!");
                        break;
                    }
                    Publisher p = booksApp.selectPublisher(in,publisherList);

                    AuthoringEntity ae = booksApp.selectAE(in,AEList);

                    //select a publisher.

                    //pust a list of ae's

                    //select ae

                    //ask for book stuff
                    //might need some error handling. might put in a function.
                    try{
                        String ISBN;
                        System.out.println("ISBN: ");
                        ISBN = booksApp.checkStringLength(in,17);
                        in.nextLine();
                        System.out.println("Title: ");
                        String title;
                        title= booksApp.checkStringLength(in,80);
                        System.out.println("Year published: ");
                        int year;
                        year = in.nextInt();
                        in.nextLine();

                        Books b = new Books(ISBN, title, year, ae, p);

                        entityManager.persist(b);
                        entityManager.flush();
                    }catch (Exception e)
                    {System.out.println("Error. Already in database or incorrect information. Please Try again Later.");
                    }

                    break;
                }
            }

            break;
        }

        //list info about object
        case 2:
        {
            System.out.println("Listing information about: ");
            int listMenuChoice = booksApp.checkInput(in,1,3);
            switch(listMenuChoice)
            {
                //show list of publishers and select one?
                case 1:
                {
                    break;
                }
                //show list of books and select one?
                case 2:
                {
                    break;
                }
                //show list of writing groups and select 1?
                case 3:
                {
                    break;
                }
            }
            break;
        }

        //delete book
        case 3:
        {
            deleteBook(in);
            break;
        }

        //update book
        case 4:
        {
            //show list of books. and select 1.

            System.out.println("New AE for book: ");
            break;
        }

        //list the primary key of all rows of something
        case 5:
        {
            System.out.println("1. Publishers\n2. Books(title + ISBN)\n3. Authoring entites(w/type)");
            int primaryKeyInfoChoice = booksApp.checkInput(in,1,3);

            switch(primaryKeyInfoChoice)
            {
                //publishers
                case 1:
                {
                    break;
                }
                //books
                case 2:
                {
                    break;
                }
                //authoring entities
                case 3:
                {
                    break;
                }
            }
            break;
        }
    }

 */
}
