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
import csulb.cecs323.BusinessLayer.BusinessLayer;
import csulb.cecs323.BusinessLayer.IBusinessLayer;
import csulb.cecs323.model.*;
import csulb.cecs323.model.AdHocTeam.AdHocTeam;
import csulb.cecs323.model.AdHocTeam.AdHocTeamsMember;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
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
    * The Logger can easily be configured to log to a file, rather than, or in addition to, the console.
    * We use it because it is easy to control how much or how little logging gets done without having to
    * go through the application and comment out/uncomment code and run the risk of introducing a bug.
    * Here also, we want to make sure that the one Logger instance is readily available throughout the
    * application, without resorting to creating a global variable.
    */
   private static final Logger LOGGER = Logger.getLogger(Book.class.getName());

   private static IBusinessLayer businessLayer;

   public static void main(String[] args) throws SQLIntegrityConstraintViolationException, IOException {
      // Create an instance of CarClub and store our new EntityManager as an instance variable
      EntityManagerFactory factory = Persistence.createEntityManagerFactory("BooksApp");
      EntityManager manager = factory.createEntityManager();

      businessLayer = new BusinessLayer(manager);

      Scanner in = new Scanner(System.in);
      boolean quit = false;

      while(!quit){
         Menu.displayOptions();

         int menuChoice = Validator.checkInput(in,0,5);
         switch(menuChoice)
         {
            //add something
            case 1:
            {
               Menu.addEntityOptions();
               int addSomethingChoice = Validator.checkInput(in,1,3);
               switch(addSomethingChoice)
               {
                  //add AE
                  case 1:
                  {
                     Menu.addAuthoringEntity();
                     int aeType = Validator.checkInput(in,1,4);
                     switch(aeType)
                     {
                        // Add writing group
                        case 1:
                        {
                           WritingGroup newWritingGroup = new WritingGroup();

                           System.out.println("Add a WritingGroup! : ");

                           System.out.println("Enter Name: ");
                           newWritingGroup.setName(Validator.checkStringLength(in,80));

                           System.out.println("Enter Email: ");
                           newWritingGroup.setEmail(Validator.checkStringLength(in,30));

                           System.out.println("Enter Head Writer: ");
                           newWritingGroup.setHeadWriter(Validator.checkStringLength(in,80));

                           System.out.println("Enter Year formed:  ");
                           newWritingGroup.setYearFormed(in.nextInt());

                           businessLayer.addAuthoringEntity(newWritingGroup);

                           break;
                        }
                        // Add inidividual author
                        case 2:
                        {
                           IndividualAuthor newIndividualAuthor = new IndividualAuthor();

                           System.out.println("Adding Individual Author!!");

                           System.out.println("Name: ");
                           newIndividualAuthor.setName(Validator.checkStringLength(in,80));

                           System.out.println("Email: ");
                           newIndividualAuthor.setEmail(Validator.checkStringLength(in,30));

                           businessLayer.addAuthoringEntity(newIndividualAuthor);

                           break;
                        }
                        // Add ad hoc team
                        case 3:
                        {
                           AdHocTeam newAdhocTeam = new AdHocTeam();

                           System.out.println("Add an Ad Hoc Team Group! : ");

                           System.out.println("Name: ");
                           newAdhocTeam.setName(Validator.checkStringLength(in,80));

                           System.out.println("Email: ");
                           newAdhocTeam.setEmail(Validator.checkStringLength(in,30));

                           businessLayer.addAuthoringEntity(newAdhocTeam);

                           break;
                        }
                        // Add individual author to existing ad hoc team
                        case 4:
                        {
                           AdHocTeamsMember newAdHocTeamsMember = new AdHocTeamsMember();

                           // Initialize Ad hoc teams and individual authors lists
                           List<AdHocTeam> adHocTeamsList = businessLayer.getAllAdHocTeams();
                           List<IndividualAuthor> individualAuthorsList = businessLayer.getAllIndividualAuthors();

                           System.out.println("Ad Hoc Teams: ");
                           for(int i = 0; i < adHocTeamsList.size(); i++){
                              AdHocTeam team = adHocTeamsList.get(i);
                              System.out.printf("%d. %-20s%n", i + 1, team.getName());
                           }

                           int adHocTeamsChoice = Validator.checkInput(in, 0, adHocTeamsList.size());

                           newAdHocTeamsMember.setAdHocTeam(adHocTeamsList.get(adHocTeamsChoice - 1));

                           System.out.println("Individual Teams: ");
                           for(int i = 0; i < individualAuthorsList.size(); i++){
                              IndividualAuthor author = individualAuthorsList.get(i);

                              System.out.printf("%d. %-20s%n", i + 1, author.getName());
                           }

                           int individualAuthorsChoice = Validator.checkInput(in, 0, individualAuthorsList.size());

                           newAdHocTeamsMember.setIndividualAuthor(individualAuthorsList.get(individualAuthorsChoice - 1));

                           businessLayer.addAdHocTeamMember(newAdHocTeamsMember);
                        }

                        case 0:
                        default:
                     }
                     break;
                  }
                  //add publisher
                  case 2:
                  {
                     Publisher newPublisher = new Publisher();

                     System.out.println("Add a publisher: ");

                     System.out.println("Name: ");
                     newPublisher.setName(Validator.checkStringLength(in,80));

                     System.out.println("Phone: ");
                     newPublisher.setPhone(Validator.checkStringLength(in,24));

                     System.out.println("Email: ");
                     newPublisher.setEmail(Validator.checkStringLength(in,80));

                     businessLayer.addPublisher(newPublisher);

                     break;
                  }
                  //add book
                  case 3:
                  {
                     //initialize publisher and author entity lists
                     List<Publisher> publisherList = businessLayer.getAllPublishers();
                     List<AuthoringEntity> authoringEntityList = businessLayer.getAllAuthoringEntities();
                     Book newBook = new Book();

                     System.out.println("Adding a book: ");
                     if (publisherList.size() == 0){
                        System.out.println("Empty publisher list, so add a publisher first! Thank you!");
                        break;
                     }

                     // display list of publishers
                     System.out.println("Selecting publisher: ");

                     for(int i = 0; i < publisherList.size(); i++){
                        Publisher pub = publisherList.get(i);
                        System.out.printf("%d. %-20s%n", i + 1, pub.getName());
                     }

                     int publisherChoice = Validator.checkInput(in, 0, publisherList.size());

                     newBook.setPub(publisherList.get(publisherChoice - 1));

                     if (authoringEntityList.size() == 0){
                        System.out.println("Empty AE list, so add an authoring entity first! Thank you!");
                        break;
                     }

                     // Select authoring entity
                     System.out.println("Selecting authoring entity");

                     for(int i = 0; i < authoringEntityList.size(); i++){
                        AuthoringEntity entity = authoringEntityList.get(i);
                        System.out.printf("%d. %-20s%n", i + 1, entity.getName());
                     }

                     int authoringEntityChoice = Validator.checkInput(in, 0, authoringEntityList.size());

                     newBook.setAE(authoringEntityList.get(authoringEntityChoice - 1));

                     System.out.println("ISBN: ");
                     newBook.setISBN(Validator.checkStringLength(in,17));

                     System.out.println("Title: ");
                     newBook.setTitle(Validator.checkStringLength(in,80));

                     System.out.println("Year published: ");
                     newBook.setYearPublished(in.nextInt());

                     businessLayer.addBook(newBook);

                     System.out.println("Book added");

                     System.in.read();
                     break;
                  }

                  case 0:
                  default:
               }
               break;
            }

            //list info about object
            case 2:
            {
               System.out.println("Listing information about: ");
               Menu.listingInfoOptions();
               int listMenuChoice = Validator.checkInput(in,1,3);
               switch(listMenuChoice)
               {
                  //show list of publishers and select one?
                  case 1:
                  {
                     List<Publisher> publisherList = businessLayer.getAllPublishers();

                     System.out.println("Publishers: ");
                     System.out.println("Select the publisher you want information about: ");

                     for (int i = 0; i < publisherList.size(); i++) {
                        Publisher pub = publisherList.get(i);
                        System.out.printf("%d. %s%n", i + 1, pub.getName());
                     }

                     int choice = Validator.checkInput(in, 0, publisherList.size());

                     if(choice > 0) {
                        System.out.println(publisherList.get(choice - 1));
                     }

                     break;
                  }
                  //show list of books and select one?
                  case 2:
                  {
                     List<Book> bookList = businessLayer.getAllBooks();

                     System.out.println("Books: ");
                     System.out.println("Select the book you want information about: ");

                     for (int i = 0; i < bookList.size(); i++){
                        Book book = bookList.get(i);

                        System.out.printf("%d. %s%n", i + 1, book.getTitle());
                     }

                     int bookChoice = Validator.checkInput(in, 0, bookList.size());

                     if(bookChoice > 0) {
                        Book book;
                        Publisher pub;
                        AuthoringEntity authoringEntity;

                        book = bookList.get(bookChoice - 1);
                        pub = book.getPub();
                        authoringEntity = book.getAE();
                        System.out.println("\nBook:");
                        System.out.println(book);
                        System.out.println("\nPublisher:");
                        System.out.println(pub);
                        System.out.println("\nAuthoring Entity:");
                        System.out.println(authoringEntity);
                     }

                     break;
                  }
                  //show list of writing groups and select 1?
                  case 3:
                  {
                     List<WritingGroup> writingGroupList = businessLayer.getAllWritingGroups();

                     for (int i = 0; i < writingGroupList.size(); i++){
                        WritingGroup group = writingGroupList.get(i);

                        System.out.printf("%d. %s", i + 1, group.getName());
                     }

                     int groupChoice = Validator.checkInput(in, 0, writingGroupList.size());

                     if(groupChoice > 0) {
                        System.out.println(writingGroupList.get(groupChoice - 1));
                     }

                     break;
                  }

                  case 0:
                  default:
               }
               break;
            }

            //delete book
            case 3:
            {
               List<Book> bookList = businessLayer.getAllBooks();

               System.out.println("Choose book to delete (0 to quit)");

               for(int i = 0; i < bookList.size(); i++){
                  Book iterate = bookList.get(i);
                  System.out.printf("%d. %-20s%n", i + 1, iterate.getISBN(), iterate.getTitle());
               }

               int bookChoice = Validator.checkInput(in, 0, bookList.size());

               if(bookChoice > 0) {
                  businessLayer.deleteBook(bookList.get(bookChoice - 1));
               }

               break;
            }

            //update book authority entity
            case 4:
            {
               List<Book> bookList = businessLayer.getAllBooks();
               List<AuthoringEntity> authoringEntityList = businessLayer.getAllAuthoringEntities();

               //show list of books. and select 1.
               for(int i = 0; i < bookList.size(); i++){
                  Book book = bookList.get(i);
                  System.out.printf("%d. ISBN: %-20sTitle: %-20s%n", i + 1, book.getISBN(), book.getTitle());
               }

               int bookChoice = Validator.checkInput(in, 0, bookList.size());

               //show list of authoring entities
               for(int i = 0; i < authoringEntityList.size(); i++){
                  AuthoringEntity entity = authoringEntityList.get(i);
                  System.out.printf("%d. Name: %-20s%n", i + 1, entity.getName());
               }

               int entityChoice = Validator.checkInput(in, 0, authoringEntityList.size());

               if(bookChoice > 0 && entityChoice > 0) {
                  Book bChoice = bookList.get(bookChoice - 1);
                  AuthoringEntity eChoice = authoringEntityList.get(entityChoice - 1);

                  bChoice.setAE(eChoice);

                  // Call business layer to update
                  businessLayer.updateBook(bChoice);
               }

               break;
            }

            //list the primary key of all rows of something
            case 5:
            {
               Menu.displayPrimaryKeyOptions();

               int primaryKeyInfoChoice = Validator.checkInput(in,1,3);

               switch(primaryKeyInfoChoice)
               {
                  //publishers
                  case 1:
                  {
                     System.out.println("Displaying publisher primary keys: ");
                     List<Publisher> publisherList = businessLayer.getAllPublishers();

                     for(Publisher pub : publisherList){
                        pub.displayPrimaryKey();
                     }
                     break;
                  }
                  //books
                  case 2:
                  {
                     System.out.println("Displaying book primary keys: ");
                     List<Book> bookList = businessLayer.getAllBooks();

                     for(Book book : bookList){
                        book.displayPrimaryKey();
                     }

                     break;
                  }
                  //authoring entities
                  case 3:
                  {
                     System.out.println("Displaying authoring entity primary keys: ");

                     List<AuthoringEntity> authoringEntityList = businessLayer.getAllAuthoringEntities();

                     for(AuthoringEntity entity : authoringEntityList){
                        entity.displayPrimaryKey();
                     }
                     System.out.println();

                     break;
                  }
               }
               break;
            }

            case 0:
               quit = true;
               break;
         }
      }
   } // End of the main method
} // End of Books class
