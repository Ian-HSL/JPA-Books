/*
 * Licensed under the Academic Free License (AFL 3.0).
 *     http://opensource.org/licenses/AFL-3.0
 *
 *  This code is distributed to CSULB students in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, other than educational.
 *
 *  2018 Alvaro Monge <alvaro.monge@csulb.edu>
 *
 */

package csulb.cecs323.app;

// Import all of the entity classes that we have written for this application.
import csulb.cecs323.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * A simple application to demonstrate how to persist an object in JPA.
 * <p>
 * This is for demonstration and educational purposes only.
 * </p>
 * <p>
 *     Originally provided by Dr. Alvaro Monge of CSULB, and subsequently modified by Dave Brown.
 * </p>
 */
public class BooksApp {
   /**
    * You will likely need the entityManager in a great many functions throughout your application.
    * Rather than make this a global variable, we will make it an instance variable within the CarClub
    * class, and create an instance of CarClub in the main.
    */
   private static EntityManager entityManager;

   /**
    * The Logger can easily be configured to log to a file, rather than, or in addition to, the console.
    * We use it because it is easy to control how much or how little logging gets done without having to
    * go through the application and comment out/uncomment code and run the risk of introducing a bug.
    * Here also, we want to make sure that the one Logger instance is readily available throughout the
    * application, without resorting to creating a global variable.
    */
   private static final Logger LOGGER = Logger.getLogger(Books.class.getName());

   /**
    * The constructor for the CarClub class.  All that it does is stash the provided EntityManager
    * for use later in the application.
    * @param manager    The EntityManager that we will use.
    */
   public BooksApp(EntityManager manager) {
      this.entityManager = manager;
   }

   private static List<Books> bookList;
   private static List<Publisher> publisherList;
   private static List<AuthoringEntity> AEList;

   private static List<AuthoringEntity> AHTList;
   private static List<AuthoringEntity> individualAuthorsList;

   public static void main(String[] args) {
      LOGGER.fine("Creating EntityManagerFactory and EntityManager");
      EntityManagerFactory factory = Persistence.createEntityManagerFactory("BooksApp");
      EntityManager manager = factory.createEntityManager();
      // Create an instance of CarClub and store our new EntityManager as an instance variable.
      BooksApp booksApp = new BooksApp(manager);

      bookList = getBooks();
      publisherList = getPublishers();
      AEList = getAEs();
      individualAuthorsList = getIndividualAuthors();
      AHTList = getAdHocTeams();

      if (bookList.size() == 0){
         System.out.println("Empty list");
      }
      if (publisherList.size() == 0){
         System.out.println("Empty pub list");
      }
      if (AEList.size() == 0){
         System.out.println("Empty AE list");
      }
      if (individualAuthorsList.size() == 0){
         System.out.println("Empty individual authors list");
      }
      if (AHTList.size() == 0){
         System.out.println("Empty aht list");
      }

      // Any changes to the database need to be done within a transaction.
      // See: https://en.wikibooks.org/wiki/Java_Persistence/Transactions

      LOGGER.fine("Begin of Transaction");
      EntityTransaction tx = manager.getTransaction();


      Scanner in = new Scanner(System.in);

      tx.begin();
      // List of owners that I want to persist.  I could just as easily done this with the seed-data.sql
      // Load up my List with the Entities that I want to persist.  Note, this does not put them
      // into the database.

      System.out.println("1. Add new objects: \n2. List information " +
              "\n3. Delete a book \n4. Update a book\n5. List primary keys");


      int menuChoice = booksApp.checkInput(in,1,5);
      switch(menuChoice)
      {
         //add something
         case 1:
         {
            System.out.println("1. Add new Authoring Entity\n2. Add a new publisher\n3. Add a new book");
            int addSomethingChoice = booksApp.checkInput(in,1,3);
            switch(addSomethingChoice)
            {
               //add AE
               case 1:
               {
                  System.out.println("Add an authoring entity: ");
                  System.out.println("1. Add WritingGroup\n2. Add Individual Author\n3. Add Ad Hoc Team\n4. Add an individual author to an existing Ad Hoc Team");
                  int aeType = booksApp.checkInput(in,1,4);
                  switch(aeType)
                  {
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

                           booksApp.entityManager.persist(newMember);
                           booksApp.entityManager.flush();
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

                  booksApp.entityManager.persist(b);
                     booksApp.entityManager.flush();
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


      // TODO: Add new objects
      //          Add new authoring entity instance
      //             Writing group
      //             Individual author
      //             Ad Hoc team
      //             Add an individual author to existing ad hoc team
      //          Add a new publisher
      //          Add a new book
      //       List all the information about a specific object
      //          Publisher
      //          Book
      //          Writing group
      //       Delete a book
      //          prompt for all elements of candidate key
      //          make sure book exists
      //       Update a book
      //          change the authoring entity for existing book
      //       List the primary key of all rows of
      //          publishers
      //          books
      //             show title and isbn
      //          authoring entities

      deleteBook(manager, in);
      // Commit the changes so that the new data persists and is visible to other users.
      try {
         tx.commit();
      }
      catch(Exception e){
         System.out.println("?");
      }
      LOGGER.fine("End of Transaction");

   } // End of the main method

   /**
    * Create and persist a list of objects to the database.
    * @param entities   The list of entities to persist.  These can be any object that has been
    *                   properly annotated in JPA and marked as "persistable."  I specifically
    *                   used a Java generic so that I did not have to write this over and over.
    */
   public <E> void createEntity(List <E> entities) {
      for (E next : entities) {
         LOGGER.info("Persisting: " + next);
         // Use the CarClub entityManager instance variable to get our EntityManager.
         this.entityManager.persist(next);
      }

      // The auto generated ID (if present) is not passed in to the constructor since JPA will
      // generate a value.  So the previous for loop will not show a value for the ID.  But
      // now that the Entity has been persisted, JPA has generated the ID and filled that in.
      for (E next : entities) {
         LOGGER.info("Persisted object after flush (non-null id): " + next);
      }
   } // End of createEntity member method

   private static List<Books> getBooks(){
      return entityManager.createNamedQuery("getAllBooks", Books.class).getResultList();
   }

   private static List<Publisher> getPublishers(){
      return entityManager.createNamedQuery("getAllPubs", Publisher.class).getResultList();
   }

   private static List<AuthoringEntity> getAEs(){
      return entityManager.createNamedQuery("getAllAE", AuthoringEntity.class).getResultList();
   }

   private static List<AuthoringEntity> getAdHocTeams(){
      return entityManager.createNamedQuery("getAllSpecificMembers", AuthoringEntity.class).setParameter(1,"AdHocTeams").getResultList();
   }

   private static List<AuthoringEntity> getIndividualAuthors(){
      return entityManager.createNamedQuery("getAllSpecificMembers", AuthoringEntity.class).setParameter(1,"IndividualAuthors").getResultList();
   }

   private static void addBook(){

   }

   private static void deleteBook(EntityManager manager, Scanner scanner) {
      boolean remove = true;

      while (remove) {

         System.out.println("Select book to delete(0 to quit): ");
         int i = 0;
         for (Books book : bookList) {
            System.out.printf("%d. %s, %s%n", i + 1, book.getISBN(), book.getTitle());
            i++;
         }

         int choice = scanner.nextInt();

         while(choice < 0 || choice > bookList.size()){
            System.out.printf("Please enter a valid number(1-%d): ", bookList.size());
            choice = scanner.nextInt();
         }

         if(choice == 0){
            remove = false;
         } else {
            manager.remove(bookList.get(choice - 1));
            System.out.println("Book has been removed. ");
         }
      }
   }

   private static void updateBook(){

   }

   public int checkInput(Scanner in, int low, int high)
   {


      //Scanner in = new Scanner(System.in);

      int returnedInt = -1;
      while(returnedInt< low || returnedInt > high)
      {
         System.out.println("Select Choice: ");

         if (in.hasNextInt())
         {
            //System.out.println("Hi");
            returnedInt = in.nextInt();
            if (returnedInt < low || returnedInt > high)
            {
               System.out.println("Bad input. Try again.");
               returnedInt = -1;
            }
            in.nextLine();
         }
         else {
            if(in.hasNextLine()) {
               in.nextLine();
            }
         }


      }


      return returnedInt;
   }

   public Publisher selectPublisher (Scanner in, List<Publisher> pubs)
   {
      System.out.println("Publishers:");
      for (int i  = 0; i < pubs.size(); i++)
      {
         System.out.println(i + " " + pubs.get(i));
      }

      //Scanner in = new Scanner(System.in);
      int pickedPub = -1;
      while(pickedPub < 0 || pickedPub >= pubs.size()) {
         System.out.println("Enter Selection: ");
         pickedPub = in.nextInt();
      }
      System.out.println(pickedPub + ": " + pubs.get(pickedPub));
      return pubs.get(pickedPub);

   }

   public AuthoringEntity selectAE (Scanner in, List<AuthoringEntity> AE)
   {
      System.out.println("Authoring Entity:");
      for (int i  = 0; i < AE.size(); i++)
      {
         if(AE.get(i) instanceof WritingGroup)
         {

            System.out.println(i + " " + ((WritingGroup) AE.get(i)));
         }
         if(AE.get(i) instanceof IndividualAuthors)
         {

            System.out.println(i + " " + ((IndividualAuthors) AE.get(i)));
         }
         if(AE.get(i) instanceof AdHocTeams)
         {

            System.out.println(i + " " + ((AdHocTeams) AE.get(i)));
         }
      }

      //Scanner in = new Scanner(System.in);
      int pickedAE = -1;
      while(pickedAE < 0 || pickedAE >= AE.size()) {
         System.out.println("Enter Selection: ");
         pickedAE = in.nextInt();
      }
      System.out.println(pickedAE + ": " + AE.get(pickedAE));
      return AE.get(pickedAE);

   }

   public String checkStringLength(Scanner in, int length)
   {
      String input;
    do {
       input = in.nextLine();
       //in.nextLine();
       if(input.length()> length || input.length() <= 0)
       {
          System.out.println("Your input length is too long for our database. Please Try again: ");
       }
    }while(input.length() > length || input.length() <= 0);
    return input;
   }


} // End of Books class
