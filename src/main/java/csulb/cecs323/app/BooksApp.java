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

   public static void main(String[] args) {
      LOGGER.fine("Creating EntityManagerFactory and EntityManager");
      EntityManagerFactory factory = Persistence.createEntityManagerFactory("Books");
      EntityManager manager = factory.createEntityManager();
      // Create an instance of CarClub and store our new EntityManager as an instance variable.
      BooksApp booksApp = new BooksApp(manager);

      bookList = getAllBooks(entityManager);

      if (bookList.size() == 0){
         System.out.println("Empty list");
      }

      // Any changes to the database need to be done within a transaction.
      // See: https://en.wikibooks.org/wiki/Java_Persistence/Transactions

      LOGGER.fine("Begin of Transaction");
      EntityTransaction tx = manager.getTransaction();

      Scanner scanner = new Scanner(System.in);

      tx.begin();
      // List of owners that I want to persist.  I could just as easily done this with the seed-data.sql
      // Load up my List with the Entities that I want to persist.  Note, this does not put them
      // into the database.

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

      deleteBook(manager, scanner);
      // Commit the changes so that the new data persists and is visible to other users.
      tx.commit();
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

   private static List<Books> getAllBooks(EntityManager manager){
      return manager.createNamedQuery("getAllBooks", Books.class).getResultList();
   }

   public static void deleteBook(EntityManager manager, Scanner scanner) {
      boolean remove = true;

      while (remove) {

         System.out.println("Select book to delete: ");
         for (Books book : bookList) {
            System.out.println(book);
         }

         int choice = scanner.nextInt();

         if(choice == -1){
            remove = false;
         }

         bookList.remove(choice);
      }
   }

   public void updateBook(){

   }


} // End of Books class